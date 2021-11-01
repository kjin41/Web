package com.ssafy.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.ws.model.UserDto;

@Component
@SuppressWarnings("deprecation")
public class ConfirmInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		System.out.println(userDto);
		if (userDto==null) {
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}
		return true;
	}
}
