	package com.batch.mig.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.common.DfSessionManager;
import com.edms.batch.mig.vo.ArchiveRawVO;
import com.edms.batch.mig.vo.MIContentsVO;

public class ArchiveRawMig {
	
	private DfSessionManager dfSessionManager;
	
	private Connection conn;
	private Configurations config;
	List<ArchiveRawVO> list = new ArrayList<ArchiveRawVO>();
	
	private String MIG_SUBJECT = "mig_archive";
	
	public static void main(String[] args) {
		
		String path = "D:/temp/";	//파일 경로 설정
		String filename = "MIArchive_0420.xls";	//파일명 설정
		
		ArchiveRawMig job = new ArchiveRawMig();
		job.readExcel(path,filename);
		job.getList();
		
		job.migList();
		
		
		
	}
	

	public void migList() {
		IDfSession session = null;
		config = new Configurations();
		config.getConfig();

		
		try {
					
			//dfSessionManager = new DfSessionManager(config);
			//session = dfSessionManager.getSession(config);			
			
			conn = DBUtil.getAWSConnection(config);
			
			listMigArchive(conn);
			
			System.out.println("list.size() = " + list.size() );
			
			
			for(int i=0; i<list.size(); i++) {
				ArchiveRawVO vo = list.get(i);
				
				System.out.println(i + "		" + vo.getProject_id() + " :: " + vo.getFile_name()  + " :: " + vo.getClass_1() + " :: " + vo.getClass_2());
								
				MIContentsVO miVo  = convertArchivetoMIContent(vo);
				migArchiveToUdDocument(session, miVo);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
			//	if (dfSessionManager != null) dfSessionManager.disconnectSession();

				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
	

	
	
	public void getList() {
		IDfSession session = null;
		config = new Configurations();
		config.getConfig();

		
		try {
					
			//dfSessionManager = new DfSessionManager(config);
			//session = dfSessionManager.getSession(config);			
			
			conn = DBUtil.getAWSConnection(config);
			
			
			for(int i=0; i<list.size(); i++) {
				ArchiveRawVO vo = list.get(i);
				
				System.out.println(i + "		" + vo.getProject_id() + " :: " + vo.getFile_name()  + " :: " + vo.getClass_1() + " :: " + vo.getClass_2());
				
				insertDBRaw(conn, vo);
				
				//MIContentsVO miVo  = convertArchivetoMIContent(vo);
				
				//migArchiveToUdDocument(session, miVo);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
			//	if (dfSessionManager != null) dfSessionManager.disconnectSession();

				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
	
	

	public void insertDBRaw(Connection conn, ArchiveRawVO vo) {
		
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql = " insert into ud_tbl_mig_archive_raw2 (project_id, project_yyyymm, project_dept, project_work, summary, reg_date, modify_date, project_name, project_name_en, duty_name, duty_name_en, class_1, class_2, class_3,  class_1_en, class_2_en, class_3_en, open_type, file_name, server_file_name ) values ";
		sql += " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";
		
		int idx = 1;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(idx++, vo.getProject_id() );
			pstmt.setString(idx++, vo.getProject_yyyymm());
			pstmt.setString(idx++, vo.getProject_dept() );
			pstmt.setString(idx++, vo.getProject_work() );
			pstmt.setString(idx++, vo.getSummary() );
			pstmt.setString(idx++, vo.getReg_date());
			pstmt.setString(idx++, vo.getModify_date() );
			pstmt.setString(idx++, vo.getProject_name() );
			pstmt.setString(idx++, vo.getProject_name_en() );
			pstmt.setString(idx++, vo.getDuty_name() );
			pstmt.setString(idx++, vo.getDuty_name_en() );
			pstmt.setString(idx++, vo.getClass_1());
			pstmt.setString(idx++, vo.getClass_2() );
			pstmt.setString(idx++, vo.getClass_3());
			pstmt.setString(idx++, vo.getClass_1_en());
			pstmt.setString(idx++, vo.getClass_2_en() );
			//pstmt.setString(idx++, vo.getClass_3_en());			
			pstmt.setString(idx++, vo.getOpen_type() );
			pstmt.setString(idx++, vo.getFile_name() );
			pstmt.setString(idx++, vo.getServer_file_name() );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null )  {pstmt.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public void migArchiveToUdDocument(IDfSession idfSession, MIContentsVO vo) {
		IDfDocument object = null;
		
		try {
			
			if (StringUtils.isBlank(vo.getrObjectId())) {
				object = (IDfDocument) idfSession.newObject("ud_document");
			} else {
				object = (IDfDocument) idfSession.getObject(new DfId(vo.getrObjectId()));
			}
			if (object == null) throw new RuntimeException("저장대상 객체가 없습니다.");

			object.setString("object_name", vo.getObjectName());
			object.setSubject( MIG_SUBJECT );
			object.setString("u_project_cd", vo.getuProjectCd());
			object.setString("u_archive_class", vo.getuArchiveClass());
			object.setString("title", vo.getTitle());
			object.setString("u_summary", vo.getuSummary());
			setRepeatingString("u_keyword_ko", vo.getuKeywordKo(), object);
			setRepeatingString("u_in_pub_bland", vo.getuInPubBland(), object);
			object.setString("owner_name", vo.getOwnerName());
			object.setString("u_owner_group", vo.getuOwnerGroup());
			if( vo.getuArea() != null )
				setRepeatingString("u_area", vo.getuArea(), object);
			setRepeatingString("u_car_type", vo.getuCarType(), object);
			setRepeatingString("u_sector", vo.getuSector(), object);
			object.setString("u_security", vo.getuSecurity());

			// TODO: 임시 - projectType을 u_pub_source에 저장
			//setRepeatingString("u_pub_source", vo.getuPubSource(), object);
			String[] tmpPubSource = new String[1];
			tmpPubSource[0] = vo.getProjectType();
			setRepeatingString("u_pub_source", tmpPubSource, object);

			object.setString("u_importance", vo.getuImportance());
			object.setString("u_mi_subject", vo.getuMiSubject());
			
			//ACL 설정. 
			IDfACL acl = (IDfACL)idfSession.getObject( new DfId("45035cf68000190a")); //45035cf68000190a , acl_internal
			object.setACL( acl );
			
			object.save();
			
			//추가 권한
			//추가권한 사용자 혹은 부서코드
			//object.grant(vo.getOwnerName(), IDfACL.DF_PERMIT_READ , null); //Relate(note), Read ( 읽기 )
			vo.setrObjectId(object.getObjectId().getId());
			object.save();
			
			String fileName = vo.getFileName();
			
			if( fileName != null && fileName.length() > 3 )
				saveAttachFiles(idfSession,object.getObjectId().getId(),  fileName);
			
		} catch (DfException e) {
			e.printStackTrace();
		}
		
		//saveAttachFiles(vo, idfSession, object.getACL());
		
	}
	
	
	private void saveAttachFiles( IDfSession idfSession, String rootId, String fileName) {

		String tmpFile = "D://temp/1.pdf";
		try {

			IDfDocument attachDoc = (IDfDocument) idfSession.newObject("ud_doc_attach");

			File newFile = new File(tmpFile);
			attachDoc.setObjectName(fileName);
			attachDoc.setSubject( MIG_SUBJECT );
			attachDoc.setString("u_attach_type", "MICNT");
			attachDoc.setString("u_root_obj_id", rootId);
			attachDoc.setInt("u_sort_order", 0);
			attachDoc.setContentType(getContentType(fileName));
			attachDoc.setFile(newFile.getAbsolutePath());
			attachDoc.save();

		} catch (DfException e) {
			e.printStackTrace();
		} finally {

		}
	}	
	
	
    public static String getContentType(String sFileName) {
    	String crtext = "crtext";
        String sContentType = "unknown";
    	if( sFileName == null ) return sContentType;
    	
        String sDosExtension = getDosExtension(sFileName);
        sDosExtension = sDosExtension.toLowerCase();

        switch( sDosExtension ) {
        	case "html" : sContentType = "html"; break;
        	case "htm" : sContentType = "html"; break;
        	case "doc" : sContentType = "msw8"; break;
        	case "ppt" : sContentType = "ppt8"; break;
        	case "xls" : sContentType = "excel8book"; break;
        	case "docx" : sContentType = "msw12"; break;
        	case "pptx" : sContentType = "ppt12"; break;
        	case "hwp" : sContentType = "hwp"; break;
        	case "txt" : sContentType = crtext.toString(); break;
        	case "log" : sContentType = crtext.toString(); break;
        	case "bat" : sContentType = crtext.toString(); break;
        	case "ini" : sContentType = crtext.toString(); break;
        	case "pdf" : sContentType = "pdf"; break;
        	case "zip" : sContentType = "zip"; break;
        	case "jpg" : sContentType = "jpeg"; break;
        	case "jpeg" : sContentType = "jpeg"; break;
        	case "tif" : sContentType = "tif"; break;
        	case "bmp" : sContentType = "bmp"; break;
        	case "mht" : sContentType = "mht"; break;
        	case "gif" : sContentType = "gif"; break;
        	case "avi" : sContentType = "avi"; break;
        	case "asf" : sContentType = "asf"; break;
        	case "wmv" : sContentType = "wmv"; break;
        	case "mpg" : sContentType = "mpeg"; break;
        	case "mov" : sContentType = "quicktime"; break;
        	case "ram" : sContentType = "ram"; break;
        	case "rm" : sContentType = "rm"; break;
        	case "rmm" : sContentType = "rmm"; break;
        	case "rnx" : sContentType = "rnx"; break;
        	case "rv" : sContentType = "rv"; break;
        	case "scm" : sContentType = "scam"; break;
        	case "mpg2" : sContentType = "mpeg2"; break;
        	case "mp4" : sContentType = "mpeg-4v"; break;
        	case "wvx" : sContentType = "wvx"; break;
        	case "asx" : sContentType = "asx"; break;
        	case "flv" : sContentType = "flv"; break;
        	case "ogg" : sContentType = "ogg"; break;
        	case "webm" : sContentType = "webm"; break;    
         	default : sContentType = "unknown"; break;
        
        }

        return sContentType;
    }	
    
    public static String getDosExtension(String fileName) {
        int iLastIdx = fileName.lastIndexOf(".");
        if (iLastIdx >= 0) {
            return fileName.substring(iLastIdx + 1, fileName.length()).toLowerCase();
        } else {
            return "";
        }
    }    
	
	
	public void listMigArchive(Connection conn)  throws Exception  {
		String sql = "";
		
		sql = " select \n";
		sql += " 	a.project_id,\n";
		sql += " 	a.project_name,\n";
		sql += " 	a.class_1,\n";
		sql += " 	(select max(class_cd) from ud_tbl_class_temp where replace(class_name,' ','') = replace(a.class_1,' ',''))   as class_name1,\n";
		sql += " 	a.class_2,\n";
		sql += " 	(select max(class_cd) from ud_tbl_class_temp where replace(class_name,' ','') = replace(a.class_2,' ',''))  as class_name2,\n";
		sql += " 	b.class_3 ,\n";
		sql += " 	a.file_name,\n";
		sql += " 	b.reg_date,\n";
		sql += " 	b.modify_date,\n";
		sql += " 	b.open_type,\n";
		sql += " 	b.summary,\n";
		sql += " 	b.project_yyyymm,\n";
		sql += " 	b.project_dept,\n";
		sql += " 	b.project_work,\n";
		sql += " 	b.duty_name\n";
		sql += " from	\n";
		sql += " 	ud_tbl_mig_archive a,\n";
		sql += " 	ud_tbl_mig_archive_raw b\n"; 
		sql += " where \n";
		sql += " 	a.project_id = b.project_id \n";
		sql += " 	and a.project_name != '-'	\n";
		sql += " order by a.project_id desc ";
		
		if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ArchiveRawVO vo = new ArchiveRawVO();
			
				vo.setProject_id( rs.getString("project_id"));
				vo.setProject_name( rs.getString("project_name"));
				vo.setClass_1( rs.getString("class_name1"));
				vo.setClass_2( rs.getString("class_name2"));
				vo.setClass_3( rs.getString("class_3"));
				vo.setFile_name( rs.getString("file_name"));
				vo.setReg_date( rs.getString("reg_date"));
				vo.setModify_date( rs.getString("modify_date"));
				vo.setOpen_type( rs.getString("open_type"));
				vo.setSummary( rs.getString("summary"));
				vo.setProject_yyyymm( rs.getString("project_yyyymm"));
				vo.setProject_dept( rs.getString("project_dept"));
				vo.setProject_work( rs.getString("project_work"));
				vo.setDuty_name( rs.getString("duty_name"));
				
				list.add(vo);
				
			}
			
			
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
	}
	
	public MIContentsVO convertArchivetoMIContent(ArchiveRawVO vo) {
		
		MIContentsVO miVo = new MIContentsVO();
		
		miVo.setObjectName( vo.getProject_name() );
		miVo.setuProjectCd( vo.getProject_id() );
		miVo.setuArchiveClass( "on" ); 
		
		miVo.setTitle( vo.getProject_dept() );
		miVo.setuSummary( vo.getSummary());
		
		String[] brand = new String[1];
		brand[0] = "H101";
		miVo.setuInPubBland(brand);
		
		
		String tmp  = vo.getClass_3();
		String[] data = null;
		
		if( tmp != null && tmp.length() > 1) {
			if( tmp.indexOf(",") >0) {
				data = tmp.split(",");
				
				for(int d=0; d<data.length; d++) {
					data[d] = data[d].trim();
				}
			} else {
				data = new String[1];
				data[0] = tmp;
			}
			miVo.setuKeywordKo(data);
		}
		miVo.setOwnerName("service_admin");
		miVo.setuOwnerGroup("gr_hmk_group");
		
		miVo.setuSecurity("10803");
		miVo.setuMiSubject("20401");
		
		// 분류체계 매핑
		String u_area = "";
		String lastCd = null;
		
		if( vo.getClass_2() != null && vo.getClass_2().length() > 1 ) {
			lastCd = vo.getClass_2();
		} else if( vo.getClass_1() != null && vo.getClass_1().length() > 1 ) {
			lastCd = vo.getClass_1();
		} else {
			lastCd = "203070";
		}
		
		String[] tmp9 = new String[1];
		tmp9[0] = "101010";
		miVo.setuArea(tmp9);
		
		if( lastCd != null ) {
			if( lastCd.startsWith("1010") ) {
				String[] tmp2 = new String[1];
				tmp2[0] = lastCd;
				miVo.setuArea(tmp2);
			} else if( lastCd.startsWith("1020") ) {
				String[] tmp2 = new String[1];
				tmp2[0] = lastCd;
				miVo.setuSector(tmp2);				
			} else if( lastCd.startsWith("2030") ) {
				String[] tmp2 = new String[1];
				tmp2[0] = lastCd;
				miVo.setuPubSource(tmp2);
			} else if( lastCd.startsWith("3010") ) {
				String[] tmp2 = new String[1];
				tmp2[0] = lastCd;
				miVo.setuPubSource(tmp2);
			}
		}
		
		miVo.setFileName( vo.getFile_name());
		
		return miVo;
	}
	
		
	

	private void setRepeatingString(String field, String[] strArr, IDfDocument object) throws DfException {
		if (isEmpty(field) || object == null || strArr == null)
			//throw new RuntimeException("작업에 필요한 정보가 없습니다.");
			return;

		object.removeAll(field);
		for(int i = 0; i < strArr.length; i++) {
			object.setRepeatingString(field, i, strArr[i]);
		}
	}
	
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	

	
	public void readExcel(String path,String filename) {
		try {
		FileInputStream file = new FileInputStream(path+filename);
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(false);	//지수로 안나오게 설정
		
		//시트 갯수
		int sheetNum = workbook.getNumberOfSheets();
		
		for(int s = 0; s < sheetNum; s++) {
			HSSFSheet sheet = workbook.getSheetAt(s);
			//행 갯수
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int r = 0 ; r < rows ; r++) {
				ArchiveRawVO vo = new ArchiveRawVO();
				
				HSSFRow row = sheet.getRow(r);
				
				if( row != null ) {
					int cells = row.getPhysicalNumberOfCells();
					
					for(int c = 0 ; c < cells; c++) {
						HSSFCell cell = row.getCell(c);
						
						String value = "";
						if(cell!=null) {
							//타입 체크
							switch(cell.getCellType()) {
							case STRING:
								value = cell.getStringCellValue();
								break;
							case NUMERIC:
								value = f.format(cell.getNumericCellValue())+"";
								break;
							case BLANK:
								value = cell.getBooleanCellValue()+"";
								break;
							case ERROR:
								value = cell.getErrorCellValue()+"";
								break;
							}
						}
					
						if( "false".equals(value) ) value = "";
						
						
						if( c == 0 ) vo.setProject_id(value);
						if( c == 1 ) vo.setProject_yyyymm(value);
						if( c == 2 ) vo.setProject_dept(value);
						if( c == 3 ) vo.setProject_work(value);
						if( c == 4 ) vo.setSummary(value);
						if( c == 5 ) vo.setReg_date(value);
						if( c == 6 ) vo.setModify_date(value);
						if( c == 7 ) vo.setProject_name(value);
						if( c == 8 ) vo.setProject_name_en(value);
						if( c == 9 ) vo.setDuty_name(value);
						if( c == 10 ) vo.setDuty_name_en(value);
						if( c == 11) vo.setClass_1(value);
						if( c == 12 ) vo.setClass_2(value);
						if( c == 13 ) vo.setClass_3(value);
						if( c == 14 ) vo.setClass_1_en(value);
						if( c == 15 ) vo.setClass_2_en(value);
						if( c == 16 ) vo.setClass_3_en(value);
						if( c == 17 ) vo.setOpen_type(value);
						if( c == 18 ) vo.setFile_name(value);
						if( c == 19 ) vo.setServer_file_name(value);
						
					}
					
					list.add(vo);
				}
			}
		}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}