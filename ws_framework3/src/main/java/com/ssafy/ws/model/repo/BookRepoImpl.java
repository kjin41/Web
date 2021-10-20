package com.ssafy.ws.model.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ws.util.DBUtil;
import com.ssafy.ws.model.BookDto;

@Repository
public class BookRepoImpl implements BookRepo {

	@Autowired
	private DataSource dataSource;
	
	private DBUtil dbUtil=DBUtil.getInstance();
	
	@Override
	public int insert(BookDto bookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into book (isbn, title, author, price, content, img) \n");
			sql.append("values (?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookDto.getIsbn());
			pstmt.setString(2, bookDto.getTitle());
			pstmt.setString(3, bookDto.getAuthor());
			pstmt.setInt(4, bookDto.getPrice());
			pstmt.setString(5, bookDto.getContent());
			pstmt.setString(6, bookDto.getImg());
			cnt=pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public int update(BookDto bookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update book \n");
			sql.append("set title=?, author=?, price=?, content=?, img=? \n");
			sql.append("where isbn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookDto.getTitle());
			pstmt.setString(2, bookDto.getAuthor());
			pstmt.setInt(3, bookDto.getPrice());
			pstmt.setString(4, bookDto.getContent());
			pstmt.setString(5, bookDto.getImg());
			pstmt.setString(6, bookDto.getIsbn());
			cnt=pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public int delete(String isbn) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from book \n");
			sql.append("where isbn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, isbn);
			cnt=pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public BookDto select(String isbn) throws Exception {
		BookDto bookDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select title, author, price, content, img \n");
			sql.append("from book \n");
			sql.append("where isbn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bookDto = new BookDto();
				bookDto.setIsbn(isbn);
				bookDto.setAuthor(rs.getString("author"));
				bookDto.setTitle(rs.getString("title"));
				bookDto.setPrice(rs.getInt("price"));
				bookDto.setContent(rs.getString("content"));
				bookDto.setImg(rs.getString("img"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return bookDto;
	}

	@Override
	public List<BookDto> search() throws Exception {
		List<BookDto> list = new ArrayList<BookDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select isbn, title, author, price, content, img \n");
			sql.append("from book \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookDto bookDto = new BookDto();
				bookDto.setIsbn(rs.getString("isbn"));
				bookDto.setAuthor(rs.getString("author"));
				bookDto.setTitle(rs.getString("title"));
				bookDto.setPrice(rs.getInt("price"));
				bookDto.setContent(rs.getString("content"));
				bookDto.setImg(rs.getString("img"));
				
				list.add(bookDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

}
