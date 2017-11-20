package com.oliver.base.util;

import java.security.MessageDigest;

/**
 * description：//MD5加密
 * author：oliver
 * email：qiwei0727@163.com
 * date：on 2017/11/19
 */

public class MD5Util {
    public static String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
