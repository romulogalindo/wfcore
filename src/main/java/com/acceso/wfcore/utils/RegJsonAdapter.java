package com.acceso.wfcore.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author rgalindo
 */
public class RegJsonAdapter implements JsonSerializer<RegJson> {

    @Override
    public JsonElement serialize(RegJson src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        JsonObject obj = new JsonObject();
        obj.addProperty("regist", src.co_pagreg);
        obj.addProperty("value", src.va_pagreg);
        obj.addProperty("front", src.tx_pagreg);

        return obj;
    }
}
