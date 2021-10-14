package com.ssafy.guestbook.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.guestbook.model.GuestbookDto;
import com.ssafy.util.DBUtil;

public class GuestbookDaoImpl implements GuestbookDao {

	private static GuestbookDao guestbookDao = new GuestbookDaoImpl();
	private DBUtil dbUtil;
	private GuestbookDaoImpl() {
		dbUtil = DBUtil.getInstance();
	};
	
	public static GuestbookDao getGuestbookDao() {
		return guestbookDao;
	}

	@Override
	public void registerArticle(GuestbookDto guestbookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("insert into guestbook (userid, subject, content, regtime) \n");
			registerArticle.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, guestbookDto.getUserId());
			pstmt.setString(2, guestbookDto.getSubject());
			pstmt.setString(3, guestbookDto.getContent());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public List<GuestbookDto> listArticle(String key, String word) throws Exception {
		List<GuestbookDto> list = new ArrayList<GuestbookDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select g.articleno, g.userid, g.subject, g.content, g.regtime, m.username \n");
			listArticle.append("from guestbook g, ssafy_member m \n");
			listArticle.append("where g.userid = m.userid \n");
			
			if(!word.isEmpty()) {
				if (key.equals("userid")) {
					listArticle.append("and g.userid=?\n");
				} else if (key.equals("subject")) {
					listArticle.append("and g.subject like ?\n");
					
				}
			}
			
			listArticle.append("order by g.articleno desc \n");
			
			pstmt = conn.prepareStatement(listArticle.toString());
			
			if(!word.isEmpty()) {
				if (key.equals("userid")) {
					pstmt.setString(1, word);
				} else if (key.equals("subject")) {
					pstmt.setString(1, "%"+word+"%");
				}
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestbookDto guestbookDto = new GuestbookDto();
				guestbookDto.setArticleNo(rs.getInt("articleno"));
				guestbookDto.setUserId(rs.getString("userid"));
				guestbookDto.setUserName(rs.getString("username"));
				guestbookDto.setSubject(rs.getString("subject"));
				guestbookDto.setContent(rs.getString("content"));
				guestbookDto.setRegTime(rs.getString("regtime"));
				
				list.add(guestbookDto);
			}
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public GuestbookDto getArticle(int articleNo) throws Exception {
		GuestbookDto guestbookDto=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder getArticle = new StringBuilder();
			getArticle.append("select subject, content \n");
			getArticle.append("from guestbook \n");
			getArticle.append("where articleno=? \n");
			pstmt = conn.prepareStatement(getArticle.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				guestbookDto = new GuestbookDto();
				guestbookDto.setArticleNo(articleNo);
				guestbookDto.setSubject(rs.getString("subject"));
				guestbookDto.setContent(rs.getString("content"));
			}
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return guestbookDto;
	}

	@Override
	public void updateArticle(GuestbookDto guestbookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("update guestbook \n");
			registerArticle.append("set subject=?, content=? \n");
			registerArticle.append("where articleno=?");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, guestbookDto.getSubject());
			pstmt.setString(2, guestbookDto.getContent());
			pstmt.setInt(3, guestbookDto.getArticleNo());
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("delete from guestbook \n");
			registerArticle.append("where articleno=?");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setInt(1, articleNo);
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
