package cn.zjc.soa.vo.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractRequest {

    /**
     * �����ϵͳ��������PolicyOrderRefundContants��
     */
    private String applySystem;

    private Map<String, Object> abstractKryoMap = new HashMap<String, Object>();

    /**
     * ��ˮ��
     */
    private long serialNumber;

    private String currentUserCode = "System";

    /**
     * ����ҵ����飬ȡֵ��0��ʾ��ͨ���飬1-X��ʾ������Ӧ���ܵķ���
     */
    private String ltywfz;

    public String requestToJson() {
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

    @SuppressWarnings("unchecked")
    public static <T extends AbstractRequest> T jsonToRequest(String json, Class<? extends AbstractRequest> c) {
        T request;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerSubtypes(c);
            request = (T) mapper.readValue(json, c);
            return request;
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCurrentUserCode() {
        return currentUserCode;
    }

    public void setCurrentUserCode(String currentUserCode) {
        this.currentUserCode = currentUserCode;
    }

    public void setApplySystem(String applySystem) {
        this.applySystem = applySystem;
    }

    public String getApplySystem() {
        return null;
    }

    public String findApplySystemValue() {
        return applySystem;
    }

    protected boolean checkApplySystem() {
        return true;
    }

    public String getLtywfz() {
        return ltywfz;
    }

    public void setLtywfz(String ltywfz) {
        this.ltywfz = ltywfz;
    }

    public Map<String, Object> getAbstractKryoMap() {
        return abstractKryoMap;
    }

    public void setAbstractKryoMap(Map<String, Object> abstractKryoMap) {
        this.abstractKryoMap = abstractKryoMap;
    }
}
