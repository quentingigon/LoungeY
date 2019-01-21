package io.lounge.api.utils;

import io.lounge.mongo.dao.*;
import io.lounge.mongo.dao.utils.MongoConnection;

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

	public static BlackListDAO getBlackListDAO() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		return new BlackListDAO(conn.getDatastore());
	}
}
