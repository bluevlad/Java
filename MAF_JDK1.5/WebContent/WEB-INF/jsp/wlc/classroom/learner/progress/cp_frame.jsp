<%@ page contentType="text/html; charset=euc-kr"%>
<%
    //웹 브라우저가 캐쉬하지 못하도록 헤더를 세팅하는 스크립틀릿
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    if(request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>
<c:set var="param1" value=""/>
<c:set var="param2" value=""/>
<c:choose>
  <c:when test="${citem.netg_sub_cid == 'ICU'}">
    <c:set var="param1" value="id"/>
    <c:set var="param2" value="course_id"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'KDI'}">
    <c:set var="param1" value="remoteUserId"/>
    <c:set var="param2" value="courseId"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'KAIST'}">
    <c:set var="param1" value="res_no1"/>
    <c:set var="param2" value="leccode"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'ENGLISHCARE'}">
    <c:set var="param1" value="uID"/>
    <c:set var="param2" value="ex_code"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'hunet'}">
    <c:set var="param1" value="user_id"/>
    <c:set var="param2" value="process_code"/>
  </c:when>
</c:choose>
<c:set var="next" value=""/>
<c:choose>
  <c:when test="${citem.netg_sub_cid == 'ICU'}">
    <c:set var="next" value="${citem.launch_data}${'?'}${param1}${'='}${userid}${'&'}${param2}${'='}${citem.netg_cid}${'&qry_src=sun&usrname='}${usernm}"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'ENGLISHCARE'}">
    <c:set var="next" value="${citem.launch_data}${'?'}${param1}${'='}${userid}${'&'}${param2}${'='}${citem.netg_cid}${'&ex_company=sunwlc'}"/>
  </c:when>
  <c:when test="${citem.netg_sub_cid == 'hunet'}">
    <c:set var="next" value="${citem.launch_data}${'&'}${param1}${'='}${userid}${'&'}${param2}${'='}${citem.netg_cid}"/>
  </c:when>
  <c:otherwise">
    <c:set var="next" value="${citem.launch_data}${'?'}${param1}${'='}${userid}${'&'}${param2}${'='}${citem.netg_cid}"/>
  </c:otherwise>
</c:choose>
<script>
    document.location.href='${next}';
</script> 
