<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>

<script language="javascript">

	
	function frmSubmit() {
		var frm = document.getElementById("myform");
		if(frm) {
			if(validate(frm)) {
					frm.submit();
			}
		}
	}

	function Photo_Del() {
		var frm = document.getElementById("photodel");
		if(frm) {
					frm.submit();
		}
	}
</script>
<table width="100%" border="0" cellpadding="6" cellspacing="0">	

	<tr>
		<td><table width="600" border="0" cellpadding="6" cellspacing="0" class="view">	
			<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return validate(this)">
				<input type="hidden" name="cust_cd" value="${cust_cd}">
				<input type="hidden" name="remarks" value="">
			  	<input type="hidden" name="cmd" value="step03">
			<col width="150"/>
			<col width="250"/>
			<col width="200"/>
		  <tr>
		    <th class="view" nowrap width="150">이름</th> 
		    <td class="view" colspan="3"><input type="text" name="nm" value="${uinfo.nm}" size="20" maxlength="50"
		        hname="이름" required  readonly></td>
				
		  </tr>
		  <tr>
		    <th class="view" nowrap width="150">영문이름</th> 
		    <td class="view" colspan="3"><input type="text" name="nm_eng" value="${uinfo.nm_eng}" size="20" maxlength="50"
		        hname="영문이름" required  readonly></td>
				
		  </tr>
		  <tr>
		    <th class="view" nowrap>주민등록번호</th> 
		    <td class="view" colspan="3"><input type="text" name="pin" value="${uinfo.pin}" size="20" maxlength="14"
		        hname="주민번호"  readonly ></td>
		  </tr>				
		  <tr>
		    <th class="view" nowrap>아이디(ID)</th> 
		    <td class="view"  colspan="3"><input type="text" name="userid" value="${uinfo.userid}" size="20" maxlength="20"
		        hname="아이디(ID)"   required readonly> </td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>암호</th> 
		    <td class="view"  colspan="3"><input type="password" name="passwd" value="${uinfo.passwd}" size="20" maxlength="32"
		        hname="암호"   match="passwd1" ></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>암호확인</th> 
		    <td class="view"  colspan="3"><input type="password" name="passwd1" value="${uinfo.passwd}" size="20" maxlength="32" hname="암호"   ></td>
		  </tr>
			<c:if test="${cust_cd != 'SU'}">			
				<tr>
				    <th class="view" nowrap>사원번호</th> 
				    <td class="view"  colspan="3"><input type="text"  name="emp_no" size="20" maxlength="20" value="${uinfo.emp_no}"/></td> 
			 	</tr> 

				<tr>
				    <th class="view" nowrap>부서</th> 
				    <td class="view"  colspan="3"><select name="dept_cd" style="width:100px;"></select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>부서장여부</th>
				    <td class="view"  colspan="3"><select name="dept_tm_yn">
				    					<mform:HtmlCodeOptions groupCd="COM_YN" value="${uinfo.dept_tm_yn}"/>
				    				</select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>직위</th> 
				    <td class="view"  colspan="3"><input type="text"  name="pos_nm" size="20" maxlength="50" value="${uinfo.pos_nm}"/></td>
			 	</tr> 
			</c:if>


		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>전화번호</th> 
		    <td class="view" colspan="3"><input type="text" name="tel" value="${uinfo.tel}" size="15" option="phone" required maxlength="15" hname="전화번호" > </td>
		  </tr>
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>휴대폰번호</th> 
		    <td class="view" colspan="3"><input type="text" name="hp" value="${uinfo.hp}" size="15"  option="phone" required maxlength="15" hname="휴대폰번호"   ></td>
		  </tr>
			<tr>
			    <th class="view" nowrap>Fax</th> 
			    <td class="view" colspan="3"><input type="text" name="fax" size="20" maxlength="20" value="${uinfo.fax}"/></td> 
		 	</tr> 

		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>e-mail</th> 
		    <td class="view" colspan="3"><input type="text" name="email" value="${uinfo.email}" size="50" maxlength="100"
		        hname="e-mail" option="email"  required ></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>우편번호</th> 
		    <td class="view" colspan="3"><input type="text" name="zip" value="${uinfo.zip}" size="8" maxlength="7"
		        hname="우편번호"  readonly required> <a href="javascript:ZipCodeSeach('myform','post=zip&addr1=addr1&addr2=addr2');"><img src="${CPATH}/images/www/member/btn_search02.gif" alt="" border="0" align="absmiddle"></a></td>
		  </tr>
			<tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>주소</th> 
		    <td class="view" colspan="3"><input type="text" name="addr1" value="${uinfo.addr1}" size="50" maxlength="100"
		        hname="주소"   readonly required></td>
		  </tr>
			
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>주소 나머지</th> 
		    <td class="view" colspan="3"><input type="text" name="addr2" value="${uinfo.addr2}" size="50" maxlength="100"
		        hname="주소 나머지"  required ></td>
		  </tr>


		</table></td>

	</tr>
	<tr>
		<td align="center"><a href="javascript:frmSubmit();"><img src="${CPATH}/images/www/member/btn_save.gif" alt="" border="0" align="middle"></a>
				 <a href="${CPATH}"><img src="${CPATH}/images/www/member/btn_cancel.gif" alt="" border="0" align="middle"></a>
		</td>
	</tr>
				</tr>
				</form>
	
</table>
<c:if test="${cust_cd != 'SU'}">			
	<script language="javascript">
	
	
		function changeDept() {
	    	var frm = $("myform");
			var cust_cd = frm.cust_cd.value;
			
			//기존의 목록 clear
			for (i=0; i<frm.dept_cd.options.length; i++ ) { 
				frm.dept_cd.options[i] = null; 
			}	
			frm.dept_cd.options.length = 0 ;
			
			//frm.dept_cd.options[0] = new Option('===============', '', false, false) ;
			var i = 0;
			<c:forEach var="dept" items="${department}" varStatus="status"> 
				if(cust_cd == '${dept.cust_cd}') {
					frm.dept_cd.options[i] = new Option('${dept.dept_nm_kor}', '${dept.dept_cd}', false, false);
					if('${dept.dept_cd}' == '${uinfo.dept_cd}') {
						frm.dept_cd.selectedIndex = i ;	
					}
					i++;
				}
			</c:forEach>          	 
		}    
	    //doChange();
	    changeDept();
	
	</script>
</c:if>
