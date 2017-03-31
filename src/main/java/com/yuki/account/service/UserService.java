package com.yuki.account.service;

import com.yuki.account.dto.User;

public interface UserService {
	public User checkUser(String username, String password);
	public User getUserByUsername(String username);
}
