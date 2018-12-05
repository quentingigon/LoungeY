package lounge.models;

public class UserDO extends BasicDO {

	private String username;
	private String password;

	public UserDO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserDO() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
