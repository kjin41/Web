package com.ssafy.ws.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.util.PageNavigation;
import com.ssafy.ws.model.BookDto;
import com.ssafy.ws.model.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {

private static final Logger logger=LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BookDto bookDto) throws Exception {
		return sqlSession.getMapper(BookMapper.class).insert(bookDto);
	}

	@Override
	public int update(BookDto bookDto) throws Exception {
		return sqlSession.getMapper(BookMapper.class).update(bookDto);
	}

	@Override
	public int delete(String isbn) throws Exception {
		return sqlSession.getMapper(BookMapper.class).delete(isbn);
	}

	@Override
	public BookDto select(String isbn) throws Exception {
		return sqlSession.getMapper(BookMapper.class).select(isbn);
	}

	@Override
	public List<BookDto> search(Map<String, String> map) throws Exception {
		Map<String, Object> param=new HashMap<String, Object>();
		int currentPage=Integer.parseInt(map.get("pg")==null?"1":map.get("pg"));
		int sizePerPage=Integer.parseInt(map.get("spp"));
		int start=(currentPage-1)*sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		param.put("col", map.get("col"));
		param.put("sort", map.get("sort"));
		return sqlSession.getMapper(BookMapper.class).search(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BookMapper.class).getTotalCount(map);
		logger.debug("total count: {}, currentPage: {}, sizePerPage: {}", totalCount, currentPage, sizePerPage);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		
		return pageNavigation;
	}

}
