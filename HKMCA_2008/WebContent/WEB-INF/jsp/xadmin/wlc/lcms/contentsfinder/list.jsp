<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<style>
    .tth{
        font-size:11px;
        color:#000000;
        text-align:center;
        /*background: #F4F7F8;*/
        height: 25px; 
        padding:2px;
        font-weight: normal;
    }
</style>
<table height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	   <td valign=top>
		   <div id="oList" style="overflow: scroll; width: 550px; height: 470px;">
		   <table border="0" width="100%" cellspacing="1" cellpadding="0">
		       <thead style="background :url(/maf_images/thead/bg_01.gif) repeat-x bottom;">
		       <tr>
		           <th class="tth">file name</th>
		           <th class="tth" align="center">size</th>
		           <th class="tth" align="center">modified date</th>
		        </tr>
                </thead>
                <tbody>
                <c:if test="${upPath != null}">
                <tr>
                    <td class="list_td">
                        <a href='<c:url  value="${control_action}">
						<c:param name="cmd" value="list"/>
						<c:param name="absPath" value="${upPath}"/>
						</c:url>'><img src='<c:url value="/js/dtree/img/up.gif"/>' 
						           alt="Up" border="0" align="absmiddle">[<c:out value="${upPath}"/>]</a>
				    </td>
				</tr>
				</c:if>
				<c:forEach var="list" items="${lists}">
				<tr onmouseover="this.style.backgroundColor='#b6b6b6'" onmouseout="this.style.backgroundColor='#FFFFFF'">
				    <td class="list_td" nowrap width="190">
				        <table border="0" cellspacing="0" cellpadding="0">
				            <tr>
				                <mh:iif var="t" test="${list.directory}" trueValue="folder" falseValue="page"/>
				                <td><img src=<c:url value="/js/dtree/img/${t}.gif"/> border="0" align="absmiddle"></td>
				                <td nowrap class="list_td">
				                    <c:if test="${list.directory}">
				                    <a href='<c:url value="${control_action}">
									<c:param name="cmd" value="list"/>
									<c:param name="absPath" value="${absPath}${list.filename}"/>
									</c:url>'><c:out value="${list.filename}"/></a>
								</c:if>
								<c:if test="${!list.directory}">
								    <a href=javascript:parent.setContents(<c:url value="'${absPath}${list.filename}'"/>)><c:out value="${list.filename}"/></a>
								</c:if>
								</td>
							</tr>
						</table>
					</td>
				    <td class="list_td" align=right nowrap><c:if test="${!list.directory}" ><fmt:formatNumber value="${list.size}" pattern="#,###" />Kb&nbsp;</c:if></td>
				    <td class="list_td" align=center nowrap><fmt:formatDate value="${list.date}" pattern="MM-dd HH:mm" /></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
		</td>
    </tr>
    <tr>
        <td height=1 bgcolor="#D6D3CE"></td>
    </tr>
</table>