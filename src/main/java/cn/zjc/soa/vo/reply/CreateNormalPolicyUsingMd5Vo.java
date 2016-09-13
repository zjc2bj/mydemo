package cn.zjc.soa.vo.reply;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNormalPolicyUsingMd5Vo {

    private Integer oldPolicyRecordId; // 待修改政策id;

    private Integer newPolicyRecordId; // 新政策id

    private Integer existPolicyRecordId; // 已存在的政策id，冲突政策id

    public Integer getOldPolicyRecordId() {
        return oldPolicyRecordId;
    }

    public void setOldPolicyRecordId(Integer oldPolicyRecordId) {
        this.oldPolicyRecordId = oldPolicyRecordId;
    }

    public Integer getNewPolicyRecordId() {
        return newPolicyRecordId;
    }

    public void setNewPolicyRecordId(Integer newPolicyRecordId) {
        this.newPolicyRecordId = newPolicyRecordId;
    }

    public Integer getExistPolicyRecordId() {
        return existPolicyRecordId;
    }

    public void setExistPolicyRecordId(Integer existPolicyRecordId) {
        this.existPolicyRecordId = existPolicyRecordId;
    }
}
