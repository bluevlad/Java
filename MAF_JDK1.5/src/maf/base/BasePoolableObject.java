/*
 * Created on 2006. 5. 22.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.base;


public class BasePoolableObject  {
    // for  CommonPool
	private long creationTime = 0;
    private boolean active = false;
    
    /**
     * CommonPool 관련 
     * @param b
     */
    public void setActive(boolean b) {
    	this.active = b;;
    }
    
    public boolean isActive() {
    	return this.active;
    }
    
    public boolean isTimeOut() {
    	return System.currentTimeMillis() - this.creationTime > 60 * 1000 * 10;
    }
    
    public BasePoolableObject() {
    	this.creationTime = System.currentTimeMillis();
    	System.out.println(getClass() + " invoke !!!");
    }
}

