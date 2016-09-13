package cn.zjc.util;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {

    /**
     * 发mail的一个通用方法
     * 
     * @param fromEmail
     *            mail的发送方地址
     * @param fromEmailPWD
     *            mail发送方登陆mail的密码
     * @param userName
     *            mail发送方登陆mail的用户名
     * @param reveiveMailAddress
     *            接收方的mail地址，如果有多个接收方地址间用“,”分隔
     * @param host
     *            发送mail的host
     * @param mailTitle
     *            邮件标题
     * @param mailHtmlContent
     *            邮件内容，支持html格式
     * @param file
     *            附件内容
     * @throws Exception
     *             异常
     */
    public static void sendEmail(String fromEmail, String fromEmailPWD, String userName, String reveiveMailAddress,
            String host, String mailTitle, String mailHtmlContent, File file) throws Exception {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPassword(fromEmailPWD);
        sender.setUsername(userName);
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        props.put("mail.smtp.localhost", "smtp.exmail.qq.com");
        props.put("mail.smtp.auth", "true");
        sender.setJavaMailProperties(props);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "gb2312");
        helper.setFrom(fromEmail);
        if (file != null) {
            helper.addAttachment(file.getName(), file);
        }
        helper.setTo(reveiveMailAddress.split(","));
        helper.setText(mailHtmlContent, true);
        helper.setSentDate(new Date());
        helper.setSubject(mailTitle);
        sender.send(message);
    }

    public static void sendEmailOnFor(String fromEmail, String fromEmailPWD, String userName,
            String reveiveMailAddress, String host, String mailTitle, String mailHtmlContent, File file)
            throws Exception {
        String[] address = reveiveMailAddress.split(",");
        for (int i = 0; i < address.length; i++) {
            try {
                sendEmail(fromEmail, fromEmailPWD, userName, address[i], host, mailTitle, mailHtmlContent
                        + "\n\r<br/>为避免某人离职导致全部发送失败改成单人发送；所有收件人：" + reveiveMailAddress, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendEmailByMultifile(String fromEmail, String fromEmailPWD, String userName,
            String reveiveMailAddress, String host, String mailTitle, String mailHtmlContent, List<File> fileList)
            throws Exception {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPassword(fromEmailPWD);
        sender.setUsername(userName);
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        props.put("mail.smtp.localhost", "smtp.exmail.qq.com");
        props.put("mail.smtp.auth", "true");
        sender.setJavaMailProperties(props);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "gb2312");
        helper.setFrom(fromEmail);
        if (fileList != null && fileList.size() > 0) {
            for (File file : fileList) {
                helper.addAttachment(file.getName(), file);
            }
        }
        helper.setTo(reveiveMailAddress.split(","));
        helper.setText(mailHtmlContent, true);
        helper.setSentDate(new Date());
        helper.setSubject(mailTitle);
        sender.send(message);
    }

}
