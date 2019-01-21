package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.entities.PostDO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * NewPost
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class NewPost   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("post")
  private Post post = null;

  public NewPost userId(String userId) {
    this.username = userId;
    return this;
  }

  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public NewPost post(Post post) {
    this.post = post;
    return this;
  }

  /**
   * Get post
   * @return post
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewPost newPost = (NewPost) o;
    return Objects.equals(this.username, newPost.username) &&
        Objects.equals(this.post, newPost.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPost {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    post: ").append(toIndentedString(post)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public PostDO toPostDO() {
  	return post.toPostDO();
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

