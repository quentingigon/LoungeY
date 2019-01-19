package io.lounge.mongo.dao.domodels;

import io.lounge.models.NewPost;
import io.lounge.models.Post;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class PostDO extends BasicDO {

	private String text;
	private String date;
	private PostType type;
	private ObjectId author;
	private boolean isCorrectAnswer;
	private ObjectId parentId;

	private ArrayList<PostDO> responsesList;
	private ArrayList<HashtagDO> hashtagsList;

	public PostDO() {}

	public PostDO(PostDO postDO) {
		this.text = postDO.text;
		this.date = postDO.date;
		this.type = postDO.type;
		this.author = postDO.author;
		this.isCorrectAnswer = postDO.isCorrectAnswer;
		this.responsesList = postDO.responsesList;
		this.hashtagsList = postDO.hashtagsList;
	}

	public PostDO(NewPost newPost) {
		new PostDO(new PostDO(newPost.getPost()));

	}

	public PostDO(String text, String date, PostType type, ObjectId author) {
		this.setId(new ObjectId());

		this.text = text;
		this.date = date;
		this.type = type;
		this.author = author;
		this.hashtagsList = getHashtagsFromText(text);
		this.responsesList = new ArrayList<>();
	}

	public PostDO(Post post) {
		text = post.getText();
		date = post.getTimestamp();
		type = PostType.valueOf(post.getType());
		author = new ObjectId(post.getUserId());
		isCorrectAnswer = post.isIsCorrectAnswer();

		responsesList = new ArrayList<>();
		for (Post resp : post.getResponses()) {
			if (resp != null)
				responsesList.add(resp.toPostDO());
		}

		hashtagsList = new ArrayList<>();

	}

	public PostDO(String text, String date, PostType type, String author, ArrayList<HashtagDO> hashtagsList) {
		this.text = text;
		this.date = date;
		this.type = type;
		this.author = new ObjectId(author);
		this.hashtagsList = hashtagsList;
	}

	public Post toPost() {
		Post p = new Post();
		p.setId(getId().toHexString());
		p.setText(text);
		p.setTimestamp(date);
		p.setIsCorrectAnswer(isCorrectAnswer);
		p.setType(type != null ? type.toString() : "");
		p.setUserId(author != null ? author.toHexString() : "");

		// used to distinguished post and responses
		if (responsesList != null && !responsesList.isEmpty()) {
			ArrayList<Post> responses = new ArrayList<>();
			for (PostDO resp : responsesList) {
				if (resp != null)
					responses.add(resp.toPost());
			}
			p.setResponses(responses);
		}

		ArrayList<String> hashtags = new ArrayList<>();

		if (hashtagsList != null) {
			for (HashtagDO hash : hashtagsList) {
				if (hash != null)
					hashtags.add(hash.getName());
			}
		}
		p.setHashtags(hashtags);

		return p;
	}

	private ArrayList<HashtagDO> getHashtagsFromText(String text){
		return new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public ObjectId getAuthor() {
		return author;
	}

	public void setAuthor(ObjectId author) {
		this.author = author;
	}

	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		isCorrectAnswer = correctAnswer;
	}

	public ArrayList<PostDO> getResponsesList() {
		return responsesList;
	}

	public void setResponsesList(ArrayList<PostDO> responsesList) {
		this.responsesList = responsesList;
	}

	public ArrayList<HashtagDO> getHashtagsList() {
		return hashtagsList;
	}

	public void setHashtagsList(ArrayList<HashtagDO> hashtagsList) {
		this.hashtagsList = hashtagsList;
	}

	public ObjectId getParentId(){
		return this.parentId;
	}

	public void setParentId(ObjectId id){
		if(this.parentId==null)
			this.parentId = id;
	}

	public void addComment(PostDO comment){
		if(this.responsesList == null)
			this.responsesList = new ArrayList<>();

		comment.setParentId(this.getId());
		this.responsesList.add(comment);
	}

	public void delComment(ObjectId id){
		for(PostDO p : this.responsesList){
			if(p.getId().equals(id)){
				System.out.println("Found comment to del");
				this.responsesList.remove(p);
				return;
			}
		}

		System.out.println("Comment not found");
	}
}