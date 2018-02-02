package default1;

import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/*PerAggiungere Statistiche
ChartPanel chartPanel = new ChartPanel(JFreeChart);
statPanel.add(chartPanel);
*/

public class Statistiche1 {
	
	public static JFreeChart TipoEventoPerLuogo (String luogo) {
	
	String[] A = {"Concerto", "Evento sportivo", "Teatro", "Convegno", "Mostra","Altro"};
	int[] B = {0,0,0,0,0,0};
	
	List<Evento> risultati = EventoDAO.cerca( "",  "",  0.0,  0.0, 0, "" ,  luogo);	
	for(Evento curr:risultati) {
		if (curr.getTipo().equals("Concerto"))
			B[0]=B[0]+1;
		if (curr.getTipo().equals("Evento sportivo"))
			B[1]=B[1]+1;
		if (curr.getTipo().equals("Teatro"))
			B[2]=B[2]+1;
		if (curr.getTipo().equals("Convegno"))
			B[3]=B[3]+1;
		if (curr.getTipo().equals("Mostra"))
			B[4]=B[4]+1;
		if (curr.getTipo().equals("Altro"))
			B[5]=B[5]+1;
			
	}

	PieChart statistica1 = new PieChart("Prova", 6, A, B);
	
	return statistica1.chart;

	}

}

