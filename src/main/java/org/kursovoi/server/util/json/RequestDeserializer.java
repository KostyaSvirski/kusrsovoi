package org.kursovoi.server.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

@Component
public interface RequestDeserializer<R> extends Function<String, R> {

    @Override
    default R apply(String t) {
        Gson gson = new Gson();
        return gson.fromJson(t, (Class<R>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1]);
    }
}
