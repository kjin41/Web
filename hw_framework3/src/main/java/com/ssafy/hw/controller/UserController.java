package com.ssafy.hw.controller;

import java.util.Map;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.hw.dto.UserDto;
import com.ssafy.hw.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private UserService userService;

	@GetMapping("/logout")
	private String logoutUser(HttpSession session) {
		session.invalidate();
		return "/index";
	}

	@PostMapping("/login")
	private String loginUser(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		
		try {
			UserDto userDto=userService.login(map);
			if (userDto!=null) {
				session.setAttribute("userinfo", userDto);
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호가 일지하지 않습니다.");
			}
			
			return "/index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "로그인 중 문제 발생");
			return "/error/error";
		}
	}
	
	@GetMapping("/insert")
	public String insertUser() {
		return "/user/insert";
	}
	
	@PostMapping("/insert")
	public String insertUser(UserDto userDto, HttpSession session, Model model) {
		try {
			userService.insert(userDto);
			session.setAttribute("userinfo", userDto);
			return "/index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "회원가입 중 문제 발생");
			return "/error/error";
		}
	}

}
