package IM_GUI.Chatting;

import Utility.MailHandler;
import cyy_IM_protocol.CYY_PACKET_generator;

public class ReceivingWork implements Runnable{
    private CYY_PACKET_generator cyy_packet_generator;

    public ReceivingWork(CYY_PACKET_generator cyy_packet_generator) {
        this.cyy_packet_generator = cyy_packet_generator;
    }

    @Override
    public void run() {
        try {
            do{
                MailHandler.receive();
                this.wait(1000L);
            } while(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
