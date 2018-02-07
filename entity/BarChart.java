package entity;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChart extends ApplicationFrame {
	
	private JFreeChart chart;
   
	public BarChart( String applicationTitle , String chartTitle, String assex, String assey, DefaultCategoryDataset dataset) {
	    super( applicationTitle );        
	    JFreeChart barChart = ChartFactory.createBarChart( //asse x ha categorie, asse y ha lo score assegnato
	    chartTitle,           
	    assex,            
	    assey,            
	    dataset,          
	    PlotOrientation.VERTICAL,           
	    true, true, false);
	         
	    chart = barChart;
	      
	    ChartPanel chartPanel = new ChartPanel( barChart );        
	    chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	    setContentPane( chartPanel ); 
   }
	
	public JFreeChart getChart() {
		return chart;
	}
	
}