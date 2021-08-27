package friendly.budget.backend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtil {

    private static final Gson GSON = new GsonBuilder().create();

    private JsonUtil(){
        // Utility class
    }

    public static <T> T fromJson(String json, Class <T> clazz){
        return GSON.fromJson(json, clazz);
    }

    public static <T> String toJson(T object){
        return GSON.toJson(object);
    }
}
