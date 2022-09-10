package com.itranlin.reimu.common.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author itranlin
 */
@Slf4j
public class Encrypt {

    /**
     * MD5加密-32位小写
     */
    public static String md5(String encryptStr) {
        String result = "";
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            result = hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 encrypt fail", e);
        }
        return result;
    }

    /**
     * MD5加密-16位小写
     */
    public static String md5For16(String encryptStr) {
        return md5(encryptStr).substring(8, 24);
    }

    /**
     * base64加密
     */
    public static String base64Encode(String str) {
        byte[] b;
        String s;
        b = str.getBytes(StandardCharsets.UTF_8);
        s = Base64.getEncoder().encodeToString(b);
        return s;
    }

    /**
     * Base64解密
     */
    public static String base64Decode(String s) {
        byte[] b;
        String result = null;
        if (s != null) {
            b = Base64.getDecoder().decode(s);
            result = new String(b, StandardCharsets.UTF_8);

        }
        return result;
    }

}
