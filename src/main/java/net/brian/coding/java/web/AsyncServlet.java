package net.brian.coding.java.web;

/**
 * 
 * Servlet 3�е��첽��������壺
 * ���һ��������ʱ���൱������ôServlet��Filter��һֱռ�����������߳�ֱ���������
 * ���Ų����û������ӣ��������������̳߳����ķ��գ�����������ºܶ�����󽫻ᱻ�ѻ�������������������ܻ������ܾ�����ֱ������Դ���Դ�������Ϊֹ
 * �첽���Կ��԰���Ӧ�ý�ʡ�����е��̣߳��ر��ʺ�ִ��ʱ�䳤�����û���Ҫ�õ����������
 * ����û�����Ҫ�õ������ֱ�ӽ�һ��Runnable���󽻸�Executor���������ؼ���
 *
 * ������Ϊһ��֧��Servlet�첽���������
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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // ����Tomcat�첽Servlet֧��
		req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		final AsyncContext ctx = req.startAsync(); // �����첽�����������
		// ctx.setTimeout(30000);
		ctx.start(new Runnable() {
			@Override
			public void run() {
				// �ڴ˴�����첽����Ĵ���
				ctx.complete();
			}
		});
	}
}