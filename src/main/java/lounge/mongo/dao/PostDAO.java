package lounge.mongo.dao;

import lounge.models.Hashtag;
import lounge.models.Post;
import lounge.models.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

public class PostDAO extends BasicDAO<Post, ObjectId> {

	public PostDAO(Datastore ds) {
		super(ds);
	}

	public boolean addPost(Post post) {
		// TODO
		return false;
	}

	public boolean addComment(Post comment, Post parent) {
		// TODO
		return false;
	}

	public List<Post> getPostsOfUser(User user) {
		// TODO
		return null;
	}

	public List<Post> getPostsWithHashtag(Hashtag hashtag) {
		// TODO
		return null;
	}

	public List<Post> getPostsWithHashtags(List<Hashtag> hashtags) {
		// TODO
		return null;
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
		// returns the posts visible by a visitor of your page
		return null;
	}

	public List<Post> getLoungeFeed(User user) {
		// TODO
		return null;
	}

	public List<Post> getLoungeFeedQuestionsOnly(User user) {
		// TODO
		return null;
	}

	public List<Post> getLoungeFeedFriendsPostsOnly(User user) {
		// TODO
		return null;
	}
}
