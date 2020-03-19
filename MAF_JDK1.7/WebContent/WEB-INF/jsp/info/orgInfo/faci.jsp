<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<jsp:include page="_view.jsp"/>
<script language="javascript">
    function viewPhoto(img) {
        popupWindow('<mh:out value="${CPATH}"/>/web_src/viewImg.jsp?img='+img, "photo", 'no');
    }
</script>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " >
        <col width="50"/>          
        <col width="*"/>          
        <col width="200"/>          
        <col width="200"/>          
        <col width="200"/>          
        <thead>
            <tr>
                <th>No.</th>
		    	<th>#</th>
                <th><mf:header name="faci_name"/></th>            
                <th><mf:header name="faci_purpose"/></th>          
                <th><mf:header name="faci_area"/></th>  
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr   >
               <td class="center"><mh:out value="${status.count}"/></td>
				<td class="center">
	        		<mf:input type="hidden" name="v_org_file_name" value="${item.faci_filename}"/>
					<img src="<mh:out value="${CPATH}"/>/pds/org/<mh:out value="${item.faci_filename}"/>" height="50" onclick="javascript:viewPhoto('<mh:out value="${item.faci_filename}"/>');" onError="this.src='<mh:out value="${CPATH}"/>/images/global/no_photo.gif'">
				</td>
                <td class='center'><mh:out value="${item.faci_name}" td="true"/></td> 
                <td align="center"><mh:out value="${item.faci_purpose}" td="true" /></td>
                <td align="center"><mh:out value="${item.faci_area}"  td="true" /></td>
            </tr>
        </c:forEach>
        </tbody>
        </table>


        
        
        


