package com.acceso.wfweb.servlets;

import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.beans.legacy.*;
import com.acceso.wfweb.daos.Frawor4DAO;
import com.acceso.wfweb.dtos.ArchivDTO;
import com.acceso.wfweb.dtos.legacy.PaginaEspecialDto;
import com.acceso.wfweb.dtos.legacy.Solicitud_credito_datos_soliciDto;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
//import wf.dto.pagesp.Solicitud_credito_datos_soliciDto;
//import pe.wf3.util.FrameworkUtil;

/**
 * @author rgalindo
 */
public class DocumentServlet extends HttpServlet {

    private Map<String, int[]> coordenadas_docume1;
    private Map<String, int[]> coordenadas_docume4;
    private Map<String, Double> coordenadas_docume4_widths;
    private Map<String, int[]> coordenadas_abaco;
    private List<int[]> coordenadas_abaco_vacio;
    private Map<String, int[]> coordenadas_carta_de_retiro;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int co_conexi = 0;
        OutputStream out = null;
        try {
            String tipo_doc = request.getParameter("ti_docume");

//            if (request.getAttribute("co_conexi") == null) {
//                co_conexi = FrameworkUtil.searchPackage(request).getCo_conexi();
//            } else {
//                co_conexi = Integer.parseInt(request.getAttribute("co_conexi").toString());
//            }

            switch (tipo_doc) {
                case "1": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 1">
                    out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "attachment;filename=CCOPAC_" + System.currentTimeMillis() + ".pdf");
                    String co_expedi = request.getParameter("co_expedi");
                    if (co_expedi != null && !co_expedi.equals("")) {
                        RepSoliciBean reporte = new RepSoliciBean();

//                        reporte.setCo_expedi(co_expedi, co_conexi);
                        reporte.setCo_expedi(co_expedi);

                        BaseFont base = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/fonts/MyriadPro-Light.otf", BaseFont.WINANSI, BaseFont.EMBEDDED);
                        Font f_default = new Font(base, 6f, Font.BOLD);
                        Font f_extensa = new Font(base, 5f);
                        Document document = new Document(PageSize.A4);
                        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                        document.open();
                        PdfContentByte cb = writer.getDirectContent();
                        PdfReader reader = new PdfReader(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/reporte_solicitud.pdf");
                        PdfImportedPage page = writer.getImportedPage(reader, 1);
                        document.newPage();
                        cb.addTemplate(page, 0, 0);
                        for (int i = 0; i < 3; i++) {

                            String texto = "";
                            int[] xy = {};

                            switch (i) {
                                case 0:
                                    texto = reporte.getAnio();
                                    xy = coordenadas_docume1.get("ano");
                                    break;
                                case 1:
                                    texto = reporte.getMes();
                                    xy = coordenadas_docume1.get("mes");
                                    break;
                                case 2:
                                    texto = reporte.getDia();
                                    xy = coordenadas_docume1.get("dia");
                                    break;
                            }

                            PdfPTable table = new PdfPTable(1);
                            PdfPCell celda = new PdfPCell();
                            celda.setVerticalAlignment(Element.ALIGN_TOP);
                            celda.setPaddingBottom(0);
                            celda.setPaddingTop(0);
                            celda.setPaddingLeft(0);
                            celda.setPaddingRight(0);
                            celda.setBorder(Rectangle.NO_BORDER);

                            Paragraph p = new Paragraph(texto, f_default);

                            p.setLeading(8);
                            p.setAlignment(Element.ALIGN_LEFT);
                            Chunk c = new Chunk(texto, f_default);

                            float width = c.getWidthPoint() + 10;

                            celda.addElement(p);
                            table.addCell(celda);
                            table.setTotalWidth(width);
                            table.writeSelectedRows(0, -1, xy[1], xy[0], writer.getDirectContent());
                        }

                        Method[] methods = reporte.getReporte_solicitudDto().getClass().getMethods();

                        for (int i = 0; i < methods.length; i++) {
                            Method method = methods[i];

                            if (method.getName().startsWith("get")) {

                                String variable = method.getName().substring(3, method.getName().length()).toLowerCase();
                                String texto;
                                int[] xy;

                                PdfPTable table = new PdfPTable(1);
                                PdfPCell celda = new PdfPCell();
                                celda.setVerticalAlignment(Element.ALIGN_TOP);
                                celda.setPaddingBottom(0);
                                celda.setPaddingTop(0);
                                celda.setPaddingLeft(0);
                                celda.setPaddingRight(0);
                                celda.setBorder(Rectangle.NO_BORDER);

                                texto = method.invoke(reporte.getReporte_solicitudDto(), null) + "";

                                if (variable.equals("ti_estciv") || variable.equals("ti_genero") || variable.equals("ti_deplab")) {
                                    //estado civil, genero, trabajador dependiente o independiente
                                    xy = coordenadas_docume1.get(variable + "_" + texto);
                                    texto = "X";
                                } else if (variable.equals("ti_intdom") || variable.equals("ti_intlab")) {
                                    //tipo interior
                                    if (texto.equals("1")) {
                                        xy = coordenadas_docume1.get(variable + "_" + texto);
                                    } else {
                                        xy = null;
                                    }
                                    texto = "X";
                                } else {
                                    xy = coordenadas_docume1.get(variable);
                                }

                                if (texto == null) {
                                    texto = " ";
                                } else if (texto.toLowerCase().equals("null")) {
                                    texto = " ";
                                }

                                if (xy != null) {
                                    Paragraph p = new Paragraph(texto, f_default);
                                    p.setLeading(8);
                                    if (variable.equals("no_dirdom") || variable.equals("no_dirlab")) {
                                        p.setFont(f_extensa);
                                    }
                                    p.setAlignment(Element.ALIGN_LEFT);
                                    Chunk c = new Chunk(texto, f_default);
                                    float width = c.getWidthPoint() + 10;
                                    celda.addElement(p);
                                    table.addCell(celda);
                                    table.setTotalWidth(width);
                                    table.writeSelectedRows(0, -1, xy[1], xy[0], writer.getDirectContent());
                                }

                            }
                        }
                        page = writer.getImportedPage(reader, 2);
                        document.newPage();
                        cb.addTemplate(page, 0, 0);
                        document.close();

                    }
                    // </editor-fold>
                    break;
                }
                case "2": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 2">
                    out = response.getOutputStream();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "attachment;filename=Modelo_AE_" + System.currentTimeMillis() + ".doc");
                    HWPFDocument doc = null;
                    int p_id_frawor = Integer.parseInt(request.getParameter("id_frawor"));
                    if (p_id_frawor != 0) {
                        ModeloAeBean modAe = new ModeloAeBean();

//                        modAe.setP_id_frawor(p_id_frawor, co_conexi);
                        modAe.setP_id_frawor(p_id_frawor);

                        try {
                            doc = new HWPFDocument(new FileInputStream(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/Modelo AE - Asume Deuda.doc"));
                            Range range = doc.getRange();
                            range.replaceText(":no_usureg", "" + modAe.getModelo_ae().getNo_usureg());
                            range.replaceText(":co_docreg", "" + modAe.getModelo_ae().getCo_docreg());
                            range.replaceText(":no_cliasu", "" + modAe.getModelo_ae().getNo_cliasu());
                            range.replaceText(":co_docasu", "" + modAe.getModelo_ae().getCo_docasu());
                            range.replaceText(":no_dirasu", "" + modAe.getModelo_ae().getNo_dirasu());
                            range.replaceText(":no_disasu", "" + modAe.getModelo_ae().getNo_disasu());
                            range.replaceText(":no_proasu", "" + modAe.getModelo_ae().getNo_proasu());
                            range.replaceText(":no_depasu", "" + modAe.getModelo_ae().getNo_depasu());
                            range.replaceText(":no_client", "" + modAe.getModelo_ae().getNo_client());
                            range.replaceText(":co_contra", "" + modAe.getModelo_ae().getCo_contra());
                            range.replaceText(":fe_activa", "" + modAe.getModelo_ae().getFe_activa());
                            range.replaceText(":im_presta", "" + modAe.getModelo_ae().getIm_presta());
                            range.replaceText(":fe_docume", "" + modAe.getModelo_ae().getFe_docume());
                            range.replaceText(":im_saldeu", "" + modAe.getModelo_ae().getIm_saldeu());
                            range.replaceText(":ca_cuoini", "" + modAe.getModelo_ae().getCa_cuoini());
                            range.replaceText(":im_cuoini", "" + modAe.getModelo_ae().getIm_cuoini());
                            range.replaceText(":fe_cuoini", "" + modAe.getModelo_ae().getFe_cuoini());
                            range.replaceText(":ca_cuotas", "" + modAe.getModelo_ae().getCa_cuotas());
                            range.replaceText(":im_cuotas", "" + modAe.getModelo_ae().getIm_cuotas());
                            range.replaceText(":fe_cuofin", "" + modAe.getModelo_ae().getFe_cuofin());

                            for (int i = 0; i < range.numParagraphs(); i++) {
                                if (range.getParagraph(i) != null) {
                                    org.apache.poi.hwpf.usermodel.Paragraph par = range.getParagraph(i);
                                    par.setJustification((byte) 1);
                                    CharacterRun run = par.getCharacterRun(0);
                                    par.setFontAlignment(i);
                                    if (i == 10) {
                                        run.setBold(true);
                                    }

                                }
                            }

                            doc.write(out);

                        } catch (Exception exep) {
//                            Escritor.escribe_errors("[]" + exep);
                        }
                    }
                    // </editor-fold>
                    break;
                }
                case "3": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 3">
                    out = response.getOutputStream();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "attachment;filename=Modelo_AE_" + System.currentTimeMillis() + ".doc");
                    HWPFDocument doc = null;
                    int p_id_frawor = Integer.parseInt(request.getParameter("id_frawor"));
                    if (p_id_frawor != 0) {
                        ModeloAeBean modAe = new ModeloAeBean();

//                        modAe.setP_id_frawor(p_id_frawor, co_conexi);
                        modAe.setP_id_frawor(p_id_frawor);

                        try {
                            doc = new HWPFDocument(new FileInputStream(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/Modelo AE - Firma Titular.doc"));
                            Range range = doc.getRange();
                            range.replaceText(":no_usureg", "" + modAe.getModelo_ae().getNo_usureg());
                            range.replaceText(":co_docreg", "" + modAe.getModelo_ae().getCo_docreg());
                            range.replaceText(":no_cliasu", "" + modAe.getModelo_ae().getNo_cliasu());
                            range.replaceText(":co_contra", "" + modAe.getModelo_ae().getCo_contra());
                            range.replaceText(":im_presta", "" + modAe.getModelo_ae().getIm_presta());
                            range.replaceText(":fe_docume", "" + modAe.getModelo_ae().getFe_docume());
                            range.replaceText(":im_saldeu", "" + modAe.getModelo_ae().getIm_saldeu());
                            range.replaceText(":ca_cuoini", "" + modAe.getModelo_ae().getCa_cuoini());
                            range.replaceText(":im_cuoini", "" + modAe.getModelo_ae().getIm_cuoini());
                            range.replaceText(":fe_cuoini", "" + modAe.getModelo_ae().getFe_cuoini());
                            range.replaceText(":ca_cuotas", "" + modAe.getModelo_ae().getCa_cuotas());
                            range.replaceText(":im_cuotas", "" + modAe.getModelo_ae().getIm_cuotas());
                            range.replaceText(":fe_cuofin", "" + modAe.getModelo_ae().getFe_cuofin());

                            for (int i = 0; i < range.numParagraphs(); i++) {
                                if (range.getParagraph(i) != null) {
                                    org.apache.poi.hwpf.usermodel.Paragraph par = range.getParagraph(i);
                                    par.setJustification((byte) 1);
                                    CharacterRun run = par.getCharacterRun(0);
                                    par.setFontAlignment(i);
                                    if (i == 10) {
                                        run.setBold(true);
                                    }

                                }
                            }

                            doc.write(out);

                        } catch (Exception exep) {
//                            Escritor.escribe_errors("[]" + exep);
                        }
                    }
// </editor-fold>
                    break;
                }
                case "4": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 4">
                    out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "attachment;filename=Solicitud_Credito_" + System.currentTimeMillis() + ".pdf");
                    String co_expedi = request.getParameter("co_expedi");
                    if (co_expedi != null && !co_expedi.equals("")) {
                        SolCreditBean solicitud = new SolCreditBean();

//                        solicitud.setCo_expedi(Integer.parseInt(co_expedi), co_conexi);
                        solicitud.setCo_expedi(Integer.parseInt(co_expedi));

                        BaseFont base = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/fonts/MyriadPro-Regular.otf", BaseFont.WINANSI, BaseFont.EMBEDDED);
                        Font f_default = new Font(base, 6f, Font.NORMAL);
                        Font f_extensa = new Font(base, 5f);
                        Document document = new Document(PageSize.A4);
                        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                        document.open();
                        PdfContentByte cb = writer.getDirectContent();
                        PdfReader reader = new PdfReader(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/solicitud_credito.pdf");
                        PdfImportedPage page = writer.getImportedPage(reader, 1);
                        document.newPage();
                        cb.addTemplate(page, 0, 0);

                        //agregando fecha
                        PdfPTable table_fec = new PdfPTable(1);
                        PdfPCell celda_fec = new PdfPCell();
                        celda_fec.setVerticalAlignment(Element.ALIGN_TOP);
                        celda_fec.setPadding(0);
                        celda_fec.setBorder(Rectangle.NO_BORDER);

                        String fecha = new SimpleDateFormat("dd-MM-YYYY").format(new Date());
                        int[] xyp_fec = coordenadas_docume4.get("fecha");

                        Paragraph p_fec = new Paragraph(fecha, f_default);
                        p_fec.setLeading(8);
                        p_fec.setAlignment(Element.ALIGN_LEFT);
                        Chunk c_fec = new Chunk(fecha, f_default);
                        float width_fec = c_fec.getWidthPoint() + 10;
                        celda_fec.addElement(p_fec);
                        table_fec.addCell(celda_fec);
                        table_fec.setTotalWidth(width_fec);
                        table_fec.writeSelectedRows(0, -1, xyp_fec[1], xyp_fec[0], writer.getDirectContent());

                        //agregando los datos del credito
                        Method[] methods = solicitud.getSolicitud_creditoDto().getClass().getMethods();

                        for (int i = 0; i < methods.length; i++) {
                            Method method = methods[i];

                            if (method.getName().startsWith("get")) {

                                String variable = method.getName().substring(3, method.getName().length()).toLowerCase();
                                String texto;
                                int[] xyp;

                                PdfPTable table = new PdfPTable(1);
                                PdfPCell celda = new PdfPCell();
                                celda.setVerticalAlignment(Element.ALIGN_TOP);
                                celda.setPaddingBottom(0);
                                celda.setPaddingTop(0);
                                celda.setPaddingLeft(0);
                                celda.setPaddingRight(0);
                                celda.setBorder(Rectangle.NO_BORDER);

                                texto = method.invoke(solicitud.getSolicitud_creditoDto(), null) + "";

                                if (variable.equals("ti_credit") || variable.equals("ti_cuotas") || variable.equals("no_descre")) {
                                    xyp = coordenadas_docume4.get(variable + "_" + texto.toLowerCase());
                                    texto = "X";
                                } else if (variable.equals("ca_plagra")) {

                                    int[] xy_ca_plagra;
                                    if (!texto.trim().equals("")) {
                                        xy_ca_plagra = coordenadas_docume4.get(variable + "_si");
                                    } else {
                                        xy_ca_plagra = coordenadas_docume4.get(variable + "_no");
                                    }

                                    PdfPTable table_plagra = new PdfPTable(1);
                                    PdfPCell celda_plagra = new PdfPCell();
                                    celda_plagra.setVerticalAlignment(Element.ALIGN_TOP);
                                    celda_plagra.setPadding(0);
                                    celda_plagra.setBorder(Rectangle.NO_BORDER);
                                    Paragraph p_plagra = new Paragraph("X", f_default);
                                    p_plagra.setLeading(8);
                                    p_plagra.setAlignment(Element.ALIGN_LEFT);
                                    Chunk c_plagra = new Chunk("X", f_default);
                                    float width_plagra = c_plagra.getWidthPoint() + 10;
                                    celda_plagra.addElement(p_plagra);
                                    table_plagra.addCell(celda_plagra);
                                    table_plagra.setTotalWidth(width_plagra);
                                    table_plagra.writeSelectedRows(0, -1, xy_ca_plagra[1], xy_ca_plagra[0], writer.getDirectContent());

                                    xyp = coordenadas_docume4.get(variable);
                                } else {
                                    xyp = coordenadas_docume4.get(variable);
                                }

                                if (texto == null) {
                                    texto = " ";
                                } else if (texto.toLowerCase().equals("null")) {
                                    texto = " ";
                                }

                                if (xyp != null) {
                                    Paragraph p = new Paragraph(texto, f_default);
                                    if (variable.equals("ti_plazo")) {
                                        p.setFont(f_extensa);
                                    }
                                    p.setLeading(8);
                                    p.setAlignment(Element.ALIGN_LEFT);
                                    Chunk c = new Chunk(texto, f_default);
                                    float width = c.getWidthPoint() + 10;
                                    celda.addElement(p);
                                    table.addCell(celda);
                                    table.setTotalWidth(width);
                                    table.writeSelectedRows(0, -1, xyp[1], xyp[0], writer.getDirectContent());
                                }

                            }
                        }

                        boolean cambioHoja = false;
                        List<String> ti_solicis = new ArrayList<>();
                        ti_solicis.add("TI");
                        ti_solicis.add("CO");
                        ti_solicis.add("GA");
                        ti_solicis.add("CG");

                        for (String ti_solici : ti_solicis) {

                            Solicitud_credito_datos_soliciDto dto = (Solicitud_credito_datos_soliciDto) solicitud.getClass().getMethod("getSolicitud_credito_" + ti_solici + "Dto", null).invoke(solicitud, null);
                            if (dto != null) {
                                methods = dto.getClass().getDeclaredMethods();

                                for (String method : dto.listaMetodos()) {
                                    String variable = method.substring(3, method.length()).toLowerCase();
                                    String texto;
                                    int[] xyp;

                                    PdfPTable table = new PdfPTable(1);
                                    PdfPCell celda = new PdfPCell();
                                    celda.setVerticalAlignment(Element.ALIGN_TOP);
                                    celda.setPaddingBottom(0);
                                    celda.setPaddingTop(0);
                                    celda.setPaddingLeft(0);
                                    celda.setPaddingRight(0);
                                    celda.setBorder(Rectangle.NO_BORDER);

                                    texto = dto.getClass().getMethod(method, null).invoke(dto, null) + "";
                                    if (variable.equals("ti_docide") || variable.equals("ti_genero") || variable.equals("no_estciv") || variable.equals("ti_sitlab")) {
                                        xyp = coordenadas_docume4.get(variable + "_" + texto.toLowerCase() + "_" + ti_solici.toLowerCase());
                                        texto = "X";
                                    } else {
                                        xyp = coordenadas_docume4.get(variable + "_" + ti_solici.toLowerCase());
                                    }

                                    if (texto == null) {
                                        texto = " ";
                                    } else if (texto.toLowerCase().equals("null")) {
                                        texto = " ";
                                    }

                                    if (xyp != null) {
                                        float x = new Float(xyp[0]);
                                        float y = new Float(xyp[1]);

                                        Paragraph p = new Paragraph(texto, f_default);
                                        p.setLeading(8);
                                        p.setAlignment(Element.ALIGN_LEFT);
                                        Chunk c = new Chunk(texto, f_default);
                                        float width = c.getWidthPoint() + 3;

                                        if ((variable.equals("no_refere") || variable.equals("no_carlab") || variable.equals("no_direcc")
                                                || variable.equals("no_urbani") || variable.equals("no_distri") || variable.equals("no_provin") || variable.equals("no_depart")
                                                || variable.equals("no_acteco") || variable.equals("no_dirlab")) && width > Float.parseFloat(coordenadas_docume4_widths.get(variable) + "")) {
                                            celda.addElement(p);
                                            table.addCell(celda);
                                            table.setTotalWidth(Float.parseFloat(coordenadas_docume4_widths.get(variable) + ""));
                                            x = x + 5;
//                                        y = y - 5;

                                        } else {
                                            celda.addElement(p);
                                            table.addCell(celda);
                                            table.setTotalWidth(width);
                                        }

                                        if (xyp[2] == 2 && !cambioHoja) {
                                            page = writer.getImportedPage(reader, 2);
                                            document.newPage();
                                            cb.addTemplate(page, 0, 0);
                                            cambioHoja = true;
                                        }

                                        table.writeSelectedRows(0, -1, y, x, writer.getDirectContent());
                                    }

                                }
                            }
                        }
                        if (page.getPageNumber() == 1) {
                            page = writer.getImportedPage(reader, 2);
                            document.newPage();
                            cb.addTemplate(page, 0, 0);
                        }
                        document.close();
                    }
                    // </editor-fold>
                    break;
                }
                case "5": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 5">
                    out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    response.setDateHeader("Expires", 0);
                    String analista = request.getParameter("analis");
                    File file = null;
                    switch (analista) {
                        case "norma": {
                            response.setHeader("Content-disposition", "attachment;filename=garantia_actual_norma.pdf");
                            file = new File(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/garantia_actual_norma.pdf");
                            break;
                        }
                        case "edith": {
                            response.setHeader("Content-disposition", "attachment;filename=garantia_actual_edith.pdf");
                            file = new File(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/garantia_actual_edith.pdf");
                            break;
                        }
                        case "veronica": {
                            response.setHeader("Content-disposition", "attachment;filename=garantia_actual_veronica.pdf");
                            file = new File(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/garantia_actual_veronica.pdf");
                            break;
                        }
                    }
                    BufferedInputStream buf = null;
                    try {
                        response.setContentLength((int) file.length());
                        buf = new BufferedInputStream(new FileInputStream(file));
                        int readBytes = 0;
                        while ((readBytes = buf.read()) != -1) {
                            out.write(readBytes);
                        }
                    } finally {
                        if (out != null) {
                            out.flush();
                        }
                        out.close();
                        if (buf != null) {
                            buf.close();
                        }
                    }
                    // </editor-fold>
                    break;
                }
                case "6": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 6">
                    out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "attachment;filename=Ficha_ABACO_" + System.currentTimeMillis() + ".pdf");
                    String co_expedi = request.getParameter("co_expedi");
                    if (co_expedi != null && !co_expedi.equals("")) {
                        RepAbacoBean abaco = new RepAbacoBean();

//                        abaco.setCo_expedi(co_expedi, co_conexi);
                        abaco.setCo_expedi(co_expedi);

                        boolean tieneConyugue = false;

                        BaseFont base = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/fonts/MyriadPro-Regular.otf", BaseFont.WINANSI, BaseFont.EMBEDDED);
                        Font f_default = new Font(base, 6f, Font.NORMAL);
                        Font f_extensa = new Font(base, 5f);
                        Document document = new Document(PageSize.A4);
                        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                        document.open();
                        PdfContentByte cb = writer.getDirectContent();
                        PdfReader reader;
                        if (abaco.getModelo_abaco_dto().getNo_apecon() != null && !abaco.getModelo_abaco_dto().getNo_apecon().isEmpty() && !abaco.getModelo_abaco_dto().getNo_apecon().toLowerCase().equals("null")) {
                            reader = new PdfReader(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/Ficha_abaco.pdf");
                            tieneConyugue = true;
                        } else {
                            reader = new PdfReader(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/Ficha_abaco_2.pdf");
                        }
                        PdfImportedPage page = writer.getImportedPage(reader, 1);
                        document.newPage();
                        cb.addTemplate(page, 0, 0);

                        //agregando los datos del credito
                        Method[] methods = abaco.getModelo_abaco_dto().getClass().getMethods();

                        Map<PdfPTable, int[]> tables_2da_pagina = new HashMap<>();

                        for (int i = 0; i < methods.length; i++) {
                            Method method = methods[i];

                            if (method.getName().startsWith("get")) {

                                String variable = method.getName().substring(3, method.getName().length()).toLowerCase();
                                String texto;
                                int[] xy;

                                PdfPTable table = new PdfPTable(1);
                                PdfPCell celda = new PdfPCell();
                                celda.setVerticalAlignment(Element.ALIGN_TOP);
                                celda.setPaddingBottom(0);
                                celda.setPaddingTop(0);
                                celda.setPaddingLeft(0);
                                celda.setPaddingRight(0);
                                celda.setBorder(Rectangle.NO_BORDER);

                                texto = method.invoke(abaco.getModelo_abaco_dto(), null) + "";
                                if (variable.equals("no_propie") || variable.equals("no_proloc")) {
                                    xy = coordenadas_abaco.get(variable + "_" + texto.toLowerCase());
                                    texto = "X";
                                } else if (variable.equals("no_direcc")) {
                                    if (texto.length() > 70) {
                                        texto = texto.substring(0, 67) + "...";
                                    }
                                    xy = coordenadas_abaco.get(variable);
                                } else {
                                    xy = coordenadas_abaco.get(variable);
                                }

                                if (xy != null) {
                                    int[] xy_new = new int[]{new Integer(xy[0]), new Integer(xy[1]), new Integer(xy[2])};
                                    xy = xy_new;
                                    if (texto == null || texto.isEmpty() || texto.toLowerCase().equals("null")) {
                                        if (variable.startsWith("no_entida") || variable.startsWith("im_cuenta")) {
                                            texto = "                                                                    ___";
                                            xy[0] = xy[0] + 3;
                                        } else {
                                            texto = "                   ___";
                                            xy[0] = xy[0] + 3;
                                        }
                                    }

                                    Paragraph p = new Paragraph(texto, f_default);
                                    p.setLeading(8);
                                    p.setAlignment(Element.ALIGN_LEFT);
                                    Chunk c = new Chunk(texto, f_default);
                                    float width = c.getWidthPoint() + 10;
                                    celda.addElement(p);
                                    table.addCell(celda);
                                    table.setTotalWidth(width);
                                    if (xy[2] == 1) {
                                        table.writeSelectedRows(0, -1, xy[1], xy[0], writer.getDirectContent());
                                    } else {
                                        tables_2da_pagina.put(table, xy);
                                    }
                                }

                            }
                        }

                        for (int i = 0; i < 2; i++) {
                            String texto = "";
                            int[] xy = null;

                            if (i == 0) {
                                texto = abaco.getModelo_abaco_dto().getNo_nombre() + " " + abaco.getModelo_abaco_dto().getNo_apelli();
                                xy = coordenadas_abaco.get("no_titula_firma");
                            } else if (i == 1) {
                                if (tieneConyugue) {
                                    texto = abaco.getModelo_abaco_dto().getNo_nomcon() + " " + abaco.getModelo_abaco_dto().getNo_apecon();
                                    xy = coordenadas_abaco.get("no_conyugue_firma");
                                }
                            }

                            if (texto == null) {
                                texto = " ";
                            } else if (texto.toLowerCase().equals("null")) {
                                texto = " ";
                            }

                            if (xy != null) {
                                PdfPTable table = new PdfPTable(1);
                                PdfPCell celda = new PdfPCell();
                                celda.setVerticalAlignment(Element.ALIGN_TOP);
                                celda.setPaddingBottom(0);
                                celda.setPaddingTop(0);
                                celda.setPaddingLeft(0);
                                celda.setPaddingRight(0);
                                celda.setBorder(Rectangle.NO_BORDER);

                                Paragraph p = new Paragraph(texto, f_default);
                                p.setLeading(8);
                                p.setAlignment(Element.ALIGN_LEFT);
                                Chunk c = new Chunk(texto, f_default);
                                float width = c.getWidthPoint() + 10;
                                celda.addElement(p);
                                table.addCell(celda);
                                table.setTotalWidth(width);
                                tables_2da_pagina.put(table, xy);
                            }
                        }

                        if (page.getPageNumber() == 1) {
                            page = writer.getImportedPage(reader, 2);
                            document.newPage();
                            cb.addTemplate(page, 0, 0);

                            if (tables_2da_pagina.size() > 0) {
                                for (Map.Entry<PdfPTable, int[]> entry : tables_2da_pagina.entrySet()) {
                                    PdfPTable table_key = entry.getKey();
                                    int[] xy_value = entry.getValue();
                                    table_key.writeSelectedRows(0, -1, xy_value[1], xy_value[0], writer.getDirectContent());
                                }
                            }
                        }
                        document.close();
                    }
                    // </editor-fold>
                    break;
                }
                case "7": {
                    // <editor-fold defaultstate="collapsed" desc="CASE 7">
                    out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "attachment;filename=CARTA_DE_RETIRO_" + System.currentTimeMillis() + ".pdf");
                    String co_expedi = request.getParameter("co_expedi");
                    if (co_expedi != null && !co_expedi.equals("")) {
                        CartaRetiroBean carta = new CartaRetiroBean();

//                        carta.setCo_expedi(co_expedi, co_conexi);
                        carta.setCo_expedi(co_expedi);

                        BaseFont base = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/fonts/MyriadPro-Regular.otf", BaseFont.WINANSI, BaseFont.EMBEDDED);
                        Font f_default = new Font(base, 11f, Font.NORMAL);
                        Font f_extensa = new Font(base, 5f);
                        Document document = new Document(PageSize.A4);
                        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                        document.open();
                        PdfContentByte cb = writer.getDirectContent();
                        PdfReader reader = new PdfReader(request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/files/carta_de_retiro.pdf");
                        PdfImportedPage page = writer.getImportedPage(reader, 1);
                        document.newPage();
                        cb.addTemplate(page, 0, 0);

                        Method[] methods = carta.getCarta_retiro_dto().getClass().getMethods();
                        for (int i = 0; i < methods.length; i++) {
                            Method method = methods[i];

                            if (method.getName().startsWith("get")) {
                                String variable = method.getName().substring(3, method.getName().length()).toLowerCase();
                                String texto;
                                int[] xy;
                                PdfPTable table = new PdfPTable(1);
                                PdfPCell celda = new PdfPCell();
                                celda.setVerticalAlignment(Element.ALIGN_TOP);
                                celda.setPaddingBottom(0);
                                celda.setPaddingTop(0);
                                celda.setPaddingLeft(0);
                                celda.setPaddingRight(0);
                                celda.setBorder(Rectangle.NO_BORDER);

                                texto = method.invoke(carta.getCarta_retiro_dto(), null) + "";
                                xy = coordenadas_carta_de_retiro.get(variable);

                                if (texto == null) {
                                    texto = " ";
                                } else if (texto.toLowerCase().equals("null")) {
                                    texto = " ";
                                }
                                if (xy != null) {
                                    Paragraph p = new Paragraph(texto, f_default);
//                                if (variable.equals("ti_plazo")) {
//                                    p.setFont(f_extensa);
//                                }
                                    p.setLeading(8);
                                    p.setAlignment(Element.ALIGN_LEFT);
                                    Chunk c = new Chunk(texto, f_default);
                                    float width = c.getWidthPoint() + 10;
                                    celda.addElement(p);
                                    table.addCell(celda);
                                    table.setTotalWidth(width);
                                    table.writeSelectedRows(0, -1, xy[1], xy[0], writer.getDirectContent());
                                }

                            }
                        }
                        document.close();
                    }
                    // </editor-fold>
                    break;
                }
                case "E": {
                    // <editor-fold defaultstate="collapsed" desc="CASE E:: PAGINA ESPECIAL">
                    if (request.getParameter("co_regist") != null) {
                        String json = (String) request.getSession().getAttribute(request.getParameter("co_regist"));
                        request.setAttribute("pag", gson(json));
                        (request.getSession()).removeAttribute(request.getParameter("co_regist"));
                    }

                    /*HTML -> PDF*/
                    String[] sparams;
                    try {
                        sparams = java.net.URLDecoder.decode(request.getQueryString(), "ISO-8859-1").split("&");
                    } catch (Exception ep) {
                        sparams = new String[0];
                        ep.printStackTrace();
                    }

                    String no_docume = "";
                    int nu = -1;
                    int max = 0;
                    ArrayList<String> p = new ArrayList<>();

                    PaginaEspecialDto paginaEspecialDto = null;
                    try {

                        if (request.getAttribute("pag") != null) {
                            PagEspBean pag = (PagEspBean) request.getAttribute("pag");
                            String del = "[~]+";
                            String[] params = null;
                            String[][] req = new String[pag.getLs_parame().size()][2];
                            for (int i = 0; i < pag.getLs_parame().size(); i++) {
                                params = pag.getLs_parame().get(i).split(del, 2);
                                req[i][0] = params[0];
                                if (params.length > 1) {
                                    req[i][1] = params[1];
                                } else {
                                    req[i][1] = "";
                                }
                            }
                            for (int i = 0; i < req.length; i++) {
                                nu = Integer.parseInt(req[i][0].substring(1));
                                if (nu > max) {
                                    max = nu;
                                }
                            }
                            int aux = 0;
                            int pos;
                            while (p.size() <= max) {
                                pos = -1;
                                for (int i = 0; i < req.length; i++) {
                                    if (Integer.parseInt(req[i][0].substring(1)) == aux) {
                                        pos = i;
                                    }
                                }
                                if (pos != -1) {
                                    p.add(req[pos][1]);
                                } else {
                                    p.add(null);
                                }
                                aux = aux + 1;
                            }
                            paginaEspecialDto = pag.docume(pag.getCo_docume(), p, co_conexi);
                            no_docume = paginaEspecialDto.getNo_docume();
                        } else {
                            Enumeration en = request.getParameterNames();
                            while (en.hasMoreElements()) {
                                String paramName = (String) en.nextElement();
                                if (paramName.startsWith("p")) {
                                    nu = Integer.parseInt(paramName.substring(1));
                                    if (nu > max) {
                                        max = nu;
                                    }
                                }
                            }

                            p.add(null);
                            for (int i = 1; i <= max; i++) {
                                String m_value = null;
                                String m1 = "p" + i;
                                for (int o = 0; o < sparams.length; o++) {
                                    String e0 = sparams[o];
                                    if (e0.indexOf(m1) == 0) {
                                        m_value = (e0.split("=").length == 2 ? e0.split("=")[1] : "");
                                        o = 190;
                                    }
                                }

                                String mp = null;
                                if (m_value != null) {
                                    mp = m_value;
                                } else {
                                    mp = request.getParameter(m1);
                                }

                                p.add(mp);
                            }


                            PagEspBean pag = new PagEspBean();
                            paginaEspecialDto = pag.docume(Util.toInt(request.getParameter("co_docume")), p, co_conexi);
                            no_docume = paginaEspecialDto.getNo_docume();
                        }
                    } catch (Exception ep) {
                        ep.printStackTrace();
                    }


                    no_docume = no_docume.replaceAll("<BR>", "<BR></BR>");
                    no_docume = no_docume.replaceAll("<br>", "<br></br>");
                    no_docume = no_docume.replaceAll("</span></p>", "");
                    no_docume = no_docume.replaceAll("../img/Firma_Jose_Mazuelos.png", "http://127.0.0.1:8080/jsp_exec/imgs/legacy/Firma_Jose_Mazuelos.png");
                    System.out.println("no_docume" + no_docume);

                    File pdfTempFile = File.createTempFile("" + System.currentTimeMillis(), ".pdf");
                    String erroMessage = "";

                    try {
                        Document document = new Document();
                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfTempFile));
                        document.open();
                        document.addAuthor("Edpyme Acceso Crediticio S.A.");
                        document.addCreationDate();
                        InputStream stream = new ByteArrayInputStream(no_docume.getBytes(StandardCharsets.ISO_8859_1));
                        XMLWorkerHelper.getInstance().parseXHtml(writer, document, stream);
                        document.close();


                    } catch (Exception ep) {
                        pdfTempFile = null;
                        erroMessage = ep.getMessage() + ":" + ep.getLocalizedMessage();
                    }

                    out = response.getOutputStream();
                    if (pdfTempFile != null) {
                        response.setContentType("application/pdf");
                        response.addHeader("Content-Disposition", "inline; filename=" + pdfTempFile.getName());
                        response.setContentLength((int) pdfTempFile.length());

                        int length;
                        byte[] buffer = new byte[4096];
                        FileInputStream fileInputStream = new FileInputStream(pdfTempFile);
                        while ((length = fileInputStream.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                        fileInputStream.close();
                    } else {
                        response.setContentType("text/html;charset=ISO-8859-1");
                        out = response.getOutputStream();
                        ((ServletOutputStream) out).println("El archivo no esta disponible.<br/>Causa:<br/>" + erroMessage);
                    }

                    out.flush();
                    out.close();
                    // </editor-fold>
                    break;
                }

                case "J": {
                    // <editor-fold defaultstate="collapsed" desc="CASE J:: PAGINA ESPECIAL">
                    boolean showpdf = true;

                    if (request.getParameter("co_arctem") == null || WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).get(request.getParameter("co_arctem")) == null) {
                        showpdf = false;
                    }

                    out = response.getOutputStream();
                    if (showpdf) {
                        File pdfFile = (File) WFCoreListener.APP.getCacheService().getZeroDawnCache().getSpace(Values.CACHE_MAIN_FILEX).get(request.getParameter("co_arctem"));

                        response.setContentType("application/pdf");
                        response.addHeader("Content-Disposition", "inline; filename=" + pdfFile.getName());
                        response.setContentLength((int) pdfFile.length());

                        int length;
                        byte[] buffer = new byte[4096];
                        FileInputStream fileInputStream = new FileInputStream(pdfFile);
                        while ((length = fileInputStream.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                        fileInputStream.close();
                    } else {
                        response.setContentType("text/html;charset=ISO-8859-1");
                        out = response.getOutputStream();
                        ((ServletOutputStream) out).println("El archivo no esta disponible.");

                    }

                    out.flush();
                    out.close();

                    // </editor-fold>
                    break;

//                System.out.println("valpagDTO = " + valpagDTO);
                }
                case "U": {
                    // <editor-fold defaultstate="collapsed" desc="SUBIR ARCHIVO WFACR?">
                    out = response.getOutputStream();
                    JsonResponse response1 = new JsonResponse();

                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    List<ArchivDTO> items2 = new ArrayList<>();

                    items.stream()
                            .filter(item -> !item.isFormField())
                            .filter(item -> item.getSize() <= Values.UPLOAD_FILE_MAXSIZE)
                            .forEach(item -> {
                                String fileName = Util.formatName(item.getName());
                                ArchivDTO arcadj;

                                Frawor4DAO dao = new Frawor4DAO(WFCoreListener.dataSourceService.getManager("wfaio").getNativeSession());
                                arcadj = dao.setArchiv(item.getName());
                                dao.close();

                                String pre_url = "";

                                File archivo = new File(pre_url + "/" + Util.formatDate1(arcadj.getFe_archiv()) + "/" + arcadj.getCo_archiv() + "." + Util.getFileExtension(fileName));

                                if (archivo.exists()) {
                                    archivo.mkdirs();
                                }

                                //
                                try {
                                    item.write(archivo);
                                    items2.add(arcadj);
                                } catch (Exception ep) {

                                }
                            });

                    if (!items2.isEmpty()) {
                        response1.setStatus(JsonResponse.OK);
                    } else {
                        response1.setStatus(JsonResponse.ERROR);
                    }

                    response1.setResult(items2);

                    ((ServletOutputStream) out).println(new Gson().toJson(response1));

                    out.flush();
                    out.close();
                    // </editor-fold>
                    break;

//                System.out.println("valpagDTO = " + valpagDTO);
                }

//                System.out.println("valpagDTO = " + valpagDTO);
            }

        } catch (Exception ep) {
//            Escritor.escribe_errors("[reporte_solicitud]" + ex);
            ep.printStackTrace();
        } /*finally {
         out.close();
         }*/
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {

        super.init(config);

        coordenadas_docume1 = new HashMap<>();
        coordenadas_docume1.put("co_expedi", new int[]{775, 520});

        coordenadas_docume1.put("dia", new int[]{720, 465});
        coordenadas_docume1.put("mes", new int[]{720, 500});
        coordenadas_docume1.put("ano", new int[]{720, 545});

        coordenadas_docume1.put("no_apepat", new int[]{595, 85});
        coordenadas_docume1.put("no_apemat", new int[]{595, 360});

        coordenadas_docume1.put("no_nombre", new int[]{580, 65});

        coordenadas_docume1.put("fe_nacimi", new int[]{553, 55});

        coordenadas_docume1.put("ti_estciv_SO", new int[]{542, 94});
        coordenadas_docume1.put("ti_estciv_CA", new int[]{542, 145});
        coordenadas_docume1.put("ti_estciv_CO", new int[]{542, 206});
        coordenadas_docume1.put("ti_estciv_VI", new int[]{542, 252});
        coordenadas_docume1.put("ti_estciv_DI", new int[]{542, 309});
        coordenadas_docume1.put("ti_genero_M", new int[]{542, 387});
        coordenadas_docume1.put("ti_genero_F", new int[]{542, 432});

        coordenadas_docume1.put("no_nacion", new int[]{527, 70});
        coordenadas_docume1.put("co_docide", new int[]{527, 305});

        coordenadas_docume1.put("no_nivedu", new int[]{515, 87});

        coordenadas_docume1.put("no_depdom", new int[]{485, 180});
        coordenadas_docume1.put("no_prodom", new int[]{485, 270});
        coordenadas_docume1.put("no_disdom", new int[]{485, 378});

        coordenadas_docume1.put("no_dirdom", new int[]{472, 55});
        coordenadas_docume1.put("nu_camdom", new int[]{472, 325});
        coordenadas_docume1.put("nu_mandom", new int[]{472, 365});
        coordenadas_docume1.put("nu_lotdom", new int[]{472, 405});
        coordenadas_docume1.put("ti_intdom_1", new int[]{475, 450});//departamento
//        coordenadas_docume1.put("ti_intdom_2", new int[]{473});//quinta
        coordenadas_docume1.put("nu_intdom", new int[]{472, 522});

        coordenadas_docume1.put("no_refdom", new int[]{460, 62});

        coordenadas_docume1.put("nu_teldom", new int[]{432, 60});
        coordenadas_docume1.put("nu_telcel", new int[]{432, 240});
        coordenadas_docume1.put("no_corele", new int[]{432, 415});

        //DATOS DEL TRABAJO
        coordenadas_docume1.put("ti_deplab_I", new int[]{271, 97}); //<-----------PROBAR!!!!!!!!!!!!!!!!!!!!!!!!!!
        coordenadas_docume1.put("ti_deplab_D", new int[]{271, 208});

        coordenadas_docume1.put("no_cenlab", new int[]{255, 68});

        coordenadas_docume1.put("co_doctri", new int[]{241, 52});//ruc

        coordenadas_docume1.put("no_cargo", new int[]{214, 84});
        coordenadas_docume1.put("fe_ingres", new int[]{214, 410});

        coordenadas_docume1.put("no_acteco", new int[]{200, 77});

        coordenadas_docume1.put("no_deplab", new int[]{173, 180});
        coordenadas_docume1.put("no_prolab", new int[]{173, 270});
        coordenadas_docume1.put("no_dislab", new int[]{173, 378});

        coordenadas_docume1.put("no_dirlab", new int[]{159, 55});
        coordenadas_docume1.put("nu_camlab", new int[]{159, 325});
        coordenadas_docume1.put("nu_manlab", new int[]{159, 365});
        coordenadas_docume1.put("nu_lotlab", new int[]{159, 405});
        coordenadas_docume1.put("ti_intlab_1", new int[]{162, 450});//departamento
//        coordenadas_docume1.put("ti_intlab_2", );//quinta
        coordenadas_docume1.put("nu_intlab", new int[]{159, 522});

        coordenadas_docume1.put("no_reflab", new int[]{145, 65});

        coordenadas_docume1.put("nu_tellab", new int[]{119, 63});

        coordenadas_docume4 = new HashMap<>();

        coordenadas_docume4.put("fecha", new int[]{825, 115, 1});

        coordenadas_docume4.put("ti_credit_consumo", new int[]{753, 76, 1});
        coordenadas_docume4.put("ti_credit_mes", new int[]{753, 123, 1});
        coordenadas_docume4.put("im_financ", new int[]{752, 242, 1});
        coordenadas_docume4.put("im_cuoini", new int[]{752, 361, 1});
        coordenadas_docume4.put("co_expedi", new int[]{752, 485, 1});

        coordenadas_docume4.put("ca_plagra_no", new int[]{725, 44, 1});
        coordenadas_docume4.put("ca_plagra_si", new int[]{725, 75, 1});
        coordenadas_docume4.put("ca_plagra", new int[]{727, 100, 1});
        coordenadas_docume4.put("ti_plagra", new int[]{727, 133, 1});
        coordenadas_docume4.put("ti_plazo", new int[]{726, 243, 1});
        coordenadas_docume4.put("ca_plamen", new int[]{727, 267, 1});
        coordenadas_docume4.put("im_serban", new int[]{727, 360, 1});
        coordenadas_docume4.put("im_cuotas", new int[]{727, 480, 1});

        coordenadas_docume4.put("pr_tasmen", new int[]{695, 242, 1});
        coordenadas_docume4.put("ti_cuotas_s", new int[]{707, 398, 1});
        coordenadas_docume4.put("ti_cuotas_m", new int[]{707, 490, 1});

        coordenadas_docume4.put("no_descre_autos/motos", new int[]{665, 427, 1});
        coordenadas_docume4.put("no_descre_otros", new int[]{665, 485, 1});
        //-----------TI---------
        coordenadas_docume4.put("no_apepat_ti", new int[]{559, 20, 1});
        coordenadas_docume4.put("no_apemat_ti", new int[]{559, 188, 1});
        coordenadas_docume4.put("no_nombre_ti", new int[]{559, 358, 1});

        coordenadas_docume4.put("ti_docide_dni_ti", new int[]{524, 17, 1});
        coordenadas_docume4.put("ti_docide_ce_ti", new int[]{524, 55, 1});
        coordenadas_docume4.put("co_docide_ti", new int[]{524, 130, 1});
        coordenadas_docume4.put("fe_nacimi_ti", new int[]{524, 245, 1});
        coordenadas_docume4.put("ti_genero_f_ti", new int[]{524, 470, 1});
        coordenadas_docume4.put("ti_genero_m_ti", new int[]{524, 525, 1});

        coordenadas_docume4.put("no_nacion_ti", new int[]{485, 20, 1});
        coordenadas_docume4.put("no_estciv_soltero_ti", new int[]{485, 187, 1});
        coordenadas_docume4.put("no_estciv_casado_ti", new int[]{485, 254, 1});
        coordenadas_docume4.put("no_estciv_conviviente_ti", new int[]{485, 323, 1});
        coordenadas_docume4.put("no_estciv_divorciado_ti", new int[]{485, 402, 1});
        coordenadas_docume4.put("no_estciv_viudo_ti", new int[]{485, 472, 1});

        coordenadas_docume4.put("no_direcc_ti", new int[]{442, 15, 1});
        coordenadas_docume4.put("no_urbani_ti", new int[]{442, 355, 1});
        coordenadas_docume4.put("no_distri_ti", new int[]{442, 411, 1});
        coordenadas_docume4.put("no_provin_ti", new int[]{442, 468, 1});
        coordenadas_docume4.put("no_depart_ti", new int[]{442, 525, 1});

        coordenadas_docume4.put("no_refere_ti", new int[]{402, 15, 1});
        coordenadas_docume4.put("no_corele_ti", new int[]{402, 301, 1});
        coordenadas_docume4.put("nu_telefo_ti", new int[]{402, 476, 1});

        coordenadas_docume4.put("ti_sitlab_independiente_ti", new int[]{367, 17, 1});
        coordenadas_docume4.put("ti_sitlab_dependiente_ti", new int[]{367, 68, 1});
        coordenadas_docume4.put("no_carlab_ti", new int[]{362, 411, 1});
        coordenadas_docume4.put("no_dirlab_ti", new int[]{362, 469, 1});

        coordenadas_docume4.put("nu_ruclab_ti", new int[]{323, 20, 1});
        coordenadas_docume4.put("no_acteco_ti", new int[]{323, 72, 1});
        coordenadas_docume4.put("nu_tellab_ti", new int[]{323, 196, 1});
        coordenadas_docume4.put("fe_ingres_ti", new int[]{323, 364, 1});
        coordenadas_docume4.put("im_ingnet_ti", new int[]{323, 480, 1});
        //-----------CO---------
        coordenadas_docume4.put("no_apepat_co", new int[]{183, 20, 1});
        coordenadas_docume4.put("no_apemat_co", new int[]{183, 188, 1});
        coordenadas_docume4.put("no_nombre_co", new int[]{183, 358, 1});

        coordenadas_docume4.put("ti_docide_dni_co", new int[]{148, 18, 1});
        coordenadas_docume4.put("ti_docide_ce_co", new int[]{148, 56, 1});
        coordenadas_docume4.put("co_docide_co", new int[]{148, 130, 1});
        coordenadas_docume4.put("fe_nacimi_co", new int[]{148, 245, 1});
        coordenadas_docume4.put("ti_genero_f_co", new int[]{148, 472, 1});
        coordenadas_docume4.put("ti_genero_m_co", new int[]{148, 526, 1});

        coordenadas_docume4.put("no_nacion_co", new int[]{109, 20, 1});
        coordenadas_docume4.put("no_estciv_soltero_co", new int[]{109, 190, 1});
        coordenadas_docume4.put("no_estciv_casado_co", new int[]{109, 257, 1});
        coordenadas_docume4.put("no_estciv_conviviente_co", new int[]{109, 325, 1});
        coordenadas_docume4.put("no_estciv_divorciado_co", new int[]{109, 405, 1});
        coordenadas_docume4.put("no_estciv_viudo_co", new int[]{109, 474, 1});

        coordenadas_docume4.put("no_direcc_co", new int[]{66, 15, 1});
        coordenadas_docume4.put("no_urbani_co", new int[]{66, 356, 1});
        coordenadas_docume4.put("no_distri_co", new int[]{66, 412, 1});
        coordenadas_docume4.put("no_provin_co", new int[]{66, 469, 1});
        coordenadas_docume4.put("no_depart_co", new int[]{66, 526, 1});

        coordenadas_docume4.put("no_refere_co", new int[]{822, 15, 2});
        coordenadas_docume4.put("no_corele_co", new int[]{822, 301, 2});
        coordenadas_docume4.put("nu_telefo_co", new int[]{822, 476, 2});//402

        coordenadas_docume4.put("ti_sitlab_independiente_co", new int[]{786, 18, 2});
        coordenadas_docume4.put("ti_sitlab_dependiente_co", new int[]{786, 69, 2});
        coordenadas_docume4.put("no_carlab_co", new int[]{781, 412, 2});
        coordenadas_docume4.put("no_dirlab_co", new int[]{781, 470, 2});

        coordenadas_docume4.put("nu_ruclab_co", new int[]{743, 20, 2});
        coordenadas_docume4.put("no_acteco_co", new int[]{743, 73, 2});
        coordenadas_docume4.put("nu_tellab_co", new int[]{743, 196, 2});
        coordenadas_docume4.put("fe_ingres_co", new int[]{743, 364, 2});
        coordenadas_docume4.put("im_ingnet_co", new int[]{743, 480, 2});
        //-----------GA---------
        coordenadas_docume4.put("no_apepat_ga", new int[]{602, 20, 2});
        coordenadas_docume4.put("no_apemat_ga", new int[]{602, 188, 2});
        coordenadas_docume4.put("no_nombre_ga", new int[]{602, 358, 2});

        coordenadas_docume4.put("ti_docide_dni_ga", new int[]{567, 17, 2});
        coordenadas_docume4.put("ti_docide_ce_ga", new int[]{567, 55, 2});
        coordenadas_docume4.put("co_docide_ga", new int[]{567, 130, 2});
        coordenadas_docume4.put("fe_nacimi_ga", new int[]{567, 245, 2});
        coordenadas_docume4.put("ti_genero_f_ga", new int[]{567, 472, 2});
        coordenadas_docume4.put("ti_genero_m_ga", new int[]{567, 525, 2});

        coordenadas_docume4.put("no_nacion_ga", new int[]{528, 20, 2});
        coordenadas_docume4.put("no_estciv_soltero_ga", new int[]{528, 188, 2});
        coordenadas_docume4.put("no_estciv_casado_ga", new int[]{528, 255, 2});
        coordenadas_docume4.put("no_estciv_conviviente_ga", new int[]{528, 323, 2});
        coordenadas_docume4.put("no_estciv_divorciado_ga", new int[]{528, 403, 2});
        coordenadas_docume4.put("no_estciv_viudo_ga", new int[]{528, 472, 2});

        coordenadas_docume4.put("no_direcc_ga", new int[]{485, 15, 2});
        coordenadas_docume4.put("no_urbani_ga", new int[]{485, 356, 2});
        coordenadas_docume4.put("no_distri_ga", new int[]{485, 412, 2});
        coordenadas_docume4.put("no_provin_ga", new int[]{485, 470, 2});
        coordenadas_docume4.put("no_depart_ga", new int[]{485, 526, 2});

        coordenadas_docume4.put("no_refere_ga", new int[]{445, 15, 2});
        coordenadas_docume4.put("no_corele_ga", new int[]{445, 301, 2});
        coordenadas_docume4.put("nu_telefo_ga", new int[]{445, 476, 2});//402
        //-----------CG---------
        coordenadas_docume4.put("no_apepat_cg", new int[]{372, 20, 2});
        coordenadas_docume4.put("no_apemat_cg", new int[]{372, 188, 2});
        coordenadas_docume4.put("no_nombre_cg", new int[]{372, 358, 2});

        coordenadas_docume4.put("ti_docide_dni_cg", new int[]{336, 17, 2});
        coordenadas_docume4.put("ti_docide_ce_cg", new int[]{336, 55, 2});
        coordenadas_docume4.put("co_docide_cg", new int[]{336, 130, 2});
        coordenadas_docume4.put("fe_nacimi_cg", new int[]{336, 245, 2});
        coordenadas_docume4.put("ti_genero_f_cg", new int[]{336, 471, 2});
        coordenadas_docume4.put("ti_genero_m_cg", new int[]{336, 525, 2});

        coordenadas_docume4.put("no_nacion_cg", new int[]{298, 20, 2});
        coordenadas_docume4.put("no_estciv_soltero_cg", new int[]{298, 188, 2});
        coordenadas_docume4.put("no_estciv_casado_cg", new int[]{298, 255, 2});
        coordenadas_docume4.put("no_estciv_conviviente_cg", new int[]{298, 323, 2});
        coordenadas_docume4.put("no_estciv_divorciado_cg", new int[]{298, 403, 2});
        coordenadas_docume4.put("no_estciv_viudo_cg", new int[]{298, 472, 2});

        coordenadas_docume4_widths = new HashMap<>();
        coordenadas_docume4_widths.put("no_refere", 270.0);
        coordenadas_docume4_widths.put("no_carlab", 55.0);
        coordenadas_docume4_widths.put("no_direcc", 220.0);
        coordenadas_docume4_widths.put("no_urbani", 55.0);
        coordenadas_docume4_widths.put("no_acteco", 110.0);
        coordenadas_docume4_widths.put("no_distri", 55.0);
        coordenadas_docume4_widths.put("no_provin", 55.0);
        coordenadas_docume4_widths.put("no_depart", 55.0);
        coordenadas_docume4_widths.put("no_dirlab", 110.0);

        coordenadas_abaco = new HashMap<>();
        coordenadas_abaco.put("no_nombre", new int[]{665, 94, 1});
        coordenadas_abaco.put("no_apelli", new int[]{650, 94, 1});
        coordenadas_abaco.put("no_nacion", new int[]{635, 94, 1});
        coordenadas_abaco.put("fe_nacimi", new int[]{620, 94, 1});
        coordenadas_abaco.put("no_nivedu", new int[]{607, 130, 1});
        coordenadas_abaco.put("co_docide", new int[]{592, 94, 1});
        coordenadas_abaco.put("no_direcc", new int[]{563, 125, 1});
        coordenadas_abaco.put("no_propie_p", new int[]{550, 105, 1});
        coordenadas_abaco.put("no_propie_a", new int[]{550, 254, 1});
        coordenadas_abaco.put("no_propie_f", new int[]{550, 400, 1});
        coordenadas_abaco.put("no_estciv", new int[]{577, 390, 1});
        coordenadas_abaco.put("no_distridom", new int[]{563, 390, 1});
        coordenadas_abaco.put("nu_telefo", new int[]{563, 522, 1});
        coordenadas_abaco.put("nu_telcel", new int[]{550, 522, 1});
        coordenadas_abaco.put("no_cenlab", new int[]{524, 117, 1});
        coordenadas_abaco.put("no_dirlab", new int[]{510, 94, 1});
        coordenadas_abaco.put("nu_tellab", new int[]{495, 94, 1});
        coordenadas_abaco.put("fe_ingres", new int[]{495, 500, 1});
        coordenadas_abaco.put("no_cargotit", new int[]{495, 244, 1});
        coordenadas_abaco.put("no_ocupac", new int[]{524, 390, 1});
        coordenadas_abaco.put("no_distrilab", new int[]{510, 390, 1});
        coordenadas_abaco.put("im_ingnet", new int[]{427, 137, 1});
        coordenadas_abaco.put("ti_estciv", new int[]{427, 460, 1});
        coordenadas_abaco.put("no_proloc_p", new int[]{363, 67, 1});
        coordenadas_abaco.put("no_proloc_a", new int[]{363, 220, 1});
        coordenadas_abaco.put("no_proloc_f", new int[]{363, 365, 1});
        coordenadas_abaco.put("no_proloc_o", new int[]{363, 496, 1});
        coordenadas_abaco.put("no_nomcon", new int[]{252, 97, 1});
        coordenadas_abaco.put("no_apecon", new int[]{237, 97, 1});
        coordenadas_abaco.put("no_naccon", new int[]{224, 97, 1});
        coordenadas_abaco.put("fe_naccon", new int[]{210, 97, 1});
        coordenadas_abaco.put("no_niveducon", new int[]{197, 140, 1});
        coordenadas_abaco.put("co_doccon", new int[]{181, 97, 1});
        coordenadas_abaco.put("no_cenlabcon", new int[]{167, 123, 1});
        coordenadas_abaco.put("no_dirlabcon", new int[]{152, 97, 1});
        coordenadas_abaco.put("nu_telcon", new int[]{139, 97, 1});
        coordenadas_abaco.put("no_ocupaccon", new int[]{167, 392, 1});
        coordenadas_abaco.put("no_distrilabcon", new int[]{152, 392, 1});
        coordenadas_abaco.put("no_cargocon", new int[]{139, 392, 1});
        coordenadas_abaco.put("no_nomhij1", new int[]{100, 45, 1});
        coordenadas_abaco.put("no_nomhij2", new int[]{85, 45, 1});
        coordenadas_abaco.put("no_nomhij3", new int[]{68, 45, 1});
        coordenadas_abaco.put("no_nomhij4", new int[]{52, 45, 1});
        coordenadas_abaco.put("no_nomhij5", new int[]{36, 45, 1});
        coordenadas_abaco.put("fe_nachij1", new int[]{102, 490, 1});
        coordenadas_abaco.put("fe_nachij2", new int[]{87, 490, 1});
        coordenadas_abaco.put("fe_nachij3", new int[]{70, 490, 1});
        coordenadas_abaco.put("fe_nachij4", new int[]{54, 490, 1});
        coordenadas_abaco.put("fe_nachij5", new int[]{37, 490, 1});
        coordenadas_abaco.put("nu_anoact", new int[]{495, 502, 1});
        coordenadas_abaco.put("no_dircor", new int[]{325, 76, 1});

        coordenadas_abaco.put("no_entida1", new int[]{430, 55, 2});
        coordenadas_abaco.put("no_entida2", new int[]{415, 55, 2});
        coordenadas_abaco.put("no_entida3", new int[]{399, 55, 2});
        coordenadas_abaco.put("im_cuenta1", new int[]{430, 285, 2});
        coordenadas_abaco.put("im_cuenta2", new int[]{415, 285, 2});
        coordenadas_abaco.put("im_cuenta3", new int[]{399, 285, 2});
        coordenadas_abaco.put("no_titula_firma", new int[]{30, 223, 2});
        coordenadas_abaco.put("no_conyugue_firma", new int[]{30, 412, 2});
        coordenadas_abaco.put("fe_caraba", new int[]{57, 72, 2});

        coordenadas_carta_de_retiro = new HashMap<>();
        coordenadas_carta_de_retiro.put("no_diafec", new int[]{752, 95});
        coordenadas_carta_de_retiro.put("no_mesfec", new int[]{752, 155});
        coordenadas_carta_de_retiro.put("no_anofec", new int[]{752, 280});
        coordenadas_carta_de_retiro.put("no_nombre", new int[]{235, 218});
        coordenadas_carta_de_retiro.put("co_docide", new int[]{219, 125});
//        coordenadas_carta_de_retiro.put("nu_codsoc", new int[]{205, 205});

    }

    private PagEspBean gson(String atr) {
        PagEspBean p = null;
        try {
            Gson gson = new Gson();
            p = gson.fromJson(atr, PagEspBean.class);
        } catch (Exception e) {
//            Escritor.escribe_errors("Leyendo el Json \n" + e.toString());
        }
        return p;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
