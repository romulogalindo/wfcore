<%@page contentType="text/html" %>
<%@page pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script>
        function handleFileSelect(e) {
            //ir a su padre y decirle que tiene data
            parent.document.inputuploadchange("${param.id}");
        }
    </script>
</head>
<body>
<form id="form_data" action="/doc?ti_docume=U" enctype="MULTIPART/FORM-DATA" method="post" accept-charset="ISO-8859-1">
    <%--<buttom id="va_regist_file2" type="button" name="va_regist_file2"--%>
    <%--onclick="document.getElementById('va_regist_file').click()" class="load_buttom" style=" "--%>
    <%--nuevo="<%=nuevo%>">--%>
    <%--Seleccione archivo--%>
    <%--</buttom>--%>

    <%--<input id="vafile" type="file" name="vafile" onchange="handleFileSelect(event)"/>--%>
    <input id="vafile" type="file" name="vafile" domid="${param.id}"/>
</form>
<%--<label id="NAME_FILE" class="tdPagina4" name="" estado="UNCOMPLETE" style="display:none;"></label>--%>
</body>
</html>