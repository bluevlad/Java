<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<style>
    .os_selector {
        width:120px;
    }
</style>
<script>
    
    var OrgSelector = {
        getOrg: function(obj, destination, nextcode) {
            var URL = CPATH+"/common.do?cmd=getOrg&destination="+destination;
            if(obj) {
                URL += "&p_org=" + obj.value;
                OrgSelector.setData(obj);
            }
            if(nextcode) {
                URL += "&nextcode=" + nextcode;
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
        },
        setData: function(option) {
            $('<c:out value="${param.target_code}" default="org_code"/>').value=option.value;
            var t = getObject('<c:out value="${param.target_name}" default="org_name"/>')
            if(t) {
                t.value=option.options[option.selectedIndex].text;
            }
        }
    }
    
    function os_init() {
        OrgSelector.getOrg('','os_region');
    }
    Event.observe(window, 'load', os_init);
    
    var OrgSetter = {
        sync: function(org_cd) {
            var URL = CPATH+"/common.do?cmd=setOrg";
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
                    OrgSelector.getOrg(k,'os_distribute',evalData.DI.code);
                } else {
                    OrgSelector.getOrg(k,'os_distribute');
                }
                var k2 = getObject("os_distribute");
                k2.value=evalData.DI.code;
                if(evalData.DE) {
                    OrgSelector.getOrg(k2,'os_dealer',evalData.DE.code);
                } else {
                    OrgSelector.getOrg(k2,'os_dealer');
                }
            }
        }
    }
    //OrgSetter.sync('A32VA.00000')
</script>

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

</table>
