package com.ssafy.hw.dao;

import java.util.Map;

import com.ssafy.hw.dto.UserDto;

public interface UserDao {
	UserDto login(Map<String, String> map) throws Exception;
	void insert(UserDto userDto) throws Exception;
}
