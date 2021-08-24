package com.kuang;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailDemo01 {
    //一封简单邮件
    public static void main(String[] args) throws GeneralSecurityException, MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.qq.com");  //设置QQ邮件服务器
        properties.setProperty("mail.transport.protocol","smtp");   //邮件发送协议
        properties.setProperty("mail.smtp.auth","true");    //需要验证用户名和密码

        //设置SSL加密，
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.ssl.sockerFactory",mailSSLSocketFactory);

        //创建整个应用程序所需要的环境信息的session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮箱用户名、授权码
                return new PasswordAuthentication("564636186@qq.com","ehmglwmbunpobchc");
            }
        });
        //开启session的debug模式，可以看到发送邮件的状态
        session.setDebug(true);
        //通过session得到transport对象
        Transport ts = session.getTransport();

        //使用邮箱的用户名和授权码连接服务器
        ts.connect("smtp.qq.com","564636186@qq.com","ehmglwmbunpobchc");
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("564636186@qq.com"));//指明发件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("fanxidan@163.com"));//指明收件人
        message.setSubject("只包含文本的简单邮件");//主题
        message.setContent("<h1 sytle='color:red'>hello</h1>","text/html;character=UTF-8");//邮件的文本内容
        //发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        //关闭连接
        ts.close();
    }
}
