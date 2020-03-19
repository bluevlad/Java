package maf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import maf.beans.FileInfo;
import maf.logger.Logging;

public class FileUtils {
	static Log logger = LogFactory.getLog(FileUtils.class);

	/*******************************************************************************************************************************
	 * 파일을 리턴한다.
	 * <p> @ param path 파일패스
	 * <p> @ param fname 파일이름
	 * <p> @ return f : 파일 , null : 파일 없음
	 *     <p>
	 ******************************************************************************************************************************/
	public static File getFile(String path, String fname) {
		try {
			if (existFile(path, fname)) {
				File f = new File(path, fname);
				return f;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/*******************************************************************************************************************************
	 * 디렉토리가 있는지 확인한다.
	 * <p> @ param path 파일패스
	 * <p> @ return true:디렉토리 있음, false:디렉토리없음
	 *     <p>
	 ******************************************************************************************************************************/
	public static boolean existDirectory(String path) {
		boolean i = false;
		try {
			File f = new File(path);
			i = f.exists();
		} catch (Exception e) {
			i = false;
		}
		return i;
	}

	/*******************************************************************************************************************************
	 * 파일이 존재하는지 확인한다.
	 * <p> @ param path 파일패스
	 * <p> @ param filename 파일이름
	 * <p> @ return true:파일존재,false:파일없음
	 *     <p>
	 ******************************************************************************************************************************/
	public static boolean existFile(String path, String filename) {
		boolean chk = false;
		try {
			File f = new File(path, filename);
			chk = f.exists();
			f = null;
		} catch (Exception e) {
			chk = false;
		}
		return chk;
	}

	public static boolean existFile(String path) {
		boolean chk = false;
		try {
			File f = new File(path);
			chk = f.exists();
			f = null;
		} catch (Exception e) {
			chk = false;
		}
		return chk;
	}

	/*******************************************************************************************************************************
	 * 디렉토리를 생성한다.
	 * <p> @ param path 파일패스
	 * <p> @ return true:성공,false:실패
	 *     <p>
	 ******************************************************************************************************************************/
	public static void createDirectory(String path) throws FileUtilsException {
		boolean flag = false;
		try {
			File f = new File(path);
			File fp = f.getParentFile();
			if (!fp.exists()) {
				createDirectory(fp.getPath());
			}
			if (!f.exists()) {
				flag = f.mkdir();
				logger.info("Created Directory:" + f.getPath());
				if (!flag) {
					throw new FileUtilsException("Can't make dir : " + path);
				}
			}
		} catch (Exception e) {
			throw new FileUtilsException(e.getMessage(), e);
		}
	}

	/*******************************************************************************************************************************
	 * 파일을 삭제한다.
	 * <p> @ param path 파일패스
	 * <p> @ param name 파일이름
	 * <p> @ return i 파일 삭제 성공여부를 리턴한다.
	 *     <p>
	 ******************************************************************************************************************************/
	public static boolean delFile(String path, String name) {
		boolean chk = false;
		try {
			Logging.log(FileUtils.class, "Delete File : " + path + "," + name);
			if (existFile(path, name)) {
				File f = new File(path, name);
				chk = f.delete();
			} else {
				chk = true;
			}
		} catch (Exception e) {
			chk = false;
		}
		return chk;
	}

	/**
	 * 경로를 포함한 절대경로 의 파일을 삭제 한다.
	 * 
	 * @param absFile
	 * @return
	 */
	public static boolean delFile(String absFile) {
		java.io.File f = new java.io.File(absFile);
		return delFile(f);
	}

	public static boolean delFile(File f) {
		if (f.exists()) {
			if (f.delete()) return true;
			else return false;
		} else {
			return true;
		}
	}

	// /****************************************************************
	// * 디렉토리를 삭제한다.<p>
	// *@ param path 파일패스<p>
	// *@ param <p>
	// *@ return i 디렉토리 삭제 성공여부 리턴<p>
	// ****************************************************************/
	// public boolean delDirectory(String path)
	// {
	// boolean i = false;
	// try
	// {
	// File f = new File(path);
	//
	// if(existDirectory(path))
	// {
	// f.delete();
	// i = true;
	// }
	// }
	// catch(Exception e)
	// {
	// i = false;
	// }
	// return i;
	// }
	/*******************************************************************************************************************************
	 * 파일크기를 리턴한다.
	 * <p> @ param path 파일패스
	 * <p> @ param name 파일이름
	 * <p> @ return 파일사이즈
	 *     <p>
	 ******************************************************************************************************************************/
	public static long getFileSize(String path, String name) {
		long i = 0;
		if (name.equals("") || name == null || name.toUpperCase().equals("NULL")) return 0;
		try {
			File f = new File(path + name);
			if (existFile(path, name)) {
				i = f.length();
			} else {
				i = 0;
			}
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	/*******************************************************************************************************************************
	 * 파일이름을 리턴한다.
	 * <p> @ param file 파일
	 * <p> @ return 파일이름
	 *     <p>
	 ******************************************************************************************************************************/
	public static String getFileName(File file) {
		String FName = "";
		try {
			if (file == null) return "";
			if (file.exists()) {
				FName = file.getName();
			} else {
				FName = "";
			}
		} catch (Exception e) {
			FName = "";
		}
		return FName;
	}

	/**
	 * 대상폴더의 존재여부 확인 후 없으면 폴더를 생성 한다.
	 * 
	 * @param FilePath
	 * @throws FileUtilsException
	 */
	public static void CheckAndMakeDir(String FilePath) throws FileUtilsException {
		// 파일패스 뒤에 separator가 있는지 확인한다.
		FilePath = chkSeparator(FilePath);
		// 복사할 곳에 디렉토리가 있는지 확인한다.
		if (!existDirectory(FilePath)) {
			// 디렉토리가 없으면 만든다.
			createDirectory(FilePath);
		}
	}

	/*******************************************************************************************************************************
	 * 파일 패스 뒤에 \ 혹은 / 이 없으면 붙여주는 메서드
	 * <p> @ param path 파일패스
	 * <p> @ param
	 * <p> @ return tempp 고쳐진 파일 패스
	 *     <p>
	 ******************************************************************************************************************************/
	public static String chkSeparator(String path) {
		String tempp = "";
		try {
			tempp = path;
			if (!tempp.substring(tempp.length() - 1, tempp.length())
			          .equals(File.separator)) {
				tempp = tempp + File.separator;
			}
		} catch (Exception e) {
		}
		return tempp;
	}

//	/**
//	 * File.renameTo 는 같은 파일시스템 내에서만 가능 하므로 renameTo가 안될경우 moveFile을 이용
//	 * 오류시 DestFile 삭제
//	 * @param SrcFile
//	 * @param DestFile
//	 * @return
//	 */
//	public static boolean moveFile(File srcFile, File sestFile) {
//		boolean chk = false;
//		FileInputStream fi = null;
//		try {
//			fi = new FileInputStream(srcFile);
//			chk = StreamToFile(fi, sestFile);
//		} catch (Throwable e) {
//		} finally {
//			if (fi != null) try {
//				fi.close();
//				fi = null;
//			} catch (Exception _ignored) {
//			}
//		}
//		return chk;
//	}

	public static boolean streamToFile(InputStream fi, File DestFile) {
		boolean chk = false;
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(DestFile);
			if (fi != null && fo != null) {
				byte[] b = new byte[1024];
				int numRead = fi.read(b);
				while (numRead != -1) {
					fo.write(b, 0, numRead);
					numRead = fi.read(b);
				}
				fo.flush();
				// logger.info("Moved file="+SrcFile+" outputFile="+DestFile);
				chk = true;
			} else {
				if (DestFile != null) {
					DestFile.delete();
				}
			}
		} catch (Throwable e) {
			try {
				if (DestFile != null) {
					DestFile.delete();
				}
			} catch (Throwable ex) {
			}
			logger.error(e.getMessage());
		} finally {
			if (fi != null) try {
				fi.close();
				fi = null;
			} catch (Exception _ignored) {
			}
			if (fo != null) try {
				fo.close();
				fo = null;
			} catch (Exception _ignored) {
			}
		}
		return chk;
	}

	/**
	 * 파일 복사 
	 * @param SrcFile
	 * @param DestFile
	 * @return
	 */
	public static boolean copyFile(File SrcFile, File DestFile) {
		boolean chk = false;
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(SrcFile);
			chk = streamToFile(fi, DestFile);
		} catch (Throwable e) {
			try {
				if (DestFile != null) {
					DestFile.delete();
				}
			} catch (Throwable ex) {
			}
			logger.error(e.getMessage());
		} finally {
			if (fi != null) try {
				fi.close();
				fi = null;
			} catch (Exception _ignored) {
			}
		}
		return chk;
	}

	/**
	 * 해당 폴더의 파일정보를 List에 담아  돌려 준다.
	 * 
	 * @param fullPath
	 * @return FileBean[]
	 */
	public static FileInfo[] getFileList(String fullPath) {
		if (fullPath == null) {
			return null;
		}
		if (!File.separator.equals(fullPath.substring(fullPath.length() - 1))) {
			fullPath = fullPath + File.separator;
		}
		File file = new File(fullPath);
		String[] list = file.list();
		List fileList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				file = new File(fullPath + list[i]);
				if (!"unknown".equals(file.getName())) {
					fileList.add(new FileInfo(file));
				}
			}
		}
		return (FileInfo[]) fileList.toArray(new FileInfo[0]);
	}

	/**
	 * 디렉토리에 파일이 포함되어 있는지 검사
	 * 
	 */
	public static boolean isContainFileDir(String fullPath, String[] list) {
		File file = null;
		for (int i = 0; i < list.length; i++) {
			file = new File(fullPath + list[i]);
			if (file.isDirectory()) {
				File[] listFile = file.listFiles();
				if (listFile.length > 0) return true;
			}
		}
		return false;
	}

	/**
	 * 폴더나 파일의 생성 및 이름변경시에 특수문자가 포함되어 있는지 검사 "." 도 포함을 시켜야 하나 파일이름 및 폴더이름 검사에 공통으로 사용하므로 제외한다. 폴더이름 검사는 별도로 구현함.
	 */
	public static boolean isInValidateName(String name) {
		RE invalidName = new RE("\\\\|\\/|\\:|\\*|\\?|\\\"|\\'|\\<|\\>|\\||\\../");
		invalidName.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		return invalidName.match(name);
	}
	//	/**
	//	 *  파일면중 확장자를 제외한 부문만 넘겨 준다.
	//	 */
	//	public static String getFileNameWithoutExt(String fileName) {
	//		if (fileName == null) {
	//			return null;
	//		} else {
	//			int x = fileName.indexOf(".");
	//			if(x > 0) {
	//				return fileName.substring(0,x);
	//			} else {
	//				return fileName;
	//			}
	//		}
	//		return S
	//	}
	/**
	 * @ToDo 
	 * @return
	 */
	//	public static String getFileType() {
	//		RE imgFile = new RE("\\.jpg$|\\.jpeg$|\\.gif$|\\.png$|\\.bmp$");
	//		imgFile.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
	//		return null;
	//
	//	}
}
