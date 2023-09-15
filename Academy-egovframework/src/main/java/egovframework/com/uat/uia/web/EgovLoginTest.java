package egovframework.com.uat.uia.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.utl.sim.service.EgovFileScrty;

/*
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
*/

@Controller
public class EgovLoginTest {

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovLoginTest.class);

    public static void main(String[] args) {
        try {                                 
        	EgovLoginTest t = new EgovLoginTest();
            t.test();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void test() {

        try {
        	
//        		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
        		String enpassword = EgovFileScrty.encryptPassword("dnflskfk", "webmaster");
        	
        		LOGGER.debug("enpassword : " + enpassword);
        		
        } catch (Exception e) {
        	LOGGER.error("e : " + e);
        }
    }
	
}