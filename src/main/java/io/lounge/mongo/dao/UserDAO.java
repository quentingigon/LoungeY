package io.lounge.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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
import java.util.Iterator;
import java.util.List;

import static io.lounge.configuration.LoungeConstants.FRIENDINVITE_NOTIF;
import static io.lounge.configuration.LoungeConstants.FRIEND_NOTIF;

public class UserDAO extends BasicDAO<UserDO, ObjectId> {


	public UserDAO(Datastore ds) {
		super(ds);
	}

	/**
	 * Gets the user with the username passed in arg. from the DB and returns it.
	 *
	 * @param username 		username of the user to find
	 *
	 * @return				UserDo representing the user
	 *
	 */
	public UserDO getUser(String username) {
		return findOne("username", username);
	}

	/**
	 * Gets the user with the passed id in arg. from the DB and returns it.
	 *
	 * @param uId 			ObjectId (DB id) of the user to find
	 *
	 * @return				UserDo representing the user
	 *
	 */
	public UserDO getUser(ObjectId uId) {
		return findOne("_id", uId);
	}

	/**
	 * Gets the user with the id (string version) passed in arg. from the DB and returns it.
	 *
	 * @param id 			DB id of the user to find in form of a String
	 *
	 * @return				UserDo representing the user
	 *
	 */
	public UserDO getUserById(String id) {
		return findOne("_id", new ObjectId(id));
	}


	/**
	 * Gets all the different users from the DB
	 *
	 * @return				List of UserDo representing all the users
	 *
	 */
	public List<UserDO> getAllUsers() {
		return find().asList();
	}

	/**
	 * Converts the passed user into a DBObject and saves it in the DB.
	 *
	 * @param user 		UserDO object to save inside the DB
	 *
	 * @return			<code>true</code> if everything went well and user is saved
	 * 					<code>false</code> if username already is taken or if there is a connection/server error.
	 *
	 */
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

	/**
	 *  Check if given user already exists in DB
	 *
	 * @param username 		username of the user to check
	 *
	 * @return				<code>true</code> if user exists
	 * 						<code>false</code> if user doesn't
	 *
	 */
	public boolean userExists(String username) {
		return (getUser(username) != null);
	}


	/**
	 *  Saves changes made over a user by getting old datas and replacing it by writing UserDO "user" object over.
	 *
	 * @param user 		UserDO representing the modified user
	 *
	 * @return          <code>true</code> if everything went well and user is saved
	 * 	 * 				<code>false</code> if username already is taken or if there is a connection/server error.
	 *
	 */
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

	/**
	 *  TODO Wut ?
	 * @param user
	 * @return
	 */
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

	/**
	 *  TODO Wut again?
	 * @param username
	 * @return
	 */
	public boolean isUsernameUnique(String username) {
		DBObject query = new BasicDBObject();
		query.put("username", username );
		Query<UserDO> q = ds.find(UserDO.class);
		q.criteria("username").equal(username);

		return q.asList().isEmpty();
	}


	/**
	 * TODO not sure if useful
	 *
	 *  Gets all passed user's friends by converting user's ObjectId list into UserDO list
	 * @param 	user	UserDO representing the user which we want to find friends for
	 * @return			List of UserDO that represents user's friends
	 */
	public List<UserDO> getAllFriendsFromUser(UserDO user) {
		ArrayList<UserDO> friendsuser = new ArrayList();
		ArrayList<ObjectId> idfriendsuser = user.getFriendsList();
		Iterator<ObjectId> it = idfriendsuser.iterator();
		while (it.hasNext()){
			friendsuser.add(get(it.next()));
		}

		return friendsuser;
	}

	/**
	 *  Gets all passed user's friends by checking if they mutually are in each others' friend list
	 * @param 	username	String equals to the username of the user which we want to find friends for
	 * @return				List of UserDO that represents user's friends
	 */
	public List<UserDO> getUserFriends(String username){
		return getUserFriends(getUser(username));
	}

	/**
	 *  Gets all passed user's friends by checking if they mutually are in each others' friend list and converts each ObjectID in UserDO
	 * @param 	user	UserDO representing the user which we want to find friends for
	 * @return			List of UserDO that represents user's friends
	 */
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

	/**
	 * Checks if users are not already friends and if not, Adds a friend invite from current to newFriend and a notification for this event
	 * If the other user already invited the user, then this function becomes addFriends and send both users a notification.
	 *
	 * @param current the user inviting newFriend
	 * @param newFriend the user invited by current
	 */
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
				current.removeNotification(notifDAO.getLastFriendInviteBetweenUsers(current.getUsername(), newFriend.getUsername()));

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

	/**
	 * Same as sendFriendInvite(UserDO,UserDO) but with usernames (String)
	 *
	 * @param username	the user's username who is inviting othername
	 * @param othername the user's username who is invited by username
	 */
	public void sendFriendInvite(String username, String othername){
		sendFriendInvite(getUser(username), getUser(othername));
	}

	/**
	 * Removes the friendship between the two users
	 *
	 * @param user		One user
	 * @param other		His soon to be ex-friend
	 */
	public void removeFriend(UserDO user, UserDO other){
		// TODO send notifications
        user.removeFromFriendList(other.getId());
        other.removeFromFriendList(user.getId());
        updateUser(user);
        updateUser(other);
	}

	/**
	 * Checks if users are already firends
	 *
	 * @param username			One user
	 * @param otherusername		His potential friend
	 * @return
	 */
	public boolean areFriends(String username, String otherusername){
		return getUser(username).isFriendWith(getUser(otherusername));
	}
}
