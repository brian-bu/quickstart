package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method is a test method. Use only on
 * parameterless static methods.
 * ���������³���Ա�����ض���ע�����ͣ��������еĳ���Ա��Ӧ��ʹ��javaƽ̨�ṩ��Ԥ�����ע������
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}