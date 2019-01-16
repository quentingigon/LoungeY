package io.lounge.mongo.dao.domodels;

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
}
