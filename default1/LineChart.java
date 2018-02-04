package default1;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {
	JFreeChart chart;
   public LineChart ( String applicationTitle , String Titolo,String asseX, String asseY, DefaultCategoryDataset dataset) {
      super(applicationTitle);
         chart = ChartFactory.createLineChart(
         Titolo,
         asseX,asseY,
         dataset,
         PlotOrientation.VERTICAL,
         true,true,false);
         


   ChartPanel chartPanel = new ChartPanel( chart );        
   chartPanel.setPreferredSize(new java.awt.Dimension( 650 , 367 ) );        
   setContentPane( chartPanel ); 
   }
}