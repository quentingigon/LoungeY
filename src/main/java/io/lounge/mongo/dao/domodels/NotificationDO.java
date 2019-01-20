package io.lounge.mongo.dao.domodels;

import io.lounge.models.Notification;

public class NotificationDO extends BasicDO {

	private String text;
	private String fromUser;
	private String toUser;
	private String type; // FRIEND, NEW_PICTURE, UPDATE_PICTURE

	public NotificationDO () {

	}

	public NotificationDO(String text, String fromUser, String toUser, String type) {
		this.text = text;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public Notification toNotification() {
		Notification notif = new Notification();
		notif.setText(text);
		notif.setType(type);
		notif.setFromUser(fromUser);
		notif.setToUser(toUser);
		return notif;
	}
}
