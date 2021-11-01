package com.ssafy.ws.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.model.BookDto;

public interface BookMapper {

	int insert(BookDto bookDto) throws Exception;
	int update(BookDto bookDto) throws Exception;
	int delete(String isbn) throws Exception;
	BookDto select(String isbn) throws Exception;
	List<BookDto> search(Map<String, Object> param) throws Exception;
	int getTotalCount(Map<String, String> map) throws Exception;
	
}
