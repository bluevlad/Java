package com.willbes.platform.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**  
 * @Class Name : MirWebBindingInitializer.java
 * @Description : MirWebBindingInitializer Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2014.09.02           최초생성
 * 
 * @author K
 * @since 2014.09.02
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Miraenet All right reserved.
 */
public class MirWebBindingInitializer implements WebBindingInitializer {

	/**
	 * initBinder
	 * @param binder
	 * @param request
	 * @see 
	 */
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

}
