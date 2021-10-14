package com.ssafy.guestbook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.MemberService;
import com.ssafy.guestbook.model.service.MemberServiceImpl;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	public void init() {
		memberService=MemberServiceImpl.getMemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root=request.getContextPath();
		String act=request.getParameter("act");
		String path="/index.jsp";
		boolean flag=true;	// sendredirect or forward 선택 가능
		if ("".equals(act)) {
			
		} else if ("mvregister".equals(act)) {
			path="/user/join.jsp";
			response.sendRedirect(root+path);
		} else if ("mvlogin".equals(act)) {
			path="/user/login.jsp";
			response.sendRedirect(root+path);
		} else if ("register".equals(act)) {
			path=registerMember(request, response);
			response.sendRedirect(root+path);
		} else if ("idcheck".equals(act)) {
			path=idCheck(request, response);
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if ("login".equals(act)) {
			path=loginMember(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("logout".equals(act)) {
			path=logoutMember(request, response);
			response.sendRedirect(root+path);
		} 
	}

	private String logoutMember(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

	private String loginMember(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("userid");
		String pass=request.getParameter("userpwd");
		MemberDto memberDto=null;
		
		try {
			memberDto=memberService.login(id, pass);
			
			if(memberDto != null) {	// 로그인 성공
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				
//				cookie 설정
				String idsave = request.getParameter("idsave");
				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", id);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
					response.addCookie(cookie);
				} else {//아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				
				return "/index.jsp";
				
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제 발생!!!");
			return "/error/error.jsp";
		}
		
	}

	private String idCheck(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("ckid");
		int cnt=1;
		try {
			cnt=memberService.idCheck(id);	// 0 or 1
			request.setAttribute("idcount", cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/idcheck_result.jsp";
	}

	private String registerMember(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmail(request.getParameter("emailid")+"@"+request.getParameter("emaildomain"));
		try {
			memberService.registerMember(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 문제 발생!!!");
			return "/error/error.jsp";
		}
		
		return "/user/login.jsp";
	}

}
