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