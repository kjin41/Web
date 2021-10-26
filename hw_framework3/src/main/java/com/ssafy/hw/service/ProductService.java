package com.ssafy.hw.service;

import java.util.List;
import java.util.Map;

import com.ssafy.hw.dto.ProductDto;

public interface ProductService {
	void registerArticle(ProductDto productDto) throws Exception;
	ProductDto getArticle(String no) throws Exception;
	List<ProductDto> listArticle(Map<String, String> map) throws Exception;
	void updateArticle(ProductDto productDto) throws Exception;
	void deleteArticle(String no, String path) throws Exception;
//	ProductDto lastProduct(String no) throws Exception;

}
