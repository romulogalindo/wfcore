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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Iterator;
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

    public static Root buildRootTree(SystemTreeDTO systemTreeDTO) throws Exception {
        Gson gson = new Gson();
        Root mainTree = null;

        try {
            mainTree = gson.fromJson(systemTreeDTO.getTree(), Root.class);

            return mainTree;
        } catch (Exception ep) {
            throw new Exception("Error al convertir el String::JSON en objeto:" + ep.getMessage());
        }
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

//                System.out.println("valpagDTO = " + valpagDTO);
                //Evalucacion para el tipo 38

                PdfJson pdfJson = evalRegist38(valpagDTO.getVa_pagreg());
                if (pdfJson != null) {
                    valpagDTO.setVa_pagreg(createPdf(pdfJson, valpagDTO));
                }

                RegJson regJson = new RegJson(valpagDTO.getCo_pagreg(), valpagDTO.getVa_pagreg(), valpagDTO.getTx_pagreg(), valpagDTO.getNo_pagreg(), valpagDTO.getTi_pagreg(), valpagDTO.getTi_estreg(), valpagDTO.getUr_pagreg());

//                System.out.println("regJson = " + regJson);
                rows.get(rows.size() - 1).getRegs().add(regJson);
            }
            valpagJson.setRows(rows);
        }

        return valpagJson;
    }

    public static PdfJson evalRegist38(String va_pagreg) {
        PdfJson pdfJson = null;
        Gson gson = new Gson();

        try {
            pdfJson = gson.fromJson(va_pagreg, PdfJson.class);

        } catch (Exception ep) {
            pdfJson = null;
        }

        return pdfJson;
    }

    public static String createPdf(PdfJson pdfJson, ValpagDTO valpagDTO) {
        Document doc = pdfJson.getOrientation().contentEquals(Values.PDF_ORIENTATION_LANDSCAPE) ? new Document(PageSize.A4.rotate()) : new Document(PageSize.A4);

//        HttpSession s = request.getSession();
        String codimp = "" + System.currentTimeMillis();//request.getParameter("codimp");
//        String cad = (String) s.getAttribute(codimp);
        int nPages = 1;

        PdfReader reader = null;

//        JsonElement json = null;
//        Paquete paq = null;
//
        OutputStream out2;

        String spre_url = "";
        try {
//            json = new JsonParser().parse(cad);
//
//            JsonArray array = json.getAsJsonArray();

//            response.setContentType("application/pdf");
//            if (request.getParameter("v").contentEquals("d")) {
//                response.setHeader("Content-disposition", "attachment;filename=imprimir.pdf");
//            }
//
//            out2 = response.getOutputStream();

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(codimp + ".pdf"));
            doc.open();

//            int co_imagen = Util.toInt(valpagDTO.getNo_pagreg(), -1);

            if (Util.toInt(valpagDTO.getNo_pagreg(), -1) > 0) {
                try {
//                Paquete paq = FrameworkUtil.searchPackage(request);
//                    Escritor.escribe_errors("coenxion:" + paq.getCo_conexi() + "--" + paq.getNo_prefij() + "--" + paq.getUr_arcadj());
                    spre_url = "";//paq.getUr_arcadj();
                    ArchivDTO archivDTO = null;

                    Frawor4DAO dao = new Frawor4DAO();
                    archivDTO = dao.getArchiv(Util.toLong(valpagDTO.getNo_pagreg(), -1));
                    dao.close();
//                    Escritor.escribe_debug("acabo la respuesta-->:" + arcadj);
//                String nom_arc_gra = arcadj.getNo_arcadj();

                    String nombre_a = archivDTO.getCo_archiv() + archivDTO.getNo_archiv().substring(archivDTO.getNo_archiv().indexOf("."), archivDTO.getNo_archiv().length());
//                    Escritor.escribe_debug("her:" + spre_url + "/algo/" + nombre_a);

                    String pre_url = "";
                    try {
//                    pre_url = paq.getUr_arcadj();
                        pre_url = spre_url;
                        System.out.println("Esto hemos recuperado(2):" + pre_url);
                    } catch (Exception ep) {
                        pre_url = "/backup/WFACR/DOCUMENTOS";
                    }
//                pre_url = pre_url + "/" + new SimpleDateFormat("yyyy/MM/dd").format(arcadj.getFe_arcadj()) + "/" + nombre_a;
                    pre_url = pre_url + "/" + new SimpleDateFormat("yyyy/MM/dd").format(archivDTO.getFe_archiv()) + "/" + nombre_a;
//                    Escritor.escribe_debug("file=" + pre_url);

                    reader = new PdfReader(new FileInputStream(pre_url));

                    nPages = reader.getNumberOfPages();

                } catch (Exception ex) {
//                    Escritor.escribe_errors("error general:" + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            if (nPages == 0) {
                nPages = 1;
            }

            for (int i = 0; i < nPages; i++) {
                doc.newPage();

                PdfContentByte cb = writer.getDirectContent();
                PdfImportedPage pageb = writer.getImportedPage(reader, (i + 1));
                cb.addTemplate(pageb, 0, 0);

                System.out.println("procesando página:" + (i + 1));
                PdfPTable table;
                PdfPCell celda;
                Paragraph p;

                doc.setPageCount((i + 1));

//                Iterator iterator = array.iterator();
//                while (iterator.hasNext()) {
                for (PdfRegistJson pdfRegistJson : pdfJson.getRegists()) {
                    if (pdfRegistJson.getPage() == (i + 1)) {
                        table = new PdfPTable(1);
                        celda = new PdfPCell();

                        celda.setVerticalAlignment(Element.ALIGN_TOP);
                        celda.setPaddingBottom(0);
                        celda.setPaddingTop(0);
                        celda.setPaddingLeft(0);
                        celda.setPaddingRight(0);
                        celda.setBorder(Rectangle.NO_BORDER);

                        Font f_default = FontFactory.getFont("Arial");
                        f_default.setSize(pdfRegistJson.getSize() == null ? 10 : pdfRegistJson.getSize());
                        f_default.setStyle(pdfRegistJson.getStyle() == null ? Font.NORMAL : pdfRegistJson.getStyle());
                        f_default.setColor(getBaseColor(pdfRegistJson.getColor()));

                        if (pdfRegistJson.getCadena() == null && (pdfRegistJson.getImg() != null & pdfRegistJson.getImg().length() > 0)) {

                            //primero evaluar si es una imagen valida
                            String pre_url = "";
                            try {
//                                Escritor.escribe_debug("paq=" + paq);
//                            pre_url = paq.getUr_arcadj();
                                pre_url = spre_url;
//                                Escritor.escribe_debug("Esto hemos recuperado:" + pre_url);
                            } catch (Exception ep) {
//                                Escritor.escribe_debug("ep=" + ep);
                                pre_url = "/backup/WFACR/DOCUMENTOS";
                                ep.printStackTrace();
                            }

                            ArcadjDTO arcadjDTO = null;
                            Frawor4DAO dao = new Frawor4DAO();
                            arcadjDTO = dao.getArcadj(Util.toLong(valpagDTO.getNo_pagreg(), -1));
                            dao.close();

                            String nombre_a = arcadjDTO.getId_arcadj() + arcadjDTO.getNo_arcadj().substring(arcadjDTO.getNo_arcadj().indexOf("."), arcadjDTO.getNo_arcadj().length());

                            pre_url = pre_url + "/" + new SimpleDateFormat("yyyy/MM/dd").format(arcadjDTO.getFe_arcadj()) + "/" + nombre_a;
//                            Escritor.escribe_debug("Buscando imagen pre_url=" + pre_url);

                            if (pre_url != null) {

                                Image image = Image.getInstance(pre_url);
                                image.setAlignment(Image.ALIGN_RIGHT);
                                image.setAbsolutePosition(0f, 0f);
                                image.scaleAbsolute(pdfRegistJson.getWidth() == null ? image.getWidth() : pdfRegistJson.getWidth(), pdfRegistJson.getHeight() == null ? image.getHeight() : pdfRegistJson.getHeight());

                                Chunk c = new Chunk(image, 0, 0);
                                float wd = c.getWidthPoint() + 10;
//                                System.out.println("wd:" + wd);

                                p = new Paragraph(c);
                                celda.addElement(p);
                                table.addCell(celda);
                                table.setTotalWidth(wd);
                                table.writeSelectedRows(0, -1, pdfRegistJson.getX() == null ? 0 : pdfRegistJson.getX(), pdfRegistJson.getY() == null ? 0 : pdfRegistJson.getY(), writer.getDirectContent());
                            }

                        } else if (pdfRegistJson.getImg() == null && (pdfRegistJson.getCadena() != null & pdfRegistJson.getCadena().length() > 0)) {
//                        System.out.println("Cadena:" + f.getCadena() + "::" + f.getAlineacion());
                            celda.setMinimumHeight(8);
                            p = new Paragraph(pdfRegistJson.getCadena(), f_default);

                            p.setLeading(8);

                            if (pdfRegistJson.getAlineacion().equals("left")) {
                                p.setAlignment(Element.ALIGN_LEFT);
                                Chunk c = new Chunk(pdfRegistJson.getCadena(), f_default);
                                float width = c.getWidthPoint() + 10;

                                celda.addElement(p);
                                table.addCell(celda);
                                table.setTotalWidth(width);
                                table.writeSelectedRows(0, -1, pdfRegistJson.getX(), pdfRegistJson.getY(), writer.getDirectContent());
                            } else if (pdfRegistJson.getAlineacion().equals("right")) {
                                p.setAlignment(Element.ALIGN_RIGHT);
                                Chunk c = new Chunk(pdfRegistJson.getCadena(), f_default);
                                float width = c.getWidthPoint() + 10;

                                celda.addElement(p);
                                table.addCell(celda);
                                table.setTotalWidth(width);
                                table.writeSelectedRows(0, -1, pdfRegistJson.getX() - width, pdfRegistJson.getY(), writer.getDirectContent());
                            } else if (pdfRegistJson.getAlineacion().equals("center")) {
                                p.setAlignment(Element.ALIGN_CENTER);
                                Chunk c = new Chunk(pdfRegistJson.getCadena(), f_default);
                                float width = c.getWidthPoint() + 10;

                                celda.addElement(p);
                                table.addCell(celda);
                                table.setTotalWidth(width);
                                table.writeSelectedRows(0, -1, pdfRegistJson.getX() - (width / 2), pdfRegistJson.getY(), writer.getDirectContent());
                            }
                        }
                    }

                }
            }


            doc.close();
//            out2.close();
        } catch (Exception ex) {
            System.out.println("Erorr " + ex);
            System.out.println("instance :" + ex.getClass());

//            s.setAttribute(FrameworkUtil.PARAM_ERROR, ex);
//            response.sendRedirect("../Error/contenedorError.jsp");
        }

        return null;
    }

    public static BaseColor getBaseColor(String color){
        BaseColor baseColor=null;
        if(color==null || color.length()<1){
            return new BaseColor(0,0,0);
        }

        String[] rgb = color.split(",");
        if(rgb.length==3){
            String r = rgb[0].trim();
            String g = rgb[1].trim();
            String b = rgb[2].trim();
            return new BaseColor(Util.toInt(r,0),Util.toInt(g,0), Util.toInt(b,0));
        }else{
            return new BaseColor(0,0,0);
        }
    }

    public static WFProperties buildDefaultProperties(ConexionDTO dto) {
        WFProperties nativeConexionProperties = new WFProperties();
        nativeConexionProperties.add("hibernate.connection.url", "jdbc:postgresql://" + dto.getUr_domini() + ":" + dto.getNu_puerto() + "/" + dto.getNo_datbas());
        nativeConexionProperties.add("hibernate.connection.username", dto.getNo_usuari());
        nativeConexionProperties.add("hibernate.connection.password", dto.getPw_usuari());
        nativeConexionProperties.add("hibernate.connection.pool_size", "" + dto.getNu_maxpoo());

        // Maximum waiting time for a connection from the pool
        nativeConexionProperties.add("hibernate.hikari.connectionTimeout", "" + dto.getNu_timout());
        // Minimum number of ideal connections in the pool
        nativeConexionProperties.add("hibernate.hikari.minimumIdle", "3");
        // Maximum number of actual connection in the pool
        nativeConexionProperties.add("hibernate.hikari.maximumPoolSize", "" + dto.getNu_maxpoo());
        // Maximum time that a connection is allowed to sit ideal in the pool
        nativeConexionProperties.add("hibernate.hikari.idleTimeout", "300000");

        return nativeConexionProperties;
    }

}
