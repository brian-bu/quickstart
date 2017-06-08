package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method is a test method. Use only on
 * parameterless static methods.
 * 大多数情况下程序员都不必定义注解类型，但是所有的程序员都应该使用java平台提供的预定义的注解类型
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}