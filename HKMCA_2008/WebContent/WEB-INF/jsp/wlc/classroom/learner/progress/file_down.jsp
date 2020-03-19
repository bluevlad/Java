<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
function resize() {
    var w = 50;
    var h = 150;

    with(document.body)
    {
       window.resizeTo(w, h);
    }

}

function sizechange(v_width,v_height)
{
    var frm = getObject("myform");
    frm.v_width.value = v_width;
    frm.v_height.value = v_height;
    frm.submit();
}

resize();

function save()
{
	document.location = '<c:url value="/filedown/?file=${citem.htmurl}"/>';
}
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
    <tr>
        <th><mf:button onclick="save();" bundle="button" key="file.down" icon="icon_save" /></th>
    </tr>
</table>