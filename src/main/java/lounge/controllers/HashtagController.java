package lounge.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/tags", description = "Hashtags Controller", produces = "application/json")
@RequestMapping("/tags")
public class HashtagController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
