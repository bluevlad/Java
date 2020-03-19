/*
 * 작성된 날짜: 2005-02-07
 *
 */
package miraenet.app.msg.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * @author goindole, 2005-02-07
 *
 */
public class SmsDB {
////String hemosid, String from_No, String to_no, String msg)
    public void sendSMS(MdbDriver oDb, 
            	String from_nm, String from_addr,
            	String to_nm, String to_addr,
            	String msg,
            	String userid) 
    throws MdbException{
	// $snd_dttm=date("YmdHis");				예) 20041221103043
	// $cmp_msg_id=$snd_dttm.".".substr(microtime(), 2, 5);	예) 20041221103937.70827
	// $msg=’메시지 내용’;
	// $member[user_id] --> 해모수 계정			예) sky6326
	// $member[usernm] = "arreo_사용자신분"		예) course_이름
	// $from_no  --> 보내는 사람 전화번호			예) 01122312322	(-, 공백등이 없어야 함)
	// $to_no    --> 받는사람 전화번호			예) 01122312322	(-, 공백등이 없어야 함)
	//    	Date 
		//Date dttm = new Date();
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyyMMddHHmmss.SSSSS");
		String cmp_msg_id = fomatter.format(new Date()); 
        String sql = "INSERT INTO ARREO_SMS ( " 
           + " CMP_MSG_ID, CMP_USR_ID, ODR_FG, SMS_GB, USED_CD, MSG_GB, WRT_DTTM, SND_DTTM, "
           + " SND_PHN_ID, RCV_PHN_ID, CALLBACK, SND_MSG, EXPIRE_VAL, SMS_ST,RSRVD_ID, RSRVD_WD "
           + " ) VALUES( "
           + " ?, '00000', '2', '1', '00', 'A', ?, ?, "
           + " ?, ?, ? , ? , 0, '0', ? ,'test' "
           + ") ";
//           '$cmp_msg_id', '00000', '2', '1', '00', 'A', '$snd_dttm', '$snd_dttm',
//           '$from_no', '$to_no', '$from_no', '$msg', 0, '0', '$member[user_id]' ,'$member[usernm]'
           
		List at = new ArrayList();
		at.add(cmp_msg_id);
		at.add(cmp_msg_id.substring(0,14));
		at.add(cmp_msg_id.substring(0,14));

		at.add(from_addr.replaceAll("-",""));
		at.add(to_addr.replaceAll("-",""));
		at.add(from_addr.replaceAll("-",""));
		at.add(msg);
		at.add("arreo_" + userid);
//		oDb.setDebug(true);
		//oDb.setAutoCommit(false);
		oDb.executeUpdate(sql, at);
		//oDb.rollback();
    }
    
}
