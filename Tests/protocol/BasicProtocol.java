package protocol;

import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class BasicProtocol {

    @Test
    public void testA() {
		// TODO Auto-generated method stub
		String testpacket = "CYY 1.0\r\n"
							+"CYYClient 1.0\r\n"
							+"GnuPG 2.0\r\n"
							+"1234\r\n"
							+"12345566\r\n"
							+ IM_Handler.ACTION_groupSending+"\r\n"
							+"IMAP\r\n"
							+"321\r\n"
							+"mac@mail.sustc.edu.cn\r\n"
							+"2\r\n"
							+"2333\r\n"
							+"luoyq@mail.sustc.edu.cn;zhangys3@mail.sustc.edu.cn;\r\n"
							+"1\r\n"
							+"\r\n"
							+"hello world\r\n"
							+"\r\n"
							+"\r\n";
		IM_Handler handler =new IM_Handler();
		try {
			IM_capsulation cap = handler.parse_packet(testpacket.getBytes());
			byte [] turn = handler.generate_packet(cap);
			System.out.println("START*****START");
			System.out.print(new String(turn,"UTF-8"));
			System.out.println("END^^^^^^^^END");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
