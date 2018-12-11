package lounge.models;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Post extends BasicDO {

	private String text;
	private String date;
	private PostType type;
	private ObjectId author;
	private boolean isCorrectAnswer;

	private ArrayList<Post> responsesList;
	private ArrayList<Hashtag> hashtagsList;

	public Post(String text, String date, PostType type, ObjectId author, ArrayList<Hashtag> hashtagsList) {
		this.text = text;
		this.date = date;
		this.type = type;
		this.author = author;
		this.hashtagsList = hashtagsList;
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

	public ArrayList<Post> getResponsesList() {
		return responsesList;
	}

	public void setResponsesList(ArrayList<Post> responsesList) {
		this.responsesList = responsesList;
	}

	public ArrayList<Hashtag> getHashtagsList() {
		return hashtagsList;
	}

	public void setHashtagsList(ArrayList<Hashtag> hashtagsList) {
		this.hashtagsList = hashtagsList;
	}
}
