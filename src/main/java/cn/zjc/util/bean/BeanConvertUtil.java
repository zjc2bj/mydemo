package cn.zjc.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import cn.zjc.util.bean.annotation.RequestMapping;

public class BeanConvertUtil {
    public enum Type {
        WEBREQ_CONVERT, // web请求参数转换
        BEAN_CONVERT;// bean转换
    }

    public static <K, V> V beanConvert(K fromBean, V toBean) {

        return toBean;
    }

    /**
     * 将map转换成对应的实体类型
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> calzz) {
        try {
            T instance = calzz.newInstance();
            BeanInfo info = Introspector.getBeanInfo(calzz);
            PropertyDescriptor pds[] = info.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                try {
                    if (pd == null)
                        continue;

                    String fieldName = pd.getName();
                    if (!map.containsKey(fieldName)) {
                        System.out.println("不包含" + fieldName + "字段");
                        continue;
                    }

                    Class<?> propertyType = pd.getPropertyType();// 字段类型
                    Object value = map.get(fieldName);// 字段所赋值

                    if (value == null)
                        continue;

                    if (propertyType.equals(Date.class)) {// 字段为Date类型
                        System.out.println(fieldName + "对应值 ：" + value + "-->日期类型转换。。。");
                        value = obj2Date(value);
                    }

                    Object convert = ConvertUtils.convert(value, propertyType);
                    Method method = pd.getWriteMethod();
                    if (method != null)
                        method.invoke(instance, convert);
                } catch (IllegalArgumentException e) {
                    System.out.println("字段赋值异常--非法参数");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    System.out.println("字段赋值失败--调用目标");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("对应值不能转换为日期");
                    e.printStackTrace();
                }
            }
            return instance;
        } catch (InstantiationException e) {
            System.out.println("对象构造异常");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("对象构造异常");
            e.printStackTrace();
        } catch (IntrospectionException e) {
            System.out.println("获取对象字节码信息异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将map转换成对应的实体类型(不支持继承)--使用反射(非javaBean用此方法不安全 如包含静态final)
     * 
     * @param map
     *            待转换的数据
     * @param calzz
     *            对应的实体类
     * @param ormMap
     *            (map的key-->class的field) map中的key和class中的字段对应关系，<br>
     *            如果为空：默认map中的key值和类中的字段名称相同
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz, Map<String, String> filedMap) {
        // 实例化bean
        T bean = null;
        try {
            bean = clazz.newInstance();

            for (String key : map.keySet()) {
                Object value = map.get(key);

                if (value == null)
                    continue;

                String fieldName = "";
                if (filedMap != null && filedMap.size() > 0) {
                    fieldName = filedMap.get(key);
                } else {
                    fieldName = key;
                }
                if (fieldName == null)
                    fieldName = key;

                // 使用反射将值封装到bean
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Class<?> type = field.getType();

                boolean isFinal = Modifier.isFinal(field.getModifiers());
                if (isFinal)
                    continue;

                if (type.equals(Date.class)) {// 字段为Date类型
                    System.out.println(fieldName + "对应值 ：" + value + "-->日期类型转换。。。");
                    value = obj2Date(value);
                }

                Object convert = null;
                if (value != null) {
                    convert = ConvertUtils.convert(value, type);// 类型转换
                }
                field.set(bean, convert);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return bean;
    }

    /**
     * 根据 map数据+注解 转换实体<br>
     * --1.不支持继承的字段赋值<br>
     * --2.如果对象属性为必填且map中无对应值 则抛出异常 <br>
     * 
     * @param map
     * @param clazz
     *            包含RequstMapping注解的类
     */
    public static <T> T map2BeanByAnnotation(Map<String, ? extends Object> map, Class<T> clazz) {
        // 实例化bean
        T bean = null;

        try {
            bean = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("对应实例化失败：" + e.getMessage());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean isFinal = Modifier.isFinal(field.getModifiers());
                if (isFinal)
                    continue;

                field.setAccessible(true);
                Class<?> type = field.getType();
                String keyMapping;

                RequestMapping annotation = field.getAnnotation(RequestMapping.class);
                if (annotation == null) {
                    keyMapping = field.getName();
                } else {
                    keyMapping = annotation.value();
                }

                Object value = map.get(keyMapping);

                if (value == null) {
                    if (annotation != null && annotation.required())// 判断是否为必填并处理
                        throw new NullPointerException("LESS_OF_" + keyMapping.toUpperCase());
                    continue;
                }

                if (type.equals(Date.class)) {// 字段为Date类型
                    System.out.println(field.getName() + "对应值 ：" + value + "-->日期类型转换。。。");
                    value = obj2Date(value);
                }
                value = ConvertUtils.convert(value, type);// 类型转换
                field.set(bean, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        return bean;
    }

    /**
     * 将Object 转换为Date类型
     * 
     * @param value
     * @return
     * @throws ParseException
     * @throws Exception
     */
    public static Date obj2Date(final Object value) {
        if (value instanceof Date) {
            return (Date) value;
        }

        try {
            if (value instanceof Long) {
                return new Date((Long) value);
            }

            if (value instanceof String) {
                String valStr = String.valueOf(value);
                DateFormat format = null;
                if (valStr.contains("-") && !valStr.contains(":")) {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
                }
                return format.parse(valStr);
            }

            throw new RuntimeException("日期转换异常");
        } catch (ParseException e) {
            System.out.println("日期转换异常");
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    /**通过PropertyUtils为实体赋值*/
    private <T> T map2BeanTest1(Map<String, Object> map, Class<T> calzz) throws Exception {
        T instance = null;
        instance = calzz.newInstance();
        Set<Entry<String, Object>> entrySet = map.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();

            PropertyUtils.setProperty(instance, key, value);
        }
        return instance;
    }

    public static String Bean2Str(Object instance) {
        try {
            Class<? extends Object> clazz = instance.getClass();
            BeanInfo info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor pds[] = info.getPropertyDescriptors();
            System.out.println("*****************" + clazz.getName() + "*****************");
            for (PropertyDescriptor pd : pds) {
                String fieldName = pd.getName();
                // System.out.println(pd.getPropertyType());
                if (!fieldName.equals("class")) {
                    Method md = pd.getReadMethod();
                    if (md != null) {
                        Object value = md.invoke(instance, new Object[] {});
                        if (value != null)
                            System.out.println(fieldName + "=" + value);
                    }
                }
            }
            System.out.println("*****************" + clazz.getName() + "*****************");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> generateList(List<?> orgList, Class<T> calzz) {
        List<T> list = new ArrayList<T>();
        for (Object object : orgList) {
            if (object instanceof LinkedHashMap) {
                try {
                    LinkedHashMap linkedMap = (LinkedHashMap) object;
                    Map map = new HashMap(linkedMap);
                    T bean = calzz.newInstance();
                    BeanUtils.copyProperties(bean, map);
                    list.add(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static Map<String, Object> sortCollection(Map<String, ? extends Object> srcMap) {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>(srcMap);
        return treeMap;
    }
}
