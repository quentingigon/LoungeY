package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.BlackListDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class BlackListDAO extends BasicDAO<BlackListDO, ObjectId> {

	public BlackListDAO(Datastore ds) {
		super(ds);
	}

	public boolean createBlackList() {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(new BlackListDO());

		return getCollection().save(tmp).wasAcknowledged();
	}

	public BlackListDO getBlackList() {
		return find().get();
	}

	public void update(BlackListDO list) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject oldBlackList = conn.getMorphia().toDBObject(getBlackList());
		DBObject newBlackList = conn.getMorphia().toDBObject(list);

		getCollection().update(oldBlackList, newBlackList);
	}

	public void removeFromBlackList(String username) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		BlackListDO blackList = getBlackList();

		blackList.removeFromBlackList(username);
		update(blackList);
	}
}
