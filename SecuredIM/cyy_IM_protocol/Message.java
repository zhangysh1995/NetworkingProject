package cyy_IM_protocol;
/**
 * This is the Message Class
 * @author Ce
 *
 */
public class Message {
private Message_ID ID;
private String Encryption_type;
private int length;
private User Source;
private int Group_size;
private User individual_Destination;
private Group Group_Destination;
private String content;

public Message(Message_ID iD, String encryption_type, int length, User source, int group_size) {
	this.ID = iD;
	this.Encryption_type = encryption_type;
	this.length = length;
	this.Source = source;
	this.Group_size = group_size;
}
public Message_ID getID() {
	return ID;
}
public void setID(Message_ID iD) {
	ID = iD;
}
public String getEncryption_type() {
	return Encryption_type;
}
public void setEncryption_type(String encryption_type) {
	Encryption_type = encryption_type;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}

public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public User getSource() {
	return Source;
}
public void setSource(User source) {
	Source = source;
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
public int getGroup_size() {
	return Group_size;
}
public void setGroup_size(int group_size) {
	Group_size = group_size;
}



}
