package com.acceso.wfcore.utils;

import com.google.gson.*;

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
        obj.addProperty("text", src.tx_pagreg);
        obj.addProperty("label", src.no_pagreg);
        obj.addProperty("state", src.ti_estreg);
        obj.addProperty("link", src.ur_pagreg);
        if (src.ti_pagreg != null && src.ti_pagreg > 0) {
            obj.addProperty("type", src.ti_pagreg);
        }
        if (src.ls_styles != null) {
            obj.addProperty("style", new Gson().toJson(src.ls_styles));
        }
        if (src.ob_dindat != null) {
            obj.addProperty("data", new Gson().toJson(src.ob_dindat));
        }
        if (src.ca_caract != null) {
            obj.addProperty("length", "" + src.ca_caract);
        }

        if (src.cf_search != null) {
            obj.addProperty("searchable", "" + src.cf_search);
        }

        if (src.do_valida != null) {
            String validation = src.do_valida.toString();
            validation = validation.replace("\\", "\\\\");
            obj.addProperty("validation", "" + validation);
        }

        if (src.tx_plahol != null) {
            obj.addProperty("placeholder", "" + src.tx_plahol);
        }
        return obj;
    }
}
