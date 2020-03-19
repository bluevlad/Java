<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8" />
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<script type="text/javascript" src='/hkmca/js/sub.common.js'></script>
<script type="text/javascript" src='/hkmca/js/prototype.js'></script>

</head>
<body>
<style>
    
</style>
<script>
    
    var OrgSelector = {
        getOrg: function(obj, destination, nextcode, nextname) {
            var URL = "/hkmca/common.do?cmd=getOrg&destination="+destination;
            if(obj) {
                URL += "&p_org=" + obj.value;
                OrgSelector.setData(obj);
            }
            if(nextcode) {
                URL += "&nextcode=" + nextcode;
            }
            if(nextname) {
                URL += "&nextname=" + nextname;
            }
            $('os_dealer').length=1;
            var rqst = new Ajax.Request(URL, {
                onSuccess: function (xmlHttp) {
                    OrgSelector.makeOption(xmlHttp);
                },
                onFailure: function (xmlHttp) {
                    alert('fail!!!');
                }
            });
        },
        makeOption: function(xmlHttp) {
            var serverData = xmlHttp.responseText;
            var evalData = serverData.evalJSON();
            
            var k = getObject(evalData.destination);
            k.options.length=1  
            for( var i=0; i < evalData.list.length; i++ ) {
               var option = new Option();
               option.value = evalData.list[i].code;
               option.text = evalData.list[i].name;
               k.options.add(option);
            }
            k.value = evalData.nextcode;
            OrgSelector.setText(evalData.nextcode, evalData.nextname);
            //alert()
        },
        setData: function(option) {
            $('<c:out value="${param.target_code}" default="org_code"/>').value=option.value;
            $('<c:out value="${param.target_name}" default="org_name"/>').value=option.options[option.selectedIndex].text;
        },
        setText: function(code, name) {
            $('<c:out value="${param.target_code}" default="org_code"/>').value=code;
            $('<c:out value="${param.target_name}" default="org_name"/>').value=name;
        }
    }
    
    function os_init() {
        OrgSelector.getOrg('','os_region');
    }
    Event.observe(window, 'load', os_init);
    
    var OrgSetter = {
        sync: function(org_cd) {
            var URL = "/hkmca/common.do?cmd=setOrg";
                URL += "&org_cd=" + org_cd;
            var rqst = new Ajax.Request(URL, {
                onSuccess: function (xmlHttp) {
                    OrgSetter.setup(xmlHttp);
                },
                onFailure: function (xmlHttp) {
                    alert('fail!!!');
                }
            });
        },
        setup:function(xmlHttp) {
            var serverData = xmlHttp.responseText;
            var evalData = serverData.evalJSON();
            
            if(evalData.RG) {
                var k = getObject("os_region");
                k.value=evalData.RG.code;
                if(evalData.DI) {
                    OrgSelector.getOrg(k,'os_distribute',evalData.DI.code, evalData.DI.name);
                } else {
                    OrgSelector.getOrg(k,'os_distribute');
                }
                var k2 = getObject("os_distribute");
                k2.value=evalData.DI.code;
                if(evalData.DE) {
                    OrgSelector.getOrg(k2,'os_dealer',evalData.DE.code, evalData.DE.name);
                } else {
                    OrgSelector.getOrg(k2,'os_dealer');
                }
            }
        }
    }
</script>
<a href="javascript:OrgSetter.sync('A02AA.5001')">setup (A02AA.5001)</a>
<a href="javascript:OrgSetter.sync('A07AG')">setup (A07AG)</a>

<form name="f" id="f">
<table>
    <tr>
        <td><label for="os_region">Region : </label> <select name="os_region" id="os_region" class="os_selector" onChange="OrgSelector.getOrg(this,'os_distribute')">
            <option value=''>-</option>
        </select></td>
        <td><label for="os_distribute">distribute : </label> <select name="os_distribute" id="os_distribute" class="os_selector" onChange="OrgSelector.getOrg(this,'os_dealer')">

        </select></td>
        <td><label for="os_dealer">dealer : </label> <select name="os_dealer" id="os_dealer" class="os_selector" onChange="OrgSelector.setData(this)">
            <option>-</option>
        </select></td>
    </tr>
    <tr>
        <td colspan="6"><label for="org_code">org_code</label><input type="text" value="E44VA" name="org_code"  id="org_code"/>
            <label for="org_name">org_name</label><input type="text" value="" name="org_name"  id="org_name"/></td>
    </tr>
</table>
</form>

</body>
</html>