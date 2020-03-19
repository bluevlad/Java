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
	private String tempFilename = null; // 임시파일명 (경로 포함)
	private String newFilename = null; // 저장된 파일명(경로 불포함)
	private String newFilePath = null; // 새로저장된 경로
	private String originalFileName = null; // 원본 파일
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
	 * mimeType을 돌려줌
	 * @return
	 */
	public String getContentType() {
		return type;
	}

//	/**
//	 * 경로가 포함된 TempFilenme을 돌려줌 
//	 * @return
//	 */
//	public String getTmpFilename() {
//		return tempFilename;
//	}
//	/**
//	 * 임시 파일 오브젝트를 넘겨줌
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
	 * form에 등록된 파일명
	 * @return
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}



	/**
	 * 경로가 포함되지 않은 저장된 파일명을 돌려줌 
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
	 * 파일이 저장된 경로를 돌려줌
	 * 주의!!! fileseprater가 안붙습니다.
	 * @return
	 */
	public String getNewFilePath() {
		return newFilePath;
	}

	/**
	 * 새로저장된 파일명
	 * @return
	 */
	public String getNewfilename() {
		return newFilename;
	}

	/**
	 * 파일 크기
	 * @return
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * 
	 * @param filePath 새로저장될 파일 경로 (없으면 생성)
	 * @param FileName 저장할 파일명
	 * 파일 존재시 기존 파일 덮어씀. 
	 * @return
	 */
	public boolean SaveAs(String filePath, String FileName) throws UploadedFileException {
		return SaveAs(filePath, FileName, true);
	}

	/**
	 * 
	 * @param filePath : 새로저장될 파일 경로 (없으면 생성)
	 * @param canOverwrite : 기존 파일이 있을경우 Overwrite 할것인지? true면 덮어씀. false면 파일면 바뀌어 저장 
	 * @param fileName 저장할 파일명 
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
	 * 파일을 FilePath 경로에 Originale 파일명으로 저장
	 * MyHttpServletRequest 의 FileSaveTo 권장 
	 * @param FilePath : 새로저장될 파일 경로 (없으면 생성)
	 * @return
	 */
	public boolean SaveTo(String FilePath) throws UploadedFileException {
		return SaveTo(FilePath, false);
	}

	/**
	 * 파일을 FilePath 경로에 Originale 파일명으로 저장
	 * @param FilePath : 새로저장될 파일 경로 (없으면 생성) 
	 * @param canOverwrite : 기존 파일이 있을경우 Overwrite 할것인지? 
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
	 * temp 경로에 저장된 임시파일 삭제
	 *
	 */
	public void deleteFile() {
		UploadedFileUtil._deleteFile(tempFilename);
		if(fileItem != null) {
			fileItem.delete();
		}
	}

	/**
	 * 새로이 생성된 파일을 삭제
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
	 * Upload 관련 모든 파일 지움 
	 * 오류 낳을때 임시, 저장된 파일 지움 
	 *
	 */
	public void deleteAllFile() {
		this.deleteFile();
		this.deleteNewFile();
	}
	

}
