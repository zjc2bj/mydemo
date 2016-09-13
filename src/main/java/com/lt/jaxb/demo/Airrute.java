package com.lt.jaxb.demo;

import javax.xml.bind.annotation.XmlElement;

/**
 * 航段信息 <br>
 * 多个航段则多个Airrute节点，往返程时，去程排在上面
 */
// @XmlAccessorType(XmlAccessType.FIELD)
public class Airrute {

    private String airStartCity;// 出发城市三字码

    private String airEndCity;// 到达城市三字码

    private String airFlightNo;// 航班号，注意： 不能加上二字码not null

    private String airClass;// 舱位 not null

    private String airFlightTime;// 起飞时间 not null

    private String airArriveTime;// 到达时间 not null

    private String airType;// 机型

    @XmlElement(name = "AirStartCity")
    public String getAirStartCity() {
        return covertNull(airStartCity);
    }

    @XmlElement(name = "AirEndCity")
    public String getAirEndCity() {
        return covertNull(airEndCity);
    }

    @XmlElement(name = "AirFlightNo")
    public String getAirFlightNo() {
        return covertNull(airFlightNo);
    }

    @XmlElement(name = "AirClass")
    public String getAirClass() {
        return covertNull(airClass);
    }

    @XmlElement(name = "AirFlightTime")
    public String getAirFlightTime() {
        return covertNull(airFlightTime);
    }

    @XmlElement(name = "AirArriveTime")
    public String getAirArriveTime() {
        return covertNull(airArriveTime);
    }

    @XmlElement(name = "AirType")
    public String getAirType() {
        return covertNull(airType);
    }

    public void setAirStartCity(String airStartCity) {
        this.airStartCity = airStartCity;
    }

    public void setAirEndCity(String airEndCity) {
        this.airEndCity = airEndCity;
    }

    public void setAirFlightNo(String airFlightNo) {
        this.airFlightNo = airFlightNo;
    }

    public void setAirClass(String airClass) {
        this.airClass = airClass;
    }

    public void setAirFlightTime(String airFlightTime) {
        this.airFlightTime = airFlightTime;
    }

    public void setAirArriveTime(String airArriveTime) {
        this.airArriveTime = airArriveTime;
    }

    public void setAirType(String airType) {
        this.airType = airType;
    }

    private String covertNull(String value) {
        return value == null ? "" : value;
    }
}
