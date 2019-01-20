package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.NotificationDO;
import io.lounge.mongo.dao.domodels.NotificationType;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class NotificationDAO extends BasicDAO<NotificationDO, ObjectId> {

	public NotificationDAO(Datastore ds) {
		super(ds);
	}

	public boolean addNotification(NotificationDO notif) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(notif);

		return getCollection().save(tmp).wasAcknowledged();
	}

	public List<NotificationDO> getLastTenNotificationsOfUser(UserDO user) {
		Query<NotificationDO> findQuery = createQuery().field("toUser").equal(user.getUsername());

		int nbQueryResults = (int) findQuery.count();

		return find(findQuery).asList().subList(0, ((10 <= nbQueryResults) ? 10 : nbQueryResults));
	}

	// TODO bien vérifier que ça fonctionne
	public NotificationDO getLastFriendInviteBetweenUsers(String fromUser, String toUser) {
		Query<NotificationDO> findQuery = createQuery()
			.field("fromUser").equal(fromUser)
			.field("toUser").equal(toUser)
			.field("type").equal(String.valueOf(NotificationType.FRIENDINVITE))
			.limit(1);

		int nbQueryResults = (int) findQuery.count();

		return find(findQuery).get();
	}
}
