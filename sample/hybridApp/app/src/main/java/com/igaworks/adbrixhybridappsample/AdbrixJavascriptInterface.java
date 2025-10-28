package com.igaworks.adbrixhybridappsample;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.igaworks.adbrix.Adbrix;
import com.igaworks.adbrix.constants.ABEventProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class AdbrixJavascriptInterface {

    //region JavascriptInterface
    @JavascriptInterface
    public void logEvent(String eventName, String properties){
        if(TextUtils.isEmpty(properties)){
            Adbrix.getInstance().logEvent(eventName);
        }else{
            Adbrix.getInstance().logEvent(eventName, parsePropertiesJsonString(properties));
        }
    }

    @JavascriptInterface
    public void enableSDK(){
        Adbrix.getInstance().enableSDK();
    }

    @JavascriptInterface
    public void disableSDK(){
        Adbrix.getInstance().disableSDK();
    }

    //endregion

    //region Internal Utils
    /**
     * Properties Builder로 생성된 JSON 문자열을 파싱하여 최종 JSONObject를 반환
     * @param propertiesJsonString JavaScript에서 전달받은 JSON 문자열
     * @return 파싱된 프로퍼티들로 구성된 JSONObject
     */
    private JSONObject parsePropertiesJsonString(String propertiesJsonString) {
        if (propertiesJsonString == null || propertiesJsonString.isEmpty()) {
            return new JSONObject();
        }

        try {
            JSONObject propertiesJson = new JSONObject(propertiesJsonString);
            return parseProperties(propertiesJson); // JSONObject 반환
        } catch (JSONException e) {
            // Log.e(TAG, "Failed to parse properties JSON string", e);
            return new JSONObject();
        }
    }

    /**
     * JSONObject를 파싱하여 새로운 JSONObject에 값만 담아 반환
     */
    private JSONObject parseProperties(JSONObject propertiesJson) throws JSONException {
        JSONObject parsedProperties = new JSONObject(); // 최종 반환될 JSONObject
        Iterator<String> keys = propertiesJson.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object rawValue = propertiesJson.get(key);
            Object value = null;

            // 1. abx:items 배열 처리
            if (ABEventProperty.ITEMS.equals(key) && rawValue instanceof JSONArray) {
                value = parseItemsArray((JSONArray) rawValue); // JSONArray 반환
            }
            // 2. 일반 프로퍼티 처리 (TypedValue 구조)
            else if (rawValue instanceof JSONObject) {
                JSONObject typedValue = (JSONObject) rawValue;
                if (typedValue.has("value") && typedValue.has("type")) {
                    value = parseTypedValue(typedValue); // 실제 값 (V) 반환
                }
            }

            // 추출된 값(value)을 최종 JSONObject에 담음
            if (value != null) {
                parsedProperties.put(key, value);
            }
        }
        return parsedProperties;
    }

    // =========================================================================
    // TypedValue 및 Items Array 파싱 로직 (수정 최소화, 반환 타입 유지)
    // =========================================================================

    /**
     * TypedValue 구조를 파싱하여 실제 값을 반환
     * (이전 코드와 동일하며, JSONObject.put에 바로 사용될 수 있는 값을 반환)
     */
    private Object parseTypedValue(JSONObject typedValue) throws JSONException {
        String type = typedValue.getString("type");

        if (!typedValue.has("value")) {
            return null;
        }

        Object value = typedValue.get("value");

        switch (type.toLowerCase()) {
            case "string":
                return value.toString();
            case "long":
                if (value instanceof Number) {
                    return ((Number) value).longValue();
                }
                return Long.parseLong(value.toString());
            case "double":
                if (value instanceof Number) {
                    return ((Number) value).doubleValue();
                }
                return Double.parseDouble(value.toString());
            case "boolean":
                if (value instanceof Boolean) {
                    return (Boolean) value;
                }
                return Boolean.parseBoolean(value.toString());
            default:
                return value.toString();
        }
    }

    /**
     * Items JSONArray를 파싱하여 새로운 JSONArray를 반환
     * (Items 배열 내부의 Item 객체를 JSONObject로 변환하여 담음)
     */
    private JSONArray parseItemsArray(JSONArray itemsArray) throws JSONException {
        JSONArray parsedItems = new JSONArray(); // 최종 반환될 JSONArray

        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject itemJson = itemsArray.getJSONObject(i);
            if (itemJson != null) {
                JSONObject parsedItem = parseItem(itemJson); // JSONObject 반환
                parsedItems.put(parsedItem);
            }
        }
        return parsedItems;
    }

    /**
     * 개별 아이템을 파싱하여 새로운 JSONObject에 값만 담아 반환
     */
    private JSONObject parseItem(JSONObject itemJson) throws JSONException {
        JSONObject parsedItem = new JSONObject(); // 최종 반환될 JSONObject
        Iterator<String> keys = itemJson.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object rawValue = itemJson.get(key);

            if (rawValue instanceof JSONObject) {
                JSONObject typedValue = (JSONObject) rawValue;
                if (typedValue.has("value") && typedValue.has("type")) {
                    Object value = parseTypedValue(typedValue);
                    if (value != null) {
                        parsedItem.put(key, value); // 추출된 값만 JSONObject에 담음
                    }
                }
            }
        }
        return parsedItem;
    }

    //endregion
}
