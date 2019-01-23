package io.lounge.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.domodels.NotificationDO;
import io.lounge.mongo.dao.domodels.NotificationType;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

import static io.lounge.configuration.LoungeConstants.FRIENDINVITE_NOTIF;
import static io.lounge.configuration.LoungeConstants.FRIEND_NOTIF;

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

	public boolean isUsernameUnique(String username) {
		DBObject query = new BasicDBObject();
		query.put("username", username );
		Query<UserDO> q = ds.find(UserDO.class);
		q.criteria("username").equal(username);

		return q.asList().isEmpty();
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

		NotificationDAO notifDAO = DAOUtils.getNotificationDAO();
		ObjectId currentId = current.getId();
		ObjectId newFriendId = newFriend.getId();

		if (!areFriends(current.getUsername(), newFriend.getUsername())) {

			// if the person we add as friend already did the same
			if (newFriend.getPendingInviteList() != null && newFriend.getPendingInviteList().contains(currentId)) {
				// add each other to their friends list
				current.addToFriendsList(newFriendId);
				newFriend.addToFriendsList(currentId);
				newFriend.removeFromPendingInviteList(currentId);
				// remove last notif of invite between users
				NotificationDO notifToDelete = notifDAO.getLastFriendInviteBetweenUsers(current.getUsername(), newFriend.getUsername());
				current.removeNotification(notifToDelete);
				notifDAO.delete(notifToDelete);

				// add a notification to both users to inform them they are now friends
				NotificationDO newNotifDO = new NotificationDO(FRIEND_NOTIF, newFriend.getUsername(), current.getUsername(),
					String.valueOf(NotificationType.NEWFRIEND));
				current.addNotification(newNotifDO);
				newNotifDO.setFromUser(current.getUsername());
				newNotifDO.setToUser(newFriend.getUsername());
				newFriend.addNotification(newNotifDO);

				updateUser(current);
				updateUser(newFriend);
			}
			else {
				// add user to pending invites
				current.addToPendingInviteList(newFriend.getId());

				// TODO maybe verify that there is not already a friendInvite from this user to the other
				// add notification of new friend invite
				NotificationDO newNotifDO = new NotificationDO(FRIENDINVITE_NOTIF, current.getUsername(),
					newFriend.getUsername(), String.valueOf(NotificationType.FRIENDINVITE));
				notifDAO.addNotification(newNotifDO);
				newFriend.addNotification(newNotifDO);

				updateUser(current);
				updateUser(newFriend);
			}
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
