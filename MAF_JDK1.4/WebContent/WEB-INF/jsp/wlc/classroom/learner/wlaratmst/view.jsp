<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript" >
<!--
//-->
</script>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%"> 
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" name="${mrt:mvcCmd()}" value="">
    <input type="hidden" name="leccode" value="${mhtml:nvl(leccode,'')}">
    <tr>
        <th width=20% height="28" class="view"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.item"/></th>
        <th colspan=3 width=80% class="view"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.rate"/></td>
    </tr>
    <tr>
        <th width=20% class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.jindo_time"/></th>
        <td width=30% class="view" bgcolor="#FFFFFF">${item.jindo_time} % </td>
        <th width=20% rowspan=2 class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratjindo"/></th>
        <td width=30% rowspan=2 class="view" bgcolor="#FFFFFF">${item.ratjindo} %<br>
            (<mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratjindo1"/>)
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.jindo_page"/></th>
        <td class="view" bgcolor="#FFFFFF">${item.jindo_page} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratexam"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratexam} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratreport"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratreport} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratproject"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratproject} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratforum"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratforum} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratoffline"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratoffline} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratetc"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratetc} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratadd"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF">${item.ratadd} % </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.total"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><span id="showTot">
        ${(item.jindo_time+item.jindo_page+item.ratexam+item.ratreport+item.ratproject+item.ratforum+item.ratoffline+item.ratetc+item.ratadd)}
        </span><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.total2"/></td>
    </tr>
    <tr>
        <th colspan=4 height="28" class="view" align="left"><font color='red'>
        <mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.noti3"/><br>
        &nbsp;&nbsp;<mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.noti4"/><br>
        &nbsp;<mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.noti5"/>        
        </font></th>
    </tr>
</form>
</table>
