package io.lounge.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO extends BasicDAO<UserDO, ObjectId> {


	public UserDAO(Datastore ds) {
		super(ds);
	}

	public UserDO getUser(String username) {
		return findOne("username", username);
	}
	public UserDO getUser(ObjectId uId) {
		return findOne("_id", uId);
	}

	public UserDO getUserById(String id) {
		return findOne("_id", new ObjectId(id));
	}

	public List<UserDO> getAllUsers() {
		return find().asList();
	}

	public boolean addUser(UserDO user) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(user);

		if(isUsernameUnique(user.getUsername()))
			return  getCollection().save(tmp).wasAcknowledged();
		else {
			System.out.println("UserDO already exists");
			return false;
		}
	}

	public boolean userExists(String username) {
		return (getUser(username) != null);
	}

	public boolean updateUser(UserDO user) {
		if(userExists(user.getUsername())) {
			MongoConnection conn = MongoConnection.getInstance();
			conn.init();
			DBObject newUser = conn.getMorphia().toDBObject(user);
			DBObject oldUser = conn.getMorphia().toDBObject(getUser(user.getUsername()));
			return getCollection().update(oldUser, newUser).wasAcknowledged();
		} else {
			System.out.println("Cannot update unexisting user");
			return false;
		}

	}

	public boolean isValidUser(UserDO user) {
		int count = 0;
		BasicDBObject query = new BasicDBObject("username", user.getUsername())
				.append("email", user.getEmail())
				.append("password", user.getPassword());
		try(DBCursor cursor = getCollection().find(query)){

			while(cursor.hasNext()){
				count = count +1;
			}
		}
		return count==1;
	}

	public boolean isUsernameUnique(String username) {
		DBObject query = new BasicDBObject();
		query.put("username", username );
		Query<UserDO> q = ds.find(UserDO.class);
		q.criteria("username").equal(username);

		return q.asList().isEmpty();
	}

	public List<UserDO> getAllFriendsFromUser(UserDO user) {
		ArrayList<UserDO> friendsuser = new ArrayList();
		ArrayList<ObjectId> idfriendsuser = user.getFriendsList();
		Iterator<ObjectId> it = idfriendsuser.iterator();
		while (it.hasNext()){
			friendsuser.add(get(it.next()));
		}

		return friendsuser;
	}

	public List<UserDO> getUserFriends(String username){
		return getUserFriends(getUser(username));
	}

	public List<UserDO> getUserFriends(UserDO user) {
		ArrayList<UserDO> friends = new ArrayList<>();
		ArrayList<ObjectId> idfriendsuser = user.getFriendsList();

		if(idfriendsuser!=null){
			for(ObjectId otherId : idfriendsuser){
				UserDO u = findOne("_id", otherId);

				if(u.getFriendsList() !=null && u.getFriendsList().contains(user.getId())){
					friends.add(u);
				}
			}
		}
		return friends;
	}

	public void sendFriendInvite(UserDO current, UserDO newFriend) {

		ObjectId currentId = current.getId();
		ObjectId newFriendId = newFriend.getId();
		// TODO send notifications
		// if the person we add as friend already did the same
		if (newFriend.getPendingInviteList() != null && newFriend.getPendingInviteList().contains(currentId)) {
			// add each other to their friends list
			current.addToFriendsList(newFriendId);
			newFriend.addToFriendsList(currentId);
			newFriend.removeFromPendingInviteList(currentId);
			updateUser(current);
			updateUser(newFriend);
		}
		else {
			// add user to pending invites
			current.addToPendingInviteList(newFriend.getId());
			updateUser(current);
		}
	}

	public void sendFriendInvite(String username, String othername){
		sendFriendInvite(getUser(username), getUser(othername));
	}

	public void removeFriend(UserDO user, UserDO other){
		// TODO send notifications
        user.removeFromFriendList(other.getId());
        other.removeFromFriendList(user.getId());
        updateUser(user);
        updateUser(other);
	}

	public boolean areFriends(String username, String otherusername){
		return getUser(username).isFriendWith(getUser(otherusername));
	}
}
