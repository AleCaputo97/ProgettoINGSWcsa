package default1;
import java.awt.Color;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/*PerAggiungere Statistiche
ChartPanel chartPanel = new ChartPanel(JFreeChart);
statPanel.add(chartPanel);
*/

public class StatisticheGeneraliController {
	
	public static void TipoEventoPerLuogo () {
		
		String[] Tipologia = {"Concerto", "Evento sportivo", "Teatro", "Convegno", "Mostra","Altro"};
		int[] Valori = {0,0,0,0,0,0};
		
		List<Evento> risultati = EventoDAO.cerca( "",  "",  0.0,  0.0, 0, "" ,  "");	
		for(Evento curr:risultati) {
			if (curr.getTipo().equals("Concerto"))
				Valori[0]=Valori[0]+1;
			if (curr.getTipo().equals("Evento sportivo"))
				Valori[1]=Valori[1]+1;
			if (curr.getTipo().equals("Teatro"))
				Valori[2]=Valori[2]+1;
			if (curr.getTipo().equals("Convegno"))
				Valori[3]=Valori[3]+1;
			if (curr.getTipo().equals("Mostra"))
				Valori[4]=Valori[4]+1;
			if (curr.getTipo().equals("Altro"))
				Valori[5]=Valori[5]+1;
				
		}
		PieChart statistica1 = new PieChart("Tipo evento per luogo", 6, Tipologia, Valori);
		ChartPanel chartPanel = new ChartPanel(statistica1.chart);
		FinestraUtente.statPanel.remove(chartPanel);
		FinestraUtente.statPanel.add(chartPanel);
		FinestraUtente.statPanel.repaint();

		
	}
	
	
}
