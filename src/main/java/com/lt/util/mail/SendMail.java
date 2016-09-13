package com.lt.util.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * @author guanlichang
 * 
 */
public class SendMail {

    // 要发送Mail地址
    private String mailTo = null;

    // Mail发送的起始地址
    private String mailFrom = null;

    // SMTP主机地址
    private String smtpHost = null;

    // 是否采用调试方式
    private boolean debug = false;

    private String messageBasePath = null;

    // Mail主题
    private String subject = "";

    // Mail内容
    private String msgContent;

    private Vector attachedFileList;

    private String mailAccount = null;

    private String mailPass = null;

    private String messageContentMimeType = "text/html; charset=gb2312";

    // bcc 该字段用于指定邮件的暗送地址
    private String mailbccTo = null;

    // cc 该字段用于指定邮件的抄送地址
    private String mailccTo = null;

    MailAuthenticator auth = null;

    public SendMail() {
        auth = new MailAuthenticator();
    }

    private void fillMail(Session session, MimeMessage msg) throws IOException, MessagingException {

        String fileName = null;
        Multipart mPart = new MimeMultipart();

        if (mailFrom != null) {
            msg.setFrom(new InternetAddress(mailFrom));
        } else {
            return;
        }

        if (mailTo != null) {
            InternetAddress[] address = InternetAddress.parse(mailTo);
            msg.setRecipients(Message.RecipientType.TO, address);
        } else {
            return;
        }

        if (mailccTo != null) {
            InternetAddress[] ccaddress = InternetAddress.parse(mailccTo);
            msg.setRecipients(Message.RecipientType.CC, ccaddress);
        }

        if (mailbccTo != null) {
            InternetAddress[] bccaddress = InternetAddress.parse(mailbccTo);
            msg.setRecipients(Message.RecipientType.BCC, bccaddress);
        }

        msg.setSubject(subject);
        InternetAddress[] replyAddress = { new InternetAddress(mailFrom) };
        msg.setReplyTo(replyAddress);

        // create and fill the first message part
        MimeBodyPart mBodyContent = new MimeBodyPart();
        if (msgContent != null)
            mBodyContent.setContent(msgContent, messageContentMimeType);
        else
            mBodyContent.setContent("", messageContentMimeType);

        mPart.addBodyPart(mBodyContent);

        // attach the file to the message
        if (attachedFileList != null) {
            for (Enumeration fileList = attachedFileList.elements(); fileList.hasMoreElements();) {
                fileName = (String) fileList.nextElement();
                MimeBodyPart mBodyPart = new MimeBodyPart();
                // attach the file to the message
                FileDataSource fds = new FileDataSource(messageBasePath + fileName);
                mBodyPart.setDataHandler(new DataHandler(fds));
                mBodyPart.setFileName(fileName);
                mPart.addBodyPart(mBodyPart);
            }
        }

        msg.setContent(mPart);
        msg.setSentDate(new Date());
    }

    public String sendMail() throws IOException, MessagingException {

        if (auth.getPassward() == null || auth.getUser() == null) {
            return "error: send mail fail ,because user name or passward is null !";
        }
        if (smtpHost == null) {
            return "error: smtp is null ";
        }

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, auth);
        session.setDebug(debug);
        MimeMessage msg = new MimeMessage(session);
        Transport trans = null;

        try {
            fillMail(session, msg);
            // send the message
            trans = session.getTransport("smtp");
            try {
                trans.connect(smtpHost, auth.getUser(), auth.getPassward());
            } catch (AuthenticationFailedException e) {
                return "error: connect server fail ";
            } catch (MessagingException e) {
                return "error: connect server fail ";
            }
            trans.send(msg);
            trans.close();
        } catch (MessagingException mex) {
            mex.printStackTrace(System.out);
            Exception ex = null;

            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace(System.out);
            }
            return "error: connect server fail ";
        } finally {
            try {
                if (trans != null && trans.isConnected())
                    trans.close();
            } catch (Exception e) {
            }
        }

        return "success";
    }

    public void setAttachedFileList(java.util.Vector filelist) {
        attachedFileList = filelist;
    }

    public void setDebug(boolean debugFlag) {
        debug = debugFlag;
    }

    public void setMailAccount(String strAccount) {
        mailAccount = strAccount;
    }

    public void setMailbccTo(String bccto) {
        mailbccTo = bccto;
    }

    public void setMailccTo(String ccto) {
        mailccTo = ccto;
    }

    public void setMailFrom(String from) {
        mailFrom = from;

        if (smtpHost == null) {
            if (from.endsWith("@51book.com")) {
                smtpHost = "smtp.exmail.qq.com";
            } else if (from.endsWith("@boco.com.cn")) {
                smtpHost = "boco.com.cn";
            } else if (from.endsWith("@126.com")) {
                smtpHost = "smtp.126.com";
            } else if (from.endsWith("@163.com")) {
                smtpHost = "smtp.163.com";
            } else if (from.endsWith("@sina.com")) {
                smtpHost = "smtp.sina.com.cn";
            } else if (from.endsWith("@sohu.com")) {
                smtpHost = "smtp.sohu.com";
            } else if (from.endsWith("@yeah.net")) {
                smtpHost = "smtp.yeah.net";
            } else if (from.endsWith("@163.com")) {
                smtpHost = "smtp.163.com";
            } else if (from.endsWith("@tom.com")) {
                smtpHost = "smtp.tom.com";
            } else if (from.endsWith("@263.net")) {
                smtpHost = "smtp.263.net";
            } else if (from.endsWith("@21cn.com")) {
                smtpHost = "smtp.21cn.com";
            } else if (from.endsWith("@yeahoo.com.cn")) {
                smtpHost = "smtp.mail.yahoo.com.cn";
            } else if (from.endsWith("@eyou.com")) {
                smtpHost = "mx.eyou.com";
            }
        }
    }

    public void setMailPass(String strMailPass) {
        mailPass = strMailPass;
    }

    public void setMailTo(String to) {
        mailTo = to;
    }

    public void setMessageBasePath(String basePath) {
        messageBasePath = basePath;
    }

    public void setMessageContentMimeType(String mimeType) {
        messageContentMimeType = mimeType;
    }

    public void setMsgContent(String content) {
        msgContent = content;
    }

    public void setSMTPHost(String host) {
        smtpHost = host;
    }

    public void setSubject(String sub) {
        subject = sub;
    }

    public void setUser(String o) {
        auth.setUser(o);
    }

    public void setPassward(String o) {
        auth.setPassward(o);
    }

    public static void main(String[] argv) throws Exception {
        SendMail sm = new SendMail();
        System.out.println("1111111111");
        sm.setMailFrom("system@51book.com");
        sm.setUser("system@51book.com");
        sm.setPassward("sysOF51b0ok");
        sm.setMailTo("guanlichang@51book.com");
        // sm.setMsgContent(MailContent.convertToHTMLText());
        sm.setSubject("alarm forward test");
        sm.sendMail();
        System.out.println("222222");

    }

}
