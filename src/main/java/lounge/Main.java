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

		User user = new User("test", "test", "test");
		DBObject tmp = conn.getMorphia().toDBObject(user);

		System.out.println("inserting :");

		userDAO.addUser(user);

		userDAO.addUser(user);


		System.out.println("Password : " + userDAO.find().asList().get(0).getPassword());


		System.out.println(userDAO.getAllUsers());
		System.out.println("Users : ");
		for(User u : userDAO.getAllUsers()){
			System.out.println("\t" + u.getUsername());
		}


	}
}
