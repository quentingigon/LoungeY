package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.UserDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="/login", description="Login Controller", produces ="application/json")
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@ApiOperation(value="Try to log in",response = User.class)
	@ApiResponses(value={
		@ApiResponse(code=200, message = "Logged in",response = User.class),
		@ApiResponse(code=500, message = "Internal Server Error")
	})
	@PostMapping(value="/")
	public User login(@RequestBody User user) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User dbUser = userDAO.findOne("username", user.getUsername());

		if (user.getPassword().equals(dbUser.getPassword())) {
			// logged in
			return dbUser;
		}
		else {
			return null;
		}
	}
}
