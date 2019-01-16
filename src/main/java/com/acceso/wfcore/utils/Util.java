package com.acceso.wfcore.utils;

import java.io.BufferedReader;
import java.io.FileReader;

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

    public static Integer toInt(Object object, int defaultInt) {
        Integer int32 = null;
        String intString = object == null ? "" : ("" + object);

        try {
            int32 = Integer.parseInt(intString);
        } catch (Exception ep) {
            int32 = defaultInt;
        }

        return int32;
    }

    public static Long toLong(Object object) {
        Long long32 = null;
        String longString = object == null ? "" : ("" + object);

        try {
            long32 = Long.parseLong(longString);
        } catch (Exception ep) {

        }

        return long32;
    }

    public static Long toLong(Object object, long defaultLong) {
        Long long32 = null;
        String longString = object == null ? "" : ("" + object);

        try {
            long32 = Long.parseLong(longString);
        } catch (Exception ep) {
            long32 = defaultLong;
        }

        return long32;
    }


    public static String getText(String filename) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                result = result + line+"\n";
            }
            reader.close();
//            return records;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
