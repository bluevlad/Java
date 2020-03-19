/*
 * Created on 2005. 11. 21.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.beans;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DbcpInfoBean {
	private String name = null;
	private String driverClassName = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private byte whenExhaustedAction = GenericObjectPool.DEFAULT_WHEN_EXHAUSTED_ACTION;
	private boolean defaultAutoCommit = true;
	private boolean defaultReadOnly = true;
	private boolean testOnBorrow = GenericObjectPool.DEFAULT_TEST_ON_BORROW;
	private boolean testWhileIdle = true; //GenericObjectPool.DEFAULT_TEST_WHILE_IDLE;
	private boolean testOnReturn = GenericObjectPool.DEFAULT_TEST_ON_RETURN;
	private int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
	private int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;
	private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
	private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;
	private int numTestsPerEvictionRun = 1000;//GenericObjectPool.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
	private long timeBetweenEvictionRunsMillis = GenericObjectPool.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
	private long minEvictableIdleTimeMillis = GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	private boolean use = true;
	
	/**
	 * 사용되지 않는 커넥션을 몇 개 검사할지 지정한다.
	 * @return Returns the numTestsPerEvictionRun.
	 */
	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}
	/**
	 * 사용되지 않는 커넥션을 몇 개 검사할지 지정한다.
	 * @param numTestsPerEvictionRun The numTestsPerEvictionRun to set.
	 */
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}
	/**
	 * true일 경우 커넥션 풀에 커넥션을 반환할 때 커넥션이 유효한지의 여부를 검사한다.
	 * @return Returns the testOnReturn.
	 */
	public boolean isTestOnReturn() {
		return testOnReturn;
	}
	/**
	 * true일 경우 커넥션 풀에 커넥션을 반환할 때 커넥션이 유효한지의 여부를 검사한다.
	 * @param testOnReturn The testOnReturn to set.
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	/**
	 * 커넥션 풀에서 가져올 수 있는 커넥션이 없을 때 어떻게 동작할지를 지정한다. 
	 * 1일 경우 maxWait 속성에서 지정한 시간만큼 커넥션을 구할 때 까지 기다리며, 
	 * 0일 경우 에러를 발생시킨다. 
	 * 2일 경우에는 일시적으로 커넥션을 생성해서 사용한다.
	 * when exhausted action, 0 = fail, 1 = block, 2 = grow 
	 * @return Returns the whenExhaustedAction.
	 */
	public byte getWhenExhaustedAction() {
		return whenExhaustedAction;
	}
	/**
	 * 커넥션 풀에서 가져올 수 있는 커넥션이 없을 때 어떻게 동작할지를 지정한다. 
	 * 1일 경우 maxWait 속성에서 지정한 시간만큼 커넥션을 구할 때 까지 기다리며, 
	 * 0일 경우 에러를 발생시킨다. 
	 * 2일 경우에는 일시적으로 커넥션을 생성해서 사용한다.
	 *  when exhausted action, 0 = fail, 1 = block, 2 = grow 
	 * @param whenExhaustedAction The whenExhaustedAction to set.
	 */
	public void setWhenExhaustedAction(byte whenExhaustedAction) {
		this.whenExhaustedAction = whenExhaustedAction;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the defaultAutoCommit.
	 */
	public boolean isDefaultAutoCommit() {
		return defaultAutoCommit;
	}
	/**
	 * @param defaultAutoCommit The defaultAutoCommit to set.
	 */
	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}
	/**
	 * 풀에의해 생성된 커넥션의 read-only 상태
	 * jdbc에 따라 지원하지 않을수도 있다 (예 : informix)
	 * @return Returns the defaultReadOnly.
	 */
	public boolean isDefaultReadOnly() {
		return defaultReadOnly;
	}
	/**
	 * 풀에의해 생성된 커넥션의 read-only 상태
	 * jdbc에 따라 지원하지 않을수도 있다 (예 : informix)
	 * @param defaultReadOnly The defaultReadOnly to set.
	 */
	public void setDefaultReadOnly(boolean defaultReadOnly) {
		this.defaultReadOnly = defaultReadOnly;
	}
	/**
	 * @return Returns the driverClassName.
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	/**
	 * @param driverClassName The driverClassName to set.
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * 커넥션 풀이 제공할 최대 커넥션 개수
	 * @return Returns the maxActive.
	 */
	public int getMaxActive() {
		return maxActive;
	}
	/**
	 * 커넥션 풀이 제공할 최대 커넥션 개수
	 * @param maxActive The maxActive to set.
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최대 커넥션 개수. 음수일 경우 제한이 없다.
	 * @return Returns the maxIdle.
	 */
	public int getMaxIdle() {
		return maxIdle;
	}
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최대 커넥션 개수. 음수일 경우 제한이 없다.
	 * @param maxIdle The maxIdle to set.
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최소 커넥션 개수.
	 * @return Returns the minIdle.
	 */
	public int getMinIdle() {
		return minIdle;
	}
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최소 커넥션 개수.
	 * @param minIdle The minIdle to set.
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	/**
	 * whenExhaustedAction 속성의 값이 1일 때 사용되는 대기 시간. 
	 * 단위는 1/1000초이며, 0 보다 작을 경우 무한히 대기한다.
	 * @return Returns the maxWait.
	 */
	public long getMaxWait() {
		return maxWait;
	}
	/**
	 * whenExhaustedAction 속성의 값이 1일 때 사용되는 대기 시간. 
	 * 단위는 1/1000초이며, 0 보다 작을 경우 무한히 대기한다.
	 * @param maxWait The maxWait to set.
	 */
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * true일 경우 커넥션 풀에서 커넥션을 가져올 때 커넥션이 유효한지의 여부를 검사한다.
	 * @return Returns the testOnBorrow.
	 */
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	/**
	 * true일 경우 커넥션 풀에서 커넥션을 가져올 때 커넥션이 유효한지의 여부를 검사한다.
	 * @param testOnBorrow The testOnBorrow to set.
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	/**
	 * 사용되지 않은 커넥션을 추출하는 쓰레드의 실행 주기를 지정한다. 
	 * 양수가 아닐 경우 실행되지 않는다. 
	 * 단위는 1/1000 초이다.
	 * @return Returns the timeBetweenEvictionRunsMillis.
	 */
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	/**
	 * 사용되지 않은 커넥션을 추출하는 쓰레드의 실행 주기를 지정한다. 
	 * 양수가 아닐 경우 실행되지 않는다. 
	 * 단위는 1/1000 초이다.
	 * @param timeBetweenEvictionRunsMillis The timeBetweenEvictionRunsMillis to set.
	 */
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	/**
	 * true일 경우 비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 
	 * 유효하지 않은 커넥션은 풀에서 제거한다.
	 * @return Returns the testWhileIdle.
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}
	/**
	 * true일 경우 비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 
	 * 유효하지 않은 커넥션은 풀에서 제거한다.
	 * @param testWhileIdle The testWhileIdle to set.
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	/**
	 * 사용되지 않는 커넥션을 추출할 때 이 속성에서 지정한 시간 이상 비활성화 상태인 커넥션만 추출한다. 
	 * 양수가 아닌 경우 비활성화된 시간으로는 풀에서 제거되지 않는다. 
	 * 시간 단위는 1/1000초이다.
	 * @return Returns the minEvictableIdleTimeMillis.
	 */
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	/**
	 * 사용되지 않는 커넥션을 추출할 때 이 속성에서 지정한 시간 이상 비활성화 상태인 커넥션만 추출한다. 
	 * 양수가 아닌 경우 비활성화된 시간으로는 풀에서 제거되지 않는다. 
	 * 시간 단위는 1/1000초이다.
	 * @param minEvictableIdleTimeMillis The minEvictableIdleTimeMillis to set.
	 */
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	/**
	 * @return Returns the use.
	 */
	public boolean isUse() {
		return use;
	}
	/**
	 * @param use The use to set.
	 */
	public void setUse(boolean use) {
		this.use = use;
	}
	
	
}

