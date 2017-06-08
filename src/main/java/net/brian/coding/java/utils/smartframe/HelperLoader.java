package net.brian.coding.java.utils.smartframe;

import net.brian.coding.java.core.jdk.jvm.classloader.ClassUtil;
import net.brian.coding.java.utils.smartframe.helper.AopHelper;
import net.brian.coding.java.utils.smartframe.helper.BeanHelper;
import net.brian.coding.java.utils.smartframe.helper.ClassHelper;
import net.brian.coding.java.utils.smartframe.helper.ControllerHelper;
import net.brian.coding.java.utils.smartframe.helper.IocHelper;

/**
 * 加载相应的 Helper 类
 *
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}