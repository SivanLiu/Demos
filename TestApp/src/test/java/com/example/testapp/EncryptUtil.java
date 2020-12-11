package com.example.testapp;
/*
 * Project: com.cmcc.vr.manage.util
 *
 * File Created at 2020/4/10
 *
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kezuyong
 * @Type EncryptUtil
 * @Desc
 * @date 2020/4/10 9:15
 */
public class EncryptUtil {

    public static void main(String[] args) {
        System.out.println(EncryptUtil.md5("18867105717"));
    }

    public static String md5(String text) {
        String result = null;
        if ("".equals(text) || null == text) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] btInput = text.getBytes("UTF-8");
            md.update(btInput);
            byte[] btResult = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : btResult) {
                int bt = b & 0xff;
                if (bt < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(bt));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
