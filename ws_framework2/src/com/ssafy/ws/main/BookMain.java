package com.ssafy.ws.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssafy.ws.config.Application;
import com.ssafy.ws.model.BookDto;
import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.BookServiceImpl;
import com.ssafy.ws.model.service.UserService;
import com.ssafy.ws.model.service.UserServiceImpl;

public class BookMain {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		while(flag) {
			System.out.println("1.책 등록  2.책 수정  3.책 삭제  4.책번호로 검색  5.책 목록  6.회원번호로 검색  0.종료");
			System.out.println("--------------------------------------------------------------");
			System.out.print("번호 입력: ");
			int num=Integer.parseInt(in.readLine());
			switch(num) {
			case 1: insertBook(); break;
			case 2: updateBook(); break;
			case 3: deleteBook(); break;
			case 4: selectBook(); break;
			case 5: searchBook(); break;
			case 6: selectUser(); break;
			default: flag=false; break;
			}
			System.out.println();
		}
	}

	private static void insertBook() {
//		ApplicationContext context= new ClassPathXmlApplicationContext("main/resources/application.xml");
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		BookService bookService = context.getBean("bService", BookServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			BookDto bookDto=new BookDto();
			System.out.print("등록할 책번호: ");
			bookDto.setIsbn(in.readLine());
			System.out.print("제목: ");
			bookDto.setTitle(in.readLine());
			System.out.print("작가: ");
			bookDto.setAuthor(in.readLine());
			System.out.print("가격: ");
			bookDto.setPrice(Integer.parseInt(in.readLine()));
			System.out.print("내용: ");
			bookDto.setContent(in.readLine());
			System.out.print("사진: ");
			bookDto.setImg(in.readLine());
			bookService.insert(bookDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void updateBook() throws IOException {
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		BookService bookService = context.getBean("bService", BookServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			BookDto bookDto=new BookDto();
			System.out.print("수정할 책번호: ");
			bookDto.setIsbn(in.readLine());
			System.out.print("제목: ");
			bookDto.setTitle(in.readLine());
			System.out.print("작가: ");
			bookDto.setAuthor(in.readLine());
			System.out.print("가격: ");
			bookDto.setPrice(Integer.parseInt(in.readLine()));
			System.out.print("내용: ");
			bookDto.setContent(in.readLine());
			System.out.print("사진: ");
			bookDto.setImg(in.readLine());
			bookService.update(bookDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void deleteBook() {
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		BookService bookService = context.getBean("bService", BookServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("삭제할 책번호: ");
			String isbn=in.readLine();
			bookService.delete(isbn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void selectBook() {
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		BookService bookService = context.getBean("bService", BookServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("볼 책번호: ");
			String isbn=in.readLine();
			BookDto bookDto=bookService.select(isbn);
			System.out.println("책번호\t\t제목\t작가\t가격\t내용\t사진");
			System.out.println("----------------------------------------------------------");
			System.out.println(bookDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void searchBook() {
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		BookService bookService = context.getBean("bService", BookServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			List<BookDto> list=bookService.search();
			System.out.println("책번호\t\t제목\t\t작가\t가격\t내용\t\t사진");
			System.out.println("----------------------------------------------------------");
			for (BookDto bookDto: list) {
				System.out.println(bookDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void selectUser() {
		ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
		UserService userService = context.getBean("uService", UserServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("볼 회원아이디: ");
			String id=in.readLine();
			UserDto bookDto=userService.select(id);
			System.out.println("아이디\t이름\t비밀번호\t추천아이디");
			System.out.println("----------------------------------------------------------");
			System.out.println(bookDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
