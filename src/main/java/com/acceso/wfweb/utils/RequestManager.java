package com.acceso.wfweb.utils;

import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.units.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class RequestManager {
    public static String REQUEST_METHOD_GET = "GET";
    public static String REQUEST_METHOD_POST = "POST";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private boolean ismultipart;
    private List<FileItem> fileItemList;

    public RequestManager(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("me llamaron agui!");
        this.request = request;
        this.response = response;

        validMultiPart(request);
    }

    public RequestManager(HttpRewriteWrappedRequest httpRewriteWrappedRequest, HttpServletResponse response) {
        System.out.println("me llamaron agui2!");
        this.request = (HttpServletRequest) httpRewriteWrappedRequest;
        this.response = response;

        validMultiPart((HttpServletRequest) httpRewriteWrappedRequest);
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
            return this.request.getParameter(paranName);
        }

    }

    public HashMap<Integer, String> getPagregs() {
        HashMap<Integer, String> pagregs = new HashMap<>();

        if (this.ismultipart) {
            for (FileItem fileItem : this.fileItemList) {
                System.out.println("><" + fileItem.getFieldName());
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
        System.out.println("redirect to>" + url);
        this.response.sendRedirect(url);
    }

    public HashMap<Integer, String> getConpars() {
        HashMap<Integer, String> conpars = new HashMap<>();

        Enumeration<String> parametersNames = this.request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String paramKey = parametersNames.nextElement();
            if (paramKey.contains("co_conpar_")) {
                conpars.put(Util.toInt(paramKey.replace("co_conpar_", "")), this.request.getParameter(paramKey));
            }
        }

        return conpars;
    }

    public Usuario getUser() {
        return (Usuario) this.request.getSession().getAttribute("US");
    }
}
