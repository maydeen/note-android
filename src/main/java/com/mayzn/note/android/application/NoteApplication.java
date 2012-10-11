package com.mayzn.note.android.application;

import android.app.Application;
import com.google.common.collect.Lists;
import com.mayzn.note.android.Config;
import com.mayzn.note.android.net.NoteRestfulService;
import com.mayzn.note.android.utils.JsonUtils;
import com.mayzn.note.android.utils.LogUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NoteApplication extends Application {

    private final static String TAG = NoteApplication.class.getSimpleName();
    private NoteRestfulService restfulService;
//    private UserService userService;

    @Override
    public void onCreate() {
        super.onCreate();

        //setting this applicaton as IoC
        restfulService = new NoteRestfulService();

        warmingObjectMapper();
    }

    private void warmingObjectMapper() {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        for (String clazzName : Config.MODEL_CLASS_NAMES) {
            waringClass(Config.MODEL_CLASS_NAMES + clazzName);
        }

        for (String clazzName : Config.REQUEST_OBJECT_CLASS) {
            waringClass(Config.REQUEST_OBJECT_CLASS_PACKAGE_NAME + clazzName);
        }

    }

    private void waringClass(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Object o = (Object)clazz.newInstance();
            String json = JsonUtils.toJson(o);
            o = JsonUtils.fromJson(json, clazz);
        } catch (ClassNotFoundException e) {
            LogUtils.LOGE(LogUtils.makeLogTag(TAG), "object mapping warming error", e);
        } catch (InstantiationException e) {
            LogUtils.LOGE(LogUtils.makeLogTag(TAG), "object mapping warming error", e);
        } catch (IllegalAccessException e) {
            LogUtils.LOGE(LogUtils.makeLogTag(TAG), "object mapping warming error", e);
        }
    }

    public NoteRestfulService getRestfulService() {
        return restfulService;
    }
}
