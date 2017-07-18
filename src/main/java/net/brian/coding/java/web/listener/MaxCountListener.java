package net.brian.coding.java.web.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计网站最多在线人数监听器:
 * 会话监听器，在用户会话创建和销毁的时候根据情况 修改onLineCount和maxOnLineCount的值
 */
@WebListener
public class MaxCountListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		int count = Integer.parseInt(ctx.getAttribute("onLineCount").toString());
		count++;
		ctx.setAttribute("onLineCount", count);
		int maxOnLineCount = Integer.parseInt(ctx.getAttribute("maxOnLineCount").toString());
		if (count > maxOnLineCount) {
			ctx.setAttribute("maxOnLineCount", count);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ctx.setAttribute("date", df.format(new Date()));
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext app = event.getSession().getServletContext();
		int count = Integer.parseInt(app.getAttribute("onLineCount").toString());
		count--;
		app.setAttribute("onLineCount", count);
	}
}