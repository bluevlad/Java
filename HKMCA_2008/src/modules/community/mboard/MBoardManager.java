/*
 * Created on 2004. 8. 23.
 *
 */
package modules.community.mboard;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.aam.MafUserManager;
import maf.base.BaseHttpSession;
import maf.logger.Logging;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.beans.MbbsCommentBean;
import modules.community.mboard.beans.MbbsDataBean;
import modules.community.mboard.beans.MbbsDataRefListBean;
import modules.community.mboard.beans.MbbsMetaBean;
import modules.community.mboard.dao.MbbsAttachDB;
import modules.community.mboard.dao.MbbsCommentDB;
import modules.community.mboard.dao.MbbsDataDB;
import modules.community.mboard.dao.MbbsDataRefListDB;
import modules.community.mboard.dao.MbbsMetaDB;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * 
 */
public class MBoardManager {
    public static final String ATT_FILE_PATH = "/mbbsfile";
    public static final String THUMB_PATH = "thumb";		// �����̹��� ���� ���
    private Log logger = LogFactory.getLog(getClass());
    
    private String att_file_path = null;
    private String thumb_path = null;

    private MdbDriver oDB = null;
    private String bid = null;
    private boolean isOk = false;
    private MbbsMetaBean oBoard=null;
    
    private int listDataCount = 0;
    private int v_page = 0;
    private BaseHttpSession sBean = null;
    
    public MbbsMetaBean getBoard() {
        return this.oBoard;
    }
    public int getListDataCount() {
        return listDataCount;
    }
    public void setListDataCount(int listDataCount) {
        this.listDataCount = listDataCount;
    }
    public int getV_page() {
        return v_page;
    }
    public void setV_page(int v_page) {
    	if(v_page < 1) v_page = 1;
        this.v_page = v_page;
    }
    /**
     * �Խ����� ���°� ������ 
     * @return
     */
    public boolean isOk() {
        return isOk;
    }
    
    public String getBid() {
        return this.bid;
    }
    
    public void setBid(String bid) {
        this.bid = bid;
    }
    
    public String getBidPath() {
    	return MBoardManager.getBid2BidPath(this.bid);
    }

    public   MBoardManager( String bid, String file_path, BaseHttpSession pBean) 
    throws SQLException, Exception {
        this.att_file_path = file_path;
        if (bid == null) {
            throw new MBoardException("�߸��� ��û�Դϴ�.");            
        } else {
            this.oDB =Mdb.getMdbDriver();
	        oBoard = GetBoardInfo(oDB, bid);
            sBean = pBean;
        }
    }
    
    /**
     * constructor
     * @param conn : Database Connection
     * @param bid   : Board ID�� 
     * @throws SQLException
     * @throws Exception
     */
    public MBoardManager(  String bid, String file_path) throws SQLException, Exception {
        this( bid,  file_path, null);
    }    

    
    private MbbsMetaBean GetBoardInfo(MdbDriver oDb, String bid) throws Exception  {

        MbbsMetaBean oM = MbbsMetaDB.getInstance().view( oDb, bid);
        
        if(oM == null) {
        	oM = new MbbsMetaBean();
        }
        if ( oM.isOk()) {
            this.isOk = true;
            this.bid = oM.getBid();
        } else {
            this.isOk = false;
        }
        return oM;
    }    
    
   
    /**
     * �Խù� ��� ���� ����
     * @param v_pagedown	
     * @param v_page	: ������ ������
     * @param v_max_index	: �� Screen���� max_index
     * @param v_min_index	: �� Screen���� min_index
     * @param v_key			: �˻� Key
     * @param v_srch		: �˻��� �ܾ�
     * @return
     * @throws SQLException
     */
	public synchronized List getList(int v_page, String v_key, String v_srch) throws Exception
	{
		return getList(v_page, v_key, v_srch, "T");
	}
	
	public List getList(int v_page, String v_key, String v_srch, String v_status) throws Exception
	{
		return MbbsDataDB.get_instance().getList(this,  v_page, v_key, v_srch, v_status);
	}	
	
	/**
	 * ���� ��� ��������
	 * @return
	 * @throws Exception
	 */
	public List getNoticeList( ) throws Exception
	{
		return MbbsDataDB.get_instance().getNoticeList(this);
	}	
	/**
	 * �ϳ��� �Խù��� �о� �´�. (Board�� this���� �Ѱ���)
	 * @param c_index	: �Խù� ID
	 * @return
	 */
	public MbbsDataBean getOne(String c_index) throws Exception
	{
		//Data cb = new Data(conn);
		return MbbsDataDB.get_instance().getOne(this, c_index);
	}
	/**
	 * �ϳ��� �Խù��� ����� ���� ���� usn, ��ȣ, ���¸� ���� �´�.(���� Ȯ�ο�)
	 * @param c_index
	 * @return
	 * @throws Exception
	 */
	public MbbsDataBean getOneSimple(String c_index) throws Exception
	{
	    return MbbsDataDB.get_instance().getOneSimple(this, c_index);
	}	
	/**
	 * �Խù��� ���õ� �յ� �Խù��� 
	 * @param c_index	: �Խù� ID
	 * @param ref_cnt	: ������ �յڰԽù� �� �� (ref_cnt * 2��)
	 * @return
	 */
	public MbbsDataRefListBean[] getRefList(String c_index, int ref_cnt) throws Exception{
	    return MbbsDataRefListDB.getInstance().getList(this,  c_index, ref_cnt);
	}
	
	public MbbsDataRefListBean[] getRefList(String c_index) throws Exception {
	    return getRefList(c_index, 1);
	}	
	
	/** 
	 * ÷�� ���ϰ� �Բ� �Խù� ����
	 * @param item
	 * @param _req
	 * @return
	 */
	public String addItem( MyHttpServletRequest _req, String c_ref) throws Exception 
	{
	    chkRight(BoardConfig.CMD_ADD, null);

	    String c_index = null;
	    MbbsAttachBean[] attFiles = null;

	    logger.debug("Max : " + oBoard.getMax_attach_size()*1024 + "\n act: " + _req.getTotalAttachSize());
        if(oBoard.getMax_attach_size()*1024 < _req.getTotalAttachSize()) {
            _req.deleteAllAttachFile();
            throw new MBoardException("÷������ �뷮�� �ѵ�(" +oBoard.getMax_attach_size()+ "MB)�� �ʰ��Ǿ����ϴ�.");  
        }
        
	    try {
	    	oDB.setAutoCommit(false);
	        String c_content = _req.getParameter("c_content");
	        attFiles = Attach_SaveFiles(_req);
	        if (attFiles != null ) {
	            c_content = MBoardLib.ArrangeContents(attFiles, this.getBid(), c_content, "http://" + _req.getHeader("HOST") + _req.getContextPath());
	        }
    		MbbsDataBean oB	= new MbbsDataBean();

    		oB.setBid(bid); 
    		oB.setC_subject(_req.getParameter("c_subject"));
    		oB.setC_content(c_content);
    		oB.setC_ip(_req.getRemoteAddr());
    		oB.setC_category(_req.getParameter("category"));
    		oB.setC_type(_req.getParameter("C_type","H"));
    		oB.setIs_notice(_req.getParameter("is_notice","F"));
    		
    		if(c_ref != null) {	// ����� ���
    		    oB.setC_ref(c_ref);
    		}
   		
//    	    if (!"".equals(this.sBean) && this.sBean != null) {
    		    oB.setUsn(this.sBean.getUsn());
    		    oB.setWname(this.sBean.getNm());
    		    oB.setC_passwd(null);
//    	    } else {
//    		    oB.setWname(_req.getP("wname"));
//    		    oB.setC_passwd(_req.getP("c_passwd"));
//    	    }
	        
            c_index = MbbsDataDB.get_instance().addItem(this, oB);
            MbbsAttachDB.AddItems( this, c_index, attFiles);
            oDB.commit();
	    } catch (Exception e) {
	       logger.error(e.getMessage());
	       Logging.trace(e);
	        try{oDB.rollback();}catch(Exception sqle){};
	        _req.deleteAllAttachFile();
	        MBoardLib.DeleteAttchFiles(this, attFiles, this.att_file_path);
	        throw new MBoardException("�Խù� ������ ������ �߻��Ͽ����ϴ�.");  
	    }
		return c_index;
	}	
	
	public String addItem( MyHttpServletRequest _req) throws Exception 
	{
	    return addItem(_req, null);
	}
	/** 
	 * �Խù� Update
	 * @param item
	 * @return
	 */
	public void UpdateItem( MyHttpServletRequest _req, String c_index) throws Exception 
	{
	    chkRight(BoardConfig.CMD_UPDATE, c_index);
	    MbbsAttachBean[]  attFiles = null; // MbbsAttachBean[]

        if(oBoard.getMax_attach_size() * 1024 < _req.getTotalAttachSize()) {
            _req.deleteAllAttachFile();
            
            throw new MBoardException("÷������ �뷮�� �ѵ�(" +oBoard.getMax_attach_size()+ "MB)�� �ʰ��Ǿ����ϴ�."); 
        }
        
	    try {
	    	oDB.setAutoCommit(false);
	        String c_content = _req.getParameter("c_content");
	        // ÷�������� ���� �Ѵ�. 
	        attFiles = Attach_SaveFiles(_req);
	        if (attFiles != null ) {
	            c_content = MBoardLib.ArrangeContents(attFiles, this.bid, c_content, "http://" + _req.getHeader("HOST") + _req.getContextPath());
	        }
    		MbbsDataBean oB	= new MbbsDataBean();
    		oB.setBid(bid); 
    		oB.setC_index(c_index );
    		oB.setC_content(c_content);
    		oB.setC_subject(_req.getParameter("c_subject"));
    		oB.setC_ip(_req.getRemoteAddr());
    		oB.setC_category(_req.getParameter("category"));
    		oB.setC_type(_req.getParameter("C_type","H"));
    		oB.setIs_notice(_req.getParameter("is_notice","F"));
    		if(this.sBean != null) {
    		    oB.setUsn(this.sBean.getUsn());
    		    oB.setWname(this.sBean.getNm());
    		    oB.setC_passwd(null);
    		} else {
        		oB.setWname(_req.getParameter("wname"));
        		oB.setC_passwd(_req.getParameter("passwd"));    		    
    		}
	        MbbsDataDB.get_instance().UpdateItem( this, oB);
	        String[] del_img = _req.getParameterValues("deleteAttachSeq");
	        if(del_img != null ) {
	            List delFiles= MbbsAttachDB.DelItems( this, c_index, del_img);
	            
	    		for(int i=0; i < delFiles.size(); i++ ) {
	    		    MBoardLib.DeleteAttchFile(this, (String) delFiles.get(i), this.att_file_path);
	    		}
	        }
	        MbbsAttachDB.AddItems(this, c_index, attFiles);
	        oDB.commit();
	    } catch (Throwable e) {
	       // logger.error(miraenet.etc.Trace.getStackTrace(e));
	        MBoardLib.DeleteAttchFiles(this, attFiles, this.att_file_path);
	        _req.deleteAllAttachFile();
	        try{oDB.rollback();}catch(Exception sqle){};
	        throw new MBoardException("�Խù� ������ ������ �߻��Ͽ����ϴ�.", e);  
	    }
	}	
	
	/**
	 * �ϳ��� �Խù� ����
	 * ����:÷�α��� ����, �ؿ�Ҵ� �ؿ�ҷ� �̵��� 
	 * @param item
	 * @param mode : ����  = d: ����, c:��������, w:�ؿ�ҷ� �̵�, n:���� 
	 * @return
	 */
	public void DeleteItem(String c_index, String mode) throws Exception
	{
	    chkRight(BoardConfig.CMD_UPDATE, c_index);
	    if("d".equals(mode) || "c".equals(mode)) {

	        MBoardLib.DeleteAttchFiles(this, getAttchList( c_index), this.att_file_path);
	    }	    
	    MbbsDataDB.get_instance().DeleteItem(this, c_index, mode);
	}


	/**
	 * �Խ��� ����
	 * @return
	 */
	public void RemoveBoard() throws Exception {
	    MbbsMetaDB dao = MbbsMetaDB.getInstance();
		dao.delete(oDB, this.bid, this.att_file_path);
    }
    
    /**
     * ÷�������� ��� ���� �ϰ�(�̹����� Thumbnail �ۼ�)
     * ���� ����Ʈ�� ������.
     * ������ ���� ���� ����.
     * @param _req
     * @return
     */
    private MbbsAttachBean[] Attach_SaveFiles( MyHttpServletRequest _req ) throws Exception
    {
        List attFiles = 	new ArrayList(); 
        
	    try {
		    Map filesMap =  _req.getFileParameterMap();
		    Set set = filesMap.entrySet();
		    Iterator i = set.iterator();

		    while(i.hasNext()){
				Map.Entry me = (Map.Entry) i.next();
				List fileList = (List) me.getValue();
				for(int j = 0; j < fileList.size(); j++ ) {
					UploadedFile uFile = (UploadedFile) fileList.get(j);
					if(uFile.getFileSize() > 0 ) {
						MbbsAttachBean oAttBean;
						// ÷������ ���� �� �����̹��� ���� 
						oAttBean = MBoardLib.Attach_SaveFile(uFile, this.getBidPath(), true, 
						        				this.att_file_path,
										        this.oBoard.getThumbwidth(), this.oBoard.getThumbheight());
						// ������ ����� �Ǿ����� 
						if (oAttBean != null) {
						    attFiles.add(oAttBean);
						} else {
							break;
						}
					}
				}
		    } // while
		   
	    } catch (Throwable e) {
	        MBoardLib.DeleteAttchFiles(this, (MbbsAttachBean[]) attFiles.toArray(new MbbsAttachBean[0]), this.att_file_path);
	       logger.error(maf.logger.Trace.getStackTrace(e));
	        throw new MBoardException("Attach_SaveFiles() : error in attach file save", e);
	    }
	    return (MbbsAttachBean[]) attFiles.toArray(new MbbsAttachBean[0]);
    }
    
   
    /**
     * ÷������ ��� ���� ����
     * @param c_index
     * @return
     * @throws Exception
     */
	public MbbsAttachBean[] getAttchList(String c_index) throws Exception {
	    return MbbsAttachDB.getList(this, c_index);
	}	    
	
	/**
	 * Comment �߰� 
	 * @param conn
	 * @param item
	 * @return
	 */
	public void CmtAdd( MbbsCommentBean item) throws Exception {
	    chkRight(BoardConfig.CMD_CMT_ADD, null);
		MbbsCommentDB.addItem(this, item);
	}	
	
	/**
	 * Comment ����
	 * @param conn
	 * @param item
	 * @return
	 */
	public void CmtDel(String c_index, String c_id) throws Exception
	{
	    chkRight(BoardConfig.CMD_CMT_DEL, c_index);
        MbbsCommentDB.delItem(this, c_index, c_id);
	}		
	/**
	 * Comment ����Ʈ �������� 
	 * @param c_index
	 * @return
	 */
	public MbbsCommentBean[] getCmtList( String c_index) throws Exception {
		return MbbsCommentDB.getList(this, c_index);
	}		
	
	/**
	 * ���� üũ�� ������ ���� ��� exception�� �߻��Ѵ�.
	 */


	public void chkRight(String Mode, String c_index) throws Exception
	{
	    
	    if(sBean != null && MafUserManager.isSuperAdmin(sBean)) 
	    {
//	      administrator �� ��� ������ ����
	    } else {


	    }
	}
	
    public MdbDriver getODB() {
        return oDB;
    }
    
    public void destructODB() {
        this.oDB.close();
        this.oDB = null;
        
    }
    
    /**
     * @return att_file_path�� �����մϴ�.
     */
    public String getAtt_file_path() {
        return att_file_path;
    }
    /**
     * @return thumb_path�� �����մϴ�.
     */
    public String getThumb_path() {
        return thumb_path;
    }
    
    // Window �迭���� : �� ���丮�� ������� �����.
    public static String getBid2BidPath(String bid) {
    	if (bid != null) {
    		return bid.replace(':','_');
    	} else {
    		return null;
    	}
    }
}
