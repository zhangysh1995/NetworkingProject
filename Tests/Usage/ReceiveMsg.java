package Usage;

import Utility.MailHandler;
import org.junit.Test;

import java.util.Vector;

public class ReceiveMsg {
    // auto-generated stub
    @Test
    public void receiveFriendRequest() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
        MailHandler.setMyIMAPserver("imap.mxhichina.com");

        Vector<String> result = MailHandler.receive();

        for(String content: result){
            System.out.println(content);
        }
    }
}
