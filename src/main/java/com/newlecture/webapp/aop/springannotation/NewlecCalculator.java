package com.newlecture.webapp.aop.springannotation;

public class NewlecCalculator implements Calculator {

	@Override
	public int add(int x, int y) {
		// System.out.println("주변업무");
		return x+y;
	}

	@Override
	public int sub(int x, int y) {
		// TODO Auto-generated method stub
		return x-y;
	}

	@Override
	public int multi(int x, int y) {
		// TODO Auto-generated method stub
		return x*y;
	}

	@Override
	public int div(int x, int y) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = x/y;
		} catch (Exception e) {
			System.out.println("에러 발생");
		}
		return result;
	}

}
