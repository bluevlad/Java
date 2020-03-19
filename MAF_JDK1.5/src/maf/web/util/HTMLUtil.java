package maf.web.util;

import java.net.URLEncoder;

import maf.MafUtil;
import maf.logger.Trace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

/**
 * @author goindole
 * 
 */
public class HTMLUtil {
	static Log logger = LogFactory.getLog(HTMLUtil.class);

	//    static final RE reg_NL = new RE("(/(\015\012)|(\015)|(\012)/)");
	static final RE reg_NL = new RE("(\015\012)|(\015)|(\012)");

	static RE reg_HTML_1 = new RE("&");

	static RE reg_HTML_2 = new RE("\"");

	static RE reg_HTML_3 = new RE("'");

	static RE reg_HTML_4 = new RE("<");

	static RE reg_HTML_5 = new RE(">");

	HTMLUtil() {
	}

	/**
	 * 
	 * nl2br -- ���ڿ��� ��� �ٹٲ� �տ� HTML �ٹٲ� �±�" <br />
	 * "�� �����մϴ�.
	 * 
	 * Windows style newlines are like this: <br>
	 * <CR><LF>-> \r\n -> \015\012 <br>
	 * 
	 * Mac style like this: <br>
	 * <CR>-> \r -> \015 <br>
	 * 
	 * Unix style like this: <br>
	 * <LF>-> \n -> \012 <br>
	 * 
	 * \012 -> 10 -> \n<br>
	 * \015 -> 13 -> \r<br>
	 * 
	 * @param txt
	 * @return
	 */
	public static String nl2br(String txt) {
		return nl2br(txt, null);
	}

	public static String nl2br(String txt, String def) {
		try {
			if (txt == null) {
				return "&nbsp;";
			} else {
				if (def == null) {
					return reg_NL.subst(MafUtil.nvl(txt,"&nbsp;"), "<br />");
				} else {
					return reg_NL.subst(MafUtil.nvl(txt,"&nbsp;"), MafUtil.nvl(def, ""));
				}
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			return "&nbsp;";
		}
	}

	/**
	 * ���� ���� ���ֱ� 
	 * @param txt
	 * @return
	 */
	public static String removenl(String txt) {
		try {
			if (txt == null) {
				return null;
			} else {
				return reg_NL.subst(MafUtil.nvl(txt,""), "");
				
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			return "&nbsp;";
		}
	}
	public static String JSaddSlashes(String txt) {
		if (txt != null) {
			txt = reg_HTML_2.subst(txt, "\\\"");
			txt = reg_HTML_3.subst(txt, "\\'");
			return txt;
		} else {
			return "";
		}
		
	}
	/**
	 * HTML�� Ư������(&, " ', < > ) �� encording
	 * 
	 * @param txt
	 * @param def
	 * @return
	 */
	public static String htmlspecialchars(String txt, String def) {
		if (txt != null) {
			txt = reg_HTML_1.subst(txt, "&amp;");
			txt = reg_HTML_2.subst(txt, "&quot;");
			txt = reg_HTML_3.subst(txt, "&#039;");
			txt = reg_HTML_4.subst(txt, "&lt;");
			txt = reg_HTML_5.subst(txt, "&gt;");
			return txt;
		} else {
			return def;
		}
	}
	
	/**
	 * HTML�� Ư������(&, " ', < > ) �� encording
	 * 
	 * @param txt
	 * @param def
	 * @return
	 */
	public static String htmlspecialchars(String txt) {
		return htmlspecialchars(txt, "");
	}

	/**
	 * null�̰ų� ��Ʈ���� ��� &nbsp;
	 * 
	 * @param txt
	 * @return
	 */
	public static String null2nbsp(String txt) {
		if (txt == null || "".equals(txt.trim())) {
			return "&nbsp;";
		} else {
			return txt;
		}
	}

	public static Object null2nbsp(Object obj) {
		return MafUtil.nvl(obj, "&nbsp;");
	}

	/**
	 * �ѱ� ���ϸ� Link�� �ɱ� ���� Url�� Encording ���� +�� %20���� ó�� <Br>
	 * URLEncoder �� -_.�� ������ ��� �����ڰ� �ƴ� ���ڸ� �ۼ�Ʈ(%) <Br>
	 * ���ο� �̾����� �� 16������ ��ü�ϰ� <Br>
	 * ������ �÷���(+) �������� ��ü�� ���ڿ��� ��ȯ�մϴ�. <Br>
	 * �̴� WWW ������ ���ڵ��� ����Ʈ ������, application/x-www-form-urlencoded <Br>
	 * ��ü���� ���� ����� ���ڵ��Դϴ�. <Br>
	 * �������� ������ ������ �÷���(+) �������� ���ڵ� �ϴ� ���� <Br>
	 * RFC1738 ���ڵ�(rawurlencode() ����)�� �ٸ��ϴ�. <Br>
	 * �� �Լ��� URL�� ���� �κп� ����ϴ� ���ڿ��� ���ڵ��� �� ���մϴ�. <Br>
	 * ������ ���� ���� �������� ������ �����մϴ�: <Br>
	 * 
	 * @param Url
	 * @return
	 */
	public static String rawURLEncode(String Url, String enc) {
		if (Url == null)
			return null;
		String Encodered;
		try {
			Encodered = URLEncoder.encode(Url, enc);
		} catch (Exception e) {
			logger.error("enc : [" + enc + "], " + e.getMessage());
			return "";
		}
		
		return Encodered.replaceAll("[/+]", "%20");
	}
	public static String rawURLEncode(String Url) {
		return rawURLEncode(Url,"UTF-8");
	}



	/**
	 * <A HREF Tag�� ���� > <A HREF="href" TITIE="title" TARGET="target"
	 * STYLE="style"> Description </a>
	 * 
	 * @param Href
	 * @param Description
	 * @param Title
	 * @param style
	 * @param target
	 */
	public static String getHtmlAtag(String Href, String Description,
			String Title, String Target, String Class, String Style) {
		StringBuffer sHtml = new StringBuffer();
		sHtml.append("<A HREF=\"");
		sHtml.append(Href);
		sHtml.append("\" ");

		if (Title != null && !"".equals(Title.trim())) {
			sHtml.append(" TITLE=\"");
			sHtml.append(htmlspecialchars(Title));
			sHtml.append("\" ");
		}
		if (Style != null && !"".equals(Style.trim())) {
			sHtml.append(" STYLE=\"");
			sHtml.append(Style);
			sHtml.append("\" ");
		}
		if (Target != null && !"".equals(Target.trim())) {
			sHtml.append(" target=\"");
			sHtml.append(Target);
			sHtml.append("\" ");
		}
		if (Class != null && !"".equals(Class.trim())) {
			sHtml.append(" CLASS=\"");
			sHtml.append(Class);
			sHtml.append("\" ");
		}
		sHtml.append(">");
		sHtml.append(Description);
		sHtml.append("</a>");
		return sHtml.toString();
	}

	public static String getHtmlAtag(String Href, String Description,
			String target) {
		return getHtmlAtag(Href, Description, null, target, null, null);
	}

	public static String getHtmlAtag(String Href, String Description) {
		return getHtmlAtag(Href, Description, null, null, null, null);
	}

	/**
	 * Alert �Ѹ���, History Back
	 * 
	 * @param msg
	 * @return
	 */
	public static String JScriptAlertBack(String msg) {
		StringBuffer sHtml = new StringBuffer();
		if (msg == null)
			msg = "";
		sHtml
				.append("<html><head><META HTTP-EQUIV='Content-type' CONTENT='text/html;charset=euc-kr'><script language='javascript'>alert('");
		sHtml.append(htmlspecialchars(msg));
		sHtml.append("');history.back();</script></head></html>");
		return sHtml.toString();
	}

	/**
	 * Msg�� alert���� �Ѹ��� forward_url��
	 * 
	 * @param msg
	 * @param forward_url
	 * @return
	 */
	public static String JScriptMsgRedirect(String msg, String forward_url) {
		StringBuffer sHtml = new StringBuffer();
		if (msg == null)
			msg = "";
		sHtml
				.append("<html><head><META HTTP-EQUIV='Content-type' CONTENT='text/html;charset=euc-kr'><script language='javascript'>alert('");
		sHtml.append(htmlspecialchars(msg));
		sHtml.append("');location.href='");
		sHtml.append(forward_url);
		sHtml.append("';</script></head></html>");
		return sHtml.toString();
	}

	/**
	 * strip_tags -- Strip HTML and PHP tags from a string
	 * 
	 * @param sHtml
	 * @return
	 */
	public static String strip_tags(String sHtml) {
		//		org.apache.regexp.RE reg_HTML = new
		// org.apache.regexp.RE("<*([^<>]*)script[^>]*>.+</script[^>]*>|<(.*?)>");
		RE reg_HTML = new RE("<(.*?)>");
		reg_HTML.setMatchFlags(RE.MATCH_CASEINDEPENDENT + RE.MATCH_MULTILINE);
		try {
			return reg_HTML.subst(sHtml, "");
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
			return "";
		}

	}

	/**
	 * strip_alltags -- HTML, NewLine, Javascript, JSP tag�� ��� ����ϴ�.
	 * 
	 * @param sHtml
	 * @return
	 */
	public static String strip_alltags(String sHtml) {
		RE reg_NL = new RE("(\015\012)|(\015)|(\012)");
		RE reg_SCRIPT = new RE("<*([^<>]*)script[^>]*>.+</script[^>]*>");
		RE reg_HTML = new RE("<(.*?)>");
		RE reg_JSP = new RE("<" + "%(.*?)%" + ">");
		reg_SCRIPT.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		//reg_SCRIPT.setMatchFlags(RE.MATCH_MULTILINE);
		String sT = null;

		//return
		// reg_HTML.subst(reg_JSP.subst(reg_SCRIPT.subst(reg_NL.subst(sHtml,"
		// "),""),""),"");

		try {
			sT = reg_NL.subst(sHtml, " ");
			//sT = reg_SCRIPT.subst(sT, "");
			sT = reg_JSP.subst(sT, "");
			sT = reg_HTML.subst(sT, "");

			//return
			// reg_HTML.subst(reg_JSP.subst(reg_SCRIPT.subst(reg_NL.subst(sHtml,"
			// "),""),""),"");
			return sT;
		} catch (Exception e) {
			logger.error("Reg Error !!!");
			//logger.error(e.getMessage());
			//logger.error(Trace.getStackTrace(e));
			return "";
		}

	}

	public static String toTab(String contents) {
		int len = contents.length();
		int linenum = 0, i = 0;

		for (i = 0; i < len; i++)
			if (contents.charAt(i) == '\t')
				linenum++;

		StringBuffer result = new StringBuffer(len + linenum * 3);

		for (i = 0; i < len; i++) {
			if (contents.charAt(i) == '\t')
				result.append("&nbsp;&nbsp;&nbsp;");
			else
				result.append(contents.charAt(i));
		}

		return result.toString();
	}

	
}
