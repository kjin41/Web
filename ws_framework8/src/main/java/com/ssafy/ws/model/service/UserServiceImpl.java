package com.ssafy.ws.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserDto select(String id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).select(id);
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

}
