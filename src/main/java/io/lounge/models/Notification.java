package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.entities.NotificationDO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Notification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-20T11:58:30.925Z")

public class Notification   {
  @JsonProperty("fromUser")
  private String fromUser = null;

  @JsonProperty("fromUser")
  private String toUser = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("type")
  private String type = null;

  public Notification friendUsername(String friendUsername) {
    this.fromUser = friendUsername;
    return this;
  }

  /**
   * Get fromUser
   * @return fromUser
  **/
  @ApiModelProperty(value = "")


  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public Notification text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get toUser
   * @return toUser
   **/
  @ApiModelProperty(value = "")


  public String getToUser() {
    return toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
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
    return Objects.equals(this.fromUser, notification.fromUser) &&
        Objects.equals(this.text, notification.text) &&
        Objects.equals(this.type, notification.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromUser, text, type);
  }

  public NotificationDO toNotificationDO() {
    return new NotificationDO(text, fromUser, toUser, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notification {\n");
    
    sb.append("    fromUser: ").append(toIndentedString(fromUser)).append("\n");
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

