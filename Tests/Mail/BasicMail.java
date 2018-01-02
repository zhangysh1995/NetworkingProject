package Mail;

import org.junit.Test;
import Utility.MailHandler;

public class BasicMail {
    @Test
    public void testSend() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

       // mailHandler.sendMessage("zhangys3@mail.sustc.edu.cn", "test");

    }

    @Test
    public void testSendRequest() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

       // mailHandler.sendRequestMail("zhangys3@mail.sustc.edu.cn");
    }
    @Test
    public void testRecv() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

       // mailHandler.receiveMessage();
    }

    @Test
    public void testConnect() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
//        MailHandler.setMyIMAPserver("imap.mxhichina.com");

//        MailHandler.testSMTP("zhangyushao@zhangyushao.site", "Kid1412kelly","smtp.mxhichina.com");
//        MailHandler.send("zhangys3@mail.sustc.edu.cn", "test");

        assert(MailHandler.receive() != null);
    }
}
