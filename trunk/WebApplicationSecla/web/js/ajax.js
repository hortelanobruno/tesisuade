var req;
var target;
var isIE;

function pagoEfectivo() {
    var div = document.getElementById("pago2");
    var datos = "<table width='100%' cellpadding='1' cellspacing='5'>";
    datos += "<tr>";
    datos += "<td width='150'>Monto</td>";
    datos += "<td><input name='monto' type='text' size='30' /><br><label class='error' id='monto2' style='visibility:hidden'></label></td>";
    datos += "</tr>";
    datos += "</table>";
    div.innerHTML = datos;
}

function pagoCheque() {
    var div = document.getElementById("pago2");
    var datos = "<table width='100%' cellpadding='1' cellspacing='5'>";
    datos += "<tr>";
    datos += "<td width='100px' >Numero Cheque</td>";
    datos += "<td><input name='numerocheque' type='text' size='30' /><br><label class='error' id='ncheque' style='visibility:hidden'></label></td>";
    datos += "</tr>";
    datos += "<tr>";
    datos += "<td>Banco</td>";
    datos += "<td><input name='banco' type='text' size='30' /><br><label class='error' id='banco2' style='visibility:hidden'></label></td>";
    datos += "</tr>";
    datos += "<tr>";
    datos += "<td>Fecha de vencimiento</td>";
    datos += "<td><input name='date2' type='text' size='30' id='fecha3' readonly='readonly'/>";
    datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector2' />";
    datos += "<br><label class='error' id='fecha4' style='visibility:hidden'></label></td>";
    datos += "</tr>";
    datos += "<tr>";
    datos += "<td>Monto</td>";
    datos += "<td><input name='monto' type='text' size='30' /><br><label class='error' id='monto2' style='visibility:hidden'></label></td>";
    datos += "</tr>";
    datos += "</table>";
    div.innerHTML = datos;
    Calendar.setup({
        inputField: "fecha3",
        ifFormat:   "%d / %m / %Y",
        button:     "selector2"
    });
}

function cargaraDatosUsuario() {
    var responsable = document.form1.listaUsuarios.value
    initRequest('url');
    var handlerFunction = getReadyStateHandler(req, processRequestCargarDatosUsuario);
    req.onreadystatechange = handlerFunction;
    req.open("POST", "../datosUsuario", true);
    req.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded; charset=UTF-8");
    req.send("responsable="+responsable);
}

function cargarDatosUsuarioAMod(){
    var responsable = document.form1.listaUsuarios.value
    initRequest('url');
    var handlerFunction = getReadyStateHandler(req, processRequestCargarDatosUsuarioAMod);
    req.onreadystatechange = handlerFunction;
    req.open("POST", "../datosUsuario", true);
    req.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded; charset=UTF-8");
    req.send("responsable="+responsable);
}

function borrarArea(){
    var responsable = document.form1.listaUsuarios.value
    initRequest('url');
    var handlerFunction = getReadyStateHandler(req, processRequestBorrarArea);
    req.onreadystatechange = handlerFunction;
    req.open("POST", "../borrarArea", true);
    req.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded; charset=UTF-8");
    req.send("responsable="+responsable);
}

function processRequestBorrarArea(){
    var borrar = req.responseXML.getElementsByTagName("borrar")[0].childNodes[0].nodeValue;
    if(borrar == "false"){
        document.getElementById("mensaje").innerHTML = "El responsable posee recibos pendientes o a confirmar";
    }else{
        document.getElementById("mensaje").innerHTML = "El responsable se puede borrar. <input type='button' value='Eliminar Responsable' onClick='confirmarBorrarArea()'  /><input type='hidden' name='eliminar' value='si' />";
    }
}

function processRequestCargarDatosUsuarioAMod(){
    var _responsable = req.responseXML.getElementsByTagName("responsable")[0].childNodes[0].nodeValue;
    var _sector = req.responseXML.getElementsByTagName("sector")[0].childNodes[0].nodeValue;
    var _sede = req.responseXML.getElementsByTagName("sede")[0].childNodes[0].nodeValue;
    var _digitos = req.responseXML.getElementsByTagName("digitos")[0].childNodes[0].nodeValue;
    var _diga =_digitos.substring(0,2);
    var _digr =_digitos.substring(2,4);
    document.getElementById("res").innerHTML = "<input name='responsable' type='text' size='30' value='"+_responsable+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/><br><label class='error' id='resp' style='visibility:hidden'></label>";
    document.getElementById("sect").innerHTML = "<input name='sector' type='text' size='30' value="+_sector+" onkeypress='if(event.keyCode == 13) validarAltaArea()'/><br><label class='error' id='sec' style='visibility:hidden'></label>";
    document.getElementById("sede").innerHTML = "<input name='sede' type='text' size='30' value='"+_sede+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/><br><label class='error' id='sed' style='visibility:hidden'></label>";
    document.getElementById("diga").innerHTML = "<input name='digarea' type='text' size='30' value='"+_diga+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/><br><label class='error' id='dig1' style='visibility:hidden'></label>";
    document.getElementById("digr").innerHTML = "<input name='digresp' type='text' size='30' value='"+_digr+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/><br><label class='error' id='dig2' style='visibility:hidden'></label>";
    document.getElementById("but").innerHTML = "<input name='cargar' type='button' value='Modificar Responsable' style='width:150px' onClick='validarModArea()'/>	";
}

function processRequestCargarDatosUsuario() {
    var _responsable = req.responseXML.getElementsByTagName("responsable")[0].childNodes[0].nodeValue;
    var _sector = req.responseXML.getElementsByTagName("sector")[0].childNodes[0].nodeValue;
    var _sede = req.responseXML.getElementsByTagName("sede")[0].childNodes[0].nodeValue;
    var _digitos = req.responseXML.getElementsByTagName("digitos")[0].childNodes[0].nodeValue;
    var _password = req.responseXML.getElementsByTagName("password")[0].childNodes[0].nodeValue;
    var _cuenta = req.responseXML.getElementsByTagName("cuenta")[0].childNodes[0].nodeValue;
    document.getElementById("res").innerHTML = _responsable;
    document.getElementById("sec").innerHTML = _sector;
    document.getElementById("sede").innerHTML = _sede;
    document.getElementById("dig").innerHTML = _digitos;
    document.getElementById("pwd").innerHTML = _password;
    document.getElementById("cue").innerHTML = _cuenta;
}

function verReciboAModificar(){
    var numero = document.form1.recibos.value
    if(numero != ""){
        var url = "../modificarRecibo?numero="+ numero;
        initRequest(url);
        req.onreadystatechange = processRequestVerReciboAModificar;
        req.open("GET", url, true);
        req.send(null);
    }
}

function processRequestVerReciboAModificar(){
    if (req.readyState == 4) {
        if (req.status == 200) {
            var div = document.getElementById("rec");
            var datos = "<table width='100%' cellpadding='1' cellspacing='5'>";
            var estadoboleta = req.responseXML.getElementsByTagName("estadoboleta")[0].childNodes[0].nodeValue;
            var motivo,fecharendicion,beneficiario,monto,fechavencimiento,banco,numerocheque,numerocuota,numeroacta;
            if(estadoboleta != "completada"){
                motivo = req.responseXML.getElementsByTagName("motivo")[0].childNodes[0].nodeValue;
                fecharendicion = req.responseXML.getElementsByTagName("fecharendicion")[0].childNodes[0].nodeValue;
                datos += "<tr>"
                datos += "<td width='180px' >Fecha</td>"
                datos += "<td><input name='date' type='text' size='30' id='fecha' readonly='readonly' value ='"+fecharendicion+"'/>"
                datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector' />"
                datos += "<br><label class='error' id='fecha2' style='visibility:hidden'></label></td>"
                datos += "</tr>"
                datos += "<tr>"
                datos += "<td valign='top'>Motivo</td>"
                datos += "<td><textarea name='motivo' cols='24' rows='10'>"+motivo+"</textarea><br><label class='error' id='motivo1' style='visibility:hidden'>Debe completar el campo</label></td>"
                datos += "</tr>"
                datos += "<tr >"
                datos += "<td colspan='2' height='30px'></td>"
                datos += "</tr>"
                datos += "<tr>"
                datos += "<td height='30px' colspan='2' align='center'>"
                datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarAnularRecibo()'/>"
                datos += "</td>"
                datos += "</tr>"
                tipopago = "efectivo";
            }else{
                motivo = '';
                fecharendicion = req.responseXML.getElementsByTagName("fecharendicion")[0].childNodes[0].nodeValue;
                beneficiario = req.responseXML.getElementsByTagName("beneficiario")[0].childNodes[0].nodeValue;
                monto = req.responseXML.getElementsByTagName("monto")[0].childNodes[0].nodeValue;
                if(req.responseXML.getElementsByTagName("numeroacta")[0].childNodes.length != 0){
                    numeroacta = req.responseXML.getElementsByTagName("numeroacta")[0].childNodes[0].nodeValue;
                }
                numerocuota = req.responseXML.getElementsByTagName("numerocuota")[0].childNodes[0].nodeValue;
                numerocuota = numerocuota.split("/");
                var ncuota1 = numerocuota[0];
                var ncuota2 = numerocuota[1];
                if((req.responseXML.getElementsByTagName("banco")[0].childNodes.length  != 0)){
                    //Pago con cheque
                    banco = req.responseXML.getElementsByTagName("banco")[0].childNodes[0].nodeValue;
                    numerocheque = req.responseXML.getElementsByTagName("numerocheque")[0].childNodes[0].nodeValue;
                    fechavencimiento = req.responseXML.getElementsByTagName("fechavencimiento")[0].childNodes[0].nodeValue;
                    datos += "<tr>";
                    datos += "<td>Fecha de confecci&oacute;n</td>";
                    datos += "<td><input name='date' type='text' size='30' id='fecha' readonly='readonly' value ='"+fecharendicion+"'/>";
                    datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector' />";
                    datos += "<br><label class='error' id='fecha2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td>Razon social</td>";
                    datos += "<td><input name='beneficiario' type='text' size='30' value='"+beneficiario+"' /><br><label class='error' id='beneficiario2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<td>Numero cuota</td>";
                    datos += "<td><input name='numerocuota1' type='text' size='1' value='"+ncuota1+"' />&nbsp;/&nbsp;<input name='numerocuota2' type='text' size='1' value='"+ncuota2+"' /><br><label class='error' id='ncuota' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    if((req.responseXML.getElementsByTagName("numeroacta")[0].childNodes.length  != 0)){
                        datos += "<td>Numero acta</td>";
                        datos += "<td><input name='numeroacta' type='text' size='30' value='"+numeroacta+"' /><br><label class='error' id='nacta' style='visibility:hidden'></label></td>";
                        datos += "</tr>";
                    }
                    datos += "<tr>";
                    datos += "<td>Fecha de vencimiento</td>";
                    datos += "<td><input name='date2' type='text' size='30' id='fecha3' readonly='readonly' value ='"+fechavencimiento+"'/>";
                    datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector2' />";
                    datos += "<br><label class='error' id='fecha4' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<td>Banco</td>";
                    datos += "<td><input name='banco' type='text' size='30' value='"+banco+"' /><br><label class='error' id='banco2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<td>Numero cheque</td>";
                    datos += "<td><input name='numerocheque' type='text' size='30' value='"+numerocheque+"' /><br><label class='error' id='ncheque' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td>Monto</td>";
                    datos += "<td><input name='monto' type='text' size='30' value='"+monto+"' /><br><label class='error' id='monto2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr >";
                    datos += "<td colspan='2' height='30px'></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td height='30px' colspan='2' align='center'>";
                    if((req.responseXML.getElementsByTagName("numeroacta")[0].childNodes.length  != 0)){
                        datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarModificarReciboInspector()'/>";
                    }else{
                        datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarModificarReciboOperador()'/>";
                    }
                    datos += "</td>";
                    datos += "</tr>";
                }else{
                    //Pago en efectivo
                    datos += "<tr>";
                    datos += "<td>Fecha de confecci&oacute;n</td>";
                    datos += "<td><input name='date' type='text' size='30' id='fecha' readonly='readonly' value ='"+fecharendicion+"'/>";
                    datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector' />";
                    datos += "<br><label class='error' id='fecha2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td>Razon social</td>";
                    datos += "<td><input name='beneficiario' type='text' size='30' value='"+beneficiario+"' /><br><label class='error' id='beneficiario2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td>Numero cuota</td>";
                    datos += "<td><input name='numerocuota1' type='text' size='1' value='"+ncuota1+"' />&nbsp;/&nbsp;<input name='numerocuota2' type='text' size='1' value='"+ncuota2+"' /><br><label class='error' id='ncuota' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    if((req.responseXML.getElementsByTagName("numeroacta")[0].childNodes.length  != 0)){
                        datos += "<td>Numero acta</td>";
                        datos += "<td><input name='numeroacta' type='text' size='30' value='"+numeroacta+"' /><br><label class='error' id='nacta' style='visibility:hidden'></label></td>";
                        datos += "</tr>";
                    }
                    datos += "<td>Monto</td>";
                    datos += "<td><input name='monto' type='text' size='30' value='"+monto+"' /><br><label class='error' id='monto2' style='visibility:hidden'></label></td>";
                    datos += "</tr>";
                    datos += "<tr >";
                    datos += "<td colspan='2' height='30px'></td>";
                    datos += "</tr>";
                    datos += "<tr>";
                    datos += "<td height='30px' colspan='2' align='center'>";
                    if((req.responseXML.getElementsByTagName("numeroacta")[0].childNodes.length  != 0)){
                        datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarModificarReciboInspector()'/>";
                    }else{
                        datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarModificarReciboOperador()'/>";
                    }
                    datos += "</td>";
                    datos += "</tr>";
                }
            }
            datos += "</table>";
            div.innerHTML = datos;
            Calendar.setup({
                inputField: "fecha",
                ifFormat:   "%d / %m / %Y",
                button:     "selector"
            });
            if((req.responseXML.getElementsByTagName("banco")[0].childNodes.length  != 0)){
                Calendar.setup({
                    inputField: "fecha3",
                    ifFormat:   "%d / %m / %Y",
                    button:     "selector2"
                });
            }
        }
    }
}

function verReciboPendientes(){
    var responsable = document.form1.listaOperadores.value
    if(responsable != ''){
        initRequest('url');
        var handlerFunction = getReadyStateHandler(req, processRequestVerReciboPendientes);
        req.onreadystatechange = handlerFunction;
        req.open("POST", "../verRecibosPendientes", true);
        req.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded; charset=UTF-8");
        req.send("responsable="+responsable);
    }
}

function verRecibo() {
    var responsable = document.form1.usuarios.value
    if(responsable != ''){
        initRequest('url');
        var handlerFunction = getReadyStateHandler(req, processRequestVerRecibo);
        req.onreadystatechange = handlerFunction;
        req.open("POST", "../verRecibo", true);
        req.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded; charset=UTF-8");
        req.send("responsable="+responsable);
    }
}
function getReadyStateHandler(req, responseXmlHandler) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error: "+req.status);
            }
        }
    }
}

function processRequestVerReciboPendientes(){
    var estado = req.responseXML.getElementsByTagName('estado')[0].childNodes[0].nodeValue;
    var div;
    if(estado == 'vacio'){
        div = document.getElementById('recibos');
        div.innerHTML = "<table width='100%'><tr><tdalign='center'>No hay boletas para devolver</td></tr></table>";
    }else{
        div = document.getElementById('recibos');
        var datos = "<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
        datos += "<tr><td align='center'>Numero</td><td align='center'>Devolver</td></tr>";
        var boleta = req.responseXML.getElementsByTagName('numero');
        for(i=0 ; i < boleta.length ; i++){
            var numero = boleta[i].childNodes[0].nodeValue;
            datos += "<tr><td align='center'>"+numero+"</td><td align='center'><input id='"+i+"' name='numero' type='checkbox' value='"+numero+"' /></td></tr>";
        }
        datos += '</table><br><br>';
        datos += "<input name='devolver' type='submit' value='Devolver' style='width:100px' />";
        div.innerHTML = datos;
    }
}

function processRequestVerRecibo() {
    var estado = req.responseXML.getElementsByTagName('estado')[0].childNodes[0].nodeValue;
    var div;
    if(estado == 'vacio'){
        div = document.getElementById('recibos');
        div.innerHTML = "<table width='100%'><tr><tdalign='center'>No hay boletas por confirmar</td></tr></table>";
    }else{
        var cantidad = req.responseXML.getElementsByTagName('cantidad')[0].childNodes[0].nodeValue;
        div = document.getElementById('recibos');
        var datos = "<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
        datos += "<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha confeccion</td><td align='center'>Razon social</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Numero Cuota</td><td align='center'>Banco</td><td align='center'>Numero Cheque</td><td align='center'>Fecha de Vencimiento</td><td align='center'>Confirmar</td></tr>";
        var boleta = req.responseXML.getElementsByTagName('boleta');
        for(i=0 ; i < boleta.length ; i++){
            var nodes = boleta[i].childNodes;
            var numero = nodes[0].childNodes[0].nodeValue;
            var estadoboleta = nodes[1].childNodes[0].nodeValue;
            var motivo;
            var fecharendicion;
            var beneficiario;
            var monto;
            var numerocuota;
            var banco;
            var numerocheque;
            var fechavencimiento;
            if(estadoboleta != 'completada'){
                motivo = nodes[5].childNodes[0].nodeValue;
            }else{
                motivo = '';
                fecharendicion = nodes[2].childNodes[0].nodeValue;
                beneficiario = nodes[3].childNodes[0].nodeValue;
                monto = nodes[4].childNodes[0].nodeValue;
                numerocuota =nodes[6].childNodes[0].nodeValue;
                banco=nodes[7].childNodes[0];
                if(banco == null){
                    banco="&nbsp;";
                    numerocheque="&nbsp;";
                    fechavencimiento="&nbsp;";
                }else{
                    banco=nodes[7].childNodes[0].nodeValue;
                    numerocheque=nodes[8].childNodes[0].nodeValue;
                    fechavencimiento=nodes[9].childNodes[0].nodeValue;
                }
            }
            if(motivo == ''){
                datos += "<tr><td align='center'>"+numero+"</td><td align='center'>"+estadoboleta+"</td><td align='center'>"+fecharendicion+"</td><td align='center'>"+beneficiario+"</td><td align='center'>"+monto+"</td><td>&nbsp;</td><td align='center' >"+numerocuota+"</td><td align='center' >"+banco+"</td><td align='center' >"+numerocheque+"</td><td align='center' >"+fechavencimiento+"</td><td align='center'><input id='"+i+"' name='recibo' type='checkbox' value='"+numero+"' /></td></tr>";
            }else{
                datos += "<tr><td align='center'>"+numero+"</td><td align='center'>"+estadoboleta+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align='center'>"+motivo+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align='center'><input name='recibo' id='"+i+"' type='checkbox' value='"+numero+"' /></td></tr>";
            }
        }
        datos += '</table><br><br>';
        datos += "<input name='cargar' type='submit' value='Confirmar' style='width:100px'/>";
        div.innerHTML = datos;
    }
}

function newXMLHttpRequest() {

    var xmlreq = false;

    if (window.XMLHttpRequest) {

        // Create XMLHttpRequest object in non-Microsoft browsers
        xmlreq = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        // Create XMLHttpRequest via MS ActiveX
        try {
            // Try to create XMLHttpRequest in later versions
            // of Internet Explorer

            xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

        } catch (e1) {

            // Failed to create required ActiveXObject

            try {
                // Try version supported by older versions
                // of Internet Explorer

                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

            } catch (e2) {

            // Unable to create an XMLHttpRequest with ActiveX
            }
        }
    }

    return xmlreq;
}


function initRequest(url) {
    //if (window.XMLHttpRequest) {
    //   req = new XMLHttpRequest();
    // } else if (window.ActiveXObject) {
    //     isIE = true;
    //     req = new ActiveXObject('Microsoft.XMLHTTP');
    // }
    if(window.ActiveXObject)
    {
        try
        {
            isIE = true;
            req = new ActiveXObject('Microsoft.XMLHTTP');
        }
        catch (e)
        {
            req = false;
        }
    }else{
        try
        {
            req = new XMLHttpRequest();
        }
        catch (e)
        {
            req = false;
        }
    }
    if (!req)
    {
        alert('Error creating the XMLHttpRequest object.');
    }
}
            
