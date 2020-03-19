<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="dtreeUrl" value="${CPATH}/js/dtree"/>
<link rel="StyleSheet" href='<c:url value="${dtreeUrl}/dtree.css"/>' type="text/css" />
<SCRIPT src="<mh:out value="${CPATH}"/>/js/dtree.js"></SCRIPT>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(sjtcode, leccode){
		var frm = getObject("myform");
		if(frm) {
        frm.action = CPATH+"/wlc.tutor/default.do";
        frm.cmd.value = "default";
        frm.to.value  = CPATH+"/wlc.tutor/chapters.do";
        frm.sjtcode.value  = sjtcode;
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
	function setSjtcd(sjt_cd) {
        if (typeof sjt_cd == "undefined") {
            sjt_cd = "";
        }
        var frm = getObject("myform");
        frm.sjt_cd.value=sjt_cd;
        CategorySelector.outSel();
        CategorySelector.hideList();
        return;
    }
    var CategorySelector = {
        clickCount:0, 
        over_sel:false,

        hideList: function(event) {
            
            if(!CategorySelector.over_sel) {
                $('catTreeDiv').setStyle({'visibility':'hidden'});
            }

            
        },
        showList: function(event) {

            $('catTreeDiv').setStyle({'visibility':'visible'});
            CategorySelector.over_sel = true;
        },
        overSel:function() {
            CategorySelector.over_sel = true;
        },
        outSel:function() {
            CategorySelector.over_sel = false;
        }
    }
     function initOnload() {
        Event.observe('catTreeDiv', 'mouseover', CategorySelector.overSel); 
        Event.observe('catTreeDiv', 'mouseout', CategorySelector.outSel); 
        Event.observe('sjt_cd', 'focus', CategorySelector.showList  );
        Event.observe('sjt_cd', 'blur', CategorySelector.hideList );
        
    }
    Event.observe(window, 'load', initOnload);
//-->
</SCRIPT>
<style>
    #catTreeDiv{
        width: 200px;
        height: 200px;
        position: absolute;
        border: 1px solid #999999;
        overflow: auto;
        background: #ffffff;
        z-index: 200;
        display: block;
        visibility: hidden;
    }
</style>  
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
			<mf:form action="${control_action}" method="get" name="myform" id="myform">
		        <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
				<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
				<mf:input type="hidden" name="miv_page" value="1"/>
				<mf:input type="hidden" name="cmd" value="list"/>
				<mf:input type="hidden" name="sjtcode" value=""/> 
				<mf:input type="hidden" name="leccode" value=""/> 
		        <mf:input type="hidden" name="to" value=""/>
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
					<td><mf:select name="section" codeGroup="SECTION" curValue="${LISTOP.ht.section}"><option value=""><mfmt:message bundle="common" key="search.all"/></option></mf:select></td>					
					<th><mf:label name="field"/></th>
					<td><mf:select name="field" codeGroup="FIELD" curValue="${LISTOP.ht.field}"><option value=""><mfmt:message bundle="common" key="search.all"/></option></mf:select></td>					
				</tr>
				<tr>
					<th><mf:label name="cert_lvl"/></th>
					<td><mf:select name="cert_lvl" codeGroup="CERT_LVL" curValue="${LISTOP.ht.cert_lvl}"><option value=""><mfmt:message bundle="common" key="search.all"/></option></mf:select></td>					
					<th><mf:label name="lecstat"/></th>
					<td><mf:select name="lecstat" codeGroup="LECSTAT" curValue="${LISTOP.ht.lecstat}"><option value=""><mfmt:message bundle="common" key="search.all"/></option></mf:select></td>					
				</tr>
				<tr>
					<th><mf:label name="lec_date"/></th>
					<td>
						<mf:input type="date" name="lec_sdate" size="10" value="${LISTOP.ht.lec_sdate}" onclick="popUpCalendar(this, this, 'yyyymmdd');"/> ~
						<mf:input type="date" name="lec_edate" size="10" value="${LISTOP.ht.lec_edate}" onclick="popUpCalendar(this, this, 'yyyymmdd');"/>
					</td>
					<th><mf:label name="sjt_cd"/></th>
					<td>
						<mf:input type="text" name="sjt_cd" readonly="true" value="${LISTOP.ht.sjt_cd}" onclick="CategorySelector.showList()" cssClass="dropdown" /><br>
                    	<div id="catTreeDiv" class="clear">
                                <script type="text/javascript">
                                    <!--
                                    d = new dTree('d');
                                    d.config.useIcons=false;
                                    d.add('ROOT', '-1', 'All', 'javascript:setSjtcd();', 'ROOT');
                                    <c:forEach var="item" items="${crs_sub}">
                                    <c:if test="${item.crs_cd == 'ROOT'}">
                                    <c:set var="sjt_cd" value=""/>
                                    </c:if>
                                    <c:if test="${item.crs_cd != 'ROOT'}">
                                    <c:set var="sjt_cd" value="javascript:setSjtcd('${item.sjt_cd}');"/>
                                    </c:if>
                                    d.add('<c:out value="${item.sjt_cd}" />',
                                    '<c:out value="${item.crs_cd}" />',
                                    '<c:out value="${item.subject_nm}[${item.sjt_cd}]" />',
                                    '<c:out value="${sjt_cd}" />',
                                    '<c:out value="${item.sjt_cd}" />');
                                    </c:forEach>
                                    document.write(d);
                                    //-->
                                </script>
                     	</div>
                     </td>
				</tr>
				
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
			</mf:form> 
	        </div>    
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">

			<thead>
			    <tr>

		            <th><mf:header name="section" sort="true"/><br>
                        <mf:header name="cert_lvl" sort="true"/></th>
		            <th><mf:header name="field" sort="true"/><br>
                        <mf:header name="orgn_cd" sort="true"/></th>
		            <th><mf:header name="leccode" sort="true"/><br>
                        <mf:header name="lecname" sort="true"/></th>
		            <th><mf:header name="lecstat" sort="true"/></th>
		            <th><mf:header name="otype" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td align="center" ><mh:out value="${item.section}" codeGroup="SECTION"/><br>
                        <mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL"/></td>
					<td align="center" ><mh:out value="${item.field}" codeGroup="FIELD"/><br>
                        <mh:out value="${item.org_name_disp}"/></td>
					<td align="center" ><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.leccode}"/>");'><mh:out value="${item.leccode}" td="true"/><br>
                        <strong><mh:out value="${item.lecname}" td="true"/></strong></a></td>
					<td align="center" ><mh:out value="${item.lecstat}" codeGroup="LECSTAT"/><br>
                        <mh:out value="${item.cnt_lr}"/> / <mh:out value="${item.cnt_lp}"/> / <mh:out value="${item.cnt_le}"/></td></td>
					<td align="center"><mh:out value="${item.otype}" codeGroup="OTYPE" td="true"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
	        
		    </div>
	    </td>
    </tr>
	<tr>
	    <td align="right">
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>
