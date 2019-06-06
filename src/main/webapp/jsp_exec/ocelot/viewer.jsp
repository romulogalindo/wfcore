<%@ page import="com.acceso.wfcore.log.Log" %>
<%@ page import="com.acceso.wfweb.dtos.ArchivDTO" %>
<%@ page import="com.acceso.wfweb.daos.Frawor4DAO" %>
<%@ page import="com.acceso.wfcore.kernel.WFIOAPP" %>
<%@ page import="com.acceso.wfcore.utils.Util" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
    long viewer_id = System.currentTimeMillis();
    Log.info("[viewer.jsp] viewer_id=" + viewer_id + ",param.file=" + request.getParameter("file") + ", param.tipo=" + request.getParameter("tipo"));
    int co_imagen = 0;
    String file = "";
    try {
        co_imagen = Integer.parseInt(request.getParameter("file"));
    } catch (Exception e) {
        file = request.getParameter("file");
    }

    String tipo_pagina = request.getParameter("tipo");
    FileInputStream fis = null;
    File fout = null;
    boolean existe = true;
    String filename = "";
    String full_filename = "";

    if (co_imagen != 0) {
        try {
            ArchivDTO arcadj = null;
            try {

                Frawor4DAO dao = new Frawor4DAO(WFIOAPP.APP.dataSourceService.getManager("wfaio").getNativeSession());
                arcadj = dao.getArchiv(co_imagen);
                dao.close();

            } catch (Exception ep) {
                ep.printStackTrace();
            }

            filename = arcadj.getCo_archiv() + "." + Util.getFileExtension(arcadj.getNo_archiv());
            full_filename = WFIOAPP.APP.getDataSourceService().getValueOfKey("AIO_DATA_FILE") + File.separator + Util.formatDate1(arcadj.getFe_archiv()) + File.separator + filename;
            Log.info("Buscando File:" + full_filename);

            File temp_file = new File(full_filename);
            System.out.println("temp_file = " + temp_file);
            System.out.println("temp_file = " + temp_file.exists());

            InputStream in = new FileInputStream(temp_file);
            OutputStream out2  = response.getOutputStream();
            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out2.write(buffer, 0, numBytesRead);
            }

            out2.flush();
            out2.close();
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.info("Archivo no encontrado sector 1:" + ex.getMessage());
            existe = false;
        }
    }


%>
<html>
<head>
    <meta http-equiv="expires" content="<%= new java.util.Date()%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="no-cache">
    <title><%=filename%>
    </title>
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/system/imagenes/${fn:toLowerCase(paquete_actual.no_paquet)}/favicon.ico">
</head>
<body>

<%
    if (existe) {
        response.sendRedirect(request.getContextPath() + "/images/documentos_dia/" + filename);
    } else {
%>
Archivo no encontrado.
<%
    }
%>


</body>
</html>
