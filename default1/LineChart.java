package default1;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {
	JFreeChart lineChart;
   public LineChart ( String applicationTitle , String Titolo,String asseX, String asseY, int valori[],int dimValori,String nomiValori[],String linea) {
      super(applicationTitle);
         lineChart = ChartFactory.createLineChart(
         Titolo,
         asseX,asseY,
         createDataset(valori,dimValori,nomiValori,linea),
         PlotOrientation.VERTICAL,
         true,true,false);
         
   }

   private DefaultCategoryDataset createDataset( int valori[],int dimValori,String nomiValori[],String linea ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
     for (int i=0;i<dimValori;i++) {
    	 dataset.addValue( valori[i] , linea , nomiValori[i]);
     }
      return dataset;
   }
   
}