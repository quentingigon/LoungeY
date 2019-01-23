package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.LogoutApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.BlackListDAO;
import io.lounge.mongo.dao.domodels.BlackListDO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class LogoutApiController implements LogoutApi {

    private static final Logger log = LoggerFactory.getLogger(LogoutApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LogoutApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

	@CrossOrigin
    public ResponseEntity<Boolean> logout(@ApiParam(value = "The username of user to log out" ,required=true )  @RequestParam String userLogout) {

        BlackListDAO blackListDAO = DAOUtils.getBlackListDAO();

		BlackListDO blackList = blackListDAO.getBlackList();

		if (blackList != null) {
			// add token to blacklist, so users who want to login again have
			// to ask for a new token
			blackList.addToBlackList(userLogout, request.getHeader("Authorization"));
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
    	return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
