package com.lt.jaxb.demo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.lt.jaxb.JAXBUtils;

@XmlRootElement(name = "Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShenHangReqVo {
    @XmlElement(name = "OutOrder")
    private OutOrder outOrder;

    @XmlElement(name = "PolicyInfo")
    private PolicyInfo policyInfo;

    @XmlElementWrapper(name = "Airrutes")
    @XmlElement(name = "Airrute")
    private List<Airrute> airrute;

    @XmlElementWrapper(name = "Passengers")
    @XmlElement(name = "Passenger")
    private List<Passenger> passenger;

    public String generateStrParam() {
        String marshal = JAXBUtils.marshal(this);
        return marshal;
    }

    public OutOrder getOutOrder() {
        return outOrder;
    }

    public PolicyInfo getPolicyInfo() {
        return policyInfo;
    }

    public List<Airrute> getAirrute() {
        return airrute;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public void setOutOrder(OutOrder outOrder) {
        this.outOrder = outOrder;
    }

    public void setPolicyInfo(PolicyInfo policyInfo) {
        this.policyInfo = policyInfo;
    }

    public void setAirrute(List<Airrute> airrute) {
        this.airrute = airrute;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }

    public static void main(String[] args) {
        String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Request><OutOrder><OutSysNo/><OutAuto>1</OutAuto><OutUserNo>G03322</OutUserNo><OutOrderCode>ZH20120803020202</OutOrderCode><OutOrderSmallPnr>GFDGHJ</OutOrderSmallPnr><OutOrderBigPnr>MDFGFD</OutOrderBigPnr><OutOrderPayType>支付宝</OutOrderPayType><OutOrderRemark/><OutOrderChargePrice>6.43</OutOrderChargePrice></OutOrder><PolicyInfo><PolicyType>1</PolicyType><PolicyOutEffTime>2012-02-01~2013-03-01</PolicyOutEffTime><PolicyFlightEffTime>2012-02-01~2013-03-01</PolicyFlightEffTime><PolicyFlightCom>ALL</PolicyFlightCom><PolicyFlightNo>ZH6546</PolicyFlightNo><PolicyFlightClass>YFCB</PolicyFlightClass><PolicyBackCommition>2.5</PolicyBackCommition><PolicyOutType>1</PolicyOutType></PolicyInfo><Airrutes><Airrute><AirStartCity>SZX</AirStartCity><AirEndCity>PEK</AirEndCity><AirFlightNo>0112</AirFlightNo><AirClass>Y</AirClass><AirFlightTime>2012-01-02 02:02:00</AirFlightTime><AirArriveTime>2012-01-03 02:02:00</AirArriveTime><AirType>320</AirType></Airrute><Airrute><AirStartCity>SZX</AirStartCity><AirEndCity>PEK</AirEndCity><AirFlightNo>0112</AirFlightNo><AirClass>Z</AirClass><AirFlightTime>2012-02-02 02:02:00</AirFlightTime><AirArriveTime>2012-02-03 02:02:00</AirArriveTime><AirType>321</AirType></Airrute></Airrutes><Passengers><Passenger><PassName>李长青</PassName><PassType>1</PassType><PassCardCode>45011111111111</PassCardCode><PassTelPhone>1892819555</PassTelPhone><PassSalePrice>1280.0</PassSalePrice><PassBackCommition>2.5</PassBackCommition><PassRealPrice>1248.0</PassRealPrice><PassOilPrice>160</PassOilPrice><PassConstructPrice>50</PassConstructPrice></Passenger><Passenger><PassName>李长青</PassName><PassType>0</PassType><PassCardCode>45011111111111</PassCardCode><PassTelPhone>1892819555</PassTelPhone><PassSalePrice>1280.0</PassSalePrice><PassBackCommition>2.5</PassBackCommition><PassRealPrice>1248.0</PassRealPrice><PassOilPrice>160</PassOilPrice><PassConstructPrice>50</PassConstructPrice></Passenger></Passengers></Request>";
        ShenHangReqVo unmarshal = JAXBUtils.unmarshal(str, ShenHangReqVo.class);

        System.out.println(unmarshal);
        String marshal = JAXBUtils.marshal(unmarshal);
        System.out.println(marshal);
    }

}
