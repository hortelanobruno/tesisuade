En este articulo se explica como conectarse con Eclipse al repositorio de google:


Primero hay que descargar el plugin para que Eclipse se pueda conectar a repositorios del tipo SVN, para descargarlo hay que agregar un nuevo sitio remoto en la sección de Updates del IDE.
El sitio remoto es http://subclipse.tigris.org/update_1.4.x, y instalar el plugin y sus requerimientos.
Una vez instalado correctamente el plugin se puede hacer muchas cosas, podemos descargar un proyecto del repositorio o subir un proyecto al mismo.

Pasos para descargar un proyecto del repositorio:

1 - Hay que ir a File, New, Other y elegir en la carpeta SVN el ítem que dice "Checkout Projects from SVN".

2 - Luego elegir el repositorio del cual se quiere descargar el proyecto, en caso de que no exista se debe crearlo, el source del repositorio es: https://tesisuade.googlecode.com/svn/trunk.

3 - Una vez seleccionado el repositorio correspondiente nos va a aparecer la jerarquía de carpetas que están en /trunk, notese que aqui es el directorio de los proyectos por lo que puede haber muchos proyectos de distinto tipo de lenguaje, así que hay que saber que proyecto se desea descargar. Una vez identificado la carpeta que contiene el proyecto se tiene que seleccionar y apretar en Finish. Notese que si es la primera vez nos puede pedir un usuario y contraseña que son los datos del repositorio.

4 - Por ultimo hay que verificar que en el icono del proyecto aparece un "tamborsito" en forma de db amarillo, esto significa que poseemos la ultima versión del proyecto en el repositorio, si este icono se vuelve en forma de asterisco significa que modificamos un archivo y hay que actualizarlo en el SVN.


Pasos para subir un proyecto al repositorio:

1 - Primero hay que elegir el proyecto que se desea subir y apretar boton derecho, Team y apretar en Share Project.

2 - Despues hay que elegir el tipo de repositorio SVN y elegir el repositorio que se desea utilizar, si no aparece en la lista hay que crearlo de la misma forma que se explico anteriormente.

3 - Por ultimo hay que elegir una carpeta en la cual se va a cargar el proyecto en el repositorio e.g: https://tesisuade.googlecode.com/svn/trunk/ProyectoNuevo y apretar en Finish.