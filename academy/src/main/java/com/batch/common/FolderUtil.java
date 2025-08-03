package com.batch.common;

import java.util.Map;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;

/**
 * 
 * 
 * @author voidyard
 * @since 2010. 12. 1.
 * @version 1.0
 * @see (필요시)
 */
public class FolderUtil {
	
	public static final String DEFAULT_PRESERVATION_CD = "10";

	/**
	 * 새 폴더 생성시 관리속성 입력 (기본값)
	 * @param idfSession
	 * @param folderObj
	 * @return
	 * @throws Exception
	 */
	public static boolean addNewManagedAttribute(IDfSession idfSession, IDfFolder folderObj, IDfFolder parentFdObj) throws Exception {
		boolean isSuccess = false;
		Map<String, String> folderInfo = null;
		
		if (!folderObj.getFolderPath(0).startsWith("/SmartDisk_GROUP")) {
			return false;
		}
		
//		boolean isTransactionActive = false;
//		if (idfSession.isTransactionActive()) {
//			idfSession.commitTrans();
//			isTransactionActive = true;
//		}

		//부모 폴더로부터 관리속성 정보가져오기
		if(!DocumentUtil.isAspectAttached(parentFdObj)){
			DocumentUtil.attachAspectInternal(parentFdObj);
		}
		folderInfo = DocumentUtil.parseFolderAttrInfo(parentFdObj);
		String u_preservation_code = folderInfo.get("u_preservation_code");

		u_preservation_code = u_preservation_code.equals("") ? DEFAULT_PRESERVATION_CD : u_preservation_code;

		//folder aspect check
		if(!DocumentUtil.isAspectAttached(folderObj)){
			DocumentUtil.attachAspectInternal(folderObj);
		}

		folderObj.setString("ud_mobis_common.u_preservation_code", u_preservation_code); //1년
		folderObj.save();

//		if (isTransactionActive) {
//			idfSession.beginTrans();
//		}
		
		return isSuccess;
	}
}
