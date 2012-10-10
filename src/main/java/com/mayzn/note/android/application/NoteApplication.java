package com.mayzn.note.android.application;

import android.app.Application;
import com.google.common.collect.Lists;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
