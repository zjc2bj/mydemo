package com.lt.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Json数据格式转换类
 * 
 */
public class JsonClientUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把String型Json串转换成对应类型对象
     * 
     * @param content
     *            String型Json串
     * @param valueType
     *            类型
     * @return
     */
    public static <T> T jsonTo(String content, Class<T> valueType) {
        if (StringUtils.isNotBlank(content)) {
            try {
                return objectMapper.readValue(content, valueType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                return null;
            } catch (JsonMappingException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 把对象转换成String型Json串
     * 
     * @param value
     *            要转换的对象
     * @return
     */
    public static String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String型Json串遍历成tree
     * 
     * @param value
     *            要转换的对象
     * @return
     */
    public static JsonNode readTree(String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return objectMapper.readTree(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 把JsonNode串转换成对应类型对象
     * 
     * @param content
     *            String型Json串
     * @param valueType
     *            类型
     * @return
     */
    public static <T> T jsonNodeTo(JsonNode root, @SuppressWarnings("rawtypes")
    TypeReference valueTypeRef) {
        if (root != null) {
            try {
                return objectMapper.readValue(root, valueTypeRef);
            } catch (JsonParseException e) {
                e.printStackTrace();
                return null;
            } catch (JsonMappingException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
