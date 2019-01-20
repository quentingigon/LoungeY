package io.lounge.api.controllers;

import io.lounge.api.interfaces.FileApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.NotificationDAO;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.domodels.NotificationDO;
import io.lounge.mongo.dao.domodels.NotificationType;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.services.FileStorageService;
import io.swagger.annotations.ApiParam;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static io.lounge.configuration.LoungeConstants.NEWPIC_NOTIF;

@RestController
public class FileController implements FileApi {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/upload/{username}/image")
	public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file,
											  @ApiParam(value = "", required = true) @PathVariable("username") String username) {
		UserDAO userDAO = DAOUtils.getUserDAO();
		NotificationDAO notifDAO = DAOUtils.getNotificationDAO();

		UserDO userDO = userDAO.getUser(username);

		if (userDO != null) {

			if (file.getOriginalFilename().endsWith("_pic")) {
				// send notifs to friends
				for (ObjectId friendId : userDO.getFriendsList()) {
					if (friendId != null) {
						NotificationDO newNotifDO = new NotificationDO(NEWPIC_NOTIF, userDO.getUsername(),
							userDAO.getUser(friendId).getUsername(), String.valueOf(NotificationType.NEWPIC));
						notifDAO.addNotification(newNotifDO);
						userDAO.getUser(friendId).addNotification(newNotifDO);
					}
				}
			}

			String fileName;
			try {
				fileName = fileStorageService.storeFile(file);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();

			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
