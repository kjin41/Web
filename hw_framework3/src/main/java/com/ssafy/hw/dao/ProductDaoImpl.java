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

import com.ssafy.hw.dto.FileInfoDto;
import com.ssafy.hw.dto.ProductDto;
import com.ssafy.hw.util.DBUtil;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private DBUtil dbUtil;

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
			pstmt.close();

			List<FileInfoDto> fileInfos=productDto.getFileInfos();
			if (fileInfos!=null&&!fileInfos.isEmpty()) {
				System.out.println("file");
				sql="insert into fileinfo (no, savefolder, originfile, savefile)\n";
				sql+="values ";
				int size=fileInfos.size();
				for (int i=0; i<size; i++) {
					sql+="(?,?,?,?)";
					if (i!=fileInfos.size()-1) {
						sql+=",";
					}
				}
				pstmt=conn.prepareStatement(sql);
				int index=0;
				for (int i=0; i<size; i++) {
					FileInfoDto fileInfoDto=fileInfos.get(i);
					pstmt.setString(++index, productDto.getNo());
					pstmt.setString(++index, fileInfoDto.getSaveFolder());
					pstmt.setString(++index, fileInfoDto.getOriginFile());
					pstmt.setString(++index, fileInfoDto.getSaveFile());
				}
				pstmt.executeUpdate();
			}
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public ProductDto getArticle(String no) throws Exception {
		ProductDto productDto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="select no, name, price, descr\n"
					+ "from products\n"
					+ "where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				productDto=new ProductDto();
				productDto.setNo(rs.getString("no"));
				productDto.setName(rs.getString("name"));
				productDto.setPrice(rs.getInt("price"));
				productDto.setDesc(rs.getString("descr"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return productDto;
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
				String no = rs.getString("no");
				product.setNo(no);
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDesc(rs.getString("descr"));
				
				
				PreparedStatement pstmt2=null;
				ResultSet rs2=null;
				try {
					sql="select savefolder, originfile, savefile \n"
							+ "from fileinfo \n"
							+ "where no = ?\n";
					pstmt2=conn.prepareStatement(sql);
					pstmt2.setString(1, no);
					rs2=pstmt2.executeQuery();
					List<FileInfoDto> files=new ArrayList<FileInfoDto>();
					while(rs2.next()) {
						FileInfoDto fileInfoDto=new FileInfoDto();
						fileInfoDto.setSaveFolder(rs2.getString("savefolder"));
						fileInfoDto.setOriginFile(rs2.getString("originfile"));
						fileInfoDto.setSaveFile(rs2.getString("savefile"));
						
						files.add(fileInfoDto);
					}
					product.setFileInfos(files);
				} finally {
					dbUtil.close(rs2, pstmt2);
				}
				
				list.add(product);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public void updateArticle(ProductDto productDto) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		System.out.println(productDto);
		try {
			conn=dataSource.getConnection();
			String sql="update products set name=?, price=?, descr=?\n";
			sql+="where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productDto.getName());
			pstmt.setInt(2, productDto.getPrice());
			pstmt.setString(3, productDto.getDesc());
			pstmt.setString(4, productDto.getNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(String no) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
//			conn.setAutoCommit(false);
			
			String sql="delete from fileinfo\n";
			sql+="where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.executeUpdate();
			pstmt.close();
		
			sql="delete from products\n";
			sql+="where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.executeUpdate();
			pstmt.close();
			
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public List<FileInfoDto> fileInfoList(String no) throws Exception {
		List<FileInfoDto> files=new ArrayList<FileInfoDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select savefolder, originfile, savefile\n");
			sql.append("from fileinfo\n");
			sql.append("where no=?\n");
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				FileInfoDto fileInfoDto=new FileInfoDto();
				fileInfoDto.setSaveFolder(rs.getString("savefolder"));
				fileInfoDto.setOriginFile(rs.getString("originfile"));
				fileInfoDto.setSaveFile(rs.getString("savefile"));
				
				files.add(fileInfoDto);
			}
			
		} finally {
			dbUtil.close(rs,pstmt,conn);
		}
		
		return files;
	}

//	@Override
//	public ProductDto lastProduct(String no) throws Exception {
//		ProductDto productDto=null;
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		try {
//			conn=dataSource.getConnection();
//			String sql="select no, name, price, descr\n"
//					+ "from products\n"
//					+ "where no=?";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, no);
//			rs=pstmt.executeQuery();
//			if (rs.next()) {
//				productDto=new ProductDto();
//				productDto.setNo(rs.getString("no"));
//				productDto.setName(rs.getString("name"));
//				productDto.setPrice(rs.getInt("price"));
//				productDto.setDesc(rs.getString("descr"));
//			}
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//		return productDto;
//	}

}
