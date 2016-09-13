package cn.zjc.util.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

@SuppressWarnings("unchecked")
public class BeanListHandler extends ResultSetHandler {

    // 当前对象实现功能必须要依赖另外一个对象， 通过构造函数向调用者索取

    public BeanListHandler(Class<?> clazz) {
        super(clazz);
    }

    @SuppressWarnings("rawtypes")
    public Object handle(ResultSet rs) {
        try {
            // 对结果集进行处理 每条记录封装成一个bean 存入list集合
            List list = new ArrayList();
            Map<String, String> filedMap = map.get(getClazz().getSimpleName());

            // 将每条结果封装到bean

            while (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData(); // 获得RusultSet的元数据
                int columnCount = metaData.getColumnCount(); // 获得resultset中的列数
                // 实例化bean
                Object bean = getClazz().newInstance();

                for (int i = 0; i < columnCount; i++) {
                    // 当前列名
                    String columnName = metaData.getColumnName(i + 1);
                    // 当前列值
                    Object columnValue = rs.getObject(columnName);

                    String fieldName = "";
                    if (filedMap != null && filedMap.size() > 0) {
                        fieldName = filedMap.get(columnName);
                    } else {
                        fieldName = columnName;
                    }
                    if (fieldName == null)
                        continue;

                    // 封装到bean 由于没有具体对象 所以只能用反射
                    Field field = getClazz().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    Object convert = null;
                    if (columnValue != null) {
                        convert = ConvertUtils.convert(columnValue, type);// 类型转换
                    }
                    field.set(bean, convert);
                    // field.set(bean, columnValue);
                }
                list.add(bean);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}