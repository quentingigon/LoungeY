package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Wall
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class Wall   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("yearOfStudy")
  private String yearOfStudy = null;

  @JsonProperty("posts")
  @Valid
  private List<Post> posts = null;

  public Wall username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Wall yearOfStudy(String yearOfStudy) {
    this.yearOfStudy = yearOfStudy;
    return this;
  }

  /**
   * Get yearOfStudy
   * @return yearOfStudy
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getYearOfStudy() {
    return yearOfStudy;
  }

  public void setYearOfStudy(String yearOfStudy) {
    this.yearOfStudy = yearOfStudy;
  }

  public Wall posts(List<Post> posts) {
    this.posts = posts;
    return this;
  }

  public Wall addPostsItem(Post postsItem) {
    if (this.posts == null) {
      this.posts = new ArrayList<Post>();
    }
    this.posts.add(postsItem);
    return this;
  }

  /**
   * Get posts
   * @return posts
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wall wall = (Wall) o;
    return Objects.equals(this.username, wall.username) &&
        Objects.equals(this.yearOfStudy, wall.yearOfStudy) &&
        Objects.equals(this.posts, wall.posts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, yearOfStudy, posts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Wall {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    yearOfStudy: ").append(toIndentedString(yearOfStudy)).append("\n");
    sb.append("    posts: ").append(toIndentedString(posts)).append("\n");
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

