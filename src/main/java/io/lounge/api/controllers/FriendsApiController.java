package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.FriendsApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.User;
import io.lounge.mongo.dao.UserDAO;
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
public class FriendsApiController implements FriendsApi {

    private static final Logger log = LoggerFactory.getLogger(FriendsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FriendsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> addFriend(@ApiParam(value = "",required=true) @PathVariable("username") String username,@ApiParam(value = "",required=true) @PathVariable("newFriendUsername") String newFriendUsername) {
    	UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO currentUser = userDAO.getUser(username);
		UserDO newFriend = userDAO.getUser(newFriendUsername);

		if (currentUser != null && newFriend != null) {

			if (userDAO.addFriend(currentUser, newFriend)) {
				// friend added
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}


    }

    public ResponseEntity<List<User>> getFriends(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
    	UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO currentUser = userDAO.getUser(username);

		if (currentUser != null) {
			ArrayList<User> friends = new ArrayList<>();

			for (UserDO userDO : userDAO.getUserFriends(currentUser.getUsername())) {
				if (userDO != null) {
					friends.add(userDO.toUser());
				}
			}
			return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<User>>(new ArrayList<User>(),
													HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}
