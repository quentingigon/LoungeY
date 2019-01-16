package io.lounge.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

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
  private List<BigDecimal> postsWithThisHashtag = null;

  @JsonProperty("conditions")
  private HashtagConditions conditions = null;

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

  public Hashtag postsWithThisHashtag(List<BigDecimal> postsWithThisHashtag) {
    this.postsWithThisHashtag = postsWithThisHashtag;
    return this;
  }

  public Hashtag addPostsWithThisHashtagItem(BigDecimal postsWithThisHashtagItem) {
    if (this.postsWithThisHashtag == null) {
      this.postsWithThisHashtag = new ArrayList<BigDecimal>();
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

  public List<BigDecimal> getPostsWithThisHashtag() {
    return postsWithThisHashtag;
  }

  public void setPostsWithThisHashtag(List<BigDecimal> postsWithThisHashtag) {
    this.postsWithThisHashtag = postsWithThisHashtag;
  }

  public Hashtag conditions(HashtagConditions conditions) {
    this.conditions = conditions;
    return this;
  }

  /**
   * Get conditions
   * @return conditions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public HashtagConditions getConditions() {
    return conditions;
  }

  public void setConditions(HashtagConditions conditions) {
    this.conditions = conditions;
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
        Objects.equals(this.postsWithThisHashtag, hashtag.postsWithThisHashtag) &&
        Objects.equals(this.conditions, hashtag.conditions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, postsWithThisHashtag, conditions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HashtagDO {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    postsWithThisHashtag: ").append(toIndentedString(postsWithThisHashtag)).append("\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
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

