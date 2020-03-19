/*
 * 작성된 날짜: 2005-01-19
 *

 */
package maf.beans;


import java.util.Collections;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.util.Assert;
import maf.web.util.SerializeHashtable;


/**
 * List페이지에 관련 된 정보를 가지고 다닌다. 
 * @author bluevlad
 *
 */
public class NavigatorInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPage=1;
	private long totalCount = 0;
	private long pageCount = 0;
	private int pageSize = 15;
	private int screenSize = 10;
	private int startPage = 0;
	private long endPage = 0;
	private List list = null;
	private NavigatorOrderInfo order = null;
	private final int DEFAULT_PAGE_SIZE = 15;
	private SerializeHashtable listOp = null;
	public static final String MIV_ORDER = "miv_order";
	public static final String MIV_PAGESIZE = "miv_pagesize";
	public static final String MIV_PAGE = "miv_page";
	public static final String TOT_COUNT_FIELD_NAME = "";

	public NavigatorInfo () {
		
	}
	public NavigatorInfo(HttpServletRequest _req, SerializeHashtable listOp ) {
		this.init(_req, listOp, null);
	}
	/**
	 * exam) new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:group_name"));
	 * @param _req
	 * @param listOp
	 * @param defaultOrder
	 */
	
	public NavigatorInfo(HttpServletRequest _req, SerializeHashtable listOp, NavigatorOrderInfo defaultOrder ) {
		this.init(_req, listOp, defaultOrder);
	}
	
	private void init(HttpServletRequest _req, SerializeHashtable listOp, NavigatorOrderInfo defaultOrder) {
		Assert.notNull(listOp);
		String ordParam = listOp.get(MIV_ORDER);
		ordParam = MafUtil.nvl(_req.getParameter(MIV_ORDER), ordParam);
		this.order = new NavigatorOrderInfo(ordParam);
		if(!this.order.isOrdSpecified() && defaultOrder != null) {
			this.order = defaultOrder;
		}
		this.pageSize = maf.MafUtil.parseInt(listOp.get(MIV_PAGESIZE), DEFAULT_PAGE_SIZE);
		this.pageSize = maf.MafUtil.parseInt(_req.getParameter(MIV_PAGESIZE),pageSize);
    
		if (this.pageSize > 100) this.pageSize = 100;
		if (this.pageSize < 1) this.pageSize = 1;
		
		try{
			this.currentPage = maf.MafUtil.parseInt(listOp.get(MIV_PAGE), 1);
			this.currentPage  = MafUtil.parseInt(_req.getParameter(MIV_PAGE),this.currentPage);
		}catch(NumberFormatException e){
			this.currentPage = 1;
		}
		if(this.currentPage < 1) this.currentPage = 1;
		this.listOp = listOp;
		this.listOp.put(MIV_ORDER, this.order.getParam());
		this.listOp.put(MIV_PAGESIZE, this.pageSize +"");
		this.listOp.put(MIV_PAGE, this.currentPage +"");
	}

    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int count) {
        this.totalCount = count;
    }
    public void setTotalCount(long count) {
        this.totalCount = count;
    }    
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public long getEndPage() {
		return endPage;
    }

    public long getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getScreenSize() {
        return screenSize;
    }
    
    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
    
    public int getStartPage() {
        return (currentPage - 1) / screenSize * screenSize + 1;
    }

    /**
     * screensize와 tocalcount 등의 값으로 page등을 계산 한다.
     *
     */
    public void sync(){
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
		startPage = (currentPage - 1) / screenSize * screenSize + 1;
		endPage = startPage + screenSize - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
    }
    
//    public int getVpage() {
//        return this.currentPage;
//    }
    
    public void setList(List list) {
    	this.list = list;
    }
    
    
    
    public List getList () {
    	if( this.list == null) {
    		return Collections.EMPTY_LIST;
    	} else {
    		return this.list;
    	}
    }
    
    public String toString() {
    	return "";
    }

	/**
	 * @return Returns the order.
	 */
	public NavigatorOrderInfo getOrder() {
		return order;
	}
	
	public String getOrderSql() {
		if(this.order == null) {
			return "";
		} else {
			return this.order.getOrderSql();
		}
	}



    
}
