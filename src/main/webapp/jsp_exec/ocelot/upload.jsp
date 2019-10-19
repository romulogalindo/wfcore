<%@page contentType="text/html" %>
<%@page pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script>
        function handleFileSelect(e) {
            parent.document.inputuploadchange("${param.id}");
        }

        function autoload(evt) {
            var auto = ${empty param.auto ? 'false': param.auto };
            console.log('auto:' + auto);
            // parent.document.onchange_vafile(document.getElementById('vafile'));
            window.parent.onchange_vafile2(document.getElementById('vafile'));
            if (auto) {
                console.log('upload:' + evt);
                document.getElementById('form_data').submit();
            }
        }
    </script>
</head>
<body>
<%--<c:if test="${not empty param.dt_archiv}">
    <pre>
    {"status":"OK","result":[{"co_archiv":${param.extfil},"no_archiv":"${param.extfil}","fe_archiv":""}]}
    </pre>
</c:if>--%>
<form id="form_data" action="/doc?ti_docume=U&auto=${param.auto}&id=${param.id}" enctype="MULTIPART/FORM-DATA"
      method="post"
      accept-charset="UTF-8">
<%--      accept-charset="ISO-8859-1">--%>
    <input id="vafile" type="file" name="vafile" domid="${param.id}" onchange="autoload(event);"/>
</form>
</body>
</html>