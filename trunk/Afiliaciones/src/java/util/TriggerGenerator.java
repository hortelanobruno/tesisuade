
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class TriggerGenerator {

    private Vector<String> tables;

    public TriggerGenerator() {
        cargarTables();
        System.out.println("CREATE TABLE Prueba (mensaje varchar(100), fecha datetime) GO");
        for(int i=0 ; i < tables.size() ; i++){
            System.out.println("CREATE TRIGGER "+tables.get(i)+"I ON "+tables.get(i)+" AFTER INSERT AS insert into Prueba values('INSERT en "+tables.get(i)+"',getdate()) \nGO");
            System.out.println("CREATE TRIGGER "+tables.get(i)+"U ON "+tables.get(i)+" AFTER UPDATE AS insert into Prueba values('UPDATE en "+tables.get(i)+"',getdate()) \nGO");
            System.out.println("CREATE TRIGGER "+tables.get(i)+"D ON "+tables.get(i)+" AFTER DELETE AS insert into Prueba values('DELETE en "+tables.get(i)+"',getdate()) \nGO");
        }
    }

    public static void main(String args[]) {
        new TriggerGenerator();
    }

    private void cargarTables() {
        tables = new Vector<String>();
        tables.add("ActasInspeccion");
        tables.add("ActasPendientes");
        tables.add("ActasRendicionesManuales");
        tables.add("Actividad");
        tables.add("Años");
        tables.add("Cheques");
        tables.add("Clasificacion");
        tables.add("Coeficientes");
        tables.add("Conceptos");
        tables.add("ConfigSistema");
        tables.add("ControlConsultas");
        tables.add("Cuentas");
        tables.add("Deuda");
        tables.add("DeudaAux");
        tables.add("DeudaPlanAux");
        tables.add("DeudaReporte");
        tables.add("DomEmpresas");
        tables.add("dtproperties");
        tables.add("Empresas");
        tables.add("EntregaActas");
        tables.add("EntregaRecibos");
        tables.add("GeneradorCodEmpresas");
        tables.add("IAMEmpleados");
        tables.add("IAMEmpresas");
        tables.add("IAMIva");
        tables.add("IAMLocalidades");
        tables.add("IAMProvincias");
        tables.add("IAMTemporal_Empresas");
        tables.add("IAMTemporal_Empresas1");
        tables.add("IAMTemporal_Empresas2");
        tables.add("IAMTipoDoc");
        tables.add("Inspectores");
        tables.add("Mapas");
        tables.add("Meses");
        tables.add("Modalidad");
        tables.add("Motivos");
        tables.add("MotivosBajasEmp");
        tables.add("Origen");
        tables.add("Pagos");
        tables.add("PagosCuota");
        tables.add("PagosRepetidos");
        tables.add("ParteInspeccion");
        tables.add("ParteInspeccionDetalle");
        tables.add("Periodo");
        tables.add("PlanesPago");
        tables.add("RecorridoPorZonas");
        tables.add("Reportes");
        tables.add("saltcatedetalle");
        tables.add("saltestadosreq");
        tables.add("saltetapareq");
        tables.add("saltHistoricorecldetalle");
        tables.add("saltHistoricorecsueldoafi");
        tables.add("salthistoricoreqdetalle");
        tables.add("saltHistoricorequerimientos");
        tables.add("saltorigenreq");
        tables.add("saltRecoDetalle");
        tables.add("saltRecorridos");
        tables.add("saltrecsueldoafi");
        tables.add("saltreqdetalle");
        tables.add("saltrequerimientos");
        tables.add("saltsecretarias");
        tables.add("salttipocate");
        tables.add("salttiporeclamo");
        tables.add("salttiporeq");
        tables.add("salttrecldetalle");
        tables.add("satlestadoslegajos");
        tables.add("Sectores");
        tables.add("siatciaaseg");
        tables.add("siatPolAct");
        tables.add("siatRecoDetalle");
        tables.add("siatRecorridos");
        tables.add("siatreqseg");
        tables.add("siatTipoAge");
        tables.add("siatTipoSeg");
        tables.add("simtMovServ");
        tables.add("simtServDeleg");
        tables.add("simtServDelegDet");
        tables.add("simtServDelegDetHist");
        tables.add("simtServDelegHist");
        tables.add("simtServEmpl");
        tables.add("simtServEmplDet");
        tables.add("simtTipoServ");
        tables.add("SMDelegados");
        tables.add("SubActividad");
        tables.add("TempDeuda");
        tables.add("TiposActa");
        tables.add("Unificaciones");
        tables.add("VerificarPagosRepetidos");
        tables.add("Zonas");
    }
}
