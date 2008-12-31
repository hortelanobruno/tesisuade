package db;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * Clase Wrapper para leer un archivo
 *
 */
public class FileReaderWrapper {
	private String file;

	public FileReaderWrapper(String file){
		this.file = file;
	}

	@SuppressWarnings("deprecation")
	public String obtenerContenido(){
		StringBuffer contenido = new StringBuffer();
		try
		{
			FileInputStream fileInputStream = new FileInputStream(file);
			DataInputStream input = new DataInputStream(fileInputStream);
			
			while (input.available() !=0)
				contenido.append(input.readLine());
			
			input.close();
		}catch(Exception ex){
			System.out.println("Error al leer el contenido del archivo");
		}
		return contenido.toString();
	}
}
