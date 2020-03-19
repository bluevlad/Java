<!--
function search(frm){
	if(frm.keyword.value == ""){
		alert("검색어를 입력하세요.");
		frm.keyword.focus();
		return false;
	}
	return true;
}
document.write('<table border="0" cellspacing="0" cellpadding="2" align="right">');
document.write('<form action="" method="post" name="frmsearch" id="frmsearch"  onSubmit="return search(this)">');
document.write('<tr><td><INPUT name="keyword"></td>');
document.write('<td><input type="image" src="/web/images/main/btn_search.gif" align="absmiddle" style="border: 0px none;" tabindex="3">');
document.write('</td></FORM></table>');
//-->