package modules.etest.support;

import maf.util.StringUtils;

public class QueueUtil {
	
	
		public static String[] getAnsWerObject(String ans) {
			return StringUtils.delimitedListToStringArray(ans, Etest.ANS_SEPERATOR);
		}
}
