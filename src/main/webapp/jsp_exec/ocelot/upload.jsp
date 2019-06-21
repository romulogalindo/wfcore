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
            // var tgt = evt.target || window.event.srcElement,
            //     files = tgt.files;
            //
            // // FileReader support
            // if (FileReader && files && files.length) {
            //     var fr = new FileReader();
            //     fr.onload = function () {
            //         document.getElementById(outImage).src = fr.result;
            //     }
            //     fr.readAsDataURL(files[0]);
            // }
            //
            // // Not supported
            // else {
            //     // fallback -- perhaps submit the input to an iframe and temporarily store
            //     // them on the server until the user's session ends.
            // }
            var auto = ${param.auto};
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
<form id="form_data" action="/doc?ti_docume=U&auto=${param.auto}" enctype="MULTIPART/FORM-DATA" method="post"
      accept-charset="ISO-8859-1">
    <input id="vafile" type="file" name="vafile" domid="${param.id}" onchange="autoload(event);"/>
</form>
</body>
</html>