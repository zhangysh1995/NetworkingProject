package Utility;

import IM_GUI.Abstract.Controller;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeMultiplexer implements Runnable{

    ConcurrentHashMap<Integer, Controller> Group_session_list;
    ConcurrentHashMap<String, Controller> Individual_session_list;
    ExecutorService executor;
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
    public DeMultiplexer(ConcurrentHashMap<Integer, Controller> Group_lst, ConcurrentHashMap<String, Controller> Ind_lst){
        this.Group_session_list = Group_lst;
        this.Individual_session_list = Ind_lst;
        this.executor = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run(){
        while(true){
            Vector<String> result = MailHandler.receive();
            for(String r : result) {
                System.out.println(r);
            }
            for(String content: result){
//                Pushexecutor worker = new Pushexecutor();
                Thread pushThread = new Thread(new Pushexecutor(this.Group_session_list,this.Individual_session_list, content));
                this.executor.submit(pushThread);
            }
//            this.executor.shutdown();
            try {
                this.wait(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
