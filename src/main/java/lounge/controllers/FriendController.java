package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.UserDAO;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "/friends", description = "Friends", produces = "application/json")
@RequestMapping("/friends")
public class FriendController {

	@ApiOperation(value = "get user", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User retrieved", response = User.class),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Event not found")
	})
	@RequestMapping(value = "/{idCurrentUser}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<User>> getAllFriends(@PathVariable("idCurrentUser") long userId) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User user = userDAO.findOne("id", userId);

		if (user != null) {

			ArrayList<ObjectId> friends = user.getFriendsList();
			ArrayList<User> result = new ArrayList<>();

			for (ObjectId o: friends) {
				result.add(userDAO.findOne("id", o));
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Add friend", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Friend added", response = User.class),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Page not found")
	})
	@RequestMapping(value = "/{idCurrentUser}/new/{idNewFriend}", method = RequestMethod.POST, produces = "application/json")
	public boolean addFriend(@PathVariable("idCurrentUser") long userId,
							 @PathVariable("idNewFriend") long newFriendId) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User user = userDAO.findOne("id", userId);

		if (user != null) {

			// do something magic here, as add friends

			return true;
		}
		else {
			return false;
		}
	}
}
