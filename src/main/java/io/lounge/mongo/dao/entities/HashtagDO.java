package io.lounge.mongo.dao.entities;

import io.lounge.models.Hashtag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;

@Entity
public class HashtagDO extends BasicDO {

	private String name;
	private ArrayList<ObjectId> postsContainingHashtag;

	public HashtagDO() {}

	public HashtagDO(String name) {
		this.name = name;

		this.postsContainingHashtag = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ObjectId> getPostsContainingHashtag() {
		return postsContainingHashtag;
	}

	public void setPostsContainingHashtag(ArrayList<ObjectId> postsContainingHashtag) {
		this.postsContainingHashtag = postsContainingHashtag;
	}

	public void addToPostsList(String postId) {

		if (postsContainingHashtag == null) {
			postsContainingHashtag = new ArrayList<>();
		}

		if (postId != null)
			postsContainingHashtag.add(new ObjectId(postId));
	}

	public Hashtag toHashtag() {
		Hashtag hash = new Hashtag();
		hash.setName(name);

		ArrayList<String> posts = new ArrayList<>();

		for (ObjectId o : getPostsContainingHashtag()) {
			if (o != null)
				posts.add(o.toHexString());
		}
		hash.setPostsWithThisHashtag(posts);

		return hash;
	}
}
