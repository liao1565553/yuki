package com.yuki.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuki.account.dto.User;
import com.yuki.account.mapper.UserMapper;
import com.yuki.account.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	UserMapper userMapper;
	
	@Override
	public User checkUser(String username, String password) {
		User user= userMapper.checkUser(username, password);
		return user;
	}

}
