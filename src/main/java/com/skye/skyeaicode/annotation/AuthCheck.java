package com.skye.skyeaicode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时保留，便于 AOP 读取
public @interface AuthCheck {

    /**
     * 必须有某个角色
     * mustRole 属性用于指定调用该方法所需要的角色（例如 "admin"、"user" 等）。
     */
    String mustRole() default "";
}
