/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * @author Administrador
 */
public class FileCopy {

    public FileCopy() {
        
    }

    public void copiar(String urlOrigen, String urlDestino){
        try{
            FileInputStream fis = new FileInputStream(urlOrigen); 
            FileOutputStream fos = new FileOutputStream(urlDestino); 
            FileChannel canalFuente = fis.getChannel(); 
            FileChannel canalDestino = fos.getChannel(); 
            canalFuente.transferTo(0, canalFuente.size(), canalDestino); 
            fis.close(); 
            fos.close();
         }catch (IOException ex) {}
    }

    public void eliminar(String urlOrigen){
        File f = new File(urlOrigen);
        if(f.exists()){ 
            f.delete();
        }else{ 
            System.out.println("El directorio no existe :("); 
        } 
    }
    
}
