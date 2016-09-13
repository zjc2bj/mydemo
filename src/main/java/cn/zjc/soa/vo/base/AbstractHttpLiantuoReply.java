package cn.zjc.soa.vo.base;

public abstract class AbstractHttpLiantuoReply {

    public static final String RETURN_CODE_SUCCESS = "S";

    public static final String RETURN_CODE_FAILURE = "F";

    /**
     * ������: S���ɹ��� or F ��ʧ�ܣ�
     */
    protected String returnCode;

    /**
     * ��returnCode��F��ʱ�򣬴��ʧ�ܵ�ԭ��
     */
    protected String returnMessage;

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
}
