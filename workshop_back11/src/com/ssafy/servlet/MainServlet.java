package com.ssafy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mainservlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String company=request.getParameter("company");
		String part=request.getParameter("part");
		String email=request.getParameter("email");
		String phone1=request.getParameter("phone1");
		String phone2=request.getParameter("phone2");
		String phone3=request.getParameter("phone3");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("  <meta charset=\"UTF-8\">");
		out.println("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("  <title>명함상세</title>");
		out.println("  <style>");
		out.println("    table{");
		out.println("      border: 1px solid blue;");
		out.println("      width: 800px;");
		out.println("      padding: 10px;");
		out.println("    }");
		out.println("    .phone{");
		out.println("      width: 50px;");
		out.println("    }");
		out.println("    .address{");
		out.println("      width: 300px;");
		out.println("    }");
		out.println("  </style>");
		out.println("</head>");
		out.println("<body>");
		out.println("  <div class=\"header\">");
		out.println("    <h3>SSAFY JSP</h3>");
		out.println("    <hr color=\"black\">");
		out.println("  </div>");
		out.println("  <div class=\"content\">");
		out.println("    <h3>명함 등록</h3>");
		out.println("    <form action=\"\" method=\"post\">");
		out.println("      <table>");
		out.println("        <tr>");
		out.println("          <td>이름</td>");
		out.println("          <td>"+name+"</td>");
		out.println("        </tr>");
		out.println("        <tr>");
		out.println("          <td>회사명</td>");
		out.println("          <td>"+company+"</td>");
		out.println("          <td>부서명</td>");
		out.println("          <td>"+part+"</td>");
		out.println("        </tr>");
		out.println("        <tr>");
		out.println("          <td>이메일</td>");
		out.println("          <td>"+email+"</td>");
		out.println("        </tr>");
		out.println("        <tr>");
		out.println("          <td>휴대폰</td>");
		out.println("          <td>");
		out.println("            "+phone1+" -");
		out.println("            "+phone2+" -");
		out.println("            "+phone3+"");
		out.println("          </td>");
		out.println("        </tr>");
		out.println("        <tr>");
		out.println("          <td>주소</td>");
		out.println("          <td colspan=\"3\">");
		out.println("            "+address1+"");
		out.println("            "+address2+"");
		out.println("          </td>");
		out.println("        </tr>");
		out.println("      </table>");
		out.println("    </form>");
		out.println("  </div>");
		out.println("  <div class=\"footer\">");
		out.println("    <br><br><hr color=\"black\">");
		out.println("    <h3>Copyright 2021 SSAFY by kjin</h3>");
		out.println("  </div>");
		out.println("  ");
		out.println("</body>");
		out.println("</html>");
	}

}
