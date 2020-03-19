/*
 * Created on 2005. 11. 21.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
	 * ������ �ʴ� Ŀ�ؼ��� �� �� �˻����� �����Ѵ�.
	 * @return Returns the numTestsPerEvictionRun.
	 */
	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}
	/**
	 * ������ �ʴ� Ŀ�ؼ��� �� �� �˻����� �����Ѵ�.
	 * @param numTestsPerEvictionRun The numTestsPerEvictionRun to set.
	 */
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}
	/**
	 * true�� ��� Ŀ�ؼ� Ǯ�� Ŀ�ؼ��� ��ȯ�� �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��Ѵ�.
	 * @return Returns the testOnReturn.
	 */
	public boolean isTestOnReturn() {
		return testOnReturn;
	}
	/**
	 * true�� ��� Ŀ�ؼ� Ǯ�� Ŀ�ؼ��� ��ȯ�� �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��Ѵ�.
	 * @param testOnReturn The testOnReturn to set.
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	/**
	 * Ŀ�ؼ� Ǯ���� ������ �� �ִ� Ŀ�ؼ��� ���� �� ��� ���������� �����Ѵ�. 
	 * 1�� ��� maxWait �Ӽ����� ������ �ð���ŭ Ŀ�ؼ��� ���� �� ���� ��ٸ���, 
	 * 0�� ��� ������ �߻���Ų��. 
	 * 2�� ��쿡�� �Ͻ������� Ŀ�ؼ��� �����ؼ� ����Ѵ�.
	 * when exhausted action, 0 = fail, 1 = block, 2 = grow 
	 * @return Returns the whenExhaustedAction.
	 */
	public byte getWhenExhaustedAction() {
		return whenExhaustedAction;
	}
	/**
	 * Ŀ�ؼ� Ǯ���� ������ �� �ִ� Ŀ�ؼ��� ���� �� ��� ���������� �����Ѵ�. 
	 * 1�� ��� maxWait �Ӽ����� ������ �ð���ŭ Ŀ�ؼ��� ���� �� ���� ��ٸ���, 
	 * 0�� ��� ������ �߻���Ų��. 
	 * 2�� ��쿡�� �Ͻ������� Ŀ�ؼ��� �����ؼ� ����Ѵ�.
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
	 * Ǯ������ ������ Ŀ�ؼ��� read-only ����
	 * jdbc�� ���� �������� �������� �ִ� (�� : informix)
	 * @return Returns the defaultReadOnly.
	 */
	public boolean isDefaultReadOnly() {
		return defaultReadOnly;
	}
	/**
	 * Ǯ������ ������ Ŀ�ؼ��� read-only ����
	 * jdbc�� ���� �������� �������� �ִ� (�� : informix)
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
	 * Ŀ�ؼ� Ǯ�� ������ �ִ� Ŀ�ؼ� ����
	 * @return Returns the maxActive.
	 */
	public int getMaxActive() {
		return maxActive;
	}
	/**
	 * Ŀ�ؼ� Ǯ�� ������ �ִ� Ŀ�ؼ� ����
	 * @param maxActive The maxActive to set.
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	/**
	 * ������ �ʰ� Ǯ�� ����� �� �ִ� �ִ� Ŀ�ؼ� ����. ������ ��� ������ ����.
	 * @return Returns the maxIdle.
	 */
	public int getMaxIdle() {
		return maxIdle;
	}
	/**
	 * ������ �ʰ� Ǯ�� ����� �� �ִ� �ִ� Ŀ�ؼ� ����. ������ ��� ������ ����.
	 * @param maxIdle The maxIdle to set.
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	
	/**
	 * ������ �ʰ� Ǯ�� ����� �� �ִ� �ּ� Ŀ�ؼ� ����.
	 * @return Returns the minIdle.
	 */
	public int getMinIdle() {
		return minIdle;
	}
	/**
	 * ������ �ʰ� Ǯ�� ����� �� �ִ� �ּ� Ŀ�ؼ� ����.
	 * @param minIdle The minIdle to set.
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	/**
	 * whenExhaustedAction �Ӽ��� ���� 1�� �� ���Ǵ� ��� �ð�. 
	 * ������ 1/1000���̸�, 0 ���� ���� ��� ������ ����Ѵ�.
	 * @return Returns the maxWait.
	 */
	public long getMaxWait() {
		return maxWait;
	}
	/**
	 * whenExhaustedAction �Ӽ��� ���� 1�� �� ���Ǵ� ��� �ð�. 
	 * ������ 1/1000���̸�, 0 ���� ���� ��� ������ ����Ѵ�.
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
	 * true�� ��� Ŀ�ؼ� Ǯ���� Ŀ�ؼ��� ������ �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��Ѵ�.
	 * @return Returns the testOnBorrow.
	 */
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	/**
	 * true�� ��� Ŀ�ؼ� Ǯ���� Ŀ�ؼ��� ������ �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��Ѵ�.
	 * @param testOnBorrow The testOnBorrow to set.
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	/**
	 * ������ ���� Ŀ�ؼ��� �����ϴ� �������� ���� �ֱ⸦ �����Ѵ�. 
	 * ����� �ƴ� ��� ������� �ʴ´�. 
	 * ������ 1/1000 ���̴�.
	 * @return Returns the timeBetweenEvictionRunsMillis.
	 */
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	/**
	 * ������ ���� Ŀ�ؼ��� �����ϴ� �������� ���� �ֱ⸦ �����Ѵ�. 
	 * ����� �ƴ� ��� ������� �ʴ´�. 
	 * ������ 1/1000 ���̴�.
	 * @param timeBetweenEvictionRunsMillis The timeBetweenEvictionRunsMillis to set.
	 */
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	/**
	 * true�� ��� ��Ȱ��ȭ Ŀ�ؼ��� ������ �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��ؼ� 
	 * ��ȿ���� ���� Ŀ�ؼ��� Ǯ���� �����Ѵ�.
	 * @return Returns the testWhileIdle.
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}
	/**
	 * true�� ��� ��Ȱ��ȭ Ŀ�ؼ��� ������ �� Ŀ�ؼ��� ��ȿ������ ���θ� �˻��ؼ� 
	 * ��ȿ���� ���� Ŀ�ؼ��� Ǯ���� �����Ѵ�.
	 * @param testWhileIdle The testWhileIdle to set.
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	/**
	 * ������ �ʴ� Ŀ�ؼ��� ������ �� �� �Ӽ����� ������ �ð� �̻� ��Ȱ��ȭ ������ Ŀ�ؼǸ� �����Ѵ�. 
	 * ����� �ƴ� ��� ��Ȱ��ȭ�� �ð����δ� Ǯ���� ���ŵ��� �ʴ´�. 
	 * �ð� ������ 1/1000���̴�.
	 * @return Returns the minEvictableIdleTimeMillis.
	 */
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	/**
	 * ������ �ʴ� Ŀ�ؼ��� ������ �� �� �Ӽ����� ������ �ð� �̻� ��Ȱ��ȭ ������ Ŀ�ؼǸ� �����Ѵ�. 
	 * ����� �ƴ� ��� ��Ȱ��ȭ�� �ð����δ� Ǯ���� ���ŵ��� �ʴ´�. 
	 * �ð� ������ 1/1000���̴�.
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

