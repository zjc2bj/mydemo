package org.spring.demo.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {
    static {
        String file = "conf/spring/applicationContext.xml";
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource(file));
        Object bean = factory.getBean("menu");
        System.out.println(bean);

        // new XmlBeanDefinitionReader(registry)
    }

    public static void main(String[] args) {
        System.out.println("111111111");
    }
}
