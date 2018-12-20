package lounge.models;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class FriendInvite extends BasicDO {

	private User fromUser;
	private User toUser;
	private boolean accepted;

	public FriendInvite(User fromUser, User toUser) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		accepted = false;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
