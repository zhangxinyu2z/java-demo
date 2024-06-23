package com.z2xinyu.reflection.cglib;

public class Atm{/*自动取款机,目标类，不需要实现指定接口*/
	public String withdraw(double amount) {
		return "取出"+ amount +"元";
	}
	
	public String checkBalance() {
		return "当前余额:" + 1200 + "元";
	}
}

