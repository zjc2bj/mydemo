package com.lt.sso;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * SSODESede对称加密算法
 * 
 * */
public class SSOSecureUtil {

    private static byte[] getKey() {
        final byte[] keyBytes = new byte[] { (byte) 0x9c, 0x3a, (byte) 0xe7, (byte) 0xcc, 0x51, 0xd, 0x77, (byte) 0xa5,
                0x6e, (byte) 0xdd, 0x3b, 0x5d, 0x7c, 0x15, (byte) 0xa3, 0x29, (byte) 0x9c, 0x3a, (byte) 0xe7,
                (byte) 0xcc, 0x51, 0xd, 0x77, (byte) 0xa5 };// 24字节的密钥,BOSS系统提供的密钥
        return keyBytes;
    }

    /**
     * 密钥算法
     * */
    public static final String KEY_ALGORITHM = "DESede";

    /**
     * 加密/解密算法/工作模式/填充方式
     * */
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    public static final String SHORT_ALGORITHM = "DESede/ECB/NoPadding";

    /**
     * 生成密钥
     * 
     * @return byte[] 二进制密钥
     * */
    @SuppressWarnings("unused")
    private static byte[] initkey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);// 实例化密钥生成器
            kg.init(168); // 初始化密钥生成器
            SecretKey secretKey = kg.generateKey(); // 生成密钥
            return secretKey.getEncoded(); // 获取二进制密钥编码形式
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换密钥
     * 
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * */
    private static Key toKey(byte[] key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key); // 实例化Des密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);// 实例化密钥工厂
        SecretKey secretKey = keyFactory.generateSecret(dks);// 生成密钥
        return secretKey;
    }

    /**
     * 加密数据
     * 
     * @param data
     *            待加密数据
     * @param key
     *            密钥
     * @return byte[] 加密后的数据
     * */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);// 还原密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 实例化
        cipher.init(Cipher.ENCRYPT_MODE, k);// 初始化，设置为加密模式
        return cipher.doFinal(data);// 执行操作
    }

    /**
     * 解密数据
     * 
     * @param data
     *            待解密数据
     * @param key
     *            密钥
     * @return byte[] 解密后的数据
     * */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key); // 欢迎密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化
        cipher.init(Cipher.DECRYPT_MODE, k); // 初始化，设置为解密模式
        return cipher.doFinal(data);// 执行操作
    }

    /**
     * 解密数据
     * 
     * @param str
     * @return
     */
    public static String DataEncrypt(String str) {
        String encrypt = null;
        try {
            byte[] ret = encrypt(str.getBytes("UTF-8"), getKey());
            encrypt = new String(Base64.encode(ret));
        } catch (Exception e) {
            e.printStackTrace();
            encrypt = str;
        }
        return encrypt;
    }

    /**
     * 加密数据
     * 
     * @param str
     * @return
     */
    public static String DataDecrypt(String str) {
        String decrypt = null;
        try {
            byte[] ret = decrypt(Base64.decode(str), getKey());
            decrypt = new String(ret, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            decrypt = str;
        }
        return decrypt;
    }

    /**
     * Cookie 3DES加密
     * 
     * @param str
     * @return
     */
    public static String cookieEncrypt(String src) {
        try {
            SecretKey deskey = new SecretKeySpec(getKey(), KEY_ALGORITHM);// 生成密钥
            Cipher c1 = Cipher.getInstance(SHORT_ALGORITHM);// 加密
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return bytesToHexString(c1.doFinal(fill8byte(src)));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * Cookie 3DES解密
     * 
     * @param str
     * @return
     */
    public static String cookieDecrypt(String src) {
        try {
            SecretKey deskey = new SecretKeySpec(getKey(), KEY_ALGORITHM);// 生成密钥
            Cipher c1 = Cipher.getInstance(SHORT_ALGORITHM);// 解密
            c1.init(Cipher.DECRYPT_MODE, deskey);
            byte[] data = c1.doFinal(hexStringToByte(src));
            return new String(drop0byte(data));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密配合方法，补为8位的整数倍，配合C++算法手动补0的ASCII 48
     * 
     * @param src
     * @return 处理后的byte数组
     */
    private static byte[] fill8byte(String src) {
        byte[] temp = src.getBytes();
        int ret = temp.length % 8;
        int length = temp.length;
        if (ret != 0) {
            int offset = 8 - ret;
            int newlength = length + offset;
            byte[] result = new byte[newlength];
            for (int i = 0; i < temp.length; i++) {
                result[i] = temp[i];
            }
            for (int j = length; j < newlength; j++) {
                result[j] = 0x00;
            }
            return result;
        } else {
            return temp;
        }
    }

    /**
     * 把字节数组转换成16进制字符串
     * 
     * @param bArray
     * @return
     */
    private static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 解密配合方法，除去补位产生的16进制
     * 
     * @param src
     * @return
     */
    private static byte[] drop0byte(byte[] data) {
        int position = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0x00) {
                position = i;
                break;
            }
        }
        if (position == -1) {
            return data;
        } else {
            byte[] result = new byte[position];
            for (int i = 0; i < position; i++) {
                result[i] = data[i];
            }
            return result;
        }
    }

    /**
     * 把16进制字符串转换成字节数组
     * 
     * @param hex
     * @return
     */
    private static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * char转换为byte
     * 
     * @param c
     * @return
     */
    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 进行加解密的测试
     * 
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // ActivateUser user = new ActivateUser();
        // User innerUser = new User();
        // innerUser.setLoginName("liantuo");
        // innerUser.setPassword("1111");
        // innerUser.setCodeName("北京联拓");
        // innerUser.setEmail("haha@163.com");
        // innerUser.setJamPassword("1111");
        // innerUser.setMobile("12312321321321");
        // innerUser.setName("联拓天津");
        // innerUser.setSrcFrom(1);
        // innerUser.setStatus(1);
        // Agency agency = new Agency();
        // agency.setCity("河北");
        // agency.setCityCode("hebei");
        // agency.setFullName("北京联拓天际游有限公司");
        // agency.setIata_no("11111");
        // agency.setId(1);
        // agency.setMinorType(1);
        // agency.setName("liantuot");
        // agency.setOffice_no("111111");
        // agency.setProvince("河北");
        // agency.setSrcFrom(1);
        // agency.setStatus(1);
        // agency.setSuperiorId(1);
        // agency.setType(1);
        // innerUser.setAgency(agency);
        // Department department = new Department();
        // department.setAgencyId(1);
        // department.setId(1);
        // department.setName("管理部");
        // department.setSuperiorId(1);
        // innerUser.setDepartment(department);
        // innerUser.setAgency(agency);
        // user.setUser(innerUser);
        // JSONObject jsonObject = JSONObject.fromObject(user);
        // String str = jsonObject.toString();
        // String str = "liantuo";
        // System.out.println(str);
        // String result = cookieEncrypt(str);
        // System.out.println(result);
        // System.out.println(result.length());
        // String back = cookieDecrypt(result);
        // System.out.println(back);
    }
}
