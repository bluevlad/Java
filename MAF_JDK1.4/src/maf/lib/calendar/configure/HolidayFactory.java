/*
 * Created on 2005-01-10
 */
package maf.lib.calendar.configure;

import maf.lib.calendar.Holidays;

/**
 *
 */
public class HolidayFactory  {
	private static HolidayFactory _instance = new HolidayFactory();


	private HolidayFactory() {
//		globalConfigMap = new HashMap();
	}

	public static synchronized HolidayFactory getInstance() {

		if (_instance == null) {
			_instance = new HolidayFactory();
		}
		return _instance;
	}
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8711254396962638605L;

	public void init(String configFilePath) throws RuntimeException {

        configure(configFilePath);
        
    }
    
    private  synchronized  void configure( String configFilePath) throws RuntimeException{
    	StringBuffer st = new StringBuffer(100);
        try {
            //File configurationFile = new File(configFilePath);
            long t1 = System.currentTimeMillis();
            st.append("\n>>>> Holidayinfo \n     - [" + configFilePath + "] Configure start!!! " );
        	//Config.getInstance().put("HOLIDAY", HolidayDigester.digest(configFilePath));
        	final HolidaysConfig c = HolidayDigester.digest(configFilePath);
        	int cnt = (c==null)?0:c.size();
            Holidays.getInstance().setConfig(c);
           
            st.append("\n<<<< Holidayinfo Configured, " + cnt + " ea, " + ( System.currentTimeMillis()- t1) + " ms" );

        } catch (HolidayDigesterException ex) {
        	st.append(" Error !!!" + ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex);
        }
        System.out.println(st);
    }
    
}
