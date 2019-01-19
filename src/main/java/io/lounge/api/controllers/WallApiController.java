package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.WallApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Wall;
import io.lounge.mongo.dao.PostDAO;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class WallApiController implements WallApi {

    private static final Logger log = LoggerFactory.getLogger(WallApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public WallApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Wall> getWall(@ApiParam(value = "",required=true) @PathVariable("idUser") String idUser) {
		UserDAO userDAO = DAOUtils.getUserDAO();
		PostDAO postDAO = DAOUtils.getPostDAO();

		UserDO userDO = userDAO.getUserById(idUser);

		if (userDO != null) {

			// TODO add all wanted info
			Wall userWall = new Wall();
			userWall.setUsername(userDO.getUsername());
			userWall.setYearOfStudy("2019");

			// add first post TODO change
			if (postDAO.getPostsOfUser(userDO) != null)
				userWall.addPostsItem(postDAO.getPostsOfUser(userDO).get(0).toPost());

			return new ResponseEntity<Wall>(userWall, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Wall>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


    }

}
