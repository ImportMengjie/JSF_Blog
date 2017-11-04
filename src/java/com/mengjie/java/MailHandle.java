/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mengjie.java;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mengjie
 */
public class MailHandle extends Thread{
    private String mail;
    private int code;
    public static String myEmailAccount = "importmengjie@163.com";
    public static String myEmailPW="limengjie961029";
    public static String myEmailSMTPHost = "smtp.163.com";
    public static Properties props;
    public static Session session;
    private String name;
    static{
        props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

    }
    
    public MailHandle(String mail,int code,String name) {
        this.code=code;
        this.mail=mail;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //发邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount, mail,code,name);
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPW);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        } catch (Exception ex) {
            Logger.getLogger(MailHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("code="+code);
    }
    
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,int code,String name) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "mengjie", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, name, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("验证码", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("你好"+name+",你的验证码是<br/>"+code+"<br/>快去注册!", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
    
    
}
