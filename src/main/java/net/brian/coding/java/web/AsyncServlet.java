package net.brian.coding.java.web;

/**
 * 
 * Servlet 3中的异步处理的意义：
 * 如果一个任务处理时间相当长，那么Servlet或Filter会一直占用着请求处理线程直到任务结束
 * 随着并发用户的增加，容器将会遭遇线程超出的风险，这这种情况下很多的请求将会被堆积起来而后续的请求可能会遭遇拒绝服务，直到有资源可以处理请求为止
 * 异步特性可以帮助应用节省容器中的线程，特别适合执行时间长而且用户需要得到结果的任务
 * 如果用户不需要得到结果则直接将一个Runnable对象交给Executor并立即返回即可
 *
 * 本例作为一个支持Servlet异步处理的例子
 */
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/async" }, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // 开启Tomcat异步Servlet支持
		req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		final AsyncContext ctx = req.startAsync(); // 启动异步处理的上下文
		// ctx.setTimeout(30000);
		ctx.start(new Runnable() {
			@Override
			public void run() {
				// 在此处添加异步处理的代码
				ctx.complete();
			}
		});
	}
}