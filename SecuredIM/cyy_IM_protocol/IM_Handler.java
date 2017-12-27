package cyy_IM_protocol;

import DataManager.Group;
import DataManager.User;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Sample protocol packet
 * 
 * [0]CYY 1.0\r\n # fill in IMAP as Title
 * [1]User-agent: CYYClient 1.0\r\n
 * [2]Encryption: GnuPG 2.0\r\n
 * [3]Sequence-number: 1234
 * [4]Time-stamp: 12345566\r\n
 * [5]Action-type: Group send\r\n
 * [6]Mail-protocol: IMAP\r\n
 * [7]Length: 1\r\n
 * [8]Source: mac@mail.sustc.edu.cn\r\n
 * [9]Group-Size:2\r\n
 * [10]check-sum: 2132\r\n
 * [11]Destination: luoyq@mail.sustc.edu.cn;zhangys3@mail.sustc.edu.cn;\r\n
 * [12]Options:1\r\n
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
public static String CRLF = "\r\n";
private String first_line = "CYY 1.0";
private String User_agent = "CYYClient 1.0";
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
	packet_Generate = packet_Generate + IM_capsulation.getMessage().getGroup_size()+CRLF;
	//check-sum
	packet_Generate = packet_Generate + IM_capsulation.getCheck_sum()+CRLF;
	//Destination
	String Destination = "";
	if(IM_capsulation.getMessage().getGroup_size()>0) {
		Group g = IM_capsulation.getMessage().getGroup_Destination();
		Set<Entry<String, User>> set = g.getSet();
		for(Entry<String, User> e : set) {
			Destination = Destination + e.getKey()+";";
		}
	}else {
		Destination = Destination+ IM_capsulation.getMessage().getIndividual_Destination();
	}
	packet_Generate = packet_Generate + Destination+CRLF;
	//optional field for group info
	if(IM_capsulation.getMessage().getGroup_size()>0) {
		packet_Generate = packet_Generate + IM_capsulation.getMessage().getGroup_Destination().getGroup_ID()+CRLF;
	}
	packet_Generate = packet_Generate + CRLF;
	packet_Generate = packet_Generate + IM_capsulation.getMessage().getContent()+CRLF+CRLF;
	
	return packet_Generate.getBytes();
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
	Message m;
	User source;
	IM_capsulation IM_cap;
	if(headers_check(lines)) {
	ID = new Message_ID(Integer.parseInt(lines[3]), Integer.parseInt(lines[4]));
	source = new User(lines[8]);
	m = new Message(ID, lines[2], Integer.parseInt(lines[7]), source, Integer.parseInt(lines[9]));
	if(Integer.parseInt(lines[9])>0) {
		Group g = new Group(Integer.parseInt(lines[12]));
		String[] members =lines[11].split(";");
		for(int i=0;i<members.length;i++) {
			g.add_member(new User(members[i]));
		}
		m.setGroup_Destination(g);
		m.setContent(lines[14]);
	}else {
		m.setIndividual_Destination(new User(lines[11]));
		m.setContent(lines[13]);
	}
	IM_cap =new IM_capsulation(m,lines[1],lines[5], Integer.parseInt(lines[10]),lines[6]);
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
public boolean headers_check(String [] lines) {
return lines[0].equals(first_line) &&lines[1].equals(User_agent);
	
	
}


}
