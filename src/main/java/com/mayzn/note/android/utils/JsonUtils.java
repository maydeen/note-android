package com.mayzn.note.android.utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {


    private static final ObjectMapper MAPPER = new ObjectMapper();


    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }

    public static class JsonEncodeException extends RuntimeException {
        private static final long serialVersionUID = 4975703115049362769L;

        public JsonEncodeException(Throwable cause) {
            super(cause);
        }
    }

    public static class JsonDecodeException extends RuntimeException {
        private static final long serialVersionUID = -2651564042039413190L;

        public JsonDecodeException(Throwable cause) {
            super(cause);
        }
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> clazz) {
        try {
            return MAPPER.readValue(inputStream, clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static JsonNode fromJson(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static JsonNode fromJson(InputStream inputStream) {
        try {
            return MAPPER.readTree(inputStream);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonEncodeException(e);
        }
    }


    /**
     * use generally
     * List<SomeClass> list = JsonUtils.fromJson(jsonString, new TypeReference<List<SomeClass>>() { }
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

}
