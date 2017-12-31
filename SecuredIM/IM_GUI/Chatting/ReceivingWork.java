package IM_GUI.Chatting;

import Utility.MailHandler;

public class ReceivingWork implements Runnable{
    private MailHandler mailHandler;

    public ReceivingWork(MailHandler mailHandler) {
        this.mailHandler = mailHandler;
    }

    @Override
    public void run() {
        try {
            do{
                mailHandler.receiveMessage();
                Thread.sleep(10000L);
            } while(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
