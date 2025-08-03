package com.batch.mig.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.mig.vo.EconomyVO;

/**
* @Class Name  : EconomicMig.java
* @Description : 경제지표 API 를 호출하여 지표 값을 받아 온다.  
* @author      : 개발환경 개발팀 송희순
* @since       : 2022. 03. 07
* @version     : 1.0
* @see
*
* @Modification Information
* <pre>
* 수정일 수정자 수정내용
* ------- -------- ---------------------------
* 2022.03.07 송희순 최초 생성
* </pre>
*
*/
public class EconomicMig {

	private final static Logger log = Logger.getLogger(EconomicMig.class);
	
	private static Connection conn;
	private static Configurations config;
	
	//------------------------------------------
	// 코드 매핑용 저장소
	//------------------------------------------
	private static HashMap<String, String> hmNationCd;		// ECM 국가코드  
	private static HashMap<String, String> hmAreaCd;		// ECM 권역코드   
	private static HashMap<String, String> hmWBNationCd;	// World Bank 코드
	
	/***
	 * 배치 실행 영역
	 * @param args
	 */
	public static void main(String[] args) {
		String btype = "com"; // 환율 : cur, 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, GNI : gni, OIL : com 
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		
		System.out.println("\n");
		System.out.println("\n\n=========================================");
		printInfo2("economic batch start.... " + timeStamp);
		//===============================================
		// 환경 정보 로드
		//===============================================
		config = new Configurations();
		config.getConfig();		
		
		//===============================================
		// 코드 매핑할 코드 매핑 리스트 조회 (국가변경/신규시 수정 필수)
		//-----------------------------------------------
		// initCodeList() 함수안에 경제 지표 관련 설명 참고
		//===============================================
		initCodeList();
		
		//===============================================
		// 상품선물 (유가) 
		//===============================================
		runBatchCommodities(btype);			// 상품선물 (유가)
		
		//===============================================
		// 배치 유형별 경제 지표 mig 작업 수행	
		//-----------------------------------------------
		// https://tradingeconomics.com/forecasts 구
		// 위 url에 표시된 국가 목록이 있어야 함. (
		//===============================================
		runBatchByNation("south korea");	// KR : 대한민국, 국내
		runBatchByNation("united states");	// US : 미국
		runBatchByNation("germany");		// DE : 독일
		runBatchByNation("china");			// CN : 중국
		runBatchByNation("india");			// IN : 인도 
		runBatchByNation("saudi arabia");	// SA : 사우디
		runBatchByNation("russia");			// RU : 러시아
		runBatchByNation("brazil");			// BR : 브라질
		runBatchByNation("indonesia");		// ID : 인도네시아 
		runBatchByNation("malaysia");		// MY : 말레이시아
		runBatchByNation("australia");		// AU : 호주     (2022-05-24 신규추가)
		
		//===============================================
		// (2022-07-22)(추가) 
		//-----------------------------------------------
		// 영국, 프랑스, 이탈리아, 스페인, 멕시코, 일본, 베트남, (태국)
		// united kingdom, france, italy, spain, mexico, japan, vietnam, thailand
		//===============================================
		runBatchByNation("united kingdom");	// GB : 영국
		runBatchByNation("france");			// FR : 프랑스
		runBatchByNation("italy");			// IT : 이탈리아
		runBatchByNation("spain");			// ES : 스페인
		runBatchByNation("mexico");			// MX : 멕시코
		runBatchByNation("japan");			// JP : 일본
		runBatchByNation("vietnam");		// VN : 베트남
		runBatchByNation("thailand");		// TH : 태국

		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		printInfo2("economic batch end .... " + timeStamp + "\n\n\n");

	}
	
	/***
	 * 국가별로 각 지표릴 실행한다.
	 * @param nation
	 */
	public static void runBatchByNation(String nation) {

		//------------------------------------------------------
		// 환율
		//------------------------------------------------------
		runBatchCurrency("cur",nation);		

		//------------------------------------------------------
		// 지수
		//------------------------------------------------------
		if ( nation.equals("south korea")) { 
			runBatchIndexKOSPI("ind", nation); // kospi 는 다른 api 로 호출
		} else {
			runBatchIndex("ind", nation);
		}
			
		//------------------------------------------------------
		// 금리
		//------------------------------------------------------		
		runBatchInterestRate("int", nation); 	

		//------------------------------------------------------
		// cpi
		//------------------------------------------------------		
		runBatchCpiInfo("cpi", nation);		

		//------------------------------------------------------
		// gdp growth rate
		//------------------------------------------------------		
		runBatchGdpInfo("gdp", nation);		

		//------------------------------------------------------
		// 인구
		//------------------------------------------------------		
		runBatchPopulation("pop", nation);		

		//------------------------------------------------------
		// 실업률
		//------------------------------------------------------		
		runBatchUnEmployment("unemp", nation); 	

		//------------------------------------------------------
		// 1인당국민총소득
		//------------------------------------------------------	
		if ( nation.equals("south korea") || nation.equals("russia") ) {
			runBatchGdpPerCapitaInfo("gni", nation);	// 한국과 러시아는 GNI 정보가 없어서 GDP PER CAPITA 정보로 대체해서 보여줌.					
		} else {
			runBatchGniInfo("gni", nation);
		}
	}
		
	/***
	 * 환율 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchCurrency(String indType, String nation) {
		//====================================================================
		// 환율
		//====================================================================				  
		String params = "currency";	
                
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation); 
        String path = "/country/" + url_nation + "/" + params;
                
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);        	
        } catch (Exception ex) {
        	log.info("error cur1 : " + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
        	//---------------------------------------------------------------
        	// 환율 정보가 0건이면 다시 한번 더 호출 시도한다.
        	//---------------------------------------------------------------
        	if ( jsonArray.length() == 0 ) {
                try {
                	Thread.sleep(1000); // 약간의 delay
                	
                	printInfo("//====// : >>> : retry get currency for [" + nation + "]");
                	
                	ecoContents = constructUrl(indType, path);
                	
                } catch (Exception ex) {
                	log.info("error cur2 : " + ex.getMessage());
                }      
                
                jsonArray = new JSONArray(ecoContents);
                
                printInfo("retry response data count : " + jsonArray.length());
        	}
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getCurrencyInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================     
        	String ecm_nation = hmNationCd.get(nation); 
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}

	/***
	 * 환율 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getCurrencyInfo(JSONArray jsonArray, String nation) {		
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
	
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표					
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("cur");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;
	}
			
	/***
	 * 지수 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchIndex(String indType, String nation) {		
		//====================================================================
		// 지수
		//====================================================================				  
		String params = "index";	
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/markets/search/" + url_nation + "?category=" + params;

        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getIndexInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================   
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
		
	/***
	 * 지수 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getIndexInfo(JSONArray jsonArray, String nation) {
		
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "Date"));				// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "Ticker"));					// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("ind");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "Last"));					// 수치값
					vo.setUnit(getJsonString(jsonObject, "unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
										
					vo.setIndValue(getRoundValue(vo.getIndValue()));					// 소숫점 제거
					
					list.add(vo);
				}
			}
		}
		
		return list;		
	}	
	
	/***
	 * 지수 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchIndexKOSPI(String indType, String nation) {		
		//====================================================================
		// 지수
		//====================================================================				  
		String params = "index";	
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/forecast/country/" + url_nation;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getIndexInfoKOSPI(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================   
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
	
	/***
	 * 지수 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getIndexInfoKOSPI(JSONArray jsonArray, String nation) {
		
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					String symbol = getJsonString(jsonObject, "HistoricalDataSymbol");
					
					if ( symbol.equals("KOSPI") ) {
						EconomyVO vo = new EconomyVO();
						vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));	// 기준일자	
						vo.setSymbol(symbol);											// 지표
						vo.setAreaCode(hmAreaCd.get(nation)); 							// 권역코드
						vo.setNationalCode(hmNationCd.get(nation));						// 국가코드			
						vo.setIndType("ind");											// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
						vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));		// 수치값
						vo.setUnit("");													// 단위
						vo.setFrequency(getJsonString(jsonObject, "Frequency"));		// 주기			
						vo.setBatch_date(getJsonTimestamp(null, "current"));			// batch time msec
						vo.setBatch_flag("N");											// batch 처리 완료 여부 y/n
						
						vo.setIndValue(getRoundValue(vo.getIndValue()));				// 소숫점 제거
						
						list.add(vo);
						
						break;
					}
				}
			}
		}
		
		return list;		
	}		
			
	/***
	 * 금리 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchInterestRate(String indType, String nation) {
		String params = "";
		//====================================================================
		// 금리
		//====================================================================				  
        params = "interest rate";	
        params = params.replaceAll("\\s","%20");
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getInterestRate(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================    
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
	
		
	/***
	 * 금리 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getInterestRate(JSONArray jsonArray, String nation) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("int");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;
	}	
		
	/***
	 * cpi 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchCpiInfo(String indType, String nation) {
		String params = "";
		//====================================================================
		// 소비자물가
		//====================================================================				  
        params = "inflation rate";	
        params = params.replaceAll("\\s","%20");
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getCpiInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================        	
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
		
	/***
	 * cpi json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getCpiInfo(JSONArray jsonArray, String nation) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("cpi");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;
	}			
	
	/***
	 * gdp 성장률 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchGdpInfo(String indType, String nation) {		
		//====================================================================
		// GDP
		//====================================================================				  
		String params = "gdp growth rate";
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getGdpInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================    
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
		
	/***
	 * gdp json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getGdpInfo(JSONArray jsonArray, String nation) {		
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("gdp");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;		
	}		

		
	/***
	 * 인구 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchPopulation(String indType, String nation) {		
		//====================================================================
		// 인구수
		//====================================================================				  
		String params = "population";	
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getPopulation(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================    
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }  
	}	
	
	/***
	 * 인구 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getPopulation(JSONArray jsonArray, String nation) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("pop");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;	
	}	
		
	/***
	 * 실업률 정보를 가져온다.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchUnEmployment(String indType, String nation) {
		String params = "";
		//====================================================================
		// 실업률
		//====================================================================				  
        params = "unemployment rate";	
        params = params.replaceAll("\\s","%20");
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getUnEmployment(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================       
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }  
	}	
		
	/***
	 * 실업률 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getUnEmployment(JSONArray jsonArray, String nation) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("unemp");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					list.add(vo);
				}
			}
		}
		
		return list;
	}		
	
	

	
	/***
	 * 1인당 국민총소득 정보를 가져온다.
	 * @param indType
	 */
	public static void runBatchGniInfo(String indType, String nation) {
		String params = "";
		
		//====================================================================
		// 세계은행 지표 카테고리 리스트에서 1인당 총소득에 해당하는 Series_code 조회
		//====================================================================
		//String Series_code = getWBGategoryList();  // 서비스코드 목록을 뽑을 수 있는지 확인 필요.  ( category, url 등을 이용하여 )
		String wbnation    = hmWBNationCd.get(nation); 
		String Series_code = "NY.GNP.PCAP.PP.CD"; // GNI per capita, PPP (current international $)
		
		if ( StringUtils.isBlank(wbnation) ) {
			//wbnation = "DEU"; // 임시
			return;
		}
			
		Series_code = wbnation + "." + Series_code;
		
		//====================================================================
		// put category here (category can be: index, bond, currency and commodity)
		//====================================================================				  
        params = Series_code;	// 지표코드
        params = params.replaceAll("\\s","%20");
                
        //====================================================================
        // set the path for the query
        //====================================================================
        String path = "/worldBank/indicator" + "?s=" + Series_code;
                
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);        	
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getGniInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================     
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }
	}
	
	/***
	 * 세계은행 지표 카테고리 리스트 
	 * @return
	 */
	public static String getWBGategoryList() {
		String params  = "";
		String indType = "WBCategory";
		//====================================================================
		// url 로 가져오는게 더 심플한데.... 실 계정 받고 확인 필요.
		//====================================================================				  
        params = "Economy & Growth";	// 카테고리
        params = params.replaceAll("\\s","%20");
                
        //====================================================================
        // set the path for the query
        //====================================================================
        String path = "/worldBank/category" + "/" + params ;
                
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);        	
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }	
        
        //====================================================================
        // 1인당 국민 총소득에 해당하는 Series_code 를 찾는다 (예 : BG.GSR.NFSV.GD.ZS )
        //====================================================================
        if ( ecoContents != null && ecoContents.length() > 0) {
        	JSONArray jsonArray = new JSONArray(ecoContents);
        	
            if ( jsonArray != null ) {
            	printInfo2("[" +  indType +"] response data count : " + jsonArray.length());
            	
            	for( int i = 0; i < jsonArray.length(); i++) {
            		JSONObject jsonObject = (JSONObject)jsonArray.get(i);
            		
            		String Series_name = getJsonString(jsonObject, "Series_name");
            		
            		if ( Series_name.toLowerCase().indexOf("gni") >= 0 ) {
	            		printInfo("//==========================================================================// ");
	            		printInfo("// Series_code   : " + getJsonString(jsonObject, "Series_code"));
	            		printInfo("// Series_name   : " + getJsonString(jsonObject, "Series_name"));
	            		printInfo("// Category      : " + getJsonString(jsonObject, "Category"));
	            		printInfo("// Sub_category  : " + getJsonString(jsonObject, "Sub_category"));
	            		printInfo("// Sub_category2 : " + getJsonString(jsonObject, "Sub_category2"));
	            		printInfo("// Sub_category3 : " + getJsonString(jsonObject, "Sub_category3"));
	            		printInfo("// Title         : " + getJsonString(jsonObject, "Title"));
            		}
            		
            	}
            }        	
        }
        
        return ecoContents;
	}
	
	/***
	 * 1인당 국민총소득 json 데이터를 vo 에 넣는다.
	 * @param jsonObject
	 */
	public static ArrayList<EconomyVO> getGniInfo(JSONArray jsonArray, String nation) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "date"));	// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "symbol"));		// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 					// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));				// 국가코드			
					vo.setIndType("gni");									// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "last"));		// 수치값
					vo.setUnit(getJsonString(jsonObject, "unit"));			// 단위
					vo.setFrequency(getJsonString(jsonObject, "frequency"));// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));	// batch time msec
					vo.setBatch_flag("N");									// batch 처리 완료 여부 y/n
					
					vo.setIndValue(getRoundValue(vo.getIndValue()));		// 소숫점 제거
					
					list.add(vo);
				}
			}
		}
		
		return list;
	}
	
	/***
	 * 한국과 러시아는 gni 정보가 없으므로 gdp per capita 를 보여줌.
	 * @param indType
	 * @param nation
	 */
	public static void runBatchGdpPerCapitaInfo(String indType, String nation) {		
		//====================================================================
		// GDP
		//====================================================================				  
		String params = "gdp per capita";
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String url_nation = nation; // hmTestCd.get(nation);
        String path = "/country/" + url_nation + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        if ( jsonArray != null ) {
        	printInfo2("[" +  indType + "/" +  nation + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getGdpPerCapitaInfo(jsonArray, nation);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================    
        	String ecm_nation = hmNationCd.get(nation);
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, ecm_nation);
        	}
        }     
	}	
		
	/***
	 * 한국과 러시아는 gni 정보가 없으므로 gdp per capita 를 보여줌.
	 * @param jsonArray
	 * @param nation
	 * @return
	 */
	public static ArrayList<EconomyVO> getGdpPerCapitaInfo(JSONArray jsonArray, String nation) {		
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
		
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
					vo.setStdDate(getJsonTimestamp(jsonObject, "LatestValueDate"));		// 기준일자	
					vo.setSymbol(getJsonString(jsonObject, "HistoricalDataSymbol"));	// 지표
					vo.setAreaCode(hmAreaCd.get(nation)); 								// 권역코드
					vo.setNationalCode(hmNationCd.get(nation));							// 국가코드			
					vo.setIndType("gni");												// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "LatestValue"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "Unit"));						// 단위
					vo.setFrequency(getJsonString(jsonObject, "Frequency"));			// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));				// batch time msec
					vo.setBatch_flag("N");												// batch 처리 완료 여부 y/n
					
					vo.setIndValue(getRoundValue(vo.getIndValue()));					// 소숫점 제거
					
					list.add(vo);
				}
			}
		}
		
		return list;		
	}			
	
	/***
	 * 상품선물 정보를 가져온다.
	 * @param indType
	 */
	public static void runBatchCommodities(String indType) {
		String params = "";
		//====================================================================
		// put category here (category can be: index, bond, currency and commodity)
		//====================================================================				  
        params = "commodities";	// 지수
        params = params.replaceAll("\\s","%20");
        
        //====================================================================
        // set the path for the query
        //====================================================================
        String path = "/markets" + "/" + params;
        
        //====================================================================
        // 경제 지표 API 를 호출한다.
        //====================================================================
        String ecoContents = "";
        try {
        	ecoContents = constructUrl(indType, path);
        } catch (Exception ex) {
        	log.info("" + ex.getMessage());
        }
        
        //====================================================================
        // 데이터를 파싱한다.
        //====================================================================      
        JSONArray jsonArray = new JSONArray(ecoContents);
        
        if ( jsonArray != null ) {
        	printInfo2("[" + indType + "] response data count : " + jsonArray.length());
        	
            //====================================================================
            // 데이터를 파싱한다.
            //====================================================================        	
        	ArrayList<EconomyVO> list = getCommodities(jsonArray);
        	
            //====================================================================
            // MIG 테이블에 저장한다. 
            //====================================================================        	
        	if ( list != null && list.size() > 0) {
        		insertDBRows(indType, list, "commodity");
        	}
        }
	}	
	
	/***
	 * 상품선물 json 데이터를 vo 에 넣는다.
	 * @param jsonArray
	 */
	public static ArrayList<EconomyVO> getCommodities(JSONArray jsonArray) {
		ArrayList<EconomyVO> list = new ArrayList<EconomyVO>();
	
		if ( jsonArray != null ) {
			for( int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if ( jsonObject != null ) {
					EconomyVO vo = new EconomyVO();
														
					//====================================================
					// 상품선물명
					//====================================================
					String symbol = getJsonString(jsonObject, "Symbol");		
					
					// WTI (CL1:COM) 만 포함
					if ( symbol.indexOf("CL1") < 0) {
						continue;
					} 
					
					if ( symbol != null && symbol.indexOf(":") > 0 ) {
						vo.setSymbol(symbol.split(":")[0]); 									
					} else {
						vo.setSymbol(symbol);			
					}
					//====================================================
					// 상품선물명
					//====================================================	
					vo.setStdDate(getJsonTimestamp(jsonObject, "Date"));		// 기준일자	
					vo.setAreaCode("000000"); 									// 권역코드 (없음)
					vo.setNationalCode("commodity");							// 지표 관리 국가코드 (코드 변환 필요)
					vo.setIndType("com");										// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
					vo.setIndValue(getJsonDouble(jsonObject, "Last"));			// 수치값
					vo.setUnit(getJsonString(jsonObject, "unit"));				// 단위
					vo.setFrequency(getJsonString(jsonObject, ""));				// 주기			
					vo.setBatch_date(getJsonTimestamp(null, "current"));		// batch time msec
					vo.setBatch_flag("N");										// batch 처리 완료 여부 y/n		
					
					if ( symbol.indexOf("CL1") >= 0) {
						
						vo.setIndValue(getRoundValue(vo.getIndValue()));					// 소숫점 제거
						
						list.add(vo);
						break;
					}
				}
			}
		}
		
		return list;
	}	
	
	
	/***
	 * 지원하지 않는 로그 알람
	 */
	public static void alarmNoEconomicBatchType() {
		printInfo("수집할 economic data type 에 대한 parameter 값이 필요합니다.");
		printInfo("예) 환율 : cur, 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni");
	}	
	
	/***
	 * 경제지표 API 를 호출하여 결과 값을 받는다. (결과양식 : json)
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public static String constructUrl(String indType, String path) throws IOException{
    	String sContents    = "";
    	String _clientKey 	= config.getProperty("api.economic.client_key");
    	
    	try {
    		sContents    = constructUrl(indType, path, _clientKey);
    	} catch (Exception ex ) {
    		throw ex;
    	}
    	return sContents;   	
    }
	
	/***
	 * 경제지표 API 를 호출하여 결과 값을 받는다. (결과양식 : json)
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public static String constructUrl(String indType, String path, String _clientKey) throws IOException{
        String base_url 	= config.getProperty("api.economic.base_url");
        String auth;
        
        StringBuffer response = new StringBuffer();
        try {        	
        	//===============================================
        	// 설정 정보 확인 및 로그
        	//===============================================
        	printInfo("base_url  : " + base_url);
        	printInfo("clientKey : " + _clientKey);  
	        printInfo("//=========================================================//");
	        printInfo("receive " + indType + " : start : " + path);
	        printInfo("//=========================================================//");	          	
        	
        	//===============================================
        	// 계정
        	//===============================================
	        if (path.contains("?"))
	            auth = base_url + path + "&c=" + _clientKey;
	        else
	            auth = base_url + path + "?c=" + _clientKey;
	
      
	        //===============================================
	        // 경제지표 사이트 연결
	        //===============================================
	        URL obj = new URL(auth);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	        //===============================================
	        // 데이터 송수신
	        //===============================================
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;	        
	
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        
	        printInfo("receive " + indType + " : data : " + response.toString());
	        printInfo("//=========================================================//");
	        printInfo("receive " + indType + " : end");
	        printInfo("//=========================================================//");

        } catch (Exception ex ) {
        	log.info("[constructUrl]" + ex.getMessage());
        }

        return response.toString();
    }
    
    /***
     * 경제 지표 리스트를 저장한다.
     * @param indType
     * @param list
     */
    public static void insertDBRows(String indType, ArrayList<EconomyVO> list, String ecm_nation) {
        try {
        	Thread.sleep(100); // 약간의 delay
        	
        	conn = DBUtil.getConnection(config); // db 연결
        	            	
        	if ( conn != null ) {
        		
            	// 오늘 실행한 데이터가 있으면 이를 삭제한다.
            	deleteDBRaw(conn, indType, ecm_nation);            		
        		
        		for( int i = 0; i < list.size(); i++) {
        			
        			EconomyVO vo = list.get(i);
        			
        			//-------------------------
        			// 저장 쿼리 실행
        			//-------------------------
        			if ( vo.getStdDate() != null ) {
        				insertDBRaw(conn, vo);
        			}
        		}
        	}
        	
        } catch (Exception ex1) {
        	log.info("" + ex1.getMessage());
        } finally {
        	if ( conn != null ) {
        		try {
        			conn.close();
        		} catch (Exception dbex) {
        			log.info("" + dbex.getMessage()); 
        		}
        	}
        }    	
    }
    
    /***
     * 경제지표 데이터 테이블 저장 
     * @param conn
     * @param vo
     */
	public static void insertDBRaw(Connection conn, EconomyVO vo) {
		
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql = " INSERT INTO ud_tbl_economy_info (";
		sql += "    std_date     ";       	// 기준일자
		sql += "   ,symbol       ";       	// 지표명
		sql += "   ,area_code    ";       	// 권역코드
		sql += "   ,nation_code  ";       	// 지표 관리 국가코드
		sql += "   ,ind_type     ";      	// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
		sql += "   ,ind_value    ";    		// 수치값
		sql += "   ,unit  		 ";       	// 단위
		sql += "   ,frequency    ";       	// 주기
		sql += "   ,batch_date   ";       	// batch time msec
		sql += "   ,batch_flag   ";        	// batch 처리 완료 여부 y/n		
		sql += ") values (";
		sql += " ?,?,?,?,?";
		sql += ",?,?,?,?,?";
		sql += ")";
				
		int idx = 1 ;
		
		try {			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setTimestamp(idx++, vo.getStdDate());		// 기준일자
			pstmt.setString(idx++, vo.getSymbol());			// 지표명
			pstmt.setString(idx++, vo.getAreaCode());		// 권역코드
			pstmt.setString(idx++, vo.getNationalCode());	// 지표 관리 국가코드
			pstmt.setString(idx++, vo.getIndType() );		// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
			pstmt.setDouble(idx++, vo.getIndValue() );		// 수치값
			pstmt.setString(idx++, vo.getUnit() );			// 단위
			pstmt.setString(idx++, vo.getFrequency());		// 주기
			pstmt.setTimestamp(idx++, vo.getBatch_date());	// batch time msec
			pstmt.setString(idx++, vo.getBatch_flag());		// batch 처리 완료 여부 y/n		
			
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
	
    /***
     * 경제지표 데이터 테이블 저장 전에 당일 데이타가 이미 있으면 삭제한다.  
     * @param conn
     * @param indType 
     * @param vo
     */
	public static void deleteDBRaw(Connection conn, String indType, String ecm_nation) {
		
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql =  " DELETE FROM ud_tbl_economy_info  ";
		sql += " WHERE 1=1         				  ";     
		sql += "   AND ind_type = ? 			  "; // 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
		sql += "   AND nation_code = ?            "; // 국가코드
		sql += "   AND batch_date BETWEEN ? AND ? "; // batch time msec
		
		int idx = 1 ;
		
		try {			
			//===============================================
			// 삭제 대상 파라메터 정리
			//===============================================
			LocalDate dt  = LocalDate.now();
			Date   dtCur  = Date.valueOf(dt);
			Date   dtNext = Date.valueOf(dt.plusDays(1));
			
			printInfo("dtCur : " + dtCur.toString() + ", dtNext : " + dtNext.toString());
			
			//===============================================
			// 쿼리 실행
			//===============================================			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(idx++, indType);	// 지표유형 (환율 : cur", 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : com, GNI : gni)
			pstmt.setString(idx++, ecm_nation);	// 국가코드
			pstmt.setDate(idx++, dtCur);		// 지표명
			pstmt.setDate(idx++, dtNext);		// 등록일자
			
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
	
    /***
     * 키 값이 있는지 체크하여 값을 리턴한다.
     * @param jsonObject
     * @param key
     * @return
     */
    private static String getJsonString(JSONObject jsonObject, String key) {
    	String sRtn = "";
    	
    	if ( jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key)) {
    		sRtn = jsonObject.getString(key);
    	}
    	
    	return sRtn; 
    }    
    
    
    /***
     * 키 값이 있는지 체크하여 값을 리턴한다.
     * @param jsonObject
     * @param key
     * @return
     */
    private static Double getJsonDouble(JSONObject jsonObject, String key) {
    	Double dRtn = 0D;
    	
    	if ( jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key)) {
    		try {
    			dRtn = jsonObject.getDouble(key);
    		} catch (Exception ex) {
    			log.info(ex.getMessage()); 
    		}
    	}
    	
    	return dRtn; 
    }       
    
    
    /***
     * Double 형의 수치에서 소수점을 제거한다.
     * @param ind_value
     * @param nRound
     * @return
     */
    private static Double getRoundValue(Double ind_value) {
    	Long   nValue  = 0L;
    	Double nResult = 0D;
    	
    	try {
    		
    		nValue = Math.round(ind_value);    		
    		nResult = Double.valueOf(nValue.toString());
    		
    	} catch (Exception ex ) {
    		nResult = ind_value;
    		log.info(ex.getMessage()); 
    	}
    	
    	return nResult;
    }
    
    /***
     * 키 값이 있는지 체크하여 값을 리턴한다.
     * @param jsonObject
     * @param key
     * @return
     */
    private static Timestamp getJsonTimestamp(JSONObject jsonObject, String key) {
    	Timestamp dRtn = null;
    	
    	if ( jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key)) {
    		try {
    			//---------------------------------------------
    			// date 형 데이터 string 추출
    			//---------------------------------------------
    			String sRtn = "";
    			sRtn = jsonObject.getString(key); 
    			
    			//---------------------------------------------
    			// Date 형으로 변환
    			//---------------------------------------------    			
    			LocalDateTime dt = LocalDateTime.parse(sRtn, DateTimeFormatter.ISO_DATE_TIME); // 예) 2022-03-07T06:49:00
    			
    			dRtn = Timestamp.valueOf(dt);
    			
    		} catch (Exception ex) {
    			log.info(ex.getMessage()); 
    		}
    	} else {
			if ( key == "current" ) {		
				dRtn = Timestamp.valueOf(LocalDateTime.now());
			}    		
    	}
    	
    	return dRtn; 
    }          
    
    /***
     * 로그를 남긴다.
     * @param sLog
     */
    private static void printInfo(String sLog) {
    	log.info(sLog);
    }
    

    private static void printInfo2(String sLog) {
		String timeStamp = new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date());

		System.out.println("[" +  timeStamp + "] " + sLog);

    }
    
	/***
	 * 매핑 코드 초기화
	 */
	public static void initCodeList() {
		
		/*
		 * 2022.07.12 국가 추가 요건
		 * 영국, 프랑스, 이탈리아, 스패인, 멕시코, 일본, 베트남, (태국)
		 * united kingdom, france, italy, spain, mexico, japan, vietnam, thailand
		 */
		
		//========================================================
		// 국가코드 매핑 (SELECT * FROM ud_code_s WHERE u_parent_cd = '109')
		//========================================================
		if ( hmNationCd == null ) hmNationCd = new HashMap<String, String>(); 
		hmNationCd.put("south korea"			, "KR");	// 대한민국, 국내
		hmNationCd.put("united states"			, "US");	// 미국
		hmNationCd.put("germany"				, "DE");	// 독일
		hmNationCd.put("china"					, "CN");	// 중국
		hmNationCd.put("india"					, "IN");	// 인도
		hmNationCd.put("indonesia"				, "ID");	// 인도네시아
		hmNationCd.put("saudi arabia"			, "SA");	// 사우디
		hmNationCd.put("russia"					, "RU");	// 러시아
		hmNationCd.put("brazil"					, "BR");	// 브라질
		hmNationCd.put("malaysia"				, "MY");	// 말레이시아
		hmNationCd.put("australia"				, "AU");	// 호주
		
		hmNationCd.put("united kingdom"			, "GB");	// 영국
		hmNationCd.put("france"					, "FR");	// 프랑스
		hmNationCd.put("italy"					, "IT");	// 이탈리아
		hmNationCd.put("spain"					, "ES");	// 스페인
		hmNationCd.put("mexico"					, "MX");	// 멕시코
		hmNationCd.put("japan"					, "JP");	// 일본
		hmNationCd.put("vietnam"				, "VN");	// 베트남
		hmNationCd.put("thailand"				, "TH");	// 태국
				
		//========================================================
		// 권역코드 매핑
		//========================================================		
		if ( hmAreaCd == null ) hmAreaCd = new HashMap<String, String>(); 
		hmAreaCd.put("south korea"			, "101010");	// 대한민국, 국내
		hmAreaCd.put("united states"		, "101020");	// 미국
		hmAreaCd.put("germany"				, "101030");	// 독일
		hmAreaCd.put("china"				, "101040");	// 중국
		hmAreaCd.put("india"				, "101050");	// 인도
		hmAreaCd.put("indonesia"			, "101060");	// 인도네시아
		hmAreaCd.put("saudi arabia"			, "101070");	// 사우디
		hmAreaCd.put("russia"				, "101080");	// 러시아
		hmAreaCd.put("brazil"				, "101090");	// 브라질		
		hmAreaCd.put("malaysia"				, "101060");	// 말레이시아 
		hmAreaCd.put("australia"			, "101060");	// 호주      (아태 : 기아는 호주를 보여준다.)
		
		hmAreaCd.put("united kingdom"		, "101030");	// 영국        
		hmAreaCd.put("france"				, "101030");	// 프랑스  
		hmAreaCd.put("italy"				, "101030");	// 이탈리아
		hmAreaCd.put("spain"				, "101030");	// 스패인
		hmAreaCd.put("mexico"				, "101090");	// 멕시코
		hmAreaCd.put("japan"				, "101060");	// 일본
		hmAreaCd.put("vietnam"				, "101060");	// 베트남
		hmAreaCd.put("thailand"				, "101060");	// 태국		
		
		//========================================================
		// World Bank 쿼리 위한 국가코드
		//========================================================
		if ( hmWBNationCd == null ) hmWBNationCd = new HashMap<String, String>(); 
		hmWBNationCd.put("south korea"			, "");		// 대한민국, 국내 (World Bank 에 gni 정보가 없음)
		hmWBNationCd.put("united states"		, "USA");	// 미국 (https://tradingeconomics.com/united-states/gni-per-capita-ppp-us-dollar-wb-data.html)
		hmWBNationCd.put("germany"				, "DEU");	// 독일
		hmWBNationCd.put("china"				, "CHN");	// 중국
		hmWBNationCd.put("india"				, "IND");	// 인도		
		hmWBNationCd.put("saudi arabia"			, "SAU");	// 사우디
		hmWBNationCd.put("russia"				, "");		// 러시아 (World Bank 에 gni 정보가 없음)
		hmWBNationCd.put("brazil"				, "BRA");	// 브라질
		hmWBNationCd.put("indonesia"			, "IDN");	// 인도네시아
		hmWBNationCd.put("malaysia"				, "MYS");	// 말레이시아 (https://tradingeconomics.com/malaysia/gni-per-capita-ppp-us-dollar-wb-data.html)
		hmWBNationCd.put("australia"			, "AUS");	// 호주   (2022-05-24 신규추가)
		
		hmWBNationCd.put("united kingdom"		, "GBR");	// 영국
		hmWBNationCd.put("france"				, "FRA");	// 프랑스
		hmWBNationCd.put("italy"				, "ITA");	// 이탈리아
		hmWBNationCd.put("spain"				, "ESP");	// 스페인
		hmWBNationCd.put("mexico"				, "MEX");	// 멕시코
		hmWBNationCd.put("japan"				, "JPN");	// 일본
		hmWBNationCd.put("vietnam"				, "VNM");	// 베트남
		hmWBNationCd.put("thailand"				, "THA");	// 태국	
		
		//========================================================
		// Symbol 및 WB 국가코드 찾기
		//========================================================
		// https://sso.tradingeconomics.com/sso 에 정상적인 계정으로 로그인해서 진행하자 않으면 샘플외에는 보이지 않음. 
		// 개발 시전에는 (hschoi2@bsgglobal.com/hmckia0414!) 를 사용하여 진행함.
		//--------------------------------------------------------
		// WB GNI 국가리스트 
		// 1. https://tradingeconomics.com/country-list/gni-per-capita-ppp-us-dollar-wb-data.html  화면에서 국가(사우디) 선택
		// 2. url 복사 아래와 같이 찾기
		//--------------------------------------------------------
		// 1. https://api.tradingeconomics.com/worldBank/indicator?url=/saudi-arabia/gni-per-capita-ppp-us-dollar-wb-data.html
		// 2. SYMBOL -> SAU.NY.GNP.PCAP.PP.CD
		// 3. saudi-arabia = SAU  <- SAU 코드 확인후... 위의 hmWBNationCd에 코드에 추가
		//--------------------------------------------------------
		// 1. https://api.tradingeconomics.com/worldBank/indicator?url=/united-states/real-interest-rate-percent-wb-data.html
		// 2. SYMBOL -> USA.FR.INR.RINR
		// 3. united-states = USA
		//========================================================
		
		//========================================================
		// 개발자 참고 (트래픽 사용량 확인 가능, 사이트 인증키 확인)
		//--------------------------------------------------------
		// https://developer.tradingeconomics.com/Home
		//========================================================		
		
		//========================================================
		// 테스트 국가
		//========================================================		
		/*
		if ( hmTestCd == null ) hmTestCd = new HashMap<String, String>(); 
		hmTestCd.put("south korea"			, "sweden");	// 대한민국, 국내
		hmTestCd.put("united-states"		, "sweden");	// 미국
		hmTestCd.put("germany"				, "sweden");	// 독일
		hmTestCd.put("china"				, "sweden");	// 중국
		hmTestCd.put("india"				, "sweden");	// 인도
		hmTestCd.put("indonesia"			, "sweden");	// 인도네시아
		hmTestCd.put("united arab emirates"	, "sweden");	// UAE
		hmTestCd.put("russia"				, "sweden");	// 러시아
		hmTestCd.put("brazil"				, "sweden");	// 브라질		
		*/
	}    

}
