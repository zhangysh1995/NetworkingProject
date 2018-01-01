package cyy_IM_protocol;

import DataManager.Group;
import DataManager.User;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Sample protocol packet
 * 
 * [0]CYY 1.0\r\n # fill in IMAP as Title <Hard coding>
 * [1]User-agent: CYYClient 1.0\r\n <Input>
 * [2]Encryption: GnuPG 2.0\r\n <Input>
 * [3]Sequence-number: 1234	<Input>
 * [4]Time-stamp: 12345566\r\n <Input>
 * [5]Action-type: Group send\r\n <Input>
 * [6]Mail-protocol: IMAP\r\n <Input>
 * [7]Length: 1\r\n <Calculate>
 * [8]Source: mac@mail.sustc.edu.cn\r\n <Calculate>
 * [9]Group-Size:2\r\n <Calculate>
 * [10]check-sum: 2132\r\n <Calculate>
 * [11]Destination: luoyq@mail.sustc.edu.cn;zhangys3@mail.sustc.edu.cn;\r\n <Input>
 * [12]Options:1\r\n <Input>
 * [13/12]\r\n
 * [14/13]^%&*^F^UTF*
 * [15/14]\r\n
 * [16/15]\r\n
 * @author Ce
 *
 */
public class IM_Handler {
public static String ACTION_contactInitializing = "CONTACT-Init";
public static String ACTION_individualSending = "INDIVIDUAL-Send";
public static String ACTION_groupInitializing = "GROUP-Init";
public static String ACTION_groupSending = "GROUP-Send";
public static String ACTION_groupQuiting = "GROUP-Quit";
public static String ENCRYPTION_type_base = "GnuPG 2.0";
public static String CRLF = "\r\n";
private String first_line = "CYY 0.1";
public static String User_agent = "CYYClient 1.0";
private String packet_Generate = "";
/**
 * This is the packet-generating method 
 * turn the object into string of packet, then turn into byte streams
 * @param IM_capsulation
 * @return the byte stream of packet
 */
public byte [] generate_packet(IM_capsulation IM_capsulation) {
	packet_Generate = packet_Generate + first_line+CRLF;
	//user-agent
	packet_Generate = packet_Generate + User_agent+CRLF;
	//Encryption-type
	packet_Generate = packet_Generate + IM_capsulation.getEncryptionType()+CRLF;
	//Sequence-number
	packet_Generate = packet_Generate + IM_capsulation.getSequenceNumber()+CRLF;
	//Time-stamp
	packet_Generate = packet_Generate + IM_capsulation.getTimeStamp()+CRLF;
	//Action-type
	packet_Generate = packet_Generate + IM_capsulation.getAction_type()+CRLF;
	//Mail-protocol
	packet_Generate = packet_Generate + IM_capsulation.getMail_protocol()+CRLF;
	//Length
	packet_Generate = packet_Generate + IM_capsulation.getLength()+CRLF;
	//Source
	packet_Generate = packet_Generate + IM_capsulation.getSourceEmail()+CRLF;
	//group-size
	packet_Generate = packet_Generate + IM_capsulation.getGroup_size()+CRLF;
	//check-sum
	packet_Generate = packet_Generate + IM_capsulation.getCheck_sum()+CRLF;
	//Destination
	String Destination = "";
	if(IM_capsulation.getGroup_size()>0) {
		Group g = IM_capsulation.getGroup_Destination();
		Set<Entry<String, User>> set = g.getSet();
		for(Entry<String, User> e : set) {
			Destination = Destination + e.getKey()+";";
		}
	}else {
		Destination = Destination+ IM_capsulation.getIndividual_Destination().getUser_email();
	}
	packet_Generate = packet_Generate + Destination+CRLF;
	//optional field for group info
	if(IM_capsulation.getGroup_size()>0) {
		packet_Generate = packet_Generate + IM_capsulation.getGroup_Destination().getGroup_ID()+CRLF;
	}
	packet_Generate = packet_Generate + CRLF;
	packet_Generate = packet_Generate + IM_capsulation.getMessageCyy().getContent()+CRLF+CRLF+CRLF;

	try {
		return packet_Generate.getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	return null;
}
/**
 * This method is used for parsing the coming packets
 * @param raw_packet the byte array raw packet
 * @return the object of IM protocol
 * @throws UnsupportedEncodingException
 */
public IM_capsulation parse_packet(byte[] raw_packet) throws UnsupportedEncodingException {
	String encoded_packet = new String(raw_packet, "UTF-8");
	String [] lines = encoded_packet.split(CRLF);
	Message_ID ID;
	Message_cyy m;
	User source;
	IM_capsulation IM_cap;
	if(headers_check(lines)) {
	ID = new Message_ID(Integer.parseInt(lines[3]), Integer.parseInt(lines[4]));
	source = new User(lines[8]);
	m = new Message_cyy(ID, lines[2], Integer.parseInt(lines[7]));
	IM_cap =new IM_capsulation(m,lines[1],lines[5], lines[10],lines[6]);
	if(Integer.parseInt(lines[9])>0) {
		Group g = new Group(Integer.parseInt(lines[12]));
		String[] members =lines[11].split(";");
		for(int i=0;i<members.length;i++) {
			g.add_member(new User(members[i]));
		}
		IM_cap.setGroup_Destination(g);
		String content = "";
		for(int i=14;i<lines.length;i++){
			content = content + lines[i];
		}
		m.setContent(content);
	}else {
		IM_cap.setIndividual_Destination(new User(lines[11]));
		String content = "";
		for(int i=13;i<lines.length;i++){
			content = content + lines[i];
		}
		m.setContent(content);
	}
	IM_cap.setSource(source);
	IM_cap.setGroup_size(Integer.parseInt(lines[9]));

	return IM_cap;
	}else {
		return null;
	}
	
}
/**
 * This method is used for legality-checking of the first two lines
 * @param lines
 * @return true if is legal
 */
private boolean headers_check(String [] lines) {
return lines[0].equals(first_line) &&lines[1].equals(User_agent);
	
	
}


}
