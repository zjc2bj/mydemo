package com.lt.memcached;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

/**
 * @author kobe 缓存客户端
 * 
 */
@Component(value = "memcachedClient")
public class MemcachedClientAdapter {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(MemcachedClientAdapter.class);

    private static ICacheManager<IMemcachedCache> manager;

    public MemcachedClientAdapter() {
        if (manager == null) {
            manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
            manager.setConfigFile("./config/properties/memcached.xml");
            manager.start();
            logger.info("ICacheManager inited OK.");
        } else {
            logger.info("ICacheManager already inited.");
        }
    }

    public IMemcachedCache getCache() {
        return manager.getCache(CacheConstants.MEMCACHE_SERVER_NODE_NAME);
    }

    public Object get(String key) {
        key = generateCacheKey(key);
        return this.getCache().get(key);
    }

    /**
     * 设置本地缓存时间，单位秒
     */
    public Object get(String key, int locaExpireTime) {
        key = generateCacheKey(key);
        return this.getCache().get(key, locaExpireTime);
    }

    public Object put(String key, Object value) {
        if (StringUtils.isNotEmpty(key) && value != null) {
            key = generateCacheKey(key);
            return this.getCache().put(key, value);
        } else {
            return null;
        }
    }

    /**
     * 设置远程缓存时间，单位秒
     */
    public Object put(String key, Object value, int remoteExpireTime) {
        if (StringUtils.isNotEmpty(key) && value != null) {
            key = generateCacheKey(key);
            System.out.println(key);
            return this.getCache().put(key, value, remoteExpireTime);
        } else {
            return null;
        }
    }

    /**
     * 删除缓存
     */
    public void remove(String key) {
        key = generateCacheKey(key);
        this.getCache().remove(key);
    }

    /**
     * 计数器值增加inc，如果计数器不存在，保存inc作为计数器值
     */
    public Long addOrIncr(String key, long incr) {
        key = generateCacheKey(key);
        return this.getCache().addOrIncr(key, incr);
    }

    /**
     * 计数器值减去decr，如果计数器不存在，保存decr作为计数器值
     */
    public Long addOrDecr(String key, long decr) {
        key = generateCacheKey(key);
        return this.getCache().addOrDecr(key, decr);
    }

    /**
     * 与addOrDecr不同的是在计数器不存在的时候不保存任何值，返回-1
     */
    public Long decr(String key, long decr) {
        key = generateCacheKey(key);
        return this.getCache().decr(key, decr);
    }

    /**
     * 与addOrIncr不同的是在计数器不存在的时候不保存任何值，返回-1
     */
    public Long incr(String key, long incr) {
        key = generateCacheKey(key);
        return this.getCache().incr(key, incr);
    }

    /**
     * 存储key的计数器，值为count
     */
    public void storeCounter(String key, long count) {
        key = generateCacheKey(key);
        this.getCache().storeCounter(key, count);
    }

    /**
     * 获取key的计数器，如果不存在返回-1
     */
    public long getCounter(String key) {
        key = generateCacheKey(key);
        return this.getCache().getCounter(key);
    }

    /**
     * 是否已经存在此key
     */
    public boolean containKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        key = generateCacheKey(key);
        return this.getCache().containsKey(key);
    }

    /**
     * 构造主键，格式:bcs_XXX_XXX
     */
    public static String generateCacheKey(String[] keys) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(CacheConstants.CACHE_KEY_PREFIX);
        keyBuilder.append(CacheConstants.CACHE_DELIMETER_KEY);
        for (String key : keys) {
            keyBuilder.append(key);
            keyBuilder.append(CacheConstants.CACHE_DELIMETER_KEY);
        }
        return keyBuilder.toString();
    }

    /**
     * 构造主键，格式:bcs_XXX
     */
    public static String generateCacheKey(String key) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(CacheConstants.CACHE_KEY_PREFIX);
        keyBuilder.append(CacheConstants.CACHE_DELIMETER_KEY);
        keyBuilder.append(key);
        return keyBuilder.toString();
    }

    public Set<String> getKeySet() {
        return this.getCache().keySet(true);
    }

    public static String convertObjectToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerSubtypes(new Class[] { o.getClass() });
        String result = "";
        try {
            result = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        MemcachedClientAdapter cache = new MemcachedClientAdapter();
        System.out.println(new Date());
        cache.remove("bcs_aaaa1");
        System.out.println("cache-put:" + cache.put("bcs_aaaa1", "aaaaaa", 20));
        try {
            int i = 0;
            while (true) {
                System.out.println(new Date());
                System.out.println(new Date().getTime());
                System.out.println(i + "cache-get:" + cache.get("bcs_aaaa1"));
                Thread.sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }

}
