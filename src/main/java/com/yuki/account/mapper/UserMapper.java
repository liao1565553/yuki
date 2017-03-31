package com.yuki.account.mapper;

import org.apache.ibatis.annotations.Param;

import com.yuki.account.dto.User;

public interface UserMapper {
	User checkUser(@Param("username")String username,@Param("password")String password);
	User getUserByUsername(@Param("username")String username);
}
