package com.ssafy.ws.model.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.UserDto;

public interface UserService {
	UserDto select(String id) throws Exception;
	UserDto login(@RequestParam Map<String, String> map) throws Exception;

}
