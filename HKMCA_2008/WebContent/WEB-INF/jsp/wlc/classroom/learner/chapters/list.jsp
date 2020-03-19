<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="daymaxtime" value=""/>
<c:set var="types" value=""/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd"/>
<c:set var="types" value="0"/>
<c:set var="daymaxtime" value="0"/>
<c:set var="exm_condition" value="0"/>
<c:set var="lecexist" value="true"/>
<c:set var="lecexist" value="true"/>
<c:set var="daycheck" value="false"/>
<c:set var="timecheck" value="false"/>
<c:set var="linercheck" value="false"/>
<c:set var="threecheck" value="false"/>
<c:set var="real_check" value="false"/>
<c:set var="eval_check" value="false"/>
<c:set var="donelec" value="true"/>
<c:set var="mlink" value="true"/>

<c:set var="last_chpcode" value=""/>
<c:if test="${(!empty prginfo)}">
    <mh:lastIndexOf var="li" value="${prginfo}" needle="," />
    <mh:length var="ln" value="${prginfo}"/>
    <mh:set var="last_chpcode" value="${prginfo}" beginIndex="${li+1}" endIndex="${ln}"/>
    <mh:indexOf var="lastchp0" value="${last_chpcode}" needle="@" />
    <mh:length var="ln01" value="${last_chpcode}"/>    
    <c:if test="${(!empty last_chpcode) && (lastchp0 == (ln01-1))}">
    <mh:indexOf var="lastchp1" value="${last_chpcode}" needle="@" />
    <mh:set var="last_chpcode" value="${last_chpcode}" beginIndex="0" endIndex="${lastchp1}"/>
    </c:if>
</c:if>

<c:set var="types" value="${prgtype.prg_type}"/>
<c:if test="${prgtype.lec_wetm == '1'}">
    <c:set var="eval_check" value="true"/>
</c:if>
<c:if test="${prgtype.lec_weck == 'Y'}">
    <c:set var="real_check" value="true"/>
</c:if>
<c:set var="daymaxtime" value="${prgtype.lec_datm}"/>
<c:set var="exm_condition" value="${prgtype.exm_condition}"/>

<c:choose>
    <c:when test="${types==2 || types==3 || types==6 || types==7 || types==10 || types==11 || types==14 || types==15}">
        <c:set var="daycheck" value="true"/>
    </c:when>
    <c:otherwise>
        <c:set var="daycheck" value="false"/>
    </c:otherwise>
</c:choose>  
<c:choose>
    <c:when test="${types==8 || types==9 || types==10 || types==11 || types==12 || types==13 || types==14 || types==15}">
        <c:set var="linercheck" value="true"/>
    </c:when>
    <c:otherwise>
        <c:set var="linercheck" value="false"/>
    </c:otherwise>
</c:choose>  
<c:choose>
    <c:when test="${types==1 || types==3 || types==5 || types==7 || types==9 || types==11 || types==13 || types==15}">
        <c:set var="threecheck" value="true"/>
    </c:when>
    <c:otherwise>
        <c:set var="threecheck" value="false"/>
    </c:otherwise>
</c:choose>  
<c:choose>
    <c:when test="${types==4 || types==5 || types==6 || types==7 || types==12 || types==13 || types==14 || types==15}">
        <c:set var="timecheck" value="true"/>
    </c:when>
    <c:otherwise>
        <c:set var="timecheck" value="false"/>
    </c:otherwise>
</c:choose>  

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <th align="left">
	        <div class="searchContainer">
			<font color='red'><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis07.msg"/></font>
			<c:if test="${daycheck}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis06_1.msg"><mfmt:param value="${exm_condition}"/></mfmt:message></c:if>
			<c:if test="${linercheck}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis04.msg"/></c:if>
			<c:if test="${threecheck}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis01.msg"/></c:if>
			<c:if test="${timecheck}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis03.msg01"><mfmt:param value="${daymaxtime}"/></mfmt:message></c:if>
			<c:if test="${real_check}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis05.msg"/></c:if>
			<c:if test="${eval_check}"><br><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis06.msg"/></c:if>
	        </div>
        </th>
    </tr>
</table>

<c:if test="${(threecheck && (daycount >=4)) || (timecheck && (studingtime >= daymaxtime)) }">    
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <th align="left">
	        <div class="searchContainer">
	        <c:if test="${threecheck && (daycount >=4)}"><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis.msg05"/><br></c:if>
	        <c:if test="${timecheck && (studingtime >= daymaxtime)}">
	        <mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis.msg0601"/> 
	        <mh:out value="${daymaxtime}" td="true" /><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis.msg0602"/> 
	        <mh:out value="${studingtime}" td="true" /><mfmt:message bundle="common.message.lecture" key="noti.lecmst.prg.basis03.msg02"/>
	        </c:if>
	        </div>
        </th>
    </tr>
</table>
</c:if>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="lec_cd" value="${lec_cd}" />
<mf:input type="hidden" name="itm_id" value=""/>
<mf:input type="hidden" name="htmcode" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="50">
                <col width="*">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="100">
                <thead>
	            <tr>
					<th>#</th>
					<th><mf:header name="itm_title"/></th>
					<th><mfmt:message bundle="table.wlc_chp_prg" key="total_time"/></th>
					<th><mfmt:message bundle="table.wlc_chp_prg" key="prgsdat"/></th>
					<th><mfmt:message bundle="table.wlc_chp_prg" key="prgedat"/></th>
                <c:choose>  
				<c:when test="${'B' != cnt_type}">
				    <th><mfmt:message bundle="table.wlc_chp_prg" key="lesson_status"/></th>
				<c:if test="${ritem.jindo_page > 0}">
				    <th><mfmt:message bundle="table.wlc_chp_prg" key="page_prg"/></th>
				</c:if>  
				</c:when>
				<c:otherwise>
				    <th><mfmt:message bundle="table.wlc_chp_prg" key="lesson_status"/></th>
				</c:otherwise>
                </c:choose>    
                </tr>
                </thead>
				<tbody>
				<c:set var="chpcode" value="0"/>
				<c:set var="cenname" value=""/>
				<c:set var="chptime" value="0"/>
				<c:set var="review" value="false"/>
				<c:set var="tmp_check" value=""/>
				<c:set var="lineflag" value="init"/>
				<c:set var="realflag" value="init"/>
				<c:set var="evalflag" value="init"/>
				<c:set var="week" value="0"/>
	
                <c:forEach var="item" items="${navigator.list}" varStatus="status"><!-- 강의안 리스트 시작 -->
                <c:set var="prgtime0" value="0"/>
                <c:set var="prgtime1" value="0"/>
                <c:choose>
                    <c:when test="${!empty item.itm_title}">
                    <c:set var="cenname" value="${item.itm_title}"/>
                    </c:when>
                    <c:otherwise>
                    <c:set var="cenname" value="&nbsp;"/>
                    </c:otherwise>
                </c:choose>  
                <c:if test="${!empty item.htmcode}">
                <c:set var="chpcode" value="${item.htmcode}"/>
                </c:if>
                <c:if test="${!empty item.itm_maxtimeallowed}">
                <c:set var="chptime" value="${item.itm_maxtimeallowed}"/>
                </c:if>
	
                <c:catch var="error">
                <msql:query var="entries">
	                SELECT NVL(FN_GET_LEARNING_TOTAL_TIME(TOTAL_TIME),'0') AS totaltime, 
	                       NVL(SCORE_RAW,'0') AS score_raw 
	                    FROM WLC_CHP_PRG 
	                    WHERE USN=?
						AND LEC_CD=? 
						AND HTMCODE = ? 
					<msql:param value="${usn}"/>
					<msql:param value="${lec_cd}"/>
					<msql:param value="${item.htmcode}"/>
                </msql:query>
                </c:catch>
	   
                <c:catch var="error">
	                <c:forEach var="row" items="${entries.rows}">
						<c:set var="prgtime0" value="${row.totaltime}"/>
						<c:set var="prgtime1" value="${row.score_raw}"/>
	                </c:forEach>
	                <c:if test="${prgtime0 == 0}">
		                <msql:query var="entries">
				            SELECT NVL(FN_GET_LEARNING_TOTAL_TIME(PAGE_TIME),'0') AS TOTALTIME, 80 AS SCORE_RAW 
				               FROM WLC_PAGE_PRG 
				               WHERE USN=? 
				               AND LEC_CD=? 
				               AND HTMCODE=?
							<msql:param value="${usn}"/>
							<msql:param value="${lec_cd}"/>
							<msql:param value="${item.htmcode}"/>
						</msql:query>
		                <c:forEach var="row" items="${entries.rows}">
		                    <c:set var="prgtime0" value="${row.totaltime}"/>
		                    <c:set var="prgtime1" value="${row.score_raw}"/>
		                </c:forEach>
	                </c:if>
                </c:catch>
	    
				<c:set var="week" value="${(week+1)}"/>
				<c:set var="link" value=""/>
				<c:set var="review" value="false"/>
				<c:set var="tmp_check" value=""/>
				<c:set var="v0" value="${chpcode}${'@,'}"/>
				<c:choose>
					<c:when test="${real_check && eval_check}">
						<mh:indexOf var="pg1" value="${prginfo}" needle="${v0}" />
						<c:if test="${(!empty prginfo) && ((pg1) >=0 ) && (prgtime0 >= chptime) && (prgtime1 >= 80)}">
						<c:set var="tmp_check" value="<img src='${CPATH}/images/wlc/prg_red.gif' border='0'>"/>
						<c:set var="review" value="true"/>
						</c:if>
					</c:when>
					<c:when test="${real_check && !eval_check}">
						<mh:indexOf var="pg2" value="${prginfo}" needle="${v0}" />
						<c:if test="${(!empty prginfo) && ((pg2) >=0 )   && (prgtime0 >= chptime)}">
						<c:set var="tmp_check" value="<img src='${CPATH}/images/wlc/prg_red.gif' border='0'>"/>
						<c:set var="review" value="true"/>
						</c:if>
					</c:when>
					<c:when test="${!real_check && eval_check}">
						<mh:indexOf var="pg3" value="${prginfo}" needle="${v0}" />
						<c:if test="${(!empty prginfo) && ((pg3) >=0 )   && (prgtime1 >= 80)}">
						<c:set var="tmp_check" value="<img src='${CPATH}/images/wlc/prg_red.gif' border='0'>"/>
						<c:set var="review" value="true"/>
						</c:if>
					</c:when>
					<c:otherwise>
						<mh:indexOf var="pg4" value="${prginfo}" needle="${v0}" />
						<c:if test="${(!empty prginfo) && ((pg4) >=0 )}">
						<c:set var="tmp_check" value="<img src='${CPATH}/images/wlc/prg_red.gif' border='0'>"/>
						<c:set var="review" value="true"/>
						</c:if>
					</c:otherwise>
				</c:choose>
	
				<c:choose>
					<c:when test="${!review && threecheck && daycount >= 4}">
    					<c:set var="link" value="${tmp_check}${cenname}"/>
					</c:when>
					<c:otherwise>
    					<c:set var="link" value="${tmp_check}${'<a href=\"javascript:learnStudy(this.myform, &#039;'}${item.lec_cd}${'&#039;, &#039;'}${item.htmcode}${'&#039;, &#039;'}${item.cnt_width}${'&#039;, &#039;'}${item.cnt_height}${'&#039;);\">'}${cenname}${'</a>'}"/>
					</c:otherwise>
				</c:choose>
				
				<mh:length var="ln_chk" value="${tmp_check}"/>
				<c:choose>
					<c:when test="${timecheck && ln_chk < 1 && daymaxtime <= studingtime}">
	    				<c:set var="link" value="${tmp_check}${cenname}"/>
					</c:when>
					<c:otherwise>
	    				<c:set var="link" value="${tmp_check}${'<a href=\"javascript:learnStudy(this.myform, &#039;'}${item.lec_cd}${'&#039;, &#039;'}${item.htmcode}${'&#039;, &#039;'}${item.cnt_width}${'&#039;, &#039;'}${item.cnt_height}${'&#039;);\">'}${cenname}${'</a>'}"/>
					</c:otherwise>
				</c:choose>
	
                <c:set var="mlink" value="true"/>
	
				<c:if test="${linercheck && lineflag == 'end'}">
    				<c:set var="link" value="${cenname}"/>
				</c:if>
				
				<mh:length var="ln_prg" value="${prginfo}"/>
				<c:if test="${(linercheck && lineflag == 'ready') || (linercheck && (ln_prg <= 1 ))}">
    				<c:set var="lineflag" value="end"/>
				</c:if>
				
				<c:if test="${linercheck}">
					<c:if test="${(lineflag == 'init') && (last_chpcode == chpcode)}">
    					<c:set var="lineflag" value="ready"/>
					</c:if>
				</c:if>
					
				<c:if test="${real_check}">
					<c:if test="${real_check && realflag == 'end'}">
    					<c:set var="link" value="${cenname}"/>
					</c:if>
					<c:if test="${real_check && realflag != 'end'}">
						<c:if test="${prgtime0 < chptime}">
    						<c:set var="realflag" value="end"/>
						</c:if>
					</c:if>
				</c:if>
					
				<c:if test="${eval_check}">
					<c:if test="${eval_check && evalflag == 'end'}">
    					<c:set var="link" value="${cenname}"/>
					</c:if>
					<c:if test="${eval_check && evalflag != 'end'}">
						<c:if test="${prgtime1 < 80}">
    						<c:set var="evalflag" value="end"/>
						</c:if>
					</c:if>
				</c:if>
	
                <tr>
                    <td align="center"><mh:out value="${item.itm_sequence}" td="true" /></td>
					<td align="left">
    					<c:set var="v1" value="${chpcode}${'@,'}"/>    
    					<mh:indexOf var="pg10" value="${prginfo}" needle="${v1}" />
    					<c:if test="${((pg10) < 0 ) && (threecheck) && (daycount >= 4)}">
        					<c:set var="link" value="${cenname}"/>
    					</c:if>
						<mh:out value="${item.itmdepth}${link}" td="true" />
					</td>
					<td align="center"><mh:out value="${item.total_time}" td="true" /></td>
					<td align="center"><mh:out value="${item.prgsdat}" format="fulldate"/>&nbsp;</td>
					<td align="center"><mh:out value="${item.prgedat}" format="fulldate"/>&nbsp;</td>
					<td align="center"><mh:out value="${item.lesson_status}" codeGroup="LEC.LESSON_STATUS"/>&nbsp;</td>
                </tr>
                </c:forEach><!-- 강의안 리스트 종료 -->
                <c:if test="${!empty exmset}">
	            <tr>
	                <td align="center">EXAM</td>
	                <td colspan="10"><a href='<c:url value="/wlc.learner/doTest.do"/>'><mh:out value="${exmset.exmtitle}" /></a></td>
	            </tr>
                </c:if>
                </tbody>
	        </table>
			<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
			    <tr>
			        <th width="35%" class="view" align="center"><mfmt:message bundle="common" key="total.list"/><mfmt:message bundle="common" key="wlc_chp_prg.total_time"/></th>
			        <td width="15%" class="list" align="right" bgcolor="#FFFFFF"><mh:out value="${totaltime}"  default="0"/> <mfmt:message bundle="common" key="time.min"/></td>
			        <th width="35%" class="view" align="center"><mfmt:message bundle="common" key="wlc_chp_prg.limit_study_time"/></th>
			        <td width="15%" class="list" align="right" bgcolor="#FFFFFF"><mh:out value="${limit_study_time}" default="0"/> <mfmt:message bundle="common" key="time.min"/></td>
			    </tr>
			</table>
	        </div>
        </td>
    </tr>
</table>
</mf:form>
<br>
<script language="javascript">
<!--
// study
    function learnStudy(form, lec_cd, htmcode, w_width, w_height, launch_data)
    {
		var frm = getObject("myform");
        frm.htmcode.value = htmcode;
        var opt = "width="+w_width+",height="+w_height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=yes,copyhistory=no,top=50,left=50";
        var remote = window.open("about:blank","classroom_win", opt);
        remote.focus();
        if(remote != null) {
            frm.target = 'classroom_win';
            frm.action='<c:url value="/wlc.learner/progress.do"/>';
	        frm.cmd.value='study';
            frm.submit();
            return;
        }
    }
    window.setTimeout("pageReload()", 5*60*1000);
    function pageReload() {
        self.location.reload();
    }
//-->
</SCRIPT>