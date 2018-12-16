package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.Post;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.PostDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/search", description = "Search through posts in wall", produces = "application/json")
@RequestMapping("/search")
public class SearchController {

	@ApiOperation(value = "Search posts")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Search successful"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Search not found")
	})
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<Post>> search(@RequestBody String searchString) {

		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());

		String[] parts = searchString.split(" ");

		boolean searchingCourses = false;

		// check if hashtags are in search string
		for (String p: parts) {
			if (p.startsWith("#")) {
				searchingCourses = true;
				break;
			}
		}

		return new ResponseEntity<>(postDAO.find().asList(), HttpStatus.OK);
	}
}
