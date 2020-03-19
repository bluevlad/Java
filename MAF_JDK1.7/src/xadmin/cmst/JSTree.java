/*
 * 작성된 날짜: 2005-01-27
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
		 * pid는 부모폴더의 id값을 나타낸다.
		 * id는 폴더자신을 나타내는 고유한 값
		 * id값을 유일한 값이어야한다. 중복되면 안된다.
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
	 * View에서 보여질 자바스크립트 문장을 생성한다.
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
	 * 하위폴더를 가지고 있는지 판단 
	 * boolean값이 아니고 String으로 return하는 것은 
	 * 하위폴더를 나타내는 값이 필요하기 때문.
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
	 * 폴더가 존재할경우 스크립트 문장을 생성하는 Method를 호출.
	 * 재귀함수를 사용한다.
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
 		 * 자식폴더는 자신의 부모폴더의 id값을 pid값으로 가져야한다.
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
