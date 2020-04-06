package cn.sher6j.java1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author sher6j
 * @create 2020-04-04-20:59
 */

@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.RUNTIME) //RUNTIME才可以通过反射获取
public @interface MyAnnotation {
    String value() default "hello";
}
