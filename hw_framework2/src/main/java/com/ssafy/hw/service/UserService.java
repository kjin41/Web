package com.ssafy.hw.service;

import java.util.Map;

import com.ssafy.hw.dto.UserDto;

public interface UserService {
	UserDto login(Map<String, String> map) throws Exception;
	void insert(UserDto userDto) throws Exception;
}
