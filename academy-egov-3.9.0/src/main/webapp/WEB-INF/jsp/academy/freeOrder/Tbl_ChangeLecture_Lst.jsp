<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
window.onload = function () {
     /* if(message != "") {
        alert(message);
    } */
}

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {       
        if (val.match(/^\d+$/gi) == null) {          
            $('#pageRow').val("");      
            $('#pageRow').focus();         
            return;       
        } else {          
            if (val < 1) {             
                $('#pageRow').val("");          
                $('#pageRow').focus();            
                return;          
            }       
        }    
    }
}

//엔터키 검색
function fn_checkEnter(){
     $('#keyword').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}

//검색
function goList(page) { 
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    
    $("#cmd").val("Y");
    
    $('#searchFrm').attr('action','<c:url value="/freeOrder/changelist.do"/>').submit();
}

//이름 클릭시
function view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
        window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}

//강의제목 클릭
function detail(orderno,package_no) {
    $("#orderno").val(orderno);
    $("#package_no").val(package_no);
    
    $('#searchFrm').attr('action','<c:url value="/freeOrder/viewChange.do"/>').submit();
}

// 엑셀 다운로드
function fn_excel_down() {
    $("#searchFrm").attr("action", "<c:url value='/freeOrder/excel_change.do' />");
    $("#searchFrm").submit();
}
</script>
</head>
  <!--content -->
  <div id="content">
    <h2>● 상품주문관리 > <strong>수강변경</strong></h2>
  
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="cmd" name="cmd"/>
    
    <input type="hidden" id="orderno" name="orderno"/>
    <input type="hidden" id="package_no" name="package_no"/>
    
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">
    
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
    
   <!--테이블--> 
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>                
                <input  title="검색" size="15" type="text" id="keyword" name="keyword"  value="${searchMap.keyword}" onkeypress="fn_checkEnter()">                
                <input type="button"   onclick="goList(1)"  value="검색" />  ( ☜ 수강자의 이름, 아이디, 전화번호, 핸드폰번호를 입력하세요! )
            </td>
          </tr>
      </table>
    <!--//테이블-->
     
    <!--버튼-->
    <%--    
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    --%>
    <!--//버튼-->
       
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
    <!--테이블-->
     <table class="table02">
        <tr>
            <th width="7%">No</th>
            <th>주문번호</th>
            <th>수강자</th>
            <th>강의코드</th>
            <th>강의제목</th>
            <th>수강료</th>
            <th>결제상태</th>
            <th>신청일</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${list.ORDERNO}</td>
                <td><a href="javascript:view('${list.USER_ID}')">${list.USER_NAME}(${list.USER_ID})</a></td>
                <td>${list.PACKAGE_NO}</td>             
                <td><a href="javascript:detail('${list.ORDERNO}','${list.PACKAGE_NO}')">${list.PACKAGE_NAME}</a></td>
                <td>${list.PRICE}</td>
                <td>${list.STATUSNAME}</td>
                <td>${list.REGDATE}</td>
            </tr>
      </c:forEach>
        </c:if>
        <c:if test="${empty list}">
         <tr bgColor=#ffffff align=center> 
            <td colspan="8">데이터가 존재하지 않습니다.</td>
        </tr>
        </c:if>  
     </table>
     <!--//테이블--> 
   
    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
    <!--//paginate-->
    
</form>
</div>
<!--//content --> 
