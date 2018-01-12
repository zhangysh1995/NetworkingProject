package Usage;

import IM_GUI.ListView.FriendListViewController;
import Utility.MailHandler;
import org.junit.Test;

public class InitializingFriendTest {
    // auto-generated stub

    @Test
    public void sendFriendRequest() {
        MailHandler.setMail("test@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
        MailHandler.setMyIMAPserver("imap.mxhichina.com");

        FriendListViewController friendLVC = new FriendListViewController();
        friendLVC.addFriend("zhangyushao@zhangyushao.site");
    }
}
