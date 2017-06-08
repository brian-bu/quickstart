/**
 * 本包下的注解实现和xml实现都是功能完整的实现方式，可以将任何一个实现改造为包含如下功能的aop：
 * 1. 对部分函数的调用进行日志记录，用于观察特定问题在运行过程中的函数调用情况
 * 2. 监控部分重要函数，若抛出指定的异常，需要以短信或邮件方式通知相关人员
 * 3. 金控部分重要函数的执行时间
 */
/**
 * @author rainy
 *
 */
package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log;