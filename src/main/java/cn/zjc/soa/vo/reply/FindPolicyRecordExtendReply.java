package cn.zjc.soa.vo.reply;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractReply;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindPolicyRecordExtendReply extends AbstractReply implements Serializable {

    private static final long serialVersionUID = 1L;

    public FindPolicyRecordExtendReply() {
        super();
    }

    /**
     * 备用参数，所有新增加的返回参数只能添加到kryoMap， 同时需定义static final String key_描述 及 get返回方法。如：
     * 
     * private static final String kryoMap_key_result = "result";
     * 
     * public String getKeyResult() { return (String)
     * kryoMap.get(kryoMap_key_result); }
     */
    private Map<String, Object> kryoMap = new HashMap<String, Object>();

    public Map<String, Object> getKryoMap() {
        return kryoMap;
    }

    public void setKryoMap(Map<String, Object> kryoMap) {
        this.kryoMap = kryoMap;
    }
}
