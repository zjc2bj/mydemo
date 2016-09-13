package cn.zjc.soa.vo.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInsertPrDynamicValidRequest extends AbstractRequest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String lastUpdateTime;

    private int limitCount = 10000;// 防止sql被杀，每次只查一万条

    private int limitStart = 0;// 分页从0开始

    public GetInsertPrDynamicValidRequest() {
        super();
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
