package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.WallApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Post;
import io.lounge.models.Wall;
import io.lounge.mongo.dao.PostDAO;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.entities.PostDO;
import io.lounge.mongo.dao.entities.UserDO;
import io.lounge.services.FileStorageService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class WallApiController implements WallApi {

    private static final Logger log = LoggerFactory.getLogger(WallApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

	@Autowired
	private FileStorageService fileStorageService;

    @org.springframework.beans.factory.annotation.Autowired
    public WallApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Wall> getWall(@ApiParam(value = "",required=true) @RequestParam("currentUsername") String currentUsername,
										@ApiParam(value = "", required = true) @PathVariable("userWatched") String userWatched) {
		UserDAO userDAO = DAOUtils.getUserDAO();
		PostDAO postDAO = DAOUtils.getPostDAO();

		UserDO currentUserDO = userDAO.getUser(currentUsername);
		UserDO userWatchedDO = userDAO.getUser(userWatched);

		if (currentUserDO != null && userWatchedDO != null) {
			// TODO add all wanted info
			Wall userWall = new Wall();
			userWall.setUsername(userWatchedDO.getUsername());
			userWall.setName(userWatchedDO.getName());

			// profile pic URL
			Resource profilePicResource = null;
			try {
				// try to get profile pic of user
				profilePicResource = fileStorageService.loadFileAsResource(userWatchedDO.getUsername() + "_pic");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (profilePicResource != null) {
				try {
					// add profile pic to wall
					userWall.setProfilePicURL(profilePicResource.getURL().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// cover pic URL
			Resource coverPicResource = null;
			try {
				// try to get cover pic of user
				coverPicResource = fileStorageService.loadFileAsResource(userWatchedDO.getUsername() + "_cover");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (coverPicResource != null) {
				try {
					// add cover pic to wall
					userWall.setCoverURL(coverPicResource.getURL().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			ArrayList<Post> posts = new ArrayList<>();

			// check if users are friends to limit info sent if they are not
			if (userDAO.areFriends(currentUsername, userWatched)) {
				userWall.setYearOfStudy(userWatchedDO.getYearOfStudy());
				userWall.setFavBeer(userWatchedDO.getFavBeer());
				userWall.setDjRank(userWatchedDO.getDjRank());
				userWall.setOrientation(userWatchedDO.getOrientation());

				// search through all user's posts
				for (PostDO postDO : postDAO.getPostsOfUser(userWatchedDO, 10)) {
					if (postDO != null) {
						posts.add(postDO.toPost());
					}
				}
			}
			else {
				// fill displayed posts with public posts of user
				for (PostDO postDO : postDAO.getPublicPostsOfUser(userWatchedDO, 10)) {
					if (postDO != null) {
						posts.add(postDO.toPost());
					}
				}
			}
			userWall.setPosts(posts);

			return new ResponseEntity<Wall>(userWall, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Wall>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
