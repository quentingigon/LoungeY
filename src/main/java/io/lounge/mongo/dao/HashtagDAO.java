package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.entities.HashtagDO;
import io.lounge.mongo.dao.entities.PostDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class HashtagDAO extends BasicDAO<HashtagDO, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}

	public HashtagDO getHashtag(String name) {
		return findOne("name", name);
	}

	public boolean createHashtag(HashtagDO hashtag) {
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

	public boolean updateHashtag(HashtagDO hashtag){
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		HashtagDO tmpOldHastag = getHashtag(hashtag.getName());

		if(tmpOldHastag!=null){
			DBObject newHashtag = conn.getMorphia().toDBObject(hashtag);
			DBObject oldHashtag = conn.getMorphia().toDBObject(tmpOldHastag);

			return getCollection().update(oldHashtag, newHashtag).wasAcknowledged();
		}

		System.out.println("Cannot update un-existing hashtag");
		return false;
	}

	// add post to each of the hashtag's list
	public void addPostIdToHashtagsLists(List<String> hashtags, String postId) {

		if (hashtags != null) {
			for (String hashName : hashtags) {
				if (hashName != null) {
					HashtagDO realHash = getHashtag(hashName);
					realHash.addToPostsList(postId);
					updateHashtag(realHash);
				}
			}
		}
	}
}
