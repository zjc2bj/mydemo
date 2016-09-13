package cn.zjc.util.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

public class BeanHandler extends ResultSetHandler {
    public BeanHandler(Class<?> clazz) {
        super(clazz);
    }

    public Object handle(ResultSet rs) {
        Map<String, String> filedMap = map.get(getClazz().getSimpleName());

        // 取出一条结果集 封装到bean中
        try {
            if (rs.next()) {
                // 获得元数据
                ResultSetMetaData metaData = rs.getMetaData();
                // 获得列数量
                int columnCount = metaData.getColumnCount();
                // 实例化bean
                Object bean = getClazz().newInstance();

                // 循环取出所有列 封装到bean
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object value = rs.getObject(columnName);

                    String fieldName = "";
                    if (filedMap != null && filedMap.size() > 0) {
                        fieldName = filedMap.get(columnName);
                    } else {
                        fieldName = columnName;
                    }
                    if (fieldName == null)
                        continue;

                    // 使用反射将值封装到bean
                    Field field = getClazz().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    Object convert = null;
                    if (value != null) {
                        convert = ConvertUtils.convert(value, type);// 类型转换
                    }
                    field.set(bean, convert);
                }
                return bean;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
