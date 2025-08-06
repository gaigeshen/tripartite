package work.gaigeshen.tripartite.core.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.*;

/**
 * @author gaigeshen
 * @see <a href="https://github.com/google/gson">Google Gson</a>
 */
class GsonJsonCodec implements JsonCodec {

    public static final GsonJsonCodec INSTANCE = new GsonJsonCodec();

    private final Gson gson;

    public GsonJsonCodec(Gson gson) {
        ArgumentValidate.notNull(gson, "gson cannot be null");
        this.gson = gson;
    }

    public GsonJsonCodec() {
        this(new GsonBuilder().create());
    }

    @Override
    public String encode(Object obj) {
        ArgumentValidate.notNull(obj, "object cannot be null");
        return gson.toJson(obj);
    }

    @Override
    public <T> T decodeObject(String json, Class<T> resultClass) {
        ArgumentValidate.notTrue(Objects.isNull(json) || Objects.isNull(resultClass),
                "json and result class cannot be null");
        return gson.fromJson(json, resultClass);
    }

    @Override
    public <E> Collection<E> decodeCollection(String json, Class<E> itemClass) {
        ArgumentValidate.notTrue(Objects.isNull(json) || Objects.isNull(itemClass),
                "json and item class cannot be null");
        return gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, itemClass).getType());
    }

    @Override
    public Map<String, Object> decodeObject(String json) {
        ArgumentValidate.notNull(json, "json cannot be null");
        JsonElement jsonElement = JsonParser.parseString(json);
        if (!(jsonElement instanceof JsonObject)) {
            throw new IllegalArgumentException("could not decode json to map instance: " + json);
        }
        return convertObject(jsonElement.getAsJsonObject());
    }

    @Override
    public Collection<Object> decodeCollection(String json) {
        ArgumentValidate.notNull(json, "json cannot be null");
        JsonElement jsonElement = JsonParser.parseString(json);
        if (!(jsonElement instanceof JsonArray)) {
            throw new IllegalArgumentException("could not decode json to list instance: " + json);
        }
        return convertArray(jsonElement.getAsJsonArray());
    }

    private Map<String, Object> convertObject(JsonObject jsonObject) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue() instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) entry.getValue();
                if (jsonPrimitive.isString()) {
                    result.put(entry.getKey(), jsonPrimitive.getAsString());
                }
                else if (jsonPrimitive.isNumber()) {
                    result.put(entry.getKey(), jsonPrimitive.getAsNumber());
                }
                else if (jsonPrimitive.isBoolean()) {
                    result.put(entry.getKey(), jsonPrimitive.getAsBoolean());
                }
                continue;
            }
            if (entry.getValue() instanceof JsonArray) {
                result.put(entry.getKey(), convertArray((JsonArray) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof JsonObject) {
                result.put(entry.getKey(), convertObject((JsonObject) entry.getValue()));
                continue;
            }
            if (entry.getValue() instanceof JsonNull) {
                result.put(entry.getKey(), null);
            }
        }
        return result;
    }

    private Collection<Object> convertArray(JsonArray jsonArray) {
        Collection<Object> result = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
                if (jsonPrimitive.isString()) {
                    result.add(jsonPrimitive.getAsString());
                }
                else if (jsonPrimitive.isNumber()) {
                    result.add(jsonPrimitive.getAsNumber());
                }
                else if (jsonPrimitive.isBoolean()) {
                    result.add(jsonPrimitive.getAsBoolean());
                }
                continue;
            }
            if (jsonElement instanceof JsonArray) {
                result.add(convertArray((JsonArray) jsonElement));
                continue;
            }
            if (jsonElement instanceof JsonObject) {
                result.add(convertObject((JsonObject) jsonElement));
                continue;
            }
            if (jsonElement instanceof JsonNull) {
                result.add(null);
            }
        }
        return result;
    }
}
