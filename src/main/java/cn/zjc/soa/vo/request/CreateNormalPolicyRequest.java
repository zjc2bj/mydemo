package cn.zjc.soa.vo.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNormalPolicyRequest extends AbstractRequest implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -8207461786530260677L;

    private Integer needAuditiong;

    private String otype;

    /**
     * departure, arrival 数组
     */

    private String userCodeName;

    private Integer agencyId;

    private String agencyCodeName;

    public Integer getNeedAuditiong() {
        return needAuditiong;
    }

    public void setNeedAuditiong(Integer needAuditiong) {
        this.needAuditiong = needAuditiong;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getUserCodeName() {
        return userCodeName;
    }

    public void setUserCodeName(String userCodeName) {
        this.userCodeName = userCodeName;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyCodeName() {
        return agencyCodeName;
    }

    public void setAgencyCodeName(String agencyCodeName) {
        this.agencyCodeName = agencyCodeName;
    }
}
