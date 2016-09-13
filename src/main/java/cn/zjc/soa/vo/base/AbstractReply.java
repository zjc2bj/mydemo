package cn.zjc.soa.vo.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractReply {
    public static final String RETURN_CODE_SUCCESS = "S";

    public static final String RETURN_CODE_FAILURE = "F";

    /**
     * ��ˮ��
     */
    private long serialNumber;

    /**
     * ���ؽ��CODE
     */
    private String returnCode;

    /**
     * ���ؽ�������
     */
    private String returnMessage;

    private Map<String, Object> abstractKryoMap = new HashMap<String, Object>();

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

    @SuppressWarnings("unchecked")
    public static <T extends AbstractReply> T jsonToReply(String json, Class<? extends AbstractReply> c) {
        T reply;
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerSubtypes(c);
            reply = (T) mapper.readValue(json, c);
            return reply;
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractReply> T jsonToReply2(String json, Class<? extends AbstractReply> c) {
        T reply;
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            mapper.getSerializationConfig().setDateFormat(df);
            mapper.getDeserializationConfig().setDateFormat(df);
            mapper.registerSubtypes(c);
            reply = (T) mapper.readValue(json, c);
            return reply;
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public String replyToJson() {
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerSubtypes(this.getClass());
            json = mapper.writeValueAsString(this);
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return json;
    }

    public String replyToJson2() {
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            mapper.getSerializationConfig().setDateFormat(df);
            mapper.getDeserializationConfig().setDateFormat(df);

            mapper.registerSubtypes(this.getClass());
            json = mapper.writeValueAsString(this);
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return json;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public AbstractReply(long serialNumber) {
        super();
        this.serialNumber = serialNumber;
        this.returnCode = AbstractReturnCodeConstant.SYS_SUCCESS;
    }

    public AbstractReply() {
        super();
        this.returnCode = AbstractReturnCodeConstant.SYS_SUCCESS;
    }

    public boolean isSuccess() {
        if (this.returnCode.equals(AbstractReturnCodeConstant.SYS_SUCCESS)) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Object> getAbstractKryoMap() {
        return abstractKryoMap;
    }

    public void setAbstractKryoMap(Map<String, Object> abstractKryoMap) {
        this.abstractKryoMap = abstractKryoMap;
    }

    public String getReturnStackTrace() {
        Object obj = this.abstractKryoMap.get("returnStackTrace");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public void setReturnStackTrace(String returnStackTrace) {
        if (returnStackTrace != null) {
            this.abstractKryoMap.put("returnStackTrace", returnStackTrace);
        }
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
