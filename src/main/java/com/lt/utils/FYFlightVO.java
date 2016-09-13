package com.lt.utils;

import java.io.Serializable;
import java.util.HashMap;

import cn.zjc.util.bean.BeanConvertUtil;

public class FYFlightVO implements Serializable {

    // 航班号
    private String FlightNo;

    // 航空公司名称
    private String FlightCompany;

    // 出发地三字码
    private String FlightDepcode;

    // 目的地三字码
    private String FlightArrcode;

    // 计划出发时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightDeptimePlanDate;

    // 计划到达时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightArrtimePlanDate;

    // 预计起飞时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightDeptimeReadyDate;

    // 预计到达时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightArrtimeReadyDate;

    // 实际起飞时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightDeptimeDate;

    // 实际到达时间（yyyy-dd-mm hh:mm:ss格式）
    private String FlightArrtimeDate;

    // 航班状态
    private String FlightState;

    // 候机楼
    private String FlightHTerminal;

    // 接机楼
    private String FlightTerminal;

    // 出发城市名
    private String FlightDep;

    // 到达城市名
    private String FlightArr;

    // 出发机场名
    private String FlightDepAirport;

    // 到达机场名
    private String FlightArrAirport;

    // 包含备降内容节点
    private String alternate_info;

    // 备降机场名
    private String AlternateDepCity;

    // 目的地时区
    private String dst_timezone;

    // 出发地时区
    private String org_timezone;

    // 航班属性（0:国内-国内;1国内-国际;2国内-地区;3:地区-国际;4:国际-国际;5:未知）
    private String fcategory;

    public String getFlightNo() {
        return FlightNo;
    }

    public void setFlightNo(String flightNo) {
        FlightNo = flightNo;
    }

    public String getFlightCompany() {
        return FlightCompany;
    }

    public void setFlightCompany(String flightCompany) {
        FlightCompany = flightCompany;
    }

    public String getFlightDepcode() {
        return FlightDepcode;
    }

    public void setFlightDepcode(String flightDepcode) {
        FlightDepcode = flightDepcode;
    }

    public String getFlightArrcode() {
        return FlightArrcode;
    }

    public void setFlightArrcode(String flightArrcode) {
        FlightArrcode = flightArrcode;
    }

    public String getFlightDeptimePlanDate() {
        return FlightDeptimePlanDate;
    }

    public void setFlightDeptimePlanDate(String flightDeptimePlanDate) {
        FlightDeptimePlanDate = flightDeptimePlanDate;
    }

    public String getFlightArrtimePlanDate() {
        return FlightArrtimePlanDate;
    }

    public void setFlightArrtimePlanDate(String flightArrtimePlanDate) {
        FlightArrtimePlanDate = flightArrtimePlanDate;
    }

    public String getFlightDeptimeReadyDate() {
        return FlightDeptimeReadyDate;
    }

    public void setFlightDeptimeReadyDate(String flightDeptimeReadyDate) {
        FlightDeptimeReadyDate = flightDeptimeReadyDate;
    }

    public String getFlightArrtimeReadyDate() {
        return FlightArrtimeReadyDate;
    }

    public void setFlightArrtimeReadyDate(String flightArrtimeReadyDate) {
        FlightArrtimeReadyDate = flightArrtimeReadyDate;
    }

    public String getFlightDeptimeDate() {
        return FlightDeptimeDate;
    }

    public void setFlightDeptimeDate(String flightDeptimeDate) {
        FlightDeptimeDate = flightDeptimeDate;
    }

    public String getFlightArrtimeDate() {
        return FlightArrtimeDate;
    }

    public void setFlightArrtimeDate(String flightArrtimeDate) {
        FlightArrtimeDate = flightArrtimeDate;
    }

    public String getFlightState() {
        return FlightState;
    }

    public void setFlightState(String flightState) {
        FlightState = flightState;
    }

    public String getFlightHTerminal() {
        return FlightHTerminal;
    }

    public void setFlightHTerminal(String flightHTerminal) {
        FlightHTerminal = flightHTerminal;
    }

    public String getFlightTerminal() {
        return FlightTerminal;
    }

    public void setFlightTerminal(String flightTerminal) {
        FlightTerminal = flightTerminal;
    }

    public String getFlightDep() {
        return FlightDep;
    }

    public void setFlightDep(String flightDep) {
        FlightDep = flightDep;
    }

    public String getFlightArr() {
        return FlightArr;
    }

    public void setFlightArr(String flightArr) {
        FlightArr = flightArr;
    }

    public String getFlightDepAirport() {
        return FlightDepAirport;
    }

    public void setFlightDepAirport(String flightDepAirport) {
        FlightDepAirport = flightDepAirport;
    }

    public String getFlightArrAirport() {
        return FlightArrAirport;
    }

    public void setFlightArrAirport(String flightArrAirport) {
        FlightArrAirport = flightArrAirport;
    }

    public String getAlternate_info() {
        return alternate_info;
    }

    public void setAlternate_info(String alternate_info) {
        this.alternate_info = alternate_info;
    }

    public String getAlternateDepCity() {
        return AlternateDepCity;
    }

    public void setAlternateDepCity(String alternateDepCity) {
        AlternateDepCity = alternateDepCity;
    }

    public String getDst_timezone() {
        return dst_timezone;
    }

    public void setDst_timezone(String dst_timezone) {
        this.dst_timezone = dst_timezone;
    }

    public String getOrg_timezone() {
        return org_timezone;
    }

    public void setOrg_timezone(String org_timezone) {
        this.org_timezone = org_timezone;
    }

    public String getFcategory() {
        return fcategory;
    }

    public void setFcategory(String fcategory) {
        this.fcategory = fcategory;
    }

    public static void main(String[] args) {
        String content = "{\"FlightNo\":\"CZ3101\",\"FlightCompany\":\"中国南方航空股份有限公司\",\"FlightDepcode\":\"CAN\",\"FlightArrcode\":\"PEK\",\"FlightDeptimePlanDate\":\"2015-05-0108:00:00\",\"FlightArrtimePlanDate\":\"2015-05-0110:55:00\",\"FlightDeptimeDate\":\"2015-05-0108:21:00\",\"FlightArrtimeDate\":\"2015-05-0111:04:00\",\"FlightState\":\"到达\",\"FlightHTerminal\":\"B\",\"FlightTerminal\":\"T2\",\"org_timezone\":\"28800\",\"dst_timezone\":\"28800\",\"FlightDep\":\"广州\",\"FlightArr\":\"北京\",\"FlightDepAirport\":\"广州白云国际\",\"FlightArrAirport\":\"北京首都国际\"}";
        HashMap map = JsonClientUtil.jsonTo(content, HashMap.class);
        // FYFlightVO map2Bean = BeanConvertUtil.map2Bean(map,
        // FYFlightVO.class);
        FYFlightVO map2Bean = BeanConvertUtil.map2Bean(map, FYFlightVO.class, null);
        System.out.println(map2Bean);

        // FYFlightVO[] vos = JsonClientUtil.jsonTo(content,
        // FYFlightVO[].class);
        // System.out.println(vos);
    }
}
