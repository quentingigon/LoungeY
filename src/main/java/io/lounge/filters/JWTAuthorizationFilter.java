package io.lounge.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.BlackListDAO;
import io.lounge.mongo.dao.entities.BlackListDO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static io.lounge.configuration.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

		BlackListDAO blackListDAO = DAOUtils.getBlackListDAO();
		// blacklist init, not clean to do it there, need a script
		if (blackListDAO.getBlackList() == null) {
			blackListDAO.createBlackList();
		}
		BlackListDO blackListDO = blackListDAO.getBlackList();

		String username = req.getHeader("userLogout");
		// check if request is for logout and if so add the token of the user to the blacklist
		// and block the request
		if (username != null) {
			blackListDO.addToBlackList(username, header);
			blackListDAO.update(blackListDO);

			chain.doFilter(req, res);
			return;
		}
		// the token of the request is blacklisted
		if (header == null || !header.startsWith(TOKEN_PREFIX) || blackListDO.getBlacklist().containsValue(header)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
				.build()
				.verify(token.replace(TOKEN_PREFIX, ""))
				.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(new ArrayList<>(), user, null);
			}
			return null;
		}
		return null;
	}
}
