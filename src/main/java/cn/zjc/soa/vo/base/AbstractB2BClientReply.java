package cn.zjc.soa.vo.base;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public abstract class AbstractB2BClientReply {
    public static final String RETURN_CODE_SUCCESS = "S";

    public static final String RETURN_CODE_FAILURE = "F";

    /**
     * // �����룬 S���ɹ��� or F ��ʧ�ܣ�
     */
    protected String returnCode;

    /**
     * // ��returnCode��F��ʱ�򣬴��ʧ�ܵ�ԭ��
     */
    protected String returnMessage;

    /**
     * // ��returnCode��F��ʱ�򣬴���쳣��ջ��Ϣ����������ļ��
     */
    protected String returnStackTrace;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnStackTrace() {
        return returnStackTrace;
    }

    public void setReturnStackTrace(String returnStackTrace) {
        this.returnStackTrace = returnStackTrace;
    }

    public <T extends AbstractB2BClientReply> T xmlToBean(String xml) {
        T reply;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reply = (T) unmarshaller.unmarshal(new StringReader(xml));

            return reply;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �Ƿ���ʧ�ܵ�reply
     * 
     * @return
     */
    public Boolean isFailureReply() {
        return this.getReturnCode() != null && this.getReturnCode().equals(RETURN_CODE_FAILURE);
    }
}
