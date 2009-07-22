/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.persistencia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Brunoli
 */
public class RunClassTestInsert {

    private ExecutorService executorService;

    public static void main(String[] args) {
        new RunClassTestInsert();
    }

    public RunClassTestInsert() {
        inserts();
    }

    private void inserts() {
        executorService = Executors.newFixedThreadPool(2);
        InsertThreadPersistencia thread1 = new InsertThreadPersistencia();
        InsertThreadPersistenciaMyISAM thread2 = new InsertThreadPersistenciaMyISAM();
        executorService.execute(thread1);
        executorService.execute(thread2);
    }
}
