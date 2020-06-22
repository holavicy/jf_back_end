package com.imooc.demo.util;

import java.util.UUID;

public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
    /**
     * 生成32位唯一字符串
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str=uuid.toString();
        String temp=str.substring(0,8)+str.substring(9,13)+str.substring(14,18)+str.substring(19,23)+str.substring(24);
        return temp;

    }
    /**
     * 生成32位唯一字符串组
     * @param number 需要生成字符串的个数
     * @return
     */
    public static String[] getUUID(int number) {
        if(number<1) {
            return null;
        }
        String str[]=new String[number];
        for (int i = 0; i < number; i++) {
            str[i]=getUUID();
        }
        return str;
    }
}
