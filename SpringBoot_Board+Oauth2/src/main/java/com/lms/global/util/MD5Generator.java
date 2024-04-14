package com.lms.global.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    private String result;

    public MD5Generator(String input) throws UnsupportedOperationException, NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(input.getBytes("UTF-8"));
        byte[] md5Hash = messageDigest.digest();
        StringBuilder hexMD5hash = new StringBuilder();
        for (byte b : md5Hash) {
            String hexString = String.format("%02x", b);
            hexMD5hash.append(hexString);
        }
        result = hexMD5hash.toString();
    }

    public String toString() {
        return result;
    }
}
