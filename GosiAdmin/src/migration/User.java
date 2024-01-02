package migration;

import java.sql.SQLException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;


public class User {

	public static void main(String[] args) throws SQLException, Base64DecodingException {
//		//String url = "jdbc:oracle:thin:@221.143.40.249:1521:willbesdev";
//		String url = "jdbc:oracle:thin:@1.224.163.236:1521:willbesgosi";
//		String className = "oracle.jdbc.driver.OracleDriver";
//		//String user = "willbes";
//		//String pwd = "willbesgosi";
//		String user = "WILLBES_PUBLIC";
//		String pwd = "WILLBESGOSI";
//		
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(className);
//			conn = DriverManager.getConnection(url, user, pwd);
//			List<HashMap<String, String>> passwordList = new ArrayList<HashMap<String, String>>();
//			
//			String sql = "";
//			int cnt = 0;
			
			String data = "AAAA";
			String test1 = Base64.encode(data.getBytes());
			String test2 = new String(Base64.decode(data.getBytes()));
			
			System.out.println("test1:"+test1);
			System.out.println("test2:"+test2);

			System.out.println("==============================================");

			data = "aaaa";
			test1 = Base64.encode(data.getBytes());
			test2 = new String(Base64.decode(data.getBytes()));

			System.out.println("test1:"+test1);
			System.out.println("test2:"+test2);
			
			// 강사
//			sql = "select user_id, user_pwd from tb_ma_member_prf";
//			conn.setAutoCommit(false);
//			stmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				String user_pwd = rs.getString(2);
//				if(user_pwd == null) user_pwd = "";
//				user_pwd = Base64.encode(user_pwd.getBytes());
//				System.out.println(++cnt + "\t" + rs.getString(1) + "===>" + user_pwd);
//				
//				HashMap<String, String> temp = new HashMap<String, String>();
//				temp.put("ID", rs.getString(1));
//				temp.put("PWD", user_pwd);
//				passwordList.add(temp);
//			}
//			rs.close();
//			stmt.close();
//			
//			sql = "update tb_ma_member set user_pwd = ? WHERE USER_ID = ? ";
//			stmt = conn.prepareStatement(sql);
//			for(int i = 0; i < passwordList.size(); i++) {
//				HashMap<String, String> temp = passwordList.get(i);
//				String decodePwd = new String(Base64.decode((String) temp.get("PWD")));
//				System.out.println(decodePwd + ".................. ONLINE" + "===>" + (i+1));
//				stmt.setString(1, temp.get("PWD"));
//				stmt.setString(2, temp.get("ID"));
//				stmt.execute();
//			}
//			conn.commit();
			// 사용자
//			passwordList = new ArrayList<HashMap<String, String>>();
//			sql = "select user_id, user_pwd from tb_ma_member_offline_original where to_char(memo) = '20140210'";
//			stmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql);
//			cnt = 0;
//			while(rs.next()) {
//				String user_pwd = rs.getString(2);
//				if(user_pwd == null) user_pwd = "";
//				user_pwd = Base64.encode(user_pwd.getBytes());
//				System.out.println(++cnt + "\t" + rs.getString(1) + "===>" + user_pwd);
//				
//				HashMap<String, String> temp = new HashMap<String, String>();
//				temp.put("ID", rs.getString(1));
//				temp.put("PWD", user_pwd);
//				passwordList.add(temp);
//			}
//			rs.close();
//			stmt.close();
//			
//			sql = "update tb_ma_member_offline set user_pwd = ? WHERE USER_ID = ? ";
//			stmt = conn.prepareStatement(sql);
//			for(int i = 0; i < passwordList.size(); i++) {
//				HashMap<String, String> temp = passwordList.get(i);
//				String decodePwd = new String(Base64.decode((String) temp.get("PWD")));
//				System.out.println(decodePwd + ".................. tb_ma_member_offline" + "===>" + (i+1));
//				stmt.setString(1, temp.get("PWD"));
//				stmt.setString(2, temp.get("ID"));
//				stmt.execute();
//			}
//
//			conn.commit();
//			// 동영상
//			passwordList = new ArrayList<HashMap<String, String>>();
//			sql = "select user_id, user_pwd from tb_ma_member_online_original where to_char(memo) = '20140210'";
//			stmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql);
//			cnt = 0;
//			while(rs.next()) {
//				String user_pwd = rs.getString(2);
//				if(user_pwd == null) user_pwd = "";
//				user_pwd = Base64.encode(user_pwd.getBytes());
//				System.out.println(++cnt + "\t" + rs.getString(1) + "===>" + user_pwd);
//				
//				HashMap<String, String> temp = new HashMap<String, String>();
//				temp.put("ID", rs.getString(1));
//				temp.put("PWD", user_pwd);
//				passwordList.add(temp);
//			}
//			rs.close();
//			stmt.close();
//			
//			sql = "update tb_ma_member_online set user_pwd = ? WHERE USER_ID = ? ";
//			stmt = conn.prepareStatement(sql);
//			for(int i = 0; i < passwordList.size(); i++) {
//				HashMap<String, String> temp = passwordList.get(i);
//				String decodePwd = new String(Base64.decode((String) temp.get("PWD")));
//				System.out.println(decodePwd + ".................. tb_ma_member_online" + "===>" + (i+1));
//				stmt.setString(1, temp.get("PWD"));
//				stmt.setString(2, temp.get("ID"));
//				stmt.execute();
//			}			
//		} catch(SQLException e) {
//			e.printStackTrace();
//			conn.rollback();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			conn.rollback();
//			e.printStackTrace();
//		} catch (Base64DecodingException e) {
//			// TODO Auto-generated catch block
//			conn.rollback();
//			e.printStackTrace();
//		} finally {
//			conn.commit();
//			rs.close();
//			stmt.close();
//			conn.close();
//		}
	}

}
