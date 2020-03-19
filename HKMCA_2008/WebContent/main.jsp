<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page import="maf.util.DateUtils"%>
<%
    String mmmm = DateUtils.getCurrentDate();
%>

<script type="text/javascript">
<!--
//win_pop();

function win_pop() {
    var eventCookie = getCookie("<%=mmmm%>");
    if (eventCookie != "done" )
    {
        var Message = window.open('pop.jsp','Message','height=10,width=10,left=0,top=0, menubar=no,directories=no,resizable=yes,status=no,scrollbars=no');
        Message.focus();
    }
}

function view_mov(contents)
{
    var View = window.open('/main/view_mov.do?contents='+contents,'view_mov','height=10,width=10,left=0,top=0, menubar=no,directories=no,resizable=yes,status=no,scrollbars=no');
    View.focus();
}

// 쿠키가 있나 찾습니다
function getCookie( name ) {
    var nameOfCookie = name + "=";
    var x = 0;

    while ( x <= document.cookie.length )
    {
            var y = (x+nameOfCookie.length);
            if ( document.cookie.substring( x, y ) == nameOfCookie ) {
                    if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                            endOfCookie = document.cookie.length;
                    return unescape( document.cookie.substring( y, endOfCookie ) );
            }
            x = document.cookie.indexOf( " ", x ) + 1;
            if ( x == 0 )
                    break;
    }
    return "";
}

//-->
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td><img src="/images/global/visual.jpg" alt="메인비주얼"/></td>
    </tr>
</table>
