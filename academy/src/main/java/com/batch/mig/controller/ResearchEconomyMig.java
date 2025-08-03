package com.batch.mig.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.common.DfSessionManager;
import com.edms.batch.mig.vo.MIContentsVO;
import com.edms.batch.mig.vo.MigClassVO;
import com.edms.batch.mig.vo.ResearchEconomyVO;

public class ResearchEconomyMig {
	
	private DfSessionManager dfSessionManager;
	
	private Connection conn;
	private Configurations config;
	List<ResearchEconomyVO> list = new ArrayList<ResearchEconomyVO>();
	
	private String MIG_SUBJECT = "mig_research2";
	
	
	public static void main(String[] args) {
		
		ResearchEconomyMig job = new ResearchEconomyMig();

		job.migList();

		
	}


	public void pickCategoryUpdateList(IDfSession session)  throws Exception {
		
		System.out.print("\tpickCategoryUpdateList .. " );

		
		// 기존 경산연 Pick clear 
		delHRIPick(session);

		//Pick 자동 설정 3(CEO Report:7, 경영환경전망:5, 연구프로젝트:24) + 3(최신순)
		List pickList = pickCategoryDocList();
					
		for(int i=0; i<pickList.size(); i++) {
			String[] data = (String[])pickList.get(i);
			String r_object_id = data[0];
			
			System.out.println("\t\t[" + i + "] " + data[0] + " .. " +  data[1] );
			
			IDfDocument obj = (IDfDocument)session.getObject(new DfId(r_object_id) );
		
			if( obj!= null ) {					
				obj.appendString("u_pick_code", "20802");
				obj.setString("a_category", data[1] );
				obj.save();
			}
			
		}
		
		if( pickList != null ) pickList.clear();
		
		 
	}	
	
	public void publicUpdateList(IDfSession session)  throws Exception {
		
		List pubList = getPublicList();
		
		System.out.println("\tpublicUpdate srcList.size() = " + pubList.size() );
		
		
		for(int i=0; i<pubList.size(); i++) {
			String[] data = (String[])pubList.get(i);
	
			System.out.println("\t\t[" + i + "] " + data[0] + " .. " +  data[1] );
			updatePubBland(session, i, data);
		}
		
		if( pubList != null ) pubList.clear();
	}	
	
	
	public void sourceUpdateList()  throws Exception{
		
		List srcList = listSouceMigData(conn);
		
		System.out.println("\tsourceUpdate srcList.size() = " + srcList.size() );
		
		
		for(int i=0; i<srcList.size(); i++) {
			
			String r_object_id = (String)srcList.get(i);
			
			if( i%50 == 0)
				System.out.println("\t\t[" +  i + "] "+ r_object_id);
		
			updateSourceeSQL(conn, r_object_id);
		}
		
		if( srcList != null ) srcList.clear();

	}

	public void dateUpdateList()   throws Exception  {
		
		List dateList = listDateMigData(conn);
		
		System.out.println("\tdateUpdate list.size() = " + dateList.size() );
		
		
		for(int i=0; i<dateList.size(); i++) {
			
			String[] data = (String[])dateList.get(i);
			
			if( i%20 == 0)
				System.out.println("\t\t[" +  i + "] "+ data[0] + " :: " + data[1] );
		
			updateDateSQL(conn, data);
		}
		
		if( dateList != null ) dateList.clear();
		
	}
	
	public void migList() {
		IDfSession session = null;
		config = new Configurations();
		config.getConfig();

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		try {
					
			dfSessionManager = new DfSessionManager(config);
			session = dfSessionManager.getSession(config);			
			
			conn = DBUtil.getAWSConnection(config);
			
			listMigResearch(conn);
			
			System.out.println("\n\n\nHRI Content list.size() = " + list.size()  + "  :: " + timeStamp );
			
			for(int i=0; i<list.size() ; i++) {
				ResearchEconomyVO vo = list.get(i);
				
				System.out.println(i + "		" + vo.getNo() + " :: " + vo.getSubject()  + " :: " + vo.getUrl()  );
								
				MIContentsVO miVo  = convertResearchtoMIContent(vo);
				migResearchToUdDocument(session, miVo);
				
			}
			
			
			dateUpdateList();
			sourceUpdateList();
			publicUpdateList(session);
			
			//Pick 자동 설정 3(CEO Report:7, 경영환경전망:5, 연구프로젝트:24) + 3(최신순)
			pickCategoryUpdateList(session);
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if (dfSessionManager != null) dfSessionManager.disconnectSession();

				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
	


	public void migResearchToUdDocument(IDfSession idfSession, MIContentsVO vo) {
		IDfDocument object = null;
		
		try {
			
			if (StringUtils.isBlank(vo.getrObjectId())) {
				object = (IDfDocument) idfSession.newObject("ud_document");
			} else {
				object = (IDfDocument) idfSession.getObject(new DfId(vo.getrObjectId()));
			}
			if (object == null) throw new RuntimeException("저장대상 객체가 없습니다.");

			object.setString("object_name", vo.getObjectName());
			object.setSubject(MIG_SUBJECT);
			object.setString("u_project_cd", vo.getuProjectCd());
			object.setString("u_archive_class", vo.getuArchiveClass());
			object.setString("title", vo.getObjectName());
			
			object.setString("log_entry", vo.getTitle());
			
			setRepeatingString("u_keyword_ko", vo.getuKeywordKo(), object);
			setRepeatingString("u_in_pub_bland", vo.getuInPubBland(), object);
			object.setString("owner_name", vo.getOwnerName());
			object.setString("u_owner_group", vo.getuOwnerGroup());
			//if( vo.getuArea() != null )
			//	setRepeatingString("u_area", vo.getuArea(), object);
			//setRepeatingString("u_car_type", vo.getuCarType(), object);
			//setRepeatingString("u_sector", vo.getuSector(), object);
			object.setString("u_security", vo.getuSecurity());

			// TODO: 임시 - projectType을 u_pub_source에 저장
			//setRepeatingString("u_pub_source", vo.getuPubSource(), object);
			String[] tmpPubSource = new String[1];
			tmpPubSource[0] = vo.getProjectType();
			setRepeatingString("u_pub_source", tmpPubSource, object);

			object.setString("u_importance", "20202"); //(중)
			object.setString("u_mi_subject", vo.getuMiSubject());
			
			object.setString("u_link", vo.getU_link());
			
			//ACL 설정. 
			IDfACL acl = (IDfACL)idfSession.getObject( new DfId("45035cf68000190a"));  //4506ea6080000f8a : 개발, 45035cf68000190a: 운영 , acl_internal
			object.setACL( acl );
			object.save();
			

			//추가 권한
			//추가권한 사용자 혹은 부서코드
			//object.grant(vo.getOwnerName(), IDfACL.DF_PERMIT_READ , null); //Relate(note), Read ( 읽기 )
			vo.setrObjectId(object.getObjectId().getId());
			
			saveContentsInFile(vo.getStrfileInfo(), object);
			
			object.setString("u_summary", vo.getuSummary());
			object.save();	
			
			
		} catch (DfException e) {
			//e.printStackTrace();

			if( object != null) {
				try {
					object.setString("u_summary", getMaxSummaryAndEscapeTag(vo.getuSummary(),1500) );
					object.save();
				} catch (DfException e1) {
					try {
						object.setString("u_summary", getMaxSummaryAndEscapeTag(vo.getuSummary(),1200) );
						object.save();
					} catch (DfException e2) {
						try {
							object.setString("u_summary", getMaxSummaryAndEscapeTag(vo.getuSummary(),1000) );
							object.save();
						} catch (DfException e3) {
							try {
								object.setString("u_summary", getMaxSummaryAndEscapeTag(vo.getuSummary(),800) );
								object.save();
							} catch (DfException e4) {
								try {
									object.setString("u_summary", getMaxSummaryAndEscapeTag(vo.getuSummary(),600) );
									object.save();
								} catch (DfException e5) {
									e5.printStackTrace();
								}
							}
						}
					}
				}
			}

		}
		
		//saveAttachFiles(vo, idfSession, object.getACL());
		
	}
	
	
	
	int fileCount = 0;
	
    public String getContentType(String sFileName) {
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
    
    public String getDosExtension(String fileName) {
        int iLastIdx = fileName.lastIndexOf(".");
        if (iLastIdx >= 0) {
            return fileName.substring(iLastIdx + 1, fileName.length()).toLowerCase();
        } else {
            return "";
        }
    }    
	
	
	public void listMigResearch(Connection conn)  throws Exception  {
		String sql = "";
		
		sql = " select \n";
		sql += " 	'mip_' || to_number(no,'9G999g999') nos, \n";
		sql += " 	subject, \n";
		sql += " 	author, \n";
		sql += " 	regdate, \n";
		sql += " 	summary, \n";
		sql += " 	case when 	length(content) in ( 273 ,13 ) \n";
		sql += " 	 then summary \n";
		sql += " 	 else  \n";
		sql += " 	  replace( content,'<p>&nbsp;</p>','') \n";
		sql += " 	end  as cnt, \n";
		sql += " 	fileid, \n";
		sql += " 	pbsmastername , \n";
		sql += " 	divisionname3 , \n";
		sql += " 	divisionname4 , \n";
		sql += " 	url , \n";
		sql += " 	pbstablename \n";
		sql += " from  \n";
		sql += " ud_tbl_ecnc_ids_rsst_ctr_eai where  \n";
		sql += " 'mip_' || no || '_' || pbstablename not in ( select log_entry from ud_document_sp where subject ='mig_research' and log_entry like 'mip%') \n";
		sql += "  and subject not like '(NB2_-%' order by 1 desc \n";
		
		if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ResearchEconomyVO vo = new ResearchEconomyVO();
			
				vo.setNo( rs.getString("nos"));
				vo.setSubject( rs.getString("subject"));
				vo.setRegdate( rs.getString("regdate"));
				vo.setAuthor( rs.getString("author"));
				vo.setSummary( rs.getString("summary"));
				vo.setContent( rs.getString("cnt"));
				vo.setFileid( rs.getString("fileid"));
				vo.setPbsmastername( rs.getString("pbsmastername"));
				vo.setDivisionname3( rs.getString("divisionname3"));
				vo.setDivisionname4( rs.getString("divisionname4"));
				vo.setUrl( rs.getString("url"));
				vo.setPbstablename(rs.getString("pbstablename"));
				
				vo.setSummary( getMaxSummaryAndEscapeTag(vo.getSummary(),1024*1024*100)); //100,000
				
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
	

	public MIContentsVO convertResearchtoMIContent(ResearchEconomyVO vo) {
		MIContentsVO miVo = new MIContentsVO();
		
			
		miVo.setObjectName( vo.getSubject() );
		
		if( vo.getFileid() != null &&  vo.getFileid().length() > 28)
			miVo.setuProjectCd( vo.getFileid().substring(0,29) );
		else 
			miVo.setuProjectCd( vo.getFileid() );
		
		miVo.setuArchiveClass( "out" ); 
		
		miVo.setTitle( vo.getNo() +"_" + vo.getPbstablename());
		miVo.setuSummary( vo.getSummary());
		
		
		miVo.setStrfileInfo(vo.getContent());
		
		String[] brand = new String[1];
		brand[0] = "H101";
		miVo.setuInPubBland(brand);
		
		
		String tmp  = "";
		String[] data = null;
		

		if( vo.getPbsmastername() != null && vo.getPbsmastername().length() > 1) {
			tmp += vo.getPbsmastername() + ",";
		}
		if( vo.getDivisionname3() != null && vo.getDivisionname3().length() > 1) {
			tmp += vo.getDivisionname3() + ",";
		}
		if( vo.getDivisionname4() != null && vo.getDivisionname4().length() > 1) {
			tmp += vo.getDivisionname4() + ",";
		}
		if( tmp.length() > 2) {
			tmp = tmp.substring(0,tmp.length()-1);
			data = tmp.split(",");
			miVo.setuKeywordKo(data);
		}
		
		miVo.setOwnerName( "service_admin" );
		miVo.setuOwnerGroup(vo.getAuthor() );
		
		miVo.setuSecurity("10803");
		miVo.setuMiSubject("20401");
		
		// 분류체계 매핑
		String u_area = "";
		String lastCd = null;
		
		/*
		lastCd = "101010";
		
		if( vo.getPbsmastername() != null && vo.getPbsmastername().length() > 1 ) {
			if( vo.getPbsmastername().indexOf("중국") > 0)  {
				lastCd = "101040";
			}
		} 
*/		
		//String[] tmp1 = new String[1];
		//tmp1[0] = lastCd;
		//miVo.setuArea(tmp1);
		
		/*
		String[] tmp2 = new String[1];
		tmp2[0] = "102050";
		miVo.setuSector(tmp2);
		*/
		
		String[] tmp3 = new String[1];
		tmp3[0] = "20301010";
		miVo.setuPubSource(tmp3);		
		
		//miVo.setOwnerName(lastCd);
		miVo.setFileName( vo.getFileid());
		miVo.setU_link(vo.getUrl());
		
		
		return miVo;
	}
	
	
	private void saveContentsInFile(String content, IDfDocument object) {
		String filePath = "D:/temp/mig/";

		String r_object_id = "";
		
		try {
			
			filePath = filePath + object.getObjectId().getId() + ".txt";

			writeInFileFromString(filePath, content, 1024*1024*10); // 10,000,000 : 운영, 개발 5,000
			
			//object.setString("u_summary", escapeContent );
			object.setContentType(getContentType(filePath));
			//object.setSubject(MIG_SUBJECT);
			object.setFile(filePath);
			object.save();

			// 임시파일삭제
			deleteFile(filePath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
    public void deleteFile(String filePath) {
        if (isEmpty(filePath)) return;

        try {
            boolean result = Files.deleteIfExists(Paths.get(filePath));
            if (!result) {
               System.out.println("[deleteFile] fail to delete file : " + filePath);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

	
    public void writeInFileFromString(String filePath, String values, int maxStrLength) {
        if (filePath == null || isEmpty(filePath)) return;

        Path savedFilePath = null;
        byte[] strValues = null;
        
        try {
            if (isNotEmpty(values) && values.length() > maxStrLength) {
                values = values.substring(0, maxStrLength);
            }

            savedFilePath = Paths.get(filePath);
            if (!Files.exists(savedFilePath)) {
                Files.createFile(savedFilePath);
            }
            strValues = values.getBytes("UTF-8");
            
            Files.write(savedFilePath, strValues);
            
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        	savedFilePath = null;
            strValues = null;
        }
    }	
	
    public String getMaxSummaryAndEscapeTag(String uSummary, int maxLength) {
        //String escapeSummary = getEsacpeHtml(uSummary);
    	
    	String escapeSummary = uSummary;
    	
        if (isNotEmpty(escapeSummary) && escapeSummary.length() > maxLength) {
        	escapeSummary = escapeSummary.substring(0, maxLength);
        }
        return escapeSummary;
    }

    
    public String getEsacpeHtml(String values) {
    	String noTagValue = values  ;
    	if (values != null ) {
    		noTagValue = textToHTML(values) ;
    		noTagValue= Jsoup.parse( noTagValue).text();		
    	} 
    	return noTagValue;
    }
    
    public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

   
    public  String textToHTML(String str) {
    	str = str.replace("&lt;", "<");
    	str = str.replace("&gt;", ">");
    	str = str.replace("&#96;", "/");
    	str = str.replace("&amp;", "&");
        return str;
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
	
    public boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    
    
	
	public List listDateMigData(Connection conn)  throws Exception  {
		String sql = "";
		
		List dateList = new ArrayList();
		
		sql = " select \n";
		sql += " 	a.r_object_id , \n";
		sql += " 	b.publicationdate  publicationdate \n";
		sql += " from  \n";
		sql += " ud_tbl_ecnc_ids_rsst_ctr_eai b, \n";
		sql += " ud_document_sp a  \n";
		sql += " where a.log_entry = 'mip_' || b.no || '_' || b.pbstablename \n";
		sql += " and a.subject ='mig_research2' \n";
		
				
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String[] data = new String[2];
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("publicationdate");
				
				dateList.add(data);				
			}
			
			
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
		
		return dateList;
	}
	

	public void updateDateSQL(Connection conn, String[] data)  throws Exception  {

		String r_object_id = data[0];
		String date = data[1];
		
		String sql = "update dm_sysobject_s set r_creation_date = to_date(substring('" + date +"',0,20),'YYYY-MM-DD HH24:MI:SS') where r_object_id = '" + r_object_id + "' ";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
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
	    
    
	//-- 경제산업연구센터, HRI's Contents

	public void updateSourceeSQL(Connection conn, String r_object_id)  throws Exception  {


		String sql = "update ud_document_s set u_in_pub_team='경제산업연구센터', u_in_pub_team_en ='HRI''s Contents'  where r_object_id ='" + r_object_id + "' ";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate(); 
			pstmt.close();
			
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
	
	
	public List  listSouceMigData(Connection conn)  throws Exception  {
		String sql = "";
		List srcList = new ArrayList();
		 
		sql = "select r_object_id from ud_document_sp where subject = 'mig_research2'  order by r_object_id desc \n";
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				srcList.add(rs.getString("r_object_id") );				
			}
			
			
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
		return srcList;
	}
	
	
	private List getPublicList() throws Exception {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List pubList = new ArrayList();
		
		String sql = "";
		
		sql = " select r_object_id , '202010,202020'  mapcode from ud_document_sp where subject= 'mig_research2' order by r_object_id desc \n"; 

		
		try { 
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			MigClassVO classVo = null;
			 
			while (rs.next()) {
				String data[] = new String[2];
				
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("mapcode");
				
				pubList.add(data); 
			}

			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
		return pubList;
		
	}			
	
	private void updatePubBland(IDfSession session, int index, String[] data) throws Exception {
		
		String r_object_id = "";
		String mapCode = "";
							
		try {	
			
			if( data != null ) {
				
				r_object_id = data[0];
				mapCode = data[1];
				
				if( mapCode != null && mapCode.length() > 2) {
					
					IDfDocument obj = (IDfDocument)session.getObject(new DfId(r_object_id) );
			
					String[] map = mapCode.split(",");
					
					obj.removeAll("u_in_pub_bland");
					obj.removeAll("u_view_company");
					
					for( int i=0; i<map.length; i++) {
						obj.appendString("u_in_pub_bland", map[i]);
						obj.appendString("u_view_company", map[i]);
					}
					
					obj.save();
				}

			}
			
		} catch (DfException e) {
			e.printStackTrace();
		}
			
		
	}

	
	private List delHRIPick(IDfSession session) throws Exception {
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql = " select r_object_id from ud_document_rp where u_pick_code ='20802' group by r_object_id \n";

		List list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			MigClassVO classVo = null;
			
			while (rs.next()) {
				list.add(rs.getString("r_object_id"));
			}

			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
			
			for(int i=0; i<list.size(); i++) {
				
				String r_object_id = (String)list.get(i);
				
				IDfDocument obj = (IDfDocument)session.getObject(new DfId(r_object_id) );
			
				if( obj!= null ) {					
					obj.removeAll("u_pick_code");
					obj.save();
				}
			}
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
		return list;
		
	}			
	
	
	
	private List pickCategoryDocList() throws Exception {
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql = " select \n";
		sql += " 	a.fileid,  \n";
		sql += " 	b.object_name,  \n";
		sql += " 	a.pbbsmasterid,  \n";
		sql += " 	b.r_creation_date,  \n";
		sql += " 	b.r_object_id   \n";
		sql += " from   \n";
		sql += " 	ud_tbl_research_economy2 a,  \n";
		sql += " 	ud_document_sp b  \n";
		sql += " where   \n";
		sql += " 	a.pbbsmasterid in ('7','5','24')  \n"; 
		sql += " 	and substring(a.fileid,1,29)  = b.u_project_cd   \n";
		sql += " union \n";
		sql += " 	select  \n";
		sql += " 	c.fileid,  \n";
		sql += " 	d.object_name,  \n";
		sql += " 	c.pbbsmasterid,  \n";
		sql += " 	d.r_creation_date,  \n";
		sql += " 	d.r_object_id   \n";
		sql += " from   \n";
		sql += " 	ud_tbl_ecnc_ids_rsst_ctr_eai c,  \n";
		sql += " 	ud_document_sp d  \n";
		sql += " where   \n";
		sql += " 	c.pbbsmasterid in ('7','5','24')   \n";
		sql += " 	and d.log_entry  = 'mip_' || c.no || '_' || c.pbstablename \n";
		sql += " order by r_creation_date  desc  \n";
		sql += " limit 5  \n";
		//CEO Report:7, 경영환경전망:5, 연구프로젝트:24

		List list = new ArrayList();
		List tmpList = new ArrayList();
		int indx = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String data[] = new String[2];
				
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("pbbsmasterid");
				
				tmpList.add(rs.getString("r_object_id"));
				
				indx++;
				
				list.add(data);
			}

			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
		
		sql = " select  \n";
		sql += " 	c.fileid,   \n";
		sql += " 	d.object_name,   \n";
		sql += " 	c.pbbsmasterid,   \n";
		sql += " 	d.r_creation_date,  \n"; 
		sql += " 	d.r_object_id    \n";
		sql += " from    \n";
		sql += " 	ud_tbl_ecnc_ids_rsst_ctr_eai c,   \n";
		sql += " 	ud_document_sp d   \n";
		sql += " where    \n";
		sql += " 	d.log_entry  = 'mip_' || c.no || '_' || c.pbstablename \n";
		sql += " order by r_creation_date  desc   \n";
		sql += " limit 20 \n";
        // 최신순 5개	: 위의 5개가 중복될것 고려		
		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next() && indx < 15) {
				String data[] = new String[2];
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("pbbsmasterid");				
				
				// 기존에 추가된 내역이 없으면 ...
				if( !tmpList.contains(rs.getString("r_object_id")) ) {
					indx++;
					tmpList.add(rs.getString("r_object_id"));
					list.add(data);
				}
			}

			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;

			tmpList.clear();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}	
		
		return list;
		
	}			
	
	
    
}