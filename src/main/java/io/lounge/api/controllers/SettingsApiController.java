package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.SettingsApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.NewUser;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.entities.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class SettingsApiController implements SettingsApi {

    private static final Logger log = LoggerFactory.getLogger(SettingsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SettingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Boolean> settings(@NotNull @Valid @RequestBody NewUser userChanges) {
        UserDAO userDAO = DAOUtils.getUserDAO();

		UserDO userDO = userDAO.getUser(userChanges.getUsername());

		if (userDO != null) {
			// apply changes and update user
			if (userDAO.updateUser(userChanges.applySettingsChangesAndReturnNewUserDO(userDO))) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
