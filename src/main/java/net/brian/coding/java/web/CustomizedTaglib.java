package net.brian.coding.java.web;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 
 * �Զ���tag�Ĳ��裺
 * a.CustomizedTaglib.java:
 * �̳�TagSupport/BodyTagSupport/SimpleTagSupport�࣬��дdoStartTag()��doEndTag()�ȷ����������ǩҪ��ɵĹ���
 * b.��д��ǩ�������ļ�my.tld:
 * ��д��չ��Ϊtld�ı�ǩ�����ļ����Զ����ǩ���в���tld�ļ�ͨ������WEB-INF�ļ����»�����Ŀ¼��
 * c.��JSPҳ��CustomizedTaglib.jsp��ʹ���Զ����ǩ:
 * ���Ҫ���Զ���ı�ǩ�ⷢ����JAR�ļ�����Ҫ����ǩ�������ļ���tld�ļ�������JAR�ļ���META-INFĿ¼�¿���JDK�е�jar�������JAR�ļ�������
 */
//FIXIT����д����
public class CustomizedTaglib extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String format = "yyyy-MM-dd hh:mm:ss";
	private String foreColor = "black";
	private String backColor = "white";

	public int doStartTag() throws JspException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		JspWriter writer = pageContext.getOut();
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<span style='color:%s;background-color:%s'>%s</span>", foreColor, backColor,
				sdf.format(new Date())));
		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setForeColor(String foreColor) {
		this.foreColor = foreColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
}