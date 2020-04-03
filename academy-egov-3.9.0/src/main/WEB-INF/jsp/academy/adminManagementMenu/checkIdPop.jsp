<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>

<div id="wrap">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    			<tr>
	    				<td height="25" align="right"  class="tit_black">
	    				    ::: 처리결과 :::
	    				</td>
	    			</tr>
	    		</table>
			</td>
		</tr>
		<tr>
			<td height="2" class="line"></td>
		</tr>
	</table>
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="2%"></td>
			<td width="96%">
	
	    		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	    			<tr>
	    				<td height="30">
	        				<table width="60%" border="0" cellspacing="0" cellpadding="0">
	        					<tr>
	        						<td width="20" align="center"><%-- <img src="<bean:message key="context.image"/>/my_adm/tit_icon.gif" width="5" height="12"> --%></td>
	        						<td width="461" class="sub_tit">결과메세지</td>
	        					</tr>
	        				</table>
	    				</td>
	    			</tr>
	            </table>
			    <table width=100% border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td height="2" class="bg1"></td>
					</tr>
					<tr>
						<td valign="top" class="bg2">
	                        <table width=100% border=0 cellpadding="0" cellspacing=1>
	                            <tr>
	                                <td align="center" class=list bgcolor=#ffffff>
	                                    <br><br><span class="coun"><c:out value='${result.message}'/></span><br><br></td>
	                            </tr>
	                        </table>
	    				</td>
	    			</tr>
	            </table>
	            <br>
	    		<table width=100% cellpadding=5 cellspacing=0 border=0>
	    		    <tr>
	    			    <td align=center>
	    			    	<input type="button" onClick="<c:out value='${result.script}'/>" class="btn04" value="확인">
	                    </td>
	                </tr>
	            </table>
	        </td>
	        <td width="2%">&nbsp;</td>
	    </tr>
	</table>
</div>
<!--//content --> 

</html>