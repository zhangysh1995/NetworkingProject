package Utility;

/**
 * this module handles mail services
 */

import com.sun.mail.imap.IMAPMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

public class MailHandler {
    private static String myMail;
    private static String mySmtpServer = "stmp.mxhichina.com";
    private static String myRecvServer = "imap.mxhichina.com";
    private static String request = "newFriendRequest";

    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

//    private smtpServerList;

    private Properties props;

    public MailHandler() {
        this.props = System.getProperties();
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
        return send(toEmail, request);
    }

    public Boolean sendMessage(String toEmail, String text ) {
        text = GPG.Encrypt(text);
        return send(toEmail, text);
    }

    private Boolean send(String toEmail, String text) {
        props.setProperty("mail.smtp.host","smtp.mxhichina.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

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
        System.out.println("\tSent");
        return true;
    }

    public Boolean receiveRequestMail() {
        return true;
    }

    public Boolean receiveMessage() {
        receive();
        return true;
    }

    private Session getSessionRecv() {
        Properties props = System.getProperties();
        props.setProperty("mail.imap.host", myRecvServer);
        props.setProperty("mail.imap.auth", "true");
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.socketFactory.port", "993");
        return Session.getDefaultInstance(props, null);
    }

    private String receive() {

        URLName urln = new URLName("imap", myRecvServer, 993,
                null, username, password);

        try {
            Store store = getSessionRecv().getStore(urln);
            store.connect();

//            Folder[] folder = store.getDefaultFolder().list();
//            for(Folder f: folder)
//                System.out.println(f.getName());
            Folder folder = store.getFolder("Inbox");
            folder.open(Folder.READ_WRITE);

            // search for "unread" messages
            Flags unread = new Flags(Flags.Flag.SEEN);
            FlagTerm unreadFlagTerm = new FlagTerm(unread, false);
            Message[] messages = folder.search(unreadFlagTerm);

            if(messages.length > 0) printMessage(messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void printMessage(Message[] messages) {
        System.out.println(messages.length);
        try{
            for(int i = 0; i < messages.length; i++) {
                if(!getSubject(messages[i]).toLowerCase().equals("cyy 0.1"))
                    continue;
                String content = getMailContent(messages[i]);
                System.out.println("=====================>>开始显示邮件内容<<=====================");
                System.out.println("发送人: " + getFrom(messages[i]));
                System.out.println("内容: \n" + content);
                System.out.println("\n发送时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format((messages[i]).getSentDate()));
                System.out.println("是否有附件: " + (isContainAttach((Part)messages[i]) ? "有附件" : "无附件"));
                        System.out.println("=====================>>结束显示邮件内容<<=====================");
                        ((IMAPMessage) messages[i]).invalidateHeaders();
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private String getMailContent(Part part) throws Exception {
        StringBuilder bodytext = new StringBuilder();//存放邮件内容
        //判断邮件类型,不同类型操作不同
        if (part.isMimeType("text/plain")) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("text/html")) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                bodytext.append(getMailContent(multipart.getBodyPart(i)));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        } else System.err.println("No content");
        return bodytext.toString();
    }

    private String getSubject(Message message) throws Exception {
        String subject = "";
        if(message.getSubject() != null){
            subject = MimeUtility.decodeText(message.getSubject());// 将邮件主题解码
        }
        return subject;
    }

    private String getFrom(Message message) throws Exception {
        InternetAddress[] address = (InternetAddress[]) message.getFrom();
        String from = address[0].getAddress();
        if (from == null){
            from = "";
        }
        return from;
    }

    private boolean isContainAttach(Part part) throws Exception {
        boolean attachflag = false;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                    attachflag = true;
                else if (mpart.isMimeType("multipart/*")) {
                    attachflag = isContainAttach((Part) mpart);
                } else {
                    String contype = mpart.getContentType();
                    if (contype.toLowerCase().contains("application"))
                        attachflag = true;
                    if (contype.toLowerCase().contains("name"))
                        attachflag = true;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachflag = isContainAttach((Part) part.getContent());
        }
        return attachflag;
    }
}
