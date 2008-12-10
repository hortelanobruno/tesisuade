/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;


/**
 *
 * @author Administrador
 */
public class ReportesDinamicos {

    public ReportesDinamicos(){

    }

    public static DynamicReport generarReporteOperador() throws ColumnBuilderException, ClassNotFoundException{
       FastReportBuilder drb = new FastReportBuilder();
                drb.addColumn("Nombre", "usuario", String.class.getName(), 30)
                .addColumn("Password", "password", String.class.getName(), 50)
                .setTitle("Usuarios del sistema")
                .setQuery("select * from usuarios", DJConstants.QUERY_LANGUAGE_SQL)
                .setUseFullPageWidth(true);
        return drb.build();
    }
}
