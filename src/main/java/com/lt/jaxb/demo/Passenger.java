package com.lt.jaxb.demo;

import javax.xml.bind.annotation.XmlElement;

/**
 * 乘客信息<br>
 * 多个乘客则多个Passenger节点
 */
// @XmlAccessorType(XmlAccessType.FIELD)
public class Passenger {
    private String passName;// 乘客姓名 not null

    private int passType;// 乘客类型：0为成人，1为儿童，2为婴儿not null

    private String passCardCode;// 证件号最好传，否则自动出票失败

    private String passTelPhone;// 联系电话

    private Double passSalePrice;// 票面价not null

    private Double passBackCommition;// 返点not null

    private Double passRealPrice;// 票面结算价not null

    private Double passOilPrice;// 燃油not null

    private Double passConstructPrice;// 机建 not null

    @XmlElement(name = "PassName")
    public String getPassName() {
        return covertNull(passName);
    }

    @XmlElement(name = "PassType")
    public int getPassType() {
        return passType;
    }

    @XmlElement(name = "PassCardCode")
    public String getPassCardCode() {
        return covertNull(passCardCode);
    }

    @XmlElement(name = "PassTelPhone")
    public String getPassTelPhone() {
        return covertNull(passTelPhone);
    }

    @XmlElement(name = "PassSalePrice")
    public Double getPassSalePrice() {
        return passSalePrice;
    }

    @XmlElement(name = "PassBackCommition")
    public Double getPassBackCommition() {
        return passBackCommition;
    }

    @XmlElement(name = "PassRealPrice")
    public Double getPassRealPrice() {
        return passRealPrice;
    }

    @XmlElement(name = "PassOilPrice")
    public Double getPassOilPrice() {
        return passOilPrice;
    }

    @XmlElement(name = "PassConstructPrice")
    public Double getPassConstructPrice() {
        return passConstructPrice;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public void setPassType(int passType) {
        this.passType = passType;
    }

    public void setPassCardCode(String passCardCode) {
        this.passCardCode = passCardCode;
    }

    public void setPassTelPhone(String passTelPhone) {
        this.passTelPhone = passTelPhone;
    }

    public void setPassSalePrice(Double passSalePrice) {
        this.passSalePrice = passSalePrice;
    }

    public void setPassBackCommition(Double passBackCommition) {
        this.passBackCommition = passBackCommition;
    }

    public void setPassRealPrice(Double passRealPrice) {
        this.passRealPrice = passRealPrice;
    }

    public void setPassOilPrice(Double passOilPrice) {
        this.passOilPrice = passOilPrice;
    }

    public void setPassConstructPrice(Double passConstructPrice) {
        this.passConstructPrice = passConstructPrice;
    }

    private String covertNull(String value) {
        return value == null ? "" : value;
    }
}
