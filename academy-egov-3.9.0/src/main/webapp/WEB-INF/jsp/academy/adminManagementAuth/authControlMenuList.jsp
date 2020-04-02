<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
    function fn_authControlList(){
        $("#frm").attr("action", "<c:url value='/adminManagementAuth/authControlList.do' />");
        $("#frm").submit();
    }

    function addMenu(){
        var menuList = "";
        $("input[name=menuCheck]:checked").each(function() {
            menuList += $(this).val() + ",";
        });
        if(menuList==""){
            alert("메뉴를 지정해주세요");
            return false;
        }

        var last = menuList.lastIndexOf(',');
        menuList = menuList.substr(0,last)
        $("#insertMenuId").val(menuList);

        $("#frm").attr("action", "<c:url value='/adminManagementAuth/authMenuInsertProcess.do' />");
        $("#frm").submit();
    }

    function fn_checkControl(target , UP_P_MENUID_RECUR , DOWN_P_MENUID_RECUR){
        var upMenuArr = UP_P_MENUID_RECUR.split(",");
        var downMenuArr = DOWN_P_MENUID_RECUR.split(",");
        var checked = $(target).attr("checked");

        if(!checked){
            /* if(upMenuArr.length  > 1){
                for ( var i = 0; i < upMenuArr.length; i++) {
                    $("#"+upMenuArr[i]+"").attr("checked" , false);
                }
            } */
             if(downMenuArr.length  > 1){
                for ( var j = 0; j < downMenuArr.length; j++) {
                    $("#"+downMenuArr[j]+"").attr("checked" , false);
                }
            }
        }
        else{
            if(upMenuArr.length  > 1){
                for ( var i = 0; i < upMenuArr.length; i++) {
                    $("#"+upMenuArr[i]+"").attr("checked" , true);
                }
            }
             if(downMenuArr.length  > 1){
                for ( var j = 0; j < downMenuArr.length; j++) {
                    $("#"+downMenuArr[j]+"").attr("checked" , true);
                }
            }
        }
    }

</script>
</head>
<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="MENU_ONOFF" name="MENU_ONOFF" value="${params.MENU_ONOFF}">
<input type="hidden" id="DETAIL_SITE_ID" name="DETAIL_SITE_ID" value="${params.DETAIL_SITE_ID}" />
<input type="hidden" id="insertMenuId" name="insertMenuId" value="" />

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

<h2>● 운영자 관리 > <strong>권한관리</strong></h2>
        <table class="table01">
            <%-- <c:forEach items="${menuList}" var="list" varStatus="status">
                <tr>
                    <td bgcolor="">
                        <input type="checkbox" id ="${list.MENU_ID}" name="menuCheck" value="${list.MENU_ID}" onClick="javascript:fn_checkControl(this , '${list.UP_P_MENUID_RECUR}','${list.DOWN_P_MENUID_RECUR}');" > ${list.MENU_NM}
                    </td>
                </tr>
            </c:forEach> --%>
            <c:forEach items="${menuList}"  var="list" varStatus="status" >
                 <c:set var="vChecked">
                      <c:forEach var="vList2" items="${authMenuList}" varStatus="vStatus2">
                           <c:choose>
                            <c:when test="${list.MENU_ID == vList2.MENU_ID}">checked="checked"</c:when>
                            <c:otherwise></c:otherwise>
                           </c:choose>
                      </c:forEach>
                 </c:set>
                 <%-- <input type="checkbox" name="chkTest" value="<c:out value='${vList}'/>" <c:out value='${vChecked}'/>> --%>
                <tr>
                    <td bgcolor="" style="padding-left:${fn:length(list.MENU_SEQ)*2}2px;">
                        <input type="checkbox"   id ="${list.MENU_ID}" name="menuCheck" value="<c:out value='${list.MENU_ID}' escapeXml="false"/>" <c:out value='${vChecked}'/> onClick="javascript:fn_checkControl(this , '${list.UP_P_MENUID_RECUR}','${list.DOWN_P_MENUID_RECUR}');" >   ${list.MENU_NM}
                    </td>
                </tr>
            </c:forEach>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:addMenu()">등록</a></li>
        <li><a href="javascript:fn_authControlList()">목록</a></li>
    </ul>
<!--     <div style="float:left; width:100%; text-align:right; margin-top:16px;">
        <span class="btn_pack medium"><button type="button" onclick="javascript:addMenu()">등록</button></span>
        <span class="btn_pack medium"><button type="button" onclick="javascript:fn_authControlList()">목록</button></span>
    </div>
 -->    <!--//버튼-->

</form>
</div>
<!--//content -->
