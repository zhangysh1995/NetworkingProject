package cyy_IM_protocol;
/**
 * This is the class abstraction of Subject of IM operation, i.e. the object perspective of packets
 * 
 * @author Ce
 *
 */
public class IM_capsulation {
private Message message;
private String User_agent;
private String Action_type;
private int check_sum;
private String Mail_protocol;
public IM_capsulation(Message message, String user_agent, String action_type, int check_sum, String mail_protocol) {
	super();
	this.message = message;
	this.User_agent = user_agent;
	this.Action_type = action_type;
	this.check_sum = check_sum;
	this.Mail_protocol = mail_protocol;
}
public String getEncryptionType() {
	return this.message.getEncryption_type();
}
public int getSequenceNumber() {
	return this.message.getID().getSequence_number();
}
public int getTimeStamp() {
	return this.message.getID().getTime_stamp();
}
public int getLength() {
	return this.message.getLength();
}
public String getSourceEmail() {
	return this.message.getSource().getUser_email();
}
public Message getMessage() {
	return message;
}
public void setMessage(Message message) {
	this.message = message;
}
public String getUser_agent() {
	return User_agent;
}
public void setUser_agent(String user_agent) {
	User_agent = user_agent;
}
public String getAction_type() {
	return Action_type;
}
public void setAction_type(String action_type) {
	Action_type = action_type;
}
public int getCheck_sum() {
	return check_sum;
}
public void setCheck_sum(int check_sum) {
	this.check_sum = check_sum;
}
public String getMail_protocol() {
	return Mail_protocol;
}
public void setMail_protocol(String mail_protocol) {
	Mail_protocol = mail_protocol;
}

}
