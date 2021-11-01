package com.ssafy.ws.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.util.PageNavigation;
import com.ssafy.ws.model.BookDto;

public interface BookService {

	int insert(BookDto bookDto) throws Exception;
	int update(BookDto bookDto) throws Exception;
	int delete(String isbn) throws Exception;
	BookDto select(String isbn) throws Exception;
	List<BookDto> search(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
}
