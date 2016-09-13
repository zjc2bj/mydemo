package com.lt.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import cn.zjc.util.DateTools;

@XmlRootElement
public class Person extends People {

    private String name;

    private int age;

    private int sex;

    private String tel;

    private Date birthday;

    private Child child;

    /**
     * 
     */
    public Person() {
        super("河南");
    }

    /**
     * @param name
     * @param age
     * @param sex
     * @param tel
     * @param birthday
     * @param child
     */
    public Person(String name, int age, int sex, String tel, Date birthday, Child child) {
        super("河南");
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.birthday = birthday;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel + "aaaaaaaaaaa";
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return this.name + " || " + this.age + " || " + this.sex + " || " + this.tel + " || " + this.child
                + DateTools.date2String(this.birthday, "yyyy-MM-dd HH:mm:SS");
    }
}
