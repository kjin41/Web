package com.ssafy.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ssafy.guestbook.model.GuestbookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestbookService;
import com.ssafy.guestbook.model.service.GuestbookServiceImpl;

@WebServlet("/guestbook")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GuestbookService guestbookService;
	
	public void init() {
		guestbookService=GuestbookServiceImpl.getGuestbookService();
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
		
		if ("list".equals(act)) {
			path=listArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else if ("mvregister".equals(act)) {
			path="/guestbook/write.jsp";
			response.sendRedirect(root+path);
			
		} else if ("register".equals(act)) {
			path=registerArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else if ("delete".equals(act)) {
			path=deleteArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else if ("mvmodify".equals(act)) {
			path=getArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else if ("modify".equals(act)) {
			path=modifyArticle(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else {
			
		}
	}

	private String modifyArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				GuestbookDto guestbookDto=new GuestbookDto();
				guestbookDto.setSubject(request.getParameter("subject"));
				guestbookDto.setContent(request.getParameter("content"));
				guestbookDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
				guestbookService.updateArticle(guestbookDto);
				request.setAttribute("article", guestbookDto);
				request.setAttribute("msg", "글 수정 성공!!!");
				return "/guestbook?act=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 수정 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	private String getArticle(HttpServletRequest request, HttpServletResponse response) {
		int articleNo=Integer.parseInt(request.getParameter("articleno"));
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				GuestbookDto guestbookDto = guestbookService.getArticle(articleNo);
				request.setAttribute("article", guestbookDto);
				return "/guestbook/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 얻기 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
		
	}

	private String deleteArticle(HttpServletRequest request, HttpServletResponse response) {
		int articleNo=Integer.parseInt(request.getParameter("articleno"));
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				guestbookService.deleteArticle(articleNo);
				request.setAttribute("msg", "글 삭제 성공!!!");
				return "/guestbook?act=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	private String registerArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			GuestbookDto guestbookDto = new GuestbookDto();
			guestbookDto.setUserId(memberDto.getUserId());
			guestbookDto.setSubject(request.getParameter("subject"));
			guestbookDto.setContent(request.getParameter("content"));
			
			try {
				guestbookService.registerArticle(guestbookDto);
				return "/guestbook?act=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 작성 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
		
	}

	private String listArticle(HttpServletRequest request, HttpServletResponse response) {
		String key=request.getParameter("key");
		String word=request.getParameter("word");
		HttpSession session=request.getSession();
		MemberDto memberDto=(MemberDto) session.getAttribute("userinfo");
		
		if (memberDto!=null) {
			try {
				List<GuestbookDto> list=guestbookService.listArticle(key, word);
				request.setAttribute("articles", list);
				return "/guestbook/list.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "목록 보기 중 문제 발생!!!");
				return "/error/error.jsp";
			}
			
		} else {
			return "/user/login.jsp";
		}
	}

}
