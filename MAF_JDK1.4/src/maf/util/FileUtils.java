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
	 * ������ �����Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ param fname �����̸�
	 * <p> @ return f : ���� , null : ���� ����
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
	 * ���丮�� �ִ��� Ȯ���Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ return true:���丮 ����, false:���丮����
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
	 * ������ �����ϴ��� Ȯ���Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ param filename �����̸�
	 * <p> @ return true:��������,false:���Ͼ���
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
	 * ���丮�� �����Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ return true:����,false:����
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
	 * ������ �����Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ param name �����̸�
	 * <p> @ return i ���� ���� �������θ� �����Ѵ�.
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
	 * ��θ� ������ ������ �� ������ ���� �Ѵ�.
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
	// * ���丮�� �����Ѵ�.<p>
	// *@ param path �����н�<p>
	// *@ param <p>
	// *@ return i ���丮 ���� �������� ����<p>
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
	 * ����ũ�⸦ �����Ѵ�.
	 * <p> @ param path �����н�
	 * <p> @ param name �����̸�
	 * <p> @ return ���ϻ�����
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
	 * �����̸��� �����Ѵ�.
	 * <p> @ param file ����
	 * <p> @ return �����̸�
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
	 * ��������� ���翩�� Ȯ�� �� ������ ������ ���� �Ѵ�.
	 * 
	 * @param FilePath
	 * @throws FileUtilsException
	 */
	public static void CheckAndMakeDir(String FilePath) throws FileUtilsException {
		// �����н� �ڿ� separator�� �ִ��� Ȯ���Ѵ�.
		FilePath = chkSeparator(FilePath);
		// ������ ���� ���丮�� �ִ��� Ȯ���Ѵ�.
		if (!existDirectory(FilePath)) {
			// ���丮�� ������ �����.
			createDirectory(FilePath);
		}
	}

	/*******************************************************************************************************************************
	 * ���� �н� �ڿ� \ Ȥ�� / �� ������ �ٿ��ִ� �޼���
	 * <p> @ param path �����н�
	 * <p> @ param
	 * <p> @ return tempp ������ ���� �н�
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
//	 * File.renameTo �� ���� ���Ͻý��� �������� ���� �ϹǷ� renameTo�� �ȵɰ�� moveFile�� �̿�
//	 * ������ DestFile ����
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
	 * ���� ���� 
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
	 * �ش� ������ ���������� List�� ���  ���� �ش�.
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
	 * ���丮�� ������ ���ԵǾ� �ִ��� �˻�
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
	 * ������ ������ ���� �� �̸�����ÿ� Ư�����ڰ� ���ԵǾ� �ִ��� �˻� "." �� ������ ���Ѿ� �ϳ� �����̸� �� �����̸� �˻翡 �������� ����ϹǷ� �����Ѵ�. �����̸� �˻�� ������ ������.
	 */
	public static boolean isInValidateName(String name) {
		RE invalidName = new RE("\\\\|\\/|\\:|\\*|\\?|\\\"|\\'|\\<|\\>|\\||\\../");
		invalidName.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		return invalidName.match(name);
	}
	//	/**
	//	 *  ���ϸ��� Ȯ���ڸ� ������ �ι��� �Ѱ� �ش�.
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
