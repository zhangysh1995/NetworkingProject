package Mail;

import Utility.MailHandler;
import org.junit.Test;

public class BasicMail {

    @Test
    public void testSend() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler.send("zhangys3@mail.sustc.edu.cn", "test");

    }

    @Test
    public void testSendRequest() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();
    }

    @Test
    public void testRecv() {
        MailHandler.receive();
    }
}
