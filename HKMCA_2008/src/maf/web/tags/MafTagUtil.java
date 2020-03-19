package maf.web.tags;

import java.text.SimpleDateFormat;

import maf.web.tags.fmt.MiFormat;

public class MafTagUtil {
	static SimpleDateFormat formatter = new SimpleDateFormat();

	public static String getFormatString(Object value, String format) {
		String rv = null;
		if ("currency".equals(format)) {
			rv = MiFormat.currency(value);
		} else if ("yyyymmdd".equals(format)) {
			rv = MiFormat.yyyymmdd(value);
		} else if ("fulldate".equals(format)) {
			rv = MiFormat.fullDate(value);	
		} else if ("fulldatetime".equals(format)) {
			rv = MiFormat.fullDateTime(value);	
		} else if ("shortdate".equals(format)) {
			rv = MiFormat.shortDate(value);			
		} else if ("mmdd".equals(format)) {
			rv = MiFormat.mmdd(value);
		} else if ("autodate".equals(format)) {
			rv = MiFormat.autoDateTime(value);
		} else if ("int".equals(format)) {
			rv = MiFormat.intValue(value)+"";
		} else if ("round".equals(format)) {
			rv = MiFormat.intRound(value)+"";
		}
		return rv;
	}
}
