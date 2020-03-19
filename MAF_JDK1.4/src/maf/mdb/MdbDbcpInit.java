/*
 * Created on 2005. 11. 21.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.mdb;

import java.sql.DriverManager;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import maf.lib.xml.XMLDigester;
import maf.logger.Logging;
import maf.mdb.beans.DBConfigBean;
import maf.mdb.beans.DbcpInfoBean;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;



public class MdbDbcpInit  {
	private static MdbDbcpInit _instance = null;
	
	public static synchronized MdbDbcpInit getInstance() {
		if (_instance == null) {
			_instance = new MdbDbcpInit();
		}
		return _instance;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6929174230966216225L;

	private DBConfigBean configuration = null;

	public void init(ServletContext ctx) throws Exception {
//		ServletContext ctx = config.getServletContext();
//		String configFile = ctx.getRealPath(config.getInitParameter("configFile"));
//		String ruleFile = ctx.getRealPath(config.getInitParameter("ruleFile"));
		String configFile = ctx.getRealPath("/WEB-INF/maf-config/DB.xml");
		String ruleFile = ctx.getRealPath("/WEB-INF/maf-config/DBDigesterRules.xml");
		
		configure(configFile, ruleFile);

		List connList = configuration.getConnList();
		DbcpInfoBean bean = null;
		for (int i = 0; i < connList.size(); i++) {
			bean = (DbcpInfoBean) connList.get(i);
			try {
				if(bean.isUse()) {
					setupDriver(bean);
				}
			} catch (Exception e) {
				
			}
		}

	}

	
	private synchronized void configure(String configFilePath, String ruleFilePath) throws ServletException {
		try {
			System.out.println(">>>> MdbDbcpInit Configure Start ...");
			configuration = (DBConfigBean) XMLDigester.digest(configFilePath, ruleFilePath);
			System.out.println("<<<< MdbDbcpInit Configure Finished ");
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage(), ex);
		}
	}

	public void destroy() {
		if (configuration != null) {
			List connList = configuration.getConnList();
			DbcpInfoBean bean = null;
			try {
				PoolingDriver driver = (PoolingDriver) DriverManager
				                                                    .getDriver("jdbc:apache:commons:dbcp:");
				for (int i = 0; i < connList.size(); i++) {
					bean = (DbcpInfoBean) connList.get(i);
					driver.closePool(bean.getName());
					System.out.println("  conn " + bean.getName() + " destroy ### ");
				}
			} catch (Exception e) {
				Logging.trace(e);
			}
		}
		System.out.println("### " + this.getClass() + " destroy ### ");
	}

	public void setupDriver(DbcpInfoBean bean) throws Exception {

		try {

			// jdbc class�� �ε��մϴ�.
			Class.forName(bean.getDriverClassName());
		} catch (ClassNotFoundException classnotfoundexception) {
			System.out.println(bean.getDriverClassName() + " is not found");
			classnotfoundexception.printStackTrace();
			throw classnotfoundexception;
		}

		// Ŀ�ؼ� Ǯ�� ����� commons-collections�� genericOjbectPool�� �����մϴ�.
		GenericObjectPool connectionPool = new GenericObjectPool(null);
		connectionPool.setMaxActive(bean.getMaxActive());
		connectionPool.setMaxIdle(bean.getMaxIdle());
		connectionPool.setMinIdle(bean.getMinIdle());
		connectionPool.setMaxWait(bean.getMaxWait());
		connectionPool.setTestOnBorrow(bean.isTestOnBorrow());
		connectionPool.setTimeBetweenEvictionRunsMillis(bean.getTimeBetweenEvictionRunsMillis());
		connectionPool.setTestWhileIdle(bean.isTestWhileIdle());
		connectionPool.setMinEvictableIdleTimeMillis(bean.getMinEvictableIdleTimeMillis());
		connectionPool.setWhenExhaustedAction(bean.getWhenExhaustedAction());
		connectionPool.setTestOnReturn(bean.isTestOnReturn());
		connectionPool.setNumTestsPerEvictionRun(bean.getNumTestsPerEvictionRun());
		
		
		// Ǯ�� Ŀ�ؼ��� �����ϴµ� ����ϴ� DriverManagerConnectionFactory�� �����մϴ�.
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(bean.getUrl(), bean.getUsername(),
																					bean.getPassword());

		// ConnectionFactory�� ���� Ŭ������ PoolableConnectionFactory�� �����Ѵ�
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, connectionPool,
																							null, null, bean.isDefaultReadOnly(),
																							bean.isDefaultAutoCommit());

		// ���������� PoolingDriver �ڽ��� �ε��Ѵ�
		Class.forName("org.apache.commons.dbcp.PoolingDriver");

		PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

		// �׸��� Ǯ�� ����Ѵ�. Ǯ�̸��� "unicorn"�̶�� �����Ͽ���
		driver.registerPool(bean.getName(), connectionPool);

	}


}
