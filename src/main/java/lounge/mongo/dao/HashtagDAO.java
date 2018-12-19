package lounge.mongo.dao;

import lounge.models.Hashtag;
import lounge.models.Post;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class HashtagDAO extends BasicDAO<Hashtag, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}

	public Hashtag getHashtag(String name) {
		// TODO
		return null;
	}

	public boolean addHashtag(Hashtag hashtag) {
		// TODO
		return false;
	}

	public List<Post> getPostsWithHashtag(Hashtag hashtag) {
		// TODO
		return null;
	}
}
