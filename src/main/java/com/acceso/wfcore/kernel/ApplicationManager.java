package com.acceso.wfcore.kernel;

import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.RegJson;
import com.acceso.wfcore.utils.RegJsonAdapter;
import com.acceso.wfcore.utils.RowJson;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.*;
import com.acceso.wfweb.servlets.LoginServlet;
import com.acceso.wfweb.units.*;
import com.acceso.wfweb.web.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;

public class ApplicationManager {

    public static LoginCTRL getLoginCTRL() {
        LoginCTRL loginCTRL = new LoginCTRL();
        loginCTRL.setLogin_page_title("Inicia sesión");
        loginCTRL.setLogin_form_title("Inicia sesión");
        loginCTRL.setLogin_form_subtitle("Ingrese sus credenciales");
        loginCTRL.setLogin_lbl_username("Usuario");
        loginCTRL.setLogin_lbl_password("Contreña");
        loginCTRL.setLogin_btn_login("Ingresar");

        loginCTRL.setLogin_action(LoginServlet.LOGINSERVLET_LOGIN64);
        loginCTRL.setLogin_param_username("username");
        loginCTRL.setLogin_param_password("password");

        return loginCTRL;
    }

    public static Root buildRootTree(SystemTreeDTO systemTreeDTO) {
        System.out.println(">>>>>>>>>>>" + systemTreeDTO);
        Gson gson = new Gson();
        Root mainTree = gson.fromJson(systemTreeDTO.getTree(), Root.class);
        return mainTree;
    }

    public static Contenedor buildContainer(int co_conten) {
        //cobnstruimos el objeto contenedor
        ContenedorDTO contenedorDTO = null;
        IdfraworDTO idfraworDTO;
        List<PaginaDTO> paginaDTOS;

        Frawor4DAO dao = new Frawor4DAO();
        contenedorDTO = dao.getContenedorDTO(co_conten);
        dao.close();

        if (contenedorDTO == null) {
            return null;
        }

        dao = new Frawor4DAO();
        idfraworDTO = dao.getIdfraworDTO();
        paginaDTOS = dao.getPaginaDTO(contenedorDTO.getCo_conten(), idfraworDTO.getId_frawor());

        //work!
        Contenedor contenedor = new Contenedor(contenedorDTO.getCo_conten(), idfraworDTO.getId_frawor(), contenedorDTO.getNo_contit());
        for (PaginaDTO paginaDTO : paginaDTOS) {
            //pagina nueva
            List<TituloDTO> tituloDTOS = dao.getTituloDTO(paginaDTO.getCo_pagina(), contenedorDTO.getCo_conten(), idfraworDTO.getId_frawor());
            List<RegistroDTO> registroDTOS = dao.getRegistroDTO(paginaDTO.getCo_pagina(), contenedorDTO.getCo_conten(), idfraworDTO.getId_frawor());

            LinkedHashMap<String, Fila> ultraFilas = new LinkedHashMap<>();

            //crear un row!!! -> elemtno unico de creaciòn
            for (TituloDTO tituloDTO : tituloDTOS) {

                //crear una row para su titulo
                String idFila = "P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit();
                ultraFilas.put(idFila, new Fila(tituloDTO, idFila));

                for (RegistroDTO registroDTO : registroDTOS) {
                    if (registroDTO.getCo_pagtit() == tituloDTO.getCo_pagtit()) {
                        idFila = "P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit() + "R" + registroDTO.getCo_pagreg();
                        ultraFilas.put(idFila, new Fila(registroDTO, idFila));
                    }
                }
            }

//            System.out.println("paginaDTO.getTi_pagina() = " + paginaDTO.getTi_pagina());
//            System.out.println("paginaDTO.getTi_pagina().contentEquals(\"R\") = " + paginaDTO.getTi_pagina().contentEquals("R"));
            if (paginaDTO.getTi_pagina().contentEquals("R")) {
                contenedor.addPagina(new PaginaFormulario(paginaDTO.getCo_pagina(), paginaDTO.getNo_pagtit(), ultraFilas));
            } else if (paginaDTO.getTi_pagina().contentEquals("T")) {
                contenedor.addPagina(new PaginaRerporte(paginaDTO.getCo_pagina(), paginaDTO.getNo_pagtit(), ultraFilas));
            }
        }

        dao.close();

        return contenedor;
    }

    public static ValpagJson buildNValPag(List<ValpagDTO> valpagDTOs) {
//        String ultraJS = "";
        ValpagJson valpagJson = new ValpagJson();

        if (valpagDTOs.size() > 0) {
            //indicador indice!
            ValpagDTO valpagDTO_i = valpagDTOs.get(0);

            List<RowJson> rows = new ArrayList<>();
            for (ValpagDTO valpagDTO : valpagDTOs) {

                if (valpagDTO_i.getCo_pagreg() == valpagDTO.getCo_pagreg()) {
                    rows.add(new RowJson());
                }

                rows.get(rows.size() - 1).getRegs().add(new RegJson(valpagDTO.getCo_pagreg(), valpagDTO.getVa_pagreg()));
            }
            valpagJson.setRows(rows);
        }

        return valpagJson;
    }

}
