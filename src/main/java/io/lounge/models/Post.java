package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.domodels.HashtagDO;
import io.lounge.mongo.dao.domodels.PostDO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PostDO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class Post   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("isCorrectAnswer")
  private Boolean isCorrectAnswer = null;

  @JsonProperty("isPublic")
  private Boolean isPublic = null;

  @JsonProperty("responses")
  @Valid
  private List<Post> responses = null;

  @JsonProperty("hashtags")
  @Valid
  private List<String> hashtags = null;


  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Post id(String id) {
    this.id = id;
    return this;
  }

  /**
   * date of post
   * @return date
  **/
  @ApiModelProperty(value = "date of post")

  @Valid

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Post date(String date) {
    this.date = date;
    return this;
  }

  /**
   * username
   * @return username
  **/
  @ApiModelProperty(value = "author")

  @Valid

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Post username(String username) {
    this.username = username;
    return this;
  }

  /**
   * userId
   * @return userId
   **/
  @ApiModelProperty(value = "author")

  @Valid

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Post userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get text
   * @return text
  **/
  @ApiModelProperty(value = "")


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Post text(String text) {
    this.text = text;
    return this;
  }

  /**
   * 0 = post, 1 = question
   * @return type
  **/
  @ApiModelProperty(value = "0 = post, 1 = question")

  @Valid

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Post type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get isCorrectAnswer
   * @return isCorrectAnswer
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsCorrectAnswer() {
    return isCorrectAnswer;
  }

  public void setIsCorrectAnswer(Boolean isCorrectAnswer) {
    this.isCorrectAnswer = isCorrectAnswer;
  }

  public Post isCorrectAnswer(Boolean isCorrectAnswer) {
    this.isCorrectAnswer = isCorrectAnswer;
    return this;
  }

  /**
   * Get isPublic
   * @return isPublic
   **/
  @ApiModelProperty(value = "")


  public Boolean isIsPublic() {
    return isPublic;
  }

  public void setIsPublic(Boolean isPublic) {
    this.isPublic = isPublic;
  }

  public Post isPublic(Boolean isPublic) {
    this.isPublic = isPublic;
    return this;
  }

  public Post responses(List<Post> responses) {
    this.responses = responses;
    return this;
  }

  public Post addResponsesItem(Post responsesItem) {
    if (this.responses == null) {
      this.responses = new ArrayList<Post>();
    }
    this.responses.add(responsesItem);
    return this;
  }

  /**
   * Get responses
   * @return responses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Post> getResponses() {
    return responses;
  }

  public void setResponses(List<Post> responses) {
    this.responses = responses;
  }

  public Post hashtags(List<String> hashtags) {
    this.hashtags = hashtags;
    return this;
  }

  public Post addHashtagsItem(String hashtagsItem) {
    if (this.hashtags == null) {
      this.hashtags = new ArrayList<String>();
    }
    this.hashtags.add(hashtagsItem);
    return this;
  }

  /**
   * Get hashtags
   * @return hashtags
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<String> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<String> hashtags) {
    this.hashtags = hashtags;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return Objects.equals(this.id, post.id) &&
        Objects.equals(this.date, post.date) &&
        Objects.equals(this.userId, post.userId) &&
        Objects.equals(this.username, post.username) &&
        Objects.equals(this.text, post.text) &&
        Objects.equals(this.type, post.type) &&
        Objects.equals(this.isCorrectAnswer, post.isCorrectAnswer) &&
        Objects.equals(this.isPublic, post.isPublic) &&
        Objects.equals(this.responses, post.responses) &&
        Objects.equals(this.hashtags, post.hashtags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, userId, username, text, type, isCorrectAnswer, isPublic, responses, hashtags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostDO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    isCorrectAnswer: ").append(toIndentedString(isCorrectAnswer)).append("\n");
    sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
    sb.append("    responses: ").append(toIndentedString(responses)).append("\n");
    sb.append("    hashtags: ").append(toIndentedString(hashtags)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public PostDO toPostDO() {
  	PostDO postDO = new PostDO(text, date.toString(), type, userId, isPublic, new ArrayList<HashtagDO>());

  	ArrayList<PostDO> responsesDO = new ArrayList<>();
  	if (responses != null) {
  		for (Post p : responses) {
  			if (p != null)
  				responsesDO.add(p.toPostDO());
  		}
  	}
  	postDO.setResponsesList(responsesDO);

  	return postDO;
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

