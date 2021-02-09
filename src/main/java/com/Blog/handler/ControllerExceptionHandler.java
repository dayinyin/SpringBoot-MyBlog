package com.Blog.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Throwable {
		
		 ModelAndView mv=new ModelAndView();
	     mv.addObject("url",request.getRequestURL());
	     mv.addObject("exception",e);
	     mv.setViewName("error/error");
	     return mv;
	}
	
}
