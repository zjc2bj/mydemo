package com.lt.jaxb.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lt.jaxb.JAXBUtils;

/**
 * 订单基本信息
 */
// @XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class OutOrder {

    private String outSysNo;// 平台代码

    private int outAuto;// 出票标识，1是自动出票，0否

    private String outUserNo;// 平台帐号 not null

    private String outOrderCode;// 外部系统订单号, 格式一般为：字母+时间戳 not null

    private String outOrderSmallPnr;// 小编，PNR号，长度6位not null

    private String outOrderBigPnr;// 大编，PNR号，长度6位 not null

    private String outOrderPayType;// 支付方式名称, not null

    private String outOrderRemark;// 备注信息

    private String outOrderChargePrice;// 支付手续费（整个订单总的手续费）not null

    @XmlElement(name = "OutSysNo")
    public String getOutSysNo() {
        return covertNull(outSysNo);
    }

    @XmlElement(name = "OutAuto")
    public int getOutAuto() {
        return outAuto;
    }

    @XmlElement(name = "OutUserNo")
    public String getOutUserNo() {
        return covertNull(outUserNo);
    }

    @XmlElement(name = "OutOrderCode")
    public String getOutOrderCode() {
        return covertNull(outOrderCode);
    }

    @XmlElement(name = "OutOrderSmallPnr")
    public String getOutOrderSmallPnr() {
        return covertNull(outOrderSmallPnr);
    }

    @XmlElement(name = "OutOrderBigPnr")
    public String getOutOrderBigPnr() {
        return covertNull(outOrderBigPnr);
    }

    @XmlElement(name = "OutOrderPayType")
    public String getOutOrderPayType() {
        return covertNull(outOrderPayType);
    }

    @XmlElement(name = "OutOrderRemark")
    public String getOutOrderRemark() {
        return covertNull(outOrderRemark);
    }

    @XmlElement(name = "OutOrderChargePrice")
    public String getOutOrderChargePrice() {
        return covertNull(outOrderChargePrice);
    }

    public void setOutSysNo(String outSysNo) {
        this.outSysNo = outSysNo;
    }

    public void setOutAuto(int outAuto) {
        this.outAuto = outAuto;
    }

    public void setOutUserNo(String outUserNo) {
        this.outUserNo = outUserNo;
    }

    public void setOutOrderCode(String outOrderCode) {
        this.outOrderCode = outOrderCode;
    }

    public void setOutOrderSmallPnr(String outOrderSmallPnr) {
        this.outOrderSmallPnr = outOrderSmallPnr;
    }

    public void setOutOrderBigPnr(String outOrderBigPnr) {
        this.outOrderBigPnr = outOrderBigPnr;
    }

    public void setOutOrderPayType(String outOrderPayType) {
        this.outOrderPayType = outOrderPayType;
    }

    public void setOutOrderRemark(String outOrderRemark) {
        this.outOrderRemark = outOrderRemark;
    }

    public void setOutOrderChargePrice(String outOrderChargePrice) {
        this.outOrderChargePrice = outOrderChargePrice;
    }

    private String covertNull(String value) {
        return value == null ? "" : value;
    }

    public static void main(String[] args) {
        OutOrder order = new OutOrder();
        order.setOutAuto(1);
        order.setOutOrderBigPnr("");
        order.setOutUserNo(null);

        System.out.println(JAXBUtils.marshal(order));

    }
}
