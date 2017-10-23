package com.newlecture.webapp.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calculator origin = new NewlecCalculator();

		// Proxy를 생성해서 실제 주업무 로직을 위임.
		Calculator cal = (Calculator) Proxy.newProxyInstance(NewlecCalculator.class.getClassLoader(),
				new Class[] { Calculator.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						System.out.println("사전처리 보조 업무"); // 위에 처리할 게 있음 여기서
						Object result = method.invoke(origin, args);
						// 밑에서 처리할 게 있으면 여기서
						return result;
					}
				});

		int data = cal.add(3, 4);
		System.out.println(data);

	}

}
