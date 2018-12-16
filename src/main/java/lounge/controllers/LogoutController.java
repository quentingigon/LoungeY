package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/logout", description = "Logout")
@RequestMapping("/logout")
public class LogoutController {

	@ApiOperation(value = "Logout current user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User retrieved", response = User.class),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Event not found")
	})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public boolean logout(){
		return false;
	}
}
