package com.acceso.wfcore.kernel;

import com.acceso.wfcore.dtos.ConexionDTO;
import com.acceso.wfcore.dtos.SystemTreeDTO;
import com.acceso.wfcore.utils.*;
import com.acceso.wfweb.controls.LoginCTRL;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.*;
import com.acceso.wfweb.servlets.LoginServlet;
import com.acceso.wfweb.units.*;
import com.acceso.wfweb.web.Root;
import com.google.gson.Gson;

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
        //System.out.println(">>>>>>>>>>>" + systemTreeDTO);
        Gson gson = new Gson();
        Root mainTree = gson.fromJson(systemTreeDTO.getTree(), Root.class);
        return mainTree;
    }

    public static Contenedor buildContainer(int co_conten, long id_frawor) {
        //cobnstruimos el objeto contenedor
        ContenedorDTO contenedorDTO = null;
        //IdfraworDTO idfraworDTO;
        List<PaginaDTO> paginaDTOS;

        Frawor4DAO dao = new Frawor4DAO();
        contenedorDTO = dao.getContenedorDTO(co_conten);
        dao.close();

        if (contenedorDTO == null) {
            return null;
        }

        dao = new Frawor4DAO();
        //idfraworDTO = dao.getIdfraworDTO();
        paginaDTOS = dao.getPaginaDTO(contenedorDTO.getCo_conten(), id_frawor);

        //work!
        Contenedor contenedor = new Contenedor(contenedorDTO.getCo_conten(), id_frawor, contenedorDTO.getNo_contit());
        for (PaginaDTO paginaDTO : paginaDTOS) {
//            System.out.println("AM:" + paginaDTO.getCo_pagina() + ",:::" + paginaDTO.getTi_pagina() + ",:::" + paginaDTO.getNo_pagtit());
            //pagina nueva
            List<TituloDTO> tituloDTOS = dao.getTituloDTO(paginaDTO.getCo_pagina(), contenedorDTO.getCo_conten(), id_frawor);
            List<RegistroDTO> registroDTOS = dao.getRegistroDTO(paginaDTO.getCo_pagina(), contenedorDTO.getCo_conten(), id_frawor);
            List<BotonDTO> botonDTOS = dao.getButonDTO(paginaDTO.getCo_pagina(), contenedorDTO.getCo_conten(), id_frawor);

            LinkedHashMap<String, Fila> ultraFilas = new LinkedHashMap<>();

            //crear un row!!! -> elemtno unico de creaciòn
            for (TituloDTO tituloDTO : tituloDTOS) {

                //crear una row para su titulo
                String idFila = "P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit();
                ultraFilas.put(idFila, new Fila(tituloDTO, idFila));

                for (RegistroDTO registroDTO : registroDTOS) {
                    if (registroDTO.getCo_pagtit() == tituloDTO.getCo_pagtit()) {
//                        idFila = "P" + paginaDTO.getCo_pagina() + "T" + tituloDTO.getCo_pagtit() + "R" + registroDTO.getCo_pagreg();
                        idFila = "P" + paginaDTO.getCo_pagina() + "R" + registroDTO.getCo_pagreg();
                        ultraFilas.put(idFila, new Fila(registroDTO, idFila));
                    }
                }


            }

            if (!botonDTOS.isEmpty() || botonDTOS.size() > 0) {
                for (BotonDTO botonDTO : botonDTOS) {
                    List<ParametroDTO> parametroDTOS = dao.getParams(contenedorDTO.getCo_conten(), paginaDTO.getCo_pagina(), (short) botonDTO.getCo_pagbot());
                    botonDTO.setParametros(parametroDTOS);
                }
                ultraFilas.put("BTN", new Fila(botonDTOS, "BTN"));

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

                rows.get(rows.size() - 1).getRegs().add(new RegJson(valpagDTO.getCo_pagreg(), valpagDTO.getVa_pagreg(), valpagDTO.getTx_pagreg()));
            }
            valpagJson.setRows(rows);
        }

        return valpagJson;
    }

    public static WFProperties buildDefaultProperties(ConexionDTO dto) {
        WFProperties nativeConexionProperties = new WFProperties();
        nativeConexionProperties.add("hibernate.connection.url", "jdbc:postgresql://" + dto.getUr_domini() + ":" + dto.getNu_puerto() + "/" + dto.getNo_datbas());
        nativeConexionProperties.add("hibernate.connection.username", dto.getNo_usuari());
        nativeConexionProperties.add("hibernate.connection.password", dto.getPw_usuari());
        nativeConexionProperties.add("hibernate.connection.pool_size", "" + dto.getNu_maxpoo());

        // Maximum waiting time for a connection from the pool
        nativeConexionProperties.add("hibernate.hikari.connectionTimeout", "20000");
        // Minimum number of ideal connections in the pool
        nativeConexionProperties.add("hibernate.hikari.minimumIdle", "20");
        // Maximum number of actual connection in the pool
        nativeConexionProperties.add("hibernate.hikari.maximumPoolSize", "50");
        // Maximum time that a connection is allowed to sit ideal in the pool
        nativeConexionProperties.add("hibernate.hikari.idleTimeout", "300000");

        return nativeConexionProperties;
    }

}
