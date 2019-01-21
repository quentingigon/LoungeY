package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.RegisterApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.configuration.LoungeConstants;
import io.lounge.models.NewUser;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.entities.UserDO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class RegisterApiController implements RegisterApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private void getEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

    @org.springframework.beans.factory.annotation.Autowired
    public RegisterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> register(@ApiParam(value = "The new user to register" ,required=true )  @Valid @RequestBody NewUser newUser) {
		UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO newUserDO = userDAO.getUser(newUser.getUsername());

		// ensure username is unique and email is from heig-vd. (in an indeal world we would have the emails
		// of all the students to be absolutely sure they are from the heig)
		// also ensure that year of study is between 1 and 7 and that the orientation exists.
		if (newUserDO == null
			&& newUser.getEmail().endsWith("@heig-vd.ch")
			&& (Integer.valueOf(newUser.getYear()) >= 1 && Integer.valueOf(newUser.getYear()) <= 7)
			&& LoungeConstants.ORIENTATIONS.contains(newUser.getOrientation())) {

			// encode password
			getEncoder();
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

			if (userDAO.addUser(newUser.toUserDO())) {
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
}
