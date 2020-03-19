/*
 * Created on 2005. 12. 3.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.mvc.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.aam.AAMManager;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.web.exception.MissingParameterException;
import maf.web.filter.RequestMonFilter;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.beans.SimpleResult;
import maf.web.mvc.beans.SiteInfo;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.CmdConfig;
import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.mvc.exception.MethodNotFoundException;
import maf.web.session.MySession;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.session.exception.UnAuthorizedException;
import miraenet.MiConfig;
import modules.common.beans.SessionBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Maf Action�� Super Class
 * peeProcess
 * doWork �Ǵ� cmd�� ������ Method ���
 *
 * @author goindole
 *
 */
public abstract class MafAction extends MafCommand {
	public final static String DEFAULT_VIEW = "default";
    protected MdbDriver oDb = null;
    protected SessionBean sessionBean = null;
    protected FormConfig formConfig = null;
    protected String  controlAction = null;
    protected SimpleResult result = null;
    protected SiteInfo siteInfo = null;
    protected Locale locale = null;
    protected String cmdParam = null;
    protected String cmd = null;
//    protected AAMBean auth = null;

    private final Log logger = LogFactory.getLog(MafAction.class);



    public synchronized ViewInfoConfig execute(ActionConfig commandConfig, ViewInfoMap viewInfoMap, HttpServletRequest _req, HttpServletResponse _res)
            throws MafException  {
    	MyHttpServletRequest mreq = null;

    	/**
    	 * ���� Action�� �Ͼ���� site������ Locale ������ ���� �´�.
    	 */
    	this.siteInfo = (SiteInfo) _req.getAttribute(RequestMonFilter.KEY_SITEINFO);
    	this.locale = MiConfig.MAF_LOCALE_RESOLVER.resolveLocale(_req);

        try {
            // Servlet���� ����� DB object�� �Ѱ���
            // ���� Connection�� ����Ҷ��� �Ͼ� ��
            this.oDb = Mdb.getMdbDriver();

            
            if (this.oDb == null) {
                throw new MdbException( "Database Connection  Fail!!!" );
            }
            if( commandConfig.isDebug() ) {
            	this.oDb.setDebug(true);
            }
            this.sessionBean = (SessionBean) MySession.getSessionBean( _req, _res );

             mreq = new MyHttpServletRequest( _req );

            this.result = new SimpleResult();

            this.controlAction = _req.getRequestURL().toString();
            // ���������� param ��
            this.cmdParam = (String) _req.getAttribute(ActionConfig.PARAM_KEY);
           

            cmd = mreq.getP(MafConstant.CMD_NAME, commandConfig.getDefaultCmd());
            CmdConfig method = commandConfig.getMethod(cmd);
            boolean hasAuth = AAMManager.chkCmdAuth(_req,method);
            if(!hasAuth) {
            	throw new UnAuthorizedException("�ش� ��� ��������� �����ϴ�.");
            }
            //TODO ���� ó�� 
            // ���� ���μ��� ����
            this.preProcess(mreq, _res);
            
            if ( method != null ) {
            	this.formConfig = commandConfig.getForm(method.getFormName());
	            try {
	                Method m = this.getDeclaredMethod( method.getMethod() );
	                Object[] args = new Object[]{ mreq,  _res};
	            	m.invoke(this, args);

	            } catch(NoSuchMethodException e) {
	            	throw new MethodNotFoundException("Command [" + cmd +"]not found : " + e.getMessage());
	            } catch (InvocationTargetException e) {
	            	Throwable thr = e.getCause();
	            	Logging.trace(thr);
	            	if( thr.getClass().equals(MissingParameterException.class)) {
	            		throw new MissingParameterException(((MissingParameterException)thr).getKey(), thr);
	            	} else {
	            		throw new MafException("internal error : " + thr +","+  thr.getMessage(), thr);
	            	}
	            } catch (IllegalAccessException e) {
	            	throw new MethodNotFoundException("IllegalAccessException : " + cmd + ", " +  method.getMethod() + " : " + e.getMessage());
	            } catch (ExceptionInInitializerError  e) {
	            	logger.error("ExceptionInInitializerError " + cmd + ", " +  method.getMethod() + ", " + e.getClass());
					throw new MafException("method invalid Exception ", e);
	            }
            } else {
            	if(cmd!=null) {
            		logger.error("method not found for cmd : " + cmd + "" );
            		throw new MethodNotFoundException("method not found " + cmd  );
            	} else {
            		doWork(mreq, _res);
            	}
            }
            // ���� ���μ��� ����
            this.postProcess(mreq, _res);
            
            
        	if(this.formConfig !=null) {
        		_req.setAttribute(FormConfig.FORM_ATTRIBUTE_NAME, this.formConfig);

        	}

        } catch (MissingParameterException e) {
        	ResultMessage msg =  new ResultMessage("message", "alert.missingparameter", e.getKey());
        	_req.setAttribute("message", msg.getMessage(_req));
        } finally {
        	try {
        		mreq.deleteAllAttachFile();
        	} catch (Throwable _ignored) {}

            if (this.oDb != null) try {this.oDb.close();} catch (Exception ex) {}
            this.oDb = null;
        }

        
        return MafActionSupport.getViewInfoConfig(viewInfoMap, result, _req, _res);
    }

    /**
     * ���� ���μ��� ������ ó���� ����
     * ����!!! 
     *   1)Exception�� ���� ��� ���� �ܰ���� ����
     *   2) ���� / ���� ���μ����� ���Ѱ����� ���� ����  
     * @param _req
     * @param _res
     * @return
     * @throws MafException
     */
    protected  void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    }

    /**
     * ���� ���μ��� ó���� ó���� ����
     * ����!! ���� ���μ��� ������ ���� ���� ����
     * @param _req
     * @param _res
     * @return
     * @throws MafException
     */
    protected  void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    }


	  public  void doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		;
	  }

	/**
	 *
	 * Ư�� FIELD�� �ش��ϴ� Setter Method�� BeansŬ�������� ã�� �޼ҵ�<br>
	 *
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 *
	 * @param field
	 * @return
	 * @throws NoSuchMethodException
	 */
	public  Method getDeclaredMethod(String fieldName) throws MafException, NoSuchMethodException{

		try{

//			Method[] cs = cls.getMethods();
//			for (int i = 0 ;i < cs.length; i ++) {
//				System.out.print(i + " = " + cs[i].getName());
//				Class[] t = cs[i].getParameterTypes();
//				if (t.length > 0) {System.out.print("(");}
//				for (int j = 0 ;j < t.length; j ++) {
//					System.out.print(", " + t[j].getName());
//				}
//				if (t.length > 0) {System.out.print(")");}
//				System.out.println();
//			}
			Class[] args = new Class[]{MyHttpServletRequest.class , HttpServletResponse.class };
			return this.getClass().getDeclaredMethod(fieldName, args);
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodException(fieldName + " not Found !!!");
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new MafException("No Such Method Exception !!! Method Name : "+ fieldName, e);
		}

	}

	/**
	 * Login ������ �ִ��� Ȯ��
	 * @throws SessionInvalidatedException
	 */
	protected  void chkLogin()  throws SessionInvalidatedException {
		if(this.sessionBean == null) {
			throw new SessionInvalidatedException();
		}
	}



}

