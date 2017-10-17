package com.newlecture.webapp.aop.springannotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerAspect {
	
	@Pointcut("within(com.newlecture.webapp.aop.spring.NewlecCalculator")
	public void pointcutMethod(){
		
	}
	
	@Around("pointcutMethod()")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println(joinPoint.getSignature().toShortString()); // 메소드 명을 얻음
		
		Object obj = joinPoint.proceed();
		
		
		System.out.println(obj);
		
		return obj;
	}
	
	@Before("within(com.newlecture.webapp.aop.spring.NewlecCalculator")
	public void beforMethod() {
		System.out.println("beforeMethod() 실행");
	}
}
