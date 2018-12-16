package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.Post;
import lounge.models.User;
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
@Api(value = "/lounge", description = "Lounge", produces = "application/json")
@RequestMapping("/lounge")
public class LoungeController {

	@ApiOperation(value = "Get LoungeY")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Lounge retrieved"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Lounge not found")
	})
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Post>> getLounge(@RequestBody User user){
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());

		return new ResponseEntity<>(postDAO.find().asList(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get LoungeY, questions only")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Lounge retrieved"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Lounge not found")
	})
	@RequestMapping(value = "/questions", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Post>> getLoungeQuestions(@RequestBody User user){
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());

		// find only questions
		return new ResponseEntity<>(postDAO.find().asList(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get LoungeY, friends' posts only")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Lounge retrieved"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Lounge not found")
	})
	@RequestMapping(value = "/friends", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Post>> getLoungeFriends(@RequestBody User user){
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());

		// find only friends' posts
		return new ResponseEntity<>(postDAO.find().asList(), HttpStatus.OK);
	}
}
