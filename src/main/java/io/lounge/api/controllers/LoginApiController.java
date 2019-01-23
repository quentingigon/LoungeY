package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.LoginApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.LoginInfo;
import io.lounge.models.UserLogin;
import io.lounge.mongo.dao.BlackListDAO;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.domodels.UserDO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private void getEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

	@CrossOrigin
    public ResponseEntity<LoginInfo> login(@ApiParam(value = "The user who wants to log in" ,required=true )  @Valid @RequestBody UserLogin user) {
		UserDAO userDAO = DAOUtils.getUserDAO();
		BlackListDAO blackListDAO = DAOUtils.getBlackListDAO();

		UserDO userDO = userDAO.getUser(user.getUsername());

		if (userDO != null) {

			getEncoder();
			if (bCryptPasswordEncoder.matches(user.getPassword(), userDO.getPassword())) {
				// user is logged in and token is in the header

				// as user just got a new token, we remove the user from the blacklist
				// blackListDAO.removeFromBlackList(user.getUsername());
				// TODO not working. not essential but would be good to have

				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setUsername(userDO.getUsername());
				return new ResponseEntity<LoginInfo>(loginInfo, HttpStatus.OK);
			}
		}
		else {
			// explication -> on veut pas donner d'info avec des codes d'erreur trop spécifiques
			return new ResponseEntity<LoginInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<LoginInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
