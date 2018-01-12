package DataManager;

import Utility.MailHandler;
import cyy_IM_protocol.Cyy_factory;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;

import java.util.Arrays;
import java.util.Vector;

public class UserManager {
    private static  Vector<String> friendList
            = new Vector<>(Arrays.asList("mac@mail.sustc.edu.cn", "Sue"));
    private static Vector<String> requestList
            = new Vector<>(Arrays.asList("Bob@example.com", "Sam@example.com"));
    private static Vector<String> sentList
            = new Vector<>(Arrays.asList( "Alice@gamil.com", "Jane@gmail.com"));

    private static Vector<String> blockedList
            = new Vector<>(Arrays.asList("Tom", "David"));

    public static Vector<String> getFriendList() {
        return friendList;
    }

    public static Vector<String> getRequestList() {
        return requestList;
    }

    public static Vector<String> getSentList() {
        return sentList;
    }

    public static Boolean sentUpdate(String email) {
        if(getSentList().indexOf(email) != -1) {
            addFriend(email);
            return getSentList().remove(email);
        }
        else return false;
    }
    public static Boolean addSend(String email) {
        return getSentList().add(email);
    }

    private static Boolean addFriend(String email) {
        return getFriendList().add(email);
    }

    // blocklist operations
    public static Vector<String> getBlockedList() {
        return blockedList;
    }

    public static Boolean blockUser(int index) {
        String user =  getFriendList().remove(index);

        return user == null? false : updateBlockedList(user);
    }

    private static Boolean updateBlockedList(String user) {
        return getBlockedList().add(user);
    }

    public static Boolean deleteUser(int index) {
        return getFriendList().remove(index) == null;
    }

    public static Boolean addRequest(String email) {return getRequestList().add(email);}

    public static Boolean acceptRequest(int index) {
        String user = getRequestList().remove(index);
        Cyy_factory cyy_packet_generator = Cyy_factory.get_cyyfactory();

        cyy_packet_generator.create_messageObj("",
                "GnuPG 2.0", -1, System.currentTimeMillis(), -1);

        IM_capsulation cap = cyy_packet_generator.capsulate("CYYClient 1.0",
                IM_Handler.ACTION_contactAddConfirm, "SMTP",
                MailHandler.getMail(), user);
        String msg = new String(cyy_packet_generator.packet_generate(cap));

        if(user != null && MailHandler.send(user, msg)) {
            return addFriend(user);
        } else return false;
    }

    public static Boolean deleteRequest(int index) {
        return getRequestList().remove(index) == null;
    }
}
