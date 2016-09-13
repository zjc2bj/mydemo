package com.lt.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Child {

    private String cName;

    private String cAge;

    public Child() {
        super();
    }

    @Override
    public String toString() {
        return "  <cName=" + this.cName + "|cAge=" + this.cAge + "> || ";
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @XmlElement
    public String getcAge() {
        return cAge;
    }

    public void setcAge(String cAge) {
        this.cAge = cAge;
    }
}
