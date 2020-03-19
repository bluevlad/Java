/*
 * �ۼ��� ��¥: 2005-01-27
 *
 */
package xadmin.cmst;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import xadmin.cmst.beans.DTreeBean;


/**
 * @author goindole
 *
 */
public class JSTree {
    private int lastId = 0;
    
    public JSTree() {
    }
	

    /**
     * 
     * @param path
     * @return
     */
    public List getTree(List nodes, String path, String url, String target) {
        return getTree(nodes, path, url, target, 0, 0);
    }
    
    public List getTree(List nodes, String path, String url, String target, int startId, int pid) {
    	//StringBuffer sb = new StringBuffer();
        
		/* 
		 * pid�� �θ������� id���� ��Ÿ����.
		 * id�� �����ڽ��� ��Ÿ���� ������ ��
		 * id���� ������ ���̾���Ѵ�. �ߺ��Ǹ� �ȵȴ�.
		 */
        //List nodes = new ArrayList();
        
		int id = startId;
		File f = new File(path);
		
		if(f.exists() ) {
			if(f.isDirectory()) {
			    File[] list = f.listFiles();
			    Arrays.sort(list);
				for (int i=0; i < list.length; i++) {
					if (list[i].isDirectory()) {
						id = id +1;
						//sb.append(createFoldersTree(list[i],  id, pid, list[i].getAbsolutePath(), path, url, target ));				
						//id = createFolders(sb, list[i],  id, pid, path, url, target);		
						nodes.add(createFoldersTree(list[i],  id, pid, list[i].getAbsolutePath(), path, url, target ));
						id = createFolders(nodes, list[i],  id, pid, path, url, target);						
					}
				}
			}
		} else {
		    f.mkdir();
		}
		lastId = id; 

		return nodes;
//		return sb.toString();
    }
    
    
    public int getLastId() {
        return lastId;
    }
    /**
	 * View���� ������ �ڹٽ�ũ��Ʈ ������ �����Ѵ�.
	 *
	 * use : cmd --> leftfolder
	 
	 * @param f
	 * @param id
	 * @param pid
	 * @param absPath
	 * @param webfolderRoot
	 * @return
	 */
	private DTreeBean createFoldersTree(File f, int id, int pid, String absPath, String webfolderRoot, String url, String target) {
		int len = webfolderRoot.length();
		String xabsPath = org.apache.taglibs.standard.functions.Functions.escapeXml(absPath.substring(len));
		String xfileName = org.apache.taglibs.standard.functions.Functions.escapeXml(f.getName());
		
		url = url.replaceAll("%PATH%", xabsPath);
		return new DTreeBean(id, pid, xfileName, xfileName, url, xabsPath, target);
//		return ("d.add("+id+"," + pid + ",'" +xfileName + "'," + url + ",'" + xabsPath +"','" + target + "');\n");				
			
	}
	
    /////////////////////////////////////////////////////////////////////////////
	/**
	 * ���������� ������ �ִ��� �Ǵ� 
	 * boolean���� �ƴϰ� String���� return�ϴ� ���� 
	 * ���������� ��Ÿ���� ���� �ʿ��ϱ� ����.
	 * 
	 * use : cmd --> leftfolder
	 
	 * @param f
	 * @return
	 */
	private String hasChildFolder(File f) {
		File[] list = f.listFiles();
		for (int i=0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				return list[i].getAbsolutePath();
			}
		}
		return "";
	}
	
	
	   /**
	 * ������ �����Ұ�� ��ũ��Ʈ ������ �����ϴ� Method�� ȣ��.
	 * ����Լ��� ����Ѵ�.
	 * 
	 * use : cmd --> leftfolder
	 
	 * @param sb
	 * @param f
	 * @param sc
	 * @param id
	 * @param pid
	 * @param webfolderRoot
	 * @return
	 */
	private int createFolders(List sb, File f, int id, int pid, String webfolderRoot, String url, String target) {
	    
		String temp = "";
 		/*
 		 * �ڽ������� �ڽ��� �θ������� id���� pid������ �������Ѵ�.
 		 */
		pid = id;

		File[] list = f.listFiles();
		for (int i=0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				id = id + 1;
				sb.add( createFoldersTree(list[i],  id, pid, list[i].getAbsolutePath(), webfolderRoot, url, target) );
				
				temp = hasChildFolder(list[i]);
				if (!temp.equals("")) {
					id = createFolders(sb, list[i], id, pid, webfolderRoot, url, target);
				} 
			} 
		}
		
		return id;
	}
}
