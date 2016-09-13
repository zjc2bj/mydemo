package com.lt.memcached;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alisoft.xplatform.asf.cache.memcached.MemcachedErrorHandler;
import com.alisoft.xplatform.asf.cache.memcached.client.MemCachedClient;

/**
 * @author kobe 缓存异常处理类
 * 
 */
public class CacheErrorHandler extends MemcachedErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheErrorHandler.class);

    @Override
    public void handleErrorOnDelete(MemCachedClient client, Throwable error, String cacheKey) {
        super.handleErrorOnDelete(client, error, cacheKey);

    }

    @Override
    public void handleErrorOnFlush(MemCachedClient client, Throwable error) {
        super.handleErrorOnFlush(client, error);

    }

    @Override
    public void handleErrorOnGet(MemCachedClient client, Throwable error, String cacheKey) {
        super.handleErrorOnGet(client, error, cacheKey);

    }

    @Override
    public void handleErrorOnGet(MemCachedClient client, Throwable error, String[] cacheKeys) {
        super.handleErrorOnGet(client, error, cacheKeys);

    }

    @Override
    public void handleErrorOnInit(MemCachedClient client, Throwable error) {
        super.handleErrorOnInit(client, error);

    }

    @Override
    public void handleErrorOnSet(MemCachedClient client, Throwable error, String cacheKey) {
        super.handleErrorOnSet(client, error, cacheKey);

    }

    @Override
    public void handleErrorOnStats(MemCachedClient client, Throwable error) {
        super.handleErrorOnStats(client, error);

    }

    public static void main(String[] args) throws ClassNotFoundException {
    }
}
