package com.ssafy.hw.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hw.dao.ProductDao;
import com.ssafy.hw.dto.FileInfoDto;
import com.ssafy.hw.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;


	@Override
	public void registerArticle(ProductDto productDto) throws Exception {
		productDao.registerArticle(productDto);
	}

	@Override
	public ProductDto getArticle(String no) throws Exception {
		return productDao.getArticle(no);
	}

	@Override
	public List<ProductDto> listArticle(Map<String, String> map) throws Exception {
		return productDao.listArticle(map);
	}

	@Override
	public void updateArticle(ProductDto productDto) throws Exception {
		productDao.updateArticle(productDto);
	}

	@Override
	public void deleteArticle(String no, String path) throws Exception {
		List<FileInfoDto> fileList=productDao.fileInfoList(no);
		productDao.deleteArticle(no);
		// 폴더에서 사진 지우기
		for (FileInfoDto fileInfoDto: fileList) {
			File file=new File(path+File.separator+fileInfoDto.getSaveFolder()+File.separator+fileInfoDto.getSaveFile());
			file.delete();
		}
	}

//	@Override
//	public ProductDto lastProduct(String no) throws Exception {
//		return productDao.lastProduct(no);
//	}

}
