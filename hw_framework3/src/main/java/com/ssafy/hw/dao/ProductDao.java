package com.ssafy.hw.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.hw.dto.FileInfoDto;
import com.ssafy.hw.dto.ProductDto;

public interface ProductDao {
	void registerArticle(ProductDto productDto) throws Exception;
	ProductDto getArticle(String no) throws Exception;
	List<ProductDto> listArticle(Map<String, String> map) throws Exception;
	void updateArticle(ProductDto productDto) throws Exception;
	void deleteArticle(String no) throws Exception;
//	ProductDto lastProduct(String no) throws Exception;
	List<FileInfoDto> fileInfoList(String no) throws Exception;
}
