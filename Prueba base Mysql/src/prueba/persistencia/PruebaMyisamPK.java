/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Brunoli
 */
@Embeddable
public class PruebaMyisamPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "timestamp")
    private long timestamp;
    @Basic(optional = false)
    @Column(name = "CMmacAddress")
    private String cMmacAddress;

    public PruebaMyisamPK() {
    }

    public PruebaMyisamPK(long timestamp, String cMmacAddress) {
        this.timestamp = timestamp;
        this.cMmacAddress = cMmacAddress;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCMmacAddress() {
        return cMmacAddress;
    }

    public void setCMmacAddress(String cMmacAddress) {
        this.cMmacAddress = cMmacAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) timestamp;
        hash += (cMmacAddress != null ? cMmacAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaMyisamPK)) {
            return false;
        }
        PruebaMyisamPK other = (PruebaMyisamPK) object;
        if (this.timestamp != other.timestamp) {
            return false;
        }
        if ((this.cMmacAddress == null && other.cMmacAddress != null) || (this.cMmacAddress != null && !this.cMmacAddress.equals(other.cMmacAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prueba.persistencia.PruebaMyisamPK[timestamp=" + timestamp + ", cMmacAddress=" + cMmacAddress + "]";
    }

}
