package com.ssafy.ws.model.repo;

import com.ssafy.ws.model.UserDto;

public interface UserRepo {
	UserDto select(String id) throws Exception;
}
