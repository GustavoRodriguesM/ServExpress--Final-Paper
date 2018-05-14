package br.com.web.servexpress.reports;

import java.util.Collections;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import br.com.web.servexpress.helper.ChartHelper;

public class ChartsTemplates {

	public static JFreeChart generatePieChart(String title, List<ChartModel> values) {
		DefaultPieDataset dataSet = new DefaultPieDataset();

		for (ChartModel chart : values) {
			dataSet.setValue(chart.getDescricao(), chart.getValor());
		}

		JFreeChart chart = ChartFactory.createPieChart(title, dataSet, true, true, false);

		return chart;
	}

	public static JFreeChart generateBarChartHelper(String title, String dataType, String legend, List<ChartHelper> values) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		Collections.reverse(values);
		for (ChartHelper chart : values) {
			dataSet.setValue(chart.getAmount().intValue(), dataType, chart.getMonth());
		}

		JFreeChart chart = ChartFactory.createBarChart(title, dataType, legend, dataSet, PlotOrientation.HORIZONTAL,
				false, false, false);

		return chart;
	}
	
	public static JFreeChart generateBarChartModel(String title, String dataType, String legend, List<ChartModel> values) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		Collections.reverse(values);
		for (ChartModel chart : values) {
			dataSet.setValue(chart.getValor().intValue(), dataType, chart.getDescricao());
		}

		JFreeChart chart = ChartFactory.createBarChart(title, dataType, legend, dataSet, PlotOrientation.HORIZONTAL,
				false, false, false);

		return chart;
	}

}