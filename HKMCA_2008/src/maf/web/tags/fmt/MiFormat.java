/*
 * Created on 2005. 5. 16.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.tags.fmt;

import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import maf.MafProperties;
import maf.MafUtil;
import maf.lib.calendar.LazyDate;
import maf.lib.calendar.MDate;
import maf.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * 

 */
public class MiFormat {
//	static Calendar cal = Calendar.getInstance();
//	private static String[] weekend = {"��","��","ȭ","��","��","��","��"};
	final static private Log logger = LogFactory.getLog(MiFormat.class);
	static SimpleDateFormat formatter = new SimpleDateFormat();
	private static final String shortDate_fs =  MafProperties.FORMAT_SHORT_DATE;//  "dd-MM-yyyy"; // "yyyy-MM-dd";
	private static final String shortDateTime_fs = MafProperties.FORMAT_FULL_DATE_TIME; // "dd-MM-yyyy HH:mm"; // "yy-MM-dd HH:mm";
	private static final String shortTime_fs = "HH:mm";
	private static final String shortMMDD_fs = MafProperties.FORMAT_MMDD;// "MM-dd";
	private static final String fullDateTime_fs = MafProperties.FORMAT_FULL_DATE_TIME;
	private static final String fullDate_fs = MafProperties.FORMAT_FULL_DATE;
	
	public  static String getFormatedDate(Date dt, String format) {
		if(dt != null) {
			try {
				synchronized (formatter) {
					formatter.applyPattern(format);
					return formatter.format(dt);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "&nbsp;";  // TD��  null�ϰ�� ȭ�� ���� ����.
			}
		} else {
			return "&nbsp;";  // TD��  null�ϰ�� ȭ�� ���� ����. 
		}
	}
	/**
	 * yyyy-mm-dd ������ short type
	 * 
	 * @param dt
	 * @return
	 */
	public static String shortDate(Object dt) {
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, shortDate_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, shortDate_fs);
			}
		} else {
			return "&nbsp;";
		}
	}
	/**
	 * yyyy-mm-dd ������ short type
	 * 
	 * @param dt
	 * @return
	 */
	public static String fullDate(Object dt) {
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, fullDate_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, fullDate_fs);
			}
		} else {
			return "&nbsp;";
		}
	}
	/**
	 * yyyy-mm-dd ������ short type
	 * 
	 * @param dt
	 * @return
	 */
	public static String mmdd(Object dt) {
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, shortMMDD_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, shortMMDD_fs);
			}
		} else {
			return "&nbsp;";
		}
	}
	public static String yyyymmdd(Object dt) {
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, shortDate_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, shortDate_fs);
			}
		} else {
			return "&nbsp;";
		}
	}	
	public static String shortTime(Date dt) {
		return getFormatedDate((Date) dt, shortTime_fs);
	}
	
	public static String shortDateTime(Object dt) {
		
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, shortDateTime_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, shortDateTime_fs);
			}
		} else {
			return "&nbsp;";
		}
	}
	/**
	 * full date time
	 * 
	 * @param dt
	 * @return
	 */
	public static String fullDateTime(Object dt) {
		if(dt != null) {
			if(dt instanceof Date) {
				return getFormatedDate((Date) dt, fullDateTime_fs);
			} else {
				
				Date nd = MDate.getDate( dt);
				return getFormatedDate((Date) nd, fullDateTime_fs);
			}
		} else {
			return "&nbsp;";
		}
	}

	
	
	public static String autoDateTime(Object obj) {
		Date dt = null;
		
		if(dt!=null) {
			if(dt != null) {
				if(dt instanceof Date) {
					dt = MDate.getDate( dt);
				} else {
					dt = (Date) obj;
				}
			}
			Date now = LazyDate.now();
			
			if(now.getYear() != dt.getYear()) {
				return getFormatedDate(dt,shortDate_fs);
			} else if ((now.getMonth() == dt.getMonth()) &&
					(now.getDate() == dt.getDate()) ) {
				return getFormatedDate(dt,shortTime_fs);
			} else {
				return getFormatedDate(dt,"MM-dd (E)");
			}
		} else {
			return "&nbsp;";
		}
					

	}
	public static String currency(Object n){
		DecimalFormat f = (DecimalFormat)NumberFormat.getInstance(Locale.KOREA);
		f.setDecimalSeparatorAlwaysShown(false);
		f.applyPattern("#,##0");
		try{
			return f.format(n);
		} catch (Throwable e) {
			return "";
		}
		
	}
	
	public static String number_format(Object n, int d) {
		DecimalFormat f = (DecimalFormat)NumberFormat.getInstance(Locale.KOREA);
		f.setDecimalSeparatorAlwaysShown(false);
		String pat = null;
		if( d == 0) {
			pat = "#,##0";
		} else if(d > 0) {
			pat = "#,##0." + StringUtils.strRepeat( "0",d);
		}
//		Logging.log(MiFormat.class, pat + " d = " + d);
//		if (d == 1) {
//			f.applyPattern("#,##0.0");
//		} else if (d == 2) {
//				f.applyPattern("#,##0.00");
//		} else {
			f.applyPattern(pat);
		try{
			return f.format(n);
		} catch (Throwable e) {
			return "0";
		}
	}
	
	public static double doubleValue(Object n) {
		try {
			return MafUtil.parseDouble(n.toString());
		} catch (Throwable e) {
			return 0;
		}
	}
	
	public static int intValue(Object n) {
		try {
			return MafUtil.parseInt(n.toString());
		} catch (Throwable e) {
			return 0;
		}
	}
	
	public static int intRound(Object n) {
		final String pattern = "######";
		final DecimalFormat dformat = new DecimalFormat(pattern);

		try {
			return MafUtil.parseInt(dformat.format(MafUtil.parseDouble(n.toString())));
		} catch (Throwable e) {
			return 0;
		}		
	}
	
	public static float intDivide(Object m, Object n) {
		try {
			return ( MafUtil.parseFloat(m.toString()) / MafUtil.parseFloat(n.toString()) );
		} catch (Throwable e) {
			return 0;
		}		
	}
	
	public static String getPercent(Object m, Object n) {
		try {
			return (intDivide(m,n)*100)+"%";
		} catch (Throwable e) {
			return "";
		}		
	}
	
	public static String getStringValue(Object str) {
		try {
			return str.toString();
		} catch (Throwable e) {
			return "";
		}
	}
	/**
	 * Returns Char1 concatenated with Char2.
	 */
	public static String concat(Object str1, Object str2) {
		try {
			return str1.toString() + str2.toString();
		} catch (Throwable e) {
			return "";
		}
	}
}
