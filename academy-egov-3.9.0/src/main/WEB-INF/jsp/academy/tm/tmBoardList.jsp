<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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

		$('#searchFrm').attr('action','<c:url value="/tm/tmBoardList.do"/>').submit();
	}

	//엑셀
	function excel_onclick1() { //ck 추가
		<c:if test="${empty list}">
			alert('엑셀파일로 저장할 데이타가 없습니다.');
			return;
		</c:if>
		$('#searchFrm').attr('action','<c:url value="/tm/tmBoardList_excel.do"/>').submit();
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
	    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=820,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	    }
	}

	function delect(idx,vcode,id){
		$("#cmd").val('delete');
		$("#idx").val(idx);
		$("#vcode").val(vcode);
		$("#userid").val(id);
		$('#searchFrm').attr('action','<c:url value="/tm/tmBoardList.do"/>').submit();
	}

	function TM_modify(idx,userid,Reqname,content,dutycode,voccode){
		document.getElementById("divTmModify").style.display = "block";
		$("#idx").val(idx);
		$("#userid").val(userid);
		$("#MName").text(Reqname);
		$("#MCONTENT").val(content);
		$("#MDUTYCODE").val(dutycode);
		$("#MVOCCODE").val(voccode);
	}

	function TM_BoardModify(){
		$("#cmd").val('modify');
		if($("#MCONTENT").val() == ''){
			alert('상담내용을 입력해야 합니다.');
			$("#MCONTENT").focus();
			return;
		}
		$('#searchFrm').attr('action','<c:url value="/tm/tmBoardList.do"/>').submit();
	}

	function TM_modifyclose(){
		$("#cmd").val('list');
		document.getElementById("divTmModify").style.display = "none";
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
	<h2>● TM 관리 &gt; <strong>TM 상담내용</strong></h2>

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

	<input type="hidden" id="cmd" name="cmd" value="list"/>
	<input type="hidden" id="idx" name="idx" />
	<input type="hidden" id="vcode" name="vcode" />
	<input type="hidden" id="userid" name="userid" />

	<table class="table01">
        <tr>
          <th width="7%">상담일</th>
          <td width="25%" style="vertical-align:middle;">
           <input type="hidden" name="cmd">
           <div id="topdecks" style="position : absolute;display:none;"></div>

          <input type="text" id="SDATE" name="SDATE" maxlength="10" class="i_text" value="${params.SDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="EDATE" name="EDATE" maxlength="10" class="i_text" value="${params.EDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
	      </td>
     	  <th width="7%">구분/분류</th>
          <td width="20%" style="vertical-align:middle;">
          <select id="DUTYCODE" name="DUTYCODE" style="width:120;"> <!-- onchange="goList(1)" -->
				<option value="">- 구분 -</option>
			<c:forEach items="${dutyCodelist}"  var="item">
				<option value="${item.CODE }" <c:if test="${params.DUTYCODE == item.CODE }">selected</c:if>>${item.NAME }</option>
			</c:forEach>
				<option value="999" <c:if test="${params.DUTYCODE == '999' }">selected</c:if>>독서실</option>
		  </select>

		  <select id="VOCCODE" name="VOCCODE" style="width:120;"> <!-- onchange="goList(1)" -->
				<option value="">- 분류 -</option>
			<c:forEach items="${vocCodelist}"  var="item">
				<option value="${item.CODE_VAL }" <c:if test="${params.VOCCODE == item.CODE_VAL}">selected</c:if>>${item.CODE_NM }</option>
			</c:forEach>
		  </select>
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

	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>

     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="5%">No</th>
          	<th width="8%">접수시간</th>
			<th width="7%">상담자</th>
			<th width="7%">회원/ID</th>
			<th width="10%">TEL/HP</th>
			<th width="7%">구분</th>
			<th width="7%">분류</th>
			<th >상담내용</th>
			<th width="10%">수정 | 삭제</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td>${list.REG_DT}</td>
				<td>${list.REGUSERNAME}</td>
				<td><a href="#" onclick="javascript:member_view('${list.REQUSERID}');">${list.REQUSERNAME}<br/>${list.REQUSERID}</a></td>
				<td>${list.TEL_NO}<br />${list.PHONE_NO}</td>
				<td>${list.DUTYCODE_NM}</td>
				<td>${list.VOCCODE_NM}</td>
				<td ONMOUSEOVER="popHelp('${list.CONTENT}','lightgreen','200','title1');" ONMOUSEOUT="kill();" >${list.CONTENT}</td>
				<td><input type="button" value="┼"
							onclick="javascrip:TM_modify('${list.IDX}','${list.REQUSERID}','${list.REQUSERNAME}','${list.CONTENT}','${list.DUTYCODE}','${list.VOCCODE}');" title="수정"></input>
                    <font color="red">|</font>
                    <input type="button" value="X" onclick="javascript:delect('${list.IDX}','${list.VOCCODE}','${list.REQUSERID}');" title="삭제" ></input>
                </td>
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

	<div id="divTmModify" style="display:none;">
		<table style="width:100%;">
			<tr>
		 	<td width="20%" style="vertical-align:middle;">
	          <select id="MDUTYCODE" name="MDUTYCODE" style="width:120;"> <!-- onchange="goList(1)" -->
					<option value="">- 구분 -</option>
				<c:forEach items="${dutyCodelist}"  var="item">
					<option value="${item.CODE }">${item.NAME }</option>
				</c:forEach>
					<option value="999">독서실</option>
			  </select>

			  <select id="MVOCCODE" name="MVOCCODE" style="width:120;"> <!-- onchange="goList(1)" -->
					<option value="">- 분류 -</option>
				<c:forEach items="${vocCodelist}"  var="item">
					<option value="${item.CODE_CD }">${item.CODE_NM }</option>
				</c:forEach>
			  </select>
	      	</td>
	      	<td><strong>고객명 : <span id="MName"></span></strong> (고객명은 수정되지 않습니다)
	      	</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:left;">
					<input id="MCONTENT" name="MCONTENT" type="text" style="width:60%;" title=""/> &nbsp;
					<input type="button" onclick="TM_BoardModify();"  value="수정" /> &nbsp;&nbsp;
					<input type="button" onclick="TM_modifyclose();"  value="취소" />
				</td>
			</tr>

		</table>
	</div>

</form>

</div>
</html>