package com.lt.kryo;

import org.apache.commons.beanutils.BeanUtils;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer.Tag;

public class KyroBean2 {

    @Tag(value = 0)
    private String name;

    @Tag(value = 1)
    private String age;

    @Tag(value = 2)
    private String birthday;

    @Deprecated
    @Tag(value = 3)
    private String adress;

    public KyroBean2(String name, String age, String birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        try {
            return BeanUtils.describe(this).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "null";
    }
}
