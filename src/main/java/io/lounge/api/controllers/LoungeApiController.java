package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.LoungeApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Post;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class LoungeApiController implements LoungeApi {

    private static final Logger log = LoggerFactory.getLogger(LoungeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LoungeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Post>> getFriendsPosts(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
        PostDAO postDAO = DAOUtils.getPostDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO user = userDAO.getUser(username);

		if (user != null) {
			ArrayList<Post> friendsPosts = new ArrayList<>();

			for (PostDO postDO : postDAO.getLoungeFeedFriendsPostsOnly(user)) {
				if (postDO != null)
					friendsPosts.add(postDO.toPost());
			}
			return new ResponseEntity<List<Post>>(friendsPosts, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Post>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<List<Post>> getLounge(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO user = userDAO.getUser(username);

		// TODO define what the "feed" of a user is

        return new ResponseEntity<List<Post>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Post>> getLoungeQuestions(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO user = userDAO.getUser(username);

		if (user != null) {
			ArrayList<Post> questions = new ArrayList<>();

			for (PostDO postDO : postDAO.getLoungeFeedQuestionsOnly(user)) {
				if (postDO != null)
					questions.add(postDO.toPost());
			}
			return new ResponseEntity<List<Post>>(questions, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Post>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
