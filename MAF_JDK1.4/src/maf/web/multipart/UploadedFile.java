package maf.web.multipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.exception.UploadedFileException;

public class UploadedFile  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tempFilename = null; // �ӽ����ϸ� (��� ����)
	private String newFilename = null; // ����� ���ϸ�(��� ������)
	private String newFilePath = null; // ��������� ���
	private String originalFileName = null; // ���� ����
	private String type = null; // content Type
	private long fileSize = 0;
	final String TMP_FILE_PREFIX = "MI_";
	private FileItem  fileItem = null;

	public UploadedFile() {
		
	}
	
	public UploadedFile(FileItem  fileItem) {
		this.fileItem = fileItem;
		this.originalFileName = StringUtils.getFilename(fileItem.getName());
		this.type = fileItem.getContentType();
		this.fileSize =  fileItem.getSize();
	}
	
	//    FileUtil fu = new FileUtil();
	public void setUploadedFile(String tempFilename, String original, String type,
	        long filesize) {
		this.tempFilename = tempFilename;
		this.originalFileName = original;
		this.type = type;
		this.fileSize = filesize;
	}

	/**
	 * mimeType�� ������
	 * @return
	 */
	public String getContentType() {
		return type;
	}

//	/**
//	 * ��ΰ� ���Ե� TempFilenme�� ������ 
//	 * @return
//	 */
//	public String getTmpFilename() {
//		return tempFilename;
//	}
//	/**
//	 * �ӽ� ���� ������Ʈ�� �Ѱ���
//	 * @return
//	 */
//	public File getTempFile() {
//		if (tempFilename == null) {
//			return null;
//		} else {
//			return new File(tempFilename);
//		}
//	}
	
	
	public InputStream getFileInputStream() throws IOException {
		if(fileItem != null) {
			return fileItem.getInputStream();
		} else {
			return new FileInputStream(new File(tempFilename));
		}
	}
	
	/**
	 * form�� ��ϵ� ���ϸ�
	 * @return
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}



	/**
	 * ��ΰ� ���Ե��� ���� ����� ���ϸ��� ������ 
	 * @return
	 */
	public File getNewFile() {
		if (newFilePath != null && newFilename != null) {
			return new File(newFilePath, newFilename);
		} else {
			return null;
		}
	}

	/**
	 * ������ ����� ��θ� ������
	 * ����!!! fileseprater�� �Ⱥٽ��ϴ�.
	 * @return
	 */
	public String getNewFilePath() {
		return newFilePath;
	}

	/**
	 * ��������� ���ϸ�
	 * @return
	 */
	public String getNewfilename() {
		return newFilename;
	}

	/**
	 * ���� ũ��
	 * @return
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * 
	 * @param filePath ��������� ���� ��� (������ ����)
	 * @param FileName ������ ���ϸ�
	 * ���� ����� ���� ���� ���. 
	 * @return
	 */
	public boolean SaveAs(String filePath, String FileName) throws UploadedFileException {
		return SaveAs(filePath, FileName, true);
	}

	/**
	 * 
	 * @param filePath : ��������� ���� ��� (������ ����)
	 * @param canOverwrite : ���� ������ ������� Overwrite �Ұ�����? true�� ���. false�� ���ϸ� �ٲ�� ���� 
	 * @param fileName ������ ���ϸ� 
	 * @return
	 */
	public boolean SaveAs(String filePath, String fileName, boolean canOverwrite)
	        throws UploadedFileException {
		File newFile = null;
		if (this.fileSize > 0) {
			try {
				FileUtils.CheckAndMakeDir(filePath);
				//fileName = new String(fileName.getBytes("MS949"),
				// "ISO-8859-1");
				newFile = new File(filePath, fileName);
				if (!canOverwrite) {
					newFile = UploadedFileUtil.rename(newFile);
				}
//				File TempFile = getTempFile();
				InputStream fis = getFileInputStream();

//				if (TempFile.renameTo(newFile)) {
//					this.newFilename = newFile.getName();
//					System.out.println(newFilename);
//					this.newFilePath = newFile.getParent();
//					return true;
//				} else {
					if (FileUtils.streamToFile(fis, newFile)) {
						this.newFilePath = newFile.getParent();
						this.newFilename = newFile.getName();
						return true;
					} else {
						throw new UploadedFileException("can't make " + newFile);
					}
//				}
			} catch (Throwable e) {
				if (newFile != null) {
					newFile.delete();
				}
				Log logger = LogFactory.getLog(getClass());
				logger.error("UploadedFile.SaveAS()  : "
				        + filePath
				        + ", "
				        + fileName
				        + " :"
				        + e.getMessage());
				throw new UploadedFileException(e.getMessage(), e);
			}
		} else {
			return true;
		}
	}

	/**
	 * ������ FilePath ��ο� Originale ���ϸ����� ����
	 * MyHttpServletRequest �� FileSaveTo ���� 
	 * @param FilePath : ��������� ���� ��� (������ ����)
	 * @return
	 */
	public boolean SaveTo(String FilePath) throws UploadedFileException {
		return SaveTo(FilePath, false);
	}

	/**
	 * ������ FilePath ��ο� Originale ���ϸ����� ����
	 * @param FilePath : ��������� ���� ��� (������ ����) 
	 * @param canOverwrite : ���� ������ ������� Overwrite �Ұ�����? 
	 * @return
	 */
	public boolean SaveTo(String FilePath, boolean canOverwrite)
	        throws UploadedFileException {
		// String NewFilePath ;
		// File NewFile = null;
		if (this.originalFileName == null) {
			return false;
		}
		try {
			return SaveAs(FilePath, originalFileName, canOverwrite);
		} catch (Exception e) {
			throw new UploadedFileException(e.getMessage(), e);
		}
	}

	/**
	 * temp ��ο� ����� �ӽ����� ����
	 *
	 */
	public void deleteFile() {
		UploadedFileUtil._deleteFile(tempFilename);
		if(fileItem != null) {
			fileItem.delete();
		}
	}

	/**
	 * ������ ������ ������ ����
	 *
	 */
	public void deleteNewFile() {
		try {
			java.io.File f = new java.io.File(newFilePath
			        + File.separator
			        + this.newFilename);
			if (f != null) {
				f.delete();
			}
		} catch (Throwable e) {
		}
	}

	/**
	 * Upload ���� ��� ���� ���� 
	 * ���� ������ �ӽ�, ����� ���� ���� 
	 *
	 */
	public void deleteAllFile() {
		this.deleteFile();
		this.deleteNewFile();
	}
	

}
