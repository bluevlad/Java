<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>

<script language="javascript">
	function frmSubmit() {
		var frm = document.getElementById("myform");
		if(frm) {
			if(validate(frm)) {
				if(confirm("Ȯ���� �����ø� SUN ȸ���� ���� �Ͻð� �˴ϴ�.")) {
					frm.submit();
				}
			}
		}
	}
	function country_chk(obj)
	{
		if(obj.value!="kr") {
			alert("�ܱ����� ��� �� �������Ϳ� ���ǹٶ��ϴ�.\n\n������ȭ:080-007-7800");
			obj.value = "kr";
			obj.focus();
		}
	}



</script>
<table width="95%" border="0" cellpadding="6" cellspacing="0">	

	<tr>
		<td><table width="600" border="0" cellpadding="6" cellspacing="0" class="view">	
			<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return validate(this)">
				<input type="hidden" name="cmd" value="step03">
				<input type="hidden" name="cust_cd" value="${cust_cd}">
				<input type="hidden" name="remarks" value="">
		  <tr>
		    <th class="view" nowrap width="150">����</th> 
		    <td class="view"><select name="country" onChange="country_chk(this)">
				<mform:HtmlCodeOptions groupCd="COUNTRY" />
			</select></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap width="150">�̸�</th> 
		    <td class="view"><input type="text" name="nm" value="${TEMP_FORM.nm}" size="20" maxlength="50"
		        hname="�̸�" required  readonly></td>
		  </tr>		  
			<tr>
			    <th class="view" nowrap>�����̸�</th> 
			    <td class="view"><input type="text"  name="nm_eng" size="20" maxlength="100" value=""/></td> 
		 	</tr>
		
		  <tr>
		    <th class="view" nowrap>�ֹε�Ϲ�ȣ</th> 
		    <td class="view"><input type="text" name="pin" value="${TEMP_FORM.pin_1}-${TEMP_FORM.pin_2}" size="20" maxlength="14"
		        hname="�ֹι�ȣ"  readonly ></td>
		  </tr>				
		  <tr>
		    <th class="view" nowrap>���̵�(ID)</th> 
		    <td class="view"><input type="text" name="userid" value="" size="20" maxlength="20"
		        hname="���̵�(ID)"  option="userid" required readonly> <a href="javascript:popupWindow('${CPATH}/member/chekoverlap.do');"><img src="${CPATH}/images/www/member/btn_confirm_overlap.gif" alt="" border="0" align="middle"></a><BR style="FONT-SIZE: 4px">
						<span id="lbl_comment">5~15���� �����ҹ���, ����, '_', '-' �� �����մϴ�.</span></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>��ȣ</th> 
		    <td class="view"><input type="password" name="passwd" value="" size="20" maxlength="32"
		        hname="��ȣ"   match="passwd1" required></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>��ȣȮ��</th> 
		    <td class="view"><input type="password" name="passwd1" value="" size="20" maxlength="32" hname="��ȣ"   required></td>
		  </tr>
			<c:if test="${cust_cd != 'SU'}">			
				<tr>
				    <th class="view" nowrap>�����ȣ</th> 
				    <td class="view"><input type="text"  name="emp_no" size="20" maxlength="20" value=""/></td> 
			 	</tr> 

				<tr>
				    <th class="view" nowrap>�μ�</th> 
				    <td class="view"><select name="dept_cd" style="width:100px;"></select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>�μ��忩��</th>
				    <td class="view"><select name="dept_tm_yn">
				    					<mform:HtmlCodeOptions groupCd="COM_YN" value=""/>
				    				</select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>����</th> 
				    <td class="view"><input type="text"  name="pos_nm" size="20" maxlength="50" value=""/></td>
			 	</tr> 
			</c:if>

		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>��ȭ��ȣ</th> 
		    <td class="view"><input type="text" name="tel_home1" value="" size="3" span="3" option="phone" required maxlength="3" hname="��ȭ��ȣ"   > -
						<input type="text" name="tel_home2" value="" size="4" maxlength="4" hname="��ȭ��ȣ" required> -
						<input type="text" name="tel_home3" value="" size="4" maxlength="4" hname="��ȭ��ȣ" required></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�޴�����ȣ</th> 
		    <td class="view"><input type="text" name="mobile1" value="" size="3" span="3" option="phone" required maxlength="3" hname="�޴�����ȣ"   > -
						<input type="text" name="mobile2" value="" size="4" maxlength="4" hname="�޴�����ȣ"  required > -
						<input type="text" name="mobile3" value="" size="4" maxlength="4" hname="�޴�����ȣ"  required ></td>
		  </tr>
			<tr>
			    <th class="view" nowrap>Fax</th> 
			    <td class="view"><input type="text" name="fax" size="20" maxlength="20" value=""/></td> 
		 	</tr> 

		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>e-mail</th> 
		    <td class="view"><input type="text" name="email" value="" size="50" maxlength="100"
		        hname="e-mail" option="email"  required ></td>
		  </tr>
		  
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�����ȣ</th> 
		    <td class="view"><input type="text" name="zip" value="" size="8" maxlength="7"
		        hname="�����ȣ"  readonly required> <a href="javascript:ZipCodeSeach('myform','post=zip&addr1=addr1&addr2=addr2');"><img src="${CPATH}/images/www/member/btn_search02.gif" alt="" border="0" align="absmiddle"></a></td>
		  </tr>
			<tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�ּ�</th> 
		    <td class="view"><input type="text" name="addr1" value="" size="50" maxlength="100"  readonly required>
				</td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>�������ּ�</th> 
		    <td class="view" colspan="2"><input type="text"  name="addr2" value="" size="50" maxlength="100" /></td>
		  </tr> 	



		</table></td>
	</tr>
	<tr>
		<td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><img src="${CPATH}/images/www/member/tit_confirm.gif" alt="" border="0"></td>
					<td align="right"></td>
				</tr>
		</table></td>
	</tr>			
	<tr>
		<td><table width="600" border="0" cellpadding="6" cellspacing="0" class="view">		
			  <tr>
			    <th class="view" nowrap  width="150">SUN �ҽĸ���</th> 
			    <td class="view"><input type="radio" name="ad_email01" style="border:none" value="Y" checked>���� 
					<input type="radio" style="border:none" name="ad_email01" value="N">�������� ���� </td>
			  </tr>
			  <tr>
			    <th class="view" nowrap>SUN �ҽ�SMS</th> 
			    <td class="view"><input type="radio" name="ad_sms01" style="border:none" value="Y" checked>���� 
					<input type="radio" style="border:none" name="ad_sms01" value="N">�������� ���� </td>
			  </tr>
			 </table></td>
	</tr>
	<tr>
		<td align="center"><a href="javascript:frmSubmit();"><img src="${CPATH}/images/www/member/btn_agreement01.gif" alt="" border="0" align="middle"></a>
					 <a href="${CPATH}"><img src="${CPATH}/images/www/member/btn_agreement02.gif" alt="" border="0" align="middle"></a></td>
		</tr>
		</form>
	</tr>
</table>
<c:if test="${cust_cd != 'SU'}">			
	<script language="javascript">
	
	
		function changeDept() {
	    	var frm = $("myform");
			var cust_cd = frm.cust_cd.value;
			
			//������ ��� clear
			for (i=0; i<frm.dept_cd.options.length; i++ ) { 
				frm.dept_cd.options[i] = null; 
			}	
			frm.dept_cd.options.length = 0 ;
			
			//frm.dept_cd.options[0] = new Option('===============', '', false, false) ;
			var i = 0;
			<c:forEach var="dept" items="${department}" varStatus="status"> 
				if(cust_cd == '${dept.cust_cd}') {
					frm.dept_cd.options[i] = new Option('${dept.dept_nm_kor}', '${dept.dept_cd}', false, false);
					if('${dept.dept_cd}' == '${item.dept_cd}') {
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