package com.ssafy.ws.model.mapper;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.UserDto;

public interface UserMapper {
	UserDto select(String id) throws Exception;
	UserDto login(@RequestParam Map<String, String> map)throws Exception;

}
