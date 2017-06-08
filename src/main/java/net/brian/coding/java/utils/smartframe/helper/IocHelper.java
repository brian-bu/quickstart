package net.brian.coding.java.utils.smartframe.helper;

import java.lang.reflect.Field;
import java.util.Map;

import javax.inject.Inject;

import net.brian.coding.java.utils.apache.CollectionUtil;
import net.brian.coding.java.utils.smartframe.util.ArrayUtil;
import net.brian.coding.java.utils.smartframe.util.ReflectionUtil;

/**
 * “¿¿µ◊¢»Î÷˙ ÷¿‡
 *
 * @author huangyong
 * @since 1.0.0
 */
public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
