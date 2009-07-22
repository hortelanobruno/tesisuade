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
@Table(name = "pruebainnodb")
@NamedQueries({@NamedQuery(name = "PruebaInnodb.findAll", query = "SELECT p FROM PruebaInnodb p"), @NamedQuery(name = "PruebaInnodb.findByTimestamp", query = "SELECT p FROM PruebaInnodb p WHERE p.pruebaInnodbPK.timestamp = :timestamp"), @NamedQuery(name = "PruebaInnodb.findByCMTShostname", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTShostname = :cMTShostname"), @NamedQuery(name = "PruebaInnodb.findByCMTSipAddress", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTSipAddress = :cMTSipAddress"), @NamedQuery(name = "PruebaInnodb.findByCMTSsysUpTime", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTSsysUpTime = :cMTSsysUpTime"), @NamedQuery(name = "PruebaInnodb.findByCMTScatvIfName", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTScatvIfName = :cMTScatvIfName"), @NamedQuery(name = "PruebaInnodb.findByCMTScatvIfIndex", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTScatvIfIndex = :cMTScatvIfIndex"), @NamedQuery(name = "PruebaInnodb.findByCMTSupIfName", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTSupIfName = :cMTSupIfName"), @NamedQuery(name = "PruebaInnodb.findByCMTSupIfType", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTSupIfType = :cMTSupIfType"), @NamedQuery(name = "PruebaInnodb.findByCMTSdownIfName", query = "SELECT p FROM PruebaInnodb p WHERE p.cMTSdownIfName = :cMTSdownIfName"), @NamedQuery(name = "PruebaInnodb.findByCMmacAddress", query = "SELECT p FROM PruebaInnodb p WHERE p.pruebaInnodbPK.cMmacAddress = :cMmacAddress"), @NamedQuery(name = "PruebaInnodb.findByCMdocsisMode", query = "SELECT p FROM PruebaInnodb p WHERE p.cMdocsisMode = :cMdocsisMode"), @NamedQuery(name = "PruebaInnodb.findByCMipAddress", query = "SELECT p FROM PruebaInnodb p WHERE p.cMipAddress = :cMipAddress"), @NamedQuery(name = "PruebaInnodb.findByCMcpeIpv4List", query = "SELECT p FROM PruebaInnodb p WHERE p.cMcpeIpv4List = :cMcpeIpv4List"), @NamedQuery(name = "PruebaInnodb.findByRecType", query = "SELECT p FROM PruebaInnodb p WHERE p.recType = :recType"), @NamedQuery(name = "PruebaInnodb.findByRecCreationTime", query = "SELECT p FROM PruebaInnodb p WHERE p.recCreationTime = :recCreationTime"), @NamedQuery(name = "PruebaInnodb.findByServiceIdentifier", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceIdentifier = :serviceIdentifier"), @NamedQuery(name = "PruebaInnodb.findByGateId", query = "SELECT p FROM PruebaInnodb p WHERE p.gateId = :gateId"), @NamedQuery(name = "PruebaInnodb.findByServiceClassName", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceClassName = :serviceClassName"), @NamedQuery(name = "PruebaInnodb.findByServiceDirection", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceDirection = :serviceDirection"), @NamedQuery(name = "PruebaInnodb.findByServiceOctetsPassed", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceOctetsPassed = :serviceOctetsPassed"), @NamedQuery(name = "PruebaInnodb.findByServicePktsPassed", query = "SELECT p FROM PruebaInnodb p WHERE p.servicePktsPassed = :servicePktsPassed"), @NamedQuery(name = "PruebaInnodb.findByServiceSlaDropPkts", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceSlaDropPkts = :serviceSlaDropPkts"), @NamedQuery(name = "PruebaInnodb.findByServiceSlaDelayPkts", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceSlaDelayPkts = :serviceSlaDelayPkts"), @NamedQuery(name = "PruebaInnodb.findByServiceTimeCreated", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceTimeCreated = :serviceTimeCreated"), @NamedQuery(name = "PruebaInnodb.findByServiceTimeActive", query = "SELECT p FROM PruebaInnodb p WHERE p.serviceTimeActive = :serviceTimeActive")})
public class PruebaInnodb implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaInnodbPK pruebaInnodbPK;
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

    public PruebaInnodb() {
    }

    public PruebaInnodb(PruebaInnodbPK pruebaInnodbPK) {
        this.pruebaInnodbPK = pruebaInnodbPK;
    }

    public PruebaInnodb(long timestamp, String cMmacAddress) {
        this.pruebaInnodbPK = new PruebaInnodbPK(timestamp, cMmacAddress);
    }

    public PruebaInnodbPK getPruebaInnodbPK() {
        return pruebaInnodbPK;
    }

    public void setPruebaInnodbPK(PruebaInnodbPK pruebaInnodbPK) {
        this.pruebaInnodbPK = pruebaInnodbPK;
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
        hash += (pruebaInnodbPK != null ? pruebaInnodbPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaInnodb)) {
            return false;
        }
        PruebaInnodb other = (PruebaInnodb) object;
        if ((this.pruebaInnodbPK == null && other.pruebaInnodbPK != null) || (this.pruebaInnodbPK != null && !this.pruebaInnodbPK.equals(other.pruebaInnodbPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prueba.persistencia.PruebaInnodb[pruebaInnodbPK=" + pruebaInnodbPK + "]";
    }

}
