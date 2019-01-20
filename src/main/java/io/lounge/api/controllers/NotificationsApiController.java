package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.NotificationsApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Notification;
import io.lounge.mongo.dao.NotificationDAO;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.domodels.UserDO;
import io.swagger.annotations.ApiParam;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-20T12:30:38.803Z")

@Controller
public class NotificationsApiController implements NotificationsApi {

    private static final Logger log = LoggerFactory.getLogger(NotificationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public NotificationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Notification>> getNotifications(@ApiParam(value = "",required=true) @PathVariable("username") String username) {
		UserDAO userDAO = DAOUtils.getUserDAO();
		NotificationDAO notificationDAO = DAOUtils.getNotificationDAO();

		UserDO userDO = userDAO.getUser(username);

		if (userDO != null) {

			ArrayList<Notification> notifications = new ArrayList<>();

			if (!userDO.getNotifications().isEmpty()) {
				for (ObjectId notifId : userDO.getNotifications()) {
					if (notifId != null)
						notifications.add(notificationDAO.get(notifId).toNotification());
				}
				// TODO maybe its a bad plan
				// when we gave notifs, they are not new anymore so we delete them
				userDO.setNotifications(new ArrayList<>());
				userDAO.updateUser(userDO);

				return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<Notification>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			return new ResponseEntity<List<Notification>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
