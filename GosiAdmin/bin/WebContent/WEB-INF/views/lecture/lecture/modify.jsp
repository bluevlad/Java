<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head><meta charset="utf-8" /></head>
<body>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHFORM" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="BRIDGE_LECCODE" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
<input type="hidden" id="LECCODE" name="LECCODE" value="${view[0].LECCODE}"/>
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value="${view[0].SUBJECT_TEACHER}"/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value="${view[0].SUBJECT_SJT_CD}"/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value="${view[0].SUBJECT_OPTION}"/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value="${view[0].SUBJECT_OFF_OPEN_YEAR}"/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value="${view[0].SUBJECT_OFF_OPEN_MONTH}"/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value="${view[0].SUBJECT_OFF_OPEN_DAY}"/>
<input type="hidden" id="BRIDGE_LEC" name="BRIDGE_LEC" value=""/>
<input type="hidden" id="UPDATE_FLAG" name="UPDATE_FLAG" value=""/>
<input type="hidden" id="ICON_GUBUN" name="ICON_GUBUN" value="${view[0].ICON_GUBUN}"/>

<c:set var="SOACHECKSET1" value="" />                   
<c:set var="SOACHECKSET2" value="" />
<c:set var="SOACHECKSET3" value="" />
<c:set var="SOACHECKSET4" value="" />
<c:set var="SOACHECKSET5" value="" />
<c:set var="SOACHECKSET6" value="" />
<c:if test="${!empty view[0].SUBJECT_OPTION}">
    <c:set var="SOA" value="${fn:split(view[0].SUBJECT_OPTION,',')}"/>
    <c:forEach items="${SOA}" var="item" varStatus="loop">
        <c:if test="${item eq '1'}"><c:set var="SOACHECKSET1" value="checked='checked'" /></c:if>
        <c:if test="${item eq 'AA'}"><c:set var="SOACHECKSET2" value="checked='checked'" /></c:if>
        <c:if test="${item eq '2'}"><c:set var="SOACHECKSET3" value="checked='checked'" /></c:if>
        <c:if test="${item eq '3'}"><c:set var="SOACHECKSET4" value="checked='checked'" /></c:if>
        <c:if test="${item eq 'BB'}"><c:set var="SOACHECKSET5" value="checked='checked'" /></c:if>
        <c:if test="${item eq '4'}"><c:set var="SOACHECKSET6" value="checked='checked'" /></c:if>
    </c:forEach>                    
</c:if>

    <h2>● 강의관리 > <strong>강의제작</strong></h2>
        
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="80">직종</th>
            <th width="80">과목</th>
            <th width="75">강의코드</th>
            <th width="55">강사명</th>
            <th>강의명</th>
            <th width="75">학습형태</th>
            <th width="100">VOD / PMP / V+P</th>
            <th width="70">시작일</th>
            <th width="110">동영상 / MP4 / PMP</th>
	        <th width="80">배수</th>
            <th width="80">등록일</th>
            <th width="60">개설여부</th>
        </tr>
        <c:forEach items="${viewlist}" var="item" varStatus="loop">
            <tr <c:if test="${item.LECCODE eq view[0].LECCODE}">style='background:#FFECEC;'</c:if>> 
                <td><a href="javascript:fn_DataView('${item.BRIDGE_LECCODE}','${item.LECCODE}');">${item.CATEGORY_NM}</a></td>
                <td>${item.SUBJECT_NM}</td>
                <td>${item.LECCODE}<br><span name="qq" id="qq">${item.BRIDGE_LECCODE}</span></td>
                <td>${item.SUBJECT_TEACHER_NM}</td>
                <td style="text-align:left;"><a href="javascript:fn_Modify('${item.BRIDGE_LECCODE}','${item.LECCODE}');">${item.SUBJECT_TITLE}</a></td>
                <td>${item.LEARNING_NM}</td>
                <td><a href="javascript:fn_PayListPop('Y','VOD','${item.LECCODE}');">${item.VODY}</a>/<a href="javascript:fn_PayListPop('N','VOD','${item.LECCODE}');">${item.VODN}</a> | <a href="javascript:fn_PayListPop('Y','PMP','${item.LECCODE}');">${item.PMPY}</a>/<a href="javascript:fn_PayListPop('N','PMP','${item.LECCODE}');">${item.PMPN}</a> | <a href="javascript:fn_PayListPop('Y','VOD+PMP','${item.LECCODE}');">${item.VODPMPY}</a>/<a href="javascript:fn_PayListPop('N','VOD+PMP','${item.LECCODE}');">${item.VODPMPN}</a></td>
                <td>${item.SUBJECT_OFF_OPEN_YEAR}-${item.SUBJECT_OFF_OPEN_MONTH}-${item.SUBJECT_OFF_OPEN_DAY}</td>
                <td>
                    <c:set var="OPTIONSTR" value="" />
                    <c:if test="${!empty item.SUBJECT_OPTION}">
                        <c:set var="SOA" value="${fn:split(item.SUBJECT_OPTION,',')}"/>
                        <c:forEach items="${SOA}" var="item2" varStatus="loop2">
                            <c:if test="${item2 eq 'AA'}">
                                <c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
                                <c:set var="OPTIONSTR" value="${OPTIONSTR}PMP" />
                            </c:if>
                            <c:if test="${item2 eq '2'}">
                                <c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
                                <c:set var="OPTIONSTR" value="${OPTIONSTR}MP4" />                           
                            </c:if>                     
                            <c:if test="${item2 eq '1'}">
                                <c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
                                <c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />                           
                            </c:if>
                            <c:if test="${item2 eq '4'}">
                                <c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
                                <c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />                           
                            </c:if>
                        </c:forEach>                    
                    </c:if>             
                    <a href="javascript:fn_MovieDataViewPop('${item.BRIDGE_LECCODE}','${item.LECCODE}','PMP');">${OPTIONSTR}</a>
                </td>               
		        <td class="txt03"><c:if test="${item.LEC_BAESU eq '00' or item.LEC_BAESU eq '' or empty item.LEC_BAESU}">배수제한없음</c:if><c:if test="${item.LEC_BAESU ne '00' and item.LEC_BAESU ne '' and not empty item.LEC_BAESU}">${item.LEC_BAESU/10}배수</c:if></td>
                <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
                <%-- <td class="txt03"><c:if test="${item.SUBJECT_ISUSE eq 'Y' }">활성</c:if><c:if test="${item.SUBJECT_ISUSE ne 'Y' }">비활성</c:if></td> --%>
                <td class="txt03"> <select onchange="javascript:Lec_ON_OFF('${item.LECCODE}','${item.SUBJECT_TITLE}');" id="ON_OFF_${item.LECCODE}"><option value="ON" id="ON_${item.LECCODE}"<c:if test="${item.SUBJECT_ISUSE eq 'Y' }">selected="selected"</c:if>>활성</option> <option value="OFF" id="OFF_${item.LECCODE}"<c:if test="${item.SUBJECT_ISUSE ne 'Y' }">selected="selected"</c:if>>비활성</option></select> </td>
               <%--  <input type="text" name="SUBJECT_ISUSE" id="SUBJECT_ISUSE" value="${item.SUBJECT_ISUSE}"></input> --%>
            </tr>
        </c:forEach>            
    </table>      
    <!-- //테이블-->
    
    <p>&nbsp; </p>
    
    <table class="table01">
        <tr>
            <th width="25%">직종</th>
            <th width="25%">학습형태</th>
            <th width="50%">과목(강사)</th>
        </tr>
        <tr>
            <td>
                <ul class="lecSerial">
                <c:forEach items="${kindlist}" var="item" varStatus="loop">
                    <c:set var="CHECKSET" value="" />                   
                    <c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>              
                    <li>${item.NAME} <span><input name="CATEGORY_CD" type="radio" value="${item.CODE}" ${CHECKSET}/></span></li>                    
                </c:forEach>
                </ul>          
            </td>
            <td>
                <ul class="lecSerial">
                <c:forEach items="${formlist}" var="item" varStatus="loop">
                    <c:set var="CHECKSET" value="" />
                    <c:if test="${item.CODE eq view[0].LEARNING_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>
                    <li>${item.NAME} <span><input name="LEARNING_CD" type="radio" value="${item.CODE}"  ${CHECKSET}/></span></li>
                </c:forEach>
                </ul>
            </td>
            <td>
                <ul class="lecSerial">
                <c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
                    <c:set var="CHECKSET" value="" />
                    <c:if test="${item.SUBJECT_CD eq view[0].SUBJECT_SJT_CD and item.USER_ID eq view[0].SUBJECT_TEACHER}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>             
                    <li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.PAYMENT}" ${CHECKSET}/></span></li>
                </c:forEach>
                </ul>
            </td>
        </tr>
    </table>
    
    <p>&nbsp; </p>
    
    <table class="table01">
        <tr>
            <th width="160">강의명</th>
            <td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="${view[0].SUBJECT_TITLE}" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
        </tr>
        <tr>
            <th>강의예정회차</th>
            <td>
            	총&nbsp;<input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" value="${view[0].LEC_SCHEDULE}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 회차&nbsp;
            	전체&nbsp;<input type="text" id="LEC_COUNT" name="LEC_COUNT" value="${view[0].LEC_COUNT}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 강
           	</td>
        </tr>
        <tr>
            <th>강의요약</th>
            <td><textarea id="SUBJECT_MEMO" name="SUBJECT_MEMO" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;">${view[0].SUBJECT_MEMO}</textarea></td>
        </tr>
        <tr>
            <th>강의개요</th>
            <td><textarea id="SUBJECT_DESC" name="SUBJECT_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;">${view[0].SUBJECT_DESC}</textarea></td>
        </tr>
        <tr>
            <th>키워드</th>
            <td><input type="text" id="SUBJECT_KEYWORD" name="SUBJECT_KEYWORD" value="${view[0].SUBJECT_KEYWORD}" size="60" maxlength="200" style="background:#FFECEC;"/></td>
        </tr>
        <tr>
            <th>기간</th>
            <td>
                <input type="text" id="SUBJECT_PERIOD" name="SUBJECT_PERIOD" value="${view[0].SUBJECT_PERIOD}" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>일 
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    시작일 <input type="text" id="SUBJECT_OFF_OPEN_CAL" name="SUBJECT_OFF_OPEN_CAL" value="${view[0].SUBJECT_OFF_OPEN_YEAR}${view[0].SUBJECT_OFF_OPEN_MONTH}${view[0].SUBJECT_OFF_OPEN_DAY}" style="background:#FFECEC;" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <th>원가</th>
            <td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="${view[0].SUBJECT_PRICE}" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
        </tr>
        <tr>
            <th>할인율</th>
            <td><input type="text" id="SUBJECT_DISCOUNT" name="SUBJECT_DISCOUNT" value="${view[0].SUBJECT_DISCOUNT}" size="10" maxlength="3" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
        </tr>
        <tr>
            <th>수강료</th>
            <td>
                동영상 : <input type="text" id="SUBJECT_MOVIE" name="SUBJECT_MOVIE" value="${view[0].SUBJECT_MOVIE}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 
                PMP : <input type="text" id="SUBJECT_PMP" name="SUBJECT_PMP" value="${view[0].SUBJECT_PMP}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
                동영상+PMP : <input type="text" id="SUBJECT_MOVIE_PMP" name="SUBJECT_MOVIE_PMP" value="${view[0].SUBJECT_MOVIE_PMP}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
                모바일 : <input type="text" id="SUBJECT_MOVIE_MP4" name="SUBJECT_MOVIE_MP4" value="${view[0].SUBJECT_MOVIE_MP4}" size="12" maxlength="7"/>
                동영상+모바일 : <input type="text" id="SUBJECT_MOVIE_VOD_MP4" name="SUBJECT_MOVIE_VOD_MP4" value="${view[0].SUBJECT_MOVIE_VOD_MP4}" size="12" maxlength="7"/>
            </td>
        </tr>
        <tr>
            <th>강사료지급률</th>
            <td><input type="text" id="SUBJECT_TEACHER_PAYMENT" name="SUBJECT_TEACHER_PAYMENT" value="${view[0].SUBJECT_TEACHER_PAYMENT}" size="10" maxlength="7" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
        </tr>       
        <tr>
            <th>주교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookJuArea');"></th>
            <td>
                <table class="tdTable" id="bookJuArea">
                    <tr>
                        <th width="15%">직급</th>
                        <th width="15%">학습형태</th>
                        <th>교재명</th>
                        <th width="10%">삭제</th>
                    </tr>
                    <c:set var="CHECKBOOKSET" value="N" />
                    <c:forEach items="${viewbooklist}" var="item" varStatus="loop">
                        <c:if test="${item.FLAG eq 'J'}">
                        <c:set var="CHECKBOOKSET" value="Y" />
                            <tr>
                                <td>${item.CATEGORY_NM}</td>
                                <td>${item.LEARNING_NM}</td>
                                <td><a href="javascript:fn_summary('${item.RSC_ID}')">${item.BOOK_NM}</a></td>
                                <td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${CHECKBOOKSET eq 'N'}">
                        <tr class="basic_space">
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </c:if>
                </table>
            </td>
        </tr>
        
        <tr>
            <th>부교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookBuArea');"></th>
            <td>
                <table class="tdTable" id="bookBuArea">
                    <tr>
                        <th width="15%">직급</th>
                        <th width="15%">학습형태</th>
                        <th>교재명</th>
                        <th width="10%">삭제</th>
                    </tr>
                    <c:set var="CHECKBOOKSET" value="N" />
                    <c:forEach items="${viewbooklist}" var="item" varStatus="loop">
                        <c:if test="${item.FLAG eq 'B'}">
                        <c:set var="CHECKBOOKSET" value="Y" />
                            <tr>
                                <td>${item.CATEGORY_NM}</td>
                                <td>${item.LEARNING_NM}</td>
                                <td><a href="javascript:fn_summary('${item.RSC_ID}')">${item.BOOK_NM}</a></td>
                                <td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${CHECKBOOKSET eq 'N'}">
                        <tr class="basic_space">
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </c:if>
                </table>
            </td>
        </tr>
        
        <tr>
            <th>수강생교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookSuArea');"></th>
            <td>
                <table class="tdTable" id="bookSuArea">
                    <tr>
                        <th width="15%">직급</th>
                        <th width="15%">학습형태</th>
                        <th>교재명</th>
                        <th width="10%">삭제</th>
                    </tr>
                    <c:set var="CHECKBOOKSET" value="N" />
                    <c:forEach items="${viewbooklist}" var="item" varStatus="loop">
                        <c:if test="${item.FLAG eq 'S'}">
                        <c:set var="CHECKBOOKSET" value="Y" />
                            <tr>
                                <td>${item.CATEGORY_NM}</td>
                                <td>${item.LEARNING_NM}</td>
                                <td><a href="javascript:fn_summary('${item.RSC_ID}')">${item.BOOK_NM}</a></td>
                                <td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${CHECKBOOKSET eq 'N'}">
                        <tr class="basic_space">
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </c:if>
                </table>
            </td>
        </tr>               
        
        <tr>
            <th><span class="txt05">동영상 기본경로(와이드)</span> </th>
            <td>
                <c:set var="SUBJECT_WIDE_DEFAULT_PATH" value="mms://willbes.gscdn.com/" />
                <c:if test="${!empty view[0].SUBJECT_WIDE_DEFAULT_PATH}"><c:set var="SUBJECT_WIDE_DEFAULT_PATH" value="${view[0].SUBJECT_WIDE_DEFAULT_PATH}" /></c:if>             
                <input type="text" id="SUBJECT_WIDE_DEFAULT_PATH" name="SUBJECT_WIDE_DEFAULT_PATH" value="${SUBJECT_WIDE_DEFAULT_PATH}" size="100" class="txt05" maxlength="400"/>
                <input type="hidden" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="">
            </td>
        </tr>
<%--         <tr>
            <th><span class="txt05">동영상 기본경로(500k)</span> </th>
            <td>
                <c:set var="SUBJECT_VOD_DEFAULT_PATH" value="mms://willbes.gscdn.com/" />
                <c:if test="${!empty view[0].SUBJECT_VOD_DEFAULT_PATH}"><c:set var="SUBJECT_VOD_DEFAULT_PATH" value="${view[0].SUBJECT_VOD_DEFAULT_PATH}" /></c:if>             
                <input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="${SUBJECT_VOD_DEFAULT_PATH}" size="100" class="txt05" maxlength="400"/>
            </td>
        </tr> --%>
        <tr>
            <th><span class="txt06">MP4 기본경로</span></th>
            <td>
                <c:set var="SUBJECT_MP4_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" />
                <c:if test="${!empty view[0].SUBJECT_MP4_DEFAULT_PATH}"><c:set var="SUBJECT_MP4_DEFAULT_PATH" value="${view[0].SUBJECT_MP4_DEFAULT_PATH}" /></c:if>         
                <input type="text" id="SUBJECT_MP4_DEFAULT_PATH" name="SUBJECT_MP4_DEFAULT_PATH" value="${SUBJECT_MP4_DEFAULT_PATH}" size="100" class="txt06" maxlength="400"/>
            </td>
        </tr>
        <tr>
            <th><span class="txt07">PMP 기본경로</span></th>
            <td>
                <c:set var="SUBJECT_PMP_DEFAULT_PATH" value="mms://1.224.163.230" />
                <c:if test="${!empty view[0].SUBJECT_PMP_DEFAULT_PATH}"><c:set var="SUBJECT_PMP_DEFAULT_PATH" value="${view[0].SUBJECT_PMP_DEFAULT_PATH}" /></c:if>             
                <input type="text" id="SUBJECT_PMP_DEFAULT_PATH" name="SUBJECT_PMP_DEFAULT_PATH" value="${SUBJECT_PMP_DEFAULT_PATH}" size="100" class="txt07" maxlength="400"/>
            </td>
        </tr>
		<tr>
          <th>강의기본배수</th>
			<td>
				<input type="radio" id="LEC_BAESU" name="LEC_BAESU" VALUE="00" <c:if test="${view[0].LEC_BAESU eq '00' }">checked="checked"</c:if>/> 배수제한없음		
				<input type="radio" id="LEC_BAESU" name="LEC_BAESU" VALUE="15" <c:if test="${view[0].LEC_BAESU eq '15' }">checked="checked"</c:if>/> 1.5배수
				<input type="radio" id="LEC_BAESU" name="LEC_BAESU" VALUE="20" <c:if test="${view[0].LEC_BAESU eq '20' }">checked="checked"</c:if>/> 2배수
				<input type="radio" id="LEC_BAESU" name="LEC_BAESU" VALUE="25" <c:if test="${view[0].LEC_BAESU eq '25' }">checked="checked"</c:if>/> 2.5배수
				<input type="radio" id="LEC_BAESU" name="LEC_BAESU" VALUE="30" <c:if test="${view[0].LEC_BAESU eq '30' }">checked="checked"</c:if>/> 3배수
          </td>
        </tr>
        <tr>
            <th>옵션</th>
            <td>
            <c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>          
                <%-- <input name="SUBJECT_OPTION_ARR" type="checkbox" value="1" ${SOACHECKSET1} /><span class="txt05">동영상개설(500k)</span> --%>
                <input name="SUBJECT_OPTION_ARR" type="checkbox" value="4" ${SOACHECKSET6} /><span class="txt05">동영상개설(와이드)</span>
                <input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA" ${SOACHECKSET2} /><span class="txt06">MP4</span>
                <input name="SUBJECT_OPTION_ARR" type="checkbox" value="2" ${SOACHECKSET3} /><span class="txt07">PMP개설</span>
                <input name="SUBJECT_OPTION_ARR" type="checkbox" value="3" ${SOACHECKSET4} /><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
                <input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB" ${SOACHECKSET5} /><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
            </td>
        </tr>
       
       <tr>
			<th>플레이어 선택</th>
			<td colspan="3">
				<input type="radio" id="SUBJECT_MONITORMODE" name="SUBJECT_MONITORMODE" VALUE="NOMAL" <c:if test="${view[0].SUBJECT_MONITORMODE eq 'NOMAL' }">checked="checked"</c:if>/>일반	
				<input type="radio" id="SUBJECT_MONITORMODE" name="SUBJECT_MONITORMODE" VALUE="WIDE" <c:if test="${view[0].SUBJECT_MONITORMODE eq 'WIDE' }">checked="checked"</c:if>/>와이드
			</td>
		</tr>
  <%--<tr>
            <th>개설여부</th>
            <td>
                <input type="radio" name="SUBJECT_ISUSE" value="Y" <c:if test="${view[0].SUBJECT_ISUSE eq 'Y' }">checked="checked"</c:if> /><span class="txt03">활성</span>
                <input type="radio" name="SUBJECT_ISUSE" value="N" <c:if test="${view[0].SUBJECT_ISUSE eq 'N' }">checked="checked"</c:if> /><span class="txt04">비활성</span>
            </td>
      </tr>--%>
      <tr>
            <th>'<span class="txt03">재수강</span>' 신청가능여부</th>
            <td>
                <input type="radio" name="RE_COURSE" value="Y" <c:if test="${view[0].RE_COURSE eq 'Y' }">checked="checked"</c:if> /><span class="txt03">가능</span>
                <input type="radio" name="RE_COURSE" value="N" <c:if test="${view[0].RE_COURSE eq 'N' }">checked="checked"</c:if> /><span class="txt04">불가능</span>
            </td>
      </tr>
      <tr>
            <th>장바구니할인에서 제외</th>
            <td>
                <input type="radio" name="SUBJECT_JANG" value="Y" <c:if test="${view[0].SUBJECT_JANG eq 'Y' }">checked="checked"</c:if> /><span class="txt03">할인안됨</span>
                <input type="radio" name="SUBJECT_JANG" value="N" <c:if test="${view[0].SUBJECT_JANG eq 'N' }">checked="checked"</c:if> /><span class="txt04">할인됨</span>
            </td>
      </tr>
      <tr>
            <th>보강비밀번호</th>
            <td><input type="text" id="SUBJECT_PASS" name="SUBJECT_PASS" value="${view[0].SUBJECT_PASS}" size="50" maxlength="50" style="IME-MODE:disabled;"/></td>
      </tr>
      <tr>
            <th><span class="txt03">신규강좌여부</th>
            <td>
                <input type="radio" name="LEC_GUBUN" value="Y" <c:if test="${view[0].LEC_GUBUN eq 'Y' }">checked="checked"</c:if> /><span class="txt03">예</span>
                <input type="radio" name="LEC_GUBUN" value="N" <c:if test="${view[0].LEC_GUBUN eq 'N' }">checked="checked"</c:if> /><span class="txt04">아니오</span>
            </td>
      </tr>     
      <tr>
            <th><span class="txt03">추천강좌여부</th>
            <td>
                <input type="radio" name="REC_GUBUN" value="Y" <c:if test="${view[0].REC_GUBUN eq 'Y' }">checked="checked"</c:if> /><span class="txt03">예</span>
                <input type="radio" name="REC_GUBUN" value="N" <c:if test="${view[0].REC_GUBUN eq 'N' }">checked="checked"</c:if> /><span class="txt04">아니오</span>
            </td>
      </tr>     
      <tr>
        <th>노출아이콘설정</th>
        <td>
        <c:forEach items="${ICON_GUBUNs}" var="data" varStatus="status" >
          <c:set var="vChecked">
            <c:choose>
              <c:when test="${fn:contains(view[0].ICON_GUBUN, data.CODE_VAL)}">checked="checked"</c:when>
              <c:otherwise></c:otherwise>
            </c:choose>
          </c:set>
          <input type="checkbox" name="ICON_GUBUN_ARR" class="i_check" value="${data.CODE_VAL}" <c:out value='${vChecked}'/> ><span class="txt0${status.index+3}">${data.CODE_NM}</span>
        </c:forEach>
        <%--  <input name="ICON_GUBUN_ARR" type="checkbox" value="B" ${ICONCHECKSET1}/><span class="txt05">BEST</span>
          <input name="ICON_GUBUN_ARR" type="checkbox" value="N" ${ICONCHECKSET2}/><span class="txt06">NEW</span>
          <input name="ICON_GUBUN_ARR" type="checkbox" value="H" ${ICONCHECKSET3}/><span class="txt07">HIT</span> --%>
        </td>
      </tr>
      <tr>
            <th><span class="txt03">동영상등록여부</th>
            <td>
                <input type="radio" name="MOV_ING" value="C" <c:if test="${view[0].MOV_ING eq 'C' }">checked="checked"</c:if> /><span class="txt03">업데이트 예정</span>
                <input type="radio" name="MOV_ING" value="I" <c:if test="${view[0].MOV_ING eq 'I' }">checked="checked"</c:if> /><span class="txt03">업데이트중</span>
                <input type="radio" name="MOV_ING" value="E" <c:if test="${view[0].MOV_ING eq 'E' }">checked="checked"</c:if> /><span class="txt03">업데이트 완료</span>
            </td>
      </tr>
      <tr>
            <th><span class="txt03">사은품(강좌)</th>
            <td>
                <table class="table01">
                    <tr>
                        <th width="25%">
                        단과명 : <input type="text" name="GIFT_NAME" id="GIFT_NAME" value="${view[0].GIFT_NAME}" size="150" readonly="readonly"> <input type="text" name="GIFT_LECCODE" id="GIFT_LECCODE" value="${view[0].GIFT_LECCODE}">
                        <input type="button" onclick="div_lecture_reset()" value="초기화" />
                        <BR>
                        <label for="SEARCHKIND"></label>
                            <select name="SEARCHKIND1" id="SEARCHKIND1">
                                <option value="">--직종검색--</option>
                                <c:forEach items="${kindlist}" var="item" varStatus="loop">
                                    <option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>                   
                                </c:forEach>
                            </select>
                            <label for="SEARCHFORM"></label>
                            <select name="SEARCHFORM1" id="SEARCHFORM1">
                                <option value="">--학습형태검색--</option>
                                <c:forEach items="${formlist}" var="item" varStatus="loop">
                                    <option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>                   
                                </c:forEach>
                          </select>
                          
                            <select name="SEARCHTYPE1" id="SEARCHTYPE1">
                                <option value="">--전체검색--</option>
                                <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>과목</option>
                                <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
                                <option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>강사명</option>
                                <option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
                            </select> 
                            <label for="SEARCHTEXT"></label>
                            <input type="text" id="SEARCHGIFT" name="SEARCHGIFT" value="" size="40" title="검색어" onkeypress="javascript:fn_Enter1();">
                          <input type="button" onclick="div_lecture_search()" value="검색" />
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <ul class="lecSerial">
                            
                                <div id="div_lecture_search">
                                </div>
                            </ul>          
                        </td>
                    </tr>
                    <tr>
                      <td class="tdCenter"><input name="" type="button" value="과목추가"></td>
                    </tr>
                </table>
            </td>
      </tr>
      
      <tr>
            <th><span class="txt03">사은품(쿠폰)</th>
            <td>
                <table class="table01">
                    <tr>
                        <th width="25%">
                        쿠폰명 : <input type="text" name="COUPON_NAME" id="COUPON_NAME" value="${view[0].COUPON_NAME}" size="150" readonly="readonly"> <input type="hidden" name="GIFT_COUPON_CCODE" id="GIFT_COUPON_CCODE" value="${view[0].GIFT_COUPON_CCODE}">
                        <input type="button" onclick="div_coupon_reset()" value="초기화" />
                        <BR>
                        <select name="GIFT_SEARCHTYPE" id="GIFT_SEARCHTYPE">
                                <option value="">--전체검색--</option>
                                <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>쿠폰코드</option>
                                <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>쿠폰명</option>
                            </select> 
                            <input type="text" id="SEARCHGIFTTEXT" name="SEARCHGIFTTEXT" value="" size="40" title="검색어" onkeypress="javascript:fn_Enter2();">
                          <input type="button" onclick="div_coupon_search()" value="검색" />
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <ul class="lecSerial">
                            
                                <div id="div_coupon_search">
                                </div>
                            </ul>          
                        </td>
                    </tr>
                </table>
            </td>
      </tr>
      <tr>
          <th><span class="txt03">사은품(모의고사)</th>
          <td>
            <table class="table01">
          <tr>
                <th width="25%">
                쿠폰명 : <input type="text" name="MO_COUPON_NAME" id="MO_COUPON_NAME" value="${view[0].MO_COUPON_NAME}" size="150" readonly="readonly"> <input type="hidden" name="MO_COUPON_CCODE" id="MO_COUPON_CCODE" value="${view[0].MO_COUPON_CCODE}">
                <input type="button" onclick="div_mo_reset()" value="초기화" />
                <BR>
                <select name="MO_SEARCHTYPE" id="MO_SEARCHTYPE">
                <option value="">--전체검색--</option>
                <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>쿠폰코드</option>
                <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>쿠폰명</option>
              </select> 
              <input type="text" id="SEARCHMOTEXT" name="SEARCHMOTEXT" value="" size="40" title="검색어" onkeypress="javascript:fn_Enter3();">
                  <input type="button" onclick="div_mo_search()" value="검색" />
                </th>
          </tr>
              <tr>
            <td>
              <ul class="lecSerial">
              
                <div id="div_mo_search">
                </div>
                    </ul>          
            </td>
              </tr>
          </table>
            </td>
      </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit('One');">수정</a></li>
        <li><a href="javascript:fn_Submit('All');">전체수정</a></li>
        <li><a href="javascript:fn_Delete();">삭제</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
        <li><a href="javascript:fn_Copy();">복제</a></li>
        <li><a href="javascript:fn_NewCopy();">신규복제</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 
<script type="text/javascript">

$(document).ready(function(){   
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDateFicker2("SUBJECT_OFF_OPEN_CAL");    
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');

    // 과목선택시 값 SETTING
    $("input[name='SUBJECT_INFO_ARR']").click(function(){
        $("#SUBJECT_SJT_CD").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[0]);
        $("#SUBJECT_TEACHER").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[1]);
        $("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
    });
    
    // 교재삭제
    $(document).on("click","input[name='BTN_BOOKDEL']",function(){
        $(this).parent().parent().remove();
    });
    
    div_lecture_search();
    div_coupon_search();
    div_mo_search();
});

//요약보기
function fn_summary(rscVal) {
	var url = '<c:url value="/lecture/bookPopup.html?rscVal=' + rscVal + '"/>' ;
	window.open(url,'_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=350,height=597');
}

// VOD,PMP,동영상 유/무 팝업
function fn_PayListPop(gubn, type, leccode){
    window.open('<c:url value="/lecture/payList.pop"/>?SEARCHPAYYN=' + gubn + '&SEARCHPAYTYPE=' + type + '&LECCODE=' + leccode , '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// VOD,PMP,동영상 클릭시 팝업
function fn_MovieDataViewPop(val1, val2, val3){
    //window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1 + '&SEARCHTYPE=' + val3, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
    window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
}

// 교재팝업
function fn_BookPop(type){
    window.open('<c:url value="/lecture/bookList.pop"/>?ADDBOOKAREA=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1340,height=800');
}

// 수정폼
function fn_Modify(val1, val2){
    $("#BRIDGE_LECCODE").val(val1);
    $("#LECCODE").val(val2);
    $("#frm").attr("action", "<c:url value='/lecture/modify.do' />");
    $("#frm").submit();
}

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/lecture/list.do' />");
    $("#frm").submit();
}

// 숫자만입력
function fn_OnlyNumber(obj) {
    for (var i = 0; i < obj.value.length ; i++){
        chr = obj.value.substr(i,1);  
        chr = escape(chr);
        key_eg = chr.charAt(1);
        if (key_eg == "u"){
            key_num = chr.substr(i,(chr.length-1));   
            if((key_num < "AC00") || (key_num > "D7A3")) { 
                event.returnValue = false;
            }    
        }
    }
    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
    }else{
        event.returnValue = false;
    }
}

// 할인율 적용
function fn_DcNum() {
        var dc = parseFloat($("#SUBJECT_DISCOUNT").val()) / 100;
        $("#SUBJECT_MOVIE").val($("#SUBJECT_PRICE").val() - $("#SUBJECT_PRICE").val() * dc);
        $("#SUBJECT_PMP").val($("#SUBJECT_PRICE").val() - $("#SUBJECT_PRICE").val() * dc);
        $("#SUBJECT_MOVIE_PMP").val( Math.floor((parseInt($("#SUBJECT_MOVIE").val()) + parseInt($("#SUBJECT_PMP").val())) * 0.8));
        
        if($("#SUBJECT_MOVIE").val() == "NaN"){
            $("#SUBJECT_MOVIE").val(0);
        }
        if($("#SUBJECT_PMP").val() == "NaN"){
            $("#SUBJECT_PMP").val(0);
        }       
}

//강의명 부터 강사료지급률까지 모든직종 강의 일괄수정
/* function fn_Submit2(){
    
    var BRIDGE_LEC = $("#BRIDGE_LECCODE").val();

    if($.trim($("#SUBJECT_TITLE").val()) == ""){
        alert("강의명을 입력하세요");
        $("#SUBJECT_TITLE").focus();
        return;
    }
    if($.trim($("#LEC_SCHEDULE").val()) == ""){
        alert("강의예정회차를 입력하세요");
        $("#LEC_SCHEDULE").focus();
        return;
    }
    if($.trim($("#SUBJECT_MEMO").val()) == ""){
        alert("강의요약을 입력하세요");
        $("#SUBJECT_MEMO").focus();
        return;
    }   
    if($.trim($("#SUBJECT_DESC").val()) == ""){
        alert("강의개요를 입력하세요");
        $("#SUBJECT_DESC").focus();
        return;
    }
    if($.trim($("#SUBJECT_KEYWORD").val()) == ""){
        alert("키워드를 입력하세요");
        $("#SUBJECT_KEYWORD").focus();
        return;
    }
    if($.trim($("#SUBJECT_PERIOD").val()) == ""){
        alert("기간을 입력하세요");
        $("#SUBJECT_PERIOD").focus();
        return;
    }   
    if($.trim($("#SUBJECT_OFF_OPEN_CAL").val()) == ""){
        alert("시작일을 입력하세요");
        $("#SUBJECT_OFF_OPEN_CAL").focus();
        return;
    }   
    if($.trim($("#SUBJECT_PRICE").val()) == ""){
        alert("원가를 입력하세요");
        $("#SUBJECT_PRICE").focus();
        return;
    }   
    if($.trim($("#SUBJECT_DISCOUNT").val()) == ""){
        alert("할인율을 입력하세요");
        $("#SUBJECT_DISCOUNT").focus();
        return;
    }
    if(confirm("모든 직렬 강의를 수정하시겠습니까?")){
        $("#BRIDGE_LEC").val(BRIDGE_LEC);
        $("#frm").attr("action","<c:url value='/lecture/update2.do' />");
        $("#frm").submit();
    }
} */

// 수정
function fn_Submit(flag){
    var FLAG = flag;
    if(1 > $("input[name='CATEGORY_CD']:checked").size()){
        alert("직종 항목을 선택하세요");
        $("input[name='CATEGORY_CD']")[0].focus();
        return;
    }
    if(1 > $("input[name='LEARNING_CD']:checked").size()){
        alert("학습형태 항목을 선택하세요");
        $("input[name='LEARNING_CD']")[0].focus();
        return;
    }   
    if(1 != $("input[name='SUBJECT_INFO_ARR']:checked").size()){
        alert("과목 항목을 한개 선택하세요");
        $("input[name='SUBJECT_INFO_ARR']")[0].focus();
        return;
    }
    if($.trim($("#SUBJECT_TITLE").val()) == ""){
        alert("강의명을 입력하세요");
        $("#SUBJECT_TITLE").focus();
        return;
    }
    if($.trim($("#LEC_SCHEDULE").val()) == ""){
        alert("강의예정회차를 입력하세요");
        $("#LEC_SCHEDULE").focus();
        return;
    }
    if($.trim($("#LEC_COUNT").val()) == ""){
        alert("강의수를 입력하세요");
        $("#LEC_COUNT").focus();
        return;
    }
    if($.trim($("#SUBJECT_MEMO").val()) == ""){
        alert("강의요약을 입력하세요");
        $("#SUBJECT_MEMO").focus();
        return;
    }   
    if($.trim($("#SUBJECT_DESC").val()) == ""){
        alert("강의개요를 입력하세요");
        $("#SUBJECT_DESC").focus();
        return;
    }
    if($.trim($("#SUBJECT_KEYWORD").val()) == ""){
        alert("키워드를 입력하세요");
        $("#SUBJECT_KEYWORD").focus();
        return;
    }
    if($.trim($("#SUBJECT_PERIOD").val()) == ""){
        alert("기간을 입력하세요");
        $("#SUBJECT_PERIOD").focus();
        return;
    }   
    if($.trim($("#SUBJECT_OFF_OPEN_CAL").val()) == ""){
        alert("시작일을 입력하세요");
        $("#SUBJECT_OFF_OPEN_CAL").focus();
        return;
    }   
    if($.trim($("#SUBJECT_PRICE").val()) == ""){
        alert("원가를 입력하세요");
        $("#SUBJECT_PRICE").focus();
        return;
    }   
    if($.trim($("#SUBJECT_DISCOUNT").val()) == ""){
        alert("할인율을 입력하세요");
        $("#SUBJECT_DISCOUNT").focus();
        return;
    }       
    if(FLAG == 'All'){
        var MSG = "모든 직렬의 강의를 수정하시겠습니까?";
        var BRIDGE_LEC = $("#BRIDGE_LECCODE").val();
        $("#BRIDGE_LEC").val(BRIDGE_LEC);
        $("#UPDATE_FLAG").val('ALL');
    }else{
        var MSG = "강의를 수정하시겠습니까?";
        var BRIDGE_LEC = $("#BRIDGE_LECCODE").val();
        $("#BRIDGE_LEC").val(BRIDGE_LEC);
    }   
        if(confirm(MSG)){
        $("#bookJuArea").find("input[name='RSC_ID']").each(function(idx,el){
            $(this).attr("name","JU_RSC_ID");
        });     
        $("#bookBuArea").find("input[name='RSC_ID']").each(function(idx,el){
            $(this).attr("name","BU_RSC_ID");
        });
        $("#bookSuArea").find("input[name='RSC_ID']").each(function(idx,el){
            $(this).attr("name","SU_RSC_ID");
        });
        var subjectoption = "";
        $("input[name='SUBJECT_OPTION_ARR']:checked").each(function(idx,el){
            if(subjectoption!="")   
                subjectoption += ",";
            subjectoption += $(this).val();
        });     
        $("#SUBJECT_OPTION").val(subjectoption);
        if($.trim($("#SUBJECT_OFF_OPEN_CAL").val()) != ""){
            $("#SUBJECT_OFF_OPEN_YEAR").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(0,4));
            $("#SUBJECT_OFF_OPEN_MONTH").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(4,2));
            $("#SUBJECT_OFF_OPEN_DAY").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(6,2));
        }
        var icongubunStr = "";
        $("input[name='ICON_GUBUN_ARR']:checked").each(function(idx,el){
          if(icongubunStr!="") icongubunStr += ",";
          icongubunStr += $(this).val();
        });   
        $("#ICON_GUBUN").val(icongubunStr);
        
        $("#frm").attr("action","<c:url value='/lecture/update.do' />");
        $("#frm").submit();
    }
}

// 삭제
function fn_Delete(){
    if("${lectureOrderCount}"!="0"){
        if(confirm("이 강의를 주문한 내역이 존재합니다. 정말 삭제하시겠습니까?")){
            $("#frm").attr("action","<c:url value='/lecture/delete.do' />");
            $("#frm").submit();
        }       
    }else{
        if(confirm("정말 삭제하시겠습니까?")){
            $("#frm").attr("action","<c:url value='/lecture/delete.do' />");
            $("#frm").submit();
        }
    }
}

// 복제
function fn_Copy(){
    $("#frm").attr("action","<c:url value='/lecture/copy.do' />");
    $("#frm").submit();
}

// 신규복제
function fn_NewCopy(){
    $("#frm").attr("action","<c:url value='/lecture/newCopy.do' />");
    $("#frm").submit();
}

//직종클릭시 팝업
function fn_DataView(val1, val2){
    window.open('<c:url value="/lecture/dataViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

function Lec_ON_OFF(Rcode,LecNM){
    var id = "#ON_OFF_"+ Rcode;
    //var dataString = $("#frm").serialize(); 
    var flag = $(id).val();
    var flag2 = "view";
    
    if(confirm("개설 여부를 변경 하시겠습니까?")){
        $.ajax({
            type: "GET",
            url: "<c:url value='/lecture/Modify_Lecture_On_Off.do' />"+"?Rcode="+Rcode + "&flag=" + flag + "&flag2=" + flag2,
            dataType: "text",
            success: function(result){
                var state = ""
                if(result == 'ON'){
                    /* $("#SUBJECT_ISUSE").val('Y'); */
                    state = '활성';
                }else{
                    /* $("#SUBJECT_ISUSE").val('N'); */
                    state = '비활성'
                }
                alert("'" + LecNM + "'" + " 강의가 " + state + " 상태로 변경 되었습니다.");
                return;
            },error: function(){
                alert("전송 실패");
                return;
            }
        }); 
    }else{

        if(flag == 'ON'){
            document.getElementById('OFF_'+Rcode).selected = "selected";
        }else{
            document.getElementById('ON_'+Rcode).selected = "selected";
        } 
        return;
    }
}

//데이타 초기화
function div_lecture_reset() {
   $('#GIFT_NAME').val("");
   $('#GIFT_LECCODE').val("");
   $("input:radio[name='LECCODE_INFO_ARR']").removeAttr("checked");
}
function div_coupon_reset() {
   $('#COUPON_NAME').val("");
   $('#GIFT_COUPON_CCODE').val("");
   $("input:radio[name='COUPON_INFO_ARR']").removeAttr("checked");
   
}
function div_mo_reset() {
   $('#MO_COUPON_NAME').val("");
   $('#MO_COUPON_CCODE').val("");
   $("input:radio[name='MO_COUPON_INFO_ARR']").removeAttr("checked");
   
}

//엔터키 검색
function fn_Enter1(){
	$("#SEARCHGIFT").keyup(function(e)  {
		if(e.keyCode == 13) 
			div_lecture_search();
	});
}
//엔터키 검색
function fn_Enter2(){
	$("#SEARCHGIFTTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			div_coupon_search();
	});
}
//엔터키 검색
function fn_Enter3(){
	$("#SEARCHMOTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			div_mo_search();
	});
}

function div_lecture_search(){
    
    var gift_leccode_arr =  "";
    var gift_name_arr =  "";
    
    var gift_leccode = "";
    var gift_name = "";

    $('#SEARCHKIND').val($('#SEARCHKIND1').val())
    $('#SEARCHFORM').val($('#SEARCHFORM1').val())
    $('#SEARCHTYPE').val( $('#SEARCHTYPE1').val())
    
    var param =  "&SEARCHKIND=" + $('#SEARCHKIND').val()+"&SEARCHFORM=" + $('#SEARCHFORM').val()+"&SEARCHTYPE=" + $('#SEARCHTYPE').val()+"&SEARCHTEXT=" + encodeURI($('#SEARCHGIFT').val());

    $.ajax({
            type : "POST"
            ,url : '<c:url value="/lecture/listpop.pop"/>'
            ,data :  param
            ,success : function (data){
                $('#div_lecture_search').html(data);
                
                // 과목선택시 값 SETTING
                $("input[name='LECCODE_INFO_ARR']").click(function(){
                    
                    if($("#GIFT_LECCODE").val() != ""){
                        gift_leccode = $("#GIFT_LECCODE").val();
                    }
                    if($("#GIFT_NAME").val() != ""){
                        gift_name = $("#GIFT_NAME").val();
                    }
                    if(gift_name != ""){
                        gift_leccode_arr = ""+gift_leccode+", ";
                        gift_name_arr = ""+gift_name+", ";
                    }
                    
/*                     alert("gift_leccode="+gift_leccode);
                    alert("gift_name="+gift_name);
                    alert("gift_leccode_arr="+gift_leccode_arr);
                    alert("gift_name_arr="+gift_name_arr); */
                    
                    $("#GIFT_LECCODE").val(gift_leccode_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[0]);
                    $("#GIFT_NAME").val(gift_name_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[1]);
                    //$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
                });
            }
        }); 
}
/*
 * 사은품 나갈때 쿠폰 리스트 가져 오기 위함
 */
function div_coupon_search(){
    
    var gift_couponcd_arr =  "";
    var gift_couponname_arr =  "";
    
    var gift_couponcd = "";
    var gift_couponname = "";
    
    var param =  "&GIFT_SEARCHTYPE=" + $('#GIFT_SEARCHTYPE').val()+"&SEARCHGIFTTEXT=" + encodeURI($('#SEARCHGIFTTEXT').val());

    $.ajax({
            type : "POST"
            ,url : "<c:url value="/lecture/couponpop.pop"/>"
            ,data :  param
            ,success : function (data){
                $('#div_coupon_search').html(data);
                
                // 과목선택시 값 SETTING
                $("input[name='COUPON_INFO_ARR']").click(function(){
                    
                    if($("#GIFT_COUPON_CCODE").val() != ""){
                        gift_couponcd = $("#GIFT_COUPON_CCODE").val();
                    }
                    if($("#COUPON_NAME").val() != ""){
                        gift_couponname = $("#COUPON_NAME").val();
                    }
                    if(gift_couponname != ""){
                    	gift_couponcd_arr = ""+gift_couponcd+", ";
                        gift_couponname_arr = ""+gift_couponname+", ";
                    }
                    //alert("gift_couponcd_arr="+gift_couponcd_arr);
                    
                    $("#GIFT_COUPON_CCODE").val(gift_couponcd_arr + $("input[name='COUPON_INFO_ARR']:checked").val().split("#")[0]);
                    $("#COUPON_NAME").val(gift_couponname_arr + $("input[name='COUPON_INFO_ARR']:checked").val().split("#")[1]);
                    //$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
                });
            }
        }); 
}

/*
 * 사은품 나갈때 쿠폰 리스트 가져 오기 위함
 */
function div_mo_search(){
  
  var mo_couponcd_arr =  "";
  var mo_couponname_arr =  "";
  
  var mo_couponcd = "";
  var mo_couponname = "";
  
  var param =  "&MO_SEARCHTYPE=" + $('#MO_SEARCHTYPE').val()+"&SEARCHMOTEXT=" + encodeURI($('#SEARCHMOTEXT').val());

  $.ajax({
        type : "POST"
		,url : "<c:url value="/lecture/mo_couponpop.pop"/>"
        ,data :  param
        ,success : function (data){
          $('#div_mo_search').html(data);
          
          
          // 과목선택시 값 SETTING
          $("input[name='MO_COUPON_INFO_ARR']").click(function(){
            
            if($("#MO_COUPON_CCODE").val() != ""){
              mo_couponcd = $("#MO_COUPON_CCODE").val();
            }
            if($("#COUPON_NAME").val() != ""){
              mo_couponname = $("#MO_COUPON_NAME").val();
            }
            if(mo_couponname != ""){
            	mo_couponcd_arr = ""+mo_couponcd+", ";
              mo_couponname_arr = ""+mo_couponname+", ";
            }
            //alert("mo_couponcd_arr="+mo_couponcd_arr);
            
            $("#MO_COUPON_CCODE").val(mo_couponcd_arr + $("input[name='MO_COUPON_INFO_ARR']:checked").val().split("#")[0]);
            $("#MO_COUPON_NAME").val(mo_couponname_arr + $("input[name='MO_COUPON_INFO_ARR']:checked").val().split("#")[1]);
            //$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
          });
        }
    }); 
}
</script>
</body>
</html>