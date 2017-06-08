package net.brian.coding.java.web.ssm.springmvc.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * springmvc的controller是singleton的（非线程安全的）
 * 大多数情况下，我们根本不需要考虑线程安全的问题，比如dao,service等，除非在bean中声明了实例变量
 * 因此，我们通常只考虑标记了@Controller的bean的线程安全，应避免在controller中定义实例变量。
 * 如果一定要在这里定义实例变量，就要考虑如下两种方法保证线程安全：
 * a.用ThreadLocal包装这些实例变量
 * b.在spring配置文件Controller中声明 scope="prototype"，每次都创建新的controller
 *
 */
@Controller
@RequestMapping("/view")
//FIXME: 线程：如何让这个类在多线程环境下跑起来
public class ThreadSafeSingletonController {
	private ThreadLocal<Integer> count = new ThreadLocal<Integer>();
	private int k = 0;

	@ResponseBody
	@RequestMapping(value = "sync", method = RequestMethod.GET)
	public String indexSync() {
		try {
			int i = new Random().nextInt(15);
			System.out.println(i);
			k = i;
			count.set(i);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
			System.out.println("-->" + sf.format(new Date()) + "   Threadname:" + Thread.currentThread().getName());
			Thread.sleep(5 * 1000);
			System.out.println("-->1--:" + sf.format(new Date()) + "   Threadname:" + Thread.currentThread().getName());
			synchronized (ThreadSafeSingletonController.class) {
				System.out.println("-->2--:" + sf.format(new Date()) + "   Threadname:" + Thread.currentThread().getName());
				Thread.sleep(10 * 1000);
			}
			System.out.println("-->3--:" + sf.format(new Date()) + "   Threadname:" + Thread.currentThread().getName());
			System.out.println("count:" + count.get() + " ,k=" + k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "suc";
	}
}
