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

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("orientation")
  private String orientation = null;

  @JsonProperty("favBeer")
  private String favBeer = null;

  @JsonProperty("djRank")
  private String djRank = null;

  @JsonProperty("badges")
  private List<String> badges = null;

  @JsonProperty("profilePicURL")
  private String profilePicURL = null;

  @JsonProperty("coverURL")
  private String coverURL = null;

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

	public Wall name(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get orientation
	 * @return orientation
	 **/
	@ApiModelProperty(value = "")


	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public Wall orientation(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get favBeer
	 * @return favBeer
	 **/
	@ApiModelProperty(value = "")


	public String getFavBeer() {
		return favBeer;
	}

	public void setFavBeer(String favBeer) {
		this.favBeer = favBeer;
	}

	public Wall favBeer(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get djRank
	 * @return djRank
	 **/
	@ApiModelProperty(value = "")


	public String getDjRank() {
		return djRank;
	}

	public void setDjRank(String djRank) {
		this.djRank = djRank;
	}

	public Wall djRanks(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get profilePicURL
	 * @return profilePicURL
	 **/
	@ApiModelProperty(value = "")


	public String getProfilePicURL() {
		return profilePicURL;
	}

	public void setProfilePicURL(String profilePicURL) {
		this.profilePicURL = profilePicURL;
	}

	public Wall profilePicURL(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get coverURL
	 * @return coverURL
	 **/
	@ApiModelProperty(value = "")


	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public Wall coverURL(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	/**
	 * Get badges
	 * @return badges
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<String> getBadges() {
		return badges;
	}

	public void setBadges(List<String> badges) {
		this.badges = badges;
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

