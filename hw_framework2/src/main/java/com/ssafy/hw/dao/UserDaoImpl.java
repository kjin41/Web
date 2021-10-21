package com.ssafy.hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hw.dto.UserDto;
import com.ssafy.hw.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;
	
	private DBUtil dbUtil=DBUtil.getInstance();

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserDto userDto=null;
//		System.out.println(map.get("id")+ " "+map.get("pass"));
		try {
			conn=dataSource.getConnection();
			String sql="select id, name\n"
					+ "from prousers\n"
					+ "where id=? and pass=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("pass"));
			rs=pstmt.executeQuery();
			if (rs.next()) {
				userDto=new UserDto();
				userDto.setId(map.get("id"));
				userDto.setName(rs.getString("name"));
			}
		} finally{
			dbUtil.close(rs, pstmt, conn);
		}
		return userDto;
	}

	@Override
	public void insert(UserDto userDto) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="insert into prousers (id, pass, name)\n"
					+ "values (?,?,?)\n";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPass());
			pstmt.setString(3, userDto.getName());
			pstmt.executeUpdate();
		} finally{
			dbUtil.close(pstmt, conn);
		}
	}

}
