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
	 * 生成一个饼状图
	 */
	public static void generatePieChartViaServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// res.setCharacterEncoding("utf-8");
		res.setContentType("image/png");
		DefaultPieDataset data = getPieDataSet();
		// JFreeChart chart = ChartFactory.createPieChart3D("Bug Stastics", //
		// 图表标题
		// "Time", // 目录轴的显示标签
		// "Milliseconds", // 数值轴的显示标签
		// dataset, // 数据集
		// PlotOrientation.VERTICAL, // 图表方向：水平、垂直
		// true, // 是否显示图例 ( 对于简单的柱状图必须是 false)
		// false, // 是否生成工具
		// false // 是否生成 URL 链接
		// );
		// 这句代码需要用到jcommon的包，否则会产生编译错误。
		JFreeChart chart = ChartFactory.createPieChart3D("Bug Stastics", data, false, false, false);
		// 设置图表的百分比.
		PiePlot plot = (PiePlot) chart.getPlot();
		// 图片中显示百分比:默认方式
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		// 将图表输出到指定的路径并返回该路径。
		createTempDir(req, chart, 400, 300);
		// 将图表输出到浏览器上.
		ChartUtilities.writeChartAsJPEG(res.getOutputStream(), 1.0f, chart, 400, 300, null);
	}

	/**
	 * 获取一个演示用的简单数据集对象 将来可以重构成可以处理数据来源的API
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
		// 季度维度
		String[] seasonCategories = {"Spring", "Summer", "Fall", "Winter"};
		// 语言维度
		String[] languageCategories = {"Java", "C/C++", "PHP"};
		DefaultCategoryDataset defaultSet = new DefaultCategoryDataset();
		// 涉及到两个维度的交叉，必然要用到双重for循环。
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
	 * 生成柱状图
	 */
	public static void generateBarChartViaServlet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JFreeChart chart = ChartFactory.createBarChart3D("Latency in ms", // 图表标题
				"Time", // 目录轴的显示标签
				"Milliseconds", // 数值轴的显示标签
				getBarDataSet(), // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例 ( 对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
		);
		chart.getCategoryPlot().addRangeMarker(new ValueMarker(1.00));
		// setAttribute(chart);
		createTempDir(request, chart, 500, 270);
	}
}
