package com.stmps.groupOne.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer {
	   @Autowired
	   LoginInterceptor loginInterceptor;
	   @Autowired
	   LoginAPIInterceptor loginAPIInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
		   registry.addInterceptor(loginAPIInterceptor).excludePathPatterns("/","/api/login","/api/register","/api/logout","/css/*");
//			registry.addInterceptor(loginInterceptor).excludePathPatterns("/","/login","/register","/logout","/api/*","/css/*");
	   }
}
