package net.brian.coding.java.web;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 
 * 自定义tag的步骤：
 * a.CustomizedTaglib.java:
 * 继承TagSupport/BodyTagSupport/SimpleTagSupport类，重写doStartTag()、doEndTag()等方法，定义标签要完成的功能
 * b.编写标签库描述文件my.tld:
 * 编写扩展名为tld的标签描述文件对自定义标签进行部署，tld文件通常放在WEB-INF文件夹下或其子目录中
 * c.在JSP页面CustomizedTaglib.jsp中使用自定义标签:
 * 如果要将自定义的标签库发布成JAR文件，需要将标签库描述文件（tld文件）放在JAR文件的META-INF目录下可以JDK中的jar工具完成JAR文件的生成
 */
//FIXIT：手写代码
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