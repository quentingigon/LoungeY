package lounge.mongo.dao;

import lounge.models.Hashtag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class HashtagDAO extends BasicDAO<Hashtag, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}
}
