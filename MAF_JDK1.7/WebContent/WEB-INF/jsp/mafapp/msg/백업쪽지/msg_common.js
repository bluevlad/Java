<!--


	function deleteForm(){ 
		var frmchk = document.getElementsByName("msgid_ck");
	
		if(frmchk != null){
			len = frmchk.length;
			cnt = 0;
			if(len == null){
				if(frmchk.checked)
					cnt++;
			}
			else{
				for(i = 0; i < len; i++){
					if(frmchk[i].checked)
						cnt++;
				}
			}
	
			if(cnt <= 0)
				alert("������ ������ �����ϼ���");
				//alert("<fmt:message bundle = "${a}" key = "msgalt.delmsg"/>");
			else{					
				if(confirm("���� ���� �Ͻðڽ��ϱ�?")){
					frm.submit();
				}
			}
		}
	} 
	
		function addrDelete(){ 
		var frmchk = document.getElementsByName("c_usn_ck");
	
		if(frmchk != null){
			len = frmchk.length;
			cnt = 0;
			if(len == null){
				if(frmchk.checked)
					cnt++;
			}
			else{
				for(i = 0; i < len; i++){
					if(frmchk[i].checked)
						cnt++;
				}
			}
	
			if(cnt <= 0)
				alert("������ ����� �����ϼ���");
			else{					
				if(confirm("���� ���� �Ͻðڽ��ϱ�?")){
					delfrm.submit();
				}
			}
		}
	} 

	function delfrm(){ 
	
		if(confirm("���� ���� �Ͻðڽ��ϱ�?")){
			frm.submit();
	    }
				
	
	} 
	
	function doSelcat (obj) {
	    var txt = document.getElementById("category");
	    if(txt && obj) {
	    	txt.value = obj.value;
	    	if(txt.style) {
			    if (obj.value == "") {
			        txt.style.display = '';
			    }else{
			        txt.style.display = 'none';
			    }
			}
		}
	}
	
	function popupWindow(theURL) {
  		var oWin = window.open(theURL,"search",'width=10, height=10, scrollbars=no, menubar=no, scrollbars=no, status=yes, titlebar=yes, toolbar=no, location=no, top=0,left=0, resizable=no');
  		oWin.focus();
	}

//-->