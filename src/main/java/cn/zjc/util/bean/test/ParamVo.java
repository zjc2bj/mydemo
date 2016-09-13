package cn.zjc.util.bean.test;

import cn.zjc.util.bean.annotation.RequestMapping;

/**
 * 合作伙伴网站数据集成参数对象
 * 
 * @author NiuXiaoYu
 * 
 */
public class ParamVo {
    /**
     * 用户类型
     */
    public final static String COMMON_USER = "COMMON_USER";// 一代员工

    public final static String AGENCY_SINGLE_USER = "AGENCY_SINGLE_USER";// 二代超管

    public final static String AGENCY_COMMON_USER = "AGENCY_COMMON_USER";// 二代员工

    public final static String AGENCY_MULTIADMIN_USER = "AGENCY_MULTIADMIN_USER";// 多级分销超管

    public final static String AGENCY_MULTICOMMON_USER = "AGENCY_MULTICOMMON_USER";// 多级分销代理营业员

    @RequestMapping(value = "partner", required = true)
    private String partner = "";// 合作伙伴代码

    private String preUrl = "";// 来源渠道

    @RequestMapping(value = "service", required = true)
    private String service = "";// 服务名称

    @RequestMapping(value = "outer_app_token", required = true)
    private String outerAppToken = "";// 合作伙伴的应用系统代码

    @RequestMapping(value = "outer_login_name", required = true)
    private String outerLoginName = "";// 外部用户登录名

    @RequestMapping("user_login_name")
    private String userLoginName = "";//

    @RequestMapping("user_pass")
    private String userPass;

    @RequestMapping(value = "user_name", required = true)
    private String userName = "";// 用户的姓名

    @RequestMapping("email")
    private String email = "";// Email

    @RequestMapping("phone")
    private String phone = "";// 电话号码

    @RequestMapping("mobilePhone")
    private String mobilePhone = "";// 移动电话

    @RequestMapping(value = "user_type", required = true)
    private String userType = "";// 用户类型(见上面的常量 )

    @RequestMapping("group_id")
    private String groupId = "";// 所属权限

    @RequestMapping("goto_url")
    private String gotoUrl = "";// 51BookURL

    @RequestMapping("return_url")
    private String returnUrl = "";// 重新登录的URL

    @RequestMapping("time_stamp")
    private String timeStamp = "";// 时间戳

    @RequestMapping(value = "input_charset", required = true)
    private String inputCharset = "";// 参数编码字符集，目前只支持UTF-8字符集

    @RequestMapping("pnr_no")
    private String pnr_no;

    @RequestMapping("pnr_txt")
    private String pnr_txt;

    @RequestMapping("pata_txt")
    private String pata_txt;

    @RequestMapping(value = "sign_type", required = true)
    private String signType = "";// 签名方式：目前只支持MD5签名

    @RequestMapping(value = "sign", required = true)
    private String sign = "";// 数字签名

    // 创见二代员工所需参数
    @RequestMapping("subouter_app_token")
    private String subouterAppToken;

    @RequestMapping("subouter_login_name")
    private String subouterLoginName;

    @RequestMapping("subuser_name")
    private String subuserName;

    @RequestMapping("subemail")
    private String subemail;

    @RequestMapping("subphone")
    private String subphone;

    @RequestMapping("submobilePhone")
    private String submobilePhone;

    private Boolean isregister = false;// 是否是新注用户

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getPnr_no() {
        return pnr_no;
    }

    public void setPnr_no(String pnr_no) {
        this.pnr_no = pnr_no;
    }

    public String getPnr_txt() {
        return pnr_txt;
    }

    public void setPnr_txt(String pnr_txt) {
        this.pnr_txt = pnr_txt;
    }

    public String getPata_txt() {
        return pata_txt;
    }

    public void setPata_txt(String pata_txt) {
        this.pata_txt = pata_txt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOuterAppToken() {
        return outerAppToken;
    }

    public void setOuterAppToken(String outerAppToken) {
        this.outerAppToken = outerAppToken;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getOuterLoginName() {
        return outerLoginName;
    }

    public void setOuterLoginName(String outerLoginName) {
        this.outerLoginName = outerLoginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSubouterAppToken() {
        return subouterAppToken;
    }

    public void setSubouterAppToken(String subouterAppToken) {
        this.subouterAppToken = subouterAppToken;
    }

    public String getSubouterLoginName() {
        return subouterLoginName;
    }

    public void setSubouterLoginName(String subouterLoginName) {
        this.subouterLoginName = subouterLoginName;
    }

    public String getSubuserName() {
        return subuserName;
    }

    public void setSubuserName(String subuserName) {
        this.subuserName = subuserName;
    }

    public String getSubemail() {
        return subemail;
    }

    public void setSubemail(String subemail) {
        this.subemail = subemail;
    }

    public String getSubphone() {
        return subphone;
    }

    public void setSubphone(String subphone) {
        this.subphone = subphone;
    }

    public String getSubmobilePhone() {
        return submobilePhone;
    }

    public void setSubmobilePhone(String submobilePhone) {
        this.submobilePhone = submobilePhone;
    }

    public Boolean getIsregister() {
        return isregister;
    }

    public void setIsregister(Boolean isregister) {
        this.isregister = isregister;
    }
}
