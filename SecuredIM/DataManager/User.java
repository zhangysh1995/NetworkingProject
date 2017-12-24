package DataManager;
/**
 * This is the class abstraction for object
 * @author Ce
 *
 */
public class User {
private String user_email;
private String status;
public User() {}
public User(String user_email) {
	this.user_email = user_email;
}
public String getUser_email() {
	return user_email;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
