package com.acceso.wfweb.beans;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Pagina;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;

public class PaginaBean implements Serializable {
    Pagina pagina;

    public PaginaBean() {
    }

    public void do64(HttpRewriteWrappedRequest httpRewriteWrappedRequest) {

        HttpServletRequest request = (HttpServletRequest) httpRewriteWrappedRequest.getRequest();

        //construir el conenedor!!
        Integer co_conten = Util.toInt(httpRewriteWrappedRequest.getParameter("co_conten"), -1);
        Integer co_pagina = Util.toInt(httpRewriteWrappedRequest.getParameter("co_pagina"), -1);
        Long id_frawor = Util.toLong(httpRewriteWrappedRequest.getParameter("id_frawor"), -1);

        //preguntar a la cache si tienen este contenedor
        Contenedor contenedor = (Contenedor) request.getSession().getAttribute("CNT" + co_conten + ":" + id_frawor);
        pagina = contenedor.getPagina(co_pagina);
    }

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }
}
