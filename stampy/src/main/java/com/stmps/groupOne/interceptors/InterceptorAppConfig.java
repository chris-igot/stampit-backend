package com.stmps.groupOne.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer {
	   @Autowired
	   LoginAPIInterceptor loginAPIInterceptor;
	   @Autowired
	   AdminAPIInterceptor adminAPIInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
		   registry.addInterceptor(loginAPIInterceptor)
		   			.excludePathPatterns("/","/api/login","/api/admin/login","/api/register","/api/logout","/css/*","/error");

		   registry.addInterceptor(adminAPIInterceptor)
		   			.addPathPatterns("/api/admin/**")
		   			.excludePathPatterns("/","/api/login","/api/admin/login","/api/register","/api/logout","/css/*","/error");

	   }
}
