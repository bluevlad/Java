function CodeFind(id, schtype, keyword, width, height) {
		var oWin = window.open(CPATH + '/codeFind/codeFind.do?id='+id+'&schtype='+schtype+'&keyword='+keyword,
		                       'find',
		                       'width='+width+',height='+height+',status=yes,menubars=no,scrollbars=yes,top=0px,left=0px');
		windows_focus(oWin);
}

//??
function CodeFind2(id, schtype, keyword, width, height,len) {
		var oWin = window.open(CPATH + '/codeFind/codeFind.do?id='+id+'&schtype='+schtype+'&keyword='+keyword+'&len='+len,
		                       'find',
		                       'width='+width+',height='+height+',status=yes,menubars=no,scrollbars=yes,top=0px,left=0px');
		windows_focus(oWin);
}



// for Tree 
function CodeFindTree(id, width, height) { 
		var oWin = window.open(CPATH + '/codeFind/codeFind.do?cmd=tree&id='+id,
		                       'find',
		                       'width='+width+',height='+height+',status=yes,menubars=no,scrollbars=yes,top=0px,left=0px');
		windows_focus(oWin);
} 

	