<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(leccode){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "view";
			frm.leccode.value = leccode; 
			frm.submit();
		}
	}
    function doSearch() {
		var frm = getObject("myform");
		if(frm) {
   			frm.cmd.value = "list";
			frm.submit();
		}     
    }
	function doClose(){
		var frm = getObject("myform");
			frm.cmd.value = "close";
			frm.submit();
	}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
			<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
			<input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
			<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="leccode" value=""/> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
					<!-- -->
					<script>
					    function os_init() {
					        pageAJAXComm.getOrg('','<c:out value="${type}"/>');
					    }
					    var pageAJAXComm = {
					        getOrg: function(obj, destination) {
					            var URL = "<%=request.getContextPath()%>/common.do?cmd=getOrg&destination="+destination;
					            if(obj) {
					                URL += "&p_org=" + obj.value;
					                $("org_code").value=obj.value;
					                $("org_name").value=obj.options[obj.selectedIndex].text;
					            }else {
					                URL += '&p_org=<c:out value="${org_cd}"/>';
					                $("org_code").value='<c:out value="${LISTOP.ht.org_code}"/>';
					            }
					            var rqst = new Ajax.Request(URL, {
					                onSuccess: function (xmlHttp) {
					                    DataShow.jsonData(xmlHttp);
					                },
					                onFailure: function (xmlHttp) {
					                    alert('fail!!!');
					                }
					            });
					        },
					        getNew: function(obj, destination) {
					            var URL = "<%=request.getContextPath()%>/common.do?cmd=getOrg&destination="+destination;
					            if(obj) {
					                URL += "&p_org=" + obj.value;
					                $("org_code").value=obj.value;
					                $("org_name").value=obj.options[obj.selectedIndex].text;
					            }
					            var rqst = new Ajax.Request(URL, {
					                onSuccess: function (xmlHttp) {
					                    ReDataShow.jsonData(xmlHttp);
					                },
					                onFailure: function (xmlHttp) {
					                    alert('fail!!!');
					                }
					            });
					        }
					        
					    }
					    
					    var DataShow = {
					        jsonData: function(xmlHttp) {
					            var serverData = xmlHttp.responseText;
					            var evalData = serverData.evalJSON();
					            var k = getObject(evalData.destination);
					            k.options.length=1 // 초기화             
					            for( var i=0; i < evalData.list.length; i++ ) {
					               var option = new Option();
					               option.value = evalData.list[i].code;
					               option.text = evalData.list[i].name;
						           option.text = " [" + evalData.list[i].code + "] " + evalData.list[i].name;
					
					               k.options.add(option);
					               
									if(option.value == '<c:out value="${LISTOP.ht.os_region}"/>') {
									   option.selected = true;
									   var obj = getObject("os_region");
									   pageAJAXComm.getOrg(obj,"os_distribute");
									}
					
									if(option.value == '<c:out value="${LISTOP.ht.os_distribute}"/>') {
									   option.selected = true;
									   var obj = getObject("os_distribute");
									   pageAJAXComm.getOrg(obj,"os_dealer");
									}
					
									if(option.value == '<c:out value="${LISTOP.ht.os_dealer}"/>') {
									   option.selected = true;
									   var obj = getObject("os_dealer");
							            $("org_code").value='<c:out value="${LISTOP.ht.org_code}"/>';
							            $("org_name").value='<c:out value="${LISTOP.ht.org_name}"/>';
									}
					            }
					        },
					        setData: function(option) {
					            $("org_code").value=option.value;
					            $("org_name").value=option.options[option.selectedIndex].text;
					            
					        }
					    }
					
					    var ReDataShow = { //이미 설정되어 있는 경우 
					        jsonData: function(xmlHttp) {
					            var serverData = xmlHttp.responseText;
					            var evalData = serverData.evalJSON();
					            var k = getObject(evalData.destination);
					            k.options.length=1 // 초기화             
					            for( var i=0; i < evalData.list.length; i++ ) {
					               var option = new Option();
					               option.value = evalData.list[i].code;
					               option.text = " [" + evalData.list[i].code + "] " + evalData.list[i].name;
					
					               k.options.add(option);
					            }
					        }
					    }
					
					    Event.observe(window, 'load', os_init);
					</script>
							<mf:input type="hidden" name="org_code" value="${LISTOP.ht.org_code}"/>
							<mf:input type="hidden" name="org_name" value="${LISTOP.ht.org_name}"/>
						    <tr>
						        <th>
						        	<label for="os_region">Region. </label>
						        </th>
						        <td colspan="3">
						        	<select name="os_region" id="os_region" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_distribute')">
						            	<option value=''>-</option>
						        	</select>
						        </td>
					        </tr>
					        <tr>
						        <th>
						        	<label for="os_region">Distribute. </label>
						        </th>
						        <td colspan="3">
						        	<select name="os_distribute" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_dealer')">
						            	<option value=''>-</option>
						        	</select>
						        </td>
					        </tr>
					        <tr>
						        <th>
						        	<label for="os_region">Dealer. </label>
						        </th>
						        <td colspan="3">
						        	<select name="os_dealer" style="WIDTH: 90%;" class="os_selector" onChange="DataShow.setData(this)">
						            	<option value=''>-</option>
						        	</select>
						        </td>
						    </tr>
					<!-- -->
				<tr>
					<th><mf:label name="section"/></th>
					<td>
						<mf:select name="s_section" codeGroup="SECTION" curValue="${LISTOP.ht.s_section}">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="field"/></th>
					<td>
						<mf:select name="s_field" codeGroup="FIELD" curValue="${LISTOP.ht.s_field}">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="lecstat"/></th>
					<td>
						<mf:select name="s_lecstat" codeGroup="LECSTAT" curValue="${LISTOP.ht.s_lecstat}">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="lec_date"/></th>
					<td>
						<mf:input type="text" name="s_lec_sdate" size="10" value="${LISTOP.ht.s_lec_sdate}" onclick="popUpCalendar(this, this, 'yyyymmdd');" /> ~
						<mf:input type="text" name="s_lec_edate" size="10" value="${LISTOP.ht.s_lec_edate}" onclick="popUpCalendar(this, this, 'yyyymmdd');" />
					</td>
				</tr>
				<tr>
					<th><mf:label name="sjt_cd"/></th>
					<td>
						<mf:select name="s_sjt_cd" items="${crs_sub}" keyValue="sjt_cd" keyText="subject_nm" curValue="${LISTOP.ht.s_sjt_cd}">
						<option value="">-</option>
						</mf:select>
					</td>
					<th><mf:label name="lecname"/></th>
					<td><mf:input type="text" name="s_lecname" value="${LISTOP.ht.s_lecname}"/></td>					
				</tr>
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
	        </div>    
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_leccode')"/></th>
			    	<th>#</th>
		            <th><mf:header name="section" sort="true"/></th>
		            <th><mf:header name="field" sort="true"/></th>
		            <th><mf:header name="leccode" sort="true"/></th>
		            <th><mf:header name="lecname" sort="true"/></th>
		            <th><mf:header name="otype" sort="true"/></th>
		            <th><mf:header name="lecstat" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center">
						<c:if test="${item.lecstat != 'E'}">
						<mf:input type="checkbox" name="v_leccode" value="${item.leccode}"/>
						</c:if>
					</td>
					<td class="center"><mh:out value="${((LISTOP.ht.miv_page-1)*LISTOP.ht.miv_pagesize)+status.count}"/></td>
					<td class="center"><mh:out value="${item.section}" codeGroup="SECTION" td="true"/></td>
					<td class="center"><mh:out value="${item.field}" codeGroup="FIELD" td="true"/></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.leccode}"/>");'><mh:out value="${item.leccode}" td="true"/></a></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.leccode}"/>");'><mh:out value="${item.lecname}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.otype}" codeGroup="OTYPE" td="true"/></td>
					<td class="center"><mh:out value="${item.lecstat}" codeGroup="LECSTAT" td="true"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
			</mf:form> 
		    </div>
	    </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td align="right">
	    	<mf:button bundle="button" key="button.lecture.close" onclick="doClose()"/></br>
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>