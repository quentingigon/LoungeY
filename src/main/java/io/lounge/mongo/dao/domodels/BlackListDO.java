package io.lounge.mongo.dao.domodels;

import java.util.HashMap;


public class BlackListDO extends BasicDO {

	// map username to token to avoid memory explosion
	private HashMap<String, String> blacklist;

	public BlackListDO() {
		this.blacklist = new HashMap<>();
	}

	public HashMap<String, String> getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(HashMap<String, String> blacklist) {
		this.blacklist = blacklist;
	}

	public void addToBlackList(String user, String s) {
		blacklist.put(user, s);
	}

	public void removeFromBlackList(String user) {
		blacklist.remove(user);
	}
}
