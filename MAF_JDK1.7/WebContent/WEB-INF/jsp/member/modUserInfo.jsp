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
		    <th class="view" nowrap width="150">�̸�</th> 
		    <td class="view" colspan="3"><input type="text" name="nm" value="${uinfo.nm}" size="20" maxlength="50"
		        hname="�̸�" required  readonly></td>
				
		  </tr>
		  <tr>
		    <th class="view" nowrap width="150">�����̸�</th> 
		    <td class="view" colspan="3"><input type="text" name="nm_eng" value="${uinfo.nm_eng}" size="20" maxlength="50"
		        hname="�����̸�" required  readonly></td>
				
		  </tr>
		  <tr>
		    <th class="view" nowrap>�ֹε�Ϲ�ȣ</th> 
		    <td class="view" colspan="3"><input type="text" name="pin" value="${uinfo.pin}" size="20" maxlength="14"
		        hname="�ֹι�ȣ"  readonly ></td>
		  </tr>				
		  <tr>
		    <th class="view" nowrap>���̵�(ID)</th> 
		    <td class="view"  colspan="3"><input type="text" name="userid" value="${uinfo.userid}" size="20" maxlength="20"
		        hname="���̵�(ID)"   required readonly> </td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>��ȣ</th> 
		    <td class="view"  colspan="3"><input type="password" name="passwd" value="${uinfo.passwd}" size="20" maxlength="32"
		        hname="��ȣ"   match="passwd1" ></td>
		  </tr>
		  <tr>
		    <th class="view" nowrap>��ȣȮ��</th> 
		    <td class="view"  colspan="3"><input type="password" name="passwd1" value="${uinfo.passwd}" size="20" maxlength="32" hname="��ȣ"   ></td>
		  </tr>
			<c:if test="${cust_cd != 'SU'}">			
				<tr>
				    <th class="view" nowrap>�����ȣ</th> 
				    <td class="view"  colspan="3"><input type="text"  name="emp_no" size="20" maxlength="20" value="${uinfo.emp_no}"/></td> 
			 	</tr> 

				<tr>
				    <th class="view" nowrap>�μ�</th> 
				    <td class="view"  colspan="3"><select name="dept_cd" style="width:100px;"></select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>�μ��忩��</th>
				    <td class="view"  colspan="3"><select name="dept_tm_yn">
				    					<mform:HtmlCodeOptions groupCd="COM_YN" value="${uinfo.dept_tm_yn}"/>
				    				</select></td>
			 	</tr> 
				<tr>
				    <th class="view" nowrap>����</th> 
				    <td class="view"  colspan="3"><input type="text"  name="pos_nm" size="20" maxlength="50" value="${uinfo.pos_nm}"/></td>
			 	</tr> 
			</c:if>


		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>��ȭ��ȣ</th> 
		    <td class="view" colspan="3"><input type="text" name="tel" value="${uinfo.tel}" size="15" option="phone" required maxlength="15" hname="��ȭ��ȣ" > </td>
		  </tr>
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�޴�����ȣ</th> 
		    <td class="view" colspan="3"><input type="text" name="hp" value="${uinfo.hp}" size="15"  option="phone" required maxlength="15" hname="�޴�����ȣ"   ></td>
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
		    <th class="view" nowrap><span id='lbl_required'>*</span>�����ȣ</th> 
		    <td class="view" colspan="3"><input type="text" name="zip" value="${uinfo.zip}" size="8" maxlength="7"
		        hname="�����ȣ"  readonly required> <a href="javascript:ZipCodeSeach('myform','post=zip&addr1=addr1&addr2=addr2');"><img src="${CPATH}/images/www/member/btn_search02.gif" alt="" border="0" align="absmiddle"></a></td>
		  </tr>
			<tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�ּ�</th> 
		    <td class="view" colspan="3"><input type="text" name="addr1" value="${uinfo.addr1}" size="50" maxlength="100"
		        hname="�ּ�"   readonly required></td>
		  </tr>
			
		  <tr>
		    <th class="view" nowrap><span id='lbl_required'>*</span>�ּ� ������</th> 
		    <td class="view" colspan="3"><input type="text" name="addr2" value="${uinfo.addr2}" size="50" maxlength="100"
		        hname="�ּ� ������"  required ></td>
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
