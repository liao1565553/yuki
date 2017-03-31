package com.yuki.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Test {
	public static void main(String[] args) {
		ByteSource credentialsSalt =ByteSource.Util.bytes("admin");
		Object result= new  SimpleHash("MD5", "21232f297a57a5a743894a0e4a801fc3",credentialsSalt, 3);
		System.out.println(result);
	}
}
