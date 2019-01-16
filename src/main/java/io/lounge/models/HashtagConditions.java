package io.lounge.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * HashtagConditions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class HashtagConditions   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("operator")
  private String operator = null;

  @JsonProperty("levelValues")
  @Valid
  private List<String> levelValues = null;

  @JsonProperty("collection")
  private String collection = null;

  public HashtagConditions name(String name) {
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

  public HashtagConditions operator(String operator) {
    this.operator = operator;
    return this;
  }

  /**
   * Get operator
   * @return operator
  **/
  @ApiModelProperty(value = "")


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public HashtagConditions levelValues(List<String> levelValues) {
    this.levelValues = levelValues;
    return this;
  }

  public HashtagConditions addLevelValuesItem(String levelValuesItem) {
    if (this.levelValues == null) {
      this.levelValues = new ArrayList<String>();
    }
    this.levelValues.add(levelValuesItem);
    return this;
  }

  /**
   * Get levelValues
   * @return levelValues
  **/
  @ApiModelProperty(value = "")


  public List<String> getLevelValues() {
    return levelValues;
  }

  public void setLevelValues(List<String> levelValues) {
    this.levelValues = levelValues;
  }

  public HashtagConditions collection(String collection) {
    this.collection = collection;
    return this;
  }

  /**
   * Get collection
   * @return collection
  **/
  @ApiModelProperty(value = "")


  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HashtagConditions hashtagConditions = (HashtagConditions) o;
    return Objects.equals(this.name, hashtagConditions.name) &&
        Objects.equals(this.operator, hashtagConditions.operator) &&
        Objects.equals(this.levelValues, hashtagConditions.levelValues) &&
        Objects.equals(this.collection, hashtagConditions.collection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, operator, levelValues, collection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HashtagConditions {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    levelValues: ").append(toIndentedString(levelValues)).append("\n");
    sb.append("    collection: ").append(toIndentedString(collection)).append("\n");
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

