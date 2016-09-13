package com.lt.dynamicload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Integrator {
    private int code;

    private String name;

    private String channel;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
