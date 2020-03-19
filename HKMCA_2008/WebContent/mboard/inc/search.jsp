<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

	<tr>
		<td>
            <div class="searchContainer">
			<table width="100%" cellspacing="1" cellpadding="1" border="0">
				<tr>
					<td>
						<mf:form method="post" action="${controlaction}" name="frmSrch" id="frmSrch" onSubmit="MBoard_Search();return false;">
						<table border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td>
		                            <select name="v_key" id="v_key"	onchange="javascript:doSelcatKey(this);">
									<mf:option value="X" curValue="${v_key}" >-</mf:option>
                                    <mf:option value="WNAME" curValue="${v_key}" ><mfmt:message bundle="board" key="label.writer" /></mf:option>
									<mf:option value="SUBJECT" curValue="${v_key}" ><mfmt:message bundle="board" key="label.title" /></mf:option>
                                    <mf:option value="CONTENT" curValue="${v_key}" ><mfmt:message bundle="board" key="label.contents" /></mf:option>
		    						</select>
		                        </td>
								<td><mf:input type="text" name="v_srch" value="${v_srch}" size="15" maxlength="40"/></td>
								<td><mf:button onclick="MBoard_Search()" bundle="button" key="search" /></td>
							</tr>
						</table>
						</mf:form>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
