<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    function os_init() {
        pageAJAXComm.getOrg('','<%=request.getParameter("type")%>');
    }
    var pageAJAXComm = {
        getOrg: function(obj, destination) {
            var URL = "<%=request.getContextPath()%>/common.do?cmd=getOrg&destination="+destination;
            if(obj) {
                URL += "&p_org=" + obj.value;
                $("org_code").value=obj.value;
                $("org_name").value=obj.options[obj.selectedIndex].text;
            }else {
                URL += '&p_org=<%=request.getParameter("org_cd")%>';
                $("org_code").value='<%=request.getParameter("org_cd")%>';
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
               
				if(option.value == '<%=request.getParameter("os_region")%>') {
				   option.selected = true;
				   var obj = getObject("os_region");
				   pageAJAXComm.getOrg(obj,"os_distribute");
				}

				if(option.value == '<%=request.getParameter("os_distribute")%>') {
				   option.selected = true;
				   var obj = getObject("os_distribute");
				   pageAJAXComm.getOrg(obj,"os_dealer");
				}

				if(option.value == '<%=request.getParameter("os_dealer")%>') {
				   option.selected = true;
				   var obj = getObject("os_dealer");
		            $("org_code").value='<%=request.getParameter("org_code")%>';
		            $("org_name").value='<%=request.getParameter("org_name")%>';
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
		<input type="hidden" value="" name="org_code"  id="org_code"/>
		<input type="hidden" value="" name="org_name"  id="org_name"/>
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