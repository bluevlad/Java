package maf.web.tags.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import maf.beans.NavigatorInfo;
import maf.web.tags.TagWriter;
import maf.web.tags.support.MafHtmlTagSupport;

/**
 * Sort v^보여 주는것 header에 포함 시켜 현재 사용 안함 
 * @author bluevlad
 *
 */
public class MafSortTag extends MafHtmlTagSupport {
	final String IMG_SORT_A ="/maf_images/navigator/sort-a.gif";
	final String IMG_SORT_D ="/maf_images/navigator/sort-d.gif";
	final String IMG_SORT_A_C ="/maf_images/navigator/sort-a_c.gif";
	final String IMG_SORT_D_C ="/maf_images/navigator/sort-d_c.gif";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = null;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		String imgSortA=IMG_SORT_A;
		String imgSortD=IMG_SORT_D;
		
		HttpServletRequest req = (HttpServletRequest) super.pageContext.getRequest();
		NavigatorInfo navigator = (NavigatorInfo) req.getAttribute("navigator");
		if(navigator != null && this.id != null) {
			if(this.id.equals(navigator.getOrder().getKey() )) {
				if("A".equals(navigator.getOrder().getOrder() )) {
					imgSortA = IMG_SORT_A_C;
				} else {
					imgSortD = IMG_SORT_D_C;
				}
			}
			
			String tid = super.evaluateString("id", this.getId());
			//
			tagWriter.startTag("A");
			
			tagWriter.writeOptionalAttributeValue("href", "javascript:mnavi_Sort('A','"+tid+"')");
			tagWriter.startTag("img");
			tagWriter.writeAttribute("src",req.getContextPath()+ imgSortA);
			tagWriter.endTag();
			tagWriter.endTag();
			// draw desc
			tagWriter.startTag("A");
			tagWriter.writeOptionalAttributeValue("href", "javascript:mnavi_Sort('D','"+tid+"')");
			tagWriter.startTag("img");
			tagWriter.writeAttribute("src", req.getContextPath()+ imgSortD);
			tagWriter.endTag();
			tagWriter.endTag();
		}
		
		return EVAL_PAGE;
	}
}
