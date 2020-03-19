/*
 * Created on 2005. 12. 3.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.aam.AAMManager;
import maf.aam.bean.AAMBean;
import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.util.MafAssert;
import maf.web.context.MafConfig;
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
import maf.web.mvc.view.ViewerSupport;
import maf.web.session.MySession;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.session.exception.UnAuthorizedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Maf Action�� Super Class
 * peeProcess
 * doWork �Ǵ� cmd�� ������ Method ���
 *
 * @author bluevlad
 *
 */
public abstract class MafAction extends MafCommand {
	public final static String DEFAULT_VIEW = "default";
    protected MdbDriver oDb = null;
    protected BaseHttpSession sessionBean = null;
    protected FormConfig formConfig = null;
    protected String  controlAction = null;
    protected SimpleResult result = null;
    protected SiteInfo siteInfo = null;
    protected Locale locale = null;
    protected String cmdParam = null;
    protected String cmd = null;
    private AAMBean AAM = null;
    
//    protected AAMBean auth = null;

    private final Log logger = LogFactory.getLog(MafAction.class);

    public synchronized ViewInfoConfig execute(ActionConfig commandConfig, ViewInfoMap viewInfoMap, HttpServletRequest _req, HttpServletResponse _res)
            throws MafException  {
    	MyHttpServletRequest mreq = null;

    	/**
    	 * ���� Action�� �Ͼ���� site������ Locale ������ ���� �´�.
    	 */
    	this.siteInfo = (SiteInfo) _req.getAttribute(RequestMonFilter.KEY_SITEINFO);
    	this.locale = MafConfig.resolveLocale(_req);

        try {
            // Servlet���� ����� DB object�� �Ѱ���
            // ���� Connection�� ����Ҷ��� �Ͼ� ��
            this.oDb = Mdb.getMdbDriver();

            
            if (this.oDb == null) {
                throw new MdbException( "Database Connection  Fail!!!" );
            }
            if( commandConfig.isDebug() ) {
            	this.oDb.setDebug(true);
            	maf.logger.Logging.printRequest(_req);
            }
            this.sessionBean =  MySession.getSessionBean( _req, _res );

             mreq = new MyHttpServletRequest( _req );

            this.result = new SimpleResult();

            this.controlAction = _req.getRequestURL().toString();
            // ���������� param ��
            this.cmdParam = (String) _req.getAttribute(ActionConfig.PARAM_KEY);
           
            cmd = mreq.getP(MafConstant.CMD_NAME, commandConfig.getDefaultCmd());
            CmdConfig method = commandConfig.getMethod(cmd);
            this.AAM = AAMManager.getAAM(_req);
            boolean hasAuth = AAMManager.chkCmdAuth(_req,method);
            if(!hasAuth) {
            	throw new UnAuthorizedException(_req.getRemoteAddr() + ":" + _req.getRequestURL() + "\n["+ commandConfig.getType() + "," + method +"]" + " �ش� ��� ��������� �����ϴ�.");
            }

            // ���� ���μ��� ����
            this.preProcess(mreq, _res);
            
            if ( method != null ) {
            	if(CmdConfig.CMD_TYPE_JAVA.equals(method.getCmdtype())) {
	            	this.formConfig = commandConfig.getForm(method.getFormName());
		            try {
		                Method m = this.getDeclaredMethod( method.getMethod() );
		                Object[] args = new Object[]{ mreq,  _res};
		            	m.invoke(this, args);
	
		            } catch(NoSuchMethodException e) {
		            	throw new MethodNotFoundException("Command [" + commandConfig.getType()+"." + method.getMethod() + ":" + cmd +"]not found : " + e.getMessage());
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
            		// cmdType�� JSP�� ��� jsp�� �Ѱ��� 
            		result.setForward(method.getMethod());
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
        	if(result.hasDataSource()) {
        		ViewerSupport.setDataSourceList(_req, result.getDataSource());
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
	 * Login ������ �ִ��� Ȯ�� / ������  SessionInvalidatedException �߻� 
	 * @throws SessionInvalidatedException
	 */
	protected  void chkLogin()  throws SessionInvalidatedException {
		if(this.sessionBean == null) {
			throw new SessionInvalidatedException();
		}
	}
	
	/**
	 * request�� ���� �Ѱ��� �Դ��� Ȯ��  ������ MissingParameterException �߻�
	 * @param _req
	 * @param key
	 * @throws MissingParameterException
	 */
	protected  void chkEmpty(MyHttpServletRequest _req, String key) throws MissingParameterException {
		MafAssert.chkEmpty(_req, key);
	}
	/**
	 * request�� ���� �Ѱ��� �Դ��� Ȯ��  ������ MissingParameterException �߻�
	 * @param _req
	 * @param String[]
	 * @throws MissingParameterException
	 */
	protected  void chkEmpty(MyHttpServletRequest _req, String[] keys) throws MissingParameterException {
		MafAssert.chkEmpty(_req, keys);
	}
	
	/**
	 * ���� Page�� ���� ���� �� ���� �ش�.
	 * @param auth_type(C|R|U|D) �빮�� AAMManager.AUTH_TYPE_[READ|UPDATE|DELETE|CREATE]
	 * @return
	 */
	public final boolean chkAuth(String auth_type) {
		if (this.AAM != null) {
			if (AAMManager.AUTH_TYPE_CREATE.equals(auth_type)) {
				return this.AAM.isAuth_c();
			} else if (AAMManager.AUTH_TYPE_READ.equals(auth_type)) {
				return this.AAM.isAuth_r();
			} else if (AAMManager.AUTH_TYPE_UPDATE.equals(auth_type)) {
				return this.AAM.isAuth_u();
			} else if (AAMManager.AUTH_TYPE_DELETE.equals(auth_type)) {
				return this.AAM.isAuth_d();
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}

