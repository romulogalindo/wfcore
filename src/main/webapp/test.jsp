


<html co_conten="">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
        <title>UNNAMED</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-amber.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

        <!--CSS-->
        <link rel="stylesheet" href="/jsp_exec/css/workflow.css?a=6">

        <!--JS -->
        <script src="/jsp_exec/js/wfajax.js?a=25"></script>
    </head>
    <body style="padding: 20px;" onload="pagina();">
        <script>
            var height_table = 0;
        </script>
        <input type="hidden" id="height_table" value="">
        <input type="hidden" id="id_frawor" value="100001003">
        <input type="hidden" id="co_conten" value="194">
        <input type="hidden" id="co_pagina" value="327">

        <input type=hidden id=ti_pagina value=F /><input type=hidden id=ls_hamoda value="12" />
        <table id=PAG327 class="w3-table-all w3-tiny w3-card-4">
            <thead>   
                <tr>       
                    <th colspan=2 >           <h3>Información del cliente</h3>       </th>   
                </tr>
            </thead>
            <tbody>
                <tr name=P327T1 style="display:none;" co_pagtit="1">
                    <th colspan=2 class="wf_f_stitle w3-highway-blue" > </th></tr>
                <tr name=P327R1 style="display:none;" co_pagtit="1">
                    <td name=P327R1K class=wf_f_titreg>Expediente</td>
                    <td class=wf_f_valreg><span id='P327R1V' class="pagreg" name='P327R1V' va_pagreg="" ti_pagreg="1"></span></td>
                </tr>
                <tr name=P327R2 style="display:none;" co_pagtit="1">
                    <td name=P327R2K class=wf_f_titreg>Solicitante</td>
                    <td class=wf_f_valreg><span id='P327R2V' class="pagreg" name='P327R2V' va_pagreg="" ti_pagreg="1"></span></td>
                </tr>
                <tr name=P327R10 style="display:none;" co_pagtit="1"><td name=P327R10K class=wf_f_titreg>Documento de identidad</td>
                    <td class=wf_f_valreg><span id='P327R10V' class="pagreg" name='P327R10V' va_pagreg="" ti_pagreg="1"></span></td>
                </tr>
                <tr name=P327R11 style="display:none;" co_pagtit="1"><td name=P327R11K class=wf_f_titreg>Nombre completo</td>
                    <td class=wf_f_valreg><span id='P327R11V' class="pagreg" name='P327R11V' va_pagreg="" ti_pagreg="1"></span></td>
                </tr>
                <tr name=P327R12 style="display:none;" co_pagtit="1">
                    <td name=P327R12K class=wf_f_titreg>Estado</td>
                    <td class=wf_f_valreg>
                        <span id='P327R12V' name='P327R12V' ti_pagreg="3" class="pagreg" >
                            <select></select>
                        </span>
                    </td>
                </tr>
                <tr name=P327R13 style="display:none;" co_pagtit="1">
                    <td name=P327R13K class=wf_f_titreg>Comentario</td>
                    <td class=wf_f_valreg>
                        <span id='P327R13V' name='P327R13V' class="pagreg" ti_pagreg="9" >
<textarea class="pagreg w3-input w3-border" rows="5" cols="80"></textarea>
                        </span>
                    </td>
                </tr>
                <tr name=P327R14 style="display:none;" co_pagtit="1"><td name=P327R14K class=wf_f_titreg>Correo</td><td class=wf_f_valreg><span id='P327R14V' class="pagreg" name='P327R14V' va_pagreg="" ti_pagreg="1"></span></td></tr>
                <tr name=BTN>
                    <td name=BTNK class=wf_f_titreg></td>
                    <td class=wf_f_valreg>
                        <script> var cfila = 1;</script><script> var BTN1P = [];BTN1P[BTN1P.length] = new Parameter(1, 1);</script>
                        <button name=BTN1 class="w3-button w3-ripple w3-tiny w3-teal wfbutton" onclick="propag(1, true, 194)" >
                            <i class="fa fa-hand-pointer-o" aria-hidden="true"></i>
                            Grabar</button>

                        <script> var BTN2P = [];BTN2P[BTN2P.length] = new Parameter(1, 1);</script>
                        <button name=BTN2 class="w3-button w3-ripple w3-tiny w3-teal wfbutton" onclick="propag(2, false, 217)" >
                            <i class="fa fa-hand-pointer-o" aria-hidden="true"></i>
                            Atras</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <div id="loader" style="position:fixed; width:100%;height:100%;top: 0;left: 0;background-color: rgba(255,255,255,0.5);">
            <table style="width: 100%;height: 100%">
                <tr>
                    <td style="vertical-align:bottom;text-align: center;height: 50%;color: darkgray;">
                        <i class="fa fa-cog fa-spin fa-3x fa-fw"></i>
                    </td>
                </tr>
                <tr>
                    <td style="height: 50%; vertical-align: top;text-align: center">
                        Cargando página
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
