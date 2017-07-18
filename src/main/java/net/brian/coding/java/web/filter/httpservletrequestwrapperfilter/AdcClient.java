package net.brian.coding.java.web.filter.httpservletrequestwrapperfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class AdcClient {
	private HttpPost httpPost = null;
	private HttpClient client = null;
	private List<NameValuePair> pairs = null;

	public AdcClient() {
		httpPost = new HttpPost("http://localhost:8080/filtertest/SiServlet");
		client = new DefaultHttpClient();
	}

	/**
	 * ����������Ϣ
	 * 
	 */
	public void sendMsg() {
		try {
			httpPost = new HttpPost("http://localhost:8080/filtertest/SiServletNormal");
			pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair(("param1"), "obamaû����"));
			pairs.add(new BasicNameValuePair(("param2"), "��û����"));
			pairs.add(new BasicNameValuePair(("param3"), "��û����"));
			pairs.add(new BasicNameValuePair(("param4"), "ɽ��û����"));
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			// httpPost.setHeader("Cookie", "TOKEN=1234567890");
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = br.readLine()) != null) {
				result.append(line);
				line = br.readLine();
			}
			System.out.println("����SiServletNormal����ӦΪ:" + result.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ͼ��ܺ����Ϣ
	 */
	public void sendEncryptMsg() {
		try {
			pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair(("param1"), "obama"));
			pairs.add(new BasicNameValuePair(("param2"), "��"));
			pairs.add(new BasicNameValuePair(("param3"), "��"));
			pairs.add(new BasicNameValuePair(("param4"), "ɽ��"));
			HttpEntity reqEntity = new UrlEncodedFormEntity(pairs, "UTF-8");
			httpPost.setEntity(reqEntity);
			// httpPost.setHeader("Cookie", "TOKEN=1234567890");
			HttpResponse response = client.execute(httpPost);
			/**
			 * ��ȡ��Ӧ��Ϣ
			 */
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = br.readLine()) != null) {
				result.append(line);
				line = br.readLine();
			}
			System.out.println("����SiServlet����ӦΪ:" + result.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		new AdcClient().sendMsg();
		new AdcClient().sendEncryptMsg();
	}
}
