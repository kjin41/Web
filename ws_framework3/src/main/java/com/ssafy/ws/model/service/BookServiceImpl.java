package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.BookDto;
import com.ssafy.ws.model.repo.BookRepo;

@Service(value="bService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public void setBookRepo(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public int insert(BookDto bookDto) throws Exception {
		return bookRepo.insert(bookDto);
	}

	@Override
	public int update(BookDto bookDto) throws Exception {
		return bookRepo.update(bookDto);
	}

	@Override
	public int delete(String isbn) throws Exception {
		return bookRepo.delete(isbn);
	}

	@Override
	public BookDto select(String isbn) throws Exception {
		return bookRepo.select(isbn);
	}

	@Override
	public List<BookDto> search() throws Exception {
		return bookRepo.search();
	}

}
