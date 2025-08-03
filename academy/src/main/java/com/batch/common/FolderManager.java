package com.batch.common;

/*
 * 占쌜쇽옙占쏙옙 占쏙옙짜: 2006. 2. 2.
 *
 * TODO 占쏙옙占�占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占시몌옙트占쏙옙 占쏙옙占쏙옙占싹톥占�占쏙옙=8占쏙옙 占싱듸옙占싹십시울옙.
 * 창 - 환占쏙옙 占쏙옙d - Java - 占쌘듸옙 占쏙옙타占쏙옙 - 占쌘듸옙 占쏙옙占시몌옙트
 */

import java.util.StringTokenizer;

//import penta.core.log.Log;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.documentum.operations.impl.common.GetObject;

/**
 * @author bassman
 *
 * TODO 占쏙옙占�/占쏙옙 占쌍쇽옙占쏙옙 占쏙옙占쏙옙 占쏙옙占시몌옙트占쏙옙 占쏙옙占쏙옙占싹톥占�占쏙옙=8占쏙옙 占싱듸옙占싹십시울옙.
 * 창 - 환占쏙옙 占쏙옙d - Java - 占쌘듸옙 占쏙옙타占쏙옙 - 占쌘듸옙 占쏙옙占시몌옙트
 */
public class FolderManager {
	
	// 그룹문서함 ACL Domain
	private static String groupCabinetAclDomain;
	// 그룹문서함 ACL Name
	private static String groupCabinetAclName;
	
  /**
   * Creates a folder specified by folder path. If the folders in the
   * path don't exist, they are created too. Thus, its a recursive
   * docbase folder creation method.
   * 
   * <p>
   * The first component of the path is assumed to be a cabinet and
   * an object of type dm_cabinet is created for the first component if
   * it does not exist.
   * 
   * <p>
   * This method is expensive in terms of time and should be called with discretion.
   * 
   * <p>
   * The last argument of the method specifies a base path. This path specifies
   * the path beyond which the recursive folder creation proceeds. Specifying this
   * value will avoid checks for existence of folders specified in base path. 
   * This will eventually help speed up processing and thus serves as a heuristic. 
   * This is an optional argument and if left empty or <code>null</code> all folders
   * specified in <code>path</code> 
   * are checked for existence and created if not present. This argument should not
   * end with the path separator ('/'). 
   * 
   * @param sess
   * @param path The folder path to be created.
   * @param pathSep (optional) Default value is '/'
   * @param type (optional) The type of the folders. Default is dm_folder.
   * @param basePath (optional) See the the method description above for this argument. 
   * @return
 * @throws Exception 
   * @throws RuntimeException This exception is thrown if basePath is not a valid basePath of
   * <br>                     specified path.
   */
	public static IDfFolder createFolder(
	  IDfSession sess,
	  String path,
	  String pathSep,
	  String type,
	  String basePath,
	  String ownerName)
	  throws Exception
	{
	  IDfFolder newFldr = null;
	  boolean cabinetStillToBeProcessed = true;
	  
	  // ACL 정보
	  String aclDomain = "dm_dbo";
	  String aclName = "SmartDisk_Cab_Usr";
	
	  if (pathSep == null || (pathSep.length() == 0))
	  {
	     pathSep = "/";
	  }
	
	  if ((type == null) || (type.length() == 0))
	  {
	     type = "dm_folder";
	  }
	
	  IDfId currentId = null;
	
	  StringBuffer bufFldrPath = new StringBuffer(48);
	  if ((basePath != null) && (basePath.length() > 1))
	  {
		try {
			currentId = getIdByPath(sess, basePath);
		} catch (Exception e) {
			currentId = null;
		}
	     if (currentId != null && !currentId.isNull())
	     {
	        //base path actually exists.
	        bufFldrPath.append(basePath);
	        cabinetStillToBeProcessed = false; //cabinet already processed due to base path.
	        
	        int basePathLen = basePath.length();            
	        if(basePathLen < path.length())
	        {
	           path = path.substring(basePath.length());
	        }            
	     }
	  }
	
	  StringTokenizer tokFldrNames = new StringTokenizer(path, pathSep);
	
	  if (cabinetStillToBeProcessed)
	  {
	     //Execution will come here only if basePath was not specified or
	     //if specified basePath was not valid.
	     String cabinetName = tokFldrNames.nextToken();
	     StringBuffer cabQual = new StringBuffer(32);
	     cabQual.append("dm_cabinet where object_name='").append(
	        cabinetName).append(
	        "'");
	
		try {
	        currentId = sess.getIdByQualification(cabQual.toString());
		} catch (Exception e) {
			currentId = null;
		}
	     if (currentId == null || currentId.isNull())
	     {
	        //need to create cabinet.
	        IDfFolder cab = (IDfFolder) sess.newObject("dm_cabinet");
	        cab.setObjectName(cabinetName);
	        cab.setOwnerName(ownerName);
	        cab.save();
	        currentId = cab.getObjectId();            
	     }
	     bufFldrPath.append(pathSep).append(cabinetName);
	  }
	  //By this point the bufFldrPath will either have the cabinet path
	  //or it will have the basePath in it. 
	  
	  //now create all folders beyond the cabinet or basePath.
	  while(tokFldrNames.hasMoreTokens())
	  {         
	     String parentPath = bufFldrPath.toString();
	     
	     String fldrName = tokFldrNames.nextToken();
	     bufFldrPath.append(pathSep).append(fldrName);
	     //by this point the buffer should contain the new expected path
	     
	     try {
	    	 currentId = getIdByPath(sess,bufFldrPath.toString());
	     } catch (Exception e) {
	    	 currentId = null;
	     }
	     if( currentId == null || currentId.isNull())
	     {
	        //looks like the new folder in the path does not exist.
	    	 
	    	// 그룹문서함 ACL 정보가 설정되어 있으면
	    	// 새폴더 생성 시 해당 ACL 정보로 설정
	    	if (groupCabinetAclDomain != null && groupCabinetAclName != null) {
	    		aclDomain = groupCabinetAclDomain;
	    		aclName = groupCabinetAclName;
	    	}
	    	/*
	    	 * 상위폴더 object 가져오기
	    	 */
	    	IDfFolder parentFolderObj = (IDfFolder) sess.getObjectByPath(parentPath);
	    	aclDomain = parentFolderObj.getACLDomain();
	    	aclName = parentFolderObj.getACLName();
	    	
	        newFldr = (IDfFolder) sess.newObject(type);
	        newFldr.setObjectName(fldrName);
	        newFldr.link(parentPath);
	        newFldr.setACLDomain(aclDomain);
	        newFldr.setACLName(aclName);
	        newFldr.setOwnerName(ownerName);
	        newFldr.save();
	        currentId = newFldr.getObjectId();            
	        
	        /*
	         * MOBIS 추가 속성 관리
	         */
	        FolderUtil.addNewManagedAttribute(sess, newFldr, parentFolderObj);
	     }
	     else
	     {
	    	newFldr = (IDfFolder) sess.getObject(currentId);
	     }
	     //by this point currentId should point to next folder in path                           
	  }//while(all folder names)
	  
	  return newFldr;
	}
	
	/**
	* Gets an object id given the path of the object. This method is a simple
	* wrapper around the IDfSession#getIdByQualification(...) method. This method
	* can be used instead of the IDfSession.getObjectByQualification() if all that is
	* needed is the object id instead of a full fetch that getObjectByQualification(...) 
	* method does. This method can be useful to check for the existence of an 
	* object if path is known.
	* 
	* @param sess Docbase session
	* @param path Docbase path of the object whose id needs to be obtained.
	* @return instance of <code>IDfId</code>. This instance will contain
	*         a valid id if the object was found or an invalid id if the
	*         object was not found or if the path was illegal.
	*    
	* @throws DfException
	*/
	private static IDfId getIdByPath(IDfSession sess, String path)
	  throws DfException
	{       
	
	  int pathSepIndex = path.lastIndexOf('/');
	  if (pathSepIndex == -1)
	  {
	     return new DfId("000");
	  }
	
	  StringBuffer bufQual = new StringBuffer(32);
	  if (pathSepIndex == 0)
	  {
	     //its a cabinet path 
	     bufQual.append(" dm_cabinet where object_name='");
	     bufQual.append(path.substring(1));
	     bufQual.append("'");
	  }
	  else
	  {
	     bufQual.append(" dm_sysobject where FOLDER('");
	     bufQual.append(path.substring(0, pathSepIndex));
	     bufQual.append("') ");
	     bufQual.append(" and object_name='");
	     bufQual.append(path.substring(pathSepIndex + 1));
	     bufQual.append("'");
	  }
	
	  String strQual = bufQual.toString();
	  IDfId id = sess.getIdByQualification(strQual);
	  
	  // 그룹문서함 ACL 정보 가져오기
	  getGroupCabinetAclInfo(sess, id);
	  
	  return id;
	}
	
	/**
	 * 그룹문서함 ACL 정보 가져오기
	 * 폴더유형이 "xiper_smartdisk" 이면 그룹문서함
	 * @param sess
	 * @param id
	 * @throws DfException
	 */
	private static void getGroupCabinetAclInfo(IDfSession sess, IDfId id) throws DfException {
		IDfSysObject folderObject = (IDfSysObject)sess.getObject(id);
		if (folderObject.getTypeName().equals("xiper_smartdisk")) {
			groupCabinetAclDomain = folderObject.getACLDomain();
			groupCabinetAclName = folderObject.getACLName();
		}		
	}

}
