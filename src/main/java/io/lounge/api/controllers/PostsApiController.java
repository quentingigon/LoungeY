package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.PostsApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Comment;
import io.lounge.models.NewPost;
import io.lounge.models.Post;
import io.lounge.mongo.dao.HashtagDAO;
import io.lounge.mongo.dao.PostDAO;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.mongo.dao.utils.MongoDBUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class PostsApiController implements PostsApi {

    private static final Logger log = LoggerFactory.getLogger(PostsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> comment(@ApiParam(value = "The new comment" ,required=true )  @Valid @RequestBody Comment comment) {
    	PostDAO postDAO = DAOUtils.getPostDAO();

		PostDO commentDO = comment.getPost().toPostDO();
		PostDO rootPostDO = postDAO.getPostById(comment.getRootPostId());

		if (commentDO != null && rootPostDO != null) {

			if (postDAO.addComment(commentDO, rootPostDO)) {
				// comment is correctly added
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}


    }

    public ResponseEntity<Post> getPost(@ApiParam(value = "The id of the post to get",required=true) @PathVariable("postId") String postId) {
		PostDAO postDAO = DAOUtils.getPostDAO();

		PostDO postDO = postDAO.getPostById(postId);

		if (postDO != null) {
			Post post = postDO.toPost();

			return new ResponseEntity<Post>(post, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


    }

    public ResponseEntity<List<Post>> getUserPosts(@ApiParam(value = "Username of the user",required=true) @PathVariable("username") String username,
												   @ApiParam(value = "number of posts to return", required = true) @RequestParam("number") int number) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO user = userDAO.getUser(username);

		if (user != null) {
			List<Post> posts = new ArrayList<>();
//Integer.valueOf(number)
			for (PostDO p : postDAO.getPostsOfUser(user, number)) {
				if (p != null)
					posts.add(p.toPost());
			}
			return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Post>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }

    public ResponseEntity<Boolean> post(@ApiParam(value = "New post" ,required=true )  @Valid @RequestBody NewPost newPost) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		HashtagDAO hashtagDAO = DAOUtils.getHashtagDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		// if hashtags list is not initialized, initialize it
		// TODO its not very clean, we should do a script at db start up if possible
		if (hashtagDAO.find().asList().isEmpty()) {
			MongoDBUtils mongoDBUtils = new MongoDBUtils();
			mongoDBUtils.fillHashtagList();
		}

		UserDO userDO = userDAO.getUser(newPost.getUsername());

		// if user exists
		if (userDO != null) {
			// set userId of Post before transforming it into a PostDO
			Post post = newPost.getPost();
			post.setUserId(userDO.getId().toHexString());
			PostDO postDO = newPost.getPost().toPostDO();

			// if post was correclty transformed to postDO
			if (postDO != null) {

				// if the post we received contains hashtags
				if (post.getHashtags() != null && !post.getHashtags().isEmpty())
					// add our hashtags to the postDO
					postDAO.fillHashTagsListOfPostDO(postDO, post.getHashtags());

				String postId = postDAO.addPost(postDO, userDO);

				// post was correclty saved
				if (postId != null) {
					// update hashtag's lists
					hashtagDAO.addPostIdToHashtagsLists(post.getHashtags(), postId);
					return new ResponseEntity<Boolean>(HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}
