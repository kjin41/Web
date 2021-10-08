package com.ssafy.namecard.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.namecard.dto.NameCardDto;
import com.ssafy.util.DBUtil;

@WebServlet("/ncservlet")
public class NameCardServlet extends HttpServlet {
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
	
	protected String registerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String company=request.getParameter("company");
		String part=request.getParameter("part");
		String email=request.getParameter("email");
		String phone1=request.getParameter("phone1");
		String phone2=request.getParameter("phone2");
		String phone3=request.getParameter("phone3");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int cnt=0;
		try {
			conn=util.getConnection();
			String sql="insert into namecards (name, company, part, email, phone1, phone2, phone3, address1, address2) \r\n" + 
					"values (?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, company);
			pstmt.setString(3, part);
			pstmt.setString(4, email);
			pstmt.setString(5, phone1);
			pstmt.setString(6, phone2);
			pstmt.setString(7, phone3);
			pstmt.setString(8, address1);
			pstmt.setString(9, address2);
			cnt=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		
		return cnt!=0? "/namecard/registsuccess.jsp":"/namecard/registfail.jsp";
	}
	
	protected String listArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<NameCardDto> list=new ArrayList<NameCardDto>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=util.getConnection();
			String sql="select name, company, part, email, phone1, phone2, phone3, address1, address2\n";
			sql+="from namecards\n";
			sql+="order by no desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				NameCardDto nameCard=new NameCardDto();
				nameCard.setName(rs.getString("name"));
				nameCard.setCompany(rs.getString("company"));
				nameCard.setPart(rs.getString("part"));
				nameCard.setEmail(rs.getString("email"));
				nameCard.setPhone1(rs.getString("phone1"));
				nameCard.setPhone2(rs.getString("phone2"));
				nameCard.setPhone3(rs.getString("phone3"));
				nameCard.setAddress1(rs.getString("address1"));
				nameCard.setAddress2(rs.getString("address2"));
				
				list.add(nameCard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
		request.setAttribute("articlelist", list);
		return "/namecard/listview.jsp";
	}

}
