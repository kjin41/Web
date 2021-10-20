package com.ssafy.ws.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.repo.UserRepo;

@Service(value="uService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
//	public void setUserRepo(UserRepo userRepo) {
//		this.userRepo = userRepo;
//	}
//	
	@Override
	public UserDto select(String id) throws Exception {
		return userRepo.select(id);
	}

	@Override
	public UserDto login(@RequestParam Map<String, String> map) throws Exception {
		return userRepo.login(map);
	}


}
