package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * LoginInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

public class LoginInfo {
  @JsonProperty("token")
  private String token = null;

  @JsonProperty("userId")
  private String userId = null;

  public LoginInfo token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

	/**
	 * Get userId
	 * @return userId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = token;
	}


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginInfo loginInfo = (LoginInfo) o;
    return Objects.equals(this.token, loginInfo.token) && Objects.equals(this.userId, loginInfo.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginInfo {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");

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

