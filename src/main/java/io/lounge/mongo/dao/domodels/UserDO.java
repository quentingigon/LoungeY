package io.lounge.mongo.dao.domodels;

import io.lounge.models.NewUser;
import io.lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class UserDO extends BasicDO {

	private String username;
	private String password;
	private String email;
	private String name;
	private String orientation;
	private String yearOfStudy;
	private String favBeer;
	private String profilePic;
	private String coverPic;

	private boolean isAdmin;

	private ArrayList<ObjectId> friendsList;
	private ArrayList<ObjectId> pendingInviteList;

	public UserDO() {}

	public UserDO(String username, String password, String email, String name, String orientation, String yearOfStudy, String favBeer) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.orientation = orientation;
		this.yearOfStudy = yearOfStudy;
		this.favBeer = favBeer;
		this.isAdmin = false;

		friendsList = new ArrayList<>();
		pendingInviteList = new ArrayList<>();
	}

	public UserDO(NewUser newUser) {
		this.username = newUser.getUsername();
		this.email = newUser.getEmail();
		this.password = newUser.getPassword();
	}

	public UserDO(String email, String username, String password) {
		this.setId(new ObjectId());
		this.email = email;
		this.username = username;
		this.password = password;
		this.isAdmin = false;

		friendsList = new ArrayList<>();
		pendingInviteList = new ArrayList<>();
	}

	public User toUser() {
		User user = new User();
		user.setEmail(email);
		user.setId(getId().toHexString());
		user.setUsername(username);

		return user;
	}

	public ArrayList<ObjectId> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(ArrayList<ObjectId> friendsList) {
		this.friendsList = friendsList;
	}

	public void addFriendInvite(UserDO other){
		if(this.friendsList == null)
			this.friendsList=new ArrayList<>() ;

		if(!this.friendsList.contains(other.getId()))
			this.friendsList.add(other.getId()); // TODO je pense qu'il faut l'ajouter a la liste d'amis
												 // quand l'autre a accepté et pas avant

		if(!isFriendWith(other)){
			if(other.pendingInviteList == null)
				other.pendingInviteList = new ArrayList<>();
			if(!other.pendingInviteList.contains(this.getId()))
				other.pendingInviteList.add(this.getId());
		}

		if(this.pendingInviteList != null && this.pendingInviteList.contains(other.getId()))
			this.pendingInviteList.remove(other.getId());
	}

	public void removeFriendInvite(UserDO other){
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

	public boolean isFriendWith(UserDO other){
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public String getFavBeer() {
		return favBeer;
	}

	public void setFavBeer(String favBeer) {
		this.favBeer = favBeer;
	}

	public ArrayList<ObjectId> getPendingInviteList() {
		return pendingInviteList;
	}

	public void setPendingInviteList(ArrayList<ObjectId> pendingInviteList) {
		this.pendingInviteList = pendingInviteList;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String toString(){
		return this.username+"@"+this.getId().toString();
	}
}
