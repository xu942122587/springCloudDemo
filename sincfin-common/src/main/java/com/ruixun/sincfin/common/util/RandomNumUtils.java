package com.ruixun.sincfin.common.util;

import java.util.UUID;

/**
 * <p> 生成随机数 </p>
 */
public class RandomNumUtils {


    public static String[] build(int number) {

        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

}
