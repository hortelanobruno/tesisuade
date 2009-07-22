/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "pruebamyisam")
@NamedQueries({@NamedQuery(name = "PruebaMyisam.findAll", query = "SELECT p FROM PruebaMyisam p"), @NamedQuery(name = "PruebaMyisam.findByTimestamp", query = "SELECT p FROM PruebaMyisam p WHERE p.pruebaMyisamPK.timestamp = :timestamp"), @NamedQuery(name = "PruebaMyisam.findByCMTShostname", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTShostname = :cMTShostname"), @NamedQuery(name = "PruebaMyisam.findByCMTSipAddress", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTSipAddress = :cMTSipAddress"), @NamedQuery(name = "PruebaMyisam.findByCMTSsysUpTime", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTSsysUpTime = :cMTSsysUpTime"), @NamedQuery(name = "PruebaMyisam.findByCMTScatvIfName", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTScatvIfName = :cMTScatvIfName"), @NamedQuery(name = "PruebaMyisam.findByCMTScatvIfIndex", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTScatvIfIndex = :cMTScatvIfIndex"), @NamedQuery(name = "PruebaMyisam.findByCMTSupIfName", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTSupIfName = :cMTSupIfName"), @NamedQuery(name = "PruebaMyisam.findByCMTSupIfType", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTSupIfType = :cMTSupIfType"), @NamedQuery(name = "PruebaMyisam.findByCMTSdownIfName", query = "SELECT p FROM PruebaMyisam p WHERE p.cMTSdownIfName = :cMTSdownIfName"), @NamedQuery(name = "PruebaMyisam.findByCMmacAddress", query = "SELECT p FROM PruebaMyisam p WHERE p.pruebaMyisamPK.cMmacAddress = :cMmacAddress"), @NamedQuery(name = "PruebaMyisam.findByCMdocsisMode", query = "SELECT p FROM PruebaMyisam p WHERE p.cMdocsisMode = :cMdocsisMode"), @NamedQuery(name = "PruebaMyisam.findByCMipAddress", query = "SELECT p FROM PruebaMyisam p WHERE p.cMipAddress = :cMipAddress"), @NamedQuery(name = "PruebaMyisam.findByCMcpeIpv4List", query = "SELECT p FROM PruebaMyisam p WHERE p.cMcpeIpv4List = :cMcpeIpv4List"), @NamedQuery(name = "PruebaMyisam.findByRecType", query = "SELECT p FROM PruebaMyisam p WHERE p.recType = :recType"), @NamedQuery(name = "PruebaMyisam.findByRecCreationTime", query = "SELECT p FROM PruebaMyisam p WHERE p.recCreationTime = :recCreationTime"), @NamedQuery(name = "PruebaMyisam.findByServiceIdentifier", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceIdentifier = :serviceIdentifier"), @NamedQuery(name = "PruebaMyisam.findByGateId", query = "SELECT p FROM PruebaMyisam p WHERE p.gateId = :gateId"), @NamedQuery(name = "PruebaMyisam.findByServiceClassName", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceClassName = :serviceClassName"), @NamedQuery(name = "PruebaMyisam.findByServiceDirection", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceDirection = :serviceDirection"), @NamedQuery(name = "PruebaMyisam.findByServiceOctetsPassed", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceOctetsPassed = :serviceOctetsPassed"), @NamedQuery(name = "PruebaMyisam.findByServicePktsPassed", query = "SELECT p FROM PruebaMyisam p WHERE p.servicePktsPassed = :servicePktsPassed"), @NamedQuery(name = "PruebaMyisam.findByServiceSlaDropPkts", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceSlaDropPkts = :serviceSlaDropPkts"), @NamedQuery(name = "PruebaMyisam.findByServiceSlaDelayPkts", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceSlaDelayPkts = :serviceSlaDelayPkts"), @NamedQuery(name = "PruebaMyisam.findByServiceTimeCreated", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceTimeCreated = :serviceTimeCreated"), @NamedQuery(name = "PruebaMyisam.findByServiceTimeActive", query = "SELECT p FROM PruebaMyisam p WHERE p.serviceTimeActive = :serviceTimeActive")})
public class PruebaMyisam implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaMyisamPK pruebaMyisamPK;
    @Column(name = "CMTShostname")
    private String cMTShostname;
    @Column(name = "CMTSipAddress")
    private String cMTSipAddress;
    @Column(name = "CMTSsysUpTime")
    private BigInteger cMTSsysUpTime;
    @Column(name = "CMTScatvIfName")
    private String cMTScatvIfName;
    @Column(name = "CMTScatvIfIndex")
    private BigInteger cMTScatvIfIndex;
    @Column(name = "CMTSupIfName")
    private String cMTSupIfName;
    @Column(name = "CMTSupIfType")
    private Integer cMTSupIfType;
    @Column(name = "CMTSdownIfName")
    private String cMTSdownIfName;
    @Column(name = "CMdocsisMode")
    private Integer cMdocsisMode;
    @Column(name = "CMipAddress")
    private String cMipAddress;
    @Column(name = "CMcpeIpv4List")
    private String cMcpeIpv4List;
    @Column(name = "RecType")
    private Integer recType;
    @Column(name = "RecCreationTime")
    private String recCreationTime;
    @Column(name = "serviceIdentifier")
    private BigInteger serviceIdentifier;
    @Column(name = "gateId")
    private BigInteger gateId;
    @Column(name = "serviceClassName")
    private String serviceClassName;
    @Column(name = "serviceDirection")
    private Integer serviceDirection;
    @Column(name = "serviceOctetsPassed")
    private BigInteger serviceOctetsPassed;
    @Column(name = "servicePktsPassed")
    private BigInteger servicePktsPassed;
    @Column(name = "serviceSlaDropPkts")
    private BigInteger serviceSlaDropPkts;
    @Column(name = "serviceSlaDelayPkts")
    private BigInteger serviceSlaDelayPkts;
    @Column(name = "serviceTimeCreated")
    private BigInteger serviceTimeCreated;
    @Column(name = "serviceTimeActive")
    private BigInteger serviceTimeActive;

    public PruebaMyisam() {
    }

    public PruebaMyisam(PruebaMyisamPK pruebaMyisamPK) {
        this.pruebaMyisamPK = pruebaMyisamPK;
    }

    public PruebaMyisam(long timestamp, String cMmacAddress) {
        this.pruebaMyisamPK = new PruebaMyisamPK(timestamp, cMmacAddress);
    }

    public PruebaMyisamPK getPruebaMyisamPK() {
        return pruebaMyisamPK;
    }

    public void setPruebaMyisamPK(PruebaMyisamPK pruebaMyisamPK) {
        this.pruebaMyisamPK = pruebaMyisamPK;
    }

    public String getCMTShostname() {
        return cMTShostname;
    }

    public void setCMTShostname(String cMTShostname) {
        this.cMTShostname = cMTShostname;
    }

    public String getCMTSipAddress() {
        return cMTSipAddress;
    }

    public void setCMTSipAddress(String cMTSipAddress) {
        this.cMTSipAddress = cMTSipAddress;
    }

    public BigInteger getCMTSsysUpTime() {
        return cMTSsysUpTime;
    }

    public void setCMTSsysUpTime(BigInteger cMTSsysUpTime) {
        this.cMTSsysUpTime = cMTSsysUpTime;
    }

    public String getCMTScatvIfName() {
        return cMTScatvIfName;
    }

    public void setCMTScatvIfName(String cMTScatvIfName) {
        this.cMTScatvIfName = cMTScatvIfName;
    }

    public BigInteger getCMTScatvIfIndex() {
        return cMTScatvIfIndex;
    }

    public void setCMTScatvIfIndex(BigInteger cMTScatvIfIndex) {
        this.cMTScatvIfIndex = cMTScatvIfIndex;
    }

    public String getCMTSupIfName() {
        return cMTSupIfName;
    }

    public void setCMTSupIfName(String cMTSupIfName) {
        this.cMTSupIfName = cMTSupIfName;
    }

    public Integer getCMTSupIfType() {
        return cMTSupIfType;
    }

    public void setCMTSupIfType(Integer cMTSupIfType) {
        this.cMTSupIfType = cMTSupIfType;
    }

    public String getCMTSdownIfName() {
        return cMTSdownIfName;
    }

    public void setCMTSdownIfName(String cMTSdownIfName) {
        this.cMTSdownIfName = cMTSdownIfName;
    }

    public Integer getCMdocsisMode() {
        return cMdocsisMode;
    }

    public void setCMdocsisMode(Integer cMdocsisMode) {
        this.cMdocsisMode = cMdocsisMode;
    }

    public String getCMipAddress() {
        return cMipAddress;
    }

    public void setCMipAddress(String cMipAddress) {
        this.cMipAddress = cMipAddress;
    }

    public String getCMcpeIpv4List() {
        return cMcpeIpv4List;
    }

    public void setCMcpeIpv4List(String cMcpeIpv4List) {
        this.cMcpeIpv4List = cMcpeIpv4List;
    }

    public Integer getRecType() {
        return recType;
    }

    public void setRecType(Integer recType) {
        this.recType = recType;
    }

    public String getRecCreationTime() {
        return recCreationTime;
    }

    public void setRecCreationTime(String recCreationTime) {
        this.recCreationTime = recCreationTime;
    }

    public BigInteger getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(BigInteger serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public BigInteger getGateId() {
        return gateId;
    }

    public void setGateId(BigInteger gateId) {
        this.gateId = gateId;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public Integer getServiceDirection() {
        return serviceDirection;
    }

    public void setServiceDirection(Integer serviceDirection) {
        this.serviceDirection = serviceDirection;
    }

    public BigInteger getServiceOctetsPassed() {
        return serviceOctetsPassed;
    }

    public void setServiceOctetsPassed(BigInteger serviceOctetsPassed) {
        this.serviceOctetsPassed = serviceOctetsPassed;
    }

    public BigInteger getServicePktsPassed() {
        return servicePktsPassed;
    }

    public void setServicePktsPassed(BigInteger servicePktsPassed) {
        this.servicePktsPassed = servicePktsPassed;
    }

    public BigInteger getServiceSlaDropPkts() {
        return serviceSlaDropPkts;
    }

    public void setServiceSlaDropPkts(BigInteger serviceSlaDropPkts) {
        this.serviceSlaDropPkts = serviceSlaDropPkts;
    }

    public BigInteger getServiceSlaDelayPkts() {
        return serviceSlaDelayPkts;
    }

    public void setServiceSlaDelayPkts(BigInteger serviceSlaDelayPkts) {
        this.serviceSlaDelayPkts = serviceSlaDelayPkts;
    }

    public BigInteger getServiceTimeCreated() {
        return serviceTimeCreated;
    }

    public void setServiceTimeCreated(BigInteger serviceTimeCreated) {
        this.serviceTimeCreated = serviceTimeCreated;
    }

    public BigInteger getServiceTimeActive() {
        return serviceTimeActive;
    }

    public void setServiceTimeActive(BigInteger serviceTimeActive) {
        this.serviceTimeActive = serviceTimeActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaMyisamPK != null ? pruebaMyisamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaMyisam)) {
            return false;
        }
        PruebaMyisam other = (PruebaMyisam) object;
        if ((this.pruebaMyisamPK == null && other.pruebaMyisamPK != null) || (this.pruebaMyisamPK != null && !this.pruebaMyisamPK.equals(other.pruebaMyisamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prueba.persistencia.PruebaMyisam[pruebaMyisamPK=" + pruebaMyisamPK + "]";
    }

}
