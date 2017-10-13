package com.newlecture.webapp.aop.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator origin = new NewlecCalculator();
		
		
		// Proxy를 생성해서 실제 주업무 로직을 위임.

		
		//int data = cal.add(3, 4);
		//System.out.println(data);

	}

}
