package org.kursovoi.server.util.interceptor;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public interface ResponseSerializer<T> extends Function<T, String> {

    @Override
    default String apply(T t){
        Gson gson = new Gson();
        return gson.toJson(t);
    }
}
