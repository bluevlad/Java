// java (Integer.MAX_VALUE)
var INT_MAX_VALUE = (Math.pow(2, 32) / 2) - 1;

    //입력 체크
    function checkInputForm( field, field_name )
    {
        if( !!field && field.value == "" )  // 필수 입력 및 내용에 공백허용
        {
            alert(field_name+"을(를) 입력하여 주십시오.");
            field.focus();
            return false;
        }else{
            return true;
        }
    }

  //body onload관련 (firefox등 고려)
    function pageonReady(){
        if(window.addEventListener){
            window.addEventListener("load", pageInit , false);
        }else if(window.attachEvent){
            window.attachEvent("onload", pageInit );
        }else{
            window.onload = pageInit ;
        }
    }

    //입력내용에 공백허용 여부 체크
    function checkInputBlankForm( field, field_name )
    {
        if( field.value.indexOf(" ") > -1 )
        {
            alert(field_name+"에 공백을 제거해 주십시오.");  // 내용에 공백허용 안함
            field.focus();
            return false;
        }
    }


    // 숫자만 입력 체크
    function OnNumberChk( field )
    {
        Result = "true";
        key = event.keyCode;

        if( key == 8 || key == 9 || key == 13 || key == 36 || key == 37 || key == 38 || key == 39 || key == 40 || key == 46 || key == 144 || key == 229 || ( key >= 48 && key <= 57) )
        {
        }
        else if( key == 189 ) // 음수일 경우
        {
            frtData = field.value.substring(0,1);
            if( frtData != "-" )  // 첫번째 자리가 음수인지 확인
            {
                Result = "false";
            }
            else
            {
                arrData = field.value.split("-");
                if( arrData.length > 2 )  // '-' 기호의 중복 입력 여부 확인
                {
                    Result = "false";
                }
            }
        }
        else if( key == 190 ) // 소수점이 포함된 숫자일 경우
        {
            arrData = field.value.split(".");
            if( arrData.length > 2 )  // '.' 기호의 중복 입력 여부 확인
            {
                Result = "false";
            }
        }
        else
        {
            Result = "false";
        }

        if( Result == "false" )
        {
            alert("숫자만 입력해주세요.");
            field.value = "";
            field.focus();
            event.returnValue = false;
            return;
        }
        return Result;
    }

    /**
     * 숫자인지 체크
     * @param obj
     * @param msg
     * @return
     */
     function isNumber(obj, msg) {
         var val = trim(obj.value);
         if(val.length < 1) {
             return false;
         }
         for(i=0; i<val.length; i++) {
             iBit = parseInt(val.substring(i,i+1)); //문자(Char)를 숫자로 변경
             if(('0' < iBit) || ('9' > iBit)) {
                 //alert(i+':'+iBit+':'+'Mun');
             } else {
                 alert(msg);
                 obj.value = "";
                 obj.focus();
                 return false;
             }
         }
         return true;
     }

    //백분율(%) 연산 결과 구하기
    function getFromPercent( TargetData, Percent )
    {
        perRet = parseInt(TargetData * Percent / 100)

        return perRet;
    }

    //백분율(%) 소수 2째자리까지 구하기
    function getToPercent( TargetData, divData )
    {
        Ret = parseInt( divData/TargetData * 100 * 100 );

        perRet = Ret / 100;

        return perRet;
    }

    //숫자 3자리 단위 , 표시하기
    function SetComma(frm) {
        var rtn = "";
        var val = "";
        var j = 0;
        x = frm.value.length;

        for(i=x; i>0; i--) {
        if(frm.value.substring(i,i-1) != ",") {
        val = frm.value.substring(i,i-1)+val;
        }
        }
        x = val.length;
        for(i=x; i>0; i--) {
        if(j%3 == 0 && j!=0) {
        rtn = val.substring(i,i-1)+","+rtn;
        }else {
        rtn = val.substring(i,i-1)+rtn;
        }
        j++;
        }
        frm.value = rtn;
    }

    function addComma(n) {
        if (isNaN(n)) {
            return 0;
        }
        var reg = /(^[+-]?\d+)(\d{3})/;
        n += '';
        while (reg.test(n))
            n = n.replace(reg, '$1' + ',' + '$2');
        return n;
    }

    // 공백제거
    function trim(str)
    {
      str = str.replace(/^\s*/,'').replace(/\s*$/, '');
      return str;
    }

    function replaceAll(str)
    {
        str = str.replace(/,/gi,'');
        return str;
    }


    // 페이지 Action 처리
    function OkSubmit( FormObj, target, method, url )
    {
      FormObj.method = method;
      FormObj.target = target;
      FormObj.action = url;
      FormObj.submit();
    }


    // 메뉴 Action 처리
    function goMenu(muVal, prefix)
    {
      var url;
      prefix = (!!prefix) ? prefix : '';
      FormObj = document.frmMenu;

      if(muVal == "1"){
          url = prefix + "/site/mainMngList.do?topCd=1";	// 싸이트관리
      }else if(muVal == "2"){
          url = prefix + "/auth/workGroupList.do?topCd=2";	// 권한관리
      }else if(muVal == "3"){
          url = prefix + "/prod/categoryList.do?topCd=3";	// 상품관리
      }else if(muVal == "4"){
          url = prefix + "/cs/seedMngList.do?topCd=4";	// C/S관리
      }else if(muVal == "5"){
          url = prefix + "/stat/salesStatList.do?topCd=5";	// 통계관리
      }else if(muVal == "6"){
          url = prefix + "/lotto/lottoAdmin.do?topCd=6";	// 행운번호관리
      }

      FormObj.topCd.value=muVal;
      FormObj.target = "_self";
      FormObj.action = url;
      FormObj.submit();
    }

    // 팝업
    function uf_popOpen(url, p_w, p_h, title) {
        if(typeof(title) == "undefined"){
            title = "";
        }
        window.open(url,title,"width="+p_w+",height="+p_h+",toolbar=no,scrollbars=no,resizable=no" );
    }

    // 팝업
    function uf_popOpenWithScrollbars(url, p_w, p_h, title) {
        if(typeof(title) == "undefined"){
            title = "";
        }
        window.open(url,title,"width="+p_w+",height="+p_h+",toolbar=no,scrollbars=yes,resizable=no" );
    }

    // 팝업Dialog
    function uf_popDialogOpen(url, p_w, p_h, args) {
        if(typeof(args) == "undefined"){
            args = new Array();
        }
        return window.showModalDialog(url,args,"dialogWidth:"+p_w+"px;dialogHeight:"+p_h+"px;center:yes;help:no;status:no;scroll:no;resizable:no");
    }

    // 페이지 이동
    function fnGoToPageNo(pno)
    {
        var trgtName = '';
        var frmName = 'frm';
        var objForm = eval("document."+frmName);
        objForm.page_no.value = pno;

        if (ActionNm == null || ActionNm == undefined) ActionNm = '';
        tcSubmit(objForm, ActionNm, trgtName);
    }


    // SUBMIT Script
    function tcSubmit(objForm, actName, trgtName)
    {
        objForm.target = trgtName;

        if(actName != '') {
            if(actName.indexOf(".") < 0){
                objForm.action = actName+".do";
            }else{
                objForm.action = actName;
            }
        } else {
            objForm.action = '';
        }
        objForm.submit();
    }


    // 로그아웃
    function goLogOut()
    {
        document.location = "loginOut.do";
    }


    //파일다운로드 요청
    function OnFileDownLoad( FileName, FilePath )
    {
        document.location = "/filedownload/filedownload.php?filepath="+FilePath+"&filename="+FileName;
    }

    // 페이지 로딩 애러시 분기 페이지
    function ErrorLocation()
    {
        document.location = "/error/error01.htm" ;
    }

    function OnLogout()
    {
        if( !confirm("로그아웃하시겠습니까?") )
        {
            return;
        }

        document.location = "../login/logout.php";
    }

    function setProgress( Div )
    {
        if( Div == "ON" )
        {
            progressDiv1.style.visibility =  'visible';
        }
        else
        {
            for( i = 0; i <= 2000000; i++ ){}

            progressDiv1.style.visibility =  'hidden';
        }
    }


/* 숫자를 comma(,)로 구분하기 */
/* 사용법 : onKeyUp="makeComma(this)" */
function makeComma(o) {
    if ((event.keyCode<37) || (event.keyCode>40)) {
        o.value = getCommaFormat(o.value);
    }
}


/* 숫자를 comma(,)로 구분된 값을 리턴한다. */
function getCommaFormat(s) {
  s = getOnlyNumber(String(s));
  if ( ! s) return "";
  return formatComma(""+Number(s));
}

/* 숫자값만을 리턴한다. */
function getOnlyNumber(s) {
  return s.replace(/[^\d]/g, '');
}

/* 숫자값에 3자리마다 comma(,)를 넣어준다. */
function formatComma(s) {
  var r = '';
  var c;
  s = getOnlyNumber(s);
  for(var ndx=s.length-1, b=0; ndx>=0; ndx--, b++) {
    c = s.charAt(ndx);
    r = ( b!=0 && b%3==0 ) ? (c+','+r):(c+r);
  }
  return r;
}


/* 숫자를 comma(,)로 구분하기 */
/* 사용법 : onKeyUp="makeComma(this)" */
function makeComma2(o) {
    if ((event.keyCode<37) || (event.keyCode>40)) {
        o.value = comma_value(o.value);
    }
}
/*  숫자입력시 3자리마다 자동으로 콤마 찍기  */
function comma_value(sval)
{
   if (event.keyCode != 9)
   {
       var cur = sval.value;
       var setMinus = 0;

       if (cur.charAt(0) == "-") {
           setMinus = 1;
       }

       cur=cur.replace(/[^.0-9]/g ,"");
       cur=cur.replace(/[.]+/g ,".");

       if (setMinus == 1)
           sval.value = "-" + formatNumbertoString(cur);
       else
           sval.value = formatNumbertoString(cur);
   }
}


/* Spec   : 숫자입력시 3자리마다 자동으로 콤마 찍기 */
function formatNumbertoString(cur)
{
   leftString = cur;
   rightString = ".";
   dotIndex = 0;

   for(i = 0; i < cur.length; i++){
    // 1) '.'이 처음에 입력 되었을때 앞에 0을 더해 "0."을 리턴
  // 2) "0."이외의 입력 일 때 "0"만 리턴
    if(cur.charAt(i) == "." || (cur.length > 1 && cur.charAt(0) == "0" && cur.charAt(1) != "."))
  {
     dotIndex = i;
     if(dotIndex == 0)
  {
               if   (cur.charAt(0) == ".")   leftString="0.";
               else                          leftString="";
      return leftString;
     }
     break;
    }
   }

    if(dotIndex != 0) //dot가 있을 경우..
   {
    leftString = cur.substr(0, dotIndex);
    rightString = cur.substr(dotIndex+1);
    rightString = rightString.replace(/\./g,"");
   }
   else //없으면..
   {
    leftString = cur;
   }

   len=leftString.length-3;
   while(len>0)
   {
       leftString=leftString.substr(0,len)+","+leftString.substr(len);
       len-=3;
   }

   if(rightString != ".")
       return (leftString + "." + rightString);
   else
       return leftString;
}



//문자열의 공백을 모조리 제거
function removeSpace(value)
{
    var regExp = / /gi;
    return value.replace(regExp, "");
}

//숫자만 입력가능하게함 사용예<   onKeyDown="numChk()"   >
function numChk()
{
    var iKey = event.keyCode;    //window.event.keyCode;

    // 8: backspace, 9: Tab, 37: left, 39: right, 46: delete
    if((iKey >= 48 && iKey <= 57 ) || (iKey >= 96 && iKey <= 105) || iKey == 8 || iKey == 9 || iKey == 37 || iKey == 39 || iKey == 46) {
        return;
    } else {
        event.returnValue = false;
    }
}

//숫자와 하이펀만 가능(onKeyDown)
function onlyNumberAndHiphone()
{
    var iKey = event.keyCode;    //window.event.keyCode;

    // 8: backspace, 9: Tab, 37: left, 39: right, 46: delete, 109,189: -(하이픈)
    if((iKey >= 48 && iKey <= 57 ) || (iKey >= 96 && iKey <= 105) || iKey == 8 || iKey == 9 || iKey == 37 || iKey == 39 || iKey == 46 || iKey == 109 || iKey == 189) {
        return;
    } else {
        event.returnValue = false;
    }
}

/* 백분율을 계산한 값을 리턴한다. */
function percentage(total, rate) {
    var ret = (rate / 100) * total;
    return ret;
}

function isEmpty( data )
{
    for( var i = 0 ; i < data.length ; i++ )
    {
        if( data.substring( i , i + 1 ) != " " )
        {
            return false;
        }
    }
    return true;
}

//숫자를 통화단위로 표시해줌
function to_Currency(val){
    var reg = /(^[+-]?\d+)(\d{3})/;
    val += "";
    while(reg.test(val)){
        val = val.replace(reg, '$1' + ',' + '$2');
    }
    return val;
}

// 소스내 스크립트 사용
function isSecuCheck(){
    var inputItem = document.getElementsByTagName("input");
      var ptn = /[<|>]/g;
      for(i=0;i<inputItem.length;i++){
       if(inputItem[i].value.match(ptn)){
        alert("'<'또는'>'는 사용할 수 없습니다.");
        inputItem[i].focus();
        return false;
       }
    }
    return true;
}

function fillZero(orgText,sz){
    var tt = orgText+"";
    while(tt.length < sz){
        tt = "0"+tt;
    }
    return tt;
}


// 숫자만 입력
function fn_OnlyNumber(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
        return;
    } else {
        alert("숫자만 입력 가능합니다.");
        return false;
    }
}

// 숫자만 입력
function removeChar(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
}


(function($) {
    $.fn.emptySelect = function(index) {
      return this.each(function(){
        if (this.tagName=='SELECT') this.options.length = index;
      });
    };

    $.fn.loadSelectComboBox = function(optionsDataArray, selectCode, code, name, remainOption) {
        if(typeof(remainOption) === "undefined"){
            remainOption = 1;
        }
        return this.emptySelect(remainOption).each(function(){
          if (this.tagName=='SELECT') {
            var selectElement = this;
            $.each(optionsDataArray,function(index,optionData){
              var option = new Option(optionData[name],
                                      optionData[code],true,
                                      optionData[code]==selectCode);
              if ($.browser.msie) {
                selectElement.add(option);
              }
              else {
                selectElement.add(option,null);
              }
            });
          }
        });
      };

      $.fn.ajaxInitSelect = function(_settings) {
          if (!this.length) {
              return this;
          }

          var _this = this;
          var settings = {
              default_val : '',
              code : 'CODE',
              name : 'CODE_NAME',
              remainOption : 1,
              type : "post",
              url : "",
              datatype : "json",
              async : false,
              data : null,
              cache : false,
              error : function() {
                  alert('error');
              },
              success : function(rtnData) {
                  _this.loadSelectComboBox(rtnData,this.default_val,this.code, this.name, this.remainOption);
              },
              complete : function(jqXHR, textStatus){}
          };
          settings = jQuery.extend(settings, _settings);
          $.ajax({
              url: settings.url,
              default_val: settings.default_val,
              code: settings.code,
              name: settings.name,
              remainOption: settings.remainOption,
              cache: settings.cache,
              async: settings.async,
              type: settings.type,
              dataType: settings.dataType,
              data: settings.data,
              success: settings.success,
              error: settings.error,
              complete: settings.complete
          });
          return _this;
      };
})(jQuery);
