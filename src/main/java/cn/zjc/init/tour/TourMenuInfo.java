package cn.zjc.init.tour;

import java.util.List;
import java.util.Map;

public class TourMenuInfo {
    /** 旅游所有菜单 */
    private Map<String, TourMenuRef> allTourMenus;

    /** 旅游所有菜单 */
    private List<String> allTourMenuKeys;

    /** 供应显示菜单 */
    private List<String> providerShowMenus;

    /** 分销显示菜单 */
    private List<String> distributorShowMenus;

    /** 供应过滤菜单 */
    private List<String> providerFilterMenus;

    /** 分销过滤菜单 */
    private List<String> distributorFilterMenus;

    public Map<String, TourMenuRef> getAllTourMenus() {
        return allTourMenus;
    }

    public List<String> getAllTourMenuKeys() {
        return allTourMenuKeys;
    }

    public List<String> getProviderShowMenus() {
        return providerShowMenus;
    }

    public List<String> getDistributorShowMenus() {
        return distributorShowMenus;
    }

    public List<String> getProviderFilterMenus() {
        return providerFilterMenus;
    }

    public List<String> getDistributorFilterMenus() {
        return distributorFilterMenus;
    }

    public void setAllTourMenus(Map<String, TourMenuRef> allTourMenus) {
        this.allTourMenus = allTourMenus;
    }

    public void setAllTourMenuKeys(List<String> allTourMenuKeys) {
        this.allTourMenuKeys = allTourMenuKeys;
    }

    public void setProviderShowMenus(List<String> providerShowMenus) {
        this.providerShowMenus = providerShowMenus;
    }

    public void setDistributorShowMenus(List<String> distributorShowMenus) {
        this.distributorShowMenus = distributorShowMenus;
    }

    public void setProviderFilterMenus(List<String> providerFilterMenus) {
        this.providerFilterMenus = providerFilterMenus;
    }

    public void setDistributorFilterMenus(List<String> distributorFilterMenus) {
        this.distributorFilterMenus = distributorFilterMenus;
    }

}
