/*
 * JstlSupport.java, @ 2005-03-11
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import maf.logger.Trace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * 

 */
public class JstlSupport {

    public static Object invoke(Object obj, String methodNM, Object param) {
        
        Class cls = obj.getClass();

        Class fieldType = null;
        try {
            Field field = cls.getDeclaredField( methodNM );
            fieldType = field.getType();
            Method method = obj.getClass().getDeclaredMethod( methodNM, new Class[] { fieldType } );
            Object[] args = new Object[1];
            args[0] = param;
            return method.invoke( obj, args );
        } catch (Throwable e) {
        	Log logger = LogFactory.getLog(JstlSupport.class);
//            logger.error( e.getMessage() );
            logger.error(Trace.getStackTrace(e));
            return null;
        }
    }
}