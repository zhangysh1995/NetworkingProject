package Utility;

/**
 * this module handles mail services
 */

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

public class MailHandler {
    private static String myMail;
    private String mySmtpServer;

    private String request = "newFriendRequest";
//    private smtpServerList;

    private Properties props;

    public MailHandler() {
        this.props = System.getProperties();;
    }

    public static void setMail(String mail) {
        myMail = mail;
    }

    private void setMySmtpServert() {

    }

    public static String getMail() throws NullPointerException{
        String mail = "";

        try
        {
            if (myMail != null)
                mail = myMail;
            else {
                mail = null;
                throw new NullPointerException("Your email was not set!!!");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return mail;
    }

    public Boolean sendRequestMail(String toEmail) {
        return sendMessage(toEmail, request);
    }

    public Boolean sendMessage(String toEmail, String text ) {
        text = GPG.Encrypt(text);
        return send(toEmail, text);
    }

    private Boolean send(String toEmail, String text) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        props.setProperty("mail.smtp.host","smtp.mxhichina.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

        final String username="zhangyushao@zhangyushao.site";
        final String password="Kid1412kelly";
        Session session =  Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // set message
        Message msg = new MimeMessage(session);
        try
        {
        msg.setFrom(new InternetAddress(myMail));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail, false));
        msg.setSubject("CYY/1.0\r\n");

        msg.setText(text);
        msg.setSentDate(new Date());

        Transport.send(msg);
        } catch(MessagingException e) {
            System.err.println("Error when constructing the message");
        }

        return true;
    }

    public Boolean receiveRequestMail() {
        return true;
    }

    public Boolean receiveMessage() {
        return true;
    }

    private String receive() {
        return "";
    }
}
