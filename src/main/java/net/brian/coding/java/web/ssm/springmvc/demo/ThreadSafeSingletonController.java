package net.brian.coding.java.web.ssm.springmvc.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * springmvc��controller��singleton�ģ����̰߳�ȫ�ģ�
 * ���������£����Ǹ�������Ҫ�����̰߳�ȫ�����⣬����dao,service�ȣ�������bean��������ʵ������
 * ��ˣ�����ͨ��ֻ���Ǳ����@Controller��bean���̰߳�ȫ��Ӧ������controller�ж���ʵ��������
 * ���һ��Ҫ�����ﶨ��ʵ����������Ҫ�����������ַ�����֤�̰߳�ȫ��
 * a.��ThreadLocal��װ��Щʵ������
 * b.��spring�����ļ�Controller������ scope="prototype"��ÿ�ζ������µ�controller
 *
 */
@Controller
@RequestMapping("/view")
//FIXME: �̣߳������������ڶ��̻߳�����������
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
