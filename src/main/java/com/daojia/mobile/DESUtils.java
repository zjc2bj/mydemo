package com.daojia.mobile;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 美到家----加密算法类
 *
 * @author mdj
 */
public class DESUtils {

    /**
     * 字节转16进制数
     *
     * @param bs
     * @return
     */
    final static char[] CS = "0123456789ABCDEF".toCharArray();

    public static String encrypt(Key key, String str) throws Exception {
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] data = c.doFinal(str.getBytes("utf-8"));
        return Base64.encode(data);
    }

    public static String decrypt(Key key, String base64Str) throws Exception {
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] data = Base64.decode(base64Str);
        byte[] deData = c.doFinal(data);
        String deDataStr = new String(deData, "utf-8");
        return deDataStr;
    }

    public static Key desKeyGenerator() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(secureRandom);
        return kg.generateKey();
    }

    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    public static String bytesToHex(byte[] bs) {
        char[] cs = new char[bs.length * 2];
        int io = 0;
        for (int n : bs) {
            cs[io++] = CS[(n >> 4) & 0xF];
            cs[io++] = CS[(n >> 0) & 0xF];
        }
        return new String(cs);
    }

    /**
     * 加密 秘钥长度大于8个字节
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static String encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //正式执行加密操作
            byte[] b = cipher.doFinal(datasource);
            return Base64.encode(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密  秘钥长度大于8个字节
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static String decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        byte[] b = cipher.doFinal(Base64.decode(new String(src)));
        return new String(b, "utf-8");
    }
}