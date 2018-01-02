package Protocol;

import cyy_IM_protocol.Cyy_factory;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class protocolFactory_test {
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
            +"-1\r\n"
            +"\r\n"
            +"hello world\r\n"
            +"\r\n"
            +"\r\n";
    String generate = "";
    String decoded = "";
    @Test
    public void test_packet_gen(){

        Cyy_factory f = Cyy_factory.get_cyyfactory();
        f.create_messageObj("hello word", "GnuPG 2.0", 1234, 12345566, 12);
        String[] dests = {"luoyq@mail.sustc.edu.cn"};

        IM_capsulation cap = f.capsulate("CYYClient 1.0",IM_Handler.ACTION_contactInitializing, "IMAP",
                "mac@mail.sustc.edu.cn","luoyq@mail.sustc.edu.cn");
        try {
            generate = new String(f.packet_generate(cap),"UTF-8");
            System.out.print(generate);
            assert(generate.equals(testpacket));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void test_packet_parse(){

    }
}
