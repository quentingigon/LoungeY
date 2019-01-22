package io.lounge.mongo.dao;

import com.mongodb.DBObject;
import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.domodels.HashtagDO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.lounge.mongo.dao.domodels.PostType;
import io.lounge.mongo.dao.domodels.UserDO;
import io.lounge.mongo.dao.utils.MongoConnection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
public class PostDAO extends BasicDAO<PostDO, ObjectId> {

	DateFormat dateFormat;

	public PostDAO(Datastore ds) {
		super(ds);
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * Converts the passed post object into a DBObject and saves it in the DB.
	 *
	 * @param post 		PostDO object to save inside the DB
	 *
	 * @return			<code>true</code> if everything went well and user is saved
	 * 					<code>false</code> if username already is taken or if there is a connection/server error.
	 *
	 */
	public boolean addPost(PostDO post) {
		MongoConnection conn = MongoConnection.getInstance();

		post.setDate(dateFormat.format(new Date()));
		conn.init();
		DBObject tmp = conn.getMorphia().toDBObject(post);

		return getCollection().save(tmp).wasAcknowledged();
	}

	/**
	 * Converts the passed post object into a DBObject and saves it in the DB.
	 * Then returns the last post posted by user. (Should be newPost's ID)
	 *
	 * @param newPost	PostDO object to save inside the DB
	 * @param user		The user from whom we want to get last post's ID
	 *
	 * @return			ID of last of user's posts
	 *
	 */
	public String addPost(PostDO newPost, UserDO user) {
		addPost(newPost);

		// return id of last post of user => newPost
		return getPostsOfUser(user, 1).get(0).getId().toHexString();
	}

	/**
	 * Returns PostDO object from DB which has the ID pId
	 *
	 * @param pId searched post's ID
	 *
	 * @return PostDO object representing the wanted post, or null if not found
	 */
	public PostDO getPost(ObjectId pId) {
		return findOne("_id", pId);
	}

	/**
	 * Same as getPost(ObjectID) but with a string arg instead
	 *
	 * @param sPId  Searched post's ID (String version)
	 *
	 * @return PostDO object representing the wanted post, or null if not found
	 */
	public PostDO getPostById(String sPId){
		return getPost(new ObjectId(sPId));
	}

	/**
	 *  Checks if given post already exists in DB via its ObjectId
	 *
	 * @param pId 			searched post's ID
	 *
	 * @return				<code>true</code> if post exists
	 * 						<code>false</code> if post doesn't
	 *
	 */
	public boolean postExists(ObjectId pId){
		return (getPost(pId) != null);
	}

	/**
	 *  Saves changes made over a post by getting old datas and replacing it by writing PostDO "p" object over.
	 *
	 * @param p 		PostDO representing the modified post
	 *
	 * @return          <code>true</code> if everything went well and post is saved
	 * 	   				<code>false</code> if there is a connection/server error.
	 *
	 */
	public boolean updatePost(PostDO p) {
		if(postExists(p.getId())) {
			MongoConnection conn = MongoConnection.getInstance();
			conn.init();
			DBObject newPost = conn.getMorphia().toDBObject(p);
			DBObject oldPost = conn.getMorphia().toDBObject(getPost(p.getId()));
			return getCollection().update(oldPost, newPost).wasAcknowledged();
		} else {
			System.out.println("Cannot update unexisting post");
			return false;
		}

	}

	/**
	 *  Add post "comment" as comment for the "parent" post, embedded inside the parent post in DB
	 *
	 * @param comment 		PostDO representing the new comment
	 * @param parent   		PostDO representing the post to be commented
	 *
	 * @return          <code>true</code> if everything went well and comment is saved
	 * 					<code>false</code> if comment has no Author or if there is a connection/server error.
	 */
	public boolean addComment(PostDO comment, PostDO parent) {

		if(comment.getAuthor()!=null){
			if(!comment.getType().equals(String.valueOf(PostType.COMMENT)))
				comment.setType(String.valueOf(PostType.COMMENT));
			comment.setDate( dateFormat.format(new Date()));
			parent.addComment(comment);
			return  updatePost(parent);
		}

		return false;
	}

	/**
	 * Removes the comment with ObjectID "cId" from the parent post identified with "parentId"
	 *
	 * @param cId		the comment's ID that needs to be deleted
	 * @param parentId	the comment's parent's ID
	 *
	 * @return          <code>true</code> if everything went well and comment is saved
	 * 					<code>false</code> if there is a connection/server error.
	 */
	public boolean remComment(ObjectId cId, ObjectId parentId){
		PostDO parent = getPost(parentId);
		parent.delComment(cId);

		updatePost(parent);
		return false;
	}

	/**
	 * Gets n public posts from a user, sorted by publication date, using a Morphia Query inside the DB
	 *
	 * @param user		The user we're interested in
	 * @param nbPosts	The number of posts wanted
	 *
	 * @return			The list of n posts from the user
	 */
	public List<PostDO> getPublicPostsOfUser(UserDO user, int nbPosts) {
		Query<PostDO> findQuery = createQuery()
			.field("author").equal(user.getId())
			.field("isPublic").equal(true);

		int nbQueryResults = (int) findQuery.count();

		return find(findQuery).asList().subList(0, ((nbPosts <= nbQueryResults) ? nbPosts : nbQueryResults));
	}


	/**
	 * Gets all posts from a user, using a morphia Query inside the DB
	 *
	 * @param user		The user we're interested in
	 *
	 * @return			A list of all posts from the user
	 */
	public List<PostDO> getPostsOfUser(UserDO user) {
		Query<PostDO> findQuery = createQuery().field("author").equal(user.getId());

		return find(findQuery).asList();
	}

	/**
	 * Gets n posts from the user (inside the db), sorted by date, regardless of their accessibility, using a Morphia Query.
	 *
	 * @param user		The user we're interested in
	 * @param nbPosts	The number of posts wanted
	 *
	 * @return			The list of n posts from the user
	 */
	public List<PostDO> getPostsOfUser(UserDO user, int nbPosts) {
		Query<PostDO> findQuery = createQuery().field("author").equal(user.getId());
		findQuery.order("-date");

		int nbQueryResults = (int) findQuery.count();

		return find(findQuery).asList().subList(0, ((nbPosts <= nbQueryResults)?nbPosts:nbQueryResults));
	}

	/**
	 * Gets n most recent posts existing in the db, sorted by date.
	 *
	 * @param nbPosts	The number of posts wanted
	 *
	 * @return			The list of n wanted
	 */
	public List<PostDO> getLoungeFeed(int nbPosts) {
		Query<PostDO> findQuery = createQuery().field("_id").exists();
		findQuery.order("-date");

		int nbQueryResults = (int) findQuery.count();

		return find(findQuery).asList().subList(0, ((nbPosts <= nbQueryResults)?nbPosts:nbQueryResults));
	}


	/**
	 * Gets all posts containing the hashtag passed by argument. Not seeking the DB.
	 *
	 * @param hashtag 	The searched hashtag
	 *
	 * @return			The list of posts containing the hashtag
	 */
	public List<PostDO> getPostsWithHashtag(HashtagDO hashtag) {
		List<ObjectId> postsId = hashtag.getPostsContainingHashtag();
		List<PostDO> postsWithHastag = new ArrayList<>();

		for(ObjectId p : postsId){
			postsWithHastag.add(getPost(p));
		}

		return postsWithHastag;
	}

	/**
	 * Gets all posts containing exactly the list of hashtag passed by argument, using a Morphia Query to query
	 * the DB.
	 *
	 * @param hashtags	A list of hastags that the posts need to contain
	 *
	 * @return			The list of posts filtered.
	 */
	public List<PostDO> getPostsWithHashtags(List<HashtagDO> hashtags) {
		Query<PostDO> findQuery = createQuery().field("hashtagsList").equal(hashtags);

		return find(findQuery).asList();
	}

	/**
	 * Looks if there is an hashtag at the beginning of each word and restrict the search to those courses
	 * For the other terms, restricts by looking at the content of the posts and searching for those words,
	 * 	using MongoDB Morphia Query
	 *
	 * @param searchString 	contains all terms used for the search, separated by a blank space.
	 *
	 * @return				The list of posts we want to find
	 */
    public List<PostDO> searchForPosts(String searchString) {
        HashtagDAO hashtagDAO = DAOUtils.getHashtagDAO();
        String[] words = searchString.split(" ");
        Query<PostDO> query = createQuery();

        // if word begin with '!', it's a hashtag (# doesnt pass through query param)
        List<String> hashtagsNames = Arrays.stream(words).filter(s -> s.contains("!"))
                .map(s -> s.substring(1))
                .collect(Collectors.toList());

        List<HashtagDO> hashtags = getRealHashtagsWithVerification(hashtagsNames);
        ArrayList<PostDO> posts = new ArrayList<>();
        List<ObjectId> postIds = new ArrayList<>();

        if (!hashtags.isEmpty()) {

            for (HashtagDO hash : hashtags) {
                if (hash != null) {
                    for (ObjectId postId : hash.getPostsContainingHashtag()) {
                        if (postId != null) {
                            posts.add(getPost(postId));
                            postIds.add(postId);
                        }
                    }
                }
            }
        }

        // we want to search only through posts we just got
        if (!postIds.isEmpty()) {
            query = query.field("_id").in(postIds);
        }

        List<String> terms = Arrays.stream(words).filter(s -> !s.contains("!")).collect(Collectors.toList());

        if (!terms.isEmpty()) {
            if (!posts.isEmpty()) {
                for (PostDO post : posts) {
                    if (post != null) {
                        postIds.add(post.getId());
                    }
                }
                query = query.search(String.join(" ", terms));
            }
        }
        return find(query).asList();
    }
    

	/**
	 *  TODO Not sure about this one, useful? goal?
	 *
	 * @param user
	 * @return
	 */
	public List<PostDO> getWallPosts(UserDO user) {
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

	/**
	 * Gets all posts inside the DB from user that are typed as "Question", using a Morphia Query
	 *
	 * @param user	The author of the desired posts
	 *
	 * @return		The list of questions
	 */
	public List<PostDO> getLoungeFeedQuestionsOnly(UserDO user) {

		Query<PostDO> findQuery = createQuery();
		findQuery.and(
				findQuery.criteria("type").equal(PostType.QUESTION),
				findQuery.criteria("author").equal(user.getId())
		);

		return find(findQuery).asList();
	}

	/**
	 *  Gets all posts inside the DB published by the user's friends, ordered by publication date and using Morphia
	 *  Query and a list of friends.
	 *
	 * @param user		The user who wants to see its friends' posts
	 *
	 * @return			he 
	 */
	public List<PostDO> getLoungeFeedFriendsPostsOnly(UserDO user) {

		UserDAO uDAO =  DAOUtils.getUserDAO();
		List<UserDO> friends = uDAO.getUserFriends(user);
		List<ObjectId> friendsIDs = new ArrayList<>();

		for(UserDO friend : friends){
			friendsIDs.add(friend.getId());
		}

		Query<PostDO> findQuery = createQuery();
		findQuery.field("author").hasAnyOf(friendsIDs).order("-date");

		return find(findQuery).asList();
	}

	/**
	 * Fill post hastags lists
	 * 
	 * @param post		The post that needs to be updates
	 * @param hashtags	A list of hastags
	 */
	public void fillHashTagsListOfPostDO(PostDO post, List<String> hashtags) {
		post.setHashtagsList(getRealHashtagsWithVerification(hashtags));
	}

	private ArrayList<HashtagDO> getRealHashtagsWithVerification(List<String> hashtagsNames) {
		HashtagDAO hashtagDAO = DAOUtils.getHashtagDAO();
		ArrayList<HashtagDO> hashtagsDO = new ArrayList<>();

		for (String name : hashtagsNames) {
			if (name != null) {
				HashtagDO hashtagDO = hashtagDAO.getHashtag(name);
				if (hashtagDO != null)
					hashtagsDO.add(hashtagDO);
			}
		}

		return hashtagsDO;
	}
}