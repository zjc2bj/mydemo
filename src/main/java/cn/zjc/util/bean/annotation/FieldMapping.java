package cn.zjc.util.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.zjc.util.bean.enums.ConvertType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface FieldMapping {
    String value();

    Class<?> type();

    ConvertType convertType() default ConvertType.BOTHWAY;

}
