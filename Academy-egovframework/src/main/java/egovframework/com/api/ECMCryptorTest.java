package egovframework.com.api;

import org.apache.log4j.Logger;

public class ECMCryptorTest {

    private static Logger LOG = Logger.getLogger(ECMCryptorTest.class);

    public static void main(String[] args) {
        try {                                 
        	ECMCryptorTest t = new ECMCryptorTest();
            t.test();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void test() {
        String str_enc = "";
        String str_dec = "";
        try {
       	
        		LOG.debug("str_enc : " + str_enc);
        		LOG.debug("str_dec : " + str_dec);
        		
        } catch (Exception e) {
        	LOG.error("e : " + e);
        }
    }

}