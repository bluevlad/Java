package com.willbes.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class adminTextPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

    private ServletContext servletContext;

    public adminTextPaginationRenderer() { }

    public void initVariables(){
        firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" title=\"처음으로 가기\" class=\"etc\">&lt;&lt;</a>";
        previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" title=\"전 페이지 가기\" class=\"etc\">&lt;</a>";
        currentPageLabel  = "<a href=\"#\" class=\"on\">{0}</a>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a>";
        nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" title=\"다음 보기\" class=\"etc\">&gt;</a>";
        lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" title=\"맨 뒤로\" class=\"etc\">&gt;&gt;</a>";
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        initVariables();
    }
}
