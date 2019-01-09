package lounge.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO extends BasicDAO<User, ObjectId> {


	public UserDAO(Datastore ds) {
		super(ds);
	}

	public User getUser(String username) {
		return findOne("username", username);
	}

	public List<User> getAllUsers() {
		return find().asList();
	}

	public boolean addUser(User user) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(user);

		if(isUsernameUnique(user.getUsername()))
			return  getCollection().save(tmp).wasAcknowledged();
		else {
			System.out.println("User already exists");
			return false;
		}
	}

	public boolean userExists(String username) {
		return (getUser(username) != null);
	}

	public boolean updateUser(User user) {
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

	public boolean isValidUser(User user) {
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
		Query<User> q = ds.find(User.class);
		q.criteria("username").equal(username);

		return q.asList().isEmpty();
	}

	public List<User> getAllFriendsFromUser(User user) {
		ArrayList<User> friendsuser = new ArrayList();
		ArrayList<ObjectId> idfriendsuser = user.getFriendsList();
		Iterator<ObjectId> it = idfriendsuser.iterator();
		while (it.hasNext()){
			friendsuser.add(get(it.next()));
		}

		return friendsuser;
	}

	public boolean addFriend(User user) {
		// TODO
		return false;
	}
}
