package cyy_IM_protocol;

import DataManager.Group;
import DataManager.User;

/**
 * This is the class abstraction of Subject of IM operation, i.e. the object perspective of packets
 * 
 * @author Ce
 *
 */
public class IM_capsulation {
private Message_cyy messageCyy;
private String User_agent;
private String Action_type;
private String check_sum;
private String Mail_protocol;
private User Source;
private int Group_size;
private User individual_Destination;
private Group Group_Destination;

public IM_capsulation(Message_cyy messageCyy, String user_agent, String action_type, String check_sum, String mail_protocol) {
	super();
	this.messageCyy = messageCyy;
	this.User_agent = user_agent;
	this.Action_type = action_type;
	this.check_sum = check_sum;
	this.Mail_protocol = mail_protocol;
}
public String getEncryptionType() {
	return this.messageCyy.getEncryption_type();
}
public int getSequenceNumber() {
	return this.messageCyy.getID().getSequence_number();
}
public long getTimeStamp() {
	return this.messageCyy.getID().getTime_stamp();
}
public int getLength() {
	return this.messageCyy.getLength();
}
public String getSourceEmail() {
	return this.Source.getUser_email();
}
public Message_cyy getMessageCyy() {
	return messageCyy;
}
public void setMessageCyy(Message_cyy messageCyy) {
	this.messageCyy = messageCyy;
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
public String getCheck_sum() {
	return check_sum;
}
public void setCheck_sum(String check_sum) {
	this.check_sum = check_sum;
}
public String getMail_protocol() {
	return Mail_protocol;
}
public void setMail_protocol(String mail_protocol) {
	Mail_protocol = mail_protocol;
}

	public User getSource() {
		return Source;
	}

	public void setSource(User source) {
		Source = source;
	}

	public int getGroup_size() {
		return Group_size;
	}

	public void setGroup_size(int group_size) {
		Group_size = group_size;
	}

	public User getIndividual_Destination() {
		return individual_Destination;
	}

	public void setIndividual_Destination(User individual_Destination) {
		this.individual_Destination = individual_Destination;
	}

	public Group getGroup_Destination() {
		return Group_Destination;
	}

	public void setGroup_Destination(Group group_Destination) {
		Group_Destination = group_Destination;
	}
}
