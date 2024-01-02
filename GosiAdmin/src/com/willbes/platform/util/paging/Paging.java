package com.willbes.platform.util.paging;

import com.willbes.platform.util.Constant;

public class Paging {
	private int currentPage; // 현재페이지

	private int totalCount; // 전체 게시물 수

	private int totalPage; // 전체 페이지 수

	private int startCount; // 한 페이지에서 보여줄 게시글의 시작 번호

	private int endCount; // 한 페이지에서 보여줄 게시글의 끝 번호

	private int startPage; // 시작 페이지

	private int endPage; // 마지막 페이지
	
	private StringBuffer pagingHtml;

	/**
	 * 페이징
	 * 
	 * @param currentPage 현재 페이지
	 * @param totalCount   전체 카운트
	 * @param pageRow	  한 화면에 보여줄 Row 수
	 */
	public Paging(int currentPage, int totalCount,int pageRow) {
		
		this.currentPage = currentPage;
		this.totalCount = totalCount;

		// 전체 페이지 수
		totalPage = (int) Math
				.ceil((double) totalCount / pageRow);
		if (totalPage == 0) {
			totalPage = 1;
		}

		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}

		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startCount = (currentPage - 1) * pageRow;
		endCount = startCount + pageRow - 1;

		// 시작 페이지와 마지막 페이지 값 구하기.
		startPage = (int) ((currentPage - 1) / Constant.BLOCK_PAGE)
				* Constant.BLOCK_PAGE + 1;
		endPage = startPage + Constant.BLOCK_PAGE - 1;

		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 이전 block 페이지
		pagingHtml = new StringBuffer();
		pagingHtml.append("<div class='paginate_complex' style='float:left; width:100%;'>");
//		if (currentPage > Constant.BLOCK_PAGE) {
			pagingHtml.append("<a class='direction prev' href='javascript:goList(1)'>");
			pagingHtml.append("<img src='"+Constant.PREV_IMG1+"' alt='처음'/>");
			pagingHtml.append("</a>");

			pagingHtml.append("<a class='direction prev' href=javascript:goList(" + (startPage - 1 == 0 ? 1 : (startPage -1)) + ")>");
			pagingHtml.append("<img src='"+Constant.PREV_IMG2+"' alt='이전' />");
			pagingHtml.append("</a>");
//		}

		// 페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("<strong>");
				pagingHtml.append(i);
				pagingHtml.append("</strong>");
			} else {
				pagingHtml
						.append("<a href=javascript:goList(" + i + ")>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}

		}

		// 다음 block 페이지
//		if (totalPage - startPage >= Constant.BLOCK_PAGE) {

			pagingHtml.append("<a class='direction next' href=javascript:goList(" + (endPage + 1 > totalPage ? totalPage : (endPage + 1)) + ")>");
			pagingHtml.append("<img src='"+Constant.NEXT_IMG1+"' alt='다음' />");
			pagingHtml.append("</a>");

			pagingHtml.append("<a class='direction next' href=javascript:goList(" + totalPage + ")>");
			pagingHtml.append("<img src='"+Constant.NEXT_IMG2+"' alt='마지막' />");
			pagingHtml.append("</a>");

//		}
		pagingHtml.append("</div>");
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}