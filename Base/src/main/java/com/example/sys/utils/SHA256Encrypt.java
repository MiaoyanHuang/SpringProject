package com.example.sys.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-256加密工具类
 * @author Huang Miaoyan
 */
public class SHA256Encrypt {
    public static String encrypt(String input) {
        try {
            // 获取MessageDigest实例，指定SHA-256算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将字符串转换为字节数组并更新MessageDigest
            byte[] encodedHash = digest.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理算法不支持的异常
            return null;
        }
    }
}
