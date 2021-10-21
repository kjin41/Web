package com.ssafy.hw.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hw.dao.ProductDao;
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
	public ProductDto getArticle(String productNo) throws Exception {
		return null;
	}

	@Override
	public List<ProductDto> listArticle(Map<String, String> map) throws Exception {
		return productDao.listArticle(map);
	}

	@Override
	public void updateArticle(ProductDto productDto) throws Exception {
	}

	@Override
	public void deleteArticle(String productNo) throws Exception {
	}

}
