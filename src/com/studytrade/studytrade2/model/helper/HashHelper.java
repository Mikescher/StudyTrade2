package com.studytrade.studytrade2.model.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import logging.STLog;

public class HashHelper {

	private HashHelper() {
		// NEVER USED
	}

	public static String doSHA1(String x) {
		return hash_toSHA1(x.getBytes());
	}

	private static String hash_toSHA1(byte[] convertme) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			STLog.log(e);
		}
		
		return byteArrayToHexString(md.digest(convertme));
	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		
		return result;
	}
}
