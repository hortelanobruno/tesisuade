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

function processRequestCargarDatosUsuario() {
    if (req.readyState == 4) {
      if (req.status == 200) {
        var responsable = req.responseXML.getElementsByTagName("usuario")[0].getElementsByTagName("responsable")[0].childNodes[0].nodeValue;
        var funcion = req.responseXML.getElementsByTagName("funcion")[0].childNodes[0].nodeValue;
        var secretaria = req.responseXML.getElementsByTagName("secretaria")[0].childNodes[0].nodeValue;
        var password = req.responseXML.getElementsByTagName("password")[0].childNodes[0].nodeValue;
        document.getElementById("res").textContent = responsable;
        document.getElementById("fun").textContent = funcion;
        document.getElementById("sec").textContent = secretaria;
        document.getElementById("pwd").textContent = password;
      }
    }
}

function verBoleta() {
    var usuario = this.form1.usuarios.value
    var url = "../verBoleta?usuario="+ usuario;
    initRequest(url);
    req.onreadystatechange = processRequestVerBoleta;
    req.open("GET", url, true); 
    req.send(null);
}

function processRequestVerBoleta() {
    //"<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
    //"<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha rendicion</td><td align='center'>Beneficiario</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Confirmar</td></tr>"
    //"<tr><td align='center'>"+boleta.getNumero()+"</td><td align='center'>"+boleta.getEstadoboleta()+"</td><td align='center'>"+boleta.getFecharendicion()+"</td><td align='center'>"+boleta.getBeneficiario()+"</td><td align='center'>"+boleta.getMonto()+"</td><td></td><td align='center'><input id='"+boleta.getNumero()+"' name='"+boleta.getNumero()+"' type='checkbox' value='' /></td></tr>"
    //"<tr><td align='center'>"+boleta.getNumero()+"</td><td align='center'>"+boleta.getEstadoboleta()+"</td><td></td><td></td><td></td><td align='center'>"+boleta.getMotivo()+"</td><td align='center'><input name='"+boleta.getNumero()+"' id='"+boleta.getNumero()+"' type='checkbox' value='' /></td></tr>"
    //"</table>"
    if (req.readyState == 4) {
      if (req.status == 200) {
          var estado = req.responseXML.getElementsByTagName("estado")[0].childNodes[0].nodeValue;
          if(estado == 'vacio'){
              var div = document.getElementById("boletas");
              div.innerHTML = "<table><tr><tdalign='center'>No hay boletas por confirmar</td></tr></table>";    
          }else{
              var cantidad = req.responseXML.getElementsByTagName("cantidad");
              var div = document.getElementById("boletas");
              var datos = "<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
              datos = datos + "<tr><td align='center'>Numero</td><td align='center'>Estado</td><td align='center'>Fecha rendicion</td><td align='center'>Beneficiario</td><td align='center'>Monto</td><td align='center'>Motivo</td><td align='center'>Confirmar</td></tr>";
              for(var i=0 ; i < cantidad ; i++){
                  var boleta = req.responseXML.getElementsByTagName("boleta"+(i+1));
                  var beneficiario = boleta.childNodes[0].nodeValue;
                  var estadoboleta = boleta.childNodes[1].nodeValue;
                  var fecharendicion = boleta.childNodes[2].nodeValue;
                  var motivo = boleta.childNodes[3].nodeValue;
                  var monto = boleta.childNodes[4].nodeValue;
                  var numero = boleta.childNodes[5].nodeValue;
                  if(motivo == ''){
                      datos = datos + "<tr><td align='center'>"+numero+"</td><td align='center'>"+estadoboleta+"</td><td align='center'>"+fecharendicion+"</td><td align='center'>"+beneficiario+"</td><td align='center'>"+monto+"</td><td></td><td align='center'><input id='"+numero+"' name='"+numero+"' type='checkbox' value='' /></td></tr>";
                  }else{
                      datos = datos + "<tr><td align='center'>"+numero+"</td><td align='center'>"+estadoboleta+"</td><td></td><td></td><td></td><td align='center'>"+motivo+"</td><td align='center'><input name='"+numero+"' id='"+numero+"' type='checkbox' value='' /></td></tr>";
                  }
              }
              datos = datos + "</table>"
          }
          alert("Response2");
          
      }
    }
}



function initRequest(url) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
}
            
//Este metodo obtiene el valor de un text field y lo manda al servidor para validar
//si es un usuario valido
function validateUserId() {
    if (!target) target = document.getElementById("userid");
    var url = "validate?id=" + escape(target.value); 

    // Invoke initRequest(url) to create XMLHttpRequest object
    initRequest(url);

    // The "processRequest" function is set as a callback function.
    // (Please note that, in JavaScript, functions are first-class objects: they
    // can be passed around as objects.  This is different from the way
    // methods are treated in Java programming language.)
    req.onreadystatechange = processRequest;
    req.open("GET", url, true); 
    req.send(null);
}       

function processRequest() {
    if (req.readyState == 4) {
      if (req.status == 200) {

        // Extract "true" or "false" from the returned data from the server.
        // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
        var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;

        // Call "setMessageUsingDOM(message)" function to display
        // "Valid User Id" or "Invalid User Id" message.
        setMessageUsingDOM(message);

        // If the user entered value is not valid, do not allow the user to
        // click submit button.
        var submitBtn = document.getElementById("submit_btn");
        if (message == "false") {
            submitBtn.disabled = true;
        } else {
            submitBtn.disabled = false;
        }
      }
    }
}

function setMessageUsingDOM(message) {
    var userMessageElement = document.getElementById("userIdMessage");
    var messageText;
    if (message == "false") {
        userMessageElement.style.color = "red";
        messageText = "Invalid User Id";
    } else {
        userMessageElement.style.color = "green";
        messageText = "Valid User Id";
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
    var submitBtn = document.getElementById("submit_btn");
    submitBtn.disabled = true;
}