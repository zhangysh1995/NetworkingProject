package IM_GUI.ListView;

import DataManager.UserManager;
import IM_GUI.Abstract.Controller;
import Utility.MailHandler;
import cyy_IM_protocol.Cyy_factory;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by KellyZhang on 2017/12/22.
 */
public class FriendListViewController extends Controller{
    // add new friend

    public ObservableList<String> getFriendList() {
        return FXCollections.observableArrayList(UserManager.getFriendList());
    }

    public ObservableList<String> getBlockedList() {
        return FXCollections.observableList(UserManager.getBlockedList());
    }

    public ObservableList<String> getSentList() {
        return FXCollections.observableArrayList(UserManager.getSentList());
    }

    public ObservableList<String> getRequestList() {return FXCollections.observableList(UserManager.getRequestList()); }

    public Boolean addFriend(String email) {
        Cyy_factory cyy_packet_generator = Cyy_factory.get_cyyfactory();

        cyy_packet_generator.create_messageObj("",
                "GnuPG 2.0", -1, System.currentTimeMillis(), -1);

        IM_capsulation cap = cyy_packet_generator.capsulate("CYYClient 1.0",
                IM_Handler.ACTION_contactInitializing, "SMTP",
                MailHandler.getMail(), email);

        String msg = new String(cyy_packet_generator.packet_generate(cap));

        if(MailHandler.send(email,msg)) {

        } else System.err.println("Error: send friend request");

        return UserManager.addSend(email);
    }

    public Boolean blockUser(int index) {
        return UserManager.blockUser(index);
    }

    public Boolean deleteFriend(int index) {
        return UserManager.deleteUser(index);
    }

    public Boolean acceptRequest(int index) {
        return UserManager.acceptRequest(index);
    }

    public Boolean deleteRequest(int index) {
        return UserManager.deleteRequest(index);
    }

    public Boolean addRequest(String email) {
        return UserManager.addRequest(email);
    }
}
