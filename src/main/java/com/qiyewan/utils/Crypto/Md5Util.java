package com.qiyewan.utils.Crypto;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by lhzbxx on 2016/10/31.
 *
 * MD5
 */
public class Md5Util {
    public static String genMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
