package cn.zjc.util.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();// 映射request中的key

    boolean required() default false;// 是否必填

    Object entity = new Object();
}
