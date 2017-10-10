package com.newlecture.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharaterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("hello filter~");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		/*
		System.out.println("bye filter~");*/
		
	}

}
