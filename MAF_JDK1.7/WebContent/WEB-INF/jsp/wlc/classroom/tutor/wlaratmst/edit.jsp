<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" src="${CPATH}/js/lib.calendar.js"></script>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
    <input type="hidden" name="${mrt:mvcCmd()}" value="">
    <input type="hidden" name="leccode" value="${mhtml:nvl(leccode, '')}">
    <tr>
        <th colspan=4 height="28" class="view"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.noti"/></th>
    </tr>
    <tr>
        <th width=20% height="28" class="view"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.item"/></th>
        <th colspan=3 width=80% class="view"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.rate"/></th>
    </tr>
    <tr>
        <th width=20% class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.jindo_time"/></th>
        <td width=30% class="view" bgcolor="#FFFFFF"><input type=text name="jindo_time" size=5 maxlength=3 value="${item.jindo_time}" onKeyUp="checkNumber(this);setTotal(this.form);setJindo(this.form);"onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
        <th width=20% rowspan=2 class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratjindo"/></th>
        <td width=30% rowspan=2 class="view" bgcolor="#FFFFFF">
            <input name=ratjindo size=3 maxlength=4 value="${item.ratjindo}" style='text-align:right;background-color: #ffffff ; border-style: none 0px;color:black;bold;font-weight:height:16;font-size:12; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px;' readonly>%<br>
            (<mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratjindo1"/>)
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.jindo_page"/></th>
        <td class="view" bgcolor="#FFFFFF"><input type=text name="jindo_page" size=5 maxlength=3  value="${item.jindo_page}" onKeyUp="checkNumber(this);setTotal(this.form);setJindo(this.form);" onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratexam"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratexam" size=5 maxlength=3 value="${item.ratexam}" onKeyUp="checkNumber(this);setTotal(this.form);" onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratreport"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratreport" size=5 maxlength=3 value="${item.ratreport}" onKeyUp="checkNumber(this);setTotal(this.form);" onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratproject"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratproject" size=5 maxlength=3 value="${item.ratproject}" onKeyUp="checkNumber(this);setTotal(this.form);" onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratforum"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratforum" size=5 maxlength=3 value="${item.ratforum}" onKeyUp="checkNumber(this);setTotal(this.form);"  onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratoffline"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratoffline" size=5 maxlength=3 value="${item.ratoffline}" onKeyUp="checkNumber(this);setTotal(this.form);"  onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratetc"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratetc" size=5 maxlength=3 value="${item.ratetc}" onKeyUp="checkNumber(this);setTotal(this.form);"  onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratadd"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><input type=text name="ratadd" size=5 maxlength=3 value="${item.ratadd}" onKeyUp="checkNumber(this);setTotal(this.form);"  onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;">% </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.total"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><span id="showTot">0</span><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.total2"/></td>
    </tr>
    <tr>
        <td colspan="4" align="center"><a href="javascript:doWrite()"><mfmt:button bundle="button" key="button.save"/></a></td>
    </tr>
<input type=hidden name="ratattend" value="${item.ratattend}">
<input type=hidden name="ratquiz" value="${item.ratquiz}">
</form>
</table>
<script language="javascript" >
<!--
function frmSubmit(frm) {
    if (validate(frm)) {
        if(setTotal(frm) == 100) {
            frm.${mrt:mvcCmd()}.value="update";
            return true;
        } else {
            //alert('<mfmt:message bundle="common.scripts" key="script.wla_rat_mst.msg1"/>');
            maf.alert('common.scripts','script.wla_rat_mst.msg1');
            return false;
        }
    } else {
        return false;
    }
}

function doWrite(){
    var frm = getObject("myform");
    if(validate(frm))   {
        if(setTotal(frm) == 100) {
            frm.${mrt:mvcCmd()}.value="update";
            frm.submit();
        } else {
            //alert('<mfmt:message bundle="common.scripts" key="script.wla_rat_mst.msg1"/>');
            maf.alert('common.scripts','script.wla_rat_mst.msg1');
            return;
        }
    } else {
        return;
    }
}
//-->
</script>
<script language="javascript">
<!--
    function containsCharsOnly(input,chars) {
        for (var inx = 0; inx < input.value.length; inx++) {
           if (chars.indexOf(input.value.charAt(inx)) == -1)
               return false;
        }
        return true;
    }

    /**
     * 입력값에 숫자만 있는지 체크
     */
    function isNumber(input) {
        var chars = "0123456789";
        return containsCharsOnly(input,chars);
    }

    function checkNumber(input) {
        if(!isNumber(input)) {
            //alert('<mfmt:message bundle="common.scripts" key="script.only.number"/>');
            maf.alert('common.scripts','script.only.number');
        }
    }
    
    // jindo rate
    function setJindo(form) {
        var total=0;
        total = eval(form.jindo_time.value) + eval(form.jindo_page.value);
        form.ratjindo.value=total;
    }
    
    // total rate
    function setTotal(form) {
        var total=0;
        total = eval(form.jindo_time.value) + eval(form.jindo_page.value)  + eval(form.ratexam.value);
        total += eval(form.ratreport.value) + eval(form.ratproject.value);
        total += eval(form.ratforum.value) + eval(form.ratoffline.value);
        total += eval(form.ratetc.value) + eval(form.ratadd.value);
        var obj = getObject("showTot");
        obj.innerText = total;
        return total;
    }
        
    setTotal(document.myform);
-->
</script>
