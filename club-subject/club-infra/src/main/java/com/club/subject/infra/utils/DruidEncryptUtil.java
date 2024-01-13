package com.club.subject.infra.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密 util
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
public class DruidEncryptUtil {


    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.println("privateKey = " + privateKey);
            System.out.println("publicKey = " + publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt = " + encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println("decrypt = " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String url = encrypt("localhost:3306");
        String username = encrypt("root");
        String pwd = encrypt("pray1314");
        System.out.println(" = " );
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("pwd = " + pwd);
    }
}
