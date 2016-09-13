package cn.zjc.util.bean.test;

import java.util.Date;

import cn.zjc.util.bean.annotation.FieldMapping;
import cn.zjc.util.bean.enums.ConvertType;

public class Agency {
    private String assistant; // 销售人员

    @FieldMapping(type = Mechant.class, value = "merchantId")
    private Integer id; // 代理Id

    @FieldMapping(type = Mechant.class, value = "superiorMerchantId")
    private Integer superId; // 上级代理id

    private Agency superiorAgency; // 上级代理

    @FieldMapping(type = Mechant.class, value = "gmtCreated")
    private Date gmtCreated; // 创建时间

    @FieldMapping(type = Mechant.class, value = "gmtModified")
    private Date gmtModified; // 修改时间

    private String name; // 名称

    @FieldMapping(type = Mechant.class, value = "codeName")
    private String codeName; // 客户代码

    private String fullName; // 全名

    private String enName; // 英文名

    private String contactPerson;// 联系人

    private String telephone;// 联系电话

    private String fax;// 传真

    private String mobilePhone;// 固话

    private String email;// 邮箱

    private String zipCode;// 邮编

    @FieldMapping(type = Mechant.class, value = "address", convertType = ConvertType.BOTHWAY)
    private String address;// 详细地址

    @FieldMapping(type = Mechant.class, value = "province")
    private String province;// 省

    private String provinceCode;// 省编码

    @FieldMapping(type = Mechant.class, value = "city")
    private String city;// 市

    private String cityCode;// 市编码

    @FieldMapping(type = Mechant.class, value = "area")
    private String district;// 区

    private String districeCode;// 区编码

    @FieldMapping(type = Mechant.class, value = "createCn")
    private String creatorCn;// 创建人

    @FieldMapping(type = Mechant.class, value = "modifiedCn")
    private String modifierCn;// 修改人

    private Integer status;// 状态
}
