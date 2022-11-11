package org.kursovoi.server.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public interface ListSerializer extends Function<List<String>, String> {

    @Override
    default String apply(List<String> s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }
}
