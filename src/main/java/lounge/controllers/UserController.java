package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/users", description = "Users", produces = "application/json")
@RequestMapping("/users")
public class UserController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@ApiOperation(value = "get user",response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User retrieved", response = User.class),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Event not found")
	})
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser() {
		User u = new User();
		u.setUsername("test");
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
}
