package com.Blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
	
	/**
     * 将字节数组里每个字节转成2个16进制位的字符串后拼接起来
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    private static String byteToHexString(byte b) {
        // byte类型赋值给int变量时，java会自动将byte类型转int类型，从低位类型到高位类型自动转换
        int n = b;

        // 将十进制数转十六进制
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;

        // d1和d2通过访问数组变量的方式转成16进制字符串；比如 d1 为12 ，那么就转为"c"; 因为int类型不会有a,b,c,d,e,f等表示16进制的字符
        return hexDigits[d1] + hexDigits[d2];
    }
    
    public static String MD5_32(String mixMessage) {
    	String resultString = null;
		MessageDigest md;
		
		try {
			
			// 1，创建MessageDigest对象
            md = MessageDigest.getInstance("MD5");
            
			// 2，向MessageDigest传送要计算的数据;传入的数据需要转化为指定编码的字节数组
//          md.update(source.getBytes());
            md.update(mixMessage.getBytes());
            // 3，计算摘要
            byte[] bytesResult = md.digest();

            // 第2步和第3步可以合并成下面一步
            // byte[] bytesResult = md.digest(origin.getBytes(charsetname));

            // 4,将字节数组转换为16进制位
            resultString = byteArrayToHexString( bytesResult );
			
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return resultString;
    }
}
