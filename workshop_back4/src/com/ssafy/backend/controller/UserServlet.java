package com.ssafy.backend.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ssafy.backend.dto.User;
import com.ssafy.util.DBUtil;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil util=DBUtil.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("act");
		String path="/index.jsp";
		String root=request.getContextPath();
		
		if ("login".equals(act)) {
			path=loginUser(request, response);
//			response.sendRedirect(root+path);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("logout".equals(act)) {
			path=logoutUser(request, response);
			response.sendRedirect(root+path);
		}
	}

	private String logoutUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

	private String loginUser(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		User user=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=util.getConnection();
			String sql="select id, name, pass\n"
					+ "from users\n"
					+ "where id=? and pass=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				user=new User();
				user.setId(id);
				user.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
		if (user!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("userinfo", user);
			
		} else {
			request.setAttribute("msg", "아이디와 비밀번호가 일치하지 않습니다.");
		}
		
		return "/index.jsp";
	}

}
