package lounge.controllers;

import com.mongodb.DBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/register", description = "Register", produces = "application/json")
@RequestMapping("/register")
public class RegisterController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private void getEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@ApiOperation(value = "Register new user", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User registered", response = User.class),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/")
	public User register(@RequestBody User newUser) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User dbUser = userDAO.findOne("username", newUser.getUsername());

		// user is not in db
		if (dbUser == null) {
			// encode password
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			DBObject tmp = conn.getMorphia().toDBObject(newUser);
			userDAO.getCollection().insert(tmp);
			return newUser;
		} else {
			// username already used
			return null;
		}
	}
}
