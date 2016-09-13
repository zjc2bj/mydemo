package com.lt.dynamicload;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "integrators")
@XmlAccessorType(XmlAccessType.FIELD)
public class Integrators {
    private List<Integrator> integrator;

    public List<Integrator> getIntegrator() {
        return integrator;
    }

    public void setIntegrator(List<Integrator> integrator) {
        this.integrator = integrator;
    }
}
