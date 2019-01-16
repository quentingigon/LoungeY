package lounge;

import com.mongodb.DBObject;
import javafx.geometry.Pos;
import lounge.models.Post;
import lounge.models.PostType;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.PostDAO;
import lounge.mongo.dao.UserDAO;
import org.bson.types.ObjectId;

public class Main {

	public static void main(String[] args) {

		// mongodb usage example
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());
		PostDAO pDAO = new PostDAO(conn.getDatastore());

		User user = (!userDAO.userExists("test")) ? new User("test", "test", "test") : userDAO.getUser("test");
		User robin = (!userDAO.userExists("Robin")) ? new User("r@r.c", "Robin", "test") : userDAO.getUser("Robin");
		User testy = (!userDAO.userExists("testy")) ? new User("t@r.c", "testy", "test") : userDAO.getUser("testy");
		User quatre = (!userDAO.userExists("quatre")) ? new User("q@r.c", "quatre", "test") : userDAO.getUser("quatre");

		DBObject tmp = conn.getMorphia().toDBObject(user);

		System.out.println("inserting :");

		userDAO.addUser(user);
		userDAO.addUser(testy);
		userDAO.addUser(robin);
		userDAO.addUser(quatre);

		Post p = new Post("Hello", "16/01/19", PostType.POST, robin.getId());

		pDAO.addPost(p);

		p.addComment(new Post("wORLD", "16/01/19", PostType.COMMENT, robin.getId()));

		pDAO.addComment(new Post("World", "16/01/19", PostType.COMMENT, robin.getId()), p);

		Post commToDel = new Post("To REMOVE", "16/01/19", PostType.COMMENT, robin.getId());
		pDAO.addComment(commToDel, p);


		pDAO.remComment(commToDel.getId(), commToDel.getParentId());
		user.setPassword("test2");

		System.out.println("Password : " + userDAO.getUser(user.getUsername()).getPassword());

		userDAO.followGuy("test", "Robin");
		userDAO.followGuy("Robin", "test");
		userDAO.addFriend("test", "testy");
		userDAO.followGuy("test", "quatre");

		System.out.println("Changed test pwd to test2 : " +  userDAO.getUser(user.getUsername()).getPassword());

		System.out.println(userDAO.getAllUsers());
		System.out.print("\nUsers : ");
		for(User u : userDAO.getAllUsers()){
			System.out.print(u.getUsername() + ", ");
		}

		System.out.println("Test friends : " + userDAO.getUserFriends("test"));


	}
}
