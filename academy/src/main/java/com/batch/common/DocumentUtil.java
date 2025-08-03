package com.batch.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.FastDateFormat;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfAliasSet;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.client.aspect.IDfAspects;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfTime;
import com.documentum.fc.common.IDfList;
import com.documentum.fc.common.IDfTime;

public class DocumentUtil {

	public DocumentUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 1 1년 3 3년 5 5년 XX 영구
	 */
	public static String getExpireDateToDfTimePattern26(String startDate,
			String preservationCode) throws Exception {
		if ("XX".equals(preservationCode)) {
			return "2999/12/30 23:59:59";
		}

		FastDateFormat fdf = FastDateFormat.getInstance("yyyy/MM/dd hh:mm:ss",
				TimeZone.getDefault(), Locale.getDefault());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(Util.parseDate(startDate, "yyyy/MM/dd hh:mm:ss"));

		if ("1".equals(preservationCode)) {
			cal.add(GregorianCalendar.YEAR, 1);
		} else if ("3".equals(preservationCode)) {
			cal.add(GregorianCalendar.YEAR, 3);
		} else if ("5".equals(preservationCode)) {
			cal.add(GregorianCalendar.YEAR, 5);
		} else if ("10".equals(preservationCode)) {
			cal.add(GregorianCalendar.YEAR, 10);
		}

		return fdf.format(cal.getTime());
	}

	public static String getContentTypeByFileName(IDfSession dfcSession,
			String sFileName) throws DfException {
		String sDosExtension = getDosExtension(sFileName);
		sDosExtension = sDosExtension.toLowerCase();
		String sContentType = getContentTypeByExtension(dfcSession,
				sDosExtension);
		return sContentType;
	}

	public static String getDosExtension(String fileName) {
		int iLastIdx = fileName.lastIndexOf(".");
		if (iLastIdx >= 0) {
			return fileName.substring(iLastIdx + 1, fileName.length())
					.toLowerCase();
		} else {
			return "";
		}
	}

	public static String getContentTypeByExtension(IDfSession dfcSession,
			String sDosExtension) throws DfException {
		sDosExtension = sDosExtension.toLowerCase();
		String sContentType = getContentType(sDosExtension);
		if ("unknown".equals(sContentType)) {
			String contentType = "unknown";
			String sDQL = "SELECT name FROM dm_format "
					    + " WHERE dos_extension='" + sDosExtension + "'";

			DfQuery dfQuery = new DfQuery();
			dfQuery.setDQL(sDQL);
			// Log.query(sDQL);
			IDfCollection dfCol = null;
			try {
				dfCol = dfQuery.execute(dfcSession, IDfQuery.DF_READ_QUERY);
				if (dfCol.next())
					sContentType = dfCol.getString("name");
			} catch (DfException de) {
				throw de;
			} finally {
				if (dfCol != null)
					dfCol.close();
			}

		}
		return sContentType;
	}

	public static String getContentType(String sDosExtension) {
		String sContentType = "unknown";
		sDosExtension = sDosExtension.toLowerCase();

		if (sDosExtension == null) {
			sContentType = null;
		} else if (sDosExtension.equals("html")) {
			sContentType = "html";
		} else if (sDosExtension.equals("htm")) {
			sContentType = "html";
		} else if (sDosExtension.equals("doc")) {
			sContentType = "msw8";
		} else if (sDosExtension.equals("ppt")) {
			sContentType = "ppt8";
		} else if (sDosExtension.equals("pptx")) {
			sContentType = "ppt12";
		} else if (sDosExtension.equals("xls")) {
			sContentType = "excel8book";
		} else if (sDosExtension.equals("hwp")) {
			return "hwp";
		} else if (sDosExtension.equals("txt")) {
			sContentType = "crtext";
		} else if (sDosExtension.equals("log")) {
			sContentType = "crtext";
		} else if (sDosExtension.equals("ini")) {
			sContentType = "crtext";
		} else if (sDosExtension.equals("bat")) {
			sContentType = "crtext";
		} else if (sDosExtension.equals("pdf")) {
			sContentType = "pdf";
		} else if (sDosExtension.equals("zip")) {
			sContentType = "zip";
		} else if (sDosExtension.equals("jpg")) {
			sContentType = "jpeg";
		} else if (sDosExtension.equals("jpeg")) {
			sContentType = "jpeg";
		} else if (sDosExtension.equals("tif")) {
			sContentType = "tiff";
		} else if (sDosExtension.equals("bmp")) {
			sContentType = "bmp";
		} else if (sDosExtension.equals("gif")) {
			sContentType = "gif";
		} else if (sDosExtension.equals("mht")) {
			sContentType = "mht";
		} else if (sDosExtension.equals("avi")) {
			sContentType = "avi";
		} else if (sDosExtension.equals("asf")) {
			sContentType = "asf";
		} else if (sDosExtension.equals("wmv")) {
			sContentType = "wmv";
		} else if (sDosExtension.equals("gul")) {
			sContentType = "gul";
		} else if (sDosExtension.equals("dwg")) {
			sContentType = "acad";
		} else {
			sContentType = "unknown";
		}
		return sContentType;
	}


	public static IDfFolder getFolderByPath(IDfSession idfSession, String sPath) throws Exception {
		String strReturn = "";

		IDfFolder idffolder;
		
		idffolder = (IDfFolder) idfSession.getObjectByPath(sPath);

		return idffolder;
	}// getFolderByPath()
		
	/**
	 * @param fdSecurityCd �대뜑 蹂댁븞�깃툒
	 * @param presetSecurityCd �ъ슜��preset���ㅼ젙�섏뼱 �덈뒗 蹂댁븞�깃툒
	 * @param drmHeader DRM 蹂댁븞�깃툒��異붿텧�섍린 �꾪븳 
	 * @return 蹂댁븞�깃툒
	 * @throws Exception
	 */
	public static String getSecurityCode(String presetSecurityCd) throws Exception {
		String security_code = "G10002"; //대외비
		
		if(presetSecurityCd != null && !presetSecurityCd.equals("")){
			security_code = presetSecurityCd;
		}
		
		return security_code;
	}
	
	/**
	 * @param fdPreservationCd �대뜑 蹂댁〈�꾪븳
	 * @param presetPreservationCd �ъ슜��preset���ㅼ젙�섏뼱 �덈뒗 蹂댁〈�꾪븳
	 * @return 蹂댁〈�꾪븳
	 * @throws Exception
	 */
	public static String getPreservationCode(String fdPreservationCd, String presetPreservationCd) throws Exception {
		String preservation_code = "10"; //1��
		int fdPreservation = 0;
		int presestPreservation = 0;
		
		if(fdPreservationCd != null && !fdPreservationCd.equals("")){
			fdPreservation = Integer.parseInt(fdPreservationCd);
			preservation_code = fdPreservationCd;
		}
		
		if(presetPreservationCd != null && !presetPreservationCd.equals("")){
			presestPreservation = Integer.parseInt(presetPreservationCd);
			
			if((presestPreservation - fdPreservation) >= 0){
				preservation_code = presetPreservationCd;
			}
		}
		
		if ("99".equals(preservation_code)) {
			preservation_code = "XX";
		}
		
		return preservation_code;
	}
	
	public static String getIsHidden(String fdIsHidden, String presetIsHidden) throws Exception {
		String is_hidden = "false"; //鍮꾧났媛�
		
		if(fdIsHidden != null && !fdIsHidden.equals("")){
			is_hidden = fdIsHidden;
		}
		
		if(presetIsHidden != null && !presetIsHidden.equals("")){
			is_hidden = presetIsHidden;
			
			if(fdIsHidden != null && !fdIsHidden.equals("") && fdIsHidden.equals("true")){
				is_hidden = fdIsHidden;
			}
		}
		
		return is_hidden;
	}
	
	public static boolean addManagedAttribute(IDfSession idfSession, IDfDocument docObj, IDfFolder parentFolderObj, String drmHeader, IDfUser owner) throws Exception, DfException {
		boolean isSuccess = false;
		Map<String, String> folderInfo = null;
		Map<String, String> presetInfo = null;
		
		//보안등급, 보존연한, 공개여부
		String security_code = "";
		String preservation_code = "";
		IDfTime retentionDate = null;
		String drmSecurityCd = "";
		
		if(!isAspectAttached(parentFolderObj)){
			attachAspectInternal(parentFolderObj);
		}
		
		//DRM 보안등급 가져오기
		drmSecurityCd = getDRMSecurityCode(drmHeader);
		folderInfo = parseFolderAttrInfo(parentFolderObj);
		presetInfo = parsePresetAttrInfo(idfSession, owner, drmSecurityCd);
			
		//보안등급 가져오기
		security_code = DocumentUtil.getSecurityCode(presetInfo.get("u_security_code"));
		//보존연한 가져오기
		preservation_code = DocumentUtil.getPreservationCode(folderInfo.get("u_preservation_code"), presetInfo.get("u_preservation_code"));
		//만료일 가져오기
		retentionDate = getRetentionDate(docObj.getCreationDate(), preservation_code);
		
		//aspect attached check
		if(!isAspectAttached(docObj)){
			attachAspectInternal(docObj);
		}
		
		//공개여부 가져오기
		boolean is_hidden = DocumentUtil.getIsHidden(presetInfo.get("u_is_hidden"));
		
		docObj.setBoolean("ud_mobis_common.u_is_hidden", is_hidden);
		docObj.setString("ud_mobis_common.u_security_code", security_code);
		docObj.setString("ud_mobis_common.u_preservation_code", preservation_code);
		docObj.setTime("ud_mobis_common.u_expire_date", retentionDate);
		docObj.setId("ud_mobis_common.u_folder_id", parentFolderObj.getObjectId());
		docObj.save();

		return isSuccess;
	}
	
	public static boolean isAspectAttached(IDfSysObject sysObj) throws DfException {
		IDfAspects aspect = (IDfAspects) sysObj;
		return isAspectAttached(aspect);
	}
	
	public static boolean isAspectAttached(IDfAspects aspect) throws DfException {
		boolean isAspectAttached = false;
		IDfList aspectList = aspect.getAspects();
		
		for (int iAspectIndex = 0; iAspectIndex < aspectList.getCount(); iAspectIndex++) {
			if (aspectList.get(iAspectIndex).toString().equals("ud_mobis_common")) {
				isAspectAttached = true;
			}
		}		
		
		return isAspectAttached;
	}
	
	public static void attachAspectInternal(IDfSysObject sysObj) throws DfException {
		IDfAspects aspect = (IDfAspects) sysObj;
		aspect.attachAspect("ud_mobis_common", null);
		sysObj.save();		
	}
	
	public static Map<String, String> parseFolderAttrInfo(IDfFolder folderObject) throws Exception{
		Map<String, String> folderAttrInfo = new HashMap<String, String>();
		
		String preservation_code = folderObject.getString("ud_mobis_common.u_preservation_code");
		String a_application_type = folderObject.getString("a_application_type");
		
		if(preservation_code.equals("XX")){
			preservation_code = "99";
		}
		
		folderAttrInfo.put("u_preservation_code", preservation_code);
		folderAttrInfo.put("a_application_type", a_application_type);
		
		return folderAttrInfo;
	}
	
	public static Map<String, String> parsePresetAttrInfo(IDfSession idfSession, IDfUser owner, String drmSecurityCd) throws Exception{
		Map<String, String> presetAttrInfo = new HashMap<String, String>();
		
		String security_code = "";
		String preservation_code = "";
		String is_hidden = "";
		
		IDfPersistentObject presetObj = null;
		
		if(drmSecurityCd != null && !drmSecurityCd.equals("")){
			presetObj = (IDfPersistentObject)idfSession.getObjectByQualification("ud_preset where u_user_name ='" + owner.getUserName() + "' " +
					" and u_security_code = '" + drmSecurityCd + "' and u_is_sec_default = 1");
		}else{
			presetObj = (IDfPersistentObject)idfSession.getObjectByQualification("ud_preset where u_user_name ='" + owner.getUserName() + "' and u_is_default = 1");
		}
		
		if( presetObj != null )
		{
			security_code = presetObj.getString("u_security_code");	
			preservation_code = presetObj.getString("u_preservation_code");
			if(preservation_code.equals("XX")){
				preservation_code = "99";
			}
			is_hidden = presetObj.getString("u_is_hidden").toString();	
			
			presetAttrInfo.put("u_security_code", security_code);
			presetAttrInfo.put("u_preservation_code", preservation_code);
			presetAttrInfo.put("u_is_hidden", is_hidden);
		}
		
		return presetAttrInfo;
	}
	
	public static final IDfTime getRetentionDate(IDfTime creationDate1, String retentionPeriod) {
		IDfTime creationDate = creationDate1;

		String dateString = null;
		if (retentionPeriod == null || "".equals(retentionPeriod)) {
			return DfTime.DF_NULLDATE;
		}
		if ("XX".equals(retentionPeriod)) {
			dateString = "2999/12/30";
		} else {
			if (creationDate == null || creationDate.isNullDate()) {
				creationDate = new DfTime();
			}
			int year = creationDate.getYear();
			int addYear = Integer.parseInt(retentionPeriod);
			int month = creationDate.getMonth();
			int date = creationDate.getDay();

			Calendar cal = Calendar.getInstance();
			cal.set(year + addYear + 1, Calendar.DECEMBER, 31);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
			dateString = sdf.format(cal.getTime());
		}
		return new DfTime(dateString, IDfTime.DF_TIME_PATTERN22);
	}
	
	/**
	 * MOBIS
	 * UPDATE ACL
	 * 신규등록 / 보안등급 변경 / 문서함 위치 변경 / 비공개,공개 변경 시
	 * u_is_hidden, u_security_code, u_folder_id update 후 호출
	 * @param dfDocument
	 * @throws Exception
	 */
	public static void setACL(IDfDocument dfDocument) throws Exception {	
		
		// change session's alias set
		IDfAliasSet aliasSet = getAliasSet(dfDocument);
		dfDocument.getSession().setAliasSet(aliasSet.getObjectId().getId());
		
		dfDocument.setId("r_alias_set_id", aliasSet.getObjectId());
		dfDocument.save();
		
		// Update acl
		dfDocument.setACLDomain(dfDocument.getSession().getDocbaseOwnerName());
		
		boolean isHidden = isHidden(dfDocument);
		
		if (isHidden) {
			dfDocument.setACLName("%Draft Hidden Permission");
		}else {
			dfDocument.setACLName("%Draft Permission");
		}
		dfDocument.save();
	}
	
	public static boolean isHidden(IDfDocument dfDocument) throws DfException {
		// return false not if confidential
		if (!"G10002".equals(getSecurityCode(dfDocument))) {
			return false;
		}
		if (dfDocument.getTypeName().equals("ud_document")) {
			return dfDocument.getBoolean("u_is_hidden");
		}else {
			return dfDocument.getBoolean("ud_mobis_common.u_is_hidden");
		}		
	}
	
	public static String getSecurityCode(IDfSysObject sysObj) throws DfException {
		if (sysObj.getTypeName().equals("ud_document")) {
			return sysObj.getString("u_security_code");
		}else {
			return sysObj.getString("ud_mobis_common.u_security_code");
		}
	}
	
	public static IDfAliasSet getAliasSet (IDfDocument dfDocument) throws Exception {
		String smartDiskObjectId = getXiperSmartDiskId(dfDocument.getSession(), dfDocument);
		
		IDfFolder smartDiskObject = (IDfFolder) dfDocument.getSession().getObject(new DfId(smartDiskObjectId));
		String departmentGroupName = new StringBuffer(smartDiskObjectId).append(".writer").toString();
		String cabinetAdminGroupName = new StringBuffer(smartDiskObjectId).append(".admin").toString();
		String cabinetDeleterGroupName = new StringBuffer(smartDiskObjectId).append(".deleter").toString();
		String managerGroupName = new StringBuffer(smartDiskObject.getString("u_user_name")).append("_manager").toString();

		// copy new alias set
		IDfAliasSet newAliasSet = copyAliasSet(dfDocument.getSession(), dfDocument);

		for (int i=0; i < newAliasSet.getAliasCount(); i++) {
			if (newAliasSet.getAliasName(i).equals("department")) {
				newAliasSet.setAliasValue(i, departmentGroupName);
			}
			else if (newAliasSet.getAliasName(i).equals("cabinetadmin")) {
				newAliasSet.setAliasValue(i, cabinetAdminGroupName);
			}
			else if (newAliasSet.getAliasName(i).equals("cabinetdeleter")) {
				newAliasSet.setAliasValue(i, cabinetDeleterGroupName);
			}
			else if (newAliasSet.getAliasName(i).equals("manager")) {
				newAliasSet.setAliasValue(i, managerGroupName);
			}							
		}
		
		newAliasSet.save();	
		
		return newAliasSet;
	}
	
	private static String getXiperSmartDiskId(IDfSession session, IDfDocument docObj) throws Exception{
		String diskId = "";
		IDfCollection col = null;
		IDfQuery query = null;
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT x.r_object_id FROM dm_dbo.xiper_smartdisk_sp x, dm_folder y ");
		strQuery.append("WHERE ");
		strQuery.append("any y.i_ancestor_id = x.r_object_id ");
		strQuery.append("AND y.r_object_id = '" + docObj.getString("ud_mobis_common.u_folder_id") + "'");
		
		try{
			query = new DfQuery();
			query.setDQL(strQuery.toString());
			col = query.execute(session, DfQuery.DF_EXEC_QUERY);
			if(col != null){
				while(col.next()){
					diskId = col.getString("r_object_id");
				}
			}
		}catch(DfException de){
			de.printStackTrace();
		}finally{
			if(col != null){
				col.close();
			}
		}
		return diskId;
	}
	
	private static IDfAliasSet copyAliasSet(IDfSession session, IDfSysObject documentObject) {
		IDfAliasSet newAliasSet = null;
		try {
			IDfPersistentObject userPresetObj =
					(IDfPersistentObject) session.getObjectByQualification(new StringBuffer()
						.append("ud_preset where u_user_name = '").append(documentObject.getOwnerName()).append("'	")
						.append("and u_security_code = '").append(DocumentUtil.getSecurityCode(documentObject)).append("'	")
						.append("and u_is_sec_default = 1")
						.toString()
					);
			
			newAliasSet = copyAliasSet(session, documentObject, userPresetObj);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newAliasSet;
	}
	
	private static IDfAliasSet copyAliasSet(IDfSession session, IDfSysObject documentObject, IDfPersistentObject userPresetObj) throws Exception {
		IDfAliasSet newAliasSet = null;
		try {
			IDfAliasSet systemAliasSet = (IDfAliasSet) session.getObject(userPresetObj.getId("u_alias_set_id"));

			newAliasSet = (IDfAliasSet) session.newObject("dm_alias_set");
			newAliasSet.setObjectName(newAliasSet.getObjectId().getId());
			if (!session.getLoginUserName().equals(session.getDocbaseOwnerName())) {
				newAliasSet.setObjectDescription(systemAliasSet.getObjectDescription());
			}
			for (int i = 0; i < systemAliasSet.getAliasCount(); i++) {
				newAliasSet.appendAlias(systemAliasSet.getAliasName(i), systemAliasSet.getAliasValue(i),
						systemAliasSet.getAliasCategory(i), systemAliasSet.getAliasUserCategory(i),
						systemAliasSet.getAliasDescription(i));
			}
			newAliasSet.save();

		} catch (Exception e) {
			throw e;
		}

		return newAliasSet;
	}
	
	/**
	 * @param fdSecurityCd 폴더 보안등급
	 * @param presetSecurityCd 사용자 preset에 설정되어 있는 보안등급
	 * @param drmHeader DRM 보안등급을 추출하기 위한 
	 * @return 보안등급
	 * @throws Exception
	 */
	public static String getDRMSecurityCode(String drmHeader) throws Exception {
		String security_code = "";
		
		if(drmHeader != null && !drmHeader.equals("")){
			
			if(drmHeader.startsWith("0000004")){
				security_code = "G10001";
			}else if(drmHeader.startsWith("0000003")){
				security_code = "G10002";
			}else if(drmHeader.startsWith("0000002")){
				security_code = "G10003";
			}else if(drmHeader.startsWith("0000001")){
				security_code = "G10004";
			}
		}		
		return security_code;
	}
	
	public static boolean getIsHidden(String presetIsHidden) throws Exception {
		boolean is_hidden = false; //공개
		
		if(presetIsHidden != null && !presetIsHidden.equals("")){
			is_hidden = Boolean.parseBoolean(presetIsHidden);
		}
		
		return is_hidden;
	}
}
