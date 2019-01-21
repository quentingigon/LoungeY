package io.lounge.mongo.dao.entities;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class FriendInviteDO extends BasicDO {

	private UserDO fromUserDO;
	private UserDO toUserDO;
	private boolean accepted;

	public FriendInviteDO(UserDO fromUserDO, UserDO toUserDO) {
		this.fromUserDO = fromUserDO;
		this.toUserDO = toUserDO;
		accepted = false;
	}

	public UserDO getFromUserDO() {
		return fromUserDO;
	}

	public void setFromUserDO(UserDO fromUserDO) {
		this.fromUserDO = fromUserDO;
	}

	public UserDO getToUserDO() {
		return toUserDO;
	}

	public void setToUserDO(UserDO toUserDO) {
		this.toUserDO = toUserDO;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
