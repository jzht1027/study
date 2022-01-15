package com.study.mail;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName SimpleMailSender
 * @Description
 * @Author
 * @Date 2021/12/30 17:34
 * @Version 1.0
 **/
public class SimpleMailSender {
    /***
     * 以文本格式/HTML格式发送邮件
     * @param mailInfo
     *     待发送的邮件信息
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo) {
        //判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证；则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        //根据邮件会话属性和密码验证器构造一个邮件发送的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            //    根据session 创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            //创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            //设置邮件消息的发送者
            mailMessage.setFrom(from);
            //创建邮件的接受者地址；并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            //Message.RecipientType.TO表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            //设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            //设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            //设置邮件消息的主要内容

            //MiniMultipart 类是一个容器 包含MimeBodyPart类型的对象
//            Multipart mailPart =new MimeMultipart();
//            //创建一个包含HTNL内容的MimeBodyPart
//            BodyPart html=new MimeBodyPart();
//            //设置HTML内容
//            html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
//            mailPart.addBodyPart(html);

            MimeMultipart mailPart = new MimeMultipart();

            BodyPart textBodyPart = new MimeBodyPart();
            System.out.println(mailInfo.getContent().indexOf("<html>"));
            if (mailInfo.getContent().indexOf("<html>") > -1) {
                textBodyPart.setContent(mailInfo.getContent(), "text/html;charset=GBK");
            } else {
                textBodyPart.setContent(mailInfo.getContent(),"text/plain;charset=GBK");
            }
            mailPart.addBodyPart(textBodyPart);

            for (int i = 0; i < 3; i++) {
                //s设置信件的附件（用本地上的文件作为附件）
                MimeBodyPart html = new MimeBodyPart();
                FileDataSource fds = new FileDataSource("C:\\Users\\jiazhitao\\Desktop\\MyEclipse配置.docx");
                DataHandler dh = new DataHandler(fds);
                html.setFileName(new String("MyEclipse配置.docx".getBytes("GBK"),"ISO_8859_1"));
                html.setDataHandler(dh);
                mailPart.addBodyPart(html);
            }

            //将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mailPart);
            mailMessage.saveChanges();

            //发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
