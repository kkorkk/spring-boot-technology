package com.demo.springbootshirodemo.utils;

import com.demo.springbootshirodemo.common.CoreConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author：kkorkk.
 * @Date：2018/10/22 18:23
 * @Description：MD5工具类
 */
public class MD5Util {

    /**
     * @author: kkorkk
     * @description: 获取MD5加密后的字符串
     * @date 2018/10/22 18:27
     * @param source
     * @return String
     * */
    public static String getMD5Str(String source){
       String result = new SimpleHash(CoreConstants.PASSWORD_ALGORITHM_NAME,source,
               CoreConstants.PASSWORD_SALT,
               CoreConstants.PASSWORD_HASH_ITERATIONS).toHex();
       return result;
    }

    /**
     * @author: kkorkk
     * @description: 加盐、多次加密的MD5生成
     * @date 2018/10/23 9:42
     * @param source
     * @param salt
     * @param iterations
     * @return String
     * */
    public static String getMD5Str(String source,String salt,int iterations){
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String resultStr = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (!StringUtils.isBlank(salt)){
                messageDigest.update(salt.getBytes());
            }
            //防止用户输入小于1的数字导致错误
            iterations = Math.max(iterations,1);
            byte[] resultBytes = messageDigest.digest(source.getBytes());
            for (int i = 0; i < iterations-1; i++) {
                messageDigest.reset();
                resultBytes = messageDigest.digest(resultBytes);
            }
            char[] resultChars = new char[resultBytes.length * 2];
            for (int i = 0; i < resultBytes.length; i++) {
                resultChars[i*2] = hexDigits[resultBytes[i]>>>4&0x0F];
                resultChars[i*2+1] = hexDigits[resultBytes[i]&0x0F];
            }
            resultStr= new String(resultChars);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return resultStr;
    }

}
