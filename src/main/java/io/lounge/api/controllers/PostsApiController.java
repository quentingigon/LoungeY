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
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ResponseEntity<Boolean> comment(@ApiParam(value = "The id of user to log out" ,required=true )  @Valid @RequestBody Comment comment) {
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

    public ResponseEntity<Post> getPost(@ApiParam(value = "",required=true) @PathVariable("postId") String postId) {
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

    public ResponseEntity<List<Post>> getUserPosts(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO user = userDAO.getUser(username);

		if (user != null) {
			List<Post> posts = new ArrayList<>();

			// TODO postDAO.getNFirstPostsOfUser(user, n)
			for (PostDO p : postDAO.getPostsOfUser(user)) {
				posts.add(p.toPost());
			}

			return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);

		}
		else {

		}

        return new ResponseEntity<List<Post>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Boolean> post(@ApiParam(value = "New post" ,required=true )  @Valid @RequestBody NewPost newPost) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		HashtagDAO hashtagDAO = DAOUtils.getHashtagDAO();

		PostDO post = newPost.getPost().toPostDO();

		if (post != null) {

			if (!newPost.getPost().getHashtags().isEmpty())
				// add our hashtags to the postDO
				postDAO.fillHashTagsList(post, newPost.getPost().getHashtags());

			// post was correclty saved
			if (postDAO.addPost(post)) {

			}
			// update hashtag's lists
			hashtagDAO.addPostToHashtagsLists(post);

			return new ResponseEntity<Boolean>(HttpStatus.OK);
		}

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

}
