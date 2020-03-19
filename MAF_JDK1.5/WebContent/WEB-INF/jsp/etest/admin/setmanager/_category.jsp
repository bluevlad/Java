<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:set var="dtreeUrl" value="${CPATH}/js/dtree" />
<link rel="StyleSheet" href='<c:out value="${dtreeUrl}"/>/dtree.css' type="text/css" />
<SCRIPT src='<c:url value="/js/dtree.js"/>'></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--

    var catTree = null;
    
   
    function selectCat(id, name) {
        var frm = getObject("myform");
        frm.s_cat_id.value=id;
        frm.s_cat_name.value=unescape(name);
        CategorySelector.outSel();
        CategorySelector.hideList();
    }
    
    var CategorySelector = {
        clickCount:0, 
        over_sel:false,
        loaded:false,
        hideList: function(event) {
            
            if(!CategorySelector.over_sel) {
                $('catTreeDiv').setStyle({'visibility':'hidden'});
            }
            //$('catTreeDiv').setStyle({'visibility':'hidden'});
            //alert("hidelist " + !CategorySelector.over_sel);
            
        },
        showList: function(event) {
            if(!CategorySelector.loaded) {
                CategorySelector.getList();
             }
            $('catTreeDiv').setStyle({'visibility':'visible'});
            CategorySelector.over_sel = true;
        },
        overSel:function() {
            CategorySelector.over_sel = true;
        },
        outSel:function() {
            CategorySelector.over_sel = false;
        },
        getList:function(event) {
            var URL = '<c:url value="bank.do"><c:param name="cmd" value="tree"/></c:url>';
            
            var rqst = new Ajax.Request(URL, {
                onSuccess: function (xmlHttp) {
                    CategorySelector.populateDiv(xmlHttp);
                    CategorySelector.loaded=true;
                }
            });
        },
        populateDiv:function(xmlHttp) {
            catTree= new dTree("catTree");
            //catTree.add("HOME", "-1", "All", "javascript:selectCat('','All')", "All");
            
            catTree.add("0", "-1", "All", "javascript:selectCat('','All')", "All");
            <c:if test="${param.target =='list'}" >
            catTree.add('<c:out value="${common}"/>', "0", "Common", "javascript:selectCat('<c:out value="${common}"/>','Common All')", "All");
            </c:if>
            catTree.add('<c:out value="${org_cd}"/>', "0", "My", "javascript:selectCat('<c:out value="${org_cd}"/>',' MyAll')", "All");

            var serverData = xmlHttp.responseText;
            var evalData = serverData.evalJSON();
            var title = null;
                
            for(var i=0; i < evalData.length; i++ ) {
                var item = evalData[i];
                if(item) {
                    title = item.cat_id + "-" + item.cat_name + " (" + item.cnt + ")";
                    catTree.add(item.cat_id, item.p_cat_id,title,"javascript:selectCat('"+item.cat_id+"','"+escape(item.cat_name)+"');", item.cat_name);
                }
            }
            
           $("catTreeDiv").innerHTML= catTree.toString();
           $("catTreeDiv").style.visibility="visible";
        }
    }

    function initOnload() {
        Event.observe('catTreeDiv', 'mouseover', CategorySelector.overSel); 
        Event.observe('catTreeDiv', 'mouseout', CategorySelector.outSel); 
        Event.observe('s_cat_name', 'focus', CategorySelector.showList  );
        Event.observe('s_cat_name', 'blur', CategorySelector.hideList );
        
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
  