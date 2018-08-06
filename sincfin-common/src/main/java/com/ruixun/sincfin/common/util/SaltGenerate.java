package com.ruixun.sincfin.common.util;

import java.security.SecureRandom;
import java.util.Random;

public class SaltGenerate {
	
	
	public static String generate(){
		return generate(4);
	}
	
	public static String generate(int length){
		Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[length];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
	}

}
