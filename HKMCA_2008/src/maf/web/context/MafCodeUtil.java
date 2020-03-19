package maf.web.context;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.context.support.LocaleUtil;


public class MafCodeUtil {
	
//	public static String getCodeName(String groupCd, String codeNo ) {
//		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
//		CodeDetailBean bean = codeInfo.getCodeInfo(groupCd, codeNo);
//		return bean.getCodeName();
//	}
	/**
	 * locale 별로 code값을 가져온다.
	 * @param groupCd
	 * @param codeNo
	 * @param locale
	 * @return
	 */
	public static String getCodeName(String groupCd, String codeNo, Locale locale ) {
		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		if(codeInfo != null) {
			CodeDetailBean bean = codeInfo.getCodeInfo(groupCd, codeNo);
			if (bean != null) {
				return bean.getCodeName(LocaleUtil.locale2String(locale));
			}
		}
		
		return null;
	}
	
	/**
	 * 해당 Code 그룹의 CodeDetailBean 의 List 목록을 돌려 줌.
	 * @param groupCd
	 * @return
	 */
	public static List getCodeList(String groupCd ) {
		CodeInfo codeInfo = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		if(codeInfo != null) {
			return codeInfo.getCodeList( groupCd);
		} else {
			return Collections.EMPTY_LIST;
		}
		
	}
}