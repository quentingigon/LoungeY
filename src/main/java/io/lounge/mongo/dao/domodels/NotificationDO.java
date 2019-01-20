package io.lounge.mongo.dao.domodels;

import io.lounge.models.Notification;

public class NotificationDO extends BasicDO {

	private String text;
	private String user;
	private String type; // FRIEND, NEW_PICTURE, UPDATE_PICTURE

	public NotificationDO(String text, String user, String type) {
		this.text = text;
		this.user = user;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Notification toNotification() {
		Notification notif = new Notification();
		notif.setText(text);
		notif.setType(type);
		notif.setUser(user);
		return notif;
	}
}
