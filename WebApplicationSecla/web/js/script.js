function retornarFecha()
{
    //--------------- LOCALIZEABLE GLOBALS ---------------
    var d=new Date();
    var monthname=new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre",	"Diciembre");
    //Ensure correct for language. English is "January 1, 2004"
    var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
    //---------------   END LOCALIZEABLE   ---------------
    return TODAY;
}


function iSubmitEnter(oEvento, oFormulario){ 
    if (oEvento && oEvento.which == 13)
        oFormulario.submit();
    else
        return true;
}

String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
    return this.replace(/^\s+/g,"");
}
String.prototype.rtrim = function() {
    return this.replace(/\s+$/g,"");
}

function valirdarGenerarReportes(){
    var aux = 0;
    if (document.form1.recibo[0].checked || document.form1.recibo[1].checked || document.form1.recibo[2].checked){
        document.getElementById('recibo1').style.visibility = 'hidden';
    }else{
        document.getElementById('recibo1').innerHTML = "Debe seleccionar un tipo";
        document.getElementById('recibo1').style.visibility = 'visible';
        aux++;
    }
    var sector = document.form1.sectores.selectedIndex; 
    var responsable = document.form1.responsables.selectedIndex; 
    if(sector == -1){
        document.getElementById('sec1').innerHTML = "Debe seleccionar un sector";
        document.getElementById('sec1').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('sec1').style.visibility = 'hidden';
    }
    if(responsable == -1){
        document.getElementById('res1').innerHTML = "Debe seleccionar un responsable";
        document.getElementById('res1').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('res1').style.visibility = 'hidden';
    }
    if((sector == 0)&&(responsable == 0)){
        document.getElementById('res2').innerHTML = "Debe seleccionar un responsable o un sector";
        document.getElementById('res2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('res2').style.visibility = 'hidden';
    }
    var fecha1 = document.form1.fecha1.value.length;
    var fecha2 = document.form1.fecha2.value.length;
    if(fecha1==0){
        document.getElementById('fe1').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fe1').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('fe1').style.visibility = 'hidden';
    }
    if(fecha2==0){
        document.getElementById('fe2').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fe2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('fe2').style.visibility = 'hidden';
    }
    if((fecha1!=0)&&(fecha2!=0)){
        fecha1 = document.form1.fecha1.value;
        fecha2 = document.form1.fecha2.value;
        fecha1 = fecha1.split("/");
        fecha2 = fecha2.split("/");
        fecha1 = new Date(fecha1[2], fecha1[1]-1, fecha1[0]);
        fecha2 = new Date(fecha2[2], fecha2[1]-1, fecha2[0]);
        if(fecha1 > fecha2){
            document.getElementById('fe2').innerHTML = "Error en el rango de las fechas";
            document.getElementById('fe2').style.visibility = 'visible';
            aux++;
        }
    }
    if(aux == 0){
        document.form1.submit();
    }
}


function validarEntregarRecibo(){
    var numMin = document.form1.numMin.value.length
    var numMax = document.form1.numMax.value.length
    var operador = document.form1.listaOperadores.selectedIndex
    var aux = 0
    if(operador == -1){
        document.getElementById('menu').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('menu').style.visibility = 'hidden';
    }
    if(numMin == 0){
        document.getElementById('rango').innerHTML = "Debe completar el rango de las recibos";
        document.getElementById('rango').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rango').style.visibility = 'hidden';
    }
    if(numMax == 0){
        document.getElementById('rango').innerHTML = "Debe completar el rango de las recibos";
        document.getElementById('rango').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rango').style.visibility = 'hidden';
    }
    if(aux == 0 ){
        numMin = document.form1.numMin.value
        numMax = document.form1.numMax.value
        if((IsReal(numMin) == 0) && (IsReal(numMax) == 0)){
            if(numMax >= numMin){
                document.form1.submit();
            }else{
                document.getElementById('rango').innerHTML = "Error en el rango";
                document.getElementById('rango').style.visibility = 'visible';
            }
        }else{
            document.getElementById('rango').style.visibility = 'visible';
            document.form1.numMin.value = ''
            document.form1.numMax.value = ''
        }	
    }
}

function IsReal(YourNumber)
{
    var Template = /^(([+|-]?d+(.d*)?)|([+|-]?(d*.)?d+))$/ //Formato de numero real con signo
    return (Template.test(YourNumber)) ? 1 : 0 //Compara "YourNumber" con el formato "Template" y si coincidevuelve verdadero si no devuelve falso
}


function validarResetPwd(){
    var password1 = document.form1.password1.value.length
    var password2 = document.form1.password2.value.length
    var usuario   = document.form1.listaUsuarios.selectedIndex
    var aux = 0;
    
    if(usuario == -1 ){
        document.getElementById('menu').innerHTML = "Debe seleccionar un campo";
        document.getElementById('menu').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('menu').style.visibility = 'hidden';
    }
    if(password1 == 0){
        document.getElementById('pwd1').innerHTML = "Debe completar el campo";
        document.getElementById('pwd1').style.visibility = 'visible';
        aux++;
    }else{
        if(password1 < 4){
            document.getElementById('pwd1').innerHTML = "Minimo 4 caracteres";
            document.getElementById('pwd1').style.visibility = 'visible';
            aux++;
        }else{
            password1 = document.form1.password1.value;
            if(vacio(password1)){
                document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco";
                document.getElementById('pwd1').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                aux++;
            }else{
                document.getElementById('pwd1').style.visibility = 'hidden';
            }
        }
    }
    if(password2 == 0){
        document.getElementById('pwd2').innerHTML = "Debe completar el campo";
        document.getElementById('pwd2').style.visibility = 'visible';
        aux++;
    }else{
        if(password2 < 4){
            document.getElementById('pwd2').innerHTML = "Minimo 4 caracteres";
            document.getElementById('pwd2').style.visibility = 'visible';
            aux++;   
        }else{
            password2 = document.form1.password2.value;
            if(vacio(password2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco";
                document.getElementById('pwd2').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                aux++;
            }else{
                document.getElementById('pwd2').style.visibility = 'hidden';
            }
        }
    }
    if(aux == 0 ){
        if (document.form1.password2.value == document.form1.password1.value){
            document.form1.submit();
        }else{
            document.getElementById('pwds').style.visibility = 'visible';
            document.form1.password1.value = ''
            document.form1.password2.value = ''
        }	
    }
}

function confirmarRecibos(){
    var aux1 = document.getElementById('validar').value;
    var aux2 = document.getElementById('validar2').value;
    aux2 = parseInt(aux1) + parseInt(aux2);
    for(aux1;aux1<=aux2;aux1++){
        if(document.getElementById(aux1) != null){
            if(document.getElementById(aux1).checked){
                document.getElementById(aux1).value = "checked";
            }else{
                document.getElementById(aux1).value = "notchecked";
            }	
        }
    }
    document.form1.submit();
}

function valirdarVerRecibosEntregados()
{
    var numMin = document.form1.numMin.value.length
    var numMax = document.form1.numMax.value.length
    
    var aux = 0
    if(numMin == 0){
        document.getElementById('rangob').innerHTML = "Debe completar el rango de las recibos";
        document.getElementById('rangob').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rangob').style.visibility = 'hidden';
    }
    if(numMax == 0){
        document.getElementById('rangob').innerHTML = "Debe completar el rango de las recibos";
        document.getElementById('rangob').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rangob').style.visibility = 'hidden';
    }
    var fecha1 = document.form1.fecha1.value.length;
    var fecha2 = document.form1.fecha2.value.length;
    if(fecha1==0){
        document.getElementById('rangof').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('rangof').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rangof').style.visibility = 'hidden';
    }
    if(fecha2==0){
        document.getElementById('rangof').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('rangof').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('rangof').style.visibility = 'hidden';
    }
    if((fecha1!=0)&&(fecha2!=0)){
        fecha1 = document.form1.fecha1.value;
        fecha2 = document.form1.fecha2.value;
        fecha1 = fecha1.split("/");
        fecha2 = fecha2.split("/");
        fecha1 = new Date(fecha1[2], fecha1[1]-1, fecha1[0]);
        fecha2 = new Date(fecha2[2], fecha2[1]-1, fecha2[0]);
        if(fecha1 > fecha2){
            document.getElementById('rangof').innerHTML = "Error en el rango de las fechas";
            document.getElementById('rangof').style.visibility = 'visible';
            aux++;
        }
    }
    numMin = parseInt(document.form1.numMin.value);
    numMax = parseInt(document.form1.numMax.value);
    if((IsReal(numMin) == 0) && (IsReal(numMax) == 0)){
        if(numMax < numMin){
            document.getElementById('rangob').innerHTML = "Error en el rango";
            document.getElementById('rangob').style.visibility = 'visible';
        }
    }else{
        document.getElementById('rangob').style.visibility = 'visible';
        document.form1.numMin.value = ''
        document.form1.numMax.value = ''
        aux++;
    }
    if(aux == 0 ){
        document.form1.submit();
    }
}

function validarModificarReciboOperador(){
     var recibo = document.form1.recibos.selectedIndex;
    var monto = document.form1.monto.value.length;
    var beneficiario = document.form1.beneficiario.value.length;
    var numerocuota1 = document.form1.numerocuota1.value.length;
    var numerocuota2 = document.form1.numerocuota2.value.length;
    var fecha = document.form1.date.value.length;
    var aux=0;
    //Chequeo numero de recibo
    if(recibo == -1 ){
        document.getElementById('recibo').innerHTML = "Debe seleccionar un campo";
        document.getElementById('recibo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('recibo').style.visibility = 'hidden';
    }
    //Chequeo fecha de confeccion
    if(fecha==0){
        document.getElementById('fecha2').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fecha2').style.visibility = 'visible';
        aux++;
    }else{
        var fecha1 = document.form1.date.value;
        fecha1 = fecha1.split("/");
        fecha1 = new Date(fecha1[2], fecha1[1]-1, fecha1[0]);
        var fecha2 = new Date();
        if(fecha1 > fecha2){
            document.getElementById('fecha2').innerHTML = "No se puede asignar una fecha adelantada";
            document.getElementById('fecha2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('fecha2').style.visibility = 'hidden';
        }
    }
    //Chequeo razon social
    if(beneficiario==0){
        document.getElementById('beneficiario2').innerHTML = "Debe completar el campo";
        document.getElementById('beneficiario2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('beneficiario2').style.visibility = 'hidden';
    }
    //Chequeo numero de quota
    if((numerocuota1==0)||(numerocuota2==0)){
        document.getElementById('ncuota').innerHTML = "Debe completar el campo";
        document.getElementById('ncuota').style.visibility = 'visible';
        aux++;
    }else{
        numerocuota1 = parseInt(document.form1.numerocuota1.value);
        numerocuota2 = parseInt(document.form1.numerocuota2.value);
        if(numerocuota1 > numerocuota2){
            document.getElementById('ncuota').innerHTML = "Error en el rango de la cuota";
            document.getElementById('ncuota').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('ncuota').style.visibility = 'hidden';
        }
    }
    //Chequeo por tipo de pago
    if(document.form1.banco == null){
        //Chequeo si es efectivo
        //Chequeo monto
        if(monto==0){
            document.getElementById('monto2').innerHTML = "Debe completar el campo";
            document.getElementById('monto2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('monto2').style.visibility = 'hidden';
        }
    }else{
        //Chequeo si es cheque
        //Chequeo fecha de vencimiento del cheque
        var fecha3 = document.form1.date2.value.length;
        if(fecha3==0){
            document.getElementById('fecha4').innerHTML = "Debe seleccionar una fecha";
            document.getElementById('fecha4').style.visibility = 'visible';
            aux++;
        }else{
            var fecha4 = document.form1.date2.value;
            fecha4 = fecha4.split("/");
            fecha4 = new Date(fecha4[2], fecha4[1]-1, fecha4[0]);
            var fecha5 = new Date();
            if(fecha4 < fecha5){
                document.getElementById('fecha4').innerHTML = "No se puede asignar una fecha pasada";
                document.getElementById('fecha4').style.visibility = 'visible';
                aux++;
            }else{
                document.getElementById('fecha4').style.visibility = 'hidden';
            }
        }
        //Chequeo nombre del banco
        var banco = document.form1.banco.value.length;
        if(banco==0){
            document.getElementById('banco2').innerHTML = "Debe completar el campo";
            document.getElementById('banco2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('banco2').style.visibility = 'hidden';
        }
        //Chequeo numero de cheque
        var numerocheque = document.form1.numerocheque.value.length;
        if(numerocheque==0){
            document.getElementById('ncheque').innerHTML = "Debe completar el campo";
            document.getElementById('ncheque').style.visibility = 'visible';
            aux++;
        }else{
            numerocheque = document.form1.numerocheque.value;
            if((/^[0-9]*$/.test(numerocheque))){
                document.getElementById('ncheque').style.visibility = 'hidden';
            }else{
                document.getElementById('ncheque').innerHTML = "Debe ser un numero";
                document.getElementById('ncheque').style.visibility = 'visible';
                aux++;
            }
        }
        //Chequeo monto
        if(monto==0){
            document.getElementById('monto2').innerHTML = "Debe completar el campo";
            document.getElementById('monto2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('monto2').style.visibility = 'hidden';
        }
    }
    if(aux == 0 ){
        var valor = document.form1.monto.value;
        valor = valor.split(".").join(",");
        if(/^[0-9]+(,[0-9]+)*$/.test(valor)){
            document.form1.submit();
        }else{
            document.getElementById('monto2').innerHTML = "Debe ser un mumero";
            document.getElementById('monto2').style.visibility = 'visible';
            document.form1.monto.value = ''
        }
    }
}


function validarCompletarReciboOperador()
{
    var recibo = document.form1.recibos.selectedIndex;
    var monto = document.form1.monto.value.length;
    var beneficiario = document.form1.beneficiario.value.length;
    var numerocuota1 = document.form1.numerocuota1.value.length;
    var numerocuota2 = document.form1.numerocuota2.value.length;
    var fecha = document.form1.date.value.length;
    var aux=0;
    //Chequeo numero de recibo
    if(recibo == -1 ){
        document.getElementById('recibo').innerHTML = "Debe seleccionar un campo";
        document.getElementById('recibo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('recibo').style.visibility = 'hidden';
    }
    //Chequeo fecha de confeccion
    if(fecha==0){
        document.getElementById('fecha2').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fecha2').style.visibility = 'visible';
        aux++;
    }else{
        var fecha1 = document.form1.date.value;
        fecha1 = fecha1.split("/");
        fecha1 = new Date(fecha1[2], fecha1[1]-1, fecha1[0]);
        var fecha2 = new Date();
        if(fecha1 > fecha2){
            document.getElementById('fecha2').innerHTML = "No se puede asignar una fecha adelantada";
            document.getElementById('fecha2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('fecha2').style.visibility = 'hidden';
        }
    }
    //Chequeo razon social
    if(beneficiario==0){
        document.getElementById('beneficiario2').innerHTML = "Debe completar el campo";
        document.getElementById('beneficiario2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('beneficiario2').style.visibility = 'hidden';
    }
    //Chequeo numero de quota
    if((numerocuota1==0)||(numerocuota2==0)){
        document.getElementById('ncuota').innerHTML = "Debe completar el campo";
        document.getElementById('ncuota').style.visibility = 'visible';
        aux++;
    }else{
        numerocuota1 = parseInt(document.form1.numerocuota1.value);
        numerocuota2 = parseInt(document.form1.numerocuota2.value);
        if(numerocuota1 > numerocuota2){
            document.getElementById('ncuota').innerHTML = "Error en el rango de la cuota";
            document.getElementById('ncuota').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('ncuota').style.visibility = 'hidden';
        }
    }
    //Chequeo por tipo de pago
    if(document.form1.tipopago[0].checked){
        //Chequeo si es efectivo
        //Chequeo monto
        if(monto==0){
            document.getElementById('monto2').innerHTML = "Debe completar el campo";
            document.getElementById('monto2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('monto2').style.visibility = 'hidden';
        }
    }else{
        //Chequeo si es cheque
        //Chequeo fecha de vencimiento del cheque
        var fecha3 = document.form1.date2.value.length;
        if(fecha3==0){
            document.getElementById('fecha4').innerHTML = "Debe seleccionar una fecha";
            document.getElementById('fecha4').style.visibility = 'visible';
            aux++;
        }else{
            var fecha4 = document.form1.date2.value;
            fecha4 = fecha4.split("/");
            fecha4 = new Date(fecha4[2], fecha4[1]-1, fecha4[0]);
            var fecha5 = new Date();
            if(fecha4 < fecha5){
                document.getElementById('fecha4').innerHTML = "No se puede asignar una fecha pasada";
                document.getElementById('fecha4').style.visibility = 'visible';
                aux++;
            }else{
                document.getElementById('fecha4').style.visibility = 'hidden';
            }
        }
        //Chequeo nombre del banco
        var banco = document.form1.banco.value.length;
        if(banco==0){
            document.getElementById('banco2').innerHTML = "Debe completar el campo";
            document.getElementById('banco2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('banco2').style.visibility = 'hidden';
        }
        //Chequeo numero de cheque
        var numerocheque = document.form1.numerocheque.value.length;
        if(numerocheque==0){
            document.getElementById('ncheque').innerHTML = "Debe completar el campo";
            document.getElementById('ncheque').style.visibility = 'visible';
            aux++;
        }else{
            numerocheque = document.form1.numerocheque.value;
            if((/^[0-9]*$/.test(numerocheque))){
                document.getElementById('ncheque').style.visibility = 'hidden';
            }else{
                document.getElementById('ncheque').innerHTML = "Debe ser un numero";
                document.getElementById('ncheque').style.visibility = 'visible';
                aux++;
            }
        }
        //Chequeo monto
        if(monto==0){
            document.getElementById('monto2').innerHTML = "Debe completar el campo";
            document.getElementById('monto2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('monto2').style.visibility = 'hidden';
        }
    }
    if(aux == 0 ){
        var valor = document.form1.monto.value;
        valor = valor.split(".").join(",");
        if(/^[0-9]+(,[0-9]+)*$/.test(valor)){
            document.form1.submit();
        }else{ 
            document.getElementById('monto2').innerHTML = "Debe ser un mumero";
            document.getElementById('monto2').style.visibility = 'visible';
            document.form1.monto.value = ''
        } 
    }
}



function validarAnularRecibo(){
    var motivo = document.form1.motivo.value.length;
    var recibo = document.form1.recibos.selectedIndex;
    var fecha = document.form1.date.value.length;
    var aux=0;
    if(recibo == -1 ){
        document.getElementById('recibo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('recibo').style.visibility = 'hidden';
    }
    if(motivo==0){
        document.getElementById('motivo1').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('motivo1').style.visibility = 'hidden';
    }
    if(fecha==0){
        document.getElementById('fecha2').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fecha2').style.visibility = 'visible';
        aux++;
    }else{
        var fecha1 = document.form1.date.value;
        fecha1 = fecha1.split("/");
        fecha1 = new Date(fecha1[2], fecha1[1]-1, fecha1[0]);
        var fecha2 = new Date();
        if(fecha1 > fecha2){
            document.getElementById('fecha2').innerHTML = "No se puede asignar una fecha adelantada";
            document.getElementById('fecha2').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('fecha2').style.visibility = 'hidden';
        }
    }
    if(aux == 0 ){
        document.form1.submit();
    }
}

function validarCambioContrasenia()
{
    var pwd1 = document.form1.password1.value.length;
    var pwd2 = document.form1.password2.value.length;
    var pwd3 = document.form1.password3.value.length;
    var aux=0;
    
    if(pwd1==0){
        document.getElementById('pwd1').innerHTML = "Debe completar el campo";
        document.getElementById('pwd1').style.visibility = 'visible';
        aux++;
    }else{
        if(pwd1 < 4){
            document.getElementById('pwd1').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('pwd1').style.visibility = 'visible';
            aux++;	
        }else{
            pwd1 = document.form1.password1.value;
            if(vacio(pwd1)){
                document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd1').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                document.form1.password3.value = ''
                aux++; 
            }else{
                document.getElementById('pwd1').style.visibility = 'hidden';
            }
        }
    }
    if(pwd2==0){
        document.getElementById('pwd2').innerHTML = "Debe completar el campo";
        document.getElementById('pwd2').style.visibility = 'visible';
        aux++;
    }else{
        if(pwd2 < 4){
            document.getElementById('pwd2').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('pwd2').style.visibility = 'visible';
            aux++;	
        }else{
            pwd2 = document.form1.password2.value;
            if(vacio(pwd2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd2').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                document.form1.password3.value = ''
                aux++; 
            }else{
                document.getElementById('pwd2').style.visibility = 'hidden';
            }
        }
    }
    if(pwd3==0){
        document.getElementById('pwd3').innerHTML = "Debe completar el campo";
        document.getElementById('pwd3').style.visibility = 'visible';
        aux++;
    }else{
        if(pwd3 < 4){
            document.getElementById('pwd3').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('pwd3').style.visibility = 'visible';
            aux++;	
        }else{
            pwd3 = document.form1.password3.value;
            if(vacio(pwd3)){
                document.getElementById('pwd3').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd3').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                document.form1.password3.value = ''
                aux++; 
            }else{
                document.getElementById('pwd3').style.visibility = 'hidden';
            }
        }
    }
    if(aux == 0 ){
        if(document.form1.password1.value == document.form1.password4.value){
            document.getElementById('pwd1').style.visibility = 'hidden';
            if (document.form1.password2.value == document.form1.password3.value){
                document.form1.submit();
            }else{
                document.getElementById('pwds').style.visibility = 'visible';
                document.form1.password2.value = ''
                document.form1.password3.value = ''
            }	
        }else{
            document.getElementById('pwd1').innerHTML = "Password actual incorrecto";
            document.getElementById('pwd1').style.visibility = 'visible';
            document.form1.password2.value = ''
            document.form1.password3.value = ''
            document.form1.password1.value = ''
        }
    }
	
}

function vacio(q) {
    for ( i = 0; i < q.length; i++ ) {
        if ( q.charAt(i) == " " ) {
            return true
        }
    }
    return false
}

function validarModArea(){
    var responsable = document.form1.responsable.value.length
    var sede = document.form1.sede.value.length
    var sector = document.form1.sector.value.length
    var digarea = document.form1.digarea.value.length
    var digresp = document.form1.digresp.value.length
    var aux = 0;
    if(responsable==0){
        document.getElementById('resp').innerHTML = "Debe completar el campo";
        document.getElementById('resp').style.visibility = 'visible';
        aux++;
    }else{
        if(responsable < 3){
            document.getElementById('resp').innerHTML = "Minimo 3 caracteres";
            document.getElementById('resp').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('resp').style.visibility = 'hidden';
        }
    }
    if(sede == 0){
        document.getElementById('sed').innerHTML = "Debe completar el campo";
        document.getElementById('sed').style.visibility = 'visible';
        aux++;
    }else{
        if(sede < 3){
            document.getElementById('sed').innerHTML = "Minimo 3 caracteres";
            document.getElementById('sed').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('sed').style.visibility = 'hidden';
        }
    }
    if(sector == 0){
        document.getElementById('sec').innerHTML = "Debe completar el campo";
        document.getElementById('sec').style.visibility = 'visible';
        aux++;
    }else{
        if(sector < 3){
            document.getElementById('sec').innerHTML = "Minimo 3 caracteres";
            document.getElementById('sec').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('sec').style.visibility = 'hidden';
        }
    }
    if(digarea == 0){
        document.getElementById('dig1').innerHTML = "Debe completar el campo";
        document.getElementById('dig1').style.visibility = 'visible';
        aux++;
    }else{
        if(digarea != 2){
            document.getElementById('dig1').innerHTML = "Debe contener 2 caracteres";
            document.getElementById('dig1').style.visibility = 'visible';
            document.form1.digarea.value = ''
            aux++;
        }else{
            document.getElementById('dig1').style.visibility = 'hidden';
        }
    }
    if(digresp == 0){
        document.getElementById('dig2').innerHTML = "Debe completar el campo";
        document.getElementById('dig2').style.visibility = 'visible';
        aux++;
    }else{
        if(digresp != 2){
            document.getElementById('dig2').innerHTML = "Debe contener 2 caracteres";
            document.getElementById('dig2').style.visibility = 'visible';
            document.form1.digresp.value = ''
            aux++;
        }else{
            document.getElementById('dig2').style.visibility = 'hidden';
        }
    }
    if(aux == 0 ){
        document.form1.submit();
    }
}

function validarAltaArea(){
    var responsable = document.form1.responsable.value.length
    var sede = document.form1.sede.value.length
    var sector = document.form1.sector.value.length
    var digarea = document.form1.digarea.value.length
    var digresp = document.form1.digresp.value.length
    var usuario = document.form1.usuario.value.length
    var password1 = document.form1.password1.value.length
    var password2 = document.form1.password2.value.length
    var aux = 0
    if(responsable==0){  
        document.getElementById('resp').innerHTML = "Debe completar el campo";
        document.getElementById('resp').style.visibility = 'visible';
        aux++;
    }else{
        if(responsable < 3){
            document.getElementById('resp').innerHTML = "Minimo 3 caracteres";
            document.getElementById('resp').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('resp').style.visibility = 'hidden';		
        }
    }
    if(sede == 0){
        document.getElementById('sed').innerHTML = "Debe completar el campo";
        document.getElementById('sed').style.visibility = 'visible';
        aux++;
    }else{
        if(sede < 3){
            document.getElementById('sed').innerHTML = "Minimo 3 caracteres"; 
            document.getElementById('sed').style.visibility = 'visible';	
            aux++;
        }else{
            document.getElementById('sed').style.visibility = 'hidden';	
        }
    }
    if(sector == 0){
        document.getElementById('sec').innerHTML = "Debe completar el campo";
        document.getElementById('sec').style.visibility = 'visible';
        aux++;
    }else{
        if(sector < 3){
            document.getElementById('sec').innerHTML = "Minimo 3 caracteres";  
            document.getElementById('sec').style.visibility = 'visible';
            aux++;
        }else{
            document.getElementById('sec').style.visibility = 'hidden';
        }
    }
    if(digarea == 0){
        document.getElementById('dig1').innerHTML = "Debe completar el campo";
        document.getElementById('dig1').style.visibility = 'visible';
        aux++;
    }else{
        if(digarea != 2){
            document.getElementById('dig1').innerHTML = "Debe contener 2 caracteres";  
            document.getElementById('dig1').style.visibility = 'visible';
            document.form1.digarea.value = ''
            aux++;
        }else{
            document.getElementById('dig1').style.visibility = 'hidden';
        }
    }
    if(digresp == 0){
        document.getElementById('dig2').innerHTML = "Debe completar el campo";
        document.getElementById('dig2').style.visibility = 'visible';
        aux++;
    }else{
        if(digresp != 2){
            document.getElementById('dig2').innerHTML = "Debe contener 2 caracteres";  
            document.getElementById('dig2').style.visibility = 'visible';
            document.form1.digresp.value = ''
            aux++;
        }else{
            document.getElementById('dig2').style.visibility = 'hidden';
        }
    }
    if(usuario == 0){
        document.getElementById('usu').innerHTML = "Debe completar el campo";
        document.getElementById('usu').style.visibility = 'visible';
        aux++;
    }else{
        if(usuario < 4){
            document.getElementById('usu').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('usu').style.visibility = 'visible';	
            aux++;
        }else{
            usuario = document.form1.usuario.value
            if(vacio(usuario)){
                document.getElementById('usu').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('usu').style.visibility = 'visible';	
                aux++;
            }else{
                document.getElementById('usu').style.visibility = 'hidden';
            }
        }
    }
    if(password1 == 0){
        document.getElementById('pwd1').innerHTML = "Debe completar el campo";
        document.getElementById('pwd1').style.visibility = 'visible';
        aux++;
    }else{
        if(password1 < 4){
            document.getElementById('pwd1').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('pwd1').style.visibility = 'visible';
            aux++;	
        }else{
            password1 = document.form1.password1.value;
            if(vacio(password1)){
                document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco";
                document.getElementById('pwd1').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                aux++; 
            }else{
                document.getElementById('pwd1').style.visibility = 'hidden';
            }
        }
    }
    if(password2 == 0){
        document.getElementById('pwd2').innerHTML = "Debe completar el campo";
        document.getElementById('pwd2').style.visibility = 'visible';
        aux++;
    }else{
        if(password2 < 4){
            document.getElementById('pwd2').innerHTML = "Minimo 4 caracteres"; 
            document.getElementById('pwd2').style.visibility = 'visible';
            aux++;
        }else{
            password2 = document.form1.password2.value;
            if(vacio(password2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd2').style.visibility = 'visible';
                document.form1.password1.value = ''
                document.form1.password2.value = ''
                aux++;
            }else{
                document.getElementById('pwd2').style.visibility = 'hidden';	
            }
        }
    }
    if(aux == 0 ){
        if (document.form1.password2.value == document.form1.password1.value){
            document.form1.submit();
        }else{
            document.getElementById('pwds').style.visibility = 'visible';
            document.form1.password1.value = ''
            document.form1.password2.value = ''
        }	
    }
}


function confirmarBorrarArea()
{
    var usuario = document.form1.listaUsuarios.options [document.form1.listaUsuarios.selectedIndex].value;
    var respuesta=confirm("Esta seguro que desea eliminar a "+usuario+" ?");
    if (respuesta==true)
        document.form1.submit();
}


