package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/settings", description = "Settings", produces = "application/json")
@RequestMapping("/settings")
public class SettingsController {

	@ApiOperation(value = "Return settings page")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User retrieved"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Event not found")
	})
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getSettings(@RequestBody long userId){
		return null;
	}
}
