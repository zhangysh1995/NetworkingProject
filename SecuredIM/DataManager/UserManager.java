package DataManager;

import java.util.Arrays;
import java.util.Vector;

public class UserManager {
    private static  Vector<String> friendList
            = new Vector<>(Arrays.asList("zhangyushao@zhangyushao.site", "mac@mail.sustc.edu.cn", "Sue"));
    private static Vector<String> requestList
            = new Vector<>(Arrays.asList("test@163.com", "test@126.com"));
    private static Vector<String> sentList
            = new Vector<>(2);

    public UserManager() {
    }

//    public Vector<String> addFriend(String email) {
//        sentList.add(email);
//    }

    public static Vector<String> getFriendList() {
        return friendList;
    }

    public static void setFriendList(Vector<String> friendList) {
        UserManager.friendList = friendList;
    }

    public static Vector<String> getRequestList() {
        return requestList;
    }

    public static void setRequestList(Vector<String> requestList) {
        UserManager.requestList = requestList;
    }

    public static Vector<String> getSentList() {
        return sentList;
    }

    public static void setSentList(Vector<String> sentList) {
        UserManager.sentList = sentList;
    }

    public static Boolean addSend(String email) {
        sentList.add(email);
        return true;
    }

    public static Boolean blockUser() {
        return true;
    }

    public static Boolean deleteUser() {
        return true;
    }
}
