package com.ssafy.ws.model.service;

import com.ssafy.ws.model.UserDto;

public interface UserService {
	UserDto select(String id) throws Exception;

}
