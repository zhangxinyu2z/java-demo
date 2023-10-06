package com.z2xinyu.annotation.jpaimpl.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 20:58
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
