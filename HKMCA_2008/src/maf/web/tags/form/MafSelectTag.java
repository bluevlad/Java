package maf.web.tags.form;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.jsp.JspException;
import maf.MafUtil;
import maf.configuration.CodeDetailBean;
import maf.context.support.LocaleUtil;
import maf.util.MethodInvoker;
import maf.util.StringUtils;
import maf.web.context.MafCodeUtil;
import maf.web.tags.TagWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Form 요소중 select를 그려주는 tag library
 * - List 이용
 * - codegroup 이용
 * - forTokes처럼 delims 이용 
 * - 단순하게 select / option 이용 

 * - curValue 처리 가능 
 * @author bluevlad
 *
 */
public class MafSelectTag extends MafAbstractHtmlElementTag {
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	private TagWriter tagWriter;
	private String codeGroup = null;
	private Object items = null;
	private Object curValue = null;
	private String keyValue = null;
	private String keyText = null;
	private String name = null;
	private String size = null;
	private boolean multiple = false;
	private boolean required = false;
	

	public void setRequired(String t) {
		this.required = StringUtils.toBoolean(t);
	}

	protected boolean getRequired() {
		return this.required;
	}
	
	
	public void setHtmlmultiple(String multiple) {
		
		this.multiple = StringUtils.toBoolean(multiple);
	}
	
	public boolean getMultiple() {
		return this.multiple;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCodeGroup(String name) {
		this.codeGroup = name;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public void setCurValue(String value) throws JspException {
		this.curValue = super.evaluateString("curValue", value);
	}
	
	public void setSize(String size) throws JspException {
		this.size = super.evaluateString("size", size);
	}
	public String getSize() {
		return this.size;
	}

	

	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		return preProcSelect(tagWriter);
	}

	public int doEndTag() throws JspException {
		postProcSelect(this.tagWriter);
		return EVAL_PAGE;
	}

	public void doFinally() {
		super.doFinally();
		this.pageContext = null;
	}

	/**
	 * body tag를 수행 하기전에 select tag를 그린다.
	 * @param tagWriter
	 * @return
	 * @throws JspException
	 */
	private int preProcSelect(TagWriter tagWriter) throws JspException {
		tagWriter.startTag("select");
		super.writeDefaultAttributes(tagWriter);
		if (this.getRequired()) {
			writeOptionalAttribute(tagWriter, "required", "true");
		}
		if (this.getMultiple()) {
			writeOptionalAttribute(tagWriter, "multiple", "true");
		}
		writeOptionalAttribute(tagWriter, "size", this.size);
		tagWriter.forceBlock();
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * body tag 그린 후 내부 option tag를 그린다.
	 * @param tagWriter
	 * @throws JspException
	 */
	private void postProcSelect(TagWriter tagWriter) throws JspException {
		if (this.items != null) {
			this.items = super.evaluate("items", this.items);
			if (this.items instanceof List) {
				_procItemsList(tagWriter, (List) this.items);
			} else if (this.items instanceof Map) {
				_procItemsMap(tagWriter, (Map) this.items);
			}
		}
		if (!MafUtil.empty(this.codeGroup)) {
			this.codeGroup = MafUtil.getString(super.evaluate("codeGroup", this.codeGroup));
			_procCodeGroup(tagWriter);
		}
		tagWriter.endTag();
	}

	private void _procItemsList(TagWriter tagWriter, List list) throws JspException {
		if (this.keyValue == null || this.keyText == null) {
				throw new JspException("List items must have keyValue and key Text !!!");
		}
		Iterator itr = list.iterator();
		String tagValue = null;
		String tagText = null;
		MethodInvoker mi = new MethodInvoker();
		while(itr.hasNext()) {
			Object hm = itr.next();
			if( hm instanceof Map) {
				
				tagValue = MafUtil.getString(((Map)hm).get(this.keyValue));
				tagText = MafUtil.getString(((Map)hm).get(this.keyText));
			} else {
				try {
					mi.setTargetObject(hm);
					mi.setTargetMethod("get"+StringUtils.capitalize(this.keyValue));
					tagValue = MafUtil.getString(mi.invoke());
					mi.setTargetMethod("get"+StringUtils.capitalize(this.keyText));
					tagText = MafUtil.getString(mi.invoke());
				}  catch (Exception e) {
					throw new JspException(e);
				}
			}
			
			tagWriter.startTag("option");
			tagWriter.writeAttribute("value", tagValue);
			if (curValue != null) {
				if (curValue.equals(tagValue)) {
					tagWriter.writeAttribute("selected", "true");
				}
			}
			tagWriter.appendValue(tagText);
			tagWriter.endTag();
		}
	}

	private void _procItemsMap(TagWriter tagWriter, Map hm)  throws JspException{
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
//			System.out.print(me.getKey() + ": ");
//			System.out.println(me.getValue());
			tagWriter.startTag("option");
			tagWriter.writeAttribute("value", MafUtil.getString(me.getKey()));
			if (curValue != null) {
				if (curValue.equals(MafUtil.getString(me.getKey()))) {
					tagWriter.writeAttribute("selected", "true");
				}
			}
			tagWriter.appendValue(MafUtil.getString(me.getValue()));
			tagWriter.endTag();
		}
	}

	private void _procCodeGroup(TagWriter tagWriter) throws JspException {
		List list = MafCodeUtil.getCodeList(codeGroup);
		if (!MafUtil.empty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CodeDetailBean bean = (CodeDetailBean) list.get(i);
				tagWriter.startTag("option");
				tagWriter.writeAttribute("value", bean.getCodeNo());
				if (curValue != null) {
					if (curValue.equals(bean.getCodeNo())) {
						tagWriter.writeAttribute("selected", "true");
					}
				}
				tagWriter.appendValue(bean.getCodeName(LocaleUtil.locale2String(super.getSafeLocale())));
				tagWriter.endTag();
			}
		}
	}
}