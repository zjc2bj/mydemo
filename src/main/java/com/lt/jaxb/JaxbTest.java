package com.lt.jaxb;

import java.util.Date;

import org.junit.Test;

public class JaxbTest {
    // @Test
    public void test() {

        Child child = new Child();
        child.setcAge(null);
        child.setcName("childchildchild");
        Date date = new Date();
        Person person = new Person("zjc", 12, 1, "131466", date, child);
        String marshal = JAXBUtils.marshal(person);
        System.out.println(marshal);

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><person><age>12</age><big>333</big><birthday>2014-05-21T11:51:21.783+08:00</birthday><child><cName>xz</cName><cAge>1</cAge></child><name>zjc</name><sex>1</sex><tel>131466</tel></person>";
        Person person2 = JAXBUtils.unmarshal(xml, Person.class);
        System.out.println(person2);
        // Map<String, Object> emptyMap = Collections.<String, Object>
        // emptyMap();
    }

    @Test
    public void test2() {
        Child child = new Child();
        child.setcAge("");
        child.setcName("childchildchild");
        String marshal = JAXBUtils.marshal(child);
        System.out.println(marshal);
    }
    
    public static void main(String[] args) {
		String str = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		WXRsp unmarshal = JAXBUtils.unmarshal(str, WXRsp.class);
		System.out.println(unmarshal);
		String marshal = JAXBUtils.marshal(unmarshal);
	    System.out.println(marshal);
	    
	    WXUnifiedorderReqVo reqVo = new WXUnifiedorderReqVo();
		reqVo.setBody("11111111");
		String marshal2 = JAXBUtils.marshal(reqVo);
		System.out.println(marshal2);
	}
}
