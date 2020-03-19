<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8" />
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>Dojo TreeTest</title>
<link href='/hkmca/css/maf_common.css' rel="stylesheet" type="text/css"></link>
<link href='/hkmca/css/kmc_common.css' rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src='/hkmca/js/sub.common.js'></script>
<script type="text/javascript" src='/hkmca/js/maf.js'></script>
<script type="text/javascript">
    var djConfig = {isDebug: true, debugAtAllCosts: true };
</script>
<script type="text/javascript" src='/hkmca/dojo/dojo.js'></script>
<script type="text/javascript">
    dojo.require("dojo.lang.*");
    dojo.require("dojo.event.*");
    dojo.require("dojo.widget.*");
    dojo.require("dojo.widget.Tree");
    dojo.require("dojo.widget.TreeSelector");
    dojo.require("dojo.widget.TreeLoadingController");
    dojo.require("dojo.widget.TreeControllerExtension");
    dojo.require("dojo.widget.LayoutContainer");
    dojo.require("dojo.widget.LinkPane");
    dojo.require("dojo.widget.ContentPane");
    dojo.require("dojo.widget.FloatingPane");
    dojo.require("dojo.widget.Dialog");
    dojo.hostenv.writeIncludes();
    dojo.addOnLoad(function() {
        // add extensions to controller
        dojo.lang.mixin(dojo.widget.byId('treeController'), dojo.widget.TreeControllerExtension.prototype);
    });

    function saveExpandedIndices() {
        // You can save this object as tree persistent state
        indices = dojo.widget.byId('treeController').saveExpandedIndices(
            dojo.widget.byId('firstTree')
        );
        alert(":" + dojo.json.serialize(indices));
        
    }

    function restoreExpandedIndices() {
        dojo.widget.byId('treeController').restoreExpandedIndices(
            dojo.widget.byId('firstTree'), indices
        );
    }

    function printExpandedIndices() {
        if (!dojo.lang.isFunction(indices.toSource)) {
            dojo.raise("Use Mozilla Firefox or other browser that supports Object.toSource()");
        }
        dojo.debug(indices.toSource());
    }

</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<div dojoType="LayoutContainer" id="main" layoutChildPriority='left-right' style="height:300px">    
    <div dojoType="LinkPane" layoutAlign="left" style="width: 200px; background-color: #acb386;" executeScripts="true">
        <div dojoType="TreeLoadingController" widgetId="treeController" RPCUrl="dojo_tree_getChildren.jsp" DNDcontroller="create"></div>
        <div dojoType="Tree" widgetId="firstTree" controller="treeController" DNDMode="between" DNDAcceptTypes="firstTree">
            <div dojoType="TreeNode" title="Root" objectId="root" expandLevel="1">
                <div dojoType="TreeNode" title="Node 1" objectId="t1" ></div>
                <div dojoType="TreeNode" title="Node 2" expandLevel="1" objectId="t2">
                    <div dojoType="TreeNode" title="Node 2.1" objectId="t2.1"></div>
                    <div dojoType="TreeNode" title="Node 2.2" objectId="t2.2"></div>
                </div>
                <div dojoType="TreeNode" title="Node 3" objectId="t3"></div>
            </div>
        </div>
    </div>
    <div dojoType="ContentPane" layoutAlign="client" style="background-color: #f5ffbf; padding: 10px;"
        id="docpane" executeScripts="true">
        This is the initial content of the pane (inlined in the HTML file), but
        it will be overriden when you click "doc0" or "doc1" or "doc2" in the tree on the left (underneath "Item 1")
    </div>
</div>

<input type="button" value="Save expanded" onClick="saveExpandedIndices()" />
<input type="button" value="Restore expanded" onClick="restoreExpandedIndices()" />
<input type="button" value="Print expanded" onClick="printExpandedIndices()" />
</body>
</html>
