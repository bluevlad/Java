/*
 * FileSender.java, @ 2005-03-14
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.downloader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 *  파일 전송 프로토콜 
 *  chagelog
 *  2007-05-11 파일 이어받기 지원기능 추가 
 */
public class HttpFileSender {
	private final int DEFAULT_BUF_SIZE = 65536;
	private static String DEFAULT_ENCODING = "ISO-8859-1";
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	//	private static final RE REG_IE = new org.apache.regexp.RE("(MSIE)",
	//	                                                          RE.MATCH_CASEINDEPENDENT);
	private Log logger = LogFactory.getLog(HttpFileSender.class);
	public static final String DN_YN_INLINE = "inline";
	public static final String DN_YN_DOWNLOAD = "down";

	public HttpFileSender(PageContext pagecontext) {
		this.request = (HttpServletRequest) pagecontext.getRequest();
		this.response = (HttpServletResponse) pagecontext.getResponse();
	}

	public HttpFileSender(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public boolean send(File file) throws IOException {
		return process(file, file.getName(), true, true, DEFAULT_ENCODING);
	}
	
	public boolean send(File file, String newFileName) throws IOException {
		return process(file, newFileName, true, true, DEFAULT_ENCODING);
	}

	public boolean send(File file, String newFileName, boolean dnyn) throws IOException {
		return process(file, newFileName, dnyn, true, DEFAULT_ENCODING);
	}

	private boolean process(File file, String newFileName, boolean dnyn, boolean cacheyn, String encording) {
		boolean chk = false;
		try {
			if (file == null || !file.exists()) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				
				
				chk = _process(file, newFileName, dnyn, cacheyn, encording);
			}
		} catch (Throwable e) {
			logger.error(e.getClass() + " : " + e.getMessage());
			try {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (Exception ignore) {
			}
		}
		return chk;
	}

	private boolean _process(File file, String newFileName, boolean dnyn, boolean cacheyn, String encording) throws IOException {
		long seek_start = 0L;
		byte abyte0[] = null;
		if (encording == null) {
			encording = "";
		}
		String seek_range = request.getHeader("Range");
		String sendFileName = "";
		String s4 = System.getProperty("file.encoding");
		String s5 = response.getCharacterEncoding();
		if (newFileName.length() > 0) {
			sendFileName = newFileName;
		}
		if (encording.length() > 0) {
			sendFileName = new String(newFileName.getBytes(), encording);
		}
		String agent = request.getHeader("USER-AGENT");
		response.setContentType("application/octet-stream");
		String disposition_type = null;
		if (dnyn) {
			disposition_type = "attachment; ";
		} else {
			disposition_type = "inline; ";
		}
		StringBuffer disposition = new StringBuffer(30);
		if (agent != null) {
			if (agent.indexOf("MSIE") >= 0) {
				int k = agent.indexOf('M', 2);
				String ver = agent.substring(k + 5, k + 8);
				if (ver.equalsIgnoreCase("5.5")) {
				} else {
					disposition.append(disposition_type);
				}
			} else {
				disposition.append(disposition_type);
			}
		} else {
			disposition.append(disposition_type);
		}
		disposition.append("filename=\"");
		disposition.append(sendFileName.replaceAll("\"", "'"));
		disposition.append("\"");
//		logger.debug(request.getRemoteAddr()+"," + agent + ", Content-Disposition:" +  disposition.toString());
		response.setHeader("Content-Disposition", disposition.toString());
		
		
		if (seek_range != null) {
			long l1 = 0L;
			int i1 = seek_range.indexOf('=') + 1;
			int j1 = seek_range.indexOf('-');
			String s8 = seek_range.substring(i1, j1);
			seek_start = Long.parseLong(s8);
			l1 = file.length() - seek_start;
			if (l1 < 0L) {
				l1 = 0L;
			}
			response.setHeader("Content-Range", Long.toString(seek_start)
			        + "-"
			        + (l1 - 1L)
			        + "/"
			        + file.length());
			response.setContentLength((int) l1);
		} else {
			response.setContentLength((int) file.length());
		}
		
		if (cacheyn) {
			response.setHeader("Pragma", "cache");
		} else {
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
		}
		abyte0 = new byte[DEFAULT_BUF_SIZE];
		RandomAccessFile randomaccessfile = new RandomAccessFile(file, "r");
		if (seek_range != null) {
			randomaccessfile.seek(seek_start);
		}
		ServletOutputStream servletoutputstream = response.getOutputStream();
		long l2 = System.currentTimeMillis();
		int j = 0;
		try {
			int i = 0;
			while ((i = randomaccessfile.read(abyte0, 0, DEFAULT_BUF_SIZE)) != -1) {
				servletoutputstream.write(abyte0, 0, i);
				servletoutputstream.flush();
				j += i;
			}
		} finally {
			if (randomaccessfile != null) {
				try {
					randomaccessfile.close();
				} catch (Exception ignore) {
				}
			}
			if (servletoutputstream != null) {
				try {
					servletoutputstream.close();
				} catch (Exception ignore) {
				}
			}
			randomaccessfile = null;
			servletoutputstream = null;
		}
		boolean flag = false;
		if ((long) j == file.length()) {
			flag = true;
		}
		return flag;
	}
	//
	//	private void oldsend(File file, String newFileName, String cmd, boolean cache) {
	//		String rawFileName = null;
	//		try {
	//			String os = request.getHeader("USER_AGENT");
	//			if (os == null) {
	//				os = request.getHeader("user-agent");
	//			}
	//			if (file != null && file.exists()) {
	//				if (file.canRead()) {
	//					String dnyn = null;
	//					if ("save".equals(cmd) || "down".equals(cmd)) {
	//						dnyn = "attachment; ";
	//					} else {
	//						dnyn = "inline; ";
	//					}
	//					rawFileName = newFileName;
	//					//                    rawFileName = java.net.URLEncoder.encode( fb.getFilename(), "8859_1" );
	//					//                    fb.setFilename("시험용 .hwp");
	//					//                    rawFileName = "시험용 .hwp";
	//					//                    if(REG_IE.match(os)) {
	//					//                    	rawFileName = i18n.convertTo(fb.getFilename(),"euc-kr","8859_1") ;
	//					//                    } else {
	//					rawFileName = i18n.convertTo(newFileName, "utf-8", "8859_1");
	//					//                    	rawFileName = java.net.URLEncoder.encode( fb.getFilename(), "UTF-8" );
	//					//                    }
	//					//                    Logging.log(this.getClass(), os + " FileName: " + rawFileName);
	//					if (REG_IE.match(os)) {
	//						rawFileName = java.net.URLEncoder.encode(newFileName, "UTF-8");
	//						rawFileName = "filename=\""
	//						        + rawFileName.replaceAll("\"", "'")
	//						        + "\"";
	//						if (os.indexOf("MSIE 5.5") != -1) {
	//							//                	        Logging.log(this.getClass(), "Download MSIE 5.5");
	//							response.setContentType("doesn/matter");
	//							response.setHeader("Content-Disposition", rawFileName);
	//						} else if (os.indexOf("MSIE 5.0") != -1) {
	//							//                	        Logging.log(this.getClass(), "Download MSIE 5.0");
	//							//                            response.setContentType("file/unknown");
	//							response.setContentType("doesn/matter");
	//							//                            response.setContentType("application/x-msdownload"); 
	//							response.setHeader("Content-Disposition", "attachment;"
	//							        + rawFileName);
	//						} else if (os.indexOf("MSIE 5.1") != -1) {
	//							//                	        Logging.log(this.getClass(), "Download MSIE 5.1");
	//							response.setContentType("file/unknown");
	//							response.setHeader("Content-Disposition", "attachment;"
	//							        + rawFileName);
	//						} else {
	//							//                	        Logging.log(this.getClass(), "Download MSIE 6.0");
	//							response.setContentType("application/x-msdownload");
	//							response.setHeader("Content-Disposition", dnyn
	//							        + " "
	//							        + rawFileName);
	//						}
	//					} else {
	//						rawFileName = i18n.convertTo(newFileName, "utf-8", "8859_1");
	//						rawFileName = "filename=\""
	//						        + rawFileName.replaceAll("\"", "'")
	//						        + "\"";
	//						response.setContentType(MimeType.getMimeType(newFileName));
	//						response.setHeader("Content-Disposition", dnyn
	//						        + " "
	//						        + rawFileName);
	//					}
	//					response.setHeader("Content-Length", file.length() + "");
	//					response.setHeader("Content-Transfer-Encoding", "binary");
	//					if (cache) {
	//						response.setHeader("Pragma", "cache");
	//					} else {
	//						response.setHeader("Cache-Control", "no-cache, must-revalidate");
	//						response.setHeader("Pragma", "no-cache");
	//						response.setHeader("Expires", "0");
	//					}
	//					FileInputStream in = null;
	//					ServletOutputStream output = null;
	//					try {
	//						in = new FileInputStream(file);
	//						output = response.getOutputStream();
	//						byte[] b = new byte[16 * 1024];
	//						int numRead = in.read(b);
	//						while (numRead != -1) {
	//							output.write(b, 0, numRead);
	//							numRead = in.read(b);
	//						}
	//						output.flush();
	//					} finally {
	//						if (output != null) try {
	//							output.close();
	//						} catch (Exception _ignore) {
	//						}
	//						if (in != null) try {
	//							in.close();
	//						} catch (Exception _ignore) {
	//						}
	//						output = null;
	//						in = null;
	//					}
	//					//Logging.log(this.getClass(), "Sent!!!");
	//				}
	//			} else {
	//				response.sendError(HttpServletResponse.SC_NOT_FOUND);
	//			}
	//		} catch (Throwable e) {
	//			logger.error(e.getMessage());
	//			try {
	//				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	//			} catch (Exception x) {
	//			}
	//		}
	//	}
}