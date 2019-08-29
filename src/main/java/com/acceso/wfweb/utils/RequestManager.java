package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

public class RequestManager {

    public static String REQUEST_METHOD_GET = "GET";
    public static String REQUEST_METHOD_POST = "POST";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private boolean ismultipart;
    private List<FileItem> fileItemList;
    private Map<String, String[]> parameters;

    public RequestManager(HttpServletRequest request, HttpServletResponse response) {
        this.parameters = request.getParameterMap();
        this.request = request;
        this.response = response;
        validMultiPart(request);
    }

    public RequestManager(HttpRewriteWrappedRequest httpRewriteWrappedRequest, HttpServletResponse response) {
//        System.out.println("me llamaron agui2!");
        this.request = (HttpServletRequest) httpRewriteWrappedRequest;
        this.response = response;
        this.parameters = request.getParameterMap();
        validMultiPart((HttpServletRequest) httpRewriteWrappedRequest);
    }

    public RequestManager(ServletRequest request) {
//        System.out.println("me llamaron agui!");
        this.parameters = request.getParameterMap();
        this.request = (HttpServletRequest) request;
        validMultiPart((HttpServletRequest) request);
    }

    public void validMultiPart(HttpServletRequest request) {
        this.ismultipart = ServletFileUpload.isMultipartContent(request);

        if (this.ismultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload sfu = new ServletFileUpload(factory);

            try {
                this.fileItemList = sfu.parseRequest(request);
            } catch (Exception ep) {
                fileItemList = new ArrayList<>();
                ep.printStackTrace();
            }
        }
    }

    public String getPath() {
        return this.request.getServletPath();

    }

    public String getMethod() {
        return this.request.getMethod();
    }

    public String getIp() {
        return this.request.getRemoteHost();
    }

    public String getParam(String paranName) {
        if (this.ismultipart) {
            for (FileItem fileItem : this.fileItemList) {
                if (fileItem.getFieldName().contentEquals(paranName)) {
                    return fileItem.getString();
                }
            }
            return null;
        } else {
            String params[] = parameters.get(paranName);

            if (params == null || params.length == 0) {
                return null;
            } else {
                return params[0];
            }
        }

    }

    public HashMap<Integer, String> getPagregs() {
        HashMap<Integer, String> pagregs = new HashMap<>();

        if (this.ismultipart) {
            for (FileItem fileItem : this.fileItemList) {
//                System.out.println("><" + fileItem.getFieldName());
                if (fileItem.getFieldName().contains("co_regist")) {
                    Integer id = Integer.parseInt(fileItem.getFieldName().replace("co_regist", ""));
                    String value = fileItem.getString();
                    pagregs.put(id, value);
                }
            }
        } else {
            Enumeration<String> paramnames = request.getParameterNames();
            while (paramnames.hasMoreElements()) {
                String paramname = paramnames.nextElement();
                if (paramname.contains("co_regist")) {
                    Integer id = Integer.parseInt(paramname.replace("co_regist", ""));
                    String value = ("" + request.getParameter(paramname)).trim();
                    pagregs.put(id, value);
                }
            }
        }

        return pagregs;
    }

    public void save_over_session(String objectKey, Object objectValue) {
        this.request.getSession().setAttribute(objectKey, objectValue);
    }

    public void save_over_request(String objectKey, Object objectValue) {
        this.request.setAttribute(objectKey, objectValue);
    }

    public void redirect(String url) throws Exception {
//        System.out.println("redirect to>" + url);
        this.response.sendRedirect(url);
    }

    public Integer getCo_conten() {
        return Util.toInt(getParam("co_conten"), -1);
    }

    //    public HashMap<Integer, String> getConpars() {
//        HashMap<Integer, String> conpars = new HashMap<>();
//
//        for (HashMap.Entry<String, String[]> param : this.parameters.entrySet()) {
//            if (param.getKey().contains("co_conpar_")) {
//                conpars.put(Util.toInt(param.getKey().replace("co_conpar_", "")), param.getValue()[0]);
//            }
//        }
//        return conpars;
//    }
    public Map<String, String> getConpars() {
        Map<String, String> conpars = new HashMap<>();

        this.parameters.entrySet()
                .stream()
                .filter((param) -> (!param.getKey().contains("co_conten")))
                .forEach((param) -> {
                    conpars.put(param.getKey(), param.getValue()[0]);
                });

        return conpars;
    }

    public Usuario getUser() {
        return (Usuario) this.request.getSession().getAttribute("US");
    }
}
