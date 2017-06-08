package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo;
/**
 * 
 * 示例程序。主程序见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo
 *
 */
public class LoginServiceImpl implements ILoginService {

    public boolean login(String userName, String password) {
        System.out.println("login:" + userName + "," + password);
        return true;
    }

}