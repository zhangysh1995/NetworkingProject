package DataManager;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
/**
 * This is the class abstraction of group object
 * @author Ce
 *
 */
public class Group {
private LinkedHashMap<String, User> group = new LinkedHashMap<String, User>();
private int Group_ID;
public Group(int group_ID) {
	Group_ID = group_ID;
}
public void add_member(User user) {
	group.put(user.getUser_email(),user);
}
public void delete_member(User user) {
	group.remove(user.getUser_email());
}
public int get_length() {
	return group.size();
}
public Set<Entry<String, User>> getSet(){
	return group.entrySet();
}
public int getGroup_ID() {
	return Group_ID;
}
public void setGroup_ID(int group_ID) {
	Group_ID = group_ID;
}

}
