package cyy_IM_protocol;

import DataManager.Group;
import DataManager.User;

/**
 * This is the Message Class
 * @author Ce
 *
 */
public class Message_cyy {
private Message_ID ID;
private String Encryption_type;
private int length;
private String content;

public Message_cyy(Message_ID iD, String encryption_type, int length) {
	this.ID = iD;
	this.Encryption_type = encryption_type;
	this.length = length;
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




}
