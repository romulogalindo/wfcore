package com.acceso.wfcore.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Security {

    public static int PASSWORD_CHANGE_OK = 1;
    public static int PASSWORD_CHANGE_FAIL = 0;

    public static String toMD5(String text) {
        text = "" + text;

        if (text.length() == 0)
            return null;
        else {

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(text.getBytes());
                byte[] digest = md.digest();
                return DatatypeConverter.printHexBinary(digest).toLowerCase();
            } catch (Exception ep) {
                return null;
            }
        }
    }


}
