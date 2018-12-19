package lounge.mongo.dao;

import lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class UserDAO extends BasicDAO<User, ObjectId> {

	public UserDAO(Datastore ds) {
		super(ds);
	}

	public User getUser(String username) {
		// TODO
		return null;
	}

	public List<User> getAllUsers() {
		// TODO
		return null;
	}

	public boolean addUser(User user) {
		// TODO
		return false;
	}

	public boolean updateUser(User user) {
		// TODO
		return false;
	}

	public boolean isValidUser(User user) {
		// TODO
		return false;
	}

	public boolean isUsernameUnique(String username) {
		// TODO
		return false;
	}

	public List<User> getAllFriendsFromUser(User user) {
		// TODO
		return null;
	}

	public boolean addFriend(User user) {
		// TODO
		return false;
	}
}
