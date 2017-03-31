package com.yuki.test;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {
	public static void main(String[] args) {
		Object result= new  SimpleHash("MD5", "21232f297a57a5a743894a0e4a801fc3", null, 3);
		System.out.println(result);
	}
}
