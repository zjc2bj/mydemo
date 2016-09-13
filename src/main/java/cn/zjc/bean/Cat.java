package cn.zjc.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Cat {
    private String name;

    private int age;

    private Date birthday;

    private Double price;

    private boolean isalive;

    private float weight;

    private Map<String, String> map;

    private String[] values;

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public List<String> getList() {
        return list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private List<String> list;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isIsalive() {
        return isalive;
    }

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
        System.out.println("cost=" + (System.currentTimeMillis() - start));
    }
}
