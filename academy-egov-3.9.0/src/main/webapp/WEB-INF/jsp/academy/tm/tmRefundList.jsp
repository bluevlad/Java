<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>

<script type="text/javascript">
	$(document).ready(function(){
		setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
		initDatePicker1("SDATE");	
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		initDatePicker1("EDATE");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	});
	
	
	//검색
	function goList(page) {
		if(typeof(page) == "undefined") $("#currentPage").val(1);
		else $("#currentPage").val(page);
		
		if($("#SDATE").val()!='' || $("#EDATE").val()!=''){
			if($("#SDATE").val()!='' && $("#EDATE").val()!=''){
				if(parseInt($("#EDATE").val().replace(/-/g,'')) < parseInt($("#SDATE").val().replace(/-/g,''))){
					alert('검색일자 종료일은 시작일보다 큰 날짜를 선택해야 합니다.');
					return;
				}			
			}		
		}
		
		$('#searchFrm').attr('action','<c:url value="/tm/tmRefundList.do"/>').submit();
	}
	
	//엑셀
	function excel_onclick1() { //ck 추가
		<c:if test="${empty list}">
			alert('엑셀파일로 저장할 데이타가 없습니다.');
			return;
		</c:if>
		$('#searchFrm').attr('action','<c:url value="/tm/tmRefundList_excel.do"/>').submit();
	}
	
	//엔터키 검색
	function fn_checkEnter(){
		$('#SEARCHTEXT').keyup(function(e)  {
			if(e.keyCode == 13) {
				goList(1);
			}
		});
	}
	
	//주문자 클릭시
	function member_view(userid){
	    
	    if(userid=="" || userid ==null){
	        alert("비회원입니다.");
	        return;
	    }else{
	    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=1200,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	    }
	}
	

    //풍선 도움말 소스
    var nav = (document.layers);
    var iex = (document.all);
    function popHelp(msg,bak,width,titlemsg) {
		if(msg == ""){ 
         	return;
        }
        var skn = document.all.topdecks;
/*          var content ="<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=1 BGCOLOR=#505050 style='width:300px;'><TR><TD>"+
                  "<TABLE cellpadding=0 cellspacing=0 BGCOLOR="+bak+" style='width:300px;'><TR><TD style='width:300px;' style='padding:5;'><FONT COLOR=#000000><nobr>"+msg+"</nobr></FONT></TD></TR></TABLE></TD></TR></TABLE>"; */
        var content = "<div style='border: 1px solid #00006F; background-color: #f8fccd; width:500px; height=100px; padding:5px;'><font size='3'>"+msg+"</font></div>"
        skn.innerHTML = content;
        skn.style.display = "";
    }
      
    function get_mouse(e) {
     	var skn = document.all.topdecks;
        var x = (nav) ? e.pageX : event.x+document.body.scrollLeft;
        var y = (nav) ? e.pageY : event.y+document.body.scrollTop;
        skn.style.left = x - 200;
        skn.style.top  = y + 20;
 	} 
    
    function kill() {
           var skn = document.all.topdecks;
           skn.style.display = "none";
    }
    document.onmousemove = get_mouse;

</script>

<title>TM관리 - 상담내용</title>
</head>

<!--content -->
  <div id="content">
	<h2>● TM 관리 &gt; <strong>TM 환불취소 리스트</strong></h2>
	   
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />

	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />

	<table class="table01">
        <tr>
          <th width="7%">취소환불일</th>
          <td width="25%" style="vertical-align:middle;">
           <input type="hidden" name="cmd">
           <div id="topdecks" style="position : absolute;display:none;"></div>
          
          <input type="text" id="SDATE" name="SDATE" maxlength="10" class="i_text" value="${params.SDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="EDATE" name="EDATE" maxlength="10" class="i_text" value="${params.EDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
	      </td>
     	  <th width="6%">검색</th>
          <td width="25%" style="vertical-align:middle;">
          <select name="SEARCHTYPE" id="SEARCHTYPE">
				<option value="" <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>전체검색</option>
				<option value="REGUSERNAME" <c:if test="${params.SEARCHTYPE == 'REGUSERNAME'}">selected</c:if>>상담원이름</option>
				<option value="REQUSERNAME" <c:if test="${params.SEARCHTYPE == 'REQUSERNAME'}">selected</c:if>>회원이름</option>
				<option value="CONTENT" <c:if test="${params.SEARCHTYPE == 'CONTENT'}">selected</c:if>>상담내용</option>
			</select>
			<input type="text" class="i_text"  title="검색" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" style="width:80px;"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
	        <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
	        </td>
	        <td><span class="btn_pack medium" style="vertical-align:middle;align:right;"><button type="button" onclick="excel_onclick1();">Excel</button></span></td>
	  	</tr>
    </table>
      
	<p class="pInto01">&nbsp;<span>- 전체 환불취소건 : ${totalCount} 건
	&nbsp;&nbsp;/&nbsp;- 전체 판매금액 : <fmt:formatNumber value="${resultMap.SALEPRICE}" groupingUsed="true"/> 원 
	&nbsp;&nbsp;/&nbsp;- 전체 환불취소금액 : <fmt:formatNumber value="${resultMap.CANCELPRICE}" groupingUsed="true"/> 원 / Page:(<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span>
	</p>	
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="5%">No</th>
          	<th width="10%">주문번호</th>
			<th width="8%">주문자/(ID)</th>
			<th width="10%">결제일/<br />취소환불일</th>
			<th >강의명</th>
			<th width="8%">판매가</th>
			<th width="8%">환불취소금액</th>
			<th width="10%">상담원/(ID)</th>
			<th width="15%">배정일/<br />최종통화일</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td>${list.ORDERNO}</td>
				<td><a href="#" onclick="javascript:member_view('${list.REQUSERID}');">${list.REQUSERNAME}<br/>(${list.REQUSERID})</a></td>
				<td>${list.CONFIRMDATE}<br >${list.CONFIRMCANCELDATE}</td>
				<td ONMOUSEOVER="popHelp('${list.SUBJECT_TITLE}','lightgreen','200','title1');" ONMOUSEOUT="kill();" >${list.SUBJECT_TITLE}</td>
				<td>${list.SALEPRICE}</td>				
				<td>${list.CONFIRMCANCELPRICE}</td>				
				<td>${list.REGUSERNAME}<br />(${list.REGUSERID})</td>
				<td>${list.LASTALLOCDATE}<br />(${list.LASTREGDATE})</td>
	        </tr>
      	  </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		    <tr bgColor=#ffffff align=center> 
				<td colspan="9">표시할 데이터가 없습니다.</td>
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
</html>