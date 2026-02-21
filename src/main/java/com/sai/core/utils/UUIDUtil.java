package com.sai.core.utils;

import java.util.UUID;

public class UUIDUtil {
	
	public static String uuid() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-","");
	}
	
	public static void main(SaiStringUtils[] args) {
		System.out.println(uuid());
	}

}
