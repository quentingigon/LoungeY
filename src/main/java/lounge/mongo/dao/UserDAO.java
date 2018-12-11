package lounge.mongo.dao;

import lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class UserDAO extends BasicDAO<User, ObjectId> {

	public UserDAO(Datastore ds) {
		super(ds);
	}
}
