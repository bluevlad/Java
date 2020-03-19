package modules.wlc.common.mail;
/**
// 1.Program Name     : WlcSendMail.java
// 2.Writer's Name    :
// 3.Date of written  :
// 4.Input Arguemnts  :
// 5.Output Arguemnts :
// 6.Related Tables   :
// 7.Program Desc     : 회원 메일 발송
// 8.Modify Log       :
//   YYYY/MM/DD     Modifier       Description
//   ------------   ----------   -------------------------------------------
//
 */


import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import maf.MafProperties;
import miraenet.AppConfig;



public class WlcSendMail {
    // 전역 인스턴스 생성

    private static final String MailServer = MafProperties.SMTP_SERVER;  // 메일서버
    private static  String from=null;     // 보내는이
    private static boolean myDebug=true;  // 디버그

    public static void main(String[] args) {
        if(myDebug)
            System.out.println("MSG : Start StudypiaMail .... ");
        try {
//          StudypiaMail studypia = new StudypiaMail();
        } catch (Exception e) {
            if(myDebug) {
                System.out.println("MSG : ERR [에러가 발생하였습니다. ]");
            }
            e.printStackTrace(System.err);
        }
    }

    public  WlcSendMail(String title) {
        from = title;
    }

    public String line_Print(String content) {
        StringBuffer result = new StringBuffer();

        if (content == null){
            return "";
        }
        for (int i = 0; i < content.length(); i++)
        {
            result.append(content.charAt(i));
            if (content.charAt(i) == '\n')
                result.append("<br> ");
        }
        return result.toString();
    }

    /**********************************************************************************
     *
     *   MailSendThread.class 에서 호출함
     *
     *********************************************************************************
    /**----------------------------------------
    // 메일을 SMTP Client Class 로 넘긴다.
    -----------------------------------------*/
    public int SendMail(String to, String cc, String bcc, String subject, String text) {
        String charset = MafProperties.DEFAULT_CHARACTER_ENCODING;
        //PrintWriter mail_log2= null;
        try{
            //mail_log2 = new PrintWriter(new FileWriter(AppConfig.DEFAULT_MAIL_TEMPLATE_FILE_PATH+"StudypiaMail.txt", true), true);
            //mail_log2.println(to+" : "+Util.toEN(subject)+"["+(subject)+"]");
            //mail_log2.println(to+" : "+(subject)+"["+(subject)+"]");
            //mail_log2.println("cc="+cc+" : bcc="+bcc+"");

        }catch(Exception e){
        }

        try {

            // make javaMail Session
            Properties props = System.getProperties();
            props.put("mail.smtp.host", MailServer);
            props.put("mail.smtp.auth", "true");
            props.put("mail.transport.protocol", "smtp");
            //Session session = Session.getDefaultInstance(props,null);
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            // make Message
            MimeMessage message = new MimeMessage(session);
            // 보낼사람 주소를 설정
            Address fromAddress= new InternetAddress(from);
            message.setFrom(fromAddress);

            // 받을사람 주소를 분석해서 설정
            Address[] toAddress = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO,toAddress);

            Address[] ccAddresses = null;
            Address[] bccAddresses = null;

            if(cc != null && !"".equals(cc)) {
                ccAddresses = InternetAddress.parse(cc);
                message.setRecipients(Message.RecipientType.CC,ccAddresses);
            }
            if(bcc != null && !"".equals(bcc)) {
                bccAddresses = InternetAddress.parse(bcc);
                message.setRecipients(Message.RecipientType.BCC,bccAddresses);
            }

            // 제목과 메일내용을 만든다
            message.setSubject(subject, charset);
            message.setText(text);
            message.setContent(text,"text/html; charset="+charset);
            // 메일 헤더 부분 설정
            message.setHeader("Content-Type","text/html; charset="+charset);
            message.setHeader("X-Mailer", "SUN Web Learning Center");
            message.setHeader("Content-Transfer-Encoding","8bit");

            // 보내는 날짜 설정
            message.setSentDate(new Date());

            // 메일 전송
            //Transport.send(message);
            Transport transport = session.getTransport();
            transport.connect(MailServer, MafProperties.SMTP_SERVER_USER, MafProperties.SMTP_SERVER_PASSWD);
            transport.sendMessage(message,toAddress);
            if(cc != null && !"".equals(cc))
                transport.sendMessage(message,ccAddresses);
            if(bcc != null && !"".equals(bcc))
                transport.sendMessage(message,bccAddresses);
            transport.close();
            //mail_log2.close();
            return 0;

        } catch(AddressException e) {
            System.out.println("return -1: " + e.toString());
            //mail_log2.println(e);
            //mail_log2.close();
            return -1;
        } catch (SendFailedException e) {
            System.out.println("return -2: " + e.toString());
            //mail_log2.println(e);
            //mail_log2.close();
            return -2;
        } catch (MessagingException e) {
            System.out.println("return -3: " + e.toString());
            //mail_log2.println(e);
            //mail_log2.close();
            return -3;
        } catch (Exception e) {
            System.out.println("return -4: " + e.toString());
            //mail_log2.println(e);
            //mail_log2.close();
            return -4;
        }

    }

    /**
     * 데이타를 데이터베이스에 저장하기 위한 값을 데이터베이스의 환경설정에 맞도록
     * 고쳐주기 위한 함수
     */
    public static String setCode(String query) {
        if(query == null) return null;
        try {
            return new String(query.getBytes("KSC5601"), "8859_1");
        } catch (Exception e){return query;}
    }

    /**
     * 데이타를 데이터베이스에서 가져온 데이타를 처리
     */
    public static String getCode(String query) {
        if(query == null) return null;
        try {
            return new String(query.getBytes("8859_1"), "KSC5601");
        } catch (Exception e){return query;}
    }
}
