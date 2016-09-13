package cn.zjc.soa.vo.reply;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractReply;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNormalPolicyUsingMd5Reply extends AbstractReply implements Serializable {

    /** 
     * 
     */
    private static final long serialVersionUID = -8249217426625619112L;

    private CreateNormalPolicyUsingMd5Vo createNormalPolicyUsingMd5Vo;

    public CreateNormalPolicyUsingMd5Reply() {
        super();
    }

    public CreateNormalPolicyUsingMd5Reply(long serialNumber) {
        super(serialNumber);
    }

    public CreateNormalPolicyUsingMd5Vo getCreateNormalPolicyUsingMd5Vo() {
        return createNormalPolicyUsingMd5Vo;
    }

    public void setCreateNormalPolicyUsingMd5Vo(CreateNormalPolicyUsingMd5Vo createNormalPolicyUsingMd5Vo) {
        this.createNormalPolicyUsingMd5Vo = createNormalPolicyUsingMd5Vo;
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
