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
	 * �Ƚ��ܣ���ȡ���ģ�Ȼ������ת��Ϊ�ֽ����飻Ȼ����ȥ��ȡ�ֽ������е�����
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
		System.out.println("MyRequestWrapper���յ�������Ϊ: " + bizBindMsg);
		/**
		 * ��ȡ���ܵ�ֵ���н���
		 */
		final StringBuffer reqStr = new StringBuffer();
		reqStr.append("param1=").append(bizBindMsg.substring(bizBindMsg.indexOf("param1=") + 7, bizBindMsg.indexOf("param2=")));
		reqStr.append("&");
		reqStr.append("param2=").append(bizBindMsg.substring(bizBindMsg.indexOf("param2=") + 7, bizBindMsg.indexOf("param3=")));
		reqStr.append("&");
		reqStr.append("param3=").append(bizBindMsg.substring(bizBindMsg.indexOf("param3=") + 7, bizBindMsg.indexOf("param4=")));
		reqStr.append("&");
		reqStr.append("param4=").append(bizBindMsg.substring(bizBindMsg.indexOf("param4=") + 7));
		System.out.println("********MyRequestWrapper���յ��Ľ��ܺ������Ϊ*********");
		System.out.println(reqStr.toString());
		/**
		 * �����ܺ�����Ĵ��ŵ�buffer������
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
