package io.lounge.mongo.dao.domodels;

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

	public PostDO(String text, String date, PostType type, ObjectId author, ArrayList<HashtagDO> hashtagsList) {
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
