package io.lounge.mongo;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.utils.MongoConnection;
import io.lounge.mongo.dao.PostDAO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.lounge.mongo.dao.domodels.PostType;
import io.lounge.mongo.dao.domodels.UserDO;

import io.lounge.mongo.dao.UserDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		// mongodb usage example
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());
		PostDAO pDAO = new PostDAO(conn.getDatastore());

		UserDO user = (!userDAO.userExists("test")) ? new UserDO("test", "test", "test") : userDAO.getUser("test");
		UserDO robin = (!userDAO.userExists("Robin")) ? new UserDO("r@r.c", "Robin", "test") : userDAO.getUser("Robin");
		UserDO testy = (!userDAO.userExists("testy")) ? new UserDO("t@r.c", "testy", "test") : userDAO.getUser("testy");
		UserDO quatre = (!userDAO.userExists("quatre")) ? new UserDO("q@r.c", "quatre", "test") : userDAO.getUser("quatre");

		DBObject tmp = conn.getMorphia().toDBObject(user);

		System.out.println("inserting :");

		userDAO.addUser(user);
		userDAO.addUser(testy);
		userDAO.addUser(robin);
		userDAO.addUser(quatre);

		PostDO p = new PostDO("Hello", "", String.valueOf(PostType.POST), robin.getId());

		pDAO.addPost(p);

		p.addComment(new PostDO("wORLD", dateFormat.format(new Date()), String.valueOf(PostType.COMMENT), robin.getId()));

		pDAO.addComment(new PostDO("World", "16/01/19", String.valueOf(PostType.COMMENT), robin.getId()), p);

		PostDO commToDel = new PostDO("To REMOVE", "16/01/19", String.valueOf(PostType.COMMENT), robin.getId());
		pDAO.addComment(commToDel, p);


		pDAO.remComment(commToDel.getId(), commToDel.getParentId());

		System.out.println("Robin posts :");
		for(PostDO pp : pDAO.getPostsOfUser(robin, 5)){
			System.out.println(pp.getId() + "; ");
		}

		user.setPassword("test2");

		System.out.println("Password : " + userDAO.getUser(user.getUsername()).getPassword());

		userDAO.sendFriendInvite("test", "Robin");
		userDAO.sendFriendInvite("Robin", "test");
		//userDAO.addFriend("test", "testy");
		userDAO.sendFriendInvite("test", "quatre");

		System.out.println("Changed test pwd to test2 : " +  userDAO.getUser(user.getUsername()).getPassword());

		System.out.println(userDAO.getAllUsers());
		System.out.print("\nUsers : ");
		for(UserDO u : userDAO.getAllUsers()){
			System.out.print(u.getUsername() + ", ");
		}

		System.out.println("Test friends : " + userDAO.getUserFriends("test"));



	}
}
