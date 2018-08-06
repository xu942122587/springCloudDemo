package com.ruixun.sincfin.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class JacksonUtil 
 * 
 * json字符与对像转换 
 *  
 * @version: $Revision$ $Date$ $LastChangedBy$ 
 * 
 */  
public final class JacksonUtil {  
  
    public static ObjectMapper objectMapper;

    public static <T> T readValue(String jsonStr, Class<T> valueType) {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.readValue(jsonStr, valueType);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.readValue(jsonStr, valueTypeRef);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  

    public static String toJson(Object object) {
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.writeValueAsString(object);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
  
}