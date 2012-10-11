package com.mayzn.note.android.net;

import com.google.common.collect.Lists;
import com.mayzn.note.android.utils.JsonUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteRestfulService {

    private final RestTemplate template;

    public static final String BASE_URL = "http://192.168.17.252:9000/v1/";


    public NoteRestfulService() {
        this.template = new RestTemplate();
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        converter.setObjectMapper(JsonUtils.getObjectMapper());
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(1);
        converters.add(converter);

        template.setMessageConverters(converters);

        ClientHttpRequestFactory factory = template.getRequestFactory();

        if(factory instanceof SimpleClientHttpRequestFactory) {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = (SimpleClientHttpRequestFactory)factory;
            simpleClientHttpRequestFactory.setConnectTimeout(1000*10);
            simpleClientHttpRequestFactory.setReadTimeout(1000*10);
        }

        if(factory instanceof  HttpComponentsClientHttpRequestFactory) {
            HttpComponentsClientHttpRequestFactory requestFactory = (HttpComponentsClientHttpRequestFactory)factory;
            requestFactory.setConnectTimeout(1000*10);
            requestFactory.setReadTimeout(1000*10);
        }
    }

    public <T> T get(Class<T> t , long id, String ... params) {
        T result = template.getForObject(BASE_URL + t.getSimpleName().toLowerCase() + "s/" + id , t , params);
        return result;
    }

    public <T> List<T> list(Class<T> t, TypeReference<List<T>> typeReference, String ... params) {
        List result = template.getForObject(BASE_URL + t.getSimpleName().toLowerCase() + "s" , List.class , params);
        String json = JsonUtils.toJson(result);
        return JsonUtils.fromJson(json , typeReference);
    }

    public <T> T put(Class<T> clazz, T data) {
        HttpHeaders headers = generateHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(data, headers);
        ResponseEntity<T> responseEntity = template.exchange(getUrl(clazz), HttpMethod.PUT, requestEntity, clazz);
        return responseEntity.getBody();
    }

    public <T> T post(Class<T> clazz, T data) {
        HttpHeaders headers = generateHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(data, headers);
        ResponseEntity<T> responseEntity = template.exchange(getUrl(clazz), HttpMethod.POST, requestEntity, clazz);
        return responseEntity.getBody();
    }

    public void delete(Object data) {
        return;
    }



    public HttpHeaders generateHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    private String getUrl(Class<?> clazz) {
        return BASE_URL + clazz.getSimpleName().toLowerCase() + "s";
    }
}
