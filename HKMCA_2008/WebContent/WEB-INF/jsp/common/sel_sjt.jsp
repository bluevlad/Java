<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    function os_init() {
        var dest = '<%=request.getParameter("destination")%>';
        if (dest == "os_sjt"){
            pageAJAXComm.getLec(getObject('os_sjt'),'os_sjt','<%=request.getParameter("os_crs")%>');
//            pageAJAXComm.getLec(getObject('os_crs'),'os_crs','');
        } else if (dest == "os_crs"){
            pageAJAXComm.getLec(getObject('os_crs'),'os_crs','');
            pageAJAXComm.getLec(getObject('os_sjt'),'os_sjt','<%=request.getParameter("os_crs")%>');
        } else {
            pageAJAXComm.getLec("getObject('os_crs')",'');
        }
    }
    var pageAJAXComm = {
        getLec: function(obj, destination, value) {
            var URL = "<%=request.getContextPath()%>/common.do?cmd=getLec&destination="+destination;
            if(obj) {
                URL += "&cd=" + value;
            }
            var rqst = new Ajax.Request(URL, {
                       onSuccess: function (xmlHttp) {
                          DataShow.jsonData(xmlHttp);
                       },
                       onFailure: function (xmlHttp) {
                         alert('fail!!!');
                       }
                });
       } ,
       getNew: function(obj, destination) {
           var URL = "<%=request.getContextPath()%>/common.do?cmd=getLec&destination="+destination+"&cd="+obj.value;

           var rqst = new Ajax.Request(URL, {
               onSuccess: function (xmlHttp) {
                    DataShow.jsonNewData(xmlHttp);
               },
               onFailure: function (xmlHttp) {
                   alert('fail!!!');
               }
           });
       }  
    }
    
    var DataShow = {
        jsonNewData: function(xmlHttp) {
            var serverData = xmlHttp.responseText;
//            alert(serverData);
            var evalData = serverData.evalJSON();
            var k = getObject(evalData.destination);
            k.options.length=1 // 초기화
            for( var i=0; i < evalData.list.length; i++ ) {
               var option = new Option();
               option.value = evalData.list[i].cd;
               option.text = evalData.list[i].nm;
               k.options.add(option);
            }
        },
        jsonData: function(xmlHttp) {
            var serverData = xmlHttp.responseText;
//            alert(serverData);
            var evalData = serverData.evalJSON();
            var k = getObject(evalData.destination);
            k.options.length=1 // 초기화
            for( var i=0; i < evalData.list.length; i++ ) {
               var option = new Option();
               option.value = evalData.list[i].cd;
               option.text = evalData.list[i].nm;
               k.options.add(option);
               if(option.value == '<%=request.getParameter("os_sjt")%>') {
                   option.selected = true;
                   var obj = getObject("os_crs");
                   pageAJAXComm.getLec(obj,"os_crs",'<%=request.getParameter("os_crs")%>');
                }
               if(option.value == '<%=request.getParameter("os_crs")%>') {
                   option.selected = true;
                }
            }
        }
    }

    Event.observe(window, 'load', os_init);
</script>
            <select name="os_crs" id="os_crs" onChange="pageAJAXComm.getNew(this,'os_sjt')">
            <option value=''>-</option>
            </select>
            <select name="os_sjt">
            <option value=''>-</option>
            </select>
