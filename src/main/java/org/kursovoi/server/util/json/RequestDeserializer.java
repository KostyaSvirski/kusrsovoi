package org.kursovoi.server.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class RequestDeserializer<R> {

    public R apply(String t, Class<R> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(t, clazz);
    }
}
