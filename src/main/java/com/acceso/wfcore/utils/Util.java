package com.acceso.wfcore.utils;

public class Util {

    public static Integer toInt(Object object) {
        Integer int32 = null;
        String intString = object == null ? "" : ("" + object);

        try {
            int32 = Integer.parseInt(intString);
        } catch (Exception ep) {

        }

        return int32;
    }
}
