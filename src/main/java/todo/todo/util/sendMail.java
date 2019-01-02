package todo.todo.util;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class sendMail {
    private String host = "smtp.qq.com";
    private String protocol = "smtp";
    private String auth = "true";
    private String port = "25";
    private String user = "1531265179@qq.com";
    private String password = "kgofufutenhchaic";
    public sendMail(){}
    public void send(String content,String to) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", host);
        prop.setProperty("mail.transport.protocol", protocol);
        prop.setProperty("mail.smtp.auth", auth);
        prop.put("mail.smtp.port",port);
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(host, user, password);
        //4、创建邮件
        Message message = createSimpleMail(session,content,to);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    public MimeMessage createSimpleMail(Session session,String content,String to)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(user));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //邮件的标题
        message.setSubject("todo");
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}
