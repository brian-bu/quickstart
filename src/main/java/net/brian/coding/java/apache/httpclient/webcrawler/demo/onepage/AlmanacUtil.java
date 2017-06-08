package net.brian.coding.java.apache.httpclient.webcrawler.demo.onepage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AlmanacUtil {

	/**
	 * ����������
	 */
	private AlmanacUtil() {
	}

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 */
	public static Almanac getAlmanac() {
		String url = "http://tools.2345.com/rili.htm";
		String html = pickData(url);
		Almanac almanac = analyzeHTMLByString(html);
		return almanac;
	}

	/*
	 * ��ȡ��ҳ��Ϣ
	 */
	private static String pickData(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// ��ȡ��Ӧʵ��
				HttpEntity entity = response.getEntity();
				// ��ӡ��Ӧ״̬
				if (entity != null) {
					return EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر�����,�ͷ���Դ
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * ʹ��jsoup������ҳ��Ϣ
	 */
	private static Almanac analyzeHTMLByString(String html) {
		String solarDate, lunarDate, chineseAra, should, avoid = " ";
		Document document = Jsoup.parse(html);
		// ����ʱ��
		solarDate = getSolarDate();
		// ũ��ʱ��
		Element eLunarDate = document.getElementById("info_nong");
		lunarDate = eLunarDate.child(0).html().substring(1, 3) + eLunarDate.html().substring(11);
		// ��ɵ�֧���귨
		Element eChineseAra = document.getElementById("info_chang");
		chineseAra = eChineseAra.text().toString();
		// ��
		should = getSuggestion(document, "yi");
		// ��
		avoid = getSuggestion(document, "ji");
		Almanac almanac = new Almanac(solarDate, lunarDate, chineseAra, should, avoid);
		return almanac;
	}

	/*
	 * ��ȡ��/��
	 */
	private static String getSuggestion(Document doc, String id) {
		Element element = doc.getElementById(id);
		Elements elements = element.getElementsByTag("a");
		StringBuffer sb = new StringBuffer();
		for (Element e : elements) {
			sb.append(e.text() + " ");
		}
		return sb.toString();
	}

	/*
	 * ��ȡ����ʱ��,��yyyy��MM��dd�� EEEE��ʽ��ʾ��
	 * 
	 * @return yyyy��MM��dd�� EEEE
	 */
	private static String getSolarDate() {
		Calendar calendar = Calendar.getInstance();
		Date solarDate = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� EEEE");
		return formatter.format(solarDate);
	}

}