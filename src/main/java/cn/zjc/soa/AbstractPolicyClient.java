package cn.zjc.soa;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.objenesis.strategy.StdInstantiatorStrategy;

import cn.zjc.soa.vo.base.AbstractReply;
import cn.zjc.soa.vo.base.AbstractRequest;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.lt.utils.HttpClientUtil;
import com.lt.utils.PropertyUtil;

public abstract class AbstractPolicyClient {

    private static String POLICY_URL = PropertyUtil.getProperty("policy.host", "/config/properties/servers.properties");

    private static final String WS_REQUEST_KEY = "req";

    /***************************************************************************
     * 设置缺省字符编码
     */
    private final static String DEFAULT_CHARSET_UTF_8 = "UTF-8";

    /**
     * 设置缺省连接超时时间(单位毫秒)
     */
    private final static int DEFAULT_CONNECTION_TIME_OUT = 3000;

    /**
     * 设置缺省连接超时时间(单位毫秒),针对大数据流的请求
     */
    private final static int DEFAULT_CONNECTION_TIME_OUT_FOR_STREAM = 20 * 000;

    /**
     * 设置缺省读数据超时时间(单位毫秒)
     */
    private final static int DEFAULT_SO_TIME_OUT = 10000;

    /**
     * 20 秒
     */
    public static final Integer TIME_OUT = 20 * 1000;

    public static final Integer TIME_OUT_30 = 30 * 1000;

    public static final Integer TIME_OUT_60 = 60 * 1000;

    public static final Integer TIME_OUT_120 = 120 * 1000;

    public static final Integer TIME_OUT_600 = 600 * 1000;

    /**
     * 根据request获取指定url的行程单服务
     * 
     * @param request
     *            请求参数对象 非空
     * @param replyClass
     *            返回的reply 对象的 Class 类型变量 非空
     * @param url
     *            指定服务的URL 非空
     * @param charset
     *            编码类型 如：utf-8 =null 缺省 utf-8 可以空
     * @param connectionTimeout
     *            （单位毫秒） 设置连接超时时间 =null 缺省3秒 可以空
     * @param soTimeout
     *            （单位毫秒）设置读数据超时时间 =null 缺省 10秒， 可以空
     * @return
     */
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromPolicy(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put(WS_REQUEST_KEY, request.requestToJson());
        return AbstractReply.jsonToReply(
                HttpClientUtil.requestAsHttpPOST(POLICY_URL + url, paras, charset, connectionTimeout, soTimeout),
                replyClass);
    }

    @SuppressWarnings("unchecked")
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromPolicyByStream(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        T reply = null;
        InputStream in = null;
        Input input = null;
        try {
            in = requestInputStreamAsHttpPOSTByStream(POLICY_URL + url, charset, connectionTimeout, soTimeout, request);
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            input = new Input(in);
            reply = (T) kryo.readObject(input, replyClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reply;
    }

    private static <K extends AbstractRequest> InputStream requestInputStreamAsHttpPOSTByStream(String urlvalue,
            String charset, Integer connectionTimeout, Integer soTimeout, K request) {
        URL u = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;
        Output output = null;
        // 尝试发送请求
        try {
            u = new URL(urlvalue);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            if (connectionTimeout != null && connectionTimeout.intValue() != 0) {
                con.setConnectTimeout(connectionTimeout);
            } else {
                con.setConnectTimeout(DEFAULT_CONNECTION_TIME_OUT);
            }
            if (soTimeout != null && soTimeout.intValue() != 0) {
                con.setReadTimeout(soTimeout);
            } else {
                con.setReadTimeout(DEFAULT_CONNECTION_TIME_OUT_FOR_STREAM);
            }
            con.setRequestProperty("Content-Type", "binary/octet-stream");
            output = new Output(con.getOutputStream());
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            kryo.writeObject(output, request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.disconnect();
                }
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            // 读取返回内容
            inputStream = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;

    }

    // GZIP方式传输
    @SuppressWarnings("unchecked")
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromPolicyByGZIP(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        T reply = null;
        InputStream in = null;
        GZIPInputStream gzipin = null;
        ObjectInputStream ois = null;
        try {
            Map<String, String> paras = new HashMap<String, String>();
            paras.put(WS_REQUEST_KEY, request.requestToJson());
            in = requestInputStreamAsHttpPOST(POLICY_URL + url, paras, charset, connectionTimeout, soTimeout);
            gzipin = new GZIPInputStream(in);
            ois = new ObjectInputStream(gzipin);
            reply = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (gzipin != null) {
                    gzipin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reply;
    }

    @SuppressWarnings("unchecked")
    protected static <K extends AbstractRequest, T extends AbstractReply> T getReplyFromPolicyByKryo(K request,
            Class<T> replyClass, String url, String charset, Integer connectionTimeout, Integer soTimeout) {
        T reply = null;
        InputStream in = null;
        Input input = null;
        try {
            Map<String, String> paras = new HashMap<String, String>();
            paras.put(WS_REQUEST_KEY, request.requestToJson());
            in = requestInputStreamAsHttpPOST(POLICY_URL + url, paras, charset, connectionTimeout, soTimeout);
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            input = new Input(in);
            reply = (T) kryo.readObject(input, replyClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reply;
    }

    private static InputStream requestInputStreamAsHttpPOST(String urlvalue, Map<String, String> paras, String charset,
            Integer connectionTimeout, Integer soTimeout) {
        HttpClient http = new HttpClient();
        HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
        if (connectionTimeout != null && connectionTimeout.intValue() != 0) {
            managerParams.setConnectionTimeout(connectionTimeout);
        } else {
            managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIME_OUT);
        }
        if (soTimeout != null && soTimeout.intValue() != 0) {
            managerParams.setSoTimeout(soTimeout);
        } else {
            managerParams.setSoTimeout(DEFAULT_SO_TIME_OUT);
        }
        PostMethod method = new PostMethod(urlvalue);
        if (StringUtils.isNotEmpty(charset)) {
            method.getParams().setHttpElementCharset(charset);
            method.getParams().setContentCharset(charset);
            method.getParams().setCredentialCharset(charset);
        } else {
            method.getParams().setHttpElementCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setContentCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setCredentialCharset(DEFAULT_CHARSET_UTF_8);
        }
        if (paras != null) {
            for (String key : paras.keySet()) {
                method.addParameter(key, paras.get(key));
            }
        }
        InputStream in = null;
        try {
            http.executeMethod(method);
            in = method.getResponseBodyAsStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

}
