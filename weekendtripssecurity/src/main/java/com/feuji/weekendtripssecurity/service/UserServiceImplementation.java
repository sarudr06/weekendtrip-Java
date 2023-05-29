package com.feuji.weekendtripssecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feuji.weekendtripssecurity.authentication.ResetPassword;
import com.feuji.weekendtripssecurity.user.User;
import com.feuji.weekendtripssecurity.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(Integer id, User user) {
		user.setId(id);
		return repository.save(user);
	}

	@Override
	public User changeStatusById(Integer id) {
		User user = repository.findById(id).get();
		if (user.getId().equals(id) && user.getRole().equalsIgnoreCase("user")) {
			user.setRole("admin");
		} else if (user.getId().equals(id) && user.getRole().equalsIgnoreCase("admin")) {
			user.setRole("user");
		}
		return user;

	}

	@Override
	public User changeStatusByEmail(String email) {
		User user = repository.findByEmail(email).get();
		if (user.getEmail().equals(email) && user.getRole().equalsIgnoreCase("user")) {
			user.setRole("admin");
		} else if (user.getEmail().equals(email) && user.getRole().equalsIgnoreCase("admin")) {
			user.setRole("user");
		}

		return repository.save(user);

	}

	@Override
	public User findbyEmail(String email) {
		return repository.findByEmail(email).get();
	}

	@Override
	public User changePassword(ResetPassword resetPassword) {
		log.info("service");
		if (passwordEncoder.matches(resetPassword.getOldPassword(), getById(resetPassword.getId()).getPassword())) {
			log.info("same password");

//			var user = User.builder().id(resetPassword.getId())
//					.password().build();
//			

//			repository.save(user);

			User user = getById(resetPassword.getId());

			user.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));

			return repository.save(user);
		}
		throw new ArrayIndexOutOfBoundsException();
	}
}
