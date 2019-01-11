package lounge.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class User extends BasicDO {

	private String username;
	private String password;
	private String email;

	private boolean isAdmin;

	private ArrayList<ObjectId> friendsList;
	private ArrayList<ObjectId> pendingInviteList;

	public User() {}

	public User(String email, String username, String password) {
		this.setId(new ObjectId());
		this.email = email;
		this.username = username;
		this.password = password;
		this.isAdmin = false;

		friendsList = new ArrayList<>();
		pendingInviteList = new ArrayList<>();
	}

	public ArrayList<ObjectId> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(ArrayList<ObjectId> friendsList) {
		this.friendsList = friendsList;
	}

	public void addFriendInvite(User other){
		if(this.friendsList == null)
			this.friendsList=new ArrayList<>() ;

		if(!this.friendsList.contains(other.getId()))
			this.friendsList.add(other.getId());

		if(!isFriendWith(other)){
			if(other.pendingInviteList == null)
				other.pendingInviteList = new ArrayList<>();
			if(!other.pendingInviteList.contains(this.getId()))
				other.pendingInviteList.add(this.getId());
		}

		if(this.pendingInviteList != null && this.pendingInviteList.contains(other.getId()))
			this.pendingInviteList.remove(other.getId());
	}

	public void removeFriendInvite(User other){
		if(this.friendsList != null && this.friendsList.contains(other.getId()))
			this.friendsList.remove(other.getId());

		if(other.friendsList != null && other.friendsList.contains(other.getId()))
			other.friendsList.remove(this.getId());

		if(other.pendingInviteList != null && other.pendingInviteList.contains(this.getId()))
			other.pendingInviteList.remove(this.getId());

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFriendWith(User other){
		if(this.friendsList!=null && other.friendsList!=null)
			return (this.friendsList.contains(other.getId()) && other.friendsList.contains(this.getId()));

		return false;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return this.username+"@"+this.getId().toString();
	}
}
