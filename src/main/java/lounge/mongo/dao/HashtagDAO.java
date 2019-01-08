package lounge.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lounge.models.Hashtag;
import lounge.models.Post;
import lounge.models.PostType;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.ArrayList;
import java.util.List;

public class HashtagDAO extends BasicDAO<Hashtag, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}

	public Hashtag getHashtag(String name) {
		return findOne(name, false);
	}

	public boolean addHashtag(Hashtag hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(hashtag);

		return  getCollection().save(tmp).wasAcknowledged();
	}

	public List<Post> getPostsWithHashtag(Hashtag hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());
		return postDAO.getPostsWithHashtag(hashtag) ;
	}
}
