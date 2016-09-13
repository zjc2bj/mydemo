package cn.zjc.soa.vo.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUnsuspended4SystemRequest extends AbstractRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String airlineCodes;

    private Integer policyType;

    private Integer productType;

    private Integer bussUnit;

    private Integer agencyId;

    public String getAirlineCodes() {
        return airlineCodes;
    }

    public void setAirlineCodes(String airlineCodes) {
        this.airlineCodes = airlineCodes;
    }

    public Integer getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getBussUnit() {
        return bussUnit;
    }

    public void setBussUnit(Integer bussUnit) {
        this.bussUnit = bussUnit;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public UpdateUnsuspended4SystemRequest(String airlineCodes, Integer policyType, Integer productType,
            Integer bussUnit, Integer agencyId) {
        super();
        this.airlineCodes = airlineCodes;
        this.policyType = policyType;
        this.productType = productType;
        this.bussUnit = bussUnit;
        this.agencyId = agencyId;
    }

    public UpdateUnsuspended4SystemRequest() {
        super();
    }
}
