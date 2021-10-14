package com.ssafy.guestbook.model.service;

import java.util.List;

import com.ssafy.guestbook.model.GuestbookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.dao.GuestbookDao;
import com.ssafy.guestbook.model.dao.GuestbookDaoImpl;

public class GuestbookServiceImpl implements GuestbookService {

	private static GuestbookService guestbookService = new GuestbookServiceImpl();
	private GuestbookDao guestbookDao;
	
	private GuestbookServiceImpl() {
		guestbookDao=GuestbookDaoImpl.getGuestbookDao();
	}
	
	public static GuestbookService getGuestbookService() {
		return guestbookService;
	}

	@Override
	public void registerArticle(GuestbookDto guestbookDto) throws Exception {
		guestbookDao.registerArticle(guestbookDto);
	}

	@Override
	public List<GuestbookDto> listArticle(String key, String word) throws Exception {
		key=key==null? "":key.trim();
		word=word==null? "":word.trim();
		return guestbookDao.listArticle(key, word);
	}

	@Override
	public GuestbookDto getArticle(int articleNo) throws Exception {
		return guestbookDao.getArticle(articleNo);
	}

	@Override
	public void updateArticle(GuestbookDto guestbookDto) throws Exception {
		guestbookDao.updateArticle(guestbookDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		guestbookDao.deleteArticle(articleNo);
	}

}
