package cn.zjc.soa.vo.base;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

public abstract class AbstractB2BClientRequest {
    private String ltywfz;

    private String serialNumber;

    public String getLtywfz() {
        return ltywfz;
    }

    public void setLtywfz(String ltywfz) {
        this.ltywfz = ltywfz;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public <T extends AbstractB2BClientRequest> String beanToXml() {
        String xmlStr = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter out = new StringWriter();
            marshaller.marshal(this, new StreamResult(out));
            xmlStr = out.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlStr.toString();
    }
}
