package com.ruixun.sincfin.message.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORITHM_AEPP = "AES/ECB/PKCS5Padding";

    public AES() {
    }

    public static byte[] encrypt(byte[] content, byte[] password, String algorithm) {
        if (content != null && password != null) {
            try {
                Cipher cipher = null;
                if (algorithm.endsWith("PKCS7Padding")) {
                    cipher = Cipher.getInstance(algorithm, "BC");
                } else {
                    cipher = Cipher.getInstance(algorithm);
                }

                cipher.init(1, new SecretKeySpec(password, "AES"));
                return cipher.doFinal(content);
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static byte[] decrypt(byte[] content, byte[] password, String algorithm) {
        if (content != null && password != null) {
            try {
                Cipher cipher = null;
                if (algorithm.endsWith("PKCS7Padding")) {
                    cipher = Cipher.getInstance(algorithm, "BC");
                } else {
                    cipher = Cipher.getInstance(algorithm);
                }

                cipher.init(2, new SecretKeySpec(password, "AES"));
                byte[] bytes = cipher.doFinal(content);
                return bytes;
            } catch (Exception var5) {
                var5.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static byte[] encrypt(byte[] content, byte[] password, byte[] ivStr, String algorithm) {
        if (content != null && password != null) {
            try {
                Cipher cipher = null;
                if (algorithm.endsWith("PKCS7Padding")) {
                    cipher = Cipher.getInstance(algorithm, "BC");
                } else {
                    cipher = Cipher.getInstance(algorithm);
                }

                IvParameterSpec iv = new IvParameterSpec(ivStr);
                cipher.init(1, new SecretKeySpec(password, "AES"), iv);
                return cipher.doFinal(content);
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static byte[] decrypt(byte[] content, byte[] password, byte[] ivStr, String algorithm) {
        if (content != null && password != null) {
            try {
                Cipher cipher = null;
                if (algorithm.endsWith("PKCS7Padding")) {
                    cipher = Cipher.getInstance(algorithm, "BC");
                } else {
                    cipher = Cipher.getInstance(algorithm);
                }

                IvParameterSpec iv = new IvParameterSpec(ivStr);
                cipher.init(2, new SecretKeySpec(password, "AES"), iv);
                byte[] bytes = cipher.doFinal(content);
                return bytes;
            } catch (Exception var7) {
                var7.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
