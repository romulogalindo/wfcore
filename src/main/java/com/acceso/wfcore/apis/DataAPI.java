package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.kernel.WFIOAPP;
import com.acceso.wfcore.log.Log;
import com.acceso.wfcore.transa.Transactional;
import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ArchivDTO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.AbstractDelegatingSessionBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    /**
     * {@link Object}
     */
    public JsonResponse SQLMAP(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String no_conexi = opts.get("no_conexi") == null ? "wfacr" : opts.get("no_conexi").toString();
        String no_consul = opts.get("no_consul") == null ? "select 1 as col1;" : opts.get("no_consul").toString();
        Integer sg_timout = opts.get("sg_timout") == null ? 30 : Integer.parseInt("" + opts.get("sg_timout"));
        Integer co_indice = opts.get("co_indice") == null ? -1 : Integer.parseInt("" + opts.get("co_indice"));
        String no_indice = opts.get("no_indice") == null ? "" : "" + opts.get("no_indice");

        long execution_time;
        JsonResponse jsonResponse = new JsonResponse();
        List<LinkedHashMap<String, Object>> valReturn;
        HashMap<Object, LinkedHashMap<String, Object>> valMapReturn = new HashMap<>();
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

            if (co_indice == -1) {
                Iterator<String> it = valReturn.get(0).keySet().iterator();
                int indicecur = 0;
                while (it.hasNext()) {
                    String idcur = it.next();
                    if (co_indice == (indicecur + 1)) {
//                        mapidcur = idcur;
                        no_indice = idcur;
                    }
                    indicecur++;
                }
            }

            System.out.println("mapidcur = " + no_indice + "?=>" + valReturn.size());
            for (LinkedHashMap<String, Object> lhm : valReturn) {
                valMapReturn.put(lhm.get(no_indice), lhm);
                System.out.print("//*" + lhm.get(no_indice) + ",");
            }
            System.out.println("\nmapidcur" + no_indice + ",===>" + valMapReturn.size());
            Log.info("[U" + getCo_usuari() + "][S" + getId_sesion() + "][F" + getId_frawor() + "][C" + getCo_conten() + "][P" + getCo_pagina() + "][" + getNo_escena() + "] Q = " + no_consul + " T = " + (System.currentTimeMillis() - execution_time) + "ms");
            transaction.commit();
            session.close();

            Transactional.update(midt);
            jsonResponse.setStatus(JsonResponse.OK);
//            jsonResponse.setResult(valReturn);
            jsonResponse.setResult(valMapReturn);

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

    public HashMap<Object, LinkedHashMap<String, Object>> TOMAP(Object o, int co_indice) {
        List<LinkedHashMap<String, Object>> obj = (List<LinkedHashMap<String, Object>>) o;
        HashMap<Object, LinkedHashMap<String, Object>> valMapReturn = new HashMap<>();
        String no_indice = "";
//        if (co_indice == -1) {
        Iterator<String> it = obj.get(0).keySet().iterator();
        int indicecur = 0;
        while (it.hasNext()) {
            String idcur = it.next();
            if (co_indice == (indicecur + 1)) {
//                        mapidcur = idcur;
                no_indice = idcur;
            }
            indicecur++;
        }
//        }

        System.out.println("mapidcur = " + no_indice + "?=>" + obj.size());
        for (LinkedHashMap<String, Object> lhm : obj) {
            valMapReturn.put(lhm.get(no_indice), lhm);
            System.out.print("//*" + lhm.get(no_indice) + ",");
        }

        return valMapReturn;
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
            ep.printStackTrace();
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

    /*
    * GENERADOR DE ARCHIVOS
    * */
    public File CREATE_FILE(Object obj) {
        ScriptObjectMirror opts = (ScriptObjectMirror) obj;
        String co_archiv = opts.get("co_archiv") == null ? null : ("" + opts.get("co_archiv"));
        String no_archiv = opts.get("no_archiv") == null ? "aiofile" : opts.get("no_archiv").toString();
        String no_extens = opts.get("no_extens") == null ? "txt" : opts.get("no_extens").toString();
        boolean il_header = opts.get("il_header") == null ? true : Boolean.parseBoolean(opts.get("il_header").toString());
        Object ls_config = opts.get("ls_config") == null ? null : opts.get("ls_config");
        Object ob_dindat = opts.get("ob_dindat");
        Object ur_file = opts.get("ur_file");

//        System.out.println("ls_config = " + ls_config + ",--->" + ls_config.getClass());
        Map<Integer, Object> columnsconfiguration = new HashMap<>();

        if (ls_config != null) {
            Iterator it = ((jdk.nashorn.api.scripting.ScriptObjectMirror) ls_config).values().iterator();
            while (it.hasNext()) {
                ScriptObjectMirror o1 = (ScriptObjectMirror) it.next();

                ColumnConfigJson columnConfigJson = new ColumnConfigJson();
                columnConfigJson.setIndex(Util.toInt(o1.get("index")));
                columnConfigJson.setAlign(o1.get("align") == null ? "LEFT" : o1.get("align").toString());
                columnConfigJson.setBold(o1.get("bold") == null ? false : Boolean.parseBoolean(o1.get("bold").toString()));
                columnConfigJson.setHwrap(o1.get("hwrap") == null ? false : Boolean.parseBoolean(o1.get("hwrap").toString()));
                columnConfigJson.setWidth(o1.get("width") == null ? -1 : Integer.parseInt(o1.get("width").toString()));
                columnConfigJson.setVwrap(o1.get("vwrap") == null ? false : Boolean.parseBoolean(o1.get("vwrap").toString()));
                columnConfigJson.setColor(o1.get("color") == null ? null : o1.get("color").toString());
                columnConfigJson.setBgcolor(o1.get("bgcolor") == null ? null : o1.get("bgcolor").toString());
                columnConfigJson.setType(o1.get("type") == null ? null : o1.get("type").toString());
                columnConfigJson.setFormat(o1.get("format") == null ? "" : o1.get("format").toString());
                System.out.println("columnConfigJson = " + columnConfigJson);
                columnsconfiguration.put(columnConfigJson.getIndex(), columnConfigJson);
            }
        }


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
            if (no_extens.toUpperCase().contentEquals("XLS")) {
                System.out.println("!xls ");
                file = new Converter(file).OBJECT_TO_XLS(ob_dindat);
            } else if (no_extens.toUpperCase().contentEquals("XLSX")) {
                System.out.println("!xlsx ");
                file = new Converter(file).OBJECT_TO_XLS(ob_dindat, il_header, columnsconfiguration);
            } else if (no_extens.toUpperCase().contentEquals("TXT")) {
                file = new Converter(file).OBJECT_TO_TXT(ob_dindat);
            } else if (no_extens.toUpperCase().contentEquals("CSV")) {
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
        String no_conexi = opts.get("no_conexi") == null ? null : opts.get("no_conexi").toString();
        ArrayList ls_params = (ArrayList) opts.get("ls_params");

        Map<String, Object> parameters = new HashMap<String, Object>();

        for (Object oit : ls_params) {
            com.acceso.wfcore.utils.Param param = (com.acceso.wfcore.utils.Param) oit;
            parameters.put(param.getNo_param(), param.getVa_param());
            System.out.println(param.getNo_param() + "=>" + param.getVa_param());
        }

        File jasper_file = Util.get_archiv(co_archiv);
        File file = null;
        String filename = "";

        try {
            filename = System.getProperty("java.io.tmpdir") + File.separator + no_archiv + ".pdf";
            JasperPrint print;

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new FileInputStream(jasper_file));

            if (no_conexi == null || WFIOAPP.APP.getDataSourceService().getManager(no_conexi) == null) {
                print = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            } else {
                print = JasperFillManager.fillReport(jasperReport, parameters, WFIOAPP.APP.getDataSourceService().getManager(no_conexi).getNativeSession().connection());
            }
            // exports report to pdf
            JasperExportManager.exportReportToPdfFile(print, filename);

            file = new File(filename);
            System.out.println("exporter = " + file);
        } catch (Exception ep) {
            ep.printStackTrace();
            file = null;
        }

        return file;
    }

    public ArchivDTO SAVE(File item) {
        System.out.println("item = " + item);

        ArchivDTO arcadj;
        String fileName = Util.formatName(item.getName());

        Frawor4DAO dao = new Frawor4DAO(WFIOAPP.APP.dataSourceService.getManager("wfaio").getNativeSession());
        arcadj = dao.setArchiv(item.getName());
        dao.close();

        String pre_url = WFIOAPP.APP.getDataSourceService().getValueOfKey("AIO_DATA_FILE");

        File archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()));
//                                File archivo = new File(pre_url + "/" + Util.formatDate1(arcadj.getFe_archiv()) + "/" + arcadj.getCo_archiv() + "." + Util.getFileExtension(fileName));
        System.out.println("archivo(1) = " + archivo);
        try {
            System.out.println("archivo(2) = " + archivo.exists());

            if (!archivo.exists()) {
                archivo.mkdirs();
            }

            archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()) + File.separator + arcadj.getCo_archiv() + "." + Util.getFileExtension(fileName));
            System.out.println("archivo(3) = " + archivo);
            System.out.println("archivo(3?) = " + archivo.exists());

//            item.write(archivo);
//            items2.add(arcadj);
            Files.copy(item.toPath(), archivo.toPath());

            /*NEW ADD*/
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(archivo));
            String file64String = new String(encoded, StandardCharsets.US_ASCII);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");

            Map<String, String> params = new HashMap<>();
            params.put("json", "{\"base\":\"" + file64String + "\"}");
            System.out.println("=====> " + "{\"base\":\"" + file64String + "\"}");

//                                    JsonResponse firstResponse = new HttpAPI().POST("http://sd1.accesocrediticio.com:6014/ms/uploadfileJS/v1.0/uploadbase64", headers, params, 10000);
//                                    JsonResponse firstResponse = new HttpAPI().POST("http://192.168.44.230:6014/ms/uploadfileJS/v1.0/uploadbase64", headers, params, 10000);
            JsonResponse firstResponse = new HttpAPI().POST("http://10.3.3.122:6014/ms/uploadfileJS/v1.0/uploadbase64", headers, params, 10000);
            System.out.println("firstResponse = " + firstResponse);
            System.out.println("firstResponse = " + firstResponse.getStatus());
            if (firstResponse.getStatus().contentEquals("ERROR")) {
                System.out.println("firstResponse = " + firstResponse.getError().getMessage());
            } else {
                System.out.println("firstResponse = " + firstResponse.getResult());
                System.out.println("firstResponse = " + firstResponse.getResult().toString());
            }

            WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).put("" + arcadj.getCo_archiv(), archivo);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return arcadj;
    }

    public ArchivDTO SAVE(FileItem item, Long customArchiv, Date customDate, String customName) {
        ArchivDTO arcadj;
        String fileName = Util.formatName(item.getName());

        if (customDate == null) {
            Frawor4DAO dao = new Frawor4DAO(WFIOAPP.APP.dataSourceService.getManager("wfaio").getNativeSession());
            arcadj = dao.setArchiv(item.getName());
            dao.close();
        } else {
            arcadj = new ArchivDTO();
            arcadj.setCo_archiv(customArchiv);
            arcadj.setFe_archiv(customDate);
            arcadj.setNo_archiv(customName);
        }

        String pre_url = WFIOAPP.APP.getDataSourceService().getValueOfKey("AIO_DATA_FILE");

        File archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()));
        try {
            if (!archivo.exists()) {
                archivo.mkdirs();
            }

            archivo = new File(pre_url + File.separator + Util.formatDate1(arcadj.getFe_archiv()) + File.separator + arcadj.getCo_archiv() + "." + Util.getFileExtension(fileName));
            item.write(archivo);

            WFIOAPP.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).put("" + arcadj.getCo_archiv(), archivo);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return arcadj;
    }
}
