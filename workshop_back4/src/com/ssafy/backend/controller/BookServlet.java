package com.ssafy.backend.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ssafy.backend.dto.Book;
import com.ssafy.backend.dto.User;
import com.ssafy.util.DBUtil;

@WebServlet("/bookservlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil util=DBUtil.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String act=request.getParameter("act");
		String root=request.getContextPath();
		String path="/index.jsp";
		
		if ("register".equals(act)) {
			path=registerArticle(request, response);
			response.sendRedirect(root+path);
		} else if ("list".equals(act)) {
			path=listArticle(request, response);
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}


	private String registerArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userinfo");
		System.out.println(user);
		if (user!=null) {
			String isbn=request.getParameter("isbn");
			String title=request.getParameter("title");
			String author=request.getParameter("author");
			int price=Integer.parseInt(request.getParameter("price"));
			String desc=request.getParameter("desc");
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			int cnt=0;
			try {
				conn=util.getConnection();
				String sql="insert into books (isbn, title, author, price, descr)\n";
				sql+="values (?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, isbn);
				pstmt.setString(2, title);
				pstmt.setString(3, author);
				pstmt.setInt(4, price);
				pstmt.setString(5, desc);
				cnt=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.close(pstmt, conn);
			}
			
			return cnt!=0?"/book/registsuccess.jsp":"/book/registfail.jsp";
			
		} else {
			return "/index.jsp";
		}
	}
	
	private String listArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userinfo");
		if (user!=null) {
			List<Book> list=new ArrayList<Book>();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				conn=util.getConnection();
				String sql="select isbn, title, author, price, descr\n";
				sql+="from books\n";
				sql+="order by title";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Book book=new Book();
					book.setIsbn(rs.getString("isbn"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setPrice(rs.getInt("price"));
					book.setDesc(rs.getString("descr"));
					
					list.add(book);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.close(rs, pstmt, conn);
			}
			
			request.setAttribute("articlelist", list);
			return "/book/listview.jsp";
		} else {
			return "/book/listview.jsp";
		}
	}

}
