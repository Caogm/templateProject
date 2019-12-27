package com.template.demo.common.util;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class TransferUtil {
    public static Map<String, Object> toMap(Object model, String... fields){
        Set<String> fs = new HashSet<String>(Arrays.asList(fields));
        return toMap(model, fs);
    }
    public static Map<String, Object> toMap(Object model, Set<String> fields) {
        if (model == null) {
            return new HashMap<String,Object>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = model.getClass();
        boolean hasFileds = !CollectionUtils.isEmpty(fields);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(hasFileds && !fields.contains(fieldName)){
                continue;
            }
            try {
                Object value = field.get(model);
                map.put(fieldName, value);
            } catch (IllegalAccessException e) {
            }
        }
        return map;
    }

}
