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
     var iAscii; 
     
     if (oEvento.keyCode) 
         iAscii = oEvento.keyCode; 
     else if (oEvento.which) 
         iAscii = oEvento.which; 
     else 
         return false; 

     if (iAscii == 13) oFormulario.submit(); 
	 
     return true; 
}

function validarEntregarBoleta(){
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
		document.getElementById('rango').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('rango').style.visibility = 'hidden';
	}
	if(numMax == 0){
		document.getElementById('rango').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('rango').style.visibility = 'hidden';
	}
	if(aux == 0 ){
		if((IsReal(numMin) == 0) && (IsReal(numMax) == 0)){
			var usuarioValue = this.form1.listaOperadores.options[operador].value
			this.form1.submit(); 
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
      document.getElementById('menu').textContent = "Debe seleccionar un campo";
      document.getElementById('menu').style.visibility = 'visible';
      aux++;
    }else{
      document.getElementById('menu').style.visibility = 'hidden';
    }
    if(password1 == 0){
      document.getElementById('pwd1').textContent = "Debe completar el campo";
      document.getElementById('pwd1').style.visibility = 'visible';
      aux++;
    }else{
        if(password2.length < 4){
          ument.getElementById('pwd1').textContent = "Minimo 4 caracteres";
          document.getElementById('pwd1').style.visibility = 'visible';
          aux
        }else{
          document.getElementById('pwd1').style.visibility = 'hidden';
        }
    }
    if(password2 == 0){
      document.getElementById('pwd2').textContent = "Debe completar el campo";
      document.getElementById('pwd2').style.visibility = 'visible';
      aux++;
    }else{
        if(password2.length < 4){
          document.getElementById('pwd2').textContent = "Minimo 4 caracteres";
          document.getElementById('pwd2').style.visibility = 'visible';
          aux   
        }else{
          document.getElementById('pwd2').style.visibility = 'hidden';
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

function confirmarBoletas(){
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



function validarCompletarBoleta()
{
	var boleta = this.form1.boletas.selectedIndex;
	var monto = this.form1.monto.value.length;
	var beneficiario = this.form1.beneficiario.value.length;
	var fecha = this.form1.date.value.length;
	var aux=0;
	if(boleta == -1 ){
                document.getElementById('boleta').textContent = "Debe seleccionar un campo";
		document.getElementById('boleta').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('boleta').style.visibility = 'hidden';
	}
	if(monto==0){
                document.getElementById('monto').textContent = "Debe completar el campo";
		document.getElementById('monto').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('monto').style.visibility = 'hidden';
	}
	if(fecha==0){
                document.getElementById('fecha2').textContent = "Debe seleccionar una fecha";
		document.getElementById('fecha2').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('fecha2').style.visibility = 'hidden';
	}
	if(beneficiario==0){
                document.getElementById('beneficiario').textContent = "Debe completar el campo";
		document.getElementById('beneficiario').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('beneficiario').style.visibility = 'hidden';
	}
	if(aux == 0 ){
            var valor = this.form1.monto.value;
            if(/^[0-9]+(,[0-9]+)*$/.test(valor)){
                    this.form1.submit();  
            }else{ 
              document.getElementById('monto').textContent = "Debe ser un mumero";
              document.getElementById('monto').style.visibility = 'visible';
              this.form1.monto.value = ''  
                    	
            } 
	}
}



function validarAnularBoleta(){
	var motivo = this.form1.motivo.value.length;
	var boleta = this.form1.boletas.selectedIndex;
	var aux=0;
	
	if(boleta == -1 ){
		document.getElementById('boleta').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('boleta').style.visibility = 'hidden';
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
	var pwd4 = this.form1.password4.value.length;
	var aux=0;

	if(pwd1==0){
		document.getElementById('pwd1').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('pwd1').style.visibility = 'hidden';
	}
	if(pwd2==0){
		document.getElementById('pwd2').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('pwd2').style.visibility = 'hidden';
	}
	if(pwd3==0){
		document.getElementById('pwd3').style.visibility = 'visible';
		aux++;
	}else{
		document.getElementById('pwd3').style.visibility = 'hidden';
	}
	if(aux == 0 ){
		if(this.form1.password1.value == this.form1.password4.value){
			document.getElementById('pwds2').style.visibility = 'hidden';
			if (this.form1.password2.value == this.form1.password3.value){
				this.form1.submit(); 	
			}else{
				document.getElementById('pwds').style.visibility = 'visible';
				this.form1.password2.value = ''
				this.form1.password3.value = ''
			}	
		}else{
			document.getElementById('pwds2').style.visibility = 'visible';
			this.form1.password2.value = ''
			this.form1.password3.value = ''
			this.form1.password1.value = ''
		}
	}
	
}

function validarAltaArea(){

	var responsable = this.form1.responsable.value.length
	var secretaria = this.form1.secretaria.value.length
	var funcion = this.form1.funcion.value.length
	var usuario = this.form1.usuario.value.length
	var password1 = this.form1.password1.value.length
	var password2 = this.form1.password2.value.length
	var aux = 0;


	if(responsable==0){  
          document.getElementById('resp').textContent = "Debe completar el campo";
          document.getElementById('resp').style.visibility = 'visible';
          aux++;
	}else{
          if(responsable < 3){
            document.getElementById('resp').textContent = "Minimo 3 caracteres";
            document.getElementById('resp').style.visibility = 'visible';
            aux++;
          }else{
            document.getElementById('resp').style.visibility = 'hidden';		
          }
	}
	if(funcion == 0){
          document.getElementById('fun').textContent = "Debe completar el campo";
          document.getElementById('fun').style.visibility = 'visible';
          aux++;
	}else{
          if(funcion < 3){
            document.getElementById('fun').textContent = "Minimo 3 caracteres";  
            document.getElementById('fun').style.visibility = 'visible';
            aux++
          }else{
            document.getElementById('fun').style.visibility = 'hidden';
          }
	}
	if(secretaria == 0){
            document.getElementById('sec').textContent = "Debe completar el campo";
            document.getElementById('sec').style.visibility = 'visible';
            aux++;
	}else{
          if(secretaria < 3){
            document.getElementById('sec').textContent = "Minimo 3 caracteres"; 
            document.getElementById('sec').style.visibility = 'visible';	
            aux++
          }else{
            document.getElementById('sec').style.visibility = 'hidden';	
          }
	}
	if(usuario == 0){
            document.getElementById('usu').textContent = "Debe completar el campo";
            document.getElementById('usu').style.visibility = 'visible';
            aux++;
	}else{
          if(usuario < 4){
              document.getElementById('usu').textContent = "Minimo 4 caracteres"; 
              document.getElementById('usu').style.visibility = 'visible';	
              aux++;
          }else{
              document.getElementById('usu').style.visibility = 'hidden';
          }
	}
	if(password1 == 0){
            document.getElementById('pwd1').textContent = "Debe completar el campo";
            document.getElementById('pwd1').style.visibility = 'visible';
            aux++;
	}else{
          if(password1 < 4){
              document.getElementById('pwd1').textContent = "Minimo 4 caracteres"; 
              document.getElementById('pwd1').style.visibility = 'visible';
              aux++;	
          }else{
              document.getElementById('pwd1').style.visibility = 'hidden';
          }
	}
	if(password2 == 0){
            document.getElementById('pwd2').textContent = "Debe completar el campo";
            document.getElementById('pwd2').style.visibility = 'visible';
            aux++;
	}else{
          if(password2 < 4){
              document.getElementById('pwd2').textContent = "Minimo 4 caracteres"; 
              document.getElementById('pwd2').style.visibility = 'visible';
              aux++;
          }else{
              document.getElementById('pwd2').style.visibility = 'hidden';	
          }
	}
	if(aux == 0 ){
		if (this.form1.password2.value == this.form1.password1.value){
                        alert("llego");
			this.form1.submit(); 	
		}else{
			document.getElementById('pwds').style.visibility = 'visible';
			this.form1.password1.value = ''
			this.form1.password2.value = ''
		}	
	}
}
