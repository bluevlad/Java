<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="JavaScript" src='<c:out value="${MBOARD.PATH}"/>/js/list.js'  type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
    var urlname = '<c:url value="/board/viewimage.do"/>?bid=' + bid + 
                '&c_index=' + c_index +
                '&seq='+seq ;
    browsing_window = window.open(urlname, "imagewin",
            "top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
    browsing_window.focus();
}
</script>
<table width="95%" cellspacing="0" cellpadding="0" border="0">
    <!--  검색부  -->
        <mh:import url="${MBOARD.PATH}/inc/search.jsp" />   
    <!--  검색부  -->
    <!-- List -->
    <tr>
        <td>
            <div class="listContainer">
            <table width=100% border="0" cellspacing="5" cellpadding="5">
                <col width="25%" />
                <col width="25%" />
                <col width="25%" />
                <col width="25%" />
                <tbody>
<!-- 일반글 -->
<c:if test="${!empty(list)}">
<c:set value="4" var="cols"/>
<c:set value="1" var="cnt"/>
    <tr>
    <c:forEach items="${list}" var="item" varStatus="status">
    <c:url var="url" value="/mboard/view.do">
    <c:param name="c_index" value="${item.c_index}" />
    <c:param name="LISTOP" value="${MBOARD.listOpStr}" />
    </c:url>
        <td width="25%" align="center">
        <!-- 사진1 start -->
            <c:url var="imgUrl" value="/mbbsfile/${MBOARD.bid}/thumb/${item.c_image}"/>
            <a href='<c:out value="${url}"/>'><img src='<c:out value="${imgUrl}"/>' border="0" 
                style="cursor: pointer;" hspace="5" vspace="5" border="0" width="80" 
                onError="this.src='<c:out value="${MBOARD.PATH}/images/no_image.gif"/>'"></a>
            <br><mh:out value="${item.c_subject }" bytes="20"/>
        <!-- 사진1 end -->
        </td>
    <c:if test="${cnt%cols == 0}">
    </tr>
    <tr>
    </c:if>
    <c:set value="${cnt+1}" var="cnt"/>
    </c:forEach>
    </tr>
</c:if>
<!-- 리스트 첫번째 줄 end -->
                </tbody>
            </table>
            </div>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <td align="center"><mh:import url="${MBOARD.PATH}/inc/navigator.jsp" /></td>
                </tr>
                <tr>
                    <td align="right">
                        <mh:indexOf var="t" value="${MBOARD.BTN_AUTH}" needle="W" />
                        <c:if test="${t >= 0 }">
                        <mf:button onclick="MBoard_Write()" bundle="button" key="add" icon="icon_add" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<mh:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />