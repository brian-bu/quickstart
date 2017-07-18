package net.brian.coding.java.web.filter.httpservletrequestwrapperfilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class MyRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * 先解密，获取明文；然后将明文转化为字节数组；然后再去读取字节数组中的内容
	 */
	@Override
	public ServletInputStream getInputStream() {
		String bizBindMsg = null;
		ServletInputStream stream = null;
		try {
			stream = request.getInputStream();
			bizBindMsg = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bizBindMsg = URLDecoder.decode(bizBindMsg.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("MyRequestWrapper接收到的请求为: " + bizBindMsg);
		/**
		 * 获取加密的值进行解密
		 */
		final StringBuffer reqStr = new StringBuffer();
		reqStr.append("param1=").append(bizBindMsg.substring(bizBindMsg.indexOf("param1=") + 7, bizBindMsg.indexOf("param2=")));
		reqStr.append("&");
		reqStr.append("param2=").append(bizBindMsg.substring(bizBindMsg.indexOf("param2=") + 7, bizBindMsg.indexOf("param3=")));
		reqStr.append("&");
		reqStr.append("param3=").append(bizBindMsg.substring(bizBindMsg.indexOf("param3=") + 7, bizBindMsg.indexOf("param4=")));
		reqStr.append("&");
		reqStr.append("param4=").append(bizBindMsg.substring(bizBindMsg.indexOf("param4=") + 7));
		System.out.println("********MyRequestWrapper接收到的解密后的请求为*********");
		System.out.println(reqStr.toString());
		/**
		 * 将解密后的明文串放到buffer数组中
		 */
		byte[] buffer = null;
		try {
			buffer = reqStr.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		ServletInputStream newStream = new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				
			}
		};
		return newStream;
	}
}
