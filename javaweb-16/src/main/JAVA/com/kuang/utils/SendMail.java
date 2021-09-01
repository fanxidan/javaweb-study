package com.kuang.utils;

import com.kuang.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail extends Thread{
    private String from = "564636186@qq.com";
    private String username = "564636186@qq.com";
    private final String password = "ehmglwmbunpobchc";
    private String host = "smtp.qq.com";

    private User user;

    public SendMail(User user){
        this.user=user;
    }

    @Override
    public void run(){
        try{
            Properties properties = new Properties();
            properties.setProperty("mail.host",host);  //设置QQ邮件服务器
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
                    return new PasswordAuthentication("564636186@qq.com",password);
                }
            });
            //开启session的debug模式，可以看到发送邮件的状态
            session.setDebug(true);
            //通过session得到transport对象
            Transport ts = session.getTransport();

            //使用邮箱的用户名和授权码连接服务器
            ts.connect(host,username,password);
            //创建邮件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));//指明发件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));//指明收件人
            message.setSubject("用户注册邮箱");//主题

            String info="恭喜您注册成功，您的用户名："+user.getUsername()+"您的密码"+user.getPassword();
            message.setContent(info,"text/html;charset=UTF-8");//邮件的文本内容
            message.saveChanges();

            //发送邮件
            ts.sendMessage(message,message.getAllRecipients());
            //关闭连接
            ts.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
