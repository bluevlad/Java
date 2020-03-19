/*
 * �ۼ��� ��¥: 2005-01-05
 *

 */
package miraenet.app.msg.dao;

import java.util.ArrayList;
import java.util.List;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.app.msg.beans.ContactBean;


/**
 * ���� �ּҷ� Dao
 * 
 * �ۼ��� : �ڱ��� �ۼ��� ��¥ : 2005-01-25
 */
public class ContactDB {

	private static ContactDB instance;


	private ContactDB() {
	}

	public static synchronized ContactDB getInstance() {
		if (instance == null)
			instance = new ContactDB();
		return instance;
	}
	
	/**
	 * �ּҷ� ī��Ʈ
	 * 
	 * @param oDb
	 * @param usn
	 * @param c_group
	 * @return
	 * @throws Exception
	 */
	public int getGrpCount(MdbDriver oDb, String usn, String c_group)
			throws Exception {
		ArrayList inputarray = new ArrayList();
		String sql = "SELECT COUNT(*) FROM MSG_CONTACT " 
				+ " WHERE USN = ? "
				+ " 	and decode(?, null, 1, decode(c_group,?,1,0)) = 1";
		inputarray.add(usn);
		inputarray.add(c_group);
		inputarray.add(c_group);
		return oDb.selectOneInt(sql, inputarray);
	}

	/**
	 * �ּҷ� ����Ʈ ��������
	 * 
	 * @param oDb
	 * @param usn
	 * @return
	 * @throws Exception
	 */
	public List getMstMnglist(MdbDriver oDb, String usn) throws Exception {
		String sql = null;
		ArrayList inputarray = new ArrayList();

		sql = " SELECT   c.usn, c.c_usn, c.c_group, u.nm"
				+ "     FROM msg_contact c, mst_user u"
				+ "    WHERE c.usn = ? AND c.c_usn = u.usn" + " ORDER BY u.nm";
		inputarray.add(usn);

		return oDb.selector(ContactBean.class, sql, inputarray);
	}

	/**
	 * �ּҷ� �׷캰 ����Ʈ ��������
	 * 
	 * @param oDb
	 * @param usn
	 * @param c_group
	 * @return
	 * @throws Exception
	 */
	public List getMsgGrpList(MdbDriver oDb, String usn, String c_group)
			throws Exception {

		ArrayList inputarray = new ArrayList();
		String sql = " SELECT   c.usn, c.c_usn, c.c_group, u.nm"
				+ "     FROM msg_contact c, mst_user u"
				+ "    WHERE c.usn = ? AND c.c_usn = u.usn AND c.c_group = ?"
				+ " ORDER BY u.nm";
		inputarray.add(usn);
		inputarray.add(c_group);

		return oDb.selector(ContactBean.class, sql, inputarray);
	}

	/**
	 * �ּҷ� �׷�� ��� ��������
	 * 
	 * @param oDb
	 * @param usn
	 * @return
	 * @throws Exception
	 */
	public List getGrplist(MdbDriver oDb, String usn) throws Exception {
		ArrayList inputarray = new ArrayList();

		String sql = " SELECT c_group"
				+ "     FROM msg_contact c"
				+ "    WHERE c.usn = ? "
				+ " GROUP BY c_group";

		inputarray.add(usn);

		return oDb.selector(ContactBean.class, sql, inputarray);
	}

	/**
	 * �ּҷ� ���
	 * 
	 * @param oDb
	 * @param mng
	 * @throws Exception
	 */
	public void insert(MdbDriver oDb, ContactBean mng) throws Exception {
		ArrayList at = new ArrayList();
		String sql = " INSERT INTO MSG_CONTACT ("
				+ "    USN, C_USN, C_GROUP) VALUES ( ?, ?, ?)";
		at.add(mng.getUsn());
		at.add(mng.getC_usn());
		at.add(mng.getC_group());
		oDb.executeUpdate(sql, at);
	}

	/**
	 * �ּҷ� ����
	 * 
	 * @param oDb
	 * @param usn
	 * @param c_usn
	 * @throws Exception
	 */
	public void addrDelete(MdbDriver oDb, String usn, String c_usn)
			throws Exception {
		ArrayList at = new ArrayList();

		String sql = " DELETE  FROM MSG_CONTACT"
				+ " where usn = ? and c_usn = ?";
		at.add(usn);
		at.add(c_usn);
		oDb.executeUpdate(sql, at);
	}

	/**
	 * ����� �˻�
	 * 
	 * @param oDb
	 * @param usn
	 * @param nm
	 * @return
	 * @throws Exception
	 */
	public List getMsgSearchList(MdbDriver oDb, String usn, String nm)
			throws MdbException {
		ArrayList inputarray = new ArrayList();

		String sql = " SELECT c.c_usn as usn, u.nm  FROM msg_contact c, mst_user u "
				+ " WHERE c.usn = ?  and c.c_usn = u.usn and INSTR(u.nm, ?)>0";
		inputarray.add(usn);
		inputarray.add(nm);

		return oDb.selector(ContactBean.class, sql, inputarray);
	}

}