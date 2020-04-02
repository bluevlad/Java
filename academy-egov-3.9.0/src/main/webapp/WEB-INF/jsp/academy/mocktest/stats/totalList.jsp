<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head></head>

<div id="content">
    <h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonList.do' />">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="MOCKCODE" name="MOCKCODE" value="">

    <!--테이블-->
    <table class="table01">
        <caption></caption>
        <colgroup>
        <col width="15%">
        <col width="*">
        <col width="15%">
        <col width="30%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <select style="width:55px;" id="SEARCHYEAR" name="SEARCHYEAR">
                    <option value="">년도</option>
	                <c:forEach var="i" begin="2016" end="2020" step="1">
	                <option value="${i}" <c:if test="${params.S_EXAMYEAR == i}">selected</c:if>><c:out value="${i}"/></option>
					</c:forEach>
                </select>&nbsp;
                <select style="width:50px;" id="SEARCHROUND" name="SEARCHROUND">
                    <option value="">회차</option>
                    <c:forEach var="i" begin="1" end="30" step="1">
	                <option value="${i}" <c:if test="${params.SEARCHROUND == i}">selected</c:if>><c:out value="${i}"/></option>
					</c:forEach>
                </select>&nbsp;
                <select style="width:100px;"  id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="" >전체검색</option>
                    <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>모의고사명</option>
                    <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>직급/직렬</option>
                </select>
                <input class="i_text"  title="검색" type="text" id="SEARCHTEXT" name="SEARCHTEXT" type="text" style="width:160px;"  value="${params.SEARCHTEXT}"/>&nbsp;&nbsp;
            </td>
            <th>화면출력건수</th>
            <td>               
                <input title="검색" type="text" id="pageRow" name="pageRow" size="5" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow}" onkeypress="fn_RowNumCheck()">
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="javascript:fn_Search();">검색</button></span>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="5%">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="10%">
            <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col"><div class="item">NO</div></th>
            <th scope="col">년</th>
            <th scope="col">회차</th>
            <th scope="col">응시<br/>형태</th>
            <th scope="col">직급</th>
            <th scope="col">모의고사명</th>
            <th scope="col">통계확인</th>
            <!-- 
            <th scope="col">조정점수</th>
             -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="index">
        <tr>
            <td>${totalCount - (( params.currentPage - 1) * params.pageRow) - index.index}</td>
            <td>${item.EXAMYEAR}</td>
            <td>${item.EXAMROUND}</td>
            <td>
                <c:if test="${item.ISEXAMTYPEON eq 1}">[ON] </c:if>
                <c:if test="${item.ISEXAMTYPEOFF eq 1}"> [OFF]</c:if>
            </td>
            <td>${item.CLASSCODENM}</td>
            <td>${item.MOCKNAME}</td>
            <td><c:if test="${item.SCOREYN > 0 }"><span class="btn_pack medium"><button type="button" onclick="javascript:fn_View('${item.MOCKCODE}');">확인</button></span></c:if></td>
            <!-- 
            <td><span class="btn_pack medium"><a href="javascript:fn_makeAdj('${item.MOCKCODE}');">[조정점수반영]</a></span></td>
             -->
        </tr>
        </c:forEach>
        <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="7">데이터가 존재하지 않습니다.</td>
        </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->

</form>
</div>
<!--//content -->

<script type="text/javascript">
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/stats/statsTotalList.do' />");
    $("#form").submit();
}

function fn_Search() {
    $("#currentPage").val(1);
    $("#form").attr("action", "<c:url value='/stats/statsTotalList.do' />");
    $("#form").submit();
}

function fn_View(val1){
    $("#MOCKCODE").val(val1);
    $("#form").attr("action", "<c:url value='/stats/statsTotalView.do'/>");
    $("#form").submit();
}


function fn_makeAdj(val1) {
    $("#MOCKCODE").val(val1);
	if(confirm("조정점수를 반영하시겠습니까?")){
		var dataString = $("#frm").serialize();
		$.ajax({
		     type: "POST", 
		     url : '<c:url value="/stats/fn_makeAdj.do"/>',
		     data: dataString,
		     dataType: "text",  
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("조정점수가 반영되었습니다.");
		        return;
		      }
		     },error: function(){
		      alert("실패");
		      return;
		     }
		  });
	}
}
</script>