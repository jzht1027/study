package com.study.mail;

/**
 * @ClassName SendMail
 * @Description
 * @Author
 * @Date 2021/12/30 17:33
 * @Version 1.0
 **/
public class SendMail {
    public static void main(String[] args) {
        SendMail.send_163();
    }

    //163邮箱
    public static void send_163() {
        //这个类主要是设置邮件
        MailSenderInfo mailInfo=new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("15176125652@163.com");//实际发送者
        mailInfo.setPassword("LKTEZOTNQNCLFIVG");//您的邮箱密码 LKTEZOTNQNCLFIVG
        mailInfo.setFromAddress("15176125652@163.com");//设置发送人邮箱地址
        mailInfo.setToAddress("15176125652@163.com");
        mailInfo.setSubject("test");//设置邮箱标题
        String content = "<html><p style=\"text-align: left;\">尊敬的客户：</br>\n" +
                "&emsp;&emsp;细节详情请见附件，烦请：</br>\n" +
                "&emsp;&emsp;1、本结算细节；</br>\n" +
                "&emsp;&emsp;&emsp;a. 计入账户；</br>\n" +
                "<table border=\"5\" style=\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;\"><tr style=\"background-color: #428BCA; color:#ffffff\"><td colspan=\"1\">合约编号</td><td colspan=\"1\">事件类型</td><td colspan=\"1\">标的代码</td><td colspan=\"1\">结算金额(正数表明中金支付，负数表明客户支付)</td><td colspan=\"1\">结算币种</td></tr><tr><td><span></span></td><td><span></span></td><td><span>000300.SH</span></td><td><span>0.0000</span></td><td><span></span></td></tr><tr><td><span></span></td><td><span></span></td><td><span>511880.SH</span></td><td><span>0.0000</span></td><td><span></span></td></tr></table><p style=\"text-indent: 2em;\"></p >\n" +
                "&emsp;&emsp;谢谢！</br></p ></html>";

//        content = "尊敬的客户：\n" +
//                "  附件，烦请查收！\n" +
//                "\n" +
//                "Dear client，\n" +
//                "  file please find attached..\n";
        mailInfo.setContent(content);//设置邮箱内容
        //这个类主要是用来发送邮件
        SimpleMailSender sms=new SimpleMailSender();
        sms.sendHtmlMail(mailInfo);

    }
}
