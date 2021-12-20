package com.stmps.groupOne.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		Long userId = (Long) request.getSession().getAttribute("id");
//		String profileId = (String) request.getSession().getAttribute("profile_id");

		if(userId == null) {
			System.out.println("not logged in!");
			response.sendRedirect("/login");
		} 
//		else {
//			String route = request.getServletPath();
//			if(route.equals("/")) {
//				response.sendRedirect("/home");
//			}
//			
//		}
		
		return true;
	}
}
