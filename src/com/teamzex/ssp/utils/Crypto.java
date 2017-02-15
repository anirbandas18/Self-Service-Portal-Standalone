package com.teamzex.ssp.utils;

public class Crypto {
	
	public static String encrypt(String text) {
		String encrypted = "";
		int shift = text.length();
		for(int i = 0 ; i < shift ; i++ ) {
			int ascii = text.charAt(i);
			ascii = ascii + shift;
			encrypted = encrypted + (char)ascii;
		}
		return encrypted;
	}

	public static String decrypt(String text) {
		String decrypted = "";
		int shift = text.length();
		for(int i = 0 ; i < shift ; i++ ) {
			int ascii = text.charAt(i);
			ascii = ascii - shift;
			decrypted = decrypted + (char)ascii;
		}
		return decrypted;
	}
}
