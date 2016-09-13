package cn.zjc.soa.vo.reply;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.zjc.soa.vo.base.AbstractReply;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInsertPrAllValidReply extends AbstractReply implements Serializable {

    /**
     * 重要！！当调用分页方法！getInsertPrDynamicValidPs||getInsertPrAllValidPs时！
     * 注意事项平台为了效率没有查询数据的总量！！！！！！也就是说 返回的数据可能存在空list此时返回的数据的size小于单页数的时候
     * ，表示该页是最后一页 listvo.size()<limitCount时最后一页
     */
    private static final long serialVersionUID = 1L;

    /**
     * 备用参数，所有新增加的返回参数只能添加到kryoMap， 同时需定义static final String key_描述 及 get返回方法。如：
     * 
     * private static final String kryoMap_key_result = "result";
     * 
     * public String getKeyResult() { return (String)
     * kryoMap.get(kryoMap_key_result); }
     */
    private Map<String, Object> kryoMap = new HashMap<String, Object>();

    public GetInsertPrAllValidReply() {
        super();
    }

    public Map<String, Object> getKryoMap() {
        return kryoMap;
    }

    public void setKryoMap(Map<String, Object> kryoMap) {
        this.kryoMap = kryoMap;
    }

}
