package com.acceso.wfcore.kernel;

import com.acceso.wfcore.daos.SistemaDAO;
import com.acceso.wfcore.daos.SystemDAO;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;

public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asyncContext;
    private int secs;

    public AsyncRequestProcessor() {
    }

    public AsyncRequestProcessor(AsyncContext asyncCtx, int secs) {
        this.asyncContext = asyncCtx;
        this.secs = secs;
    }

    @Override
    public void run() {
        //asyncContext.getRequest()
        try {
            PrintWriter out = asyncContext.getResponse().getWriter();

            Integer co_conten = Util.toInt(asyncContext.getRequest().getParameter("co_conten"), -1);
            Integer co_pagina = Util.toInt(asyncContext.getRequest().getParameter("co_pagina"), -1);
            Long id_frawor = Util.toLong(asyncContext.getRequest().getParameter("id_frawor"), -1);

            //ejecuta eñ valpag
            //el list del valpag;
            List<ValpagDTO> resultado_valpag = new ArrayList<>();
            Frawor4DAO dao = new Frawor4DAO();
            resultado_valpag = dao.getValPag_legacy(co_pagina, co_conten, id_frawor);
            dao.close();

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setStatus("OK");
            jsonResponse.setResult(resultado_valpag);

            Gson gson = new Gson();
            String urpta = gson.toJson(jsonResponse);

            System.out.println("urpta = " + urpta);

            out.write(urpta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //complete the processing
        asyncContext.complete();
    }

}