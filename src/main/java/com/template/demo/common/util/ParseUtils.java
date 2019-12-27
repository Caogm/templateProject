package com.template.demo.common.util;

import org.springframework.util.StringUtils;

public class ParseUtils {
    public static String toString(Long num) {
        if(num == null){
            return null;
        }
        return String.valueOf(num);
    }
    public static Long toLong(String num) {
        if(StringUtils.isEmpty(num)){
            return 0L;
        }
        return Long.parseLong(num);
    }
}
