package Mail;

import org.junit.Test;
import Utility.MailHandler;

public class BasicMail {
    @Test
    public void testSend() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

        mailHandler.sendMessage("zhangys3@mail.sustc.edu.cn", "test");

    }

    @Test
    public void testSendRequest() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

        mailHandler.sendRequestMail("zhangys3@mail.sustc.edu.cn");
    }

    @Test
    public void testRecv() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

        mailHandler.receiveMessage();
    }
}
