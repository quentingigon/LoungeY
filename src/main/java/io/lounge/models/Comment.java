package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Comment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class Comment   {
  @JsonProperty("rootPostId")
  private String rootPostId = null;

  @JsonProperty("post")
  private Post post = null;

  public Comment rootPostId(String rootPostId) {
    this.rootPostId = rootPostId;
    return this;
  }

  /**
   * Get rootPostId
   * @return rootPostId
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getRootPostId() {
    return rootPostId;
  }

  public void setRootPostId(String rootPostId) {
    this.rootPostId = rootPostId;
  }

  public Comment post(Post post) {
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
    Comment comment = (Comment) o;
    return
        Objects.equals(this.rootPostId, comment.rootPostId) &&
        Objects.equals(this.post, comment.post);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rootPostId, post);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");

    sb.append("    rootPostId: ").append(toIndentedString(rootPostId)).append("\n");
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

