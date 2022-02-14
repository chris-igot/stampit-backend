package com.stmps.groupOne.utilities.misc;

import java.security.SecureRandom;

import com.stmps.groupOne.fixeddata.StringData;

public class CreateRandom {
	public static String urlSafe(int length) {
		String out = "";
		for (int i = 0; i < length; i++) {
			
			out += StringData.BASE64[number(StringData.BASE64.length)];
		}
		return out;
	}
	public static String urlSafe() {
		return urlSafe(16);
	}
	public static String password(int words) {
		String out = "";
		for (int i = 0; i < words; i++) {
			out += StringData.WORD_LIST[number(StringData.WORD_LIST.length)];
		}
		return out+number(99);
	}
	public static String password() {
		return password(3);
	}
	public static int number(int min, int max) {
		SecureRandom secureRandom = new SecureRandom();
		
		return secureRandom.nextInt(max - min)+min;
	}
	public static int number(int max) {
		return number(0,max);
	}
}
