package lounge;

import com.mongodb.DBObject;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.UserDAO;

public class Main {

	public static void main(String[] args) {

		// mongodb usage example
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User user = new User("test", "test");
		DBObject tmp = conn.getMorphia().toDBObject(user);

		System.out.println("inserting");
		userDAO.getCollection().insert(tmp);

		System.out.println(userDAO.find().asList().get(0).getPassword());

	}
}
