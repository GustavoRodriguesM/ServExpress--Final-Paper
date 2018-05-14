package br.com.web.servexpress.reports;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ChartsTemplates {

	public static JFreeChart generatePieChart(String title, List<ChartModel> values) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		
		for (ChartModel chart : values) {
			dataSet.setValue(chart.getDescricao(), chart.getValor());
		}

		JFreeChart chart = ChartFactory.createPieChart(
				title, dataSet, true, true, false);

		return chart;
	}

	public static JFreeChart generateBarChart() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.setValue(791, "Population", "1750 AD");
		dataSet.setValue(978, "Population", "1800 AD");
		dataSet.setValue(1262, "Population", "1850 AD");
		dataSet.setValue(1650, "Population", "1900 AD");
		dataSet.setValue(2519, "Population", "1950 AD");
		dataSet.setValue(6070, "Population", "2000 AD");

		JFreeChart chart = ChartFactory.createBarChart(
				"World Population growth", "Year", "Population in millions",
				dataSet, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}
}