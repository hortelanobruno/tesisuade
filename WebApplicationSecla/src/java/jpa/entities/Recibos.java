/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "recibos")
@NamedQueries({@NamedQuery(name = "Recibos.findAll", query = "SELECT r FROM Recibos r"),
@NamedQuery(name = "Recibos.findByUsuario", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario"),
@NamedQuery(name = "Recibos.findByNumero", query = "SELECT r FROM Recibos r WHERE r.recibosPK.numero = :numero"),
@NamedQuery(name = "Recibos.obtenerRecibosConfirmados", query = "SELECT r.recibosPK.numero FROM Recibos r WHERE r.estadotransaccion = 'rendida' ORDER BY r.recibosPK.numero desc"),
@NamedQuery(name = "Recibos.totalEfectivoOperador", query = "SELECT sum(r.monto) FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadotransaccion <> 'pendiente' and r.estadotransaccion <> 'rendida' and r.banco IS NULL"),
@NamedQuery(name = "Recibos.totalChequeOperador", query = "SELECT sum(r.monto) FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadotransaccion <> 'pendiente' and r.estadotransaccion <> 'rendida' and r.banco IS NOT NULL"),
@NamedQuery(name = "Recibos.obtenerRecibosPendientes", query = "SELECT r.recibosPK.numero FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadotransaccion = 'pendiente'"),
@NamedQuery(name = "Recibos.obtenerRecibosAnuladas", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'anulada' and r.estadotransaccion <> 'rendida'"),
@NamedQuery(name = "Recibos.obtenerRecibosAConfirmar", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadotransaccion = 'a confirmar'"),
@NamedQuery(name = "Recibos.obtenerRecibosAnuladasRendidos", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'anulada' and r.estadotransaccion = 'rendida'  ORDER BY r.recibosPK.numero desc"),
@NamedQuery(name = "Recibos.obtenerRecibosExtraviadas", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'extraviada' and r.estadotransaccion <> 'rendida'"),
@NamedQuery(name = "Recibos.obtenerRecibosExtraviadasRendidos", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'extraviada' and r.estadotransaccion = 'rendida' ORDER BY r.recibosPK.numero desc"),
@NamedQuery(name = "Recibos.obtenerRecibosCompletadas", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'completada' and r.estadotransaccion <> 'rendida'"),
@NamedQuery(name = "Recibos.obtenerRecibosCompletadasRendidos", query = "SELECT r FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadorecibo = 'completada' and r.estadotransaccion = 'rendida' ORDER BY r.recibosPK.numero desc"),
@NamedQuery(name = "Recibos.existeRecibo", query = "SELECT count(r) FROM Recibos r WHERE r.recibosPK.numero BETWEEN  :num1 and :num2 "),
@NamedQuery(name = "Recibos.findByFechaconfeccion", query = "SELECT r FROM Recibos r WHERE r.fechaconfeccion = :fechaconfeccion"),
@NamedQuery(name = "Recibos.chequearBorrarResponsable", query = "SELECT count(r) FROM Recibos r WHERE r.recibosPK.usuario = :usuario and r.estadotransaccion <> 'rendida'"),
@NamedQuery(name = "Recibos.findByRazonsocial", query = "SELECT r FROM Recibos r WHERE r.razonsocial = :razonsocial"),
@NamedQuery(name = "Recibos.findByMonto", query = "SELECT r FROM Recibos r WHERE r.monto = :monto"),
@NamedQuery(name = "Recibos.findByEstadorecibo", query = "SELECT r FROM Recibos r WHERE r.estadorecibo = :estadorecibo"),
@NamedQuery(name = "Recibos.findByEstadotransaccion", query = "SELECT r FROM Recibos r WHERE r.estadotransaccion = :estadotransaccion"),
@NamedQuery(name = "Recibos.findByMotivo", query = "SELECT r FROM Recibos r WHERE r.motivo = :motivo"),
@NamedQuery(name = "Recibos.findByNumerocuota", query = "SELECT r FROM Recibos r WHERE r.numerocuota = :numerocuota"),
@NamedQuery(name = "Recibos.findByNumeroacta", query = "SELECT r FROM Recibos r WHERE r.numeroacta = :numeroacta"),
@NamedQuery(name = "Recibos.findByBanco", query = "SELECT r FROM Recibos r WHERE r.banco = :banco"),
@NamedQuery(name = "Recibos.findByNumerocheque", query = "SELECT r FROM Recibos r WHERE r.numerocheque = :numerocheque"),
@NamedQuery(name = "Recibos.findByFechavencimiento", query = "SELECT r FROM Recibos r WHERE r.fechavencimiento = :fechavencimiento")})
public class Recibos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecibosPK recibosPK;
    @Column(name = "fechaconfeccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaconfeccion;
    @Column(name = "razonsocial")
    private String razonsocial;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "estadorecibo")
    private String estadorecibo;
    @Column(name = "estadotransaccion")
    private String estadotransaccion;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "numerocuota")
    private String numerocuota;
    @Column(name = "numeroacta")
    private Integer numeroacta;
    @Column(name = "banco")
    private String banco;
    @Column(name = "numerocheque")
    private String numerocheque;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavencimiento;

    public Recibos() {
    }

    public Recibos(RecibosPK recibosPK) {
        this.recibosPK = recibosPK;
    }

    public Recibos(String usuario, int numero) {
        this.recibosPK = new RecibosPK(usuario, numero);
    }

    public RecibosPK getRecibosPK() {
        return recibosPK;
    }

    public void setRecibosPK(RecibosPK recibosPK) {
        this.recibosPK = recibosPK;
    }

    public Date getFechaconfeccion() {
        return fechaconfeccion;
    }

    public void setFechaconfeccion(Date fechaconfeccion) {
        this.fechaconfeccion = fechaconfeccion;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstadorecibo() {
        return estadorecibo;
    }

    public void setEstadorecibo(String estadorecibo) {
        this.estadorecibo = estadorecibo;
    }

    public String getEstadotransaccion() {
        return estadotransaccion;
    }

    public void setEstadotransaccion(String estadotransaccion) {
        this.estadotransaccion = estadotransaccion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNumerocuota() {
        return numerocuota;
    }

    public void setNumerocuota(String numerocuota) {
        this.numerocuota = numerocuota;
    }

    public Integer getNumeroacta() {
        return numeroacta;
    }

    public void setNumeroacta(Integer numeroacta) {
        this.numeroacta = numeroacta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumerocheque() {
        return numerocheque;
    }

    public void setNumerocheque(String numerocheque) {
        this.numerocheque = numerocheque;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recibosPK != null ? recibosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibos)) {
            return false;
        }
        Recibos other = (Recibos) object;
        if ((this.recibosPK == null && other.recibosPK != null) || (this.recibosPK != null && !this.recibosPK.equals(other.recibosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Recibos[recibosPK=" + recibosPK + "]";
    }

}
