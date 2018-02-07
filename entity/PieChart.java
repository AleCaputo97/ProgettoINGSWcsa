package entity;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChart {
	private JFreeChart chart;
	
	public PieChart (String nome, int numerospicchi, String spicchio[], int valore[]) {
		 DefaultPieDataset dataset = new DefaultPieDataset( );
	     int i;
	     for (i=0; i<numerospicchi; i++) dataset.setValue( spicchio[i] , valore[i] );  
	     chart = ChartFactory.createPieChart(      
		         nome,   
		         dataset,             
		         true,       
		         true, 
		         false);
	}
	
	public JFreeChart getChart() {
		return chart;
	}

}
	   
