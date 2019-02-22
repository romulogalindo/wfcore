package com.acceso.wfcore.utils;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class Util {

    public static Boolean toBoolean(Object object) {
        Boolean bool32 = null;
        String mboolean = object == null ? "" : ("" + object).trim().toLowerCase();
        if (mboolean.contentEquals("false") | mboolean.contentEquals("f") | mboolean.contentEquals("0") | mboolean.contentEquals("no")) {
            bool32 = false;
        } else if (mboolean.contentEquals("true") | mboolean.contentEquals("t") | mboolean.contentEquals("1") | mboolean.contentEquals("yes") | mboolean.contentEquals("si")) {
            bool32 = true;
        }

        return bool32;
    }

    public static boolean toBoolean(Object object, boolean defaultBoolean) {
        Boolean bool32 = null;
        String mboolean = object == null ? "" : ("" + object).trim().toLowerCase();
        if (mboolean.contentEquals("false") | mboolean.contentEquals("f") | mboolean.contentEquals("0") | mboolean.contentEquals("no")) {
            bool32 = false;
        } else if (mboolean.contentEquals("true") | mboolean.contentEquals("t") | mboolean.contentEquals("1") | mboolean.contentEquals("yes") | mboolean.contentEquals("si")) {
            bool32 = true;
        } else {
            bool32 = defaultBoolean;
        }

        return bool32;
    }

    public static Short toShort(Object object) {
        Short short32 = null;
        String shortString = object == null ? "" : ("" + object);

        try {
            short32 = Short.parseShort(shortString);
        } catch (Exception ep) {

        }

        return short32;
    }

    public static short toShort(Object object, short defaultShort) {
        Short short32 = null;
        String shortString = object == null ? "" : ("" + object);

        try {
            short32 = Short.parseShort(shortString);
        } catch (Exception ep) {
            short32 = defaultShort;
        }

        return short32;
    }

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
                result = result + line + "\n";
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

    public static ErrorMessage getError(Exception ep) {
        ErrorMessage errorMessage = new ErrorMessage();
        System.out.println("error class:" + ep.getClass());
        String message = "";

        
        if (ep instanceof HibernateException) {
            message = ((HibernateException) ep).getMessage();
        } else if (ep instanceof GenericJDBCException) {
            message = ((GenericJDBCException) ep).getSQLException().getMessage();
        } else if (ep instanceof ConstraintViolationException) {
            message = ((ConstraintViolationException) ep).getSQLException().getMessage();
        } else if (ep instanceof DataException) {
            message = ((DataException) ep).getSQLException().getMessage();
        } else if (ep instanceof SQLGrammarException) {
            message = ((SQLGrammarException) ep).getSQLException().getMessage();
        } else if (ep instanceof SQLGrammarException) {
            message = ((SQLGrammarException) ep).getSQLException().getMessage();
        } else if (ep instanceof org.hibernate.exception.JDBCConnectionException) {
            message = ((org.hibernate.exception.JDBCConnectionException) ep).getSQLException().getMessage();
            if (message.indexOf("backend") > -1) {
                message = "{El sistema esta tardando mucho en responder, por favor intentalo en unos segundos. Si este inconveniente persiste avísanos haciendo clic <a href=\"wf?co_conten=22\">aquí</a>.}";
            }
        }

        if (message.contentEquals("")) {
            message = ep.getMessage();
        }

        message = message.replace("ERROR: ", "").replace("{", "").replace("}", "").replace("\n", " ").replace("\"", "\\'").replace("\'", "\\'");


        System.out.println("internal message:" + message);
        errorMessage.setType((message.contains("{") && message.contains("}")) ? ErrorMessage.ERROR_TYPE_USER : ErrorMessage.ERROR_TYPE_SYSTEM);
        errorMessage.setMessage(message);

        System.out.println("error final:" + errorMessage.getMessage());


        return errorMessage;
    }
}
