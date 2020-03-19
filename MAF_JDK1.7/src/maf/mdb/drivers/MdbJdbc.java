package maf.mdb.drivers;





public class MdbJdbc extends MdbDriver {
    public MdbJdbc(String dbpoolname) {
        this.dbPoolName = dbpoolname;
        this.dbms = MdbDriver.DMBS_JDBC;
    }
    
//    public PreparedStatement mPrepareStatement(Connection conn, String sql, MdbParameters p){
//        return null;
//    }

}
