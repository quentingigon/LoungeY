package io.lounge.mongo.dao.domodels;

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
	private String djRank;

	private String profilePic;
	private String coverPic;

	private boolean isAdmin;

	private ArrayList<ObjectId> friendsList;
	private ArrayList<ObjectId> pendingInviteList;
	private ArrayList<NotificationDO> notifications;

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
		notifications = new ArrayList<>();
	}

	public UserDO(String email, String username, String password) {
		this.setId(new ObjectId());
		this.email = email;
		this.username = username;
		this.password = password;
		this.isAdmin = false;

		friendsList = new ArrayList<>();
		pendingInviteList = new ArrayList<>();
		notifications = new ArrayList<>();
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

	public void addToFriendsList(ObjectId newFriend) {
		if (friendsList == null) {
			friendsList = new ArrayList<>();
		}
		friendsList.add(newFriend);
	}

	public void removeFromFriendList(ObjectId userId) {
		friendsList.remove(userId);
	}

	public ArrayList<NotificationDO> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<NotificationDO> notifications) {
		this.notifications = notifications;
	}

	public void addNotification(NotificationDO notifId) {
		if (notifications == null) {
			notifications = new ArrayList<>();
		}
		notifications.add(notifId);
	}

	public void removeNotification(NotificationDO notifId) {
		notifications.remove(notifId);
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

	public void addToPendingInviteList(ObjectId newInvite) {
		if (pendingInviteList == null) {
			pendingInviteList = new ArrayList<>();
		}
		pendingInviteList.add(newInvite);
	}

	public void removeFromPendingInviteList(ObjectId inviteId) {
		pendingInviteList.remove(inviteId);
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

	public String getDjRank() {
		return djRank;
	}

	public void setDjRank(String djRank) {
		this.djRank = djRank;
	}

	public String toString(){
		return this.username+"@"+this.getId().toString();
	}
}
