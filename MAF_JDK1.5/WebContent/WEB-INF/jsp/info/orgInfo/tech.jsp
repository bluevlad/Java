<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<jsp:include page="_view.jsp"/>

<script language="javascript">
    function viewProfile(num) {
        var frm = getObject("tech");
        frm.profile_num.value=num;
        frm.cmd.value="tech";
        frm.submit();
    }
    function viewPhoto(img) {
        popupWindow('<mh:out value="${CPATH}"/>/web_src/viewImg.jsp?img='+img, "photo", 'no');
    }
</script>
<mf:form action="${control_action}" method="post" name="tech" id="tech">
<mf:input type="hidden" name="cmd" value="tech"/>
<mf:input type="hidden" name="profile_num" value="${profile_num}"/>
<mf:input type="hidden" name="org_cd" value="${item.org_cd}"/>

<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
    <tr>
		<td>#<mh:out value="${profile_num}"/></td>
		<td align="right">
			<input type="button" onclick="viewProfile('1')" value="#1">&nbsp;
			<input type="button" onclick="viewProfile('2')" value="#2">&nbsp;
			<input type="button" onclick="viewProfile('3')" value="#3">&nbsp;
			<input type="button" onclick="viewProfile('4')" value="#4">
		</td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
    <tr>
        <th>Number of Instructor(Full/Part Time)</th>
		<td class="center">Full Time</td>
		<td class="center"><mh:out value="${inst_info.full_num}" td="true"/></td>
		<td class="center">Part Time</td>
		<td class="center"><mh:out value="${inst_info.part_num}" td="true"/></td>
    </tr>
    <tr>
        <th>Language translation performing by yourself</th>
		<td class="center"><mh:out value="${inst_info.trans}" codeGroup="YESORNO" td="true"/></td>
		<td class="center"><mh:out value="${inst_info.lan1}" codeGroup="FAC.LAN" td="true"/></td>
		<td class="center"><mh:out value="${inst_info.lan2}" codeGroup="FAC.LAN" td="true"/></td>
		<td class="center"><mh:out value="${inst_info.lan3}" codeGroup="FAC.LAN" td="true"/></td>
    </tr>
    <tr>
        <th> Number of visist to dealer for training?</th>
		<td class="center"><mh:out value="${inst_info.train}" codeGroup="YESORNO" td="true"/></td>
		<td class="center"><mh:out value="${inst_info.cours}" td="true"/>&nbsp;Course/1Year</td>
		<td class="center"><mh:out value="${inst_info.times}" td="true"/>&nbsp;Times/Course</td>
		<td class="center">&nbsp;</td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view" id="itemInstructor">
    <tr>
        <th rowspan="2">Pic.</th>
        <th rowspan="2">Instructor name</th>
        <th rowspan="2">e-mail</th>
        <th rowspan="2">Year of joint in HMC</th>
        <th colspan="8">Year of experience with other company</th>
        <th rowspan="2">Subject in charge</th>
    </tr>
    <tr>
        <th>Benz</th>
        <th>BMW</th>
        <th>Volvo</th>
        <th>GM</th>
        <th>Ford</th>
        <th>Toyota</th>
        <th>Nissan</th>
        <th>other</th>
    </tr>
    <c:forEach var="item" items="${instructor}">
	<tr>
		<td class="center"><img src="<mh:out value="${CPATH}"/>/pds/org/<mh:out value="${item.inst_filename}"/>" height="40" onclick="javascript:viewPhoto('<mh:out value="${item.inst_filename}"/>');" onError="this.src='<mh:out value="${CPATH}"/>/images/global/no_photo.gif'"></td>
		<td class="center"><mh:out value="${item.inst_nm}" td="true"/></td>
		<td class="center"><mh:out value="${item.email}" td="true"/></td>
		<td class="center"><mh:out value="${item.join_date}" td="true"/></td>
		<td class="center"><mh:out value="${item.bz}" td="true"/></td>
		<td class="center"><mh:out value="${item.bm}" td="true"/></td>
		<td class="center"><mh:out value="${item.vo}" td="true"/></td>
		<td class="center"><mh:out value="${item.gm}" td="true"/></td>
		<td class="center"><mh:out value="${item.fo}" td="true"/></td>
		<td class="center"><mh:out value="${item.ty}" td="true"/></td>
		<td class="center"><mh:out value="${item.ni}" td="true"/></td>
		<td class="center"><mh:out value="${item.ot}" td="true"/></td>
		<td class="center"><mh:out value="${item.inst_subj}" codeGroup="FAC.SUBJECT" td="true"/></td>
	</tr>
	</c:forEach>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
    <col width="25%"/>
    <col width="25%"/>
    <col width="25%"/>
    <col width="25%"/>
    <tr>
        <th>Sales Volume</th>
		<td class="center"><mh:out value="${inst_info.sec}" codeGroup="FAC.SECTION" td="true"/></td>
        <th>Number of Technician</th>
		<td class="center"><mh:out value="${inst_info.tech}" codeGroup="FAC.TECHNICIAN" td="true"/></td>
	</tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
    <col width="10%"/>
    <col width="15%"/>
    <col width="12%"/>
    <col width="12%"/>
    <col width="15%"/>
    <col width="12%"/>
    <col width="12%"/>
    <tr>
        <th>Room</th>
        <th>Theory</th>
        <th colspan="2">Vehicle</th>
        <th>Prctical</th>
        <th colspan="2">Vehicle + Prctical</th>
    </tr>
    <c:forEach var="item" items="${room}"  varStatus="status">
	<tr>
		<td align="center">Room#<mh:out value="${status.count}"/></td>
		<td align="center">
			<mh:out value="${item.th_tr}" codeGroup="FAC.TRAINEE" td="true"/><br>
			<mh:out value="${item.th_sp}" codeGroup="FAC.SPACE" td="true"/>
		</td>
		<td align="center">
			<mh:out value="${item.ve_tr}" codeGroup="FAC.TRAINEE" td="true"/><br>
			<mh:out value="${item.ve_ca}" codeGroup="FAC.CAR" td="true"/>
		</td>
		<td align="center">
			<mh:out value="${item.ve_li}" codeGroup="FAC.LIFT" td="true"/><br>
			<mh:out value="${item.ve_sp}" codeGroup="FAC.SPACE" td="true"/>
		</td>
		<td align="center">
			<mh:out value="${item.pr_tr}" codeGroup="FAC.TRAINEE" td="true"/><br>
			<mh:out value="${item.pr_sp}" codeGroup="FAC.SPACE" td="true"/>
		</td>
		<td align="center">
			<mh:out value="${item.vp_tr}" codeGroup="FAC.TRAINEE" td="true"/><br>
			<mh:out value="${item.vp_ca}" codeGroup="FAC.CAR" td="true"/>
		</td>
		<td align="center">
			<mh:out value="${item.vp_li}" codeGroup="FAC.LIFT" td="true"/><br>
			<mh:out value="${item.vp_sp}" codeGroup="FAC.SPACE" td="true"/>
		</td>
	</tr>
	</c:forEach>
</table>
<br><br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view" >
    <col width="30%"/>
    <col width="*"/>

    <tbody>
    <c:forEach var="it" items="${categories}">
        <tr>
            <th style="text-align:left" <c:if test="${empty pfvalues[it.cat_id]}">colspan="2"</c:if>>
                <mh:out value="&nbsp;" repeate="${it.lvl*4}"/><c:if test="${it.lvl > 0}">-</c:if> <mh:out value="${it.cat_name }"/><br>
            </th>
            <c:if test="${!empty pfvalues[it.cat_id]}">
            <td>&nbsp;
                <c:if test="${empty pfvalues[it.cat_id] }">&nbsp;</c:if>
                <c:set var="index" value="0"/>
                <c:forEach var="pf" items="${pfvalues[it.cat_id]}" varStatus="status">
                	<c:if test="${pf.item_value > 0}">
	                    <mf:input name="itemids" type="hidden" value="${pf.item_id }"/>
	                    <c:set var="index" value="${index+1 }"/>
	                    <c:choose>
	                        <c:when test="${pf.item_type =='C' }">
	                            <span style="white-space: nowrap">:<label for='<c:out value="item_${pf.item_id }"/>'><mh:out value="${pf.item_name}" td="true"/>&nbsp;&nbsp;</label></span>
	                        </c:when>
	                        <c:when test="${pf.item_type =='N' }">
	                            <span style="white-space: nowrap"><mh:out value="${pf.item_name}" td="true"/>&nbsp;:&nbsp;
	                            <c:out value="${t }"/>&nbsp;
	                            </span>
	                            <br>
	                        </c:when>
	                    </c:choose>
	                    <c:if test="${(index % 3) == 0}"><!-- br --></c:if>
                    </c:if>
                </c:forEach>
            </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</mf:form>