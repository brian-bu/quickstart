package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.httpclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.config.RequestConfig;

public class DownloadFile {

	// ����URL����ҳ����������Ҫ�������ҳ���ļ�����ȥ��URL�еķ��ļ����ַ�
	public String getFileNameByUrl(String url, String contentType) {
		// �Ƴ�http://
		url = url.substring(7); // ���شӵ�7�������һ���ַ�֮����Ӵ�
		// text/html����
		if (contentType.indexOf("html") != -1) { // �����html���͵��ı�
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} else { // �������html���͵��ı�
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}

	// ������ҳ�ֽ����������ļ�,filepathΪҪ�����ļ�����Ե�ַ
	private void saveToLocal(HttpEntity entity, String filePath) {
		try {

			if (filePath.indexOf("JPG") != -1 || filePath.indexOf("png") != -1 || filePath.indexOf("jpeg") != -1) {
				File storeFile = new File(filePath);
				FileOutputStream output = new FileOutputStream(storeFile);

				// �õ�������Դ���ֽ�����,��д���ļ�
				if (entity != null) {
					InputStream instream = entity.getContent();
					byte b[] = new byte[1024];
					int j = 0;
					while ((j = instream.read(b)) != -1) {
						output.write(b, 0, j);
					}
				}
				output.flush();
				output.close();
				return;
			}

			if (entity != null) {
				InputStream input = entity.getContent();
				DataOutputStream output = new DataOutputStream(new FileOutputStream(new File(filePath)));

				int tempByte = -1;
				while ((tempByte = input.read()) > 0) {
					output.write(tempByte);
				}

				if (input != null) {
					input.close();
				}

				if (output != null) {
					output.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ����URLָ������ҳ
	public String downloadFile(String url) throws IOException {
		String filePath = null;

		// ����CloseableHttpClient�������ò���
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// ִ������
		try {

			// ����GetMethod�����ò���
			HttpGet httpget = new HttpGet(url);

			// ��������ʱ��5����
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
					.setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();

			httpget.setConfig(requestConfig);

			CloseableHttpResponse response = httpclient.execute(httpget);
			// �жϷ���״̬
			int statusCode = response.getStatusLine().getStatusCode();

			// System.out.println("�õ��Ľ��:" +
			// response.getStatusLine().getStatusCode());//�õ�������
			HttpEntity entity = response.getEntity();// �õ��������������

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + response.getStatusLine());
				// System.err.println("Method failed: " +
				// getMethod.getStatusLine());
				filePath = null;
			}
			// ����HTTP��Ӧ����
			// read byte array

			filePath = "Y:\\crawler\\csdntest\\" + getFileNameByUrl(url, entity.getContentType().getValue());

			saveToLocal(entity, filePath);
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal URL!");
		}

		catch (UnknownHostException e) {
			// fatal error
			System.out.println("Please check your provided http address!");
		} catch (IOException e) {
			// web error
			e.printStackTrace();
		} finally {
			// realease connection
			httpclient.close();
		}
		return filePath;
	}

	public static void main(String[] args) throws IOException {
		DownloadFile a = new DownloadFile();
		String tmp = null;
		tmp = a.downloadFile("http://www.lietu.com");
		System.out.println(tmp);
	}
}