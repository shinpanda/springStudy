package com.newlecture.webapp.aop;

public class NewlecCalculator implements Calculator {

	@Override
	public int add(int x, int y) {
		// System.out.println("�ֺ�����");
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
		return x/y;
	}

}
