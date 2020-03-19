/*
 * 작성된 날짜: 2005-02-02
 *  
 */
package miraenet.app.dbadmin;

import miraenet.app.dbadmin.dao.DbAdmin;
import miraenet.app.dbadmin.dao.OCI8;

/**
 * @author goindole
 *  
 */
public class DbAdminManager {
    public static DbAdmin getDbAdmin() {
        
          return new OCI8();
          //return new MYSQL(oDb);
    }
}