package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method is a test method that must throw the any
 * of the designated exceptions to succeed.
 * ĳ����չException�����Class����������ע����û�ָ���κ��쳣���ͣ����磺
 * @ExceptionTest(ArithmeticException.class)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
	Class<? extends Exception>[] value();
}