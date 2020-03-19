<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
    function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                // 사용자 조건 추가.
                <c:choose>
                    <c:when test="${param.cmd =='add'}">
                        frm.cmd.value="insert";
                    </c:when>
                    <c:otherwise>
                        frm.cmd.value="update";
                    </c:otherwise>
                </c:choose>
                selectOptionAll("myform","s_selorg");
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }

    function os_init() {
        pageAJAXComm.getOrg('','<c:out value="${type}"/>');
    }
    
    var pageAJAXComm = {
        getNew: function(obj, destination) {
	            var URL = "<%=request.getContextPath()%>/common.do?cmd=getTargetOrg&region="+obj.value;
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
    var ReDataShow = { //이미 설정되어 있는 경우 
        jsonData: function(xmlHttp) {
            var serverData = xmlHttp.responseText;
            var evalData = serverData.evalJSON();
            var k = getObject("s_dist");
            k.options.length=0 // 초기화             
            for( var i=0; i < evalData.list.length; i++ ) {
               var option = new Option();
               option.value = evalData.list[i].code;
               option.text = " [" + evalData.list[i].code + "] " + evalData.list[i].name;

               k.options.add(option);
            }
        }
    }
    function os_init() {
        var obj = getObject("s_region");
        pageAJAXComm.getNew(obj);
    }
    Event.observe(window, 'load', os_init);      
                       
</script>

<script type="text/javascript" src='<c:url value="/js/fckeditor/fckeditor.js"/>' ></script>
<script type="text/javascript">
    addEvent(window,'load',function() {
        var sBasePath =CPATH+'/js/fckeditor/' ;
    
        var oFCKeditor = new FCKeditor( 'contetnts' ) ;
        oFCKeditor.BasePath = sBasePath ;
        oFCKeditor.ToolbarSet = 'mBoard' ;
        
        oFCKeditor.Height = 400;
        oFCKeditor.DisplayErrors = false;
        oFCKeditor.ReplaceTextarea() ;
        });
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" enctype="multipart/form-data" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
    <input type="hidden" name="cmd" value="">
    <mf:input type="hidden" name="id" value="${item.id}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:label name="title" /></th>
            <td colspan="3"><mf:input type="text" name="title" size="80" maxlength="200" value="${item.title}" required="true"/></td>
        </tr>
         <tr>
            <th nowrap><mf:label name="category" /></th>
            <td><mf:select name="category"  curValue="${item.category}" codeGroup="NOTICE.CATEGORY">
                <option value="">-</option>
                </mf:select></td>

            <td nowrap colspan=2">
            <mf:label name="always_top" /> <mf:input type="checkbox" name="always_top" value="Y" curValue="${item.always_top}" />
            <mf:label name="popup_yn" /> <mf:input type="checkbox" name="popup_yn" value="Y" curValue="${item.popup_yn}" />
            </td>
        </tr>        
        <tr>
            <th nowrap>Target</th>
            <td  colspan="3"><table class="">
                <tr>
                    <td valign="bottom"><mf:select name="s_region" cssStyle="width:250px" items="${regions}" keyValue="org_cd" keyText="org_name" onchange="pageAJAXComm.getNew(this)"></mf:select><br>
                        <mf:select name="s_dist" cssStyle="width:250px" size="8" htmlmultiple="true"></mf:select></td>
                        <td align="center"> 
                            <input type="button" name="btn_add_all" style="width:100px" value="&gt;&gt;" onClick="selectMoveAll('myform','s_dist','s_selorg', false)"><br>
                            <input type="button" name="btn_add"  style="width:100px" value="&gt;" onClick="selectMove('myform','s_dist','s_selorg', false)"><br><br>
                            <input type="button" name="btn_del"  style="width:100px" value="&lt;" onClick="selectDel('myform','s_selorg')"><br>
                            <input type="button" name="btn_add_all"  style="width:100px" value="&lt;&lt;"  onClick="selectDelAll('myform','s_selorg')">
                        </td>
                    <td valign="bottom"><mf:label name="view_all" /> : <mf:input type="checkbox" name="view_hq" value="Y" curValue="${item.view_hq}" />
                <mf:label name="view_de" /> : <mf:input type="checkbox" name="view_de" value="Y" curValue="${item.view_de}" /><br>
                <mf:select name="s_selorg" cssStyle="width:250px" size="8"  items="${targets}" keyValue="org_cd" keyText="org_name"  htmlmultiple="true"></mf:select></td>
                </tr>
                </table>
                
            
            </td>
        </tr>

        <tr>
            <th nowrap><mf:label name="contetnts" /></th>
            <td  colspan="3">
            <textarea cols="68" rows="38" name="contetnts" id="contetnts" 
                class="bbs_text_area" style="WIDTH: 100%; "  hname="contetnts"><mh:out value="${item.contetnts}"/></textarea>
           
            </td>
        </tr>

       <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"><c:forEach var="fi" items="${files}">
                <mf:input name="delfiles" type="checkbox" value="${fi.filename}"/>
                <a href='<c:url value="/wdownload">
                    <c:param name="filename" value="${fi.filename}"/>
                    <c:param name="type" value="notice_file"/></c:url>'><c:out value="${fi.org_filename}"/></a> (<c:out value="${fi.file_size}"/>)KB : <br>
            </c:forEach>
            <br><jsp:include page="/WEB-INF/jsp/common/fileAttach/fileAttach.jsp" flush="true">
           <jsp:param name="FILEID" value="attachfile" />     
         </jsp:include> </td>
    </tr>
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center"><mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> <mf:button
                bundle="button" key="goList" onclick="goList()" /></td>
        </tr>
    </table>
</mf:form>
