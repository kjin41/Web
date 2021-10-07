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
		String isbn=request.getParameter("isbn");
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		int price=Integer.parseInt(request.getParameter("price"));
		String desc=request.getParameter("desc");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>SSAFY - 글목록</title>");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
		out.println("    <style>");
		out.println("        mark.sky {");
		out.println("            background: linear-gradient(to top, #54fff9 20%, transparent 30%);");
		out.println("        }");
		out.println("    </style>");
		out.println("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
		out.println("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>");
		out.println("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
		out.println("    ");
		out.println("</head>");
		out.println("<body>");
		out.println("    <div class=\"container text-center mt-3\">");
		out.println("        <div class=\"col-lg-8 mx-auto\">");
		out.println("            <h2 class=\"p-3 mb-3 shadow bg-light\"><mark class=\"sky\">글목록</mark></h2>");
		out.println("            <div class=\"m-3 text-right\">");
		out.println("                <button type=\"button\" id=\"mvRegisterBtn\" class=\"btn btn-link\">글작성</button>");
		out.println("            </div>");
		out.println("            <table class=\"table table-active text-left\">");
		out.println("                <tbody>");
		out.println("                    <tr class=\"table-info\">");
		out.println("                        <td>도서번호 : "+isbn+"</td>");
		out.println("                        <td>도서이름 : "+title+"</td>");
		out.println("                    </tr>");
		out.println("                    <tr class=\"table-info\">");
		out.println("                        <td>저자 : "+author+"</td>");
		out.println("                        <td>가격 : "+price+"</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                        <td>설명 : "+desc+"</td>");
		out.println("                    </tr>");
		out.println("                </tbody>");
		out.println("            </table>");
		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");
	}

}
