package io.lounge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lounge.mongo.dao.entities.UserDO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * NewUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-19T17:04:32.157Z")

public class NewUser   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("orientation")
  private String orientation = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("favBeer")
  private String favBeer = null;

  public NewUser username(String username) {
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

  public NewUser name(String name) {
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

  public NewUser email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public NewUser password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public NewUser orientation(String orientation) {
    this.orientation = orientation;
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

  public NewUser year(String year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  **/
  @ApiModelProperty(value = "")


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public NewUser favBeer(String favBeer) {
    this.favBeer = favBeer;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewUser newUser = (NewUser) o;
    return Objects.equals(this.username, newUser.username) &&
        Objects.equals(this.name, newUser.name) &&
        Objects.equals(this.email, newUser.email) &&
        Objects.equals(this.password, newUser.password) &&
        Objects.equals(this.orientation, newUser.orientation) &&
        Objects.equals(this.year, newUser.year) &&
        Objects.equals(this.favBeer, newUser.favBeer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, name, email, password, orientation, year, favBeer);
  }

  public UserDO applySettingsChangesAndReturnNewUserDO(UserDO originUser) {
    UserDO userDO = new UserDO();

    if (username == null) {
      userDO.setUsername(originUser.getUsername());
    } else {
      userDO.setUsername(username);
    }

    if (name == null) {
      userDO.setName(originUser.getName());
    } else {
      userDO.setName(name);
    }

    if (password == null) {
      userDO.setPassword(originUser.getPassword());
    } else {
      userDO.setPassword(password);
    }

    if (email == null) {
      userDO.setEmail(originUser.getEmail());
    } else {
      userDO.setEmail(email);
    }

    if (orientation == null) {
      userDO.setOrientation(originUser.getOrientation());
    } else {
      userDO.setOrientation(orientation);
    }

    if (year == null) {
      userDO.setYearOfStudy(originUser.getYearOfStudy());
    } else {
      userDO.setYearOfStudy(year);
    }

    if (favBeer == null) {
      userDO.setFavBeer(originUser.getFavBeer());
    } else {
      userDO.setFavBeer(favBeer);
    }

    return userDO;
  }

  public UserDO toUserDO() {
    return new UserDO(username, password, email, name, orientation, year, favBeer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewUser {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    orientation: ").append(toIndentedString(orientation)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    favBeer: ").append(toIndentedString(favBeer)).append("\n");
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

