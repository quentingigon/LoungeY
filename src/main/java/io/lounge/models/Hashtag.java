package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.entities.HashtagDO;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * HashtagDO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class Hashtag   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("postsWithThisHashtag")
  @Valid
  private List<String> postsWithThisHashtag = null;

  public Hashtag name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Hashtag postsWithThisHashtag(List<String> postsWithThisHashtag) {
    this.postsWithThisHashtag = postsWithThisHashtag;
    return this;
  }

  public Hashtag addPostsWithThisHashtagItem(String postsWithThisHashtagItem) {
    if (this.postsWithThisHashtag == null) {
      this.postsWithThisHashtag = new ArrayList<String>();
    }
    this.postsWithThisHashtag.add(postsWithThisHashtagItem);
    return this;
  }

  /**
   * Get postsWithThisHashtag
   * @return postsWithThisHashtag
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<String> getPostsWithThisHashtag() {
    return postsWithThisHashtag;
  }

  public void setPostsWithThisHashtag(List<String> postsWithThisHashtag) {
    this.postsWithThisHashtag = postsWithThisHashtag;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hashtag hashtag = (Hashtag) o;
    return Objects.equals(this.name, hashtag.name) &&
        Objects.equals(this.postsWithThisHashtag, hashtag.postsWithThisHashtag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, postsWithThisHashtag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HashtagDO {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    postsWithThisHashtag: ").append(toIndentedString(postsWithThisHashtag)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public HashtagDO toHashtagDO() {
  	HashtagDO hashtagDO = new HashtagDO(name);

  	ArrayList<ObjectId> posts = new ArrayList<>();

  	if (postsWithThisHashtag != null) {
		for (String s : postsWithThisHashtag) {
			posts.add(new ObjectId(s));
		}
	}

	hashtagDO.setPostsContainingHashtag(posts);
	return hashtagDO;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

