package com.tx.txcall.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Util {  
 
	/***
	 * md5加密
	 * @param source
	 * @return
	 */
    public static String encrypt(String source) {  
        if (source == null) {  
            source = "";  
        }  
        String result = "";  
        try {  
            result = encrypt(source, "MD5");  
        } catch (NoSuchAlgorithmException ex) {
            // this should never happen  
            throw new RuntimeException(ex);  
        } catch (UnsupportedEncodingException e) {
        	 throw new RuntimeException(e);  
		}
        return result;  
    }  
   
    private static String encrypt(String source, String algorithm)  
            throws NoSuchAlgorithmException, UnsupportedEncodingException {  
        byte[] resByteArray = encrypt(source.getBytes("UTF-8"), algorithm);  
        return toHexString(resByteArray);  
    }  
 
    private static byte[] encrypt(byte[] source, String algorithm)  
            throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.reset();  
        md.update(source);  
        return md.digest();  
    }  

    private static String toHexString(byte[] res) {  
        StringBuffer sb = new StringBuffer(res.length << 1);  
        for (int i = 0; i < res.length; i++) {  
            String digit = Integer.toHexString(0xFF & res[i]);  
            if (digit.length() == 1) {  
                digit = '0' + digit;  
            }  
            sb.append(digit);  
        }  
        return sb.toString().toLowerCase();  
    }  
    
    private static String byteArrayToHexString(byte b[]) {  
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
  
        return resultSb.toString();  
    }  
  
    private static String byteToHexString(byte b) {  
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
  
    public static String MD5Encode(String origin, String charsetname) {  
        String resultString = null;  
        try {  
            resultString = new String(origin);  
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {  
        }  
        return resultString;  
    }  
  
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 
    
    
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法皿MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘覿
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}