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

function generarReporte(){
    if(document.getElementsByName('html').checked){
        
    }else{
        
    }
}


function validarEntregarRecibo(){
    var numMin = this.form1.numMin.value.length
    var numMax = this.form1.numMax.value.length
    var operador = this.form1.listaOperadores.selectedIndex
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
        numMin = this.form1.numMin.value
        numMax = this.form1.numMax.value
        if((IsReal(numMin) == 0) && (IsReal(numMax) == 0)){
            if(numMax >= numMin){
              this.form1.submit(); 
            }else{
                document.getElementById('rango').innerHTML = "Error en el rango";
                document.getElementById('rango').style.visibility = 'visible';
            }
        }else{
            document.getElementById('rango').style.visibility = 'visible';
            this.form1.numMin.value = ''
            this.form1.numMax.value = ''
        }	
    }
}

function IsReal(YourNumber)
{
    var Template = /^(([+|-]?d+(.d*)?)|([+|-]?(d*.)?d+))$/ //Formato de numero real con signo
    return (Template.test(YourNumber)) ? 1 : 0 //Compara "YourNumber" con el formato "Template" y si coincidevuelve verdadero si no devuelve falso
}


function validarResetPwd(){
    
    var password1 = this.form1.password1.value.length
    var password2 = this.form1.password2.value.length
    var usuario   = this.form1.listaUsuarios.selectedIndex
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
            password1 = this.form1.password1.value;
            if(vacio(password1)){
                document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco";
                document.getElementById('pwd1').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = ''
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
            password2 = this.form1.password2.value;
            if(vacio(password2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco";
                document.getElementById('pwd2').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = ''
                aux++;
            }else{
                document.getElementById('pwd2').style.visibility = 'hidden';
            }
        }
    }
    if(aux == 0 ){
        if (this.form1.password2.value == this.form1.password1.value){
            this.form1.submit(); 	
        }else{
            document.getElementById('pwds').style.visibility = 'visible';
            this.form1.password1.value = ''
            this.form1.password2.value = ''
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
    this.form1.submit();
}



function validarCompletarRecibo()
{
    var recibo = this.form1.recibos.selectedIndex;
    var monto = this.form1.monto.value.length;
    var beneficiario = this.form1.beneficiario.value.length;
    var fecha = this.form1.date.value.length;
    var aux=0;
    if(recibo == -1 ){
        document.getElementById('recibo').innerHTML = "Debe seleccionar un campo";
        document.getElementById('recibo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('recibo').style.visibility = 'hidden';
    }
    if(monto==0){
        document.getElementById('monto2').innerHTML = "Debe completar el campo";
        document.getElementById('monto2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('monto2').style.visibility = 'hidden';
    }
    if(fecha==0){
        document.getElementById('fecha2').innerHTML = "Debe seleccionar una fecha";
        document.getElementById('fecha2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('fecha2').style.visibility = 'hidden';
    }
    if(beneficiario==0){
        document.getElementById('beneficiario2').innerHTML = "Debe completar el campo";
        document.getElementById('beneficiario2').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('beneficiario2').style.visibility = 'hidden';
    }
    if(aux == 0 ){
        var valor = this.form1.monto.value;
        valor = valor.split(".").join(",");
        if(/^[0-9]+(,[0-9]+)*$/.test(valor)){
            this.form1.submit();  
        }else{ 
            document.getElementById('monto2').innerHTML = "Debe ser un mumero";
            document.getElementById('monto2').style.visibility = 'visible';
            this.form1.monto.value = ''  
                    	
        } 
    }
}



function validarAnularRecibo(){
    var motivo = this.form1.motivo.value.length;
    var recibo = this.form1.recibos.selectedIndex;
    var aux=0;
	
    if(recibo == -1 ){
        document.getElementById('recibo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('recibo').style.visibility = 'hidden';
    }
    if(motivo==0){
        document.getElementById('motivo').style.visibility = 'visible';
        aux++;
    }else{
        document.getElementById('motivo').style.visibility = 'hidden';
    }
    if(aux == 0 ){
        this.form1.submit(); 	
    }
}

function validarCambioContrasenia()
{
    var pwd1 = this.form1.password1.value.length;
    var pwd2 = this.form1.password2.value.length;
    var pwd3 = this.form1.password3.value.length;
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
            pwd1 = this.form1.password1.value;
            if(vacio(pwd1)){
                document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd1').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = '' 
                this.form1.password3.value = '' 
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
            pwd2 = this.form1.password2.value;
            if(vacio(pwd2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd2').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = '' 
                this.form1.password3.value = '' 
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
            pwd3 = this.form1.password3.value;
            if(vacio(pwd3)){
                document.getElementById('pwd3').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd3').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = '' 
                this.form1.password3.value = '' 
                aux++; 
            }else{
              document.getElementById('pwd3').style.visibility = 'hidden';
            }
        }
    }
    if(aux == 0 ){
        if(this.form1.password1.value == this.form1.password4.value){
            document.getElementById('pwd1').style.visibility = 'hidden';
            if (this.form1.password2.value == this.form1.password3.value){
                this.form1.submit(); 	
            }else{
                document.getElementById('pwds').style.visibility = 'visible';
                this.form1.password2.value = ''
                this.form1.password3.value = ''
            }	
        }else{
            document.getElementById('pwd1').innerHTML = "Password actual incorrecto";
            document.getElementById('pwd1').style.visibility = 'visible';
            this.form1.password2.value = ''
            this.form1.password3.value = ''
            this.form1.password1.value = ''
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

function validarAltaArea(){
    var responsable = this.form1.responsable.value.length
    var sede = this.form1.sede.value.length
    var sector = this.form1.sector.value.length
    var digarea = this.form1.digarea.value.length
    var digresp = this.form1.digresp.value.length
    var usuario = this.form1.usuario.value.length
    var password1 = this.form1.password1.value.length
    var password2 = this.form1.password2.value.length
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
            this.form1.digarea.value = ''
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
            this.form1.digresp.value = ''
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
            usuario = this.form1.usuario.value
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
            password1 = this.form1.password1.value;
            if(vacio(password1)){
               document.getElementById('pwd1').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd1').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = '' 
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
            password2 = this.form1.password2.value;
            if(vacio(password2)){
                document.getElementById('pwd2').innerHTML = "No puede contener espacios en blanco"; 
                document.getElementById('pwd2').style.visibility = 'visible';
                this.form1.password1.value = ''
                this.form1.password2.value = '' 
                aux++;
            }else{
                document.getElementById('pwd2').style.visibility = 'hidden';	
            }
        }
    }
    if(aux == 0 ){
        if (this.form1.password2.value == this.form1.password1.value){
            this.form1.submit(); 	
        }else{
            document.getElementById('pwds').style.visibility = 'visible';
            this.form1.password1.value = ''
            this.form1.password2.value = ''
        }	
    }
}
