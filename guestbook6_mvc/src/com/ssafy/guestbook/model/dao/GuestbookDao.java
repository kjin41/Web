package com.ssafy.guestbook.model.dao;

import java.util.List;

import com.ssafy.guestbook.model.GuestbookDto;

public interface GuestbookDao {
	void registerArticle(GuestbookDto guestbookDto) throws Exception;
	List<GuestbookDto> listArticle(String key, String word) throws Exception;
	GuestbookDto getArticle(int articleNo) throws Exception;
	void updateArticle(GuestbookDto guestbookDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
