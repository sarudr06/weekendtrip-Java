package com.feuji.weekendtripssecurity.service;

import java.util.List;

import com.feuji.weekendtripssecurity.authentication.ResetPassword;
import com.feuji.weekendtripssecurity.user.User;

public interface UserService {

	User findbyEmail(String email);

	User saveUser(User user);

	List<User> getAllUsers();

	User getById(Integer id);

	User updateUser(Integer id, User user);

	User changeStatusByEmail(String email);

	User changeStatusById(Integer id);

	User changePassword(ResetPassword resetPassword);
}
