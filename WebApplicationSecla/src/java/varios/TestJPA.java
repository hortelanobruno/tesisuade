/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package varios;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.controllers.RecibosJpaController;
import jpa.controllers.UsuariosJpaController;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.entities.Recibos;
import jpa.entities.RecibosPK;
import jpa.entities.Usuarios;

/**
 *
 * @author Administrador
 */
public class TestJPA {

    private UsuariosJpaController usuariosController;
    private RecibosJpaController recibosController;

    public TestJPA() {
        usuariosController = new UsuariosJpaController();
        recibosController = new RecibosJpaController();


        Usuarios admin = usuariosController.findUsuarios("jorge");
        System.out.println("1");

        RecibosPK rPK = new RecibosPK();
        rPK.setNumero(10000);
        rPK.setUsuario("jorge");
        Recibos rec = recibosController.findRecibos(rPK);
        System.out.println("1");


        int resps = recibosController.chequearBorrarResponsable("mago");
        System.out.println("1");
    }

    public void modificarRecibo() {
        try {
            RecibosPK rPK = new RecibosPK();
            rPK.setNumero(10000);
            rPK.setUsuario("jorge");
            Recibos rec = recibosController.findRecibos(rPK);
            rec.setBanco("Banco Píáño");
            recibosController.edit(rec);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TestJPA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarRecibo() {
        try {
            RecibosPK rPK = new RecibosPK();
            rPK.setNumero(10000);
            rPK.setUsuario("jorge");
            recibosController.destroy(rPK);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TestJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearRecibo() {
        Recibos recibo = new Recibos();
        RecibosPK rPK = new RecibosPK();
        rPK.setNumero(10002);
        rPK.setUsuario("jorge");
        recibo.setRecibosPK(rPK);
        recibo.setEstadotransaccion("pendiente");
        Date date = new Date();
        date.setDate(31);
        date.setMonth(10);
        date.setYear(108);
        recibo.setFechaconfeccion(date);
        try {
            recibosController.create(recibo);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TestJPA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("1");
    }

    public void crearUsuario() {
        /*Crear usuario*/
        try {
            Usuarios newUser = new Usuarios();
            newUser.setUsuario("jorge");
            newUser.setPassword("jorge");
            usuariosController.create(newUser);
        } catch (PreexistingEntityException ex) {
            System.out.println("1");
        } catch (Exception ex) {
            System.out.println("2");
        }
    }

    public static void main(String args[]) {
        new TestJPA();
    }
}
