package cn.zjc.init.style;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 每个模板配置文件对应实体
 * 
 * @author zjc
 */
public class StyleRef implements Serializable {
    private static final long serialVersionUID = -4311913752538581610L;

    /** 最新推荐 */
    private List<TemplateRef> latestLoginTemplates;

    /** 更多精彩-公共模板 */
    private List<TemplateRef> commonLoginTemplates;

    /** 更多精彩-供应特有模板 */
    private Map<String, List<TemplateRef>> agencyLoginTemplates;

    /** 内页模版--公共模板 */
    private List<TemplateRef> commonInnerTemplates;

    /** 内页模版--供应特有 */
    private Map<String, List<TemplateRef>> agencyInnerTemplates;

    // ---------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------

    /** 返回所有登录模板集合 */
    public ArrayList<TemplateRef> getLoginTemplates(String codeName) {
        ArrayList<TemplateRef> agencyLoginList = new ArrayList<TemplateRef>();
        agencyLoginList.addAll(commonLoginTemplates);
        if (agencyLoginTemplates.get(codeName) != null)
            agencyLoginList.addAll(agencyLoginTemplates.get(codeName));
        return agencyLoginList;
    }

    /** 返回所有内页模板集合 */
    public ArrayList<TemplateRef> getInnerTemplates(String codeName) {
        ArrayList<TemplateRef> agencyInnerList = new ArrayList<TemplateRef>();
        agencyInnerList.addAll(commonInnerTemplates);
        if (agencyInnerTemplates.get(codeName) != null) {
            agencyInnerList.addAll(agencyInnerTemplates.get(codeName));
        }
        return agencyInnerList;
    }

    /**
     * 根据loginStyle获取对应的TemplateRef
     */
    public TemplateRef getLoginTemplatesByLoginStyle(String loginStyle) {
        for (TemplateRef templateRef : latestLoginTemplates) {
            if (templateRef.getLoginStyle().equals(loginStyle.trim()))
                return templateRef;
        }
        for (TemplateRef templateRef : commonLoginTemplates) {
            if (templateRef.getLoginStyle().equals(loginStyle.trim()))
                return templateRef;
        }

        for (List<TemplateRef> list : agencyLoginTemplates.values()) {
            for (TemplateRef templateRef : list) {
                if (templateRef.getLoginStyle().equals(loginStyle.trim()))
                    return templateRef;
            }
        }
        return null;
    }

    public List<TemplateRef> getLatestLoginTemplates() {
        return latestLoginTemplates;
    }

    public List<TemplateRef> getCommonLoginTemplates() {
        return commonLoginTemplates;
    }

    public Map<String, List<TemplateRef>> getAgencyLoginTemplates() {
        return agencyLoginTemplates;
    }

    public List<TemplateRef> getCommonInnerTemplates() {
        return commonInnerTemplates;
    }

    public Map<String, List<TemplateRef>> getAgencyInnerTemplates() {
        return agencyInnerTemplates;
    }

    public void setLatestLoginTemplates(List<TemplateRef> latestLoginTemplates) {
        this.latestLoginTemplates = latestLoginTemplates;
    }

    public void setCommonLoginTemplates(List<TemplateRef> commonLoginTemplates) {
        this.commonLoginTemplates = commonLoginTemplates;
    }

    public void setAgencyLoginTemplates(Map<String, List<TemplateRef>> agencyLoginTemplates) {
        this.agencyLoginTemplates = agencyLoginTemplates;
    }

    public void setCommonInnerTemplates(List<TemplateRef> commonInnerTemplates) {
        this.commonInnerTemplates = commonInnerTemplates;
    }

    public void setAgencyInnerTemplates(Map<String, List<TemplateRef>> agencyInnerTemplates) {
        this.agencyInnerTemplates = agencyInnerTemplates;
    }
}
