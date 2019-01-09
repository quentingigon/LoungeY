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

		User user = (!userDAO.userExists("test")) ? new User("test", "test", "test") : userDAO.getUser("test");
		User robin = (!userDAO.userExists("Robin")) ? new User("r@r.c", "Robin", "test") : userDAO.getUser("Robin");

		DBObject tmp = conn.getMorphia().toDBObject(user);

		System.out.println("inserting :");

		userDAO.addUser(user);
		userDAO.addUser(user);
		userDAO.addUser(robin);
		user.setPassword("test2");

		System.out.println("Password : " + userDAO.getUser(user.getUsername()).getPassword());
		user.addFriendinvite(robin);
		userDAO.updateUser(user);


		System.out.println("Changed test pwd to test2 : " +  userDAO.getUser(user.getUsername()).getPassword());


		System.out.println(userDAO.getAllUsers());
		System.out.print("\nUsers : ");
		for(User u : userDAO.getAllUsers()){
			System.out.print(u.getUsername() + ", ");
		}


	}
}
