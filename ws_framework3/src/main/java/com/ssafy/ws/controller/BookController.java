package com.ssafy.ws.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.model.BookDto;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/register")
	public String insert() {
		return "/book/regist";
	}
	
	@PostMapping("/register")
	public String insert(BookDto bookDto, HttpSession session, Model model) {
		try {
			bookService.insert(bookDto);
			System.out.println(bookDto);
			session.setAttribute("article", bookDto);
			return "redirect:/book/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "책 등록 중 문제 발생");
			return "/error/error";
		}
		
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		try {
			model.addAttribute("articlelist", bookService.search());
			return "/book/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "목록보기 중 문제 발생");
			return "/error/error";
		}
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "/book/detail";
	}
	
	
}
