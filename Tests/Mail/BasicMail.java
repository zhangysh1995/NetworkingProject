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
<<<<<<< HEAD

    @Test
    public void testRecv() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler mailHandler = new MailHandler();

        mailHandler.receiveMessage();
    }
=======
>>>>>>> 55a7ad08d2ae2ea55c4b22bae9d91539e3a7284c
}
