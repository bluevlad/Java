package com.batch.common;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;

public class DfSessionManager {
	
	private IDfSession session = null;
	private IDfSessionManager sessionManager = null;
	
	//private Logger _log = null;
	
	public DfSessionManager(Configurations config, String execLog, String userName, String password, String docbaseName) throws DfException {
		try {
			IDfClient dmClient = DfClient.getLocalClient();
			sessionManager = dmClient.newSessionManager();

			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(userName);
			loginInfo.setPassword(password);
			sessionManager.setIdentity(docbaseName, loginInfo);

		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public DfSessionManager(Configurations config) throws DfException {
		try {
			IDfClient dmClient = DfClient.getLocalClient();
			sessionManager = dmClient.newSessionManager();
			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser("dmadmin");
			loginInfo.setPassword("demo.demo");
			sessionManager.setIdentity("HM", loginInfo);
		
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public DfSessionManager(Configurations config,String execLog, String userId) throws DfException {
		try {
			IDfClient dmClient = DfClient.getLocalClient();
			sessionManager = dmClient.newSessionManager();

			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(userId);
			loginInfo.setPassword("demo.demo");
			sessionManager.setIdentity("HM", loginInfo);
		
		} catch (DfException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public IDfSession getSession(Configurations config) throws DfException {
		
		try {
					
			session = sessionManager.getSession("HM");
			
		
		} catch (DfException e) {
			throw e;
		}
		return session; 
	}

	public void disconnectSession() {
		sessionManager.release(session);
	}	
	
	public void beginTrans() throws DfException {
		sessionManager.beginTransaction();
	}
	
	public void commitT() throws DfException {
		sessionManager.commitTransaction();
	}
	
	public void abortT() throws DfException {
		sessionManager.abortTransaction();
	}	
}
