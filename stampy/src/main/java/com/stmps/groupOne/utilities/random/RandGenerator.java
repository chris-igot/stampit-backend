package com.stmps.groupOne.utilities.random;

import java.security.SecureRandom;

public class RandGenerator {
	public static String urlSafe(int length) {
		SecureRandom secureRandom = new SecureRandom();
		String[] b64 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "_"};
		String out = "";
		for (int i = 0; i < length; i++) {
			out += b64[secureRandom.nextInt(64)];
		}
		return out;
	}
	public static String urlSafe() {
		return urlSafe(16);
	}
}
