package cn.zjc.soa.vo.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindPolicyRecordExtendRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer beginId;

    private String beginTime;

    private String endTime;

    private Integer count;

    public FindPolicyRecordExtendRequest() {
        super();
    }

    public FindPolicyRecordExtendRequest(Integer beginId, String beginTime, String endTime, Integer count) {
        super();
        this.beginId = beginId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.count = count;
    }

    public Integer getBeginId() {
        return beginId;
    }

    public void setBeginId(Integer beginId) {
        this.beginId = beginId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
