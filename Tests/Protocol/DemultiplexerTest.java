package Protocol;

import Utility.Pushexecutor;
import cyy_IM_protocol.Cyy_factory;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemultiplexerTest {
    // this could not be parsed correctly
    String packet =
            "CYY 0.1\r\n" +
            "CYYClient 1.0\r\n" +
            "GnuPG 2.0\r\n" +
            "-1\r\n" +
            "1514872643086\r\n" +
            "CONTACT-Init\r\n" +
            "SMTP\r\n" +
            "0\r\n" +
            "zhangyushao@zhangyushao.site\r\n" +
            "0\r\n" +
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855\r\n" +
            "test@zhangyushao.site\r\n";

    // this could be parsed correctly
    String testpacket = "CYY 0.1\r\n"
            +"CYYClient 1.0\r\n"
            +"GnuPG 2.0\r\n"
            +"1234\r\n"
            +"12345566\r\n"
            + IM_Handler.ACTION_contactInitializing+"\r\n"
            +"IMAP\r\n"
            +"10\r\n"
            +"mac@mail.sustc.edu.cn\r\n"
            +"0\r\n"
            +"b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9\r\n"
            +"luoyq@mail.sustc.edu.cn;\r\n"
            +"\r\n"
            +"hello world\r\n"
            +"\r\n"
            +"\r\n";

    @Test
    public void testPushExecutor() {
//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            Pushexecutor p = new Pushexecutor(testpacket);
        Cyy_factory f = Cyy_factory.get_cyyfactory();
        IM_capsulation cap = f.packet_parse(testpacket);
        System.out.println("\nSource mail: " + cap.getSourceEmail());

//            p.run();
//            Thread pushThread = new Thread(new Pushexecutor(packet));
//            executor.submit(pushThread);
    }
}
