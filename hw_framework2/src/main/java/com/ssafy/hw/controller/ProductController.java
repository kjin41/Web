package com.ssafy.hw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.hw.dto.ProductDto;
import com.ssafy.hw.dto.UserDto;
import com.ssafy.hw.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends HttpServlet {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	private String listArticle(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				List<ProductDto> list=productService.listArticle(map);
				model.addAttribute("articlelist", list);
				return "/product/listview";
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "목록 얻기 중 문제 발생");
				return "/error/error";
			}
			
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}

	
	@GetMapping("/register")
	private String registArticle() {
		return "/product/regist";
	}
	
	@PostMapping("/register")
	private String registArticle(ProductDto productDto, Model model, HttpSession session, HttpServletResponse response) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				productService.registerArticle(productDto);
				Cookie cookie=new Cookie(userDto.getId(), productDto.getNo());
				cookie.setMaxAge(60*60*24*365*40);
				cookie.setPath("/");
				response.addCookie(cookie);

//				model.addAttribute("msg", "상품이 등록되었습니다.");
				return "redirect:/product/list?key=&word=";
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "상품 등록 중 문제 발생");
				return "/error/error";
			} 
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}
	
	@GetMapping("/lastproduct")
	public String lastProduct(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		productService.lastProduct(userDto.getId());
		return "/product/lastproduct";
	}

}
