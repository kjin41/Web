package com.ssafy.hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hw.dto.ProductDto;
import com.ssafy.hw.util.DBUtil;

@Repository
public class ProductDaoImpl implements ProductDao {

	private DBUtil dbUtil=DBUtil.getInstance();

	@Autowired
	private DataSource dataSource;

	@Override
	public void registerArticle(ProductDto productDto) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="insert into products (no, name, price, descr)\n";
			sql+="values (?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productDto.getNo());
			pstmt.setString(2, productDto.getName());
			pstmt.setInt(3, productDto.getPrice());
			pstmt.setString(4, productDto.getDesc());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public ProductDto getArticle(String productNo) throws Exception {
		return null;
	}

	@Override
	public List<ProductDto> listArticle(Map<String, String> map) throws Exception {
		List<ProductDto> list=new ArrayList<ProductDto>();  
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String key=map.get("key");
		String word=map.get("word");
		try {
			conn=dataSource.getConnection();
			String sql="select no, name, price, descr\n";
			sql+="from products\n";
			if (word!=null && !word.isEmpty()) {
				if (key.contentEquals("name")) {
					sql+="where name like ?\n";
				} else {
					sql+="where price<=?\n";
				}
			}
			pstmt=conn.prepareStatement(sql);
			
			if (word!=null & !word.isEmpty()) {
				if (key.contentEquals("name")) {
					pstmt.setString(1, "%"+word+"%");
				} else {
					pstmt.setString(1, word);
				}
				
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductDto product=new ProductDto();
				product.setNo(rs.getString("no"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("descr"));
				
				list.add(product);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public void updateArticle(ProductDto productDto) throws Exception {
	}

	@Override
	public void deleteArticle(String productNo) throws Exception {
	}

}
