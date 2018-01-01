package IM_GUI.Chatting;

import Utility.MailHandler;
import cyy_IM_protocol.CYY_PACKET_generator;

public class ReceivingWork implements Runnable{
    private MailHandler mailHandler;
    private CYY_PACKET_generator cyy_packet_generator;

    public ReceivingWork(MailHandler mailHandler, CYY_PACKET_generator cyy_packet_generator) {
        this.cyy_packet_generator = cyy_packet_generator;
        this.mailHandler = mailHandler;
    }

    @Override
    public void run() {
        try {
            do{
                mailHandler.receiveMessage();
                this.wait(1000L);
            } while(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
