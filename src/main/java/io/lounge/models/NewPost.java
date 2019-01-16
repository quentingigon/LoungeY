package io.lounge.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * NewPost
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class NewPost   {
  @JsonProperty("userId")
  private BigDecimal userId = null;

  @JsonProperty("post")
  private Post post = null;

  public NewPost userId(BigDecimal userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getUserId() {
    return userId;
  }

  public void setUserId(BigDecimal userId) {
    this.userId = userId;
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
    return Objects.equals(this.userId, newPost.userId) &&
        Objects.equals(this.post, newPost.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPost {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    post: ").append(toIndentedString(post)).append("\n");
    sb.append("}");
    return sb.toString();
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

