package com.bigdatalighter.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJsonString(Object value) {
        try{
            return mapper.writeValueAsString(value);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
