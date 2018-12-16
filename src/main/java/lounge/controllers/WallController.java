package lounge.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/wall", description = "Wall", produces = "application/json")
@RequestMapping("/wall")
public class WallController {

	@GetMapping("/")
	public String index() {
		return "You are now in the settings";
	}

}
