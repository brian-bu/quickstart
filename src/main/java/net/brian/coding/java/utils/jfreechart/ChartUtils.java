package net.brian.coding.java.utils.jfreechart;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public final class ChartUtils {
	
	private ChartUtils() {}
	
	private static String tempDirName = "E:\\TDocs";

	/**
	 * ����һ����״ͼ
	 */
	public static void generatePieChartViaServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// res.setCharacterEncoding("utf-8");
		res.setContentType("image/png");
		DefaultPieDataset data = getPieDataSet();
		// JFreeChart chart = ChartFactory.createPieChart3D("Bug Stastics", //
		// ͼ�����
		// "Time", // Ŀ¼�����ʾ��ǩ
		// "Milliseconds", // ��ֵ�����ʾ��ǩ
		// dataset, // ���ݼ�
		// PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
		// true, // �Ƿ���ʾͼ�� ( ���ڼ򵥵���״ͼ������ false)
		// false, // �Ƿ����ɹ���
		// false // �Ƿ����� URL ����
		// );
		// ��������Ҫ�õ�jcommon�İ������������������
		JFreeChart chart = ChartFactory.createPieChart3D("Bug Stastics", data, false, false, false);
		// ����ͼ��İٷֱ�.
		PiePlot plot = (PiePlot) chart.getPlot();
		// ͼƬ����ʾ�ٷֱ�:Ĭ�Ϸ�ʽ
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		// ��ͼ�������ָ����·�������ظ�·����
		createTempDir(req, chart, 400, 300);
		// ��ͼ��������������.
		ChartUtilities.writeChartAsJPEG(res.getOutputStream(), 1.0f, chart, 400, 300, null);
	}

	/**
	 * ��ȡһ����ʾ�õļ����ݼ����� ���������ع��ɿ��Դ���������Դ��API
	 * 
	 * @return
	 */
	private static DefaultPieDataset getPieDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("A", 100);
		dataset.setValue("B", 200);
		dataset.setValue("C", 300);
		dataset.setValue("D", 400);
		dataset.setValue("E", 500);
		return dataset;
	}
	
	private static CategoryDataset getBarDataSet() {
		// ����ά��
		String[] seasonCategories = {"Spring", "Summer", "Fall", "Winter"};
		// ����ά��
		String[] languageCategories = {"Java", "C/C++", "PHP"};
		DefaultCategoryDataset defaultSet = new DefaultCategoryDataset();
		// �漰������ά�ȵĽ��棬��ȻҪ�õ�˫��forѭ����
		for(int i = 0; i < seasonCategories.length; i++) {
			String seasonCategory = seasonCategories[i];
			for(int j = 0; j < languageCategories.length; j++) {
				String languageCategory = languageCategories[j];
				defaultSet.addValue(new Random().nextInt(100), languageCategory, seasonCategory);
			}
		}
		return defaultSet;
	}

	private static String createTempDir(HttpServletRequest request, JFreeChart chart, int w, int h) throws IOException {
		
		String prefix = ServletUtilities.getTempFilePrefix();
		if (request.getSession() == null) {
			prefix = ServletUtilities.getTempOneTimeFilePrefix();
		}
		File tempFile = File.createTempFile(prefix, ".png", new File(tempDirName));
		try {
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			ChartUtilities.saveChartAsPNG(tempFile, chart, w, h, info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempDirName + tempFile.getName();
	}

	/*
	 * ������״ͼ
	 */
	public static void generateBarChartViaServlet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JFreeChart chart = ChartFactory.createBarChart3D("Latency in ms", // ͼ�����
				"Time", // Ŀ¼�����ʾ��ǩ
				"Milliseconds", // ��ֵ�����ʾ��ǩ
				getBarDataSet(), // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ�� ( ���ڼ򵥵���״ͼ������ false)
				false, // �Ƿ����ɹ���
				false // �Ƿ����� URL ����
		);
		chart.getCategoryPlot().addRangeMarker(new ValueMarker(1.00));
		// setAttribute(chart);
		createTempDir(request, chart, 500, 270);
	}
}
