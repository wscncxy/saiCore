package com.sai.core.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class CryptoUtil {

    public static String encrypt(String content) {
        try {
            if (StringUtil.isNotBlank(content)) {
                KeyGenerator AESGenerator = AESGenerator = KeyGenerator.getInstance("AES");
                SecureRandom SHA1PRNGRandom = SecureRandom.getInstance("SHA1PRNG");
                Cipher AESCipher = AESCipher = Cipher.getInstance("AES");
                SHA1PRNGRandom.setSeed(content.getBytes());
                AESGenerator.init(128, SHA1PRNGRandom);
                SecretKey secretKey = AESGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                byte[] byteContent = content.getBytes("utf-8");
                AESCipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] result = AESCipher.doFinal(byteContent);
                return ByteUtil.byteArr2HexStr(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String ciphertext) {
        try {
            if (StringUtil.isNotBlank(ciphertext)) {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");// 修复linux系统报错问题
                secureRandom.setSeed(ciphertext.getBytes());
                kgen.init(128, secureRandom);
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] result = cipher.doFinal(ByteUtil.hexStr2ByteArr(ciphertext));
                return new String(result, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
