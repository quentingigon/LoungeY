package lounge.models;

import javafx.geometry.Pos;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class Post extends BasicDO {

	private String text;
	private String date;
	private PostType type;
	private ObjectId author;
	private boolean isCorrectAnswer;
	private ObjectId parentId;

	private ArrayList<Post> responsesList;
	private ArrayList<Hashtag> hashtagsList;

	private Post() {}

	public Post(String text, String date, PostType type, ObjectId author) {
		this.setId(new ObjectId());

		this.text = text;
		this.date = date;
		this.type = type;
		this.author = author;
		this.hashtagsList = getHashtagsFromText(text);
		this.responsesList = new ArrayList<>();
	}

	private ArrayList<Hashtag> getHashtagsFromText(String text){
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

	public ObjectId getParentId(){
		return this.parentId;
	}

	public void setParentId(ObjectId id){
		if(this.parentId==null)
			this.parentId = id;
	}

	public void addComment(Post comment){
		if(this.responsesList == null)
			this.responsesList = new ArrayList<>();

		comment.setParentId(this.getId());
		this.responsesList.add(comment);
	}

	public void delComment(ObjectId id){
		for(Post p : this.responsesList){
			if(p.getId().equals(id)){
				System.out.println("Found comment to del");
				this.responsesList.remove(p);
				return;
			}
		}

		System.out.println("Comment not found");
	}
}
