package Utility;

import DataManager.Group;
import DataManager.User;
import IM_GUI.Abstract.Controller;
import IM_GUI.Chatting.P2PchatController;
import IM_GUI.Home.HomeController;
import cyy_IM_protocol.Cyy_factory;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;

import java.util.concurrent.ConcurrentHashMap;

public class Pushexecutor implements Runnable {
    ConcurrentHashMap<Integer, Controller> Group_session_list;
    ConcurrentHashMap<String, Controller> Individual_session_list;
    String raw_content;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public Pushexecutor(ConcurrentHashMap<Integer, Controller> Group_session_list,
                        ConcurrentHashMap<String, Controller> Individual_session_list,
                        String protocolpacket){
        this.Group_session_list = Group_session_list;
        this.Individual_session_list = Individual_session_list;
        this.raw_content = protocolpacket;
    }

    public Pushexecutor(String packet){ this.raw_content = packet; };

    @Override
    public void run() {
//        System.out.println(this);
        Cyy_factory factory = Cyy_factory.get_cyyfactory();
//        System.out.println("Raw :" + raw_content);
        IM_capsulation cap = factory.packet_parse(raw_content);
        System.out.println("\nSource mail: " + cap.getSourceEmail());
        System.out.println("Group size: " + cap.getGroup_size());
        Controller controller = null;
        if(cap.getGroup_size()>0){
            /**
             * this is a group destination
             */
            Group group = cap.getGroup_Destination();
            int group_id = group.getGroup_ID();
            String action = cap.getAction_type();
            if(action.equals(IM_Handler.ACTION_groupInitializing)){
                    HomeController homeController = (HomeController)this.Group_session_list.get(-1);
            }else if(action.equals(IM_Handler.ACTION_groupAddConfirm)){

            }else if(action.equals(IM_Handler.ACTION_groupSending)){

            }else {
                /**
                 * wrong action type usage
                 */
                System.out.println("Wrong action type usage");
            }
        }else if(cap.getGroup_size()==0){
            User src = cap.getSource();
            String action = cap.getAction_type();
            if(action.equals(IM_Handler.ACTION_contactInitializing)){
                System.out.println("====== Get new friend request from: " + cap.getSourceEmail());
                HomeController homeController = (HomeController) this.Individual_session_list.get("-1");
                homeController.pushNewRequest(cap.getSourceEmail());
                System.out.println(cap.getSourceEmail());

            }else if(action.equals(IM_Handler.ACTION_contactAddConfirm)){
                /**
                 * confirmation need a separate function in home controller if u want to actually use a confirmation
                 * to before init a contact locally
                 */
                System.out.println("====== Get new friend request confirmation from: " + cap.getSourceEmail());
                HomeController homeController = (HomeController) this.Individual_session_list.get("-1");
                homeController.pushNewConfirm(cap.getSourceEmail());

            }else if(action.equals(IM_Handler.ACTION_individualSending)){
                System.out.println("====== Prepare to push new message... ======");
                System.out.println("====== Message from: " + cap.getSourceEmail());
                System.out.println("====== message " + cap.getMessageCyy().getContent());
                P2PchatController p2PchatController = (P2PchatController) this.Individual_session_list.get(cap.getSourceEmail());
                p2PchatController.pushNewMsg(cap.getMessageCyy().getContent());

            }else {
                /**
                 * wrong action type usage
                 */
                System.out.println("Wrong action type usage");
            }
        }else {

        }

    }
}
