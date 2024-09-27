package egovframework.com.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.psl.dataaccess.util.CamelUtil;

public class StringTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringTest.class);

	public static void main(String[] args) {
		try {
			StringTest t = new StringTest();
			t.test();
		} catch (Exception ignore) {
			ignore.printStackTrace();
		}
	}
	public void test() {
		try {
			LOGGER.debug("Out : " + CamelUtil.convert2CamelCase("QUE_PERCENT"));
		} catch (Exception ignore) {
			LOGGER.debug("Connection Close : " + ignore.getMessage());
		}
	}

}
