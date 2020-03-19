<%@ page contentType = "text/html; charset=euc-kr" %>
Table Name : ${tabsname}

<c:set var="ColumnCnt" value="${fn:length(columns)}"/>
<script language="javascript" >
	function form_copy(objnm)
	{
	    var frm = document.getElementById("frm");
			if(frm) {
		    var szTmpText = frm[objnm].value;
		    window.clipboardData.setData('Text', szTmpText);
		   alert("소스를 복사하였습니다.\n\n Editor에 붙여넣기 하세요.");
			}
	    return;
	}
</script>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>

<table width="500" border="1" cellspacing="0" cellpadding="2">
	<tr>
		<th> Sample </th>
	</tr>
	<tr>
		<td valign="top"><table border="0" cellpadding="2" cellspacing="0" class="table">	
			<form action="${control_action}" method="post" name="frmx" id="frmx" onSubmit="return validate(this)">
			<c:forEach items="${columns}" var="item">
			  <tr>
			    <th class="th" nowrap>${(item.nullable=="Y")?"":"<span id='lbl_required'>*</span>"}${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}</th> 
			    <td class="td"><input type="text" name="${fn:toLowerCase(item.name)}" 
			        value="${mhtml:stripQuote(item.data_default)}" 
			        size="20" maxlength="${item.dlength}"
			        hname="${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}"
			        ${(item.nullable=="Y")?"":"required"} ${(item.type=="NUMBER")?"option='number'":""} ></td>
			  </tr>
			</c:forEach>
			  <tr>
			    <td colspan="2" align="center"><input type="submit" value="저장"><input type="reset" value="취소"></td>
			  </tr>
			</form>
			</table></td>
	</tr>
	<tr>
		<th><a href="javascript:form_copy('frmins')"> [입력 소스복사]</a></th>			
	</tr>
	<tr>
		<form action="${control_action}" method="post" name="frm" id="frm" >
		<td><textarea cols="80" rows="25" name="frmins">
		
			<script language="javascript" src="\${CPATH}/js/lib.validate.js"></script>
			<script>
				function frmSubmit(frm) {
					if (validate(frm)) {
						// 사용자 조건 추가 
						
						return true;
					} else {
						return false;
					}
				}
			</script>
			<table border="0" cellpadding="2" cellspacing="0" class="table">	
			<form action="\${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
			<c:forEach items="${columns}" var="item"><c:if test="${!empty(item.pk)}">  <input type="hidden" name="${fn:toLowerCase(item.name)}" value="">
			</c:if></c:forEach>  <input type="hidden" name="cmd" value="">
			<c:forEach items="${columns}" var="item">  <tr>
			    <th class="th" nowrap>${(item.nullable=="Y")?"":"<span id='lbl_required'>*</span>"}${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}</th> 
			    <td class="td"><input type="text" name="${fn:toLowerCase(item.name)}" value="${mhtml:stripQuote(item.data_default)}" size="20" maxlength="${item.dlength}"
			        hname="${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}" ${(item.nullable=="Y")?"":"required"} ${(item.type=="NUMBER")?"option='number'":""} ></td>
			  </tr>
			</c:forEach>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="저장"><input type="reset" value="취소"></td>
				</tr>
			</form>
			</table>
			
			</textarea></td>
</tr>
<tr>
	<th><a href="javascript:form_copy('frmedit')"> [수정 소스복사]</a></th>
</tr>
	<tr>
		<td><textarea cols="80" rows="25" name="frmedit">
<script language="javascript" src="\${CPATH}/js/lib.validate.js"></script>
<script language="javascript" >
		function frmSubmit(frm) {
			if (validate(frm)) {
				// 사용자 조건 추가 
				
				return true;
			} else {
				return false;
			}
		}
	</script>
<table border="0" cellpadding="2" cellspacing="0" class="table">	
<form action="\${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
<c:forEach items="${columns}" var="item"><c:if test="${!empty(item.pk)}">  <input type="hidden" name="${fn:toLowerCase(item.name)}" value="">
</c:if></c:forEach>  <input type="hidden" name="cmd" value="">
<c:forEach items="${columns}" var="item">  <tr>
    <th class="th" nowrap>${(item.nullable=="Y")?"":"<span id='lbl_required'>*</span>"}${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}</th> 
    <td class="td"><input type="text" name="${fn:toLowerCase(item.name)}" size="20" maxlength="${item.dlength}" value="\${item.${fn:toLowerCase(item.name)}}"
        hname="${empty(item.comments)?fn:toLowerCase(item.name) : mhtml:null2nbsp(item.comments)}" ${(item.nullable=="Y")?"":"required"} ${(item.type=="NUMBER")?"option='number'":""} ></td>
  </tr>
</c:forEach>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="저장"><input type="reset" value="취소"></td>
	</tr>
</form>
</table></textarea></td>

	</tr>
	<tr>
		<th><a href="javascript:form_copy('frmlist')"> [목록 소스복사]</a></th>
	<tr>	
		<td><textarea cols="80" rows="25" name="frmlist">
===================================================	
&lt;SCRIPT LANGUAGE="JavaScript"&gt;
&lt;!--
	function doWrite(){
	    var frm = getObject("myform");
			if(frm) {
		    frm.cmd.value = "update";
		    frm.submit();
			}
	}

//--&gt;
&lt;/SCRIPT&gt;
	
&lt;form action="\${control_action}" method="post" name="myform" id="myform"&gt;
	&lt;input type="hidden" name="v_page" value="\${navigator.currentPage}"&gt;
	&lt;input type="hidden" name="cmd" value=""&gt;
	&lt;table width="100%" border="0" cellspacing="1" cellpadding="0" class="table"&gt;
    &lt;tr&gt;
      <c:forEach items="${columns}" var="item">&lt;td class="th" align=center &gt;${mhtml:null2nbsp(item.comments)}&lt;/td&gt;
      </c:forEach>				
    &lt;/tr&gt;
 &lt;c:forEach var="item" items="\${list}" varStatus="status"/&gt;
    &lt;tr&gt;
       <c:forEach items="${columns}" var="item">&lt;td class="td" align=center&gt;\${item.${fn:toLowerCase(item.name)}}&lt;/td&gt;
       </c:forEach>
     &lt;/tr&gt;
&lt;/c:forEach&gt;
    &lt;/table&gt;
    
&lt;/form&gt;

&lt;tr&gt;
	&lt;td colspan="6" class="td01_tl" align="center"&gt;&lt;a href="javascript:doWrite()"&gt;[등록]&lt;/a&gt;&lt;br&gt;
		&lt;jsp:include page="/xadmin/_layout/navigator.jsp" flush="true"/&gt;&lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;


&lt;form action="${control_action}" method="post" name="frm_navi" id="frm_navi"&gt;
    &lt;input type="hidden" name="v_page" value="${navigator.v_page}"&gt;
    &lt;input type="hidden" name="cmd" value="list"&gt;
&lt;/form&gt;
=============================================================
		</textarea><td>
	</tr>
	</form>
</table>
