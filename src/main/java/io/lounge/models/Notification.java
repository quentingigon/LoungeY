package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.domodels.NotificationDO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Notification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-20T11:58:30.925Z")

public class Notification   {
  @JsonProperty("user")
  private String user = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("type")
  private String type = null;

  public Notification friendUsername(String friendUsername) {
    this.user = friendUsername;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Notification text(String text) {
    this.text = text;
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

  public Notification type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notification notification = (Notification) o;
    return Objects.equals(this.user, notification.user) &&
        Objects.equals(this.text, notification.text) &&
        Objects.equals(this.type, notification.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, text, type);
  }

  public NotificationDO toNotificationDO() {
    return new NotificationDO(text, user, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notification {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

