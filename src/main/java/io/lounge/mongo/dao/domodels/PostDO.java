package io.lounge.mongo.dao.domodels;

import io.lounge.models.Post;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class PostDO extends BasicDO {

	private String text;
	private String date;
	private String type;
	private ObjectId author;
	private boolean isCorrectAnswer;
	private boolean isPublic;
	private ObjectId parentId;

	private ArrayList<PostDO> responsesList;
	private ArrayList<HashtagDO> hashtagsList;

	public PostDO() {}

	public PostDO(String text, String date, String type, ObjectId author) {
		this.setId(new ObjectId());

		this.text = text;
		this.setDate(date);
		this.type = type;
		this.author = author;
		this.hashtagsList = getHashtagsFromText(text);
		this.responsesList = new ArrayList<>();
	}

	public PostDO(String text, String date, String type, String author, boolean isPublic, ArrayList<HashtagDO> hashtagsList) {
		this.text = text;
		this.setDate(date);
		this.type = type;
		this.author = new ObjectId(author);
		this.isPublic = isPublic;
		this.hashtagsList = hashtagsList;
	}

	public Post toPost() {
		Post p = new Post();
		p.setId(getId().toHexString());
		p.setText(text);
		p.setDate(date);
		p.setIsCorrectAnswer(isCorrectAnswer);
		p.setType(type != null ? type.toString() : "");
		p.setUsername(author != null ? author.toHexString() : "");
		p.setIsPublic(isPublic);

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean aPublic) {
		isPublic = aPublic;
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