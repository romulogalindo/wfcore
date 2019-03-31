package com.acceso.wfcore.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;

import javax.persistence.PersistenceException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Util {

    private static List<String> exts = Arrays.asList("tar.gz", "tgz", "gz", "zip");
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    public static Gson gson_typeA = new Gson();
    public static Gson gson_typeB = new GsonBuilder()
            .registerTypeAdapter(RegJson.class, new RegJsonAdapter())
            .create();

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

    public static String formatName(String name) {
        String myName = name;

        myName = myName.toLowerCase().trim();
        myName = myName.replaceAll("  ", "_");
        myName = myName.replaceAll("á", "a");
        myName = myName.replaceAll("é", "e");
        myName = myName.replaceAll("í", "i");
        myName = myName.replaceAll("ó", "o");
        myName = myName.replaceAll("ú", "u");
        myName = myName.replaceAll("ñ", "n");

        String fileextension = getFileExtension(name);
        try {
            myName = myName.substring(0, myName.lastIndexOf(fileextension) - 1);
            myName = myName.replaceAll("\\.", "_");
            myName = myName + "." + fileextension;
        } catch (Exception ep) {
        }

        return myName;
    }

    public static String getFileExtension(String filename) {
        String ext = null;

        for (String xt : exts) {
            if (filename.endsWith("." + xt)) {
                if (ext == null || ext.length() < xt.length()) {
                    ext = xt;
                }
            }
        }

        if (ext == null) {
            ext = FilenameUtils.getExtension(filename);
        }
        return ext;
    }

    /**
     * Utiliza SDF => yyyy/MM/dd
     *
     */
    public static String formatDate1(Date date) {
        return date != null ? sdf1.format(date) : null;
    }

    public static ErrorMessage getError(Exception ep) {
        ErrorMessage errorMessage = new ErrorMessage();
        System.out.println("error class:" + ep.getClass());
        String message = "";

        if (ep instanceof PersistenceException) {

            if (ep.getCause() instanceof org.hibernate.MappingException) {
                return null;
            } else if (ep.getCause() instanceof GenericJDBCException) {
                message = ((GenericJDBCException) ep.getCause()).getSQLException().getLocalizedMessage();

                if (message.contains("Where:")) {
                    message = message.substring(0, message.indexOf("Where:")).trim();
                }
            }

        } else if (ep instanceof HibernateException) {
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

        errorMessage.setType((message.contains("{") && message.contains("}")) ? ErrorMessage.ERROR_TYPE_USER : ErrorMessage.ERROR_TYPE_SYSTEM);
        errorMessage.setMessage(message);

        return errorMessage;
    }

    /*Conversor tradicional*/
    public static String toJSON(Object object) {
        return gson_typeA.toJson(object);
    }

    /*Para uso con el valpag*/
    public static String toJSON2(Object object) {
        return gson_typeB.toJson(object);
    }
}
