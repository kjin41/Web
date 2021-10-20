package com.ssafy.ws.model.repo;

import java.util.List;

import com.ssafy.ws.model.BookDto;

public interface BookRepo {
	int insert(BookDto bookDto) throws Exception;
	int update(BookDto bookDto) throws Exception;
	int delete(String isbn) throws Exception;
	BookDto select(String isbn) throws Exception;
	List<BookDto> search() throws Exception;
	
}
