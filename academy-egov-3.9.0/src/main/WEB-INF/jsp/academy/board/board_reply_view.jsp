﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<style type="text/css">
	.wb_reply {width:100%; max-width:980px; text-align:left; margin:10px auto; background:#FFF}
	.wb_reply .st {width:100%; max-width:980px; margin:0 auto}			
	.wb_reply .reply {width:100%; padding-bottom:50px}
	.replyList span.name { padding-left:20px}
	.textarBx textarea {width:830px !important}
	.reply_inbx{padding:10px;background:#555556}
	.reply_inbx .total{margin:0 0 10px;font-weight:bold;color:#fff;}
	.reply_inbx .textarBx{overflow:hidden}
	.reply_inbx .textarBx textarea{float:left;width:630px;height:76px;font-size:12px}
	.reply_inbx .textarBx .btnrwt{float:right;width:110px;height:88px;background:#fafafa;}
	.replyList .ry_info{overflow:hidden;padding:10px 0;border-bottom:1px solid #e0e0e0}
	.replyList .ry_info .num{float:left;width:10%;text-align:center}
	.replyList .ry_info .name{float:left;font-weight:bold;color:#444}
	.replyList .ry_info .date{float:right;width:15%;font-size:11px;text-align:center;line-height:1.4}
	.replyList .ry_cont{display: inline-flex;width: 96%;padding:10px 20px;background:#fafafa;border-bottom:1px solid #999;line-height:1.4}
	.replyList .ry_cont{overflow:hidden}
	.replyList .ry_cont .fL{width:90%}
	.del{width:40px;height:20px;font-size:10px;}
</style>
<script type="text/javascript">
	//댓글	
	function fn_focusComment() {
		if("<c:out value='${params.USER_ID}' />" == "") {
			alert("회원만 댓글을 등록할 수 있습니다.");
			return;
		} else {
			if ($("#cmmtForm #CONTENT").val() == '댓글을 남겨주세요.') {
				$("#cmmtForm #CONTENT").val('');
			}
		}
	}
	
	function fn_comment_insert() {
		if("<c:out value='${params.USER_ID}' />" == "") {
			alert("회원만 댓글을 등록할 수 있습니다.");
			return;
		}

		if($("#cmmtForm #CONTENT").val() == "") {
			alert("댓글을 입력하여 주십시오.");
			$("#cmmtForm #CONTENT").focus();
			return;
		}
		
		if(confirm("등록 하시겠습니까?")){
			$.ajax({
					type: "POST",
					url: '<c:url value="/board/boardCommentInsert.do"/>',
					data: $("#cmmtForm").serialize(),
					async : false,
					dataType: "json",
					success: function (result) {
						if(result.result == "1") {
							alert($("#REPLY_USER_NAME").val() + "님 댓글이 등록 되었습니다.");
							window.opener.document.location.href = window.opener.document.URL;
						}
					}
			});
		}
	}
	
	function fn_comment_delete(seq,name) {
		$("#DELETE_SEQ").val(seq);
		$("#REPLY_USER_NAME").val(name);
		
		if(confirm("삭제 하시겠습니까?")){
			$.ajax({
				type: "POST",
				url: '<c:url value="/board/boardCommentDelete.do"/>',
				data: $("#cmmtForm").serialize(),
				async : false,
				dataType: "json",
				success: function (result) {
					if(result.result == "1") {
						alert($("#REPLY_USER_NAME").val() + "님 댓글이 삭제 되었습니다.");
						$("#"+seq+"_SEQ").remove();
					}
				}
			});
		}
	}
	
	function goList(page) {
		if(typeof(page) == "undefined") $("#cmmtCurrentPage").val(1);
		else $("#cmmtCurrentPage").val(page);
		var url = location.href;
		$("#cmmtForm").attr("action", url);
		$("#cmmtForm").submit();
	}
	</script>
	<div class="wb_reply" id="link">
               <div class="reply">
				<p align="center">
                	<form id="cmmtForm" name="cmmtForm" method="post">
                        <h4 class="skip">댓글</h4>
                        <div class="reply_inbx">
                            <!--  <p class="total">총댓글 |  ${listCount}</p> -->
                            <div class="textarBx">
                                <textarea name="CONTENT" id="CONTENT" cols="30" rows="3" onfocus="javascript:fn_focusComment();">댓글을 남겨주세요.</textarea>
                                <button type="submit" class="btnrwt" onclick="javascript:fn_comment_insert();"><span class="ir">글쓰기</span></button>
                            </div>
                        </div>
                                    
                        <ul class="replyList">
                        <c:forEach items="${boardCommentList}" var="data" varStatus="status">
                             <li id="${data.SEQ}_SEQ">
                                <div class="ry_info"><span class="name">${data.USER_NM }</span><span class="date">${data.REG_DT }</span></div>
                                <div class="ry_cont">
                                    <div class="fL">${fn:replace(data.CONTENT, newLineChar, '<br/>')}</div>
                                        <c:if test="${loginInfo.USER_ROLE eq 'ADMIN' }">
                                        <div class="fR"><a href="javascript:fn_comment_delete('${data.SEQ }','${data.USER_NM}');">삭제</a></div>
                                        </c:if>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                                    
                        <div class="rolling">
                            <c:if test="${not empty boardCommentList}">
                                <c:out value="${pagingHtml}" escapeXml="false" />
                            </c:if>
                        </div>
                                    
                        <input type="hidden" id="USER_ID" name="USER_ID" value="${params.USER_ID }"/>
                        <input type="hidden" id="cmmtCurrentPage" name="cmmtCurrentPage" value="${params.cmmtCurrentPage}" />
    					<input type="hidden" id="cmmtPageRow" name="cmmtPageRow" value="${params.cmmtPageRow}" />
                        <input type="hidden" id="REPLY_USER_NAME" name="REPLY_USER_NAME" value="${loginInfo.USER_NM }"/>
                        <input type="hidden" id="CMMT_BOARD_SEQ" name="CMMT_BOARD_SEQ" value="${params.CMMT_BOARD_SEQ }"/>
                        <input type="hidden" id="CMMT_BOARD_MNG_SEQ" name="CMMT_BOARD_MNG_SEQ" value="${params.CMMT_BOARD_MNG_SEQ }"/>
                        <input type="hidden" id="DELETE_SEQ" name="DELETE_SEQ" value=""/>
                        
                        <input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
						<input type="hidden" id=BOARDVIEW_MNG_SEQ name="BOARDVIEW_MNG_SEQ" value="${params.BOARDVIEW_MNG_SEQ}">
						<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ }"/>
						<input type="hidden" id=BOARDVIEW_SEQ name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
						<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
						<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">
						
						<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
						<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
						<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
                    </form>    
                   </p>             
	</div>
</div>