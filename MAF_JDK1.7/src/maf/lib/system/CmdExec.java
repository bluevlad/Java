package maf.lib.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *
 */
public class CmdExec {
	StringBuffer rMessage = null;
	
	/**
	 * �ܺ����α׷����� ���
	 *
	 */
	public  CmdExec() {
		
	}
	
	/**
	 * �ܺ� ���α׷� cmdline�� ���� �Ѵ�. 
	 * @param cmdline
	 * @return
	 * @throws MiSystemException
	 */
	public int exec(String cmdline, String[] envs) throws MiSystemException{
		rMessage = new StringBuffer();
		int ec = -1;
		try {
			String line = null;
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec(cmdline, envs);
			
			BufferedReader br = new BufferedReader (new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				rMessage.append(line + "\n");
			}
			br.close();
			ec = p.waitFor();
		} catch (Throwable e) {
			Log logger = LogFactory.getLog(getClass());
			logger.error(e.toString());
			throw new MiSystemException(e.getMessage());
		}
		return ec;
	}
	
	/**
	 * ���� �� return �� �޼����� ���� �ش�. 
	 * @return
	 */
	public String getMessage() {
		return rMessage.toString();
	}
}
