package com.newlecture.webapp.aop.springannotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		
		
		// Proxy를 생성해서 실제 주업무 로직을 위임.
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/webapp/aop/spring/aop-context.xml");
		
		Calculator cal = (Calculator) context.getBean("cal");
		
		int data = cal.add(3, 4);
		//data = cal.div(3, 0);
		
		System.out.println(data);

	}

}
