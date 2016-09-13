package cn.zjc.bean;

import java.util.Date;

@SuppressWarnings("serial")
/**
 * 推单记录：胜意、小太阳、聚合
 * @author zjc
 *
 */
public class ShengYiNotifyRecord implements java.io.Serializable {
    private Integer id;

    private Date gmtCreated;// 创建时间

    private Date gmtDeal;// 处理时间

    private String reqTxt;// 请求内容

    private String rspTxt;// 返回内容

    /**
     * @see com.liantuo.schedule.notify.NotifyConstants
     */
    private Integer status;// 通知状态

    private Integer busType;// 业务类型

    private Integer retryTimes;// 通知次数

    private String outOrderNo;// 外部单号--唯一约束

    private String reqUrl;// 请求地址

    private String remark;// 备注

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ShengYiNotifyRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtDeal() {
        return gmtDeal;
    }

    public void setGmtDeal(Date gmtDeal) {
        this.gmtDeal = gmtDeal;
    }

    public String getReqTxt() {
        return reqTxt;
    }

    public void setReqTxt(String reqTxt) {
        this.reqTxt = reqTxt;
    }

    public String getRspTxt() {
        return rspTxt;
    }

    public void setRspTxt(String rspTxt) {
        this.rspTxt = rspTxt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }
}
