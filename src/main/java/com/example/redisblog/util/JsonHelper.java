package com.example.redisblog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


/**
 * JSON HELPER CLASS
 */
@SuppressWarnings("ALL")
public class JsonHelper {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static String toJSONString(Object o) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(o);
    }

    public static <D> D convertJSONStringToClass(String json, Class<D> destinationType) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, destinationType);
        } catch (IOException e) {

        }
        return null;
    }
}