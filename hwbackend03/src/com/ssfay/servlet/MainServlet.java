package com.ssfay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.jasper.compiler.StringInterpreterFactory;

import com.ssafy.product.dto.ProductDto;
import com.ssafy.product.dto.UserDto;
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
		} else if ("lastproduct".equals(act)) {
			path=lastProduct(request, response);
			RequestDispatcher dispatcher=request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} else if ("delete".equals(act)) {
			path=deleteProduct(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if ("update".equals(act)) {
			path=updateProduct(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		}
		
	}
	
	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			int cnt=0;
			Cookie[] cookies=request.getCookies();
			String no=null;
			String name=request.getParameter("name");
			int price=Integer.parseInt(request.getParameter("price"));
			String desc=request.getParameter("desc");
	        if (cookies!=null){
	        	for (Cookie cookie: cookies){
	        		if (cookie.getName().equals(userDto.getId())){
	        			no=cookie.getValue();
	        			break;
	        		}
	        	}
				ProductDto productDto=null;
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try {
					conn=util.getConnection();
					String sql="update products set name=?, price=?, descr=?\n"
							+ "where no=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setInt(2, price);
					pstmt.setString(3, desc);
					pstmt.setString(4, no);
					cnt=pstmt.executeUpdate();
					if (cnt!=0) {
						productDto=new ProductDto();
						productDto.setNo(no);
						productDto.setName(name);
						productDto.setPrice(price);
						productDto.setDesc(desc);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					util.close(rs, pstmt, conn);
				}
				
				request.setAttribute("lastproduct", productDto);
				request.setAttribute("msg", "상품 내용이 수정되었습니다.");
	        }
	        
	    	return "/product/lastproduct.jsp";
		} else {
			request.setAttribute("msg", "로그인 후 이용해주세요.");
			return "/index.jsp";
		}
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			Cookie[] cookies=request.getCookies();
			String no=null;
            if (cookies!=null){
            	for (Cookie cookie: cookies){
            		if (cookie.getName().equals(userDto.getId())){
            			no=cookie.getValue();
            			break;
            		}
            	}

    			Connection conn=null;
    			PreparedStatement pstmt=null;
    			try {
    				conn=util.getConnection();
    				String sql="delete\n"
    						+ "from products\n"
    						+ "where no=?";
    				pstmt=conn.prepareStatement(sql);
    				pstmt.setString(1, no);
    				pstmt.executeUpdate();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			} finally {
    				util.close(pstmt, conn);
    			}
    			
    			request.setAttribute("msg", "상품 내용이 삭제되었습니다.");
            }
            
        	return "/product/lastproduct.jsp";
		} else {
			request.setAttribute("msg", "로그인 후 이용해주세요.");
			return "/index.jsp";
		}
		
	}

	private String registerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			String no=request.getParameter("no");
			String name=request.getParameter("name");
			int price=Integer.parseInt(request.getParameter("price"));
			String desc=request.getParameter("desc");
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			int cnt=0;
			try {
				conn=util.getConnection();
				String sql="insert into products (no, name, price, descr)\n";
				sql+="values (?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, no);
				pstmt.setString(2, name);
				pstmt.setInt(3, price);
				pstmt.setString(4, desc);
				cnt=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.close(pstmt, conn);
			}
			
			if (cnt!=0) {
				Cookie cookie=new Cookie(userDto.getId(), no);
				
				cookie.setMaxAge(60*60*24*365*40);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			
			
			return cnt!=0?"/product/registsuccess.jsp":"/product/registfail.jsp";

		} else {
			request.setAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index.jsp";
		}
	}
	
	private String listArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
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
					product.setNo(rs.getString("no"));
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
			
		} else {
			request.setAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index.jsp";
		}
		
	}

	
	private String lastProduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			Cookie[] cookies=request.getCookies();
			String no=null;
            if (cookies!=null){
            	for (Cookie cookie: cookies){
            		if (cookie.getName().equals(userDto.getId())){
            			no=cookie.getValue();
            			break;
            		}
            	}

    			ProductDto productDto=null;
    			Connection conn=null;
    			PreparedStatement pstmt=null;
    			ResultSet rs=null;
    			try {
    				conn=util.getConnection();
    				String sql="select no, name, price, descr\n"
    						+ "from products\n"
    						+ "where no=?";
    				pstmt=conn.prepareStatement(sql);
    				pstmt.setString(1, no);
    				rs=pstmt.executeQuery();
    				if (rs.next()) {
    					productDto=new ProductDto();
    					productDto.setNo(rs.getString("no"));
    					productDto.setName(rs.getString("name"));
    					productDto.setPrice(rs.getInt("price"));
    					productDto.setDesc(rs.getString("descr"));
    				}
    			} catch (SQLException e) {
    				e.printStackTrace();
    			} finally {
    				util.close(rs, pstmt, conn);
    			}
    			
    			request.setAttribute("lastproduct", productDto);
            }
            
        	return "/product/lastproduct.jsp";
		} else {
			request.setAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index.jsp";
			
		}
		
	}


}
