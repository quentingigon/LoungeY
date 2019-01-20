package io.lounge.configuration;

public class SecurityConstants {

	public static final String SECRET = "ThisSecrectIsSooooooooGooooooooooood!";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/register";
	public static final String LOGIN_URL = "/login";
	public static final String SWAGGER_UI_URL = "/swagger-ui.html";
	public static final String HOME_URL = "/";

}
