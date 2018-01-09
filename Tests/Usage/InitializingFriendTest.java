package Usage;

import IM_GUI.ListView.FriendListViewController;
import Utility.MailHandler;
import Utility.Pushexecutor;
import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitializingFriendTest {
    // auto-generated stub

    @Test
    public void sendFriendRequest() {
        MailHandler.setMail("zhangyushao@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
        MailHandler.setMyIMAPserver("imap.mxhichina.com");

        FriendListViewController friendLVC = new FriendListViewController();
        friendLVC.addFriend("test@zhangyushao.site");


    }

    @Test
    public void receiveFriendRequest() {
        MailHandler.setMail("test@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
        MailHandler.setMyIMAPserver("imap.mxhichina.com");

        Vector<String> result = MailHandler.receive();

        for(String content: result){
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Thread pushThread = new Thread(new Pushexecutor(content));
            executor.submit(pushThread);
        }
    }
}
