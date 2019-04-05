<%--
    Document   : code
    Created on : 28-ago-2012, 10:42:09
    Author     : romulogalindo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/codemirror/codemirror.css">
    <script src="${pageContext.request.contextPath}/jsp_exec/js/codemirror/codemirror.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/codemirror/sql.js"></script>
    <script src="${pageContext.request.contextPath}/jsp_exec/js/codemirror/matchbrackets.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/codemirror/codemirror.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/codemirror/docs.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp_exec/css/codemirror/eclipse.css">--%>
    <style>.CodeMirror {
        border: 2px inset #dee;
    }</style>
    <script>
        var editor;

        function plsql() {
            editor = CodeMirror.fromTextArea(document.getElementById("code"), {
                lineNumbers: true,
                matchBrackets: true,
                indentUnit: 4,
                mode: "text/x-plsql"
            });
        }

        function setCode(code) {
            editor.setValue(code);
        }

        function getCode() {
            return editor.getValue();
        }
    </script>
</head>
<body onload="plsql()">
<textarea id="code" name="code">
                -- Oracle PL/SQL Code Demo
                /*
                   based on c-like mode, adapted to PL/SQL by Peter Raganitsch ( http://www.oracle-and-apex.com/ )
                   April 2011
                */
                DECLARE
                    vIdx    NUMBER;
                    vString VARCHAR2(100);
                    cText   CONSTANT VARCHAR2(100) := 'That''s it! Have fun with CodeMirror 2';
                BEGIN
                    vIdx := 0;
                    --
                    FOR rDATA IN
                      ( SELECT *
                          FROM EMP
                         ORDER BY EMPNO
                      )
                    LOOP
                        vIdx    := vIdx + 1;
                        vString := rDATA.EMPNO || ' - ' || rDATA.ENAME;
                        --
                        UPDATE EMP
                           SET SAL   = SAL * 101/100
                         WHERE EMPNO = rDATA.EMPNO
                        ;
                    END LOOP;
                    --
                    SYS.DBMS_OUTPUT.Put_Line (cText);
                END;
                --
        </textarea>

</body>
</html>
