package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.HashtagDO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class HashtagDAO extends BasicDAO<HashtagDO, ObjectId> {

	public HashtagDAO(Datastore ds) {
		super(ds);
	}

	/**
	 * Gets the hashtag identified by the name passed in argument from the DB
	 *
	 * @param name	Seeked hashtag name
	 *
	 * @return	the hashtagDO corresponding to name
	 */
	public HashtagDO getHashtag(String name) {
		if (name != null)
			return findOne("name", name);
		else
			return null;
	}

	/**
	 * Creates a new hastag into the DB, normally not called by user interaction
	 *
	 * @param hashtag	The hashtagDO to save into the DB
	 *
	 * @return          <code>true</code> if everything went well and hashtag is saved
	 * 	  				<code>false</code> if there is a connection/server error.
	 */
	public boolean createHashtag(HashtagDO hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(hashtag);

		return getCollection().save(tmp).wasAcknowledged();
	}

	/**
	 * Creates a PostDAO object to call the same function inside it, that returns all the posts containing the hashtag
	 *
	 * @param hashtag	The hashtagDO to use to filter posts into the DB
	 *
	 * @return          The filtered list of PostDO
	 */
	public List<PostDO> getPostsWithHashtag(HashtagDO hashtag) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		PostDAO postDAO = new PostDAO(conn.getDatastore());
		return postDAO.getPostsWithHashtag(hashtag) ;
	}

	/**
	 *	Saves changes made over an hashtag by getting old datas and replacing it by writing HashtagDO "hashtag" object
	 *	over into the DB.
	 *
	 * @param hashtag	HashtagDO representing the modified hashtag
	 *
	 * @return          <code>true</code> if everything went well and hashtag is saved
	 *    				<code>false</code> if there is a connection/server error.
	 */
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

	/**
	 * Adds the post containing each of the hashtags passed to the function inside each hashtag's list of post
	 *
	 * @param hashtags		The hashtags contained in the post
	 * @param postId		The post that contains the hashtags
	 */
	public void addPostIdToHashtagsLists(List<String> hashtags, String postId) {

		if (hashtags != null) {
			for (String hashName : hashtags) {
				if (hashName != null) {
					HashtagDO realHash = getHashtag(hashName);
					if (realHash != null) {
						realHash.addToPostsList(postId);
						updateHashtag(realHash);
					}
				}
			}
		}
	}
}
