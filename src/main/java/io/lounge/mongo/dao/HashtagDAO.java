package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.HashtagDO;
import io.lounge.mongo.dao.domodels.PostDO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class HashtagDAO extends BasicDAO<HashtagDO, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}

	public HashtagDO getHashtag(String name) {
		return findOne(name, false);
	}

	public boolean addHashtag(HashtagDO hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(hashtag);

		return getCollection().save(tmp).wasAcknowledged();
	}

	public List<PostDO> getPostsWithHashtag(HashtagDO hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());
		return postDAO.getPostsWithHashtag(hashtag) ;
	}

	// add post to each of the hashtag's list
	public void addPostToHashtagsLists(PostDO post) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();

		for (HashtagDO hash : post.getHashtagsList()) {

			DBObject oldHashtag = conn.getMorphia().toDBObject(hash);
			hash.addToPostsList(post.getId().toHexString());
			DBObject newHashtag = conn.getMorphia().toDBObject(hash);

			getCollection().update(oldHashtag, newHashtag);
		}


	}
}
