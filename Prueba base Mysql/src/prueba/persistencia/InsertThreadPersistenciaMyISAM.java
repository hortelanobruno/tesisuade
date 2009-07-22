/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.persistencia;

import java.math.BigInteger;
import java.util.Calendar;
import prueba.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Brunoli
 */
public class InsertThreadPersistenciaMyISAM implements Runnable {

    private PruebaMyisamJpaController jpa;

    public InsertThreadPersistenciaMyISAM() {
        jpa = new PruebaMyisamJpaController();
    }

    @Override
    public void run() {
        PruebaMyisam ipdr = new PruebaMyisam();
        cargarIPDR(ipdr);
        PruebaMyisamPK pk;
        int aux = 0;
        int aux2 = 0;
        while (true) {
            try {
                pk = new PruebaMyisamPK();
                pk.setCMmacAddress("asdf" + Math.random() * 123444);
                pk.setTimestamp(Calendar.getInstance().getTimeInMillis());
                ipdr.setPruebaMyisamPK(pk);
                jpa.create(ipdr);
            } catch (PreexistingEntityException ex) {
                System.out.println("ERROR");
            } catch (Exception ex) {
                System.out.println("ERROR");
            }
            aux++;
            aux2++;
            if (aux > 500) {
                System.out.println(aux2 + " INSERT EN LA TABLA MYISAM");
                aux = 0;
            }
        }
    }

    private void cargarIPDR(PruebaMyisam ipdr) {
        ipdr.setCMTScatvIfIndex(new BigInteger("1234234"));
        ipdr.setCMTScatvIfName("asdfsdf");
        ipdr.setCMTSdownIfName("asdfsd");
        ipdr.setCMTShostname("asdfs");
        ipdr.setCMTSipAddress("asdfs");
        ipdr.setCMTSsysUpTime(new BigInteger("45454"));
        ipdr.setCMTSupIfName("asdfsd");
        ipdr.setCMTSupIfType(1234);
        ipdr.setCMcpeIpv4List("asdfs");
        ipdr.setCMdocsisMode(12354);
        ipdr.setCMipAddress("asdfa");
        ipdr.setGateId(new BigInteger("35345"));
        ipdr.setRecCreationTime("fdsafas");
        ipdr.setRecType(432432);
        ipdr.setServiceClassName("sadfds");
        ipdr.setServiceDirection(45354);
        ipdr.setServiceIdentifier(new BigInteger("32423"));
        ipdr.setServiceOctetsPassed(new BigInteger("234234"));
        ipdr.setServicePktsPassed(new BigInteger("34234"));
        ipdr.setServiceSlaDelayPkts(new BigInteger("3243"));
        ipdr.setServiceSlaDropPkts(new BigInteger("34234"));
        ipdr.setServiceTimeActive(new BigInteger("34234"));
        ipdr.setServiceTimeCreated(new BigInteger("34234"));
    }
}
