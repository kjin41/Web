package com.ssafy.ws.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		try {
			UserDto userDto=userService.login(map);
			if (userDto!=null) {
				session.setAttribute("userinfo", userDto);
				return "/index";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "/index";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생");
			return "/error/error";
			
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
