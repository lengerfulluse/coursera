package me.hengwei.t.java.annotations;

import java.lang.annotation.*;

/**
 * Created by hengwei on 31/10/2016.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Immutable {
    String value();
}
