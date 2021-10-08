package com.ssafy.photo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photo")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("act");
		String path="C:/ssafy/workspace/Web/workshop_back12/WebContent/images/";
		List<File> list=getImgFileList(path);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		for (File f: list) {
			System.out.println(f.getName());
			out.println(f.getName()+"<br>");
			out.println("<img src=\"/workshop_back12/images/"+f.getName()+"\" width=500px><br><br>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	private static List<File> getImgFileList(String path) {
		return getImgFileList(new File(path));
	}

	private static List<File> getImgFileList(File file) {
		List<File> resultList=new ArrayList<File>();
		if (!file.exists()) {
			return resultList;
		}
		
		File[] list=file.listFiles(new FileFilter() {
			String strImgExt="jpg";
			
			@Override
			public boolean accept(File pathname) {
				boolean flag=false;
				if (pathname.isFile()) {
					String ext=pathname.getName().substring(pathname.getName().lastIndexOf(".")+1);
					flag=strImgExt.contains(ext.toLowerCase());
				} else {
					flag=true;
				}
				return flag;
			}
		});
		
		for (File f: list) {
			if (f.isDirectory()) {	// 폴더명이라면
				resultList.addAll(getImgFileList(f));	// 재귀호출로 파일 불러옴.
			} else {
				resultList.add(f);	// 파일이면 리스트에 추가.
			}
		}
		
		return resultList;
	}
}
