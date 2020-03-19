package modules.etest.support;

import java.util.Arrays;
import java.util.Map;


import maf.MafUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 사용자 답변정보 Bean 
 * @author bluevlad
 *
 */
public class QueViewInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private Log logger = LogFactory.getLog(this.getClass());
	
	private Map record = null;
	private String[] userMultiAnswer = null;
	private String userAnswer = null;
	private int quecount =0;
	private String qtype = null;
	
	/**
	 * 보기관련 도우미 만들기 
	 * @param item
	 */
	public QueViewInfo(Map item) {
		if(item != null) {
			String t=MafUtil.getString(item.get("usransw"));
			this.record = item;
			this.qtype = MafUtil.getString(item.get("quetype"));
			if(Etest.QUE_TYPE_MULTI.equals(this.qtype)) {
				this.userMultiAnswer = QueueUtil.getAnsWerObject(t);
			} else {
				userAnswer = t;
			}
			
			this.quecount = MafUtil.parseInt(MafUtil.getString(item.get("quecount")));
		}
	}

	public String getView(int qseq) {
		String rv = null;
		rv = MafUtil.getString(record.get("queviw" + qseq));
		return rv;
	}
	/**
	 * 답안의 check 여부를 돌려 줌 
	 * @param qseq
	 * @return
	 */
	public boolean isViewCheck(int qseq) {
		boolean chk = false;
		String sqseq = qseq +""; 
		if(this.userAnswer != null) {
			
			if(Etest.QUE_TYPE_MULTI.equals(this.qtype)) {
				for(int i = 0; i < this.userMultiAnswer.length; i++) {
					if(this.userMultiAnswer[i] != null) {
						if(this.userMultiAnswer[i].equals(sqseq)) {
							chk = true;
							break;
						}
					}
				}
			} else if (Etest.QUE_TYPE_SINGLE.equals(this.qtype)){
				if(getUserAnswer().equals(sqseq)) {
					chk = true;					
				}
			}
		}
		return chk;
	}
	
	public String getQtype() {
    	return qtype;
    }

	/**
	 * 사용자 답변 ( multi 형이 아닐결우 )
	 * @return
	 */
	public String getUserAnswer() {
		if (this.userAnswer == null) {
			return "";
		} else {
			return userAnswer;
		}
		
	}
	
	/**
	 * 사용자 답변 ( multi 형일경우  )
	 * @return
	 */
	public String[] getUserMultiAnswer() {
		if (this.userMultiAnswer == null) {
			return null;
		} else {
			return userMultiAnswer;
		}
		
	}

	/**
	 * 보기 갯수 
	 * @return
	 */
	public int getQuecount() {
    	return quecount;
    }

	
}
