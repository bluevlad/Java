/*
 * Created on 2005. 11. 25.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import java.util.HashMap;
import java.util.Map;

public class MStore {
//	public static final String KEY_CODE_DET = "CODE_DET";
	/**
	 * 저장형태 maf.configuration.CodeInfo
	 */
	public static final String KEY_CODE_DET_MAP = "CODE_DET_MAP";
//	public static final String KEY_TABLE_DICTIONARY = "TABLE_DICTIONARY";
	
    private static MStore instance = new MStore(); 
    private Map config = new HashMap();
    
    private MStore(){
        
    }
    
    public synchronized void setConfig(String key, Object newConfig) {
    	config.put(key, newConfig );
    }

    public Object getConfig(String key) {
    	return config.get(key);
    }
    
    public static synchronized MStore getInstance() {
        return instance;
    }
    
    public boolean containsKey(String key) {
    	return config.containsKey(key);
    }
    
    
}

