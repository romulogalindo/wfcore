package com.acceso.wfcore.kernel;

import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.*;
import com.acceso.wfweb.servlets.LoginServlet;
import com.acceso.wfweb.units.Contenedor;
import com.acceso.wfweb.units.Fila;
import com.acceso.wfweb.units.Pagina;
import com.acceso.wfweb.web.Root;
import com.google.gson.Gson;

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

        if (contenedorDTO == null)
            return null;

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
//                Fila fila_subtitulo = new Fila(tituloDTO);
                ultraFilas.put("P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit(), new Fila(tituloDTO));
                for (RegistroDTO registroDTO : registroDTOS) {
                    if (registroDTO.getCo_pagtit() == tituloDTO.getCo_pagtit()) {
                        ultraFilas.put("P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit() + "R" + registroDTO.getCo_pagreg(), new Fila(tituloDTO));
                    }
                }
            }


            contenedor.addPagina(new Pagina(paginaDTO.getCo_pagina(), paginaDTO.getNo_pagtit()));
        }

        dao.close();

        return contenedor;
    }
}
