package io.lounge.api.utils;

import io.lounge.mongo.dao.HashtagDAO;
import io.lounge.mongo.dao.NotificationDAO;
import io.lounge.mongo.dao.utils.MongoConnection;
import io.lounge.mongo.dao.PostDAO;
import io.lounge.mongo.dao.UserDAO;

public class DAOUtils {

	public static PostDAO getPostDAO() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		return new PostDAO(conn.getDatastore());
	}

	public static UserDAO getUserDAO() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		return new UserDAO(conn.getDatastore());
	}

	public static HashtagDAO getHashtagDAO() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		return new HashtagDAO(conn.getDatastore());
	}

	public static NotificationDAO getNotificationDAO() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		return new NotificationDAO(conn.getDatastore());
	}
}
