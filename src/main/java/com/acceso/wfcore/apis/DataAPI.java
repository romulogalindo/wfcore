package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.transa.Transactional;
import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataAPI extends GenericAPI {

    String co_usuari;
    String id_sesion;
    String id_frawor;
    String co_conten;
    String co_pagina;
    String no_escena;

    public String getId_frawor() {
        return id_frawor;
    }

    public void setId_frawor(String id_frawor) {
        this.id_frawor = id_frawor;
    }

    public String getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(String co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(String co_conten) {
        this.co_conten = co_conten;
    }

    public String getCo_usuari() {
        return co_usuari;
    }

    public void setCo_usuari(String co_usuari) {
        this.co_usuari = co_usuari;
    }

    public String getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(String id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getNo_escena() {
        return no_escena;
    }

    public void setNo_escena(String no_escena) {
        this.no_escena = no_escena;
    }

    /**
     * SQL
     */
    @Deprecated
    public JsonResponse SQL(String conectionName, String sqlQuery) {
        return SQL(conectionName, sqlQuery, 30);
    }

    /**
     * SQL
     */
    @Deprecated
    public JsonResponse SQL(String conectionName, String sqlQuery, int timeoutseg) {
        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<LinkedHashMap<String, Object>> valReturn;
        StatelessSession session = null;
        Transaction transaction = null;
        execution_time = System.currentTimeMillis();

        try {
            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(timeoutseg);

            Query sql = session.createNativeQuery(sqlQuery);
            sql.setTimeout(timeoutseg);
            sql.setResultTransformer(AliasToEntityOrderedMapResultTransformer.INSTANCE);

            if (WFIOAPP.APP.SHOW_PREQUERY) {
                Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery);
            }

            Long midt = Transactional.insert(1, Long.parseLong(getCo_usuari()), sqlQuery);
            valReturn = sql.getResultList();

            Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
            transaction.commit();
            session.close();

            Transactional.update(midt);
            jsonResponse.setStatus(JsonResponse.OK);
            jsonResponse.setResult(valReturn);

        } catch (Exception ep) {
            Log.error("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] E = " + ep);

            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ep2) {
                transaction = null;
            }

            if (session != null) {
                session = null;
            }

            ErrorMessage errormessage = Util.getError(ep);
            if (errormessage == null) {
                jsonResponse.setStatus(JsonResponse.OK);
                jsonResponse.setResult("[]");
                jsonResponse.setError(null);
            } else {
                jsonResponse.setStatus(JsonResponse.ERROR);
                jsonResponse.setResult(null);
                jsonResponse.setError(Util.getError(ep));
            }

            Log.error("[@" + conectionName + "] Q = " + sqlQuery + "e=" + jsonResponse.getError().getMessage() + ": E1 = " + ep.getMessage() + "");

            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        return jsonResponse;
    }

    /**
     * {@link Object}
     */
    public JsonResponse SQL(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String no_conexi = opts.get("no_conexi") == null ? "wfacr" : opts.get("no_conexi").toString();
        String no_consul = opts.get("no_consul") == null ? "select 1 as col1;" : opts.get("no_consul").toString();
        Integer sg_timout = opts.get("sg_timout") == null ? 30 : Integer.parseInt("" + opts.get("sg_timout"));

        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<LinkedHashMap<String, Object>> valReturn;
        StatelessSession session = null;
        Transaction transaction = null;
        execution_time = System.currentTimeMillis();

        try {
            session = WFIOAPP.APP.getDataSourceService().getManager(no_conexi).getNativeSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(sg_timout);

            Query sql = session.createNativeQuery(no_consul);
            sql.setTimeout(sg_timout);
            sql.setResultTransformer(AliasToEntityOrderedMapResultTransformer.INSTANCE);

            if (WFIOAPP.APP.SHOW_PREQUERY) {
                Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + no_consul);
            }

            Long midt = Transactional.insert(1, Long.parseLong(getCo_usuari()), no_consul);
            if (no_consul.trim().toLowerCase().startsWith("insert") | no_consul.trim().toLowerCase().startsWith("update") | no_consul.trim().toLowerCase().startsWith("delete")) {
                int rowsaf = sql.executeUpdate();
                List<LinkedHashMap<String, Object>> lsh = new ArrayList<>();
                LinkedHashMap<String, Object> lh = new LinkedHashMap<>();
                lh.put("ca_rowafe", rowsaf);
                lsh.add(lh);
                valReturn = lsh;
            } else {
                valReturn = sql.getResultList();
            }
            Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + no_consul + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
            transaction.commit();
            session.close();

            Transactional.update(midt);
            jsonResponse.setStatus(JsonResponse.OK);
            jsonResponse.setResult(valReturn);

        } catch (Exception ep) {
            Log.error("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] E = " + ep);

            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ep2) {
                transaction = null;
            }

            if (session != null) {
                session = null;
            }

            ErrorMessage errormessage = Util.getError(ep);
            if (errormessage == null) {
                jsonResponse.setStatus(JsonResponse.OK);
                jsonResponse.setResult("[]");
                jsonResponse.setError(null);
            } else {
                jsonResponse.setStatus(JsonResponse.ERROR);
                jsonResponse.setResult(null);
                jsonResponse.setError(Util.getError(ep));
            }

            Log.error("[@" + no_conexi + "] Q = " + no_consul + "e=" + jsonResponse.getError().getMessage() + ": E1 = " + ep.getMessage() + "");

            if (WFIOAPP.APP.THROWS_EXCEPTION) {
                ep.printStackTrace();
            }
        }

        return jsonResponse;
    }

    public ValpagJson VALPAG_LEGACY(String conectionName, String sqlQuery) throws Exception {

        long execution_time = System.currentTimeMillis();
        List<ValpagDTO> valReturn;
        StatelessSession session = null;

        try {
            session = WFIOAPP.APP.getDataSourceService().getManager(conectionName).getNativeSession();

            Log.info("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery);

            NativeQuery sql = session.createNativeQuery(sqlQuery).addEntity(ValpagDTO.class);
            valReturn = sql.getResultList();

            Log.info("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery + " T = " + (System.currentTimeMillis() - execution_time) + "ms");

            session.close();

            return ApplicationManager.buildNValPag(valReturn);
        } catch (Exception ep) {
            valReturn = new ArrayList<>();

            if (session != null) {
                session = null;
            }

//            System.out.println("[VALPAG_LEGACY@" + conectionName + "] Q = " + sqlQuery + " E = " + ep.getMessage() + "");
            throw ep;
        }

    }

    /**
     * @param co_archiv
     * @return
     */
    public ExcelJson READ_FROM_FILE(long co_archiv) {
        File file = (File) WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).get("" + co_archiv);
        System.out.println("file = " + file);
        ExcelJson excelJson = null;

        try {
            String extension = FilenameUtils.getExtension(file.getName()).toUpperCase();
            switch (extension) {
                case "XLS": {
                    excelJson = new Converter(file).XLS_TO_JSON();
                    break;
                }
                case "XLSX": {
                    excelJson = new Converter(file).XLSX_TO_JSON();
                    break;
                }
            }
        } catch (Exception ep) {
            return null;
        }

        return excelJson;
    }

    public File CREATE_FILE(String filename, String extension, Object result) {
        return CREATE_FILE(filename, extension, null, result);
    }

    @Deprecated
    public File CREATE_FILE(String filename, String extension, Object cfg, Object result) {
        File file = null;
        try {
            file = new File(System.getProperty("java.io.tmpdir") + File.separator + filename + "." + extension);
        } catch (Exception ep) {
            file = null;
        }

        if (extension.toUpperCase().contains("XLS")) {
            file = new Converter(file).OBJECT_TO_XLS(result);
        } else if (extension.toUpperCase().contains("XLSX")) {
            file = new Converter(file).OBJECT_TO_XLS(result);
        } else if (extension.toUpperCase().contains("TXT")) {
            file = new Converter(file).OBJECT_TO_TXT(result);
        }

        return file;
    }

    public File CREATE_FILE(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String co_archiv = opts.get("co_archiv") == null ? null : ("" + opts.get("co_archiv"));
        String no_archiv = opts.get("no_archiv") == null ? "aiofile" : opts.get("no_archiv").toString();
        String no_extens = opts.get("no_extens") == null ? "txt" : opts.get("no_extens").toString();
        Object ob_dindat = opts.get("ob_dindat");
        Object ur_file = opts.get("ur_file");
        //-------------------
        File file = null;
        try {
            file = new File(System.getProperty("java.io.tmpdir") + File.separator + no_archiv + "." + no_extens);
        } catch (Exception ep) {
            file = null;
        }

        if (co_archiv != null & ur_file == null) {
            file = new Converter(file).ARCHIVO(co_archiv);
        } else if (co_archiv == null & ur_file != null) {
            file = new Converter(file).DOWNLOAD(ur_file);
        } else {
            if (no_extens.toUpperCase().contains("XLS")) {
                file = new Converter(file).OBJECT_TO_XLS(ob_dindat);
            } else if (no_extens.toUpperCase().contains("XLSX")) {
                file = new Converter(file).OBJECT_TO_XLS(ob_dindat);
            } else if (no_extens.toUpperCase().contains("TXT")) {
                file = new Converter(file).OBJECT_TO_TXT(ob_dindat);
            } else if (no_extens.toUpperCase().contains("CSV")) {
                file = new Converter(file).OBJECT_TO_CSV(ob_dindat);
            }
        }

        return file;
    }


    public File CREATE_ZIP(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String no_archiv = opts.get("no_archiv") == null ? "aiofile" : opts.get("no_archiv").toString();
        Object ls_archiv = opts.get("ls_archiv");
        System.out.println("ls_archiv = " + ls_archiv);
        System.out.println("ls_archiv = " + ls_archiv.getClass());
        jdk.nashorn.api.scripting.ScriptObjectMirror obh = (jdk.nashorn.api.scripting.ScriptObjectMirror) ls_archiv;
        System.out.println("obh = " + obh.isArray());
        //-------------------
        File file = null;
        try {
            file = new File(System.getProperty("java.io.tmpdir") + File.separator + no_archiv);
        } catch (Exception ep) {
            file = null;
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ZipOutputStream zipOS = new ZipOutputStream(fos);

            Iterator it = ((jdk.nashorn.api.scripting.ScriptObjectMirror) ls_archiv).values().iterator();
            while (it.hasNext()) {
                Object oit = it.next();
                if (oit instanceof File) {
                    File file1 = (File) oit;

                    FileInputStream fis = new FileInputStream(file1);
                    ZipEntry zipEntry = new ZipEntry(file1.getName());
                    zipOS.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOS.write(bytes, 0, length);
                    }

                    zipOS.closeEntry();
                    fis.close();
                }
            }
            zipOS.close();
            fos.close();
        } catch (Exception ep) {
        }

        return file;
    }

    public File CREATE_PDF(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String no_archiv = opts.get("no_archiv").toString();
        Integer co_archiv = Integer.parseInt(opts.get("co_archiv").toString());
        String no_conexi = opts.get("no_conexi").toString();
        ArrayList ls_params = (ArrayList) opts.get("ls_params");

        Map<String, Object> parameters = new HashMap<String, Object>();

        for (Object oit : ls_params) {
            com.acceso.wfcore.utils.Param param = (com.acceso.wfcore.utils.Param) oit;
            parameters.put(param.getNo_param(), param.getVa_param());
        }

        File jasper_file = Util.get_archiv(co_archiv);

        File file = null;
        String filename = "";

        try {
            filename = System.getProperty("java.io.tmpdir") + File.separator + no_archiv + ".pdf";
            JasperPrint print = JasperFillManager.fillReport(new FileInputStream(jasper_file), parameters, WFIOAPP.APP.getDataSourceService().getManager(no_conexi).getNativeSession().connection());

            // exports report to pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(filename));
            exporter.exportReport();

            file = new File(filename);
            System.out.println("exporter = " + file);
        } catch (Exception ep) {
            ep.printStackTrace();
            file = null;
        }

        return file;
    }
}
