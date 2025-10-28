package com.igaworks.adbrixjavasample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class Utils {
    public static <T> JSONArray makeJSONArray(T... element) {
        JSONArray array = new JSONArray();
        for(T temp : element){
            array.put(temp);
        }
        return array;
    }
    public static <T> List<T> makeList(T... element){
        List<T> list = new ArrayList<>();
        for(T temp : element){
            list.add(temp);
        }
        return list;
    }

    public static class JSONObjectBuilder {
        private final JSONObject object;

        public JSONObjectBuilder() {
            this.object = new JSONObject();
        }

        public JSONObjectBuilder addProperty(String key, Object value){
            try {
                if(value == null){
                    object.put(key, JSONObject.NULL);
                } else{
                    object.put(key, value);
                }
            } catch (JSONException e) {
                System.out.println(e.toString());
            }
            return JSONObjectBuilder.this;
        }
        public JSONObject build(){
            return object;
        }
    }
}
