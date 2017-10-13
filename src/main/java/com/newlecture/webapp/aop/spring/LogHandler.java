package com.newlecture.webapp.aop.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogHandler implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		System.out.println("스프링 사전 보조 업무");
		Object result = method.proceed();
		System.out.println("스프링 사후 보조 업무");
		return result;
	}

}
