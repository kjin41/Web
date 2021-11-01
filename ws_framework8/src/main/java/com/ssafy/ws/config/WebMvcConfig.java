package com.ssafy.ws.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ws.interceptor.ConfirmInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 인터셉터 설정
	private final List<String> patterns = Arrays.asList("/book/*");

	@Autowired
	private ConfirmInterceptor confirmInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
	}

}
