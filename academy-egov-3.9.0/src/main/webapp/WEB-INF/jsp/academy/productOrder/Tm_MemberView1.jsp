<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!--  last modified 2014-08-20 -->
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var orderstatuscode = "${searchMap.orderstatuscode}";
var search_date_type = "${searchMap.search_date_type}";
var payall = "${searchMap.payall}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";
	
var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	window.resizeTo(1024, 600);
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "point") {
				$('#point').val("");      
				$('#point').focus();         
				return;
			}
		}       
	}
}

//메모 및 POINT 변경
function user_memo(){

	var userid = $('#userid').val();
	var point = $('#point').val();
	var memo = $('#memo').val();
	var comment1 = $('#comment1').val();
	var _url = '<c:url value="/productOrder/MemoModify1.do"/>?userid=' + userid + '&point=' + point + '&memo=' + escape(encodeURIComponent(memo)) + '&comment1=' + escape(encodeURIComponent(comment1));
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			alert("변경되었습니다.");
			return;
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
}
  
// TM 상담내용 등록 버튼 클릭
function tmboardInsert() {
	
	if ($('#TMBDUTYCODE').val() == "") {
		alert('TM상담내용의 구분을 선택해 주세요.');
		$('#TMBDUTYCODE').focus();
		return;
	}
	if ($('#TMBVOCCODE').val() == "") {
		alert('TM상담내용의 분류를 선택해 주세요.');
		$('#TMBVOCCODE').focus();
		return;
	}
	
	 if ($('#tmbcontent').val() == "") {
		alert('TM 상담내용을 입력해 주세요.');
		$('#tmbcontent').focus();
		return;
	}
	 
	 $('#STS_TM').val("Y");
	 
	 $('#searchFrm').attr('action','<c:url value="/productOrder/MemberView1.pop"/>').submit();	
}  

 //CS상담내용 등록버튼 클릭
 function chkTmInsForm2(){ //cs상담내용 등록
	 if ($('#ccode').val() == "") {
		alert('분류를 선택해 주세요.');
		$('#ccode').focus();
		return;
	}
 
	 if ($('#cscontent').val() == "") {
		alert('상담내용을 입력해 주세요.');
		$('#cscontent').focus();
		return;
	}
	 
	 $('#STS').val("Y");
	 
	 $('#searchFrm').attr('action','<c:url value="/productOrder/MemberView1.pop"/>').submit();
 }
  
 
 //쿠폰발행 버튼
 function tmcoupon(){
	 window.open('<c:url value="/productOrder/TmCoupon.pop"/>?userid=' + $('#userid').val(), '_blank', 'width=800,height=500,top=0,left=0,location=no,resizable=no,scrollbars=yes');	 
} 
 
//신규쿠폰등록 버튼
 function newtmcoupon()
 {
	 window.open('<c:url value="/productOrder/TmCouponInsertAdd.pop"/>?userid=' + $('#userid').val(), '_blank', 'width=800,height=500,top=0,left=0,location=no,resizable=no,scrollbars=yes');
 }

/*   function searchsubmit1(form){
      if(event.keyCode == 13) chkTmInsForm(form);
  } */
  
function searchsubmit2(form){
    if(event.keyCode == 13) chkTmInsForm2(form);
}

//문자메세지
function sms(TelNO){
//	window.open("http://2010.hanlimgosi.co.kr/sms/sms.asp?recPhone="+TelNO+"","SMS","width=100,height=530,top=0,left=0,resizable=no,location=no,scrollbars=no");
	window.open('<c:url value="/productOrder/add.pop"/>?TOP_MENU_ID=${menuParams.TOP_MENU_ID}&userphone=' + TelNO + '&smsname=' + escape(encodeURIComponent('<c:out value="${list[0].USER_NM}"/>'))
			 , '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}
  
//포인트 내역
function PointHistory(){
	 window.open('<c:url value="/productOrder/TmPointHistory.pop"/>?userid=' + $('#userid').val(), '_blank', 'width=800,height=500,top=0,left=0,location=no,resizable=no,scrollbars=yes');	 
} 

//쪽지
function fn_message(USERID,USER_NM)
{  		
	$("#MESSAGEID").val(USERID);
	$("#MESSAGENM").val(USER_NM);
	window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=800,height=310 ');
	$('#searchFrm').attr('target' ,'message');
	$('#searchFrm').attr('action','<c:url value="/memberManagement/memberCheckMessage.pop"/>').submit();
}

//사용자 로그인
function fn_login()
{  
	window.open('', 'user', 'scrollbars=yes,toolbar=yes,resizable=yes');
	$('#searchFrm').attr('target' ,'user');
	$('#searchFrm').attr('action','<c:url value="http://www.willbescop.net/login/loginAdminProc"/>').submit();
}

//비동기 분류 가져오기
function getSubCode(selectId, target, url) {
	var ccode_top = "";
	
	ccode_top = $("#"+selectId).val();
	//alert("ccode_top:"+ccode_top);
	
	if(ccode_top == "" || ccode_top == undefined) {
		return false;
	}
	
	$("#ccode").html('').append("<option value=\"\">-- 분류 --</option>");
		
	var _url = url + '?ccode_top=' + ccode_top;
	
	//var PROFCODE = "${searchMap.PROFCODE}";
	//alert("###PROFCODE:"+PROFCODE);
	
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){					
					$("#ccode").append('<option value="' + mouigosa.CODE_VAL + '" >' + mouigosa.CODE_NM + '</option>');
					//alert("CODE_NM:"+mouigosa.CODE_NM);
					/* if(mouigosa.USERID == PROFCODE){					
						popFrm.PROFCODE.options[index+1].selected = true;
					} */
					
				});	
			}
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
}

//엔터키 검색
function fn_checkEnter(){
	 $('#keyword').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

function Coupon_Del(idx,ccode){
	if(confirm("쿠폰을 삭제 하시겠습니까?")){
				
		$.ajax({
			type: "GET",
			url: '<c:url value="/productOrder/Coupon_Del.do"/>?USERID='+$("#userid").val()+"&CCODE="+ccode+"&IDX="+idx,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				dataType : "text",
				cache : false,
				success : function(RES) {
					if(RES == 'Y'){
						alert("쿠폰이 정상적으로 삭제되었습니다.");
						window.location.reload();
						return;
					}else{
						alert("오류");
						return;
					}
				},
				error : function() {
					alert("삭제실패");
					return;
				}
			
		});
			
	}else{
		return;
	}
}
</script>
</head>
    <!--content -->
    <div>
    
    <form id="searchFrm" name="searchFrm" method="post">
  	
  	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
  	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
  	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
  				
  	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
  	<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
  	<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
  	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
  	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
  	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
  	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
  	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
  	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
  	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
	<input type="hidden" name="cmd" id="cmd">
	<input type="hidden" name="idx" id="idx"> 
    <input type="hidden" name="userid" id="userid" value="${list[0].USERID}" >
    <input type="hidden" name="username" id="username" value="${list[0].USER_NM}" >

	<input type="hidden" id="a_userid" name="a_userid" value="${list[0].USERID}" >
	<input type="hidden" id="a_userPwd" name="a_userPwd" value="a" >
    
    <input type="hidden" name="MESSAGEID" id="MESSAGEID">
    <input type="hidden" name="MESSAGENM" id="MESSAGENM">
	
	<input type="hidden" name="STS" id="STS" value="N" >
	<input type="hidden" name="STS_TM" id="STS_TM" value="N" >
	  
<table style="width:100%;">
	<tr>
		<td width="2%"></td>
		<td>
			<table style="width:100%; border:0; cellspacing:0; cellpadding:12px;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><h2>▣ 회원 상세정보</h2></td>
      			</tr>
    		</table>

			<table class="table05">
        		<tr>
					<th width="30%">그룹</th>
		          	<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].USER_ROLE} (${list[0].USER_POSITION})</td>
        		</tr>
				<tr>
					<th>아이디 / 이름</th>
					<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].USERID} / ${list[0].USER_NM}
         				<a href="#" onclick="javascript:sms('${list[0].CELP_NO}');">
             			<font color="blue" title="SMS보내기">[문자메세지]</font>
             			</a>
             			<a href="#" onclick="javascript:fn_message('${list[0].USERID}','${list[0].USER_NM}');">
             			<font color="blue" title="쪽지">[쪽지]</font>
             			</a>
    					<input type="button" onclick="fn_login();"  value="로그인" />
          			</td>
				</tr>
				<tr>
					<th>성별 / 생년월일</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].SEX} / ${list[0].BIRTH_DAY}</td>
        		</tr>
        		<tr>  
          			<th>전화번호 / 휴대폰</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].TELEP_NO} / ${list[0].CELP_NO}</td>
        		</tr>
        		<tr>  
          			<th>이메일 주소</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].EMAIL}</td>
        		</tr>
				<tr>
					<th>주소</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].JUSO}&nbsp;${list[0].JUSO2}</td>
        		</tr>
        		<tr>  
          			<th>가입일</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;${list[0].REGDATE}</td>
        		</tr>
				<tr>
					<th>POINT</th>
          			<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;
	          			<input  size="7" type="text" id="point" name="point"  value="${list[0].POINT}" onkeyup="chk(this,'point');" onblur="chk(this,'point');">&nbsp; 원
	          			&nbsp;&nbsp;<a href="javascript:PointHistory();">[포인트 내역]</a>&nbsp;&nbsp;포인트 수정 메모&nbsp;&nbsp;<input type="text" name="comment1" id="comment1" style="width: 40%" value=""/>
          			</td>
				</tr>
				<tr>
					<th>메모</th>
          			<td><textarea rows="3" cols="70" name="memo" id="memo"><c:out value='${list[0].REMARK}'/></textarea></td>
        		</tr>
    		</table>
    
			<!--버튼-->    
    		<ul class="boardBtns">
    		<c:if test="${list[0].USERID ne null }">
	        	<li><a href="javascript:user_memo();">메모 및 POINT 변경</a></li>
        	</c:if>
    		</ul>
    		<!--//버튼-->
    
			<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ TM 상담내용 등록</p></td>
      			</tr>
    		</table>
    
			<!--테이블--> 
      		<table class="table05">
          		<tr>
            		<td style="text-align:left;"> 선택 :            
	          			<select id="TMBDUTYCODE" name="TMBDUTYCODE" style="width:120;"> <!-- onchange="goList(1)" -->
							<option value="">- 구분 -</option>
							<c:forEach items="${dutyCodelist}"  var="item">
							<option value="${item.CODE }" <c:if test="${params.DUTYCODE == item.CODE }">selected</c:if>>${item.NAME }</option>
							</c:forEach>
							<option value="999" <c:if test="${params.DUTYCODE == '999' }">selected</c:if>>독서실</option>
			  			</select>
			  			<select id="TMBVOCCODE" name="TMBVOCCODE" style="width:120;"> <!-- onchange="goList(1)" -->
							<option value="">- 분류 -</option>
							<c:forEach items="${vocCodelist}"  var="item">
							<option value="${item.CODE_VAL }" <c:if test="${params.VOCCODE == item.CODE_VAL}">selected</c:if>>${item.CODE_NM }</option>
							</c:forEach>
			  			</select>
					</td>
  		  		</tr>
		  		<tr>
					<td style="text-align:left;">
						<input id="tmbcontent" name="tmbcontent" type="text" style="width:80%;" title=""/>
						<input type="button"   onclick="tmboardInsert();"  value="등록" />
		    		</td>
          		</tr>
      		</table>
    
			<!--//테이블-->
    		<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ TM 상담내용 리스트</p></td>
        			<td align="right"><span>총 &nbsp; ${fn:length(tm_list)}건</span></td>
      			</tr>
    		</table>
    
			<!--테이블-->
     		<table class="table05">
        		<tr>
					<th width="7%">No</th>
					<th>접수시간</th>
					<th>상담자</th>
					<th>구분</th>
					<th>분류</th>
					<th>질의내용</th>
				</tr>
				<c:if test="${not empty tm_list}">
          		<c:forEach items="${tm_list}" var="tmitem" varStatus="status">
             	<tr>
					<td>${tmitem.CNT}</td>
					<td>${tmitem.REG_DT}</td>
					<td>${tmitem.REGUSERNAME}<br />(${tmitem.REGUSERID})</td>
					<td>${tmitem.DUTYCODE_NM}</td>
					<td>${tmitem.VOCCODE_NM}</td>
					<td style="text-align:left;">${tmitem.CONTENT}</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty tm_list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="6">표시할 데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->
        
			<br><br>
    
			<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ CS상담내용 등록</p></td>
      			</tr>
    		</table>
    
			<!--테이블--> 
      		<table class="table05">
          		<tr>
            		<td style="text-align:left;">            				
						<c:forEach items="${csccode_list}" var="csccode_list" varStatus="status">				
						<input type="radio" name="ccode_top"  id="ccode_top${status.index}" value="${csccode_list.CODE_VAL}" onclick="getSubCode('ccode_top${status.index}', 'ccode', '<c:url value="/productOrder/ccode_top.do"/>')">
                    	${csccode_list.CODE_NM}
						</c:forEach>
				
						<select id="ccode" name="ccode" >
							<option value="">-- 분류 --</option>
						</select>
				
						<select name="actionflag" id="actionflag" >
	                    	<option value="Y">조치</option>
	                    	<option value="N">미조치</option>
                		</select>
					</td>
  		  		</tr>
		  		<tr>
					<td style="text-align:left;">
						<input id="cscontent" name="cscontent" type="text" style="width:80%;" title=""/>
						<input type="button" onclick="chkTmInsForm2();"  value="등록" />
		    		</td>
          		</tr>
      		</table>
    
			<!--//테이블-->
       
    		<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ CS 상담 내용 리스트</p></td>
        			<td align="right"><span>총 &nbsp; ${fn:length(Cs_list)}건</span></td>
      			</tr>
    		</table>    
    
    		<!--테이블-->
     		<table class="table05">
        		<tr>
					<th width="7%">No</th>
					<th>접수시간</th>
					<th>상담자</th>
					<th>구분</th>
					<th>분류</th>
					<th>조치</th>
					<th>질의내용</th>
				</tr>
				<c:if test="${not empty Cs_list}">
          		<c:forEach items="${Cs_list}" var="Cs_list" varStatus="status">
             	<tr>
					<td>${Cs_pop_allCount - status.index}</td>
					<td>${Cs_list.REG_DT}</td>
					<td>${Cs_list.COUNSELOR_NM}</td>
					<td>${Cs_list.CS_DIV_NM}</td>
					<td>${Cs_list.CS_KIND_NM}</td>
					<td>${Cs_list.ACTION_YN}</td>
					<td style="text-align:left;">${Cs_list.CONTENT}</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty Cs_list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="7">데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->

			<br><br>
    
			<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ 내쿠폰 리스트</p></td>
					<td bgcolor="#FFFFFF" width="10%" align="right">
           				<div style="border:solid 1px #1E1E1E;"  align="center" >
             			<strong>  미사용</strong>
           				</div>
        			</td>
        			<td width="10%" align="right">
            			<div style="border:solid 1px #1E1E1E; background-color:#FFE3EE;" align="center">
             			<strong> 사용</strong>
            			</div>
        			</td>
        			<td width="10%" align="right">
            			<div style="border:solid 1px #1E1E1E; background-color:#66FF66;" align="center">
             			<strong>삭제</strong>
            			</div>
        			</td>
					<td width="5%"></td>
        			<td width="30%" align="right"><input type="button" value="쿠폰발행" onclick="javascript:tmcoupon()"></td>
					<td width="10%" align="right">총 &nbsp; ${fn:length(My_coupon_list)}건</td>
                         
				</tr>
			</table>
    
			<!--테이블-->
     		<table class="table05">
        		<tr>
          			<th width="7%">No</th>
          			<th>쿠폰</th>
					<th>쿠폰이름</th>
					<th>할인액</th>
					<th>유효기간</th>
					<th>쿠폰삭제</th>
        		</tr>
         		<c:if test="${not empty My_coupon_list}">
          		<c:forEach items="${My_coupon_list}" var="My_coupon_list" varStatus="status">
	       			<c:choose>
						<c:when test="${My_coupon_list.ORDERFLAG eq '0' }">
							<c:set var="trcolor" value="#FFFFFF"/>
						</c:when>
						<c:when test="${My_coupon_list.ORDERFLAG eq '2' }">
							<c:set var="trcolor" value="#66FF66"/>
						</c:when>
						<c:otherwise>
							<c:set var="trcolor" value="#FFE3EE"/>
						</c:otherwise>
					</c:choose>
				<tr bgcolor="${trcolor}">
					<td>${My_coupon_list.CNT}</td>
					<td>${My_coupon_list.CCODE}</td>
	            	<td style="text-align:left;">${My_coupon_list.CNAME}</td>
	            	<td>
	            		${My_coupon_list.REGPRICE}
						<c:choose>
							<c:when test="${My_coupon_list.REGTYPE eq 'C' }">
								(%)
							</c:when>
							<c:when test="${My_coupon_list.REGTYPE eq 'P' }">
								(point)
							</c:when>
							<c:otherwise>
							-
							</c:otherwise>
						</c:choose>
					</td>
					<td>${fn:substring(My_coupon_list.EXPDATES, 0, 10)} ~ ${fn:substring(My_coupon_list.EXPDATEE, 0, 10)}</td>
						<c:choose>
							<c:when test="${My_coupon_list.ORDERFLAG eq '2' ||  My_coupon_list.ORDERFLAG eq '1'}">
					<td></td>
							</c:when>
							<c:otherwise>
					<td><a href="javascript:Coupon_Del('${My_coupon_list.IDX}','${My_coupon_list.CCODE}');">[삭제]</a></td>
							</c:otherwise>
						</c:choose>
				</tr>
      			</c:forEach>
				</c:if>
				<c:if test="${empty My_coupon_list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="7">데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->

			<br><br>

			<table style="width:100%;">
				<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ 사용자 수강 내역 - 동영상</p></td>
        			<td align="right"><span>총 &nbsp; ${fn:length(Tm_Class_list)}건</span></td>
      			</tr>
    		</table>
    
			<!--테이블-->
			<table class="table05">
        		<tr>
          			<th>결제일</th>
          			<th>상태</th>
          			<th>시작일</th>
					<th>종료일</th>
					<th>강의코드</th>
					<th>강의명</th>
					<th>금액</th>
					<th>진행률</th>
					<th>등록자</th>
        		</tr>
         		<c:if test="${not empty Tm_Class_list}">
          		<c:forEach items="${Tm_Class_list}" var="Tm_Class_list" varStatus="status">
             	<tr>
					<td>${Tm_Class_list.ISCONFIRM}</td>
					<td>${Tm_Class_list.STATUS_NM}</td>
					<td>${Tm_Class_list.MYLECTURE_START_DATE}</td>
					<td>${Tm_Class_list.MYLECTURE_END_DATE}</td>
	            	<td>${Tm_Class_list.PACKAGE_NO}</td>
	            	<td style="text-align:left;">${Tm_Class_list.SUBJECT_TITLE}</td>
	            	<td><fmt:formatNumber value="${Tm_Class_list.PRICE}" type="currency" /></td>
	            	<td>${Tm_Class_list.MYLECTURE_STUDY_PERCENT}%</td>
	            	<td>${Tm_Class_list.OPEN_ADMIN_ID}</td>
	        	</tr>
         		<c:if test="${Tm_Class_list.MEMO ne '' && Tm_Class_list.MEMO ne null}">
		        <tr> 
					<th>등록사유</th>
					<td colspan="10" style="text-align:left;">${Tm_Class_list.MEMO}</td>
				</tr>
         		</c:if>
      			</c:forEach>
				</c:if>
				<c:if test="${empty Tm_Class_list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="10">데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->
     
			<br><br>

    		<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ 사용자 수강 내역 - 학원</p></td>
        			<td align="right"><span>총 &nbsp; ${fn:length(Off_Class_List)}건</span></td>
      			</tr>
    		</table>
    
			<!--테이블-->
     		<table class="table05">
        		<tr>
          			<th>결제일</th>
          			<th>상태</th>
          			<th>주문번호</th>
					<th>과목</th>
					<th>금액</th>
					<th>결재금액</th>
					<th>강의시작일</th>
        		</tr>
         		<c:if test="${not empty Off_Class_List}">
          		<c:forEach items="${Off_Class_List}" var="Off_Class_List" varStatus="status">
				<tr>
					<td>${Off_Class_List.ISCONFIRM}</td>
					<td>${Off_Class_List.STATUS_NM}</td>
					<td>${Off_Class_List.ORDERNO}</td>
					<td style="text-align:left;">${Off_Class_List.SUBJECT_TITLE}</td>
	            	<td><fmt:formatNumber value="${Off_Class_List.SUBJECT_REAL_PRICE}" type="currency" /></td>
	            	<td><fmt:formatNumber value="${Off_Class_List.PRICE}" type="currency" /></td>
	            	<td>${Off_Class_List.SUBJECT_OPEN_DATE}</td>
	        	</tr>
         		<c:if test="${Off_Class_List.PRICE_DISCOUNT_REASON ne '' && Off_Class_List.PRICE_DISCOUNT_REASON ne null}">
		        <tr> 
					<th>등록사유</th>
					<td colspan="7" style="text-align:left;">${Off_Class_List.PRICE_DISCOUNT_REASON}</td>
				</tr>
         		</c:if>
      			</c:forEach>
				</c:if>
				<c:if test="${empty Off_Class_List}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="8">데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->
     
			<br><br>

			<table style="width:100%;">
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><p>▣ 쪽지 보낸 리스트</p></td>
        			<td align="right"><span>총 &nbsp; ${fn:length(momo_list)}건</span></td>
      			</tr>
    		</table>
    		
    		<!--테이블-->
			<table class="table05">
        		<tr>
          			<th width="10%">NO</th>
					<th width="20%">발송일시</th>
					<th width="10%">구분</th>
					<th>내용</th>
					<th width="20%">확인일시</th>
        		</tr>
         		<c:if test="${not empty momo_list}">
          		<c:forEach items="${momo_list}" var="momo_list" varStatus="status">
					<c:choose>
						<c:when test="${momo_list.READ_YN eq 'N' }">
							<c:set var="trcolor" value="#black"/>
						</c:when>
						<c:otherwise>
							<c:set var="trcolor" value="red"/>
						</c:otherwise>
					</c:choose>
				<tr>
					<td>${momo_list.CNT}</td>
					<td>${momo_list.SEND_DT}</td>
	            	<td style="color:${trcolor};">${momo_list.STS}</td>
	            	<td style="text-align:left;">${momo_list.CONT}</td>
					<td>${momo_list.READ_DT}</td>
	        	</tr>
      			</c:forEach>
				</c:if>
				<c:if test="${empty momo_list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="6">표시할 데이터가 존재하지 않습니다.</td>
				</tr>
				</c:if>	 
     		</table>
     		<!--//테이블-->
     
			<!--버튼-->    
    		<ul class="boardBtns">
        		<li><a href="javascript:self.close();">닫기</a></li>
    		</ul>
    		<!--//버튼-->
		</td>
		<td width="2%"></td>
	</tr>
</table>	  
    
</form>
</div>
<!--//content --> 

</html>