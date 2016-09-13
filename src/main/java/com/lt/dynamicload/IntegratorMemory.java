package com.lt.dynamicload;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class IntegratorMemory {

    private static final String INTEGRATOR_XML = "Integrator.xml";

    private static long lastModifyTime = 0;

    private static Map<Integer, String> channelMap = new HashMap<Integer, String>();

    private static Integrators integrators;

    static {
        lastModifyTime = getAttributes();
        loadIntegrators();
    }

    public static String getChannelNo(int code) {
        return channelMap.get(code);
    }

    /**
     * 获取配置信息 <br>
     * 1：未修改-->直接获取 <br>
     * 2：已修改-->重新加载并获取
     * 
     * @return
     */
    public static Integrators loadIntegratorConfig() {
        long modifyAttr = getAttributes();

        if (lastModifyTime == 0 || lastModifyTime != modifyAttr) {
            loadIntegrators();
        }

        return integrators;
    }

    /**
     * 加载配置信息 <br>
     * 加载channelMap
     */
    public static void loadIntegrators() {
        InputStream resourceAsStream = IntegratorMemory.class.getResourceAsStream(INTEGRATOR_XML);
        Integrators integratorList = unmarshal(resourceAsStream, Integrators.class);
        integrators = integratorList;
        loadChannelMap(integrators);
    }

    // 加载ChannelMap
    public static void loadChannelMap(Integrators integrators) {
        // 为channelMap赋值
        channelMap = new HashMap<Integer, String>();
        for (Integrator integrator : integrators.getIntegrator()) {
            channelMap.put(integrator.getCode(), integrator.getChannel());
        }
    }

    private static long getAttributes() {
        try {
            URL resource = IntegratorMemory.class.getResource(INTEGRATOR_XML);
            File file = new File(resource.toURI());
            if (!file.exists())
                return 0L;

            // 文件最后的修改时间
            long lastModified = file.lastModified();
            // 文件的大小
            long length = file.length();

            return lastModified + length;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @SuppressWarnings("unchecked")
    private static <T> T unmarshal(InputStream resourceAsStream, Class<T> clazz) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            resourceAsStream = IntegratorMemory.class.getResourceAsStream(INTEGRATOR_XML);
            return (T) unmarshaller.unmarshal(resourceAsStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
