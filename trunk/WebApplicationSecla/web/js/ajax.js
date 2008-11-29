var req;
var target;
var isIE;

function cargaraDatosUsuario() {
    var usuario   = this.form1.listaUsuarios.value
    var url = "../datosUsuario?usuario="+ usuario;
    initRequest(url);
    req.onreadystatechange = processRequestCargarDatosUsuario;
    req.open("GET", url, true); 
    req.send(null);
}

function cargarDatosUsuarioAMod(){
    var usuario   = this.form1.listaUsuarios.value
    var url = "../datosUsuario?usuario="+ usuario;
    initRequest(url);
    req.onreadystatechange = processRequestCargarDatosUsuarioAMod;
    req.open("GET", url, true);
    req.send(null);
}

function processRequestCargarDatosUsuarioAMod(){
    if (req.readyState == 4) {
        if (req.status == 200) {
            var _responsable = req.responseXML.getElementsByTagName("responsable")[0].childNodes[0].nodeValue;
            var _sector = req.responseXML.getElementsByTagName("sector")[0].childNodes[0].nodeValue;
            var _sede = req.responseXML.getElementsByTagName("sede")[0].childNodes[0].nodeValue;
            var _digitos = req.responseXML.getElementsByTagName("digitos")[0].childNodes[0].nodeValue;
            var _diga =_digitos.substring(0,2);
            var _digr =_digitos.substring(2,4);
            document.getElementById("res").innerHTML = "<input name='responsable' type='text' size='30' value='"+_responsable+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='resp' style='visibility:hidden'></label>";
            document.getElementById("sect").innerHTML = "<input name='sector' type='text' size='30' value="+_sector+" onkeypress='if(event.keyCode == 13) validarAltaArea()'/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='sec' style='visibility:hidden'></label>";
            document.getElementById("sede").innerHTML = "<input name='sede' type='text' size='30' value='"+_sede+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='sed' style='visibility:hidden'></label>";
            document.getElementById("diga").innerHTML = "<input name='digarea' type='text' size='30' value='"+_diga+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='dig1' style='visibility:hidden'></label>";
            document.getElementById("digr").innerHTML = "<input name='digresp' type='text' size='30' value='"+_digr+"' onkeypress='if(event.keyCode == 13) validarAltaArea()'/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='dig2' style='visibility:hidden'></label>";
            document.getElementById("but").innerHTML = "<input name='cargar' type='button' value='Alta Area' style='width:100px' onClick='validarModArea()'/>	";
        }
    }
}

function processRequestCargarDatosUsuario() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            var _responsable = req.responseXML.getElementsByTagName("responsable")[0].childNodes[0].nodeValue;
            var _sector = req.responseXML.getElementsByTagName("sector")[0].childNodes[0].nodeValue;
            var _sede = req.responseXML.getElementsByTagName("sede")[0].childNodes[0].nodeValue;
            var _digitos = req.responseXML.getElementsByTagName("digitos")[0].childNodes[0].nodeValue;
            var _password = req.responseXML.getElementsByTagName("password")[0].childNodes[0].nodeValue;
            document.getElementById("res").innerHTML = _responsable;
            document.getElementById("sec").innerHTML = _sector;
            document.getElementById("sede").innerHTML = _sede;
            document.getElementById("dig").innerHTML = _digitos;
            document.getElementById("pwd").innerHTML = _password;
        }
    }
}

function verReciboAModificar(){
    var numero = this.form1.recibos.value
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
            var motivo;
            var fecharendicion;
            var beneficiario;
            var monto;
            if(estadoboleta != "completada"){
                motivo = req.responseXML.getElementsByTagName("motivo")[0].childNodes[0].nodeValue;
                fecharendicion = req.responseXML.getElementsByTagName("fecharendicion")[0].childNodes[0].nodeValue;
            }else{
                motivo = '';
                fecharendicion = req.responseXML.getElementsByTagName("fecharendicion")[0].childNodes[0].nodeValue;
                beneficiario = req.responseXML.getElementsByTagName("beneficiario")[0].childNodes[0].nodeValue;
                monto = req.responseXML.getElementsByTagName("monto")[0].childNodes[0].nodeValue;
            }
            if(motivo == ''){
                datos += "<tr>";
                datos += "<td>Fecha de rendici&oacute;n</td>";
                datos += "<td><input name='date' type='text' size='30' id='fecha' readonly='readonly' value ='"+fecharendicion+"'/>";
                datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector' />";
                datos += "&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='fecha2' style='visibility:hidden'></label></td>";
                datos += "</tr>";
                datos += "<tr>";
                datos += "<td>Beneficiario</td>";
                datos += "<td><input name='beneficiario' type='text' size='30' value='"+beneficiario+"' />&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='beneficiario2' style='visibility:hidden'></label></td>";
                datos += "</tr>";
                datos += "<tr>";
                datos += "<td>Monto</td>";
                datos += "<td><input name='monto' type='text' size='30' value='"+monto+"' />&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='monto2' style='visibility:hidden'></label></td>";
                datos += "</tr>";
                datos += "<tr >";
                datos += "<td colspan='2' height='30px'></td>";
                datos += "</tr>";
                datos += "<tr>";
                datos += "<td height='30px' colspan='2' align='center'>";
                datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarCompletarRecibo()'/>";
                datos += "</td>";
                datos += "</tr>";
            }else{
                datos += "<tr>"
                datos += "<td>Fecha</td>"
                datos += "<td><input name='date' type='text' size='30' id='fecha' readonly='readonly' value ='"+fecharendicion+"'/>"
                datos += "<img src='../img/calendario.png'  width='20' height='20' id='selector' />"
                datos += "&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='fecha2' style='visibility:hidden'></label></td>"
                datos += "</tr>"
                datos += "<tr>"
                datos += "<td valign='top'>Motivo</td>"
                datos += "<td><textarea name='motivo' cols='24' rows='10'>"+motivo+"</textarea>&nbsp;&nbsp;&nbsp;&nbsp;<label class='error' id='motivo1' style='visibility:hidden'>Debe completar el campo</label></td>"
                datos += "</tr>"
                datos += "<tr >"
                datos += "<td colspan='2' height='30px'></td>"
                datos += "</tr>"
                datos += "<tr>"
                datos += "<td height='30px' colspan='2' align='center'>"
                datos += "<input name='cargar' type='button' value='Cargar recibo' style='width:100px' onClick='validarAnularRecibo()'/>"
                datos += "</td>"
                datos += "</tr>"
            }
            datos += "</table>";
            div.innerHTML = datos;
            Calendar.setup({
                inputField: "fecha",
                ifFormat:   "%d / %m / %Y",
                button:     "selector"
            });
        }
    }
}
function verRecibo() {
    var usuario = this.form1.usuarios.value
    if(usuario != ''){
        var url = '../verRecibo?usuario='+ usuario;
        initRequest(url);
        req.onreadystatechange = processRequestVerRecibo;
        req.open('GET', url, true);
        req.send(null);
    }
}

function processRequestVerRecibo() {
    //'<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>'
    //'<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha rendicion</td><td align='center'>Beneficiario</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Confirmar</td></tr>'
    //'<tr><td align='center'>'+boleta.getNumero()+'</td><td align='center'>'+boleta.getEstadoboleta()+'</td><td align='center'>'+boleta.getFecharendicion()+'</td><td align='center'>'+boleta.getBeneficiario()+'</td><td align='center'>'+boleta.getMonto()+'</td><td></td><td align='center'><input id=''+boleta.getNumero()+'' name=''+boleta.getNumero()+'' type='checkbox' value='' /></td></tr>'
    //'<tr><td align='center'>'+boleta.getNumero()+'</td><td align='center'>'+boleta.getEstadoboleta()+'</td><td></td><td></td><td></td><td align='center'>'+boleta.getMotivo()+'</td><td align='center'><input name=''+boleta.getNumero()+'' id=''+boleta.getNumero()+'' type='checkbox' value='' /></td></tr>'
    //'</table>'
    if (req.readyState == 4) {
        if (req.status == 200) {
            var estado = req.responseXML.getElementsByTagName('estado')[0].childNodes[0].nodeValue;
            if(estado == 'vacio'){
                var div = document.getElementById('recibos');
                div.innerHTML = "<table width='100%'><tr><tdalign='center'>No hay boletas por confirmar</td></tr></table>";
            }else{
                var cantidad = req.responseXML.getElementsByTagName('cantidad')[0].childNodes[0].nodeValue;
                var div = document.getElementById('recibos');
                var datos = "<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
                datos += "<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha rendicion</td><td align='center'>Beneficiario</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Confirmar</td></tr>";
                var boleta = req.responseXML.getElementsByTagName('boleta');
                for(i=0 ; i < boleta.length ; i++){
                    var nodes = boleta[i].childNodes;
                    var numero = nodes[0].childNodes[0].nodeValue;
                    var estadoboleta = nodes[1].childNodes[0].nodeValue;
                    var motivo;
                    var fecharendicion;
                    var beneficiario;
                    var monto;
                    if(estadoboleta != 'completada'){
                        motivo = nodes[5].childNodes[0].nodeValue;
                    }else{
                        motivo = '';
                        fecharendicion = nodes[2].childNodes[0].nodeValue;
                        beneficiario = nodes[3].childNodes[0].nodeValue;
                        monto = nodes[4].childNodes[0].nodeValue;
                    }
                    if(motivo == ''){
                        datos += "<tr><td align='center'>'+numero+'</td><td align='center'>'+estadoboleta+'</td><td align='center'>'+fecharendicion+'</td><td align='center'>'+beneficiario+'</td><td align='center'>'+monto+'</td><td>&nbsp;</td><td align='center'><input id=''+i+'' name='recibo' type='checkbox' value=''+numero+'' /></td></tr>";
                    }else{
                        datos += "<tr><td align='center'>'+numero+'</td><td align='center'>'+estadoboleta+'</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align='center'>'+motivo+'</td><td align='center'><input name='recibo' id=''+i+'' type='checkbox' value=''+numero+'' /></td></tr>";
                    }
                }
                datos += '</table><br><br>';
                datos += "<input name='cargar' type='submit' value='Confirmar' style='width:100px'/>";
                div.innerHTML = datos;
            }
        }
    }
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
            
//Este metodo obtiene el valor de un text field y lo manda al servidor para validar
//si es un usuario valido
function validateUserId() {
    if (!target) target = document.getElementById('userid');
    var url = 'validate?id=' + escape(target.value);

    // Invoke initRequest(url) to create XMLHttpRequest object
    initRequest(url);

    // The 'processRequest' function is set as a callback function.
    // (Please note that, in JavaScript, functions are first-class objects: they
    // can be passed around as objects.  This is different from the way
    // methods are treated in Java programming language.)
    req.onreadystatechange = processRequest;
    req.open('GET', url, true);
    req.send(null);
}

function processRequest() {
    if (req.readyState == 4) {
        if (req.status == 200) {

            // Extract 'true' or 'false' from the returned data from the server.
            // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
            var message = req.responseXML.getElementsByTagName('valid')[0].childNodes[0].nodeValue;

            // Call 'setMessageUsingDOM(message)' function to display
            // 'Valid User Id' or 'Invalid User Id' message.
            setMessageUsingDOM(message);

            // If the user entered value is not valid, do not allow the user to
            // click submit button.
            var submitBtn = document.getElementById('submit_btn');
            if (message == 'false') {
                submitBtn.disabled = true;
            } else {
                submitBtn.disabled = false;
            }
        }
    }
}

function setMessageUsingDOM(message) {
    var userMessageElement = document.getElementById('userIdMessage');
    var messageText;
    if (message == 'false') {
        userMessageElement.style.color = 'red';
        messageText = 'Invalid User Id';
    } else {
        userMessageElement.style.color = 'green';
        messageText = 'Valid User Id';
    }
    var messageBody = document.createTextNode(messageText);
    // if the messageBody element has been created simple replace it otherwise
    // append the new element
    if (userMessageElement.childNodes[0]) {
        userMessageElement.replaceChild(messageBody, userMessageElement.childNodes[0]);
    } else {
        userMessageElement.appendChild(messageBody);
    }
}

function disableSubmitBtn() {
    var submitBtn = document.getElementById('submit_btn');
    submitBtn.disabled = true;
}