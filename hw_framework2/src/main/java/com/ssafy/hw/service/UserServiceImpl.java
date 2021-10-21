package com.ssafy.hw.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hw.dao.UserDao;
import com.ssafy.hw.dto.UserDto;
	
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userDao.login(map);
	}

	@Override
	public void insert(UserDto userDto) throws Exception {
		userDao.insert(userDto);
	}

}
