package lounge.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lounge.models.Hashtag;
import lounge.models.Post;
import lounge.models.User;
import lounge.mongo.dao.HashtagDAO;
import lounge.mongo.dao.MongoConnection;
import lounge.mongo.dao.PostDAO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "/posts", description = "Posts Controller", produces = "application/json")
@RequestMapping("/posts")
public class PostController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@ApiOperation(value = "Get post by id", response = Post.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got post", response = Post.class),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/getPost")
	public Post getPost(@RequestBody ObjectId postId) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		PostDAO postDAO = new PostDAO(conn.getDatastore());

		return postDAO.findOne("id", postId);
	}

	@ApiOperation(value = "Return all post of user", response = Post.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got list of posts", response = Post.class),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/userPosts")
	public List<Post> getPostsOfUser(@RequestBody User user) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		PostDAO postDAO = new PostDAO(conn.getDatastore());

		// maybe id is not good choices
		Query<Post> q = postDAO.createQuery().filter("author", user.getId());
		return postDAO.find(q).asList();
	}

	@ApiOperation(value = "Return all posts containing this hashtag", response = Post.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got list of posts", response = Post.class),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/tagPosts")
	public List<Post> getPostsWithHashtag(@RequestBody Hashtag hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		ArrayList<Post> result = new ArrayList<>();

		PostDAO postDAO = new PostDAO(conn.getDatastore());
		HashtagDAO tagDAO = new HashtagDAO(conn.getDatastore());
		Hashtag tag = tagDAO.findOne("name", hashtag.getName());
		ArrayList<ObjectId> posts = tag.getPostsContainingHashtag();

		return null;
	}

	@ApiOperation(value = "Return all posts containing all hashtags in the given list", response = Post.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got list of posts", response = Post.class),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/tagsPosts")
	public List<Post> getPostsWithHashtag(@RequestBody List<Hashtag> hashtags) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		PostDAO postDAO = new PostDAO(conn.getDatastore());
		return null;
	}
}
