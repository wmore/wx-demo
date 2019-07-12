package com.zhouzhi.wangyue.util;


import com.zhouzhi.wangyue.util.SHA1;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class WeiXinUtil {

    @Value("${com.constant.weixin.token}")
    private static String token;//自己在微信测试平台设置的token

    /**
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] str = new String[]{token,timestamp,nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ;i<str.length;i++){
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }

}
