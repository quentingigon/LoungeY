package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * FriendInvite
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-19T20:02:09.331Z")

public class FriendInvite   {
  @JsonProperty("fromUser")
  private String fromUser = null;

  @JsonProperty("toUser")
  private String toUser = null;

  public FriendInvite fromUser(String fromUser) {
    this.fromUser = fromUser;
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

  public FriendInvite toUser(String toUser) {
    this.toUser = toUser;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendInvite friendInvite = (FriendInvite) o;
    return Objects.equals(this.fromUser, friendInvite.fromUser) &&
        Objects.equals(this.toUser, friendInvite.toUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromUser, toUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendInvite {\n");
    
    sb.append("    fromUser: ").append(toIndentedString(fromUser)).append("\n");
    sb.append("    toUser: ").append(toIndentedString(toUser)).append("\n");
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

