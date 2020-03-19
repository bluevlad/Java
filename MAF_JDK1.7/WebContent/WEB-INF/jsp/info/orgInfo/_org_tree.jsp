<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:set var="dtreeUrl" value="${CPATH}/js/dtree" />
<link rel="StyleSheet" href='<c:out value="${dtreeUrl}"/>/dtree.css' type="text/css" />
<SCRIPT src='<c:url value="/js/dtree.js"/>'></SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--

    var catTreeDiv = null;
    function getCategory(obj) {
        var params = new Array();

        var bindArgs = {
            url: "<c:url value="${controlaction}"><c:param name="cmd" value="tree"/></c:url>",
            error: function(type, data, evt){alert("error");},
            mimetype: "text/json",
            content: params
        };
        var req = dojo.io.bind(bindArgs);

        dojo.event.connect(req, "load", this, "populateDiv");
        var obj = getObject(obj);
        document.getElementById("dept_tree").top=element_top(obj);
        document.getElementById("dept_tree").left=element_left(obj);
    }
    function getSubCategory(obj) {
        var params = new Array();

        var bindArgs = {
            url: "<c:url value="${controlaction}"><c:param name="cmd" value="tree"/></c:url>",
            error: function(type, data, evt){alert("error");},
            mimetype: "text/json",
            content: params
        };
        var req = dojo.io.bind(bindArgs);

        dojo.event.connect(req, "load", this, "populateDiv");
        var obj = getObject(obj);
        document.getElementById("dept_tree").top=element_top(obj);
        document.getElementById("dept_tree").left=element_left(obj);
    }


    function populateDiv(type, data, evt) {

        for(var i=0; i < data.length; i++ ) {
            var item = data[i];
            if(item) {
                catTreeDiv.add(item.cat_id, item.p_cat_id,item.cat_name,"javascript:selectCat('"+item.cat_id+"','"+escape(item.cat_name)+"');hideCatTree2();", item.cat_name);
            }
        }
        
        document.getElementById("dept_tree").innerHTML= catTreeDiv.toString();
        document.getElementById("dept_tree").style.visibility="visible";
    }
    function selectCat(id, name) {
        var frm = getObject("myform");
        frm.cat_id.value=id;
        frm.cat_name.value=unescape(name);
    }
    
    function hideCatTree(e) {
        var divObj=getObject("dept_tree");
        
        if(!dojo.html.overElement(divObj, e)) {
            divObj.style.visibility="hidden";
        } else{
            return ;
        }
        
    }
    function hideCatTree2() {
        var divObj=getObject("dept_tree");
        divObj.style.visibility="hidden";
    }
    function initOnload() {
       
       //dojo.event.connect(document, "onclick",  this, "hideCatTree");
       
       catTreeDiv = new dTree("catTreeDiv");
       catTreeDiv.add("HOME", "-1", "All", "javascript:selectCat('','All');hideCatTree2();", "All");
    }
    dojo.addOnLoad(initOnload());
    
//-->
</SCRIPT>
<style>
    #dept_tree{
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
  