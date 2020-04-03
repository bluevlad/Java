<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<title></title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<link rel="StyleSheet" type="text/css" href="<c:url value="/resources/libs/dtree/dtree.css" />">
<script type="text/javascript" src="<c:url value="/resources/libs/dtree/dtree.js" />"></script>
<script language="JavaScript">
	function OpenMWin(url,w,h)
	{
	    var theDes = "status:no;center:yes;help:no;minimize:no;maximize:no;dialogWidth:"+w+"px;scroll:no;dialogHeight:"+h+"px;border:think";
	    return self.showModalDialog(url,null,theDes);
	}
	function Selector(type, val, ModeID, all)
	{
		val = val?val:'';
		ModeID = ModeID?ModeID:'';
		all = all?all:'';
		return OpenMWin("ACT.D.asp?r=" + Math.random() + "&Type=" + type + "&IdList=" + val + "&ModeID=" + ModeID + "&ShowAll=" + all, 450, 350);
	}
	function GetCheckBoxList(objName)
	{
		var result = "";
		var coll=document.all.item(objName)
		if(!coll) return result;
		if(coll.length){
			for(var i=0;i<coll.length;i++)
			{
				if(coll.item(i).checked)
				{
					result += (result == "")?coll.item(i).value:("," + coll.item(i).value);
				}
			}
		}else{
			if(document.all.item(objName).checked)
			{
				result = document.all.item(objName).value;
			}
		}
		return result;
	}
	
	function GetRadioBox(objName)
	{
	    var Coll = document.all.item(objName);
		if(!Coll) return null;
		if(Coll.length)
		{
			for(var i=0;i<Coll.length;i++)
			{
				if(Coll.item(i).checked)
				{
					return Coll.item(i).value;
				}
			}
			return null;
		}else{
			return Coll.checked?Coll.value:null;
		}
	}
</script>
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td bgcolor="#D6D3CE">
    		<table width=100% border=0 cellspacing=0>
    			<tr>
    				<td>Folder &nbsp;&nbsp;</td>
    				<td align=right><a href="javascript: d.openAll();"><img src="<c:url value="/resources/libs/dtree//img/open_all.gif" />" border=0 alt="open all" align="absmiddle"></a>
    			 		<a href="javascript: d.closeAll();"><img src="<c:url value="/resources/libs/dtree//img/close_all.gif" />" border=0 alt="close all" align="absmiddle"></a>
    			 		<a href="javascript:location.reload();"><img src="<c:url value="/resources/libs/dtree//img/refresh.gif" />" border=0 alt="page refresh" align="absmiddle"></a>
    			 		&nbsp;&nbsp;
    			 	</td>
    			</tr>
    		</table>
        </td>
    </tr>
    <tr>
    	<td>
    	<div class="dtree">
			<!-- <p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p> -->
		
			<script type="text/javascript">
			 	d = new dTree('d');

			 	function menuUpdate(menu_id){
					$(parent.document).find("#frmMenuEdit").attr("src" ,  '<c:url value="/adminManagementMenu/menuDetail.frm"/>?MENU_ID='+menu_id+'&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}');
				}	
						
				var menuList = [];
				<c:forEach var="list" items="${menuList}" varStatus="status">
					d.add("${list.MENU_ID}","${list.P_MENUID}","${list.MENU_NM}[${list.MENU_ID}]" ,"javascript:menuUpdate('${list.MENU_ID}');");
				</c:forEach>

				document.write(d);
			</script>
		</div>
    	</td>
    </tr>
</table>
</body>
</html>