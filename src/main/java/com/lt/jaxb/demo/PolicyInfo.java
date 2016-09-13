package com.lt.jaxb.demo;

import javax.xml.bind.annotation.XmlElement;

/**
 * 政策节点
 */
// @XmlAccessorType(XmlAccessType.FIELD)
public class PolicyInfo {

    private int policyType;// 政策类型，如：1为普通政策、2特为殊政策

    private String policyOutEffTime;// 对应该政策的出票有效期，格式如:2012-02-01~2013-03-01

    private String policyFlightEffTime;// 对应该政策的乘机有效期，格式如:2012-02-01~2013-03-01

    private String policyFlightCom;// 对应该政策的航空公司，ALL或航空公司二字码，多个则用英文逗号隔开

    private String policyFlightNo;// 对应该订单的航班号即可，不能是多个航班号

    private String policyFlightClass;// 对应该政策的舱位

    private Double policyBackCommition;// 对应该政策的返点

    private Integer policyOutType;// 类型，有1为BSP政策或2为B2B政策

    @XmlElement(name = "PolicyType")
    public int getPolicyType() {
        return policyType;
    }

    @XmlElement(name = "PolicyOutEffTime")
    public String getPolicyOutEffTime() {
        return covertNull(policyOutEffTime);
    }

    @XmlElement(name = "PolicyFlightEffTime")
    public String getPolicyFlightEffTime() {
        return covertNull(policyFlightEffTime);
    }

    @XmlElement(name = "PolicyFlightCom")
    public String getPolicyFlightCom() {
        return covertNull(policyFlightCom);
    }

    @XmlElement(name = "PolicyFlightNo")
    public String getPolicyFlightNo() {
        return covertNull(policyFlightNo);
    }

    @XmlElement(name = "PolicyFlightClass")
    public String getPolicyFlightClass() {
        return covertNull(policyFlightClass);
    }

    @XmlElement(name = "PolicyBackCommition")
    public Double getPolicyBackCommition() {
        return policyBackCommition;
    }

    @XmlElement(name = "PolicyOutType")
    public Integer getPolicyOutType() {
        return policyOutType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }

    public void setPolicyOutEffTime(String policyOutEffTime) {
        this.policyOutEffTime = policyOutEffTime;
    }

    public void setPolicyFlightEffTime(String policyFlightEffTime) {
        this.policyFlightEffTime = policyFlightEffTime;
    }

    public void setPolicyFlightCom(String policyFlightCom) {
        this.policyFlightCom = policyFlightCom;
    }

    public void setPolicyFlightNo(String policyFlightNo) {
        this.policyFlightNo = policyFlightNo;
    }

    public void setPolicyFlightClass(String policyFlightClass) {
        this.policyFlightClass = policyFlightClass;
    }

    public void setPolicyBackCommition(Double policyBackCommition) {
        this.policyBackCommition = policyBackCommition;
    }

    public void setPolicyOutType(Integer policyOutType) {
        this.policyOutType = policyOutType;
    }

    private String covertNull(String value) {
        return value == null ? "" : value;
    }
}
