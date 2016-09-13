package cn.zjc.demo.json;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.zjc.util.bean.BeanConvertUtil;

import com.lt.utils.JsonClientUtil;

/**
 * 扫仓分配请求信息
 */
public class DistributeCabinRequest {
    private String org;// 出发地3字码

    private String dst;// 目的地3字码

    private Date orgDate;// 起飞日期，格式：yyyy-MM-dd

    private String airline;// 航司2字码

    private String cabin;// 舱位(A-Z)单字母

    private String flightNo;// 航班号

    private float price;// 票面价

    private String levels;// 服务等级

    private String pnrNo;// 小编码

    private String pnrId;// 客户pnr唯一标识(小编码有可能重复，避免串号）

    private int passNum;// 当前pnr的乘客人数

    private String officeNo;// offcie号

    private String orderGroupNo;// 订单组号

    private boolean isTheSameDay;// 是否是非当天pnr

    private float priceSpread;// 票面价差价

    private float invTicketHandlingCharge;// 非当天手续费

    private Date ticketTime;// 出票时间

    private String buyerName;// 采购方

    private String rule;// 废退改签规则

    private float ratio;// 废退改签费率

    /**
     * 扫舱结果信息，详情请参照 {@link ApiDistributeCabin}<br>
     * 该节点使用json转换异常 进行了特殊处理 需要再优化处理
     */
    private List<ApiDistributeCabin> distributeCabins;

    public String getOrg() {
        return org;
    }

    public String getDst() {
        return dst;
    }

    public Date getOrgDate() {
        return orgDate;
    }

    public String getAirline() {
        return airline;
    }

    public String getCabin() {
        return cabin;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public float getPrice() {
        return price;
    }

    public String getLevels() {
        return levels;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public String getPnrId() {
        return pnrId;
    }

    public int getPassNum() {
        return passNum;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public String getOrderGroupNo() {
        return orderGroupNo;
    }

    public boolean getIsTheSameDay() {
        return isTheSameDay;
    }

    public float getPriceSpread() {
        return priceSpread;
    }

    public float getInvTicketHandlingCharge() {
        return invTicketHandlingCharge;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getRule() {
        return rule;
    }

    public float getRatio() {
        return ratio;
    }

    public List<ApiDistributeCabin> getDistributeCabins() {
        return distributeCabins;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public void setOrgDate(Object orgDate) {
        Date obj2Date = BeanConvertUtil.obj2Date(orgDate);
        this.orgDate = obj2Date;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setCabin(String cabins) {
        this.cabin = cabins;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public void setPnrId(String pnrId) {
        this.pnrId = pnrId;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public void setOrderGroupNo(String orderGroupNo) {
        this.orderGroupNo = orderGroupNo;
    }

    public void setIsTheSameDay(boolean isTheSameDay) {
        this.isTheSameDay = isTheSameDay;
    }

    public void setPriceSpread(float priceSpread) {
        this.priceSpread = priceSpread;
    }

    public void setInvTicketHandlingCharge(float invTicketHandlingCharge) {
        this.invTicketHandlingCharge = invTicketHandlingCharge;
    }

    public void setTicketTime(Object ticketTime) {
        Date obj2Date = BeanConvertUtil.obj2Date(orgDate);
        this.ticketTime = obj2Date;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setDistributeCabins(List<ApiDistributeCabin> distributeCabins) {
        // List<ApiDistributeCabin> list =
        // BeanConvertUtil.generateList(distributeCabins,
        // ApiDistributeCabin.class);
        this.distributeCabins = distributeCabins;
    }

    public static void main(String[] args) throws Exception {
        String parameter = "[{\"airline\":\"FM\",\"cabin\":\"Y\",\"dst\":\"TXN\",\"flightNo\":\"9265\",\"invTicketHandlingCharge\":29,\"isTheSameDay\":false,\"levels\":\"Y\",\"officeNo\":\"CKG187\",\"orderGroupNo\":\"141209220827982228\",\"org\":\"SHA\",\"orgDate\":\"2015-02-11 14:10:00\",\"passNum\":1,\"pnrId\":\"947421\",\"pnrNo\":\"JX8KRT\",\"price\":580,\"priceSpread\":30,\"ratio\":0.05,\"distributeCabins\":[{\"cabin\":\"E\",\"cabinNum\":1,\"discount\":8.28,\"id\":\"000000004b34901c014b385a1e927767\",\"levels\":\"Y\",\"pnrId\":\"000000004b34901c014b366cab390828\",\"price\":480,\"status\":1,\"userCabinNum\":0}]}]";
        long start = System.currentTimeMillis();
        test2(parameter);
        System.out.println("cost=" + (System.currentTimeMillis() - start));

    }

    private static void test1(String parameter) throws IllegalAccessException, InvocationTargetException {
        ArrayList list = JsonClientUtil.jsonTo(parameter, ArrayList.class);
        System.out.println(list);

        for (Object object : list) {
            DistributeCabinRequest distributeCabinRequest = new DistributeCabinRequest();
            // Map map = new HashMap();
            // BeanUtils.populate(map, (Map) object);
            //
            // BeanUtils.copyProperties(distributeCabinRequest, map);
            BeanUtils.copyProperties(distributeCabinRequest, object);
            System.out.println(object);
            System.out.println(object instanceof Map);
            System.out.println(distributeCabinRequest.getDistributeCabins().get(0).getCabin());
        }
    }

    private static void test2(String parameter) throws IllegalAccessException, InvocationTargetException {
        DistributeCabinRequest[] list = JsonClientUtil.jsonTo(parameter, DistributeCabinRequest[].class);
        System.out.println(list);

        for (DistributeCabinRequest distributeCabinRequest : list) {
            System.out.println(distributeCabinRequest);
            System.out.println(distributeCabinRequest.getDistributeCabins().get(0).getCabin());
        }
    }
}
