function Research (tbody_name, seq, name) {
	this._name = name;
	this._tbody = document.all.namedItem (tbody_name);
	this._seq = seq;
}

Research.prototype.doResearchInsert = function () {
    var itemStr;

	var research_no = 1;

	for (i = 0; i < this._tbody.rows.length; i++) {
    	if (this.getTypevalue (i) != 5) { 
    		research_no++;
    	}
	}

    oRow = this._tbody.insertRow();
    

    itemStr = "<span id='RESEARCH_NO'>" + this._seq + "</span>";
    Research.commCellInsert (oRow, itemStr,  "center", "p2", "", "", "", "", "", "");
    

    itemStr =   "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
    itemStr += "    <tr>";
    itemStr += "        <td class='p2' style='padding:5px;'>";
    itemStr += "            <textarea name='question' rows=3 style='width:100%;' onkeyup='javascript:Research.chkLength(this, document.all.a_" + this._seq + ");' maxlength='500' required hname='????'></textarea>";
    itemStr += "						<input type='hidden' name='bank_id' value=''>"
    itemStr += "        </td>";
    itemStr += "        <td width='80' class='p2'>";
    itemStr += "            &nbsp<span id=a_" + this._seq + ">0</span>자/500자";
    itemStr += "        </td>";
    itemStr += "    </tr>";
    itemStr += "</table>";
    itemStr += "<table id='ITEMID_" + this._seq + "' width='100%' border='0' cellspacing='0' cellpadding='0' style='display:'>";
    itemStr += "	  <input type='hidden' name='seq' value='" + this._seq + "'/>";
    itemStr += "    <tr>";
    itemStr += "        <td class='p2' style='padding:5px;'>";
    itemStr += "            <table width='100%' border='0' cellspacing='1' cellpadding='0'>";
    itemStr += "                <tbody id='ITEM_BODY_" + this._seq + "'>";
    itemStr += "                <tr>";
    itemStr += "                    <td class='p2'>";
    itemStr += "                        1. <input type='text' name='reply_"+ this._seq +"' style='width:85%' maxlength='500' value=''/> <input type='text' name='score_"+ this._seq +"' style='width:10%' maxlength='2' value=''/>";
    itemStr += "                    </td>";
    itemStr += "                </tr>";
    itemStr += "                <tr>";
    itemStr += "                    <td class='p2'>";
    itemStr += "                        2. <input type='text' name='reply_"+ this._seq +"' style='width:85%' maxlength='500' value=''/> <input type='text' name='score_"+ this._seq +"' style='width:10%' maxlength='2' value=''/>";
    itemStr += "                    </td>";
    itemStr += "                </tr>";
    itemStr += "                </tbody>";
    itemStr += "            </table>";
    itemStr += "        </td>";
    itemStr += "        <td valign='top' style='padding:5px;' width='50' align='right'>";
    itemStr += "            <a href='javascript:Research.doItemInsert(" + this._seq + ");'>";
    itemStr += "                항목추가</a>";
    itemStr += "            <a href='javascript:Research.doItemDelete(" + this._seq + ");'>";
    itemStr += "                항목삭제</a>";
    itemStr += "        </td>";
    itemStr += "    </tr>";
    itemStr += "</table>";
    Research.commCellInsert (oRow, itemStr,  "left", "p2", "", "", "", "", "", "");
		itemStr = "기타여부<br><select name='etc_yn'>";
    itemStr += "<option value='1'>사용</option>";
    itemStr += "<option value='0'>미사용</option>";
    itemStr += "</select><br>";
    itemStr += "Comment<br><select name='comment_yn'>";
    itemStr += "<option value='1'>사용</option>";
    itemStr += "<option value='0'>미사용</option>";
    itemStr += "</select><br>";
    itemStr += "<select name='que_type' id='TYPE_" + this._seq + "' onChange='javascript:" + this._name + ".doShowType (this, " + this._seq + ");'>";
    itemStr += "<option value='1'>N지선다형</option>";
    itemStr += "<option value='2'>다중선택</option>";
    itemStr += "<option value='3'>단답형</option>";
    itemStr += "<option value='4'>서술형</option>";
    itemStr += "</select>";
    
    itemStr += "<p>";
    
    itemStr += "<a href='javascript:" + this._name + ".doUp(" + this._seq + ");'>위로</a>\n";
    itemStr += "<a href='javascript:" + this._name + ".doDown(" + this._seq + ");'>아래로</a><br>\n";
    itemStr += "<a href='javascript:" + this._name + ".doOpen(" + this._seq + ", \"\");'>열기</a>\n";
    itemStr += "<a href='javascript:" + this._name + ".doOpen(" + this._seq + ", \"none\");'>닫기</a>\n";
    itemStr += "<a href='javascript:" + this._name + ".doDelete(" + this._seq + ");'>삭제</a>";
    Research.commCellInsert (oRow, itemStr,  "left", "p2", "", "5px", "top", "", ""," ");
    
    this._seq++;
}


Research.doItemInsert = function (num) { 
    eval ("var tbodyObj = document.all.ITEM_BODY_" + num + ";");
    var itemStr;

    oRow = tbodyObj.insertRow();
    var row = tbodyObj.rows;

    itemStr =  row.length + ". <input type='text' name='reply_"+num+"' style='width:85%' maxlength='500' value=''/> <input type='text' name='score_"+num+"' style='width:10%' maxlength='2' value=''/>";
    Research.commCellInsert (oRow, itemStr,  "left", "p2", "", "", "", "", "","");
}

Research.doItemDelete = function (num) { 
    eval ("var tbodyObj = document.all.ITEM_BODY_" + num + ";");
    var rows = tbodyObj.rows;
    if (rows.length > 2)
        tbodyObj.deleteRow (rows.length - 1);
}

Research.prototype.doShowType = function (obj, num) {
    eval ("var id = document.all.ITEMID_" + num + ";");
    if (obj.value == 3 || obj.value == 4 || obj.value == 5) {
       this.doDisplay (id, "none");
    }else{
       this.doDisplay (id, "");
    }
    
    this.doQuestionNumCompute();
}

Research.prototype.doOpen = function (num, show) {
    eval ("var checkObj = document.all.TYPE_" + num + ";");
    eval ("var obj = document.all.ITEMID_" + num + ";");

    if (checkObj.value != 3 || checkObj.value != 4 || checkObj.value != 5) {
        this.doDisplay (obj, show);
    }
}

Research.prototype.doDisplay = function (obj, show) {
    obj.style.display = show;
}

Research.prototype.doUp = function (num) {
    eval ("var obj = document.all.ITEMID_" + num + ";");
    
    var curObj = obj.parentElement.parentElement;
    var rows = this._tbody.rows;
    for (i = 1; i < rows.length; i++ ){
        if (rows[i] == curObj) {
            var col = rows[i - 1].cells;
            
            var st = ( this.getTypevalue (i) == 5 || this.getTypevalue (i - 1) == 5 ) ? 0 : 1;
            for (i = st; i < col.length; i++) {
                var tmp = curObj.cells[i].innerHTML;
                curObj.cells[i].innerHTML = col[i].innerHTML;
                col[i].innerHTML = tmp;
            }
            return;
        }
    }
}

Research.prototype.doDown = function (num) {
    eval ("var obj = document.all.ITEMID_" + num + ";");
    
    var curObj = obj.parentElement.parentElement;
    var rows = this._tbody.rows;

    for (i = rows.length - 2; i >= 0; i-- ){
        if (rows[i] == curObj) {
            var col = rows[i + 1].cells;
            var st = ( this.getTypevalue (i) == 5 || this.getTypevalue (i + 1) == 5 ) ? 0 : 1;
            for (i = st; i < col.length; i++) {
                var tmp = curObj.cells[i].innerHTML;
                curObj.cells[i].innerHTML = col[i].innerHTML;
                col[i].innerHTML = tmp;
            }
            return;
        }
    }
}

Research.prototype.doDelete = function (num) {
    eval ("var obj = document.all.ITEMID_" + num + ";");
    var rows = this._tbody.rows;
    for (i = 0; i < rows.length; i++ ){
        if (rows[i] == obj.parentElement.parentElement) {
            this._tbody.deleteRow (i);
            break;
        }
    }
    
    this.doQuestionNumCompute ();
}

Research.prototype.doQuestionNumCompute = function () {
    var col = document.all.RESEARCH_NO;
    if (col != null) {
        if (col.length == null) {
            col.innerHTML = (i + 1);
        }else{
        	var d = 0;
            for (i = 0; i < col.length; i++) {

	            if (document.all[i].tagName == 'SELECT') {
	            	if (document.all[i].options[document.all[i].selectedIndex].value == 5) {
	            		d--;
	            		col[i].innerHTML = '';
	            	}else{
		                col[i].innerHTML = (i + 1) + d;
		            }
		        }
            }
        }
    }
}

Research.prototype.getQuestionCount = function () {
    var col = document.all.RESEARCH_NO;
    if (col != null) {
        if (col.length == null)
            return 1;
        else
            return col.length;
    }else{
        return 0;
    }
}

Research.prototype.getTypevalue = function (num) {
	
	if ( typeof (document.all.length) == 'undefined') {
		if (document.all.tagName == 'INPUT') return document.all.value;
		else if (document.all.tagName =='SELECT') return document.all.options [document.all.selectedIndex].value;
	}else{
		if (document.all[num].tagName == 'INPUT') return document.all[num].value;
		else if (document.all[num].tagName =='SELECT') return document.all[num].options [document.all[num].selectedIndex].value;
	}
	
	return 0;
}

Research.commCellInsert = function (rowObj, data, align, className, bgColor, padding, valign, colSpan, height) {
	oCell = rowObj.insertCell();
	oCell.innerHTML = data;
	oCell.align = align ;  
	oCell.className = className;    
	oCell.bgColor = bgColor;    
	oCell.vAlign = valign;    
	oCell.height = height;

	if (padding != "")
		oCell.style.padding = padding;
	
	if (colSpan != "")
		oCell.colSpan = colSpan;
}


Research.chkLength = function (el, a){
    var maxbyte = el.getAttribute("maxlength");
    id_value = eval(a);
	
	if (el.value != "") {
		if (el.value.bytes() > parseInt(maxbyte)) {
			id_value.innerHTML = el.value.bytes();
			return doError(el,"{name}글자수가 초과하였습니다. (총 "+maxbyte+" : byte)");
		}
		else{
			id_value.innerHTML = el.value.bytes();
		}
	}
	else{
		id_value.innerHTML = "0";
	}
}