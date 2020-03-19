<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%@ page import="modules.etest.support.Etest" %>
<script language="javascript"   >
function type_reload(formName){
	var form = getObject(formName);
    var type = form.quetype.value;    
    if(type=='<%=Etest.QUE_TYPE_SINGLE%>'){  //single선택형
        form.quecount.selectedIndex=form.tmp.value;
        view_reload(formName, type);
        form.quecount.disabled=false;
        getObject("answer").style.display = "none";
    }else if(type=='<%=Etest.QUE_TYPE_MULTI%>'){  //multi선택형
        form.quecount.selectedIndex=form.tmp.value;
        view_reload(formName, type);
        form.quecount.disabled=false;
        getObject("answer").style.display = "none";
    }else if(type=='<%=Etest.QUE_TYPE_TEXT%>'){    //단답형
        form.quecount.selectedIndex=0;
        view_reload(formName, type);
        form.quecount.disabled=true;
        getObject("answer").style.display = "";
    }else if(type=='<%=Etest.QUE_TYPE_DESCRIPTION%>'){    //서술형
        form.quecount.selectedIndex=0;
        view_reload(formName, type);
        form.quecount.disabled=true;
        getObject("answer").style.display = "";
    }
}

function view_reload(formName, type){
	var form = getObject(formName);
    var cnt = parseInt(form.tmp.value);
    
    var view;
    for(i=1; i<=20; i++){
    	var sv = getObject("SviewTEXT"+i);
        var sm = getObject("MviewTEXT"+i);
        if(i<=cnt && (type=='<%=Etest.QUE_TYPE_SINGLE%>' || type=='<%=Etest.QUE_TYPE_MULTI%>')){
 			if(sv && sm ) {
	            if(type=='<%=Etest.QUE_TYPE_SINGLE%>'){  //single 선택형
	            	sv.style.display="";	
	            	sm.style.display="none";
	            }else if(type=='<%=Etest.QUE_TYPE_MULTI%>'){  //multi 선택형
	            	sv.style.display="none";	
	            	sm.style.display="";
	            }
	        }
        }else{
            sv.style.display = "none";
            sm.style.display = "none";
        }
    }
}
function changeQueuCount() {
	var frm = getObject('myform');
	frm.tmp.value=frm.quecount.value;
	view_reload('myform', frm.quetype.value)
}
function CheckboxCheck(f){
    var flag=true;

    // 여러개 체크했을 경우
    if(f.length!='undefined'){
        for ( var i=0; i<f.length; i++) {
            if (f[i].checked == true) {
                flag = false;
            }
        }
    // 1개만 체크 했을 경우
    }else if(f.value!='undefined'){
        if(f.checked==true){
            flag = false;
        }
    }
    
    if (flag) {
        return true;
    } else {
        return false;
    }
}

function gogogo(form, sel){
    if( !validate(form) ) {
        return false;
    }
    var sw = true;
    if(sel==1){ //등록
        if(form.quescore.value==""){
            alert('<mfmt:message bundle="etest.common" key="script.exm.score"/>');
            form.quescore.focus();
            sw = false;
        }else if(form.quetitle.value==""){
            alert('<mfmt:message bundle="etest.common" key="script.exm.title"/>');
            form.quetitle.focus();
            sw = false;
        }else{
            switch (form.quetype.value){
                case "<%=Etest.QUE_TYPE_SINGLE%>":   
                    for(i=1; i<=parseInt(form.quecount.value); i++){    //보기를 입력하지 않은 경우
                    	
                        if(eval("form.Squeviw"+i).value=="" ){
                            alert('<mfmt:message bundle="etest.common" key="script.exm.view"/>');
                            eval("form.Squeviw"+i).focus();
                            sw = false;
                            break;  //for end...
                        }
                    }
                    if(sw==true){
                        cnt=0;
                        for(i=0; i<form.queansw_Scheck.length; i++){
                            if(form.queansw_Scheck[i].checked==true){
                                cnt++;   //정답 개수
                            }
                        }
                        if(cnt==0){
                            alert('<mfmt:message bundle="etest.common" key="script.exm.answer.select"/>');
                            sw = false;
                        }
                    }
                    break;
                    
                case "<%=Etest.QUE_TYPE_MULTI%>":   
                    for(i=1; i<=parseInt(form.quecount.value); i++){    //보기를 입력하지 않은 경우
                        if(eval("form.Mqueviw"+i).value=="" ){
                            alert('<mfmt:message bundle="etest.common" key="script.exm.view"/>');
                            eval("form.Mqueviw"+i).focus();
                            sw = false;
                            break;  //for end...
                        }
                    }
                    if(sw==true && CheckboxCheck(form.queansw_Mcheck)==true){
                        alert('<mfmt:message bundle="etest.common" key="script.exm.answer.select"/>');
                        sw = false;
                    }
                    break;

                case "<%=Etest.QUE_TYPE_TEXT%>":   
                    if(form.queansw_word.value==""){ //정답을 입력하지 않은경우
                        alert('<mfmt:message bundle="etest.common" key="script.exm.answer.insert"/>');
                        form.queansw_word.focus();
                        sw = false;
                    }
                    break;

                case "<%=Etest.QUE_TYPE_DESCRIPTION%>": 
                    break;

                default:
                    sw = false;
                    break;
            }
            
            if(sw){
                if(form.quetype.value=="<%=Etest.QUE_TYPE_SINGLE%>"){
                    form.queviw1.value=form.Squeviw1.value;
                    form.queviw2.value=form.Squeviw2.value;
                    form.queviw3.value=form.Squeviw3.value;
                    form.queviw4.value=form.Squeviw4.value;
                    form.queviw5.value=form.Squeviw5.value;
                    form.queviw6.value=form.Squeviw6.value;
                    form.queviw7.value=form.Squeviw7.value;
                    form.queviw8.value=form.Squeviw8.value;
                    form.queviw9.value=form.Squeviw9.value;
                    form.queviw10.value=form.Squeviw10.value;
                }else if(form.quetype.value=="<%=Etest.QUE_TYPE_MULTI%>"){
                    form.queviw1.value=form.Mqueviw1.value;
                    form.queviw2.value=form.Mqueviw2.value;
                    form.queviw3.value=form.Mqueviw3.value;
                    form.queviw4.value=form.Mqueviw4.value;
                    form.queviw5.value=form.Mqueviw5.value;
                    form.queviw6.value=form.Mqueviw6.value;
                    form.queviw7.value=form.Mqueviw7.value;
                    form.queviw8.value=form.Mqueviw8.value;
                    form.queviw9.value=form.Mqueviw9.value;
                    form.queviw10.value=form.Mqueviw10.value;
                }
                 <c:set var="cmd" value="insert"/>
                <c:if test="${param.cmd=='edit'}">
                    <c:set var="cmd" value="update"/>
                </c:if>
                
                form.cmd.value='<c:out value="${cmd}"/>'
                form.submit();
            }
        }
    }else if(sel==2){   //리스트
        document.location.href = "";
    }
    return sw;
}

function checkNumber(input) {
    if(!isNumber(input)) {
        malert('<mfmt:message bundle="common.scripts" key="script.alert.only.number"/>');
        setEmpty();
    }else if(parseInt(input.value)<0 || parseInt(input.value)>100){
        alert('<mfmt:message bundle="common.scripts" key="script.alert.score.limit.fail"/>');
        setEmpty();                
    }
}

function isNumber(el) {
    var pattern = /^[0-9]+$/;
    return (pattern.test(el.value)) ? true : false;
}
</script>