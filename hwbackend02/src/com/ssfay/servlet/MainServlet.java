package com.ssfay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.product.dto.ProductDto;
import com.ssafy.util.DBUtil;

@WebServlet("/mainservlet")
public class MainServlet extends HttpServlet {
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
	
	private String registerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		String desc=request.getParameter("desc");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int cnt=0;
		try {
			conn=util.getConnection();
			String sql="insert into products (name, price, descr)\n";
			sql+="values (?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setString(3, desc);
			cnt=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		
		return cnt!=0?"/product/registsuccess.jsp":"/product/registfail.jsp";
//		return "productservlet?act=list";
	}
	
	private String listArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductDto> list=new ArrayList<ProductDto>();  
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=util.getConnection();
			String sql="select no, name, price, descr\n";
			sql+="from products\n";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductDto product=new ProductDto();
				product.setNo(rs.getInt("no"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("descr"));
				
				list.add(product);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
		request.setAttribute("articlelist", list);
		return "/product/listview.jsp";
	}
	

}
