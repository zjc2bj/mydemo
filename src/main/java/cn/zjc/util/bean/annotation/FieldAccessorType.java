package cn.zjc.util.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.zjc.util.bean.enums.FieldAccessType;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAccessorType {
    FieldAccessType value() default FieldAccessType.PUBLIC_MEMBER;
}
