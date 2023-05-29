package com.feuji.weekendtripssecurity.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feuji.weekendtripssecurity.configuration.JwtService;
import com.feuji.weekendtripssecurity.user.User;
import com.feuji.weekendtripssecurity.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	// regiser the user save in db generate token and refresh token and return
	public AuthenticationResponse register(RegisterRequest request) {

//		CryptoJS.AES.decrypt(encrypted_string, secret)
		var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).status(request.getStatus()).age(request.getAge()).gender(request.getGender())
				.mobileNumber(request.getMobileNumber()).build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

//	authenticating by manager and call to save token generating tooken and refresh token and  return
	public Map<String, Object> authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user = repository.findByEmail(request.getEmail()).orElseGet(() -> new User());
		var jwtToken = jwtService.generateToken(user);
//		return AuthenticationResponse.builder().token(jwtToken).build();

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("user", user);
		responseMap.put("token", jwtToken);

		return responseMap;
	}

//	save token in db
//	private void saveUserToken(User user, String jwtToken) {
//		var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false)
//				.build();
//		tokenRepository.save(token);
//	}

//	private void revokeAllUserTokens(User user) {
//		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//		if (validUserTokens.isEmpty())
//			return;
//		validUserTokens.forEach(token -> {
//			token.setExpired(true);
//			token.setRevoked(true);
//		});
//		tokenRepository.saveAll(validUserTokens);
//	}

//	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//		final String refreshToken;
//		final String userEmail;
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			return;
//		}
//		refreshToken = authHeader.substring(7);
//		userEmail = jwtService.extractUsername(refreshToken);
//		if (userEmail != null) {
//			var user = this.repository.findByEmail(userEmail).orElseThrow();
//			if (jwtService.isTokenValid(refreshToken, user)) {
//				var accessToken = jwtService.generateToken(user);
//				revokeAllUserTokens(user);
//				saveUserToken(user, accessToken);
//				var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
//						.build();
//				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//			}
//		}

//	}
}

class UserNotFound extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFound(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}