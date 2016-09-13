package cn.zjc.soa.vo.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.utils.HttpClientUtil;
import com.lt.utils.PropertyUtil;

public abstract class AbstractClient {
    private static final Logger logger = LoggerFactory.getLogger(AbstractClient.class);

    private static String BASE_URL = PropertyUtil.getProperty("b2b.host", "/config/properties/servers.properties");

    private static String LOGSYS_URL = PropertyUtil.getProperty("logsys.host", "/config/properties/servers.properties");

    private static final String WS_REQUEST_KEY = "req";

    private static final String NGX_GROUP_TYPE = "ltywfz";

    private static final String ENCODING_UTF8 = "UTF-8";

    /**
     * 20 ��
     */
    public static final Integer TIME_OUT = 20 * 1000;

    public static final Integer TIME_OUT_30 = 30 * 1000;

    public static final Integer TIME_OUT_60 = 60 * 1000;

    public static final Integer TIME_OUT_120 = 120 * 1000;

    public static final Integer TIME_OUT_600 = 600 * 1000;

    /**
     * ���request��ȡָ��url��B2B����
     * 
     * @param request
     *            ���������� �ǿ�
     * @param replyClass
     *            ���ص�reply ����� Class ���ͱ��� �ǿ�
     * @param url
     *            ָ�������URL �ǿ�
     * @param charset
     *            �������� �磺utf-8 =null ȱʡ utf-8 ���Կ�
     * @param connectionTimeout
     *            ����λ���룩 �������ӳ�ʱʱ�� =null ȱʡ3�� ���Կ�
     * @param soTimeout
     *            ����λ���룩���ö���ݳ�ʱʱ�� =null ȱʡ 10�룬 ���Կ�
     * @return
     */
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromB2B2(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.requestToJson());
        if (StringUtils.isNotEmpty(request.getLtywfz())) {
            paras.put(NGX_GROUP_TYPE, request.getLtywfz());
        } else {
            if (!request.getApplySystem().equals("CRMϵͳ")) {
                logger.info("ltywfzisnull BASE_URL=:" + url + " json=" + request.requestToJson());
            }
            // ���crmͳһ����
            String ltywfz = "0";
            try {
                ltywfz = PropertyUtil.getProperty("ltywfz", "/config/properties/servers.properties");
                if (StringUtils.isNotEmpty(ltywfz)) {
                    paras.put(NGX_GROUP_TYPE, ltywfz);
                }
            } catch (Exception e) {
            }
        }

        return AbstractReply.jsonToReply2(
                HttpClientUtil.requestAsHttpPOST(BASE_URL + url, paras, charset, connectionTimeout, soTimeout),
                replyClass);
    }

    /**
     * ���request��ȡָ��url��B2B����
     * 
     * @param request
     *            ���������� �ǿ�
     * @param replyClass
     *            ���ص�reply ����� Class ���ͱ��� �ǿ�
     * @param url
     *            ָ�������URL �ǿ�
     * @param charset
     *            �������� �磺utf-8 =null ȱʡ utf-8 ���Կ�
     * @param connectionTimeout
     *            ����λ���룩 �������ӳ�ʱʱ�� =null ȱʡ3�� ���Կ�
     * @param soTimeout
     *            ����λ���룩���ö���ݳ�ʱʱ�� =null ȱʡ 10�룬 ���Կ�
     * @return
     */
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromB2B(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.requestToJson());
        if (StringUtils.isNotEmpty(request.getLtywfz())) {
            paras.put(NGX_GROUP_TYPE, request.getLtywfz());
        } else {
            if (!request.getApplySystem().equals("CRMϵͳ")) {
                logger.info("ltywfzisnull BASE_URL=:" + url + " json=" + request.requestToJson());
            }
            // ���crmͳһ����
            String ltywfz = "0";
            try {
                ltywfz = PropertyUtil.getProperty("ltywfz", "/config/properties/servers.properties");
                if (StringUtils.isNotEmpty(ltywfz)) {
                    paras.put(NGX_GROUP_TYPE, ltywfz);
                }
            } catch (Exception e) {
            }
        }

        return AbstractReply.jsonToReply(
                HttpClientUtil.requestAsHttpPOST(BASE_URL + url, paras, charset, connectionTimeout, soTimeout),
                replyClass);
    }

    /**
     * ��־ϵͳ���񣬽���B2Bʹ��
     * 
     * @return
     */
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromLogsys(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.requestToJson());
        return AbstractReply.jsonToReply(
                HttpClientUtil.requestAsHttpPOST(LOGSYS_URL + url, paras, charset, connectionTimeout, soTimeout),
                replyClass);
    }

    /**
     * ���request��reply��ȡָ��url��B2B����
     * 
     * @param <K>
     * @param <T>
     * @param request
     * @param reply
     * @param url
     * @return
     */
    public static <K extends AbstractB2BClientRequest, T extends AbstractB2BClientReply> T getReplyFromB2B(K request,
            T reply, String url, int timeout) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.beanToXml());
        return (T) reply.xmlToBean(HttpClientUtil.requestAsHttpPOST(BASE_URL + url, paras, ENCODING_UTF8, timeout));
    }

    public static <K extends AbstractB2BClientRequest, T extends AbstractB2BClientReply> T getReplyFromB2B(K request,
            T reply, String url) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.beanToXml());

        return (T) reply.xmlToBean(HttpClientUtil.requestAsHttpPOST(BASE_URL + url, paras, ENCODING_UTF8));
    }
}
