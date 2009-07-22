/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.db;

import java.net.Inet4Address;
import java.util.Map;

/**
 *
 * @author brunoli
 */
public class IPDRFormat {
    
    public static final String CONST_CMCPEIPV4LIST = "CMcpeIpv4List";
    public static final String CONST_CMDOCSISMODE = "CMdocsisMode";
    public static final String CONST_CMIPADDRESS = "CMipAddress";
    public static final String CONST_CMMACADDRESS = "CMmacAddress";
    public static final String CONST_CMTSCATVIFINDEX = "CMTScatvIfIndex";
    public static final String CONST_CMTSCATVIFNAME = "CMTScatvIfName";
    public static final String CONST_CMTSDOWNIFNAME = "CMTSdownIfName";
    public static final String CONST_CMTSHOSTNAME = "CMTShostname";
    public static final String CONST_CMTSIPADDRESS = "CMTSipAddress";
    public static final String CONST_CMTSSYSUPTIME = "CMTSsysUpTime";
    public static final String CONST_CMTSUPIFNAME = "CMTSupIfName";
    public static final String CONST_CMTSUPIFTYPE = "CMTSupIfType";
    public static final String CONST_GATEID = "gateId";
    public static final String CONST_RECCREATIONTIME = "RecCreationTime";
    public static final String CONST_RECTYPE = "RecType";
    public static final String CONST_SERVICECLASSNAME = "serviceClassName";
    public static final String CONST_SERVICEDIRECTION = "serviceDirection";
    public static final String CONST_SERVICEIDENTIFIER = "serviceIdentifier";
    public static final String CONST_SERVICEOCTETSPASSED = "serviceOctetsPassed";
    public static final String CONST_SERVICEPKTSPASSED = "servicePktsPassed";
    public static final String CONST_SERVICESLADELAYPKTS = "serviceSlaDelayPkts";
    public static final String CONST_SERVICESLADROPPKTS = "serviceSlaDropPkts";
    public static final String CONST_SERVICETIMEACTIVE = "serviceTimeActive";
    public static final String CONST_SERVICETIMECREATED = "serviceTimeCreated";


    private String CMTShostname;
    private String CMTSipAddress;
    private Long CMTSsysUpTime;
    private String CMTScatvIfName;
    private Long CMTScatvIfIndex;
    private String CMTSupIfName;
    private Integer CMTSupIfType; //Long
    private String CMTSdownIfName;
    private String CMmacAddress;
    private Integer CMdocsisMode;
    private String CMipAddress;
    private String CMcpeIpv4List;   //Byte[]
    private Integer RecType;
    private String RecCreationTime;
    private Long serviceIdentifier;
    private Long gateId;
    private String serviceClassName;
    private Integer serviceDirection;
    private Long serviceOctetsPassed;
    private Long servicePktsPassed;
    private Long serviceSlaDropPkts;
    private Long serviceSlaDelayPkts;
    private Long serviceTimeCreated;
    private Long serviceTimeActive;

    public IPDRFormat() {
    }
    
    public IPDRFormat(Map<String,Object> datos) {
        if(datos.containsKey(CONST_CMTSHOSTNAME)){
           setCMTShostname((String) datos.get(CONST_CMTSHOSTNAME));
        }
        if(datos.containsKey(CONST_CMTSIPADDRESS)){
            setCMTSipAddress(((String) datos.get(CONST_CMTSIPADDRESS)));
        }
        if(datos.containsKey(CONST_CMTSSYSUPTIME)){
            setCMTSsysUpTime((Long) datos.get(CONST_CMTSSYSUPTIME));
        }
        if(datos.containsKey(CONST_CMTSCATVIFNAME)){
            setCMTScatvIfName((String) datos.get(CONST_CMTSCATVIFNAME));
        }
        if(datos.containsKey(CONST_CMTSCATVIFINDEX)){
            setCMTScatvIfIndex((Long) datos.get(CONST_CMTSCATVIFINDEX));
        }
        if(datos.containsKey(CONST_CMTSUPIFNAME)){
            setCMTSupIfName((String) datos.get(CONST_CMTSUPIFNAME));
        }
        if(datos.containsKey(CONST_CMTSUPIFTYPE)){
            setCMTSupIfType((Integer) datos.get(CONST_CMTSUPIFTYPE));
        }
        if(datos.containsKey(CONST_CMTSDOWNIFNAME)){
            setCMTSdownIfName((String) datos.get(CONST_CMTSDOWNIFNAME));
        }
        if(datos.containsKey(CONST_CMMACADDRESS)){
            setCMmacAddress((String) datos.get(CONST_CMMACADDRESS));
        }
        if(datos.containsKey(CONST_CMDOCSISMODE)){
            setCMdocsisMode((Integer) datos.get(CONST_CMDOCSISMODE));
        }
        if(datos.containsKey(CONST_CMIPADDRESS)){
            setCMipAddress(((String) datos.get(CONST_CMIPADDRESS)));
        }
        if(datos.containsKey(CONST_CMCPEIPV4LIST)){
            setCMcpeIpv4List((String) datos.get(CONST_CMCPEIPV4LIST));
        }
        if(datos.containsKey(CONST_RECTYPE)){
            setRecType((Integer) datos.get(CONST_RECTYPE));
        }
        if(datos.containsKey(CONST_RECCREATIONTIME)){
            setRecCreationTime((String) datos.get(CONST_RECCREATIONTIME));
        }
        if(datos.containsKey(CONST_SERVICEIDENTIFIER)){
            setServiceIdentifier((Long) datos.get(CONST_SERVICEIDENTIFIER));
        }
        if(datos.containsKey(CONST_GATEID)){
            setGateId((Long) datos.get(CONST_GATEID));
        }
        if(datos.containsKey(CONST_SERVICECLASSNAME)){
            setServiceClassName((String) datos.get(CONST_SERVICECLASSNAME));
        }
        if(datos.containsKey(CONST_SERVICEDIRECTION)){
            setServiceDirection((Integer) datos.get(CONST_SERVICEDIRECTION));
        }
        if(datos.containsKey(CONST_SERVICEOCTETSPASSED)){
            setServiceOctetsPassed((Long) datos.get(CONST_SERVICEOCTETSPASSED));
        }
        if(datos.containsKey(CONST_SERVICEPKTSPASSED)){
            setServicePktsPassed((Long) datos.get(CONST_SERVICEPKTSPASSED));
        }
        if(datos.containsKey(CONST_SERVICESLADROPPKTS)){
            setServiceSlaDropPkts((Long) datos.get(CONST_SERVICESLADROPPKTS));
        }
        if(datos.containsKey(CONST_SERVICESLADELAYPKTS)){
            setServiceSlaDelayPkts((Long) datos.get(CONST_SERVICESLADELAYPKTS));
        }
        if(datos.containsKey(CONST_SERVICETIMECREATED)){
            setServiceTimeCreated((Long) datos.get(CONST_SERVICETIMECREATED));
        }
        if(datos.containsKey(CONST_SERVICETIMEACTIVE)){
            setServiceTimeActive((Long) datos.get(CONST_SERVICETIMEACTIVE));
        }
    }

    public void inicializeVariablesInNull(){
        CMTShostname = null;
        CMTSipAddress = null;
        CMTSsysUpTime = null;
        CMTScatvIfName = null;
        CMTScatvIfIndex = null;
        CMTSupIfName = null;
        CMTSupIfType = null;
        CMTSdownIfName = null;
        CMmacAddress = null;
        CMdocsisMode = null;
        CMipAddress = null;
        CMcpeIpv4List = null;
        RecType = null;
        RecCreationTime = null;
        serviceIdentifier = null;
        gateId = null;
        serviceClassName = null;
        serviceDirection = null;
        serviceOctetsPassed = null;
        servicePktsPassed = null;
        serviceSlaDropPkts = null;
        serviceSlaDelayPkts = null;
        serviceTimeCreated = null;
        serviceTimeActive = null;
    }


    //getters y setters
    /**
     * @return the CMTShostname
     */
    public String getCMTShostname() {
        return CMTShostname;
    }

    /**
     * @param CMTShostname the CMTShostname to set
     */
    public void setCMTShostname(String CMTShostname) {
        this.CMTShostname = CMTShostname;
    }

    /**
     * @return the CMTSipAddress
     */
    public String getCMTSipAddress() {
        return CMTSipAddress;
    }

    /**
     * @param CMTSipAddress the CMTSipAddress to set
     */
    public void setCMTSipAddress(String CMTSipAddress) {
        this.CMTSipAddress = CMTSipAddress;
    }

    /**
     * @return the CMTSsysUpTime
     */
    public Long getCMTSsysUpTime() {
        return CMTSsysUpTime;
    }

    /**
     * @param CMTSsysUpTime the CMTSsysUpTime to set
     */
    public void setCMTSsysUpTime(Long CMTSsysUpTime) {
        this.CMTSsysUpTime = CMTSsysUpTime;
    }

    /**
     * @return the CMTScatvIfName
     */
    public String getCMTScatvIfName() {
        return CMTScatvIfName;
    }

    /**
     * @param CMTScatvIfName the CMTScatvIfName to set
     */
    public void setCMTScatvIfName(String CMTScatvIfName) {
        this.CMTScatvIfName = CMTScatvIfName;
    }

    /**
     * @return the CMTScatvIfIndex
     */
    public Long getCMTScatvIfIndex() {
        return CMTScatvIfIndex;
    }

    /**
     * @param CMTScatvIfIndex the CMTScatvIfIndex to set
     */
    public void setCMTScatvIfIndex(Long CMTScatvIfIndex) {
        this.CMTScatvIfIndex = CMTScatvIfIndex;
    }

    /**
     * @return the CMTSupIfName
     */
    public String getCMTSupIfName() {
        return CMTSupIfName;
    }

    /**
     * @param CMTSupIfName the CMTSupIfName to set
     */
    public void setCMTSupIfName(String CMTSupIfName) {
        this.CMTSupIfName = CMTSupIfName;
    }

    /**
     * @return the CMTSupIfType
     */
    public Integer getCMTSupIfType() {
        return CMTSupIfType;
    }

    /**
     * @param CMTSupIfType the CMTSupIfType to set
     */
    public void setCMTSupIfType(Integer CMTSupIfType) {
        this.CMTSupIfType = CMTSupIfType;
    }

    /**
     * @return the CMTSdownIfName
     */
    public String getCMTSdownIfName() {
        return CMTSdownIfName;
    }

    /**
     * @param CMTSdownIfName the CMTSdownIfName to set
     */
    public void setCMTSdownIfName(String CMTSdownIfName) {
        this.CMTSdownIfName = CMTSdownIfName;
    }

    /**
     * @return the CMmacAddress
     */
    public String getCMmacAddress() {
        return CMmacAddress;
    }

    /**
     * @param CMmacAddress the CMmacAddress to set
     */
    public void setCMmacAddress(String CMmacAddress) {
        this.CMmacAddress = CMmacAddress;
    }

    /**
     * @return the CMdocsisMode
     */
    public Integer getCMdocsisMode() {
        return CMdocsisMode;
    }

    /**
     * @param CMdocsisMode the CMdocsisMode to set
     */
    public void setCMdocsisMode(Integer CMdocsisMode) {
        this.CMdocsisMode = CMdocsisMode;
    }

    /**
     * @return the CMipAddress
     */
    public String getCMipAddress() {
        return CMipAddress;
    }

    /**
     * @param CMipAddress the CMipAddress to set
     */
    public void setCMipAddress(String CMipAddress) {
        this.CMipAddress = CMipAddress;
    }

    /**
     * @return the CMcpeIpv4List
     */
    public String getCMcpeIpv4List() {
        return CMcpeIpv4List;
    }

    /**
     * @param CMcpeIpv4List the CMcpeIpv4List to set
     */
    public void setCMcpeIpv4List(String CMcpeIpv4List) {
        this.CMcpeIpv4List = CMcpeIpv4List;
    }

    /**
     * @return the RecType
     */
    public Integer getRecType() {
        return RecType;
    }

    /**
     * @param RecType the RecType to set
     */
    public void setRecType(Integer RecType) {
        this.RecType = RecType;
    }

    /**
     * @return the RecCreationTime
     */
    public String getRecCreationTime() {
        return RecCreationTime;
    }

    /**
     * @param RecCreationTime the RecCreationTime to set
     */
    public void setRecCreationTime(String RecCreationTime) {
        this.RecCreationTime = RecCreationTime;
    }

    /**
     * @return the serviceIdentifier
     */
    public Long getServiceIdentifier() {
        return serviceIdentifier;
    }

    /**
     * @param serviceIdentifier the serviceIdentifier to set
     */
    public void setServiceIdentifier(Long serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    /**
     * @return the gateId
     */
    public Long getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    /**
     * @return the serviceClassName
     */
    public String getServiceClassName() {
        return serviceClassName;
    }

    /**
     * @param serviceClassName the serviceClassName to set
     */
    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    /**
     * @return the serviceDirection
     */
    public Integer getServiceDirection() {
        return serviceDirection;
    }

    /**
     * @param serviceDirection the serviceDirection to set
     */
    public void setServiceDirection(Integer serviceDirection) {
        this.serviceDirection = serviceDirection;
    }

    /**
     * @return the serviceOctetsPassed
     */
    public Long getServiceOctetsPassed() {
        return serviceOctetsPassed;
    }

    /**
     * @param serviceOctetsPassed the serviceOctetsPassed to set
     */
    public void setServiceOctetsPassed(Long serviceOctetsPassed) {
        this.serviceOctetsPassed = serviceOctetsPassed;
    }

    /**
     * @return the servicePktsPassed
     */
    public Long getServicePktsPassed() {
        return servicePktsPassed;
    }

    /**
     * @param servicePktsPassed the servicePktsPassed to set
     */
    public void setServicePktsPassed(Long servicePktsPassed) {
        this.servicePktsPassed = servicePktsPassed;
    }

    /**
     * @return the serviceSlaDropPkts
     */
    public Long getServiceSlaDropPkts() {
        return serviceSlaDropPkts;
    }

    /**
     * @param serviceSlaDropPkts the serviceSlaDropPkts to set
     */
    public void setServiceSlaDropPkts(Long serviceSlaDropPkts) {
        this.serviceSlaDropPkts = serviceSlaDropPkts;
    }

    /**
     * @return the serviceSlaDelayPkts
     */
    public Long getServiceSlaDelayPkts() {
        return serviceSlaDelayPkts;
    }

    /**
     * @param serviceSlaDelayPkts the serviceSlaDelayPkts to set
     */
    public void setServiceSlaDelayPkts(Long serviceSlaDelayPkts) {
        this.serviceSlaDelayPkts = serviceSlaDelayPkts;
    }

    /**
     * @return the serviceTimeCreated
     */
    public Long getServiceTimeCreated() {
        return serviceTimeCreated;
    }

    /**
     * @param serviceTimeCreated the serviceTimeCreated to set
     */
    public void setServiceTimeCreated(Long serviceTimeCreated) {
        this.serviceTimeCreated = serviceTimeCreated;
    }

    /**
     * @return the serviceTimeActive
     */
    public Long getServiceTimeActive() {
        return serviceTimeActive;
    }

    /**
     * @param serviceTimeActive the serviceTimeActive to set
     */
    public void setServiceTimeActive(Long serviceTimeActive) {
        this.serviceTimeActive = serviceTimeActive;
    }


}
