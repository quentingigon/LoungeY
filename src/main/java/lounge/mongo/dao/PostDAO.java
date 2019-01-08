package lounge.mongo.dao;

import com.mongodb.DBObject;
import lounge.models.Hashtag;
import lounge.models.Post;
import lounge.models.PostType;
import lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostDAO extends BasicDAO<Post, ObjectId> {

	public PostDAO(Datastore ds) {
		super(ds);
	}

	public boolean addPost(Post post) {


		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(post);

		return  getCollection().save(tmp).wasAcknowledged();
	}

	public boolean addComment(Post comment, Post parent) {
		comment.setAuthor(parent.getAuthor());
		comment.setType(PostType.COMMENT);

		return  addPost(comment) ;
	}

	public List<Post> getPostsOfUser(User user) {

		List<Post> postsofUser = new ArrayList();
		List<Post> allpost = new ArrayList();
		allpost = find().asList();
		for(Post post: allpost){
			if(post.getAuthor()==user.getId()){
				postsofUser.add(post);
			}
		}
		return postsofUser;
	}

	public List<Post> getPostsWithHashtag(Hashtag hashtag) {
		List<Post> postsWithHastag = new ArrayList();
		List<Post> allposthastag = new ArrayList();
		allposthastag = find().asList();
		for(Post post: allposthastag ){
			for (Hashtag hashtagtmp: post.getHashtagsList()){
				if (hashtagtmp.equals(hashtag)){
					postsWithHastag.add(post);
				}
			}
		}

		return postsWithHastag;
	}

	public List<Post> getPostsWithHashtags(List<Hashtag> hashtags) {

		List<Post> postsWithHastag = new ArrayList();
		List<Post> allpost= new ArrayList();
		allpost= find().asList();
		for(Post post: allpost ){
			if(post.getHashtagsList().equals(hashtags))
				postsWithHastag.add(post);

		}
		return postsWithHastag;
	}

	public List<Post> searchForPosts(String searchString) {
		// TODO
		// searchString contains all terms used for the search, separated by a blank space.
		// 2 things to do here:
		//	- look if there is an hashtag at the beginning of each word and restrict the search to those courses
		//	- for the other terms, restrict by looking at the content of the posts and searching for those words
		return null;
	}

	public List<Post> getWallPosts(User user) {
		// TODO
		List<Post> postsUser = new ArrayList();
		List<Post> allpost= new ArrayList();
		allpost = find().asList();
		for(Post post: allpost){
			if(post.getAuthor().equals(user.getId())){
				postsUser.add(post);
			}
		}
		// returns the posts visible by a visitor of your page
		return postsUser;
	}

	public List<Post> getLoungeFeed(User user) {
		// TODO
		return null;
	}

	public List<Post> getLoungeFeedQuestionsOnly(User user) {

		List<Post> postsUserQuestions = new ArrayList();
		List<Post> allpost= new ArrayList();
		allpost = find().asList();
		for(Post post: allpost){
			if(post.getType().equals(PostType.QUESTION)&&post.getAuthor().equals(user.getId())){
				postsUserQuestions.add(post);
			}
		}
		return postsUserQuestions;
	}

	public List<Post> getLoungeFeedFriendsPostsOnly(User user) {
		//


		return null;
	}
}
