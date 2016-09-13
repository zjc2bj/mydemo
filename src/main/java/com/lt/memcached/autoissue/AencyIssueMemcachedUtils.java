/**
 * 
 * Copyright (C)2014 LianTuoTianJi Co.,Ltd. All rights reserved.
 *
 */
package com.lt.memcached.autoissue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.lt.memcached.MemcachedClientAdapter;

/**
 * 自动出票相关缓存服务
 * 
 * @author GuanLichang in 2014-10-30
 * 
 */
@Component
public class AencyIssueMemcachedUtils {

    @Resource
    private MemcachedClientAdapter memcachedClientAdapter;

    private final Integer locacacheTime = 10;// 单位秒

    private final Integer remoteExpireTime = 22 * 24 * 60 * 60;// 单位秒 22天

    private final String delimiter = "|";

    /**
     * 缓存自动出票启用的航司ok
     * 
     * @param agencyID
     * @param airline
     * @param type
     */
    public void updateB2bAutoIssueAirline(Integer agencyID, String airline, Integer type) {
        String airlines = "";
        Object cacheObject = memcachedClientAdapter.get("b2bIssueAirline" + delimiter + agencyID, locacacheTime);
        if (cacheObject != null) {
            airlines = (String) cacheObject;
        }
        if (type != null && AencyIssueMemcachedType.UPDATE == type) {
            if (StringUtils.isNotBlank(airlines) && airlines.indexOf(airline) < 0) {// 缓存有航司，但不包含当前航司
                airlines += delimiter + airline;
            } else if (StringUtils.isBlank(airlines)) {
                airlines = delimiter + airline;
            }
        } else if (type != null && AencyIssueMemcachedType.DELETED == type) {
            if (StringUtils.isNotBlank(airlines) && airlines.indexOf(airline) > -1) {
                airlines = airlines.replace(delimiter + airline, "");
            }
        }
        // memcachedClientAdapter.remove("b2bIssueAirline" + delimiter +
        // agencyID);
        memcachedClientAdapter.put("b2bIssueAirline" + delimiter + agencyID, airlines, remoteExpireTime);
    }

    /**
     * 缓存自动出票启用的航司ok
     * 
     * @param agencyID
     * @param airline
     * @param type
     */
    public void deleteB2bAutoIssueAirlineAll(Integer agencyID) {
        memcachedClientAdapter.remove("b2bIssueAirline" + delimiter + agencyID);
    }

    /**
     * 缓存自动废票启用的航司ok
     * 
     * @param agencyID
     * @param airline
     * @param type
     */
    public void updateB2bAutoInvaliAirline(Integer agencyID, String airline, Integer type) {
        String airlines = "";
        Object cacheObject = memcachedClientAdapter.get("b2bInvaliAirline" + delimiter + agencyID, locacacheTime);
        if (cacheObject != null) {
            airlines = (String) cacheObject;
        }
        if (type != null && AencyIssueMemcachedType.UPDATE == type) {
            if (StringUtils.isNotBlank(airlines) && airlines.indexOf(airline) < 0) {// 缓存有航司，但不包含当前航司
                airlines += delimiter + airline;
            } else if (StringUtils.isBlank(airlines)) {
                airlines = delimiter + airline;
            }
        } else if (type != null && AencyIssueMemcachedType.DELETED == type) {
            if (StringUtils.isNotBlank(airlines) && airlines.indexOf(airline) > -1) {
                airlines = airlines.replace(delimiter + airline, "");
            }
        }
        // memcachedClientAdapter.remove("b2bInvaliAirline" + delimiter +
        // agencyID);
        memcachedClientAdapter.put("b2bInvaliAirline" + delimiter + agencyID, airlines, remoteExpireTime);
    }

    /**
     * 缓存自动废票启用规则（航司）ok
     * 
     * @param agencyID
     * @param airline
     * @param type
     */
    public void updateB2bAutoInvaliRule(Integer agencyID, String airline, Integer type) {

        String invaliAirlines = "";
        Object cacheObject = memcachedClientAdapter.get("b2bInvaliRuleAirline" + delimiter + agencyID, locacacheTime);
        if (cacheObject != null) {
            invaliAirlines = (String) cacheObject;
        }
        if (type != null && AencyIssueMemcachedType.UPDATE == type) {
            if (StringUtils.isNotBlank(invaliAirlines) && invaliAirlines.indexOf(airline) < 0) {// 缓存有航司，但不包含当前航司
                invaliAirlines += delimiter + airline;
            } else if (StringUtils.isBlank(invaliAirlines)) {
                invaliAirlines = delimiter + airline;
            }
        } else if (type != null && AencyIssueMemcachedType.DELETED == type) {
            if (StringUtils.isNotBlank(invaliAirlines) && invaliAirlines.indexOf(airline) > -1) {
                invaliAirlines = invaliAirlines.replace(delimiter + airline, "");
            }
        }
        // memcachedClientAdapter.remove("b2bInvaliRuleAirline" + delimiter +
        // agencyID);
        memcachedClientAdapter.put("b2bInvaliRuleAirline" + delimiter + agencyID, invaliAirlines, remoteExpireTime);
    }

    /**
     * 自动出票规则总开关ok
     * 
     * @param agencyID
     * @param type
     */
    public void updateIssueRuleSwitch(Integer agencyID, Integer type) {
        if (type != null && AencyIssueMemcachedType.UPDATE == type) {
            memcachedClientAdapter.put("b2bIssueRuleSwitch" + delimiter + agencyID, "true", remoteExpireTime);// 开启全自动规则开关
        } else if (type != null && AencyIssueMemcachedType.DELETED == type) {
            memcachedClientAdapter.put("b2bIssueRuleSwitch" + delimiter + agencyID, "false", remoteExpireTime);// 关闭全自动规则开关
        }
    }

    /**
     * 更新自动出票规则缓存ok
     * 
     * @param agencyAutoIssueRule
     * @param type
     */
    /*
     * public void updateIssueRuleByAirline(AgencyAutoIssueRule
     * agencyAutoIssueRule, Integer type) { if (agencyAutoIssueRule == null ||
     * type == null || agencyAutoIssueRule.getId() == null) { return; }
     * 
     * List<AgencyIssueRuleCacheVo> ruleCacheList = new
     * ArrayList<AgencyIssueRuleCacheVo>(); Object cacheObject =
     * memcachedClientAdapter.get( "b2bIssueRuleAirline" + delimiter +
     * agencyAutoIssueRule.getAgencyId() + delimiter +
     * agencyAutoIssueRule.getAirline(), locacacheTime); if (cacheObject !=
     * null) { ruleCacheList = (ArrayList<AgencyIssueRuleCacheVo>) cacheObject;
     * } if (agencyAutoIssueRule.getStatus() != null &&
     * agencyAutoIssueRule.getStatus().intValue() == Status.ON) {
     * AgencyIssueRuleCacheVo agencyIssueRuleCacheVo = new
     * AgencyIssueRuleCacheVo();
     * agencyIssueRuleCacheVo.setRuleId(agencyAutoIssueRule.getId());
     * agencyIssueRuleCacheVo.setArrCodes(agencyAutoIssueRule.getArrCodes());
     * agencyIssueRuleCacheVo.setDepCodes(agencyAutoIssueRule.getDepCodes());
     * agencyIssueRuleCacheVo
     * .setGmtFinished(agencyAutoIssueRule.getGmtFinished());
     * agencyIssueRuleCacheVo
     * .setGmtStarted(agencyAutoIssueRule.getGmtStarted());
     * agencyIssueRuleCacheVo.setSeatCodes(agencyAutoIssueRule.getSeatCodes());
     * agencyIssueRuleCacheVo.setVoageType(agencyAutoIssueRule.getVoageType());
     * 
     * agencyIssueRuleCacheVo.setExceptArrCodes(agencyAutoIssueRule.
     * getExceptArrCodes());// 除外出发城市
     * agencyIssueRuleCacheVo.setExceptDepCodes(agencyAutoIssueRule
     * .getExceptDepCodes());// 除外抵达城市
     * 
     * agencyIssueRuleCacheVo.setFlightNoUseType(agencyAutoIssueRule.
     * getFlightNoUseType());// 航班号适用类型0-所有航班号，1-适用航班号，2-不适用航班号
     * agencyIssueRuleCacheVo
     * .setIncludeFightNoes(agencyAutoIssueRule.getIncludeFightNoes());// 包含航班
     * agencyIssueRuleCacheVo
     * .setExcludeFightNoes(agencyAutoIssueRule.getExcludeFightNoes());// 除外航班
     * 
     * agencyIssueRuleCacheVo.setB2bAirlineAccountId(agencyAutoIssueRule.
     * getB2bAirlineAccountId()); if (ruleCacheList.size() > 0) { for (int i =
     * ruleCacheList.size() - 1; i >= 0; i--) { if
     * (agencyAutoIssueRule.getId().intValue() ==
     * ruleCacheList.get(i).getRuleId().intValue()) { ruleCacheList.remove(i); }
     * } ruleCacheList.add(agencyIssueRuleCacheVo); } else {
     * ruleCacheList.add(agencyIssueRuleCacheVo); } } else { if
     * (ruleCacheList.size() > 0) { for (int i = ruleCacheList.size() - 1; i >=
     * 0; i--) { if (agencyAutoIssueRule.getId().intValue() ==
     * ruleCacheList.get(i).getRuleId().intValue()) { ruleCacheList.remove(i); }
     * } } } memcachedClientAdapter.put("b2bIssueRuleAirline" + delimiter +
     * agencyAutoIssueRule.getAgencyId() + delimiter +
     * agencyAutoIssueRule.getAirline(), ruleCacheList, remoteExpireTime); }
     */

    /**
     * 更新自动出票时间ok
     * 
     * @param agencyID
     * @param autoIssueStartTime
     * @param autoIssueEndtTime
     */
    public void updateAutoIssueTime(Integer agencyID, String autoStartTime, String autoEndtTime) {
        String autoIssueTime = "";
        // 放入代理人全动出票时间
        if ((StringUtils.isBlank(autoStartTime) && StringUtils.isBlank(autoEndtTime))
                || (StringUtils.isNotBlank(autoStartTime) && StringUtils.isNotBlank(autoEndtTime)
                        && autoStartTime.equalsIgnoreCase("null") && autoEndtTime.equalsIgnoreCase("null"))) {
            autoIssueTime = "00:00-23:59";
        } else {
            autoIssueTime = autoStartTime + "-" + autoEndtTime;
        }
        memcachedClientAdapter.put("autoIssueTime" + delimiter + agencyID, autoIssueTime, remoteExpireTime);
    }

    /**
     * 更新自动废票时间ok
     * 
     * @param agencyID
     * @param autoIssueStartTime
     * @param autoIssueEndtTime
     */
    public void updateAutoInvalidateTime(Integer agencyID, String autoStartTime, String autoEndtTime) {
        String autoInvalidateTime = "";
        // 放入代理人全动废票时间
        if ((StringUtils.isBlank(autoStartTime) && StringUtils.isBlank(autoEndtTime))
                || (StringUtils.isNotBlank(autoStartTime) && StringUtils.isNotBlank(autoEndtTime)
                        && autoStartTime.equalsIgnoreCase("null") && autoEndtTime.equalsIgnoreCase("null"))) {
            autoInvalidateTime = "00:00-23:59";
        } else {
            autoInvalidateTime = autoStartTime + "-" + autoEndtTime;
        }
        memcachedClientAdapter.put("autoInvalidateTime" + delimiter + agencyID, autoInvalidateTime, remoteExpireTime);
    }

    /**
     * BSP 自动出票航司OK
     * 
     * @param agencyID
     * @param airlines
     * @param type
     */
    public void updateAllowedBSPAutoIssue(Integer agencyID, String airlines, Integer type) {
        if (StringUtils.isBlank(airlines) || agencyID == null || type == null) {
            return;
        }
        String bspIssueAirlines = "";
        Object cacheObject = memcachedClientAdapter.get("bspIssueAirline" + delimiter + agencyID, locacacheTime);
        if (cacheObject != null) {
            bspIssueAirlines = (String) cacheObject;
        }
        if (type != null && AencyIssueMemcachedType.UPDATE == type) {
            if (StringUtils.isNotBlank(bspIssueAirlines)) {
                String bspIssueAirlinesArr[] = bspIssueAirlines.split("\\,");
                for (int i = 0; i < bspIssueAirlinesArr.length; i++) {
                    if (StringUtils.isNotBlank(bspIssueAirlinesArr[i]) && airlines.indexOf(bspIssueAirlinesArr[i]) > -1) {
                        bspIssueAirlines = bspIssueAirlines.replace("," + bspIssueAirlinesArr[i], "");
                    }
                }
            }
            bspIssueAirlines += "," + airlines;
        } else if (type != null && AencyIssueMemcachedType.DELETED == type) {
            if (StringUtils.isNotBlank(bspIssueAirlines)) {
                String bspIssueAirlinesArr[] = bspIssueAirlines.split("\\,");
                for (int i = 0; i < bspIssueAirlinesArr.length; i++) {
                    if (StringUtils.isNotBlank(bspIssueAirlinesArr[i]) && airlines.indexOf(bspIssueAirlinesArr[i]) > -1) {
                        bspIssueAirlines = bspIssueAirlines.replace("," + bspIssueAirlinesArr[i], "");
                    }
                }
            }
        }
        // memcachedClientAdapter.remove("bspIssueAirline" + delimiter +
        // agencyID);
        memcachedClientAdapter.put("bspIssueAirline" + delimiter + agencyID, bspIssueAirlines, remoteExpireTime);
    }

    /**
     * 更新自动出票账号多营业员or单营业员ok
     * 
     * type=0-单营业员：1：多营业员
     * 
     * @param agencyID
     * @param type
     */
    public void updateAutoB2bAccountType(Integer agencyID, String type) {
        if (StringUtils.isBlank(type)) {
            type = "0";
        }
        memcachedClientAdapter.put("autoB2bAccountType" + delimiter + agencyID, type, remoteExpireTime);
    }

    /**
     * 清空缓存
     * 
     * @param key
     */
    public void removeMemcache(String key) {
        memcachedClientAdapter.remove(key);
    }

    /**
     * 查询缓存
     * 
     * @param key
     * @return
     */
    public String querybcsMemcache(String key) {
        String rspTxt = "返回值为空";
        Object rspObject = memcachedClientAdapter.get(key);
        if (rspObject != null)
            rspTxt = MemcachedClientAdapter.convertObjectToJson(rspObject);
        return rspTxt;
    }

    /**
     * 缓存类型
     * 
     * @author GuanLichang in 2014-10-30
     * 
     */
    public static class AencyIssueMemcachedType {
        // 增加
        public static final int UPDATE = 1;

        // 删除
        public static final int DELETED = 2;

        // 自动出票时间
        public static final int TIMETYPE_AUTOISSUE = 1;

        // 自动废票时间
        public static final int TIMETYPE_INVALIDATE = 2;
    }

    public static void main(String[] args) {
        System.out.println("cq".indexOf("ca") < 0);

        String stra[] = "sss|1".split("\\|");
        System.out.println(stra[1]);
    }

}
