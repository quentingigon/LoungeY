package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(value = "/login", description = "Login Controller", produces = "application/json")
@RequestMapping("/login")
public class LoginController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private void getEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@ApiOperation(value = "Try to log in")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Logged in"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/")
	public void login(@RequestBody User user, HttpServletResponse response) throws IOException {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		User dbUser = userDAO.findOne("username", user.getUsername());

		if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
			// logged in
			response.sendRedirect("/lounge");
		}
		else {
			response.sendRedirect("/login");
		}
	}
}
