package com.furniturecloud.security.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtil {
	public static KeyPair getRSAKey() {
		KeyPair kp;
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			kp=kpg.generateKeyPair();
		}
		catch(Exception e) {
			throw new IllegalStateException();
		}
		return kp;
	}
	

}
