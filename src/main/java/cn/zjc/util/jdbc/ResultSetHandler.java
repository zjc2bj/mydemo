package cn.zjc.util.jdbc;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public abstract class ResultSetHandler {
    // 缓存：类名<-->filedMap
    public static Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

    private Class<?> clazz;

    protected ResultSetHandler(Class<?> clazz) {
        this.setClazz(clazz);
        // 检测缓存中是不是存在-- 类对应的filedMap
        synchronized (ResultSetHandler.class) {
            String className = clazz.getSimpleName();
            Map<String, String> fieldMap = map.get(className);
            if (fieldMap == null || fieldMap.size() < 1) {
                fieldMap = ORMConfigLoader.loadORM(clazz);
                map.put(className, fieldMap);
                System.out.println("初始化缓存map=" + map);
            }
        }
    }

    public abstract Object handle(ResultSet rs);

    // // 检测缓存中是不是存在-- 类对应的filedMap
    // Map<String, String> fieldMap = map.get(clazz.getSimpleName());
    // if (fieldMap == null || fieldMap.size() < 1) {
    // Map<String, String> hbm = JdbcUtil.loadHibernateMapping(clazz);
    // map.put(clazz.getSimpleName(), hbm);
    // }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
