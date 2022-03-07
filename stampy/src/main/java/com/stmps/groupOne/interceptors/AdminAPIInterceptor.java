package com.stmps.groupOne.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.UserService;

@Component
public class AdminAPIInterceptor implements HandlerInterceptor {
	@Autowired
	UserService userServ;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String userId = (String) request.getSession().getAttribute("id");
		if(userId == null) {
			response.sendError(401);
			return false;
		} else {
			User user = userServ.getById(userId);
			
			if(user == null) {
				response.sendError(418);
				return false;
			} else {
				if(user.hasRole("admin")) {
					return true;
				} else {
					System.out.println(user.getUsername());
					response.sendError(403);
					return false;
				}
			}
		}
	}
}
