package util;

import cn.hutool.extra.mail.MailAccount;
import entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

public class Mailutil {

    public static final Logger log = LoggerFactory.getLogger(Mailutil.class);
    private static final String EMAIL_SUBJECT = "手机商城激活";
    private static final String EMAIL_CONTENT = "欢迎注册会员，点击链接完成注册激活，祝您在商城购物愉快！激活链接：<a href='http://localhost:8080/#/reg_ok''>点击激活</a>";

    public static void sendMail(String accptor, Mail ms) {
        try {
            MailAccount account = new MailAccount();
            account.setHost(ms.getHost());//qq邮箱的协议,固定的不用改
            account.setPort(ms.getPort());//qq邮箱的端口号,不用改
            account.setAuth(true);//验证规则
            account.setFrom(ms.getEmail());//发件人的邮箱地址
            account.setUser(ms.getNickName());//发件人的邮箱昵称
            account.setPass(ms.getPwd()); //邮箱设置获取到的授权码,可以在qq邮箱设置里面获取
            account.setSslEnable(true);
            List<String> acceptor = new ArrayList<>();
            acceptor.add(accptor);//收件人的邮箱地址.要封装到一个list集合里面
            //五个参数,第一个是发件人信息,第二个是收件人,第三个是邮件的主题,第四个是邮件的内容.
            // 第五个是邮件内容是否以html格式展示
            cn.hutool.extra.mail.MailUtil.send(account, acceptor, EMAIL_SUBJECT, EMAIL_CONTENT, true);
        } catch (Exception e) {
            log.error("send email failure", e);
            throw new RuntimeException("发送邮件失败");
        }
    }
}
