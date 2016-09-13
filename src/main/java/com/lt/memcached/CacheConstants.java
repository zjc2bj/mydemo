package com.lt.memcached;

public interface CacheConstants {

    /**
     * 配置的memcache客户端名称
     */
    String MEMCACHE_SERVER_NODE_NAME = "MCache";

    String CACHE_DELIMETER_KEY = "_";

    /**
     * memcached 缓存 key前缀
     */
    String CACHE_KEY_PREFIX = "bcs";

    /**
     * 默认 remote_cache_time 单位：秒
     */
    int CACHE_EXPIRATION_TIME = 5 * 60;

    /**
     * 默认 local_cache_time 单位：秒
     */
    int CACHE_LOCAL_FLAG = 5 * 60;

}
