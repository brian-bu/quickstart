package net.brian.coding.java.utils.smartframe;

import net.brian.coding.java.core.jdk.jvm.classloader.ClassUtil;
import net.brian.coding.java.utils.smartframe.helper.AopHelper;
import net.brian.coding.java.utils.smartframe.helper.BeanHelper;
import net.brian.coding.java.utils.smartframe.helper.ClassHelper;
import net.brian.coding.java.utils.smartframe.helper.ControllerHelper;
import net.brian.coding.java.utils.smartframe.helper.IocHelper;

/**
 * 加载相应的 Helper 类
 * 类比spring的容器初始化功能
 *
 * 这里注意AopHelper需要在IocHelper之前加载，因为首先需要通过AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入
 * 由此联想spring应该也是这样的
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