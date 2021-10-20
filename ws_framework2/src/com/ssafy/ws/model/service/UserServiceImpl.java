package com.ssafy.ws.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.repo.UserRepo;

@Service(value="uService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
//	public UserServiceImpl(UserRepo userRepo) {
//		this.userRepo=userRepo;
//	}
	

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDto select(String id) throws Exception {
		return userRepo.select(id);
	}


}
