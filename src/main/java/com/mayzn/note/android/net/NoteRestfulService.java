package com.mayzn.note.android.net;

import com.google.common.collect.Lists;
import com.mayzn.note.android.utils.JsonUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteRestfulService {

    private static final RestTemplate TEMPLATE = new RestTemplate(false);

    private static final String BASE_URL = "http://192.168.17.252:9000/v1/";

    static {
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        converter.setObjectMapper(JsonUtils.getObjectMapper());
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(1);
        converters.add(converter);

        TEMPLATE.setMessageConverters(converters);
    }

    private NoteRestfulService() {}

    public static <T> T get(Class<T> t , long id, String ... params) {
        T result = TEMPLATE.getForObject(BASE_URL + t.getSimpleName().toLowerCase() + "s/" + id , t , params);
        return result;
    }

    public static <T> List<T> list(Class<T> t, TypeReference<List<T>> typeReference, String ... params) {
        List result = TEMPLATE.getForObject(BASE_URL + t.getSimpleName().toLowerCase() + "s" , List.class , params);
        String json = JsonUtils.toJson(result);
        return JsonUtils.fromJson(json , typeReference);
    }

    public static <T> T put(Class<T> clazz, T data) {
        HttpHeaders headers = generateHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(data, headers);
        ResponseEntity<T> responseEntity = TEMPLATE.exchange(getUrl(clazz), HttpMethod.PUT, requestEntity, clazz);
        return responseEntity.getBody();
    }

    public static <T> T post(Class<T> clazz, T data) {
        HttpHeaders headers = generateHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(data, headers);
        ResponseEntity<T> responseEntity = TEMPLATE.exchange(getUrl(clazz), HttpMethod.POST, requestEntity, clazz);
        return responseEntity.getBody();
    }

    public static void delete(Object data) {
        return;
    }



    public static HttpHeaders generateHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    private static String getUrl(Class<?> clazz) {
        return BASE_URL + clazz.getSimpleName().toLowerCase() + "s";
    }
}
