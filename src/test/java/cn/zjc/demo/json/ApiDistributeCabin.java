package cn.zjc.demo.json;

/**
 * 扫仓分配参数 ApiDistributeCabin Parameter
 */
public class ApiDistributeCabin {
    private String cabin;// 分配的舱位

    private String id;// 扫舱系统主要参数

    private float price;// 舱位票面价

    private int cabinNum;// 分配的舱位数量

    private float discount;// 折扣

    private String levels;// 服务等级

    private int status;// 状态 1、分配成功

    private String newPnrNo;// 换票新Pnr

    private int userCabinNum;// 使用舱位位数

    private String oldId;// 之前低舱信息的id

    private String pnrId;// 扫舱系统主要参数，后面异步通知扫舱服务器接口需要此参数

    public String getCabin() {
        return cabin;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getCabinNum() {
        return cabinNum;
    }

    public float getDiscount() {
        return discount;
    }

    public String getLevels() {
        return levels;
    }

    public int getStatus() {
        return status;
    }

    public String getNewPnrNo() {
        return newPnrNo;
    }

    public int getUserCabinNum() {
        return userCabinNum;
    }

    public String getOldId() {
        return oldId;
    }

    public String getPnrId() {
        return pnrId;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCabinNum(int cabinNum) {
        this.cabinNum = cabinNum;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNewPnrNo(String newPnrNo) {
        this.newPnrNo = newPnrNo;
    }

    public void setUserCabinNum(int userCabinNum) {
        this.userCabinNum = userCabinNum;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public void setPnrId(String pnrId) {
        this.pnrId = pnrId;
    }
}
