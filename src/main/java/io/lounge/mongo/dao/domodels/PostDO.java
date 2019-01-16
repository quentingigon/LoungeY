package io.lounge.mongo.dao.domodels;

import io.lounge.models.Hashtag;
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
		PostDO postDO = new PostDO(newPost.getPost());
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
		for (Hashtag hash : post.getHashtags()) {
			if (hash != null)
				hashtagsList.add(hash.toHashtagDO());
		}

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
		p.setType(type.toString());
		p.setUserId(author.toHexString());

		// used to distinguished post and responses
		if (!responsesList.isEmpty()) {
			ArrayList<Post> responses = new ArrayList<>();
			for (PostDO resp : responsesList) {
				if (resp != null)
					responses.add(resp.toPost());
			}
			p.setResponses(responses);
		}

		ArrayList<Hashtag> hashtags = new ArrayList<>();
		for (HashtagDO hash : hashtagsList) {
			if (hash != null)
				hashtags.add(hash.toHashtag());
		}
		p.setHashtags(hashtags);

		return p;
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
}
