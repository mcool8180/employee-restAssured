package com.briozing.employee.listners;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface DefectInfo {

    String jirId() default "";

    String stepName() default "";
}