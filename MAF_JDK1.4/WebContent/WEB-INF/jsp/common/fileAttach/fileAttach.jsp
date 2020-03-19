<%@ page contentType="text/html;charset=utf-8" %> 
<%
    String FILEID = request.getParameter("FILEID");
    if(FILEID == null || FILEID == "") {
    	FILEID = "attach_file";
    }   

%>
<script language=javascript>

var scripts = new Array;

function make_array(status, display_script) {
	this.status = status;
	this.display_script = display_script;
}

function attach(obj) {
	var val = obj.value;
	var idx = obj.name.substring('<%=FILEID%>'.length);
	obj.style.display = 'none';
	
	add_item(++idx, val);	
	item_list();
}

function add_item(idx, val) {
	var seq = scripts.length;
	var display_script = '<span id=display_item'+idx+'>'+val+' <b onclick=remove_item('+seq+') style=cursor:pointer>remove..</b></span><br>';
	var file_script = '<span id=file_item'+idx+'><input type=file name=<%=FILEID%>'+idx+' id=<%=FILEID%>'+idx+' onchange=attach(this) size=50 style=cursor:pointer></span>';	
	scripts[seq] = new make_array(true, display_script);
	
	document.getElementById('file_items').insertAdjacentHTML("afterEnd", file_script);
}

function item_list() {
	var validate_cnt = 0;
	var display_scripts = '';

	for (var i = 0; i < scripts.length; i++) {
		if (scripts[i].status){
			validate_cnt++;
			display_scripts += '<b>'+validate_cnt+'</b>.'+scripts[i].display_script;
		}
	}
	
	if (validate_cnt == 0){
		display_scripts = 'No files..';
    }
	document.getElementById('display_items').innerHTML = display_scripts;
}

function remove_item(seq) {
	scripts[seq].status = false;
	document.getElementById('file_item'+(seq+1)).innerHTML = '';
	item_list();
}

function preprocessing() {
	var idx = scripts.length + 1;
	document.getElementById('file_item'+idx).innerHTML = '';
}

/*  Netscape/Mozilla에서 insertAdjacentHTML을 emulation하는 스크립트 
 *  참고 사이트 http://forums.mozilla.or.kr/viewtopic.php?t=678, http://www.faqts.com/knowledge_base/view.phtml/aid/5756
**/
if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement){
	HTMLElement.prototype.insertAdjacentElement = function(where,parsedNode){
		switch (where){
			case 'beforeBegin':
			this.parentNode.insertBefore(parsedNode,this)
			break;
			case 'afterBegin':
			this.insertBefore(parsedNode,this.firstChild);
			break;
			case 'beforeEnd':
			this.appendChild(parsedNode);
			break;
			case 'afterEnd':
			if (this.nextSibling) this.parentNode.insertBefore(parsedNode,this.nextSibling);
			else this.parentNode.appendChild(parsedNode);
			break;
		}
	}

	HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr) {
		var r = this.ownerDocument.createRange();
		r.setStartBefore(this);
		var parsedHTML = r.createContextualFragment(htmlStr);
		this.insertAdjacentElement(where,parsedHTML)
	}
	
	
	HTMLElement.prototype.insertAdjacentText = function(where,txtStr){
		var parsedText = document.createTextNode(txtStr)
		this.insertAdjacentElement(where,parsedText)
	}
}
</script>  
<fieldset style=padding:5px;background-color:#eeeeee;font-size:9pt;>
<div id=display_items>No files..</div>
</fieldset>
<div style='width:100;height:16;overflow:hidden;position:relative;top:0px;'>
 <div id=file_items></div>
 <span id=file_item1><input type="file" name="<%=FILEID%>1" id="<%=FILEID%>1" onchange="attach(this)" size="50" style="cursor:pointer;"></span>
</div>
