package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.mongo.dao.domodels.HashtagDO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.lounge.mongo.dao.domodels.PostType;
import io.lounge.mongo.dao.domodels.UserDO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.ArrayList;
import java.util.List;

public class PostDAO extends BasicDAO<PostDO, ObjectId> {

	public PostDAO(Datastore ds) {
		super(ds);
	}

	public boolean addPost(PostDO post) {


		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(post);

		return  getCollection().save(tmp).wasAcknowledged();
	}

	public boolean addComment(PostDO comment, PostDO parent) {
		comment.setAuthor(parent.getAuthor());
		comment.setType(PostType.COMMENT);

		return  addPost(comment) ;
	}

	public List<PostDO> getPostsOfUser(UserDO user) {
		// TODO fix this, it is way too slow !

		List<PostDO> postsofUser = new ArrayList();
		List<PostDO> allpost = new ArrayList();
		allpost = find().asList();
		for(PostDO post: allpost){
			if(post.getAuthor()==user.getId()){
				postsofUser.add(post);
			}
		}
		return postsofUser;
	}

	public List<PostDO> getPostsWithHashtag(HashtagDO hashtag) {
		List<PostDO> postsWithHastag = new ArrayList();
		List<PostDO> allposthastag = new ArrayList();
		allposthastag = find().asList();
		for(PostDO post: allposthastag ){
			for (HashtagDO hashtagtmp: post.getHashtagsList()){
				if (hashtagtmp.equals(hashtag)){
					postsWithHastag.add(post);
				}
			}
		}

		return postsWithHastag;
	}

	public List<PostDO> getPostsWithHashtags(List<HashtagDO> hashtags) {

		List<PostDO> postsWithHastag = new ArrayList();
		List<PostDO> allpost= new ArrayList();
		allpost= find().asList();
		for(PostDO post: allpost ){
			if(post.getHashtagsList().equals(hashtags))
				postsWithHastag.add(post);

		}
		return postsWithHastag;
	}

	public List<PostDO> searchForPosts(String searchString) {
		// TODO
		// searchString contains all terms used for the search, separated by a blank space.
		// 2 things to do here:
		//	- look if there is an hashtag at the beginning of each word and restrict the search to those courses
		//	- for the other terms, restrict by looking at the content of the posts and searching for those words
		return null;
	}

	public List<PostDO> getWallPosts(UserDO user) {
		// TODO
		List<PostDO> postsUser = new ArrayList();
		List<PostDO> allpost= new ArrayList();
		allpost = find().asList();
		for(PostDO post: allpost){
			if(post.getAuthor().equals(user.getId())){
				postsUser.add(post);
			}
		}
		// returns the posts visible by a visitor of your page
		return postsUser;
	}

	public List<PostDO> getLoungeFeed(UserDO user) {
		// TODO
		return null;
	}

	public List<PostDO> getLoungeFeedQuestionsOnly(UserDO user) {

		List<PostDO> postsUserQuestions = new ArrayList();
		List<PostDO> allpost= new ArrayList();
		allpost = find().asList();
		for(PostDO post: allpost){
			if(post.getType().equals(PostType.QUESTION)&&post.getAuthor().equals(user.getId())){
				postsUserQuestions.add(post);
			}
		}
		return postsUserQuestions;
	}

	public List<PostDO> getLoungeFeedFriendsPostsOnly(UserDO user) {
		//


		return null;
	}
}
