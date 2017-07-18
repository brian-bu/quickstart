package net.brian.coding.java.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * �����ļ��������ڷ���������ʱ��ʼ��onLineCount��maxOnLineCount��������
 * ���������ڷ����������ģ�ServletContext���У����ʼֵ����0
 */
@WebListener
public class InitListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent evt) {
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		evt.getServletContext().setAttribute("onLineCount", 0);
		evt.getServletContext().setAttribute("maxOnLineCount", 0);
	}
}