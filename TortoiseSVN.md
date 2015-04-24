En este documento se explica como descargar del repositorio carpetas mediate el cliente de svn TortoiseSVN osea sin necesidad de usar el Eclipse:

Lo primero que hay que hacer es descargar el cliente e instalarlo, la pagina para descargarlo es http://tortoisesvn.net/downloads

Una vez que tenemos el cliente instalado correctamente hay que crear una carpeta en el sistema operativo con el nombre que queramos y apretar botón derecho y ir a la sección de TortoiseSVN y elegir Import.

Se nos va a abrir una ventana y tenemos que poner la dirección al repositorio que contiene la carpeta que deseamos descargar e.g : https://tesisuade.googlecode.com/svn/trunk/Ontologias y apretar Ok.

Si nos pide un usuario y contraseña son los datos del repositorio de la pagina de google. Y al apretar Ok se nos va a descargar la carpeta del repositorio.



Utilizar el repositorio:

Para utilizar el repositorio hay 2 funciones indispensables, Commit y Update.

La función Commit se encarga de actualizar ( copiar archivos modificados) el elemento seleccionado al repositorio. Osea de la pc al repositorio.

La función Update se encarga de actualizar ( copiar archivos modificados) los elementos modificados a la pc. Osea del repositorio a la pc.

Una buena practica es antes de modificar algo hacer un Update así modificamos sobre la ultima versión. Porque pueden aparecer conflictos porque queremos subir una versión que esta obsoleta.