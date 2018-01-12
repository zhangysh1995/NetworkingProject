package Usage;

import Utility.MailHandler;
import cyy_IM_protocol.CYY_PACKET_generator;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import cyy_IM_protocol.Message_cyy;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class SendRecieveMsg {
    // auto-generated stub

    @Test
    public void sendMessage() {
        MailHandler.setMail("test@zhangyushao.site");
        MailHandler.setPass("Kid1412kelly");
        MailHandler.setMySmtpServer("smtp.mxhichina.com");
        MailHandler.setMyIMAPserver("imap.mxhichina.com");

        CYY_PACKET_generator cyy_packet_generator = new CYY_PACKET_generator();
        Message_cyy message = cyy_packet_generator.create_messageObj("test message",
                "GnuPG 2.0", 1, System.currentTimeMillis(), 1);

        IM_capsulation cap = cyy_packet_generator.capsulate("CYYClient 1.0",
                IM_Handler.ACTION_individualSending, "IMAP",
                MailHandler.getMail(), "zhangyushao@zhangyushao.site");

        try {
            String content = new String(cyy_packet_generator.packet_generate(cap), "UTF-8");
            System.out.println(content);
                MailHandler.send("zhangyushao@zhangyushao.site", content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
