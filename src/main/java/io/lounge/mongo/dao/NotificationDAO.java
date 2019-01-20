package io.lounge.mongo.dao;

import io.lounge.mongo.dao.domodels.NotificationDO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class NotificationDAO extends BasicDAO<NotificationDO, ObjectId> {

	public NotificationDAO(Datastore ds) {
		super(ds);
	}
}
