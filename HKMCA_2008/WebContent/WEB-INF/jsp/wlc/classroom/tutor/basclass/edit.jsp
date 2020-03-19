<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>    

<script language="javascript" >
<!--
function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
        if (validate(frm)) {
            if(setTotal(frm) == 100) {
                frm.cmd.value="update";
                frm.submit();
            } else {
                alert('<mfmt:message bundle="common.scripts" key="wla_rat_mst.msg1"/>');
                return false;
            }
        } else {
            return false;
        }
    }
}

function doWrite(){
    var frm = getObject("myform");
    if(validate(frm))   {
        frm.cmd.value = "update";
        frm.submit();
    } else {
        return;
    }
}
//-->
</script>

<div class="viewContainer">
<mfmt:message bundle="common.lec" key="progress.basis.mng"/>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" name="cmd" value=""/>  
<mf:input type="hidden" name="lec_cd" value="${lec_cd}" />
<mf:input type="hidden" name="ratattend" value="${item.ratattend}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">  
    <tr>
        <th colspan=3><mfmt:message bundle="common.lec" key="progressbasis.msg1"/> </th>
    </tr>
    <tr>
        <th width=5%>&nbsp;</th>
        <th width=85%><mfmt:message bundle="common.lec" key="progressbasis.title"/></th>
        <th width=10%><mfmt:message bundle="common.lec" key="progressbasis.isuse"/></th>
    </tr>
    <tr>
        <th width=5%>1. </th>
        <td width=85% align="left">&nbsp;<mfmt:message bundle="common.lec" key="progressbasis.basis01"/></td>
        <td width=10% align="center" bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item.prg_type == '1' || item.prg_type=='3' || item.prg_type=='5' || item.prg_type=='7' || item.prg_type=='9' || item.prg_type=='11' || item.prg_type=='13' || item.prg_type=='15'}">
            <input type=checkbox name="prg_type" value="1" checked>
            </c:when>
            <c:otherwise>
            <input type=checkbox name="prg_type" value="1">
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th width=5%>2. </th>
        <td width=85% align="left">&nbsp;<mfmt:message bundle="common.lec" key="progressbasis.basis03"/>&nbsp;&nbsp;<mf:input type="text" name="lec_datm" size="3" maxlength="3" value="${item.lec_datm}" onkeyup="checkNumber(this);" option="number" /><mfmt:message bundle="common.lec" key="progressbasis.msg3"/></td>
        <td width=10% align="center" bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item.prg_type == '4' || item.prg_type=='5' || item.prg_type=='6' || item.prg_type=='7' || item.prg_type=='12' || item.prg_type=='13' || item.prg_type=='14' || item.prg_type=='15'}">
            <input type=checkbox name="prg_type" value="4" checked>
            </c:when>
            <c:otherwise>
            <input type=checkbox name="prg_type" value="4">
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th width=5%>3. </th>
        <td width=85% align="left">&nbsp;<mfmt:message bundle="common.lec" key="progressbasis.basis04"/></td>
        <td width=10% align="center" bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item.prg_type == '8' || item.prg_type=='9' || item.prg_type=='10' || item.prg_type=='11' || item.prg_type=='12' || item.prg_type=='13' || item.prg_type=='14' || item.prg_type=='15'}">
            <input type=checkbox name="prg_type" value="8" checked>
            </c:when>
            <c:otherwise>
            <input type=checkbox name="prg_type" value="8">
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th width=5%>4. </th>
        <td width=85% align="left">&nbsp;<mfmt:message bundle="common.lec" key="progressbasis.basis05"/>(<mfmt:message bundle="common.lec" key="progressbasis.msg2"/>)</td>
        <td width=10% align="center" bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item.lec_weck == 'Y'}">
            <input type=checkbox name="lec_weck" value="Y" checked>
            </c:when>
            <c:otherwise>
            <input type=checkbox name="lec_weck" value="Y">
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th width=5%>5. </th>
        <td width=85% align="left">&nbsp;<mfmt:message bundle="common.lec" key="progressbasis.basis06_1"/>&nbsp;&nbsp;<mf:input type="text" name="exm_condition" size="3" maxlength="3" value="${item.exm_condition}" onkeyup="checkNumber(this);" option="number" /><mfmt:message bundle="common.lec" key="progressbasis.basis06_2"/></td>
        <td width=10% align="center" bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item.prg_type == '2' || item.prg_type=='3' || item.prg_type=='6' || item.prg_type=='7' || item.prg_type=='10' || item.prg_type=='11' || item.prg_type=='14' || item.prg_type=='15'}">
            <input type=checkbox name="prg_type" value="2" checked>
            </c:when>
            <c:otherwise>
            <input type=checkbox name="prg_type" value="2">
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
<br/>
<mfmt:message bundle="common.lec" key="estimat.basis.mng"/> :
<mfmt:message bundle="common.lec" key="finished_score"/> = <c:out value="${item.finished_score}"/>, &nbsp;&nbsp;
<mfmt:message bundle="common.lec" key="limit_study_time"/> = <c:out value="${item.limit_study_time}"/><mfmt:message bundle="common" key="time.min"/>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="view"> 
    <col width="20%"/>
    <col width="30%"/>
    <col width="20%"/>
    <col width="30%"/>
    <tr>
        <th colspan=4 height="28" class="view"><mfmt:message bundle="table.wlc_rat_mst" key="noti"/></th>
    </tr>
    <tr> 
        <th height="28"><mfmt:message bundle="table.wlc_rat_mst" key="item"/></th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="rate"/></th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="item"/></th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="rate"/></th>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wlc_rat_mst" key="jindo_time"/></th>
        <td class="view" bgcolor="#FFFFFF">
            <mh:iif var="jindo_time" test="${ritem.jindo_time == '' || ritem.jindo_time == null }" trueValue="0" falseValue="${ritem.jindo_time}"/>
            <mf:input type="text" name="jindo_time" size="5" maxlength="3" value="${jindo_time}" onkeyup="checkNumber(this);setTotal(this.form);setJindo(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
        <th class="view" align="center"><mfmt:message bundle="table.wlc_rat_mst" key="jindo_page"/></th>
        <td class="view" bgcolor="#FFFFFF">
            <mh:iif var="jindo_page" test="${ritem.jindo_page == '' || ritem.jindo_page == null }" trueValue="0" falseValue="${ritem.jindo_page}"/>
            <mf:input type="text" name="jindo_page" size="5" maxlength="3" value="${jindo_page}" onkeyup="checkNumber(this);setTotal(this.form);setJindo(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratjindo"/></th>
        <td colspan="3">
            <mf:input name="ratjindo" size="3" maxlength="4" value="${ritem.ratjindo}" readonly="true" />%
            (<mfmt:message bundle="table.wlc_rat_mst" key="ratjindo1"/>)
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratexam"/></th>
        <td>
            <mh:iif var="ratexam" test="${ritem.ratexam == '' || ritem.ratexam == null}" trueValue="0" falseValue="${ritem.ratexam}"/>
            <mf:input type="text" name="ratexam" size="5" maxlength="3" value="${ratexam}" onkeyup="checkNumber(this);setTotal(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratreport"/></th>
        <td>
            <mh:iif var="ratreport" test="${ritem.ratreport == '' || ritem.ratreport == null}" trueValue="0" falseValue="${ritem.ratreport}"/>
            <mf:input type="text" name="ratreport" size="5" maxlength="3" value="${ratreport}" onkeyup="checkNumber(this);setTotal(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratproject"/></th>
        <td>
            <mh:iif var="ratproject" test="${ritem.ratproject == '' || ritem.ratproject == null}" trueValue="0" falseValue="${ritem.ratproject}"/>
            <mf:input type="text" name="ratproject" size="5" maxlength="3" value="${ratproject}" onkeyup="checkNumber(this);setTotal(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratdiscuss"/></th>
        <td>
            <mh:iif var="ratdiscuss" test="${ritem.ratdiscuss == '' || ritem.ratdiscuss == null}" trueValue="0" falseValue="${ritem.ratdiscuss}"/>
            <mf:input type="text" name="ratdiscuss" size="5" maxlength="3" value="${ratdiscuss}" onkeyup="checkNumber(this);setTotal(this.form);" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratoffline"/></th>
        <td>
            <mh:iif var="ratoffline" test="${ritem.ratoffline == '' || ritem.ratoffline == null}" trueValue="0" falseValue="${ritem.ratoffline}"/>
            <mf:input type="text" name="ratoffline" size="5" maxlength="3" value="${ratoffline}" onkeyup="checkNumber(this);setTotal(this.form);"  onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratforum"/></th>
        <td>
            <mh:iif var="ratforum" test="${ritem.ratforum == '' || ritem.ratforum == null}" trueValue="0" falseValue="${ritem.ratforum}"/>
            <mf:input type="text" name="ratforum" size="5" maxlength="3" value="${ratforum}" onkeyup="checkNumber(this);setTotal(this.form);"  onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratetc"/></th>
        <td>
            <mh:iif var="ratetc" test="${ritem.ratetc == '' || ritem.ratetc == null}" trueValue="0" falseValue="${ritem.ratetc}"/>
            <mf:input type="text" name="ratetc" size="5" maxlength="3" value="${ratetc}" onkeyup="checkNumber(this);setTotal(this.form);"  onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="ratadd"/></th>
        <td>
            <mh:iif var="ratadd" test="${ritem.ratadd == '' || ritem.ratadd == null}" trueValue="0" falseValue="${ritem.ratadd}"/>
            <mf:input type="text" name="ratadd" size="5" maxlength="3" value="${ratadd}" onkeyup="checkNumber(this);setTotal(this.form);"  onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" />% 
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wlc_rat_mst" key="total"/></th>
        <td colspan=3 class="view" bgcolor="#FFFFFF"><span id="showTot">0</span><mfmt:message bundle="table.wlc_rat_mst" key="total2"/></td>
    </tr>
</table>
</mf:form>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">   
  <tr>
    <td align="right"><mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/></td>
  </tr>
</table>
</div>

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
     * 
     */
    function isNumber(input) {
        var chars = "0123456789";
        return containsCharsOnly(input,chars);
    }

    function checkNumber(input) {
        if(!isNumber(input)) {
            alert('<mfmt:message bundle="common.scripts" key="only.number"/>');
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
        total = eval(form.jindo_time.value) + eval(form.jindo_page.value);
        total += eval(form.ratexam.value);
        total += eval(form.ratreport.value);
        total += eval(form.ratproject.value);
        total += eval(form.ratdiscuss.value);
        total += eval(form.ratforum.value);
        total += eval(form.ratoffline.value);
        total += eval(form.ratetc.value);
        total += eval(form.ratadd.value);
         var obj = getObject("showTot");
        obj.innerText = total;
        return total;
    }
    setTotal(document.myform);
//-->
</script>