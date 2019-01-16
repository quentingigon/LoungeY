package io.lounge.services;

import io.lounge.mongo.dao.MongoConnection;
import io.lounge.mongo.dao.UserDAO;
import io.lounge.mongo.dao.domodels.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
		UserDAO userDAO = new UserDAO(conn.getDatastore());

		UserDO user = userDAO.findOne("username", username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
			user.getPassword(), Collections.<GrantedAuthority>emptyList());
	}
}