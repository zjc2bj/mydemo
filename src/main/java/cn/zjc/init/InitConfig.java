package cn.zjc.init;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.zjc.util.DateTools;

/**
 * 配置文件解析
 * 
 * @author zhujunchao
 * 
 * @param <T>
 *            配置文件对应的解析后类型
 */
public abstract class InitConfig<T> {
    /** 文件名称-->对应配置 */
    public static final Map<String, Object> confs = new HashMap<String, Object>();

    /** 记录文件最后一次加载时间 */
    public static final Map<String, Long> refresh = new HashMap<String, Long>();

    /** 重新加载文档 */
    public abstract T refrushLoad(File configFile);

    /*
     * protected Class<T> clazz;
     * 
     * public InitConfig() { ParameterizedType pt = (ParameterizedType)
     * this.getClass().getGenericSuperclass(); this.clazz = (Class<T>)
     * pt.getActualTypeArguments()[0]; }
     */

    /**
     * 根据路径获取配置信息
     * 
     * @param confPath
     *            类路径--加此参数解决相同配置实体对应多个配置文件的问题
     */
    @SuppressWarnings("unchecked")
    public T loadConfig(String confPath) {
        try {
            File confFile = InitFactory.getResourceFile(confPath);// 有bug
            long lastModified = confFile.lastModified();

            if (refresh.get(confFile.getName()) == null || refresh.get(confFile.getName()) != lastModified) {
                synchronized (InitConfig.class) {
                    if (refresh.get(confFile.getName()) == null || refresh.get(confFile.getName()) != lastModified) {
                        String confName = confFile.getName();

                        println("[start] [loadConfFile]：" + confPath);
                        long start = System.currentTimeMillis();
                        T refrush = refrushLoad(confFile);
                        println("[cost=" + (System.currentTimeMillis() - start) + "] [loadConfFile]：" + confPath);

                        confs.put(confName, refrush);
                        refresh.put(confName, lastModified);
                    }
                }
            }

            return (T) confs.get(confFile.getName());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("获取配置信息出错：" + confPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取配置信息出错：" + confPath);
        }
    }

    private void println(String str) {
        System.out.println("[" + DateTools.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "] " + str);
    }
}
