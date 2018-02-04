package default1;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		
		List<Evento> risultati = EventoController.cercaTuttiEventi();	
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
		FinestraUtente.statPanel.removeAll();
		FinestraUtente.statPanel.add(chartPanel);
		FinestraUtente.statPanel.revalidate();

		
	}
	
	
	public static void NumeroEventiLuogo () {
		int i=0,count=0;
		List<Luogo> Luoghi =  LuogoController.cercaTuttiLuoghi();
		for (Luogo currluogo:Luoghi)count=count+1;
		int[] valore=new int[(int) count];
		String[] nomiluoghi = new String[(int) count];
		for (Luogo currluogo2:Luoghi){
			nomiluoghi[i]=currluogo2.getNome();
			List<Evento> Eventi = EventoController.cercaPerLuogo(currluogo2.getNome());
				for(Evento currevento:Eventi)valore[i]=valore[i]+1;
			i=i+1;
		}
				DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
				for(int j=0;j<count;j++) dataset.addValue( valore[j] , nomiluoghi[j] , "luogo");  
				BarChart_AWT chart = new BarChart_AWT("Istogramma","Numero di eventi per luogo", "", "Numero", dataset);
				ChartPanel chartPanel = new ChartPanel(chart.chart);
				
				FinestraUtente.statPanel.removeAll();
				FinestraUtente.statPanel.add(chartPanel);
				FinestraUtente.statPanel.revalidate();
				

		}
	
	
	
	public static void FasceEtaClienti () {
		int classe1=0,classe2=0,classe3=0,classe4=0,classe5=0,classe6=0,classe7=0;
		List<Cliente> Clienti=ClienteController.cercaTuttiClienti();
		SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
		Date Datacurr = new Date();
		String strDatacurr = sdfDate.format(Datacurr);
		for(Cliente currCliente:Clienti){
				if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<9){
							classe1=classe1+1;
					}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>10 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<19 ) {
							classe2=classe2+1;
					}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>20 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<29 ) {
							classe3=classe3+1;
					}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>30 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<39 ) {
							classe4=classe4+1;
					}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>40 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<49 ) {
							classe5=classe5+1;
					}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>50 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<59 ) {
							classe6=classe6+1;
					}else{
							classe7=classe7+1;
					}
						
						}
				String[] Età = {"<10","(10,20)","(20,30)","(30,40)","(40,50)","(50,60)","61+"};
				int[] valori4 = {classe1,classe2,classe3,classe4,classe5,classe6,classe7};
				DefaultCategoryDataset dataset4 = new DefaultCategoryDataset( ); 
				for(int i=0;i<7;i++) dataset4.addValue( valori4[i] , "plot1" , Età[i]);
				LineChart chart4= new LineChart( "", "Fasce d'età dei clienti", "", "Numero clienti", dataset4);
				ChartPanel chartPanel4 = new ChartPanel(chart4.chart);
				chartPanel4.setSize(new java.awt.Dimension( 1000 , 1000 ));
				FinestraUtente.statPanel.removeAll();
				FinestraUtente.statPanel.add(chartPanel4);
				FinestraUtente.statPanel.revalidate();
		
		
	}
	
	public static void EventiRicavati () {
		List<Evento> Eventi=EventoController.cercaTuttiEventi();
		int i=0;
		for(Evento currEvento:Eventi) i=i+1; //numero eventi
		String[] intervallo=new String[i];
		double[] valori=new double[i];
		double ricavatoCorrente=0;
		int j=0;
			for(Evento currEvento:Eventi){
			//calcola i ricavati di evento corrente, dopodiché li inserisce nell'array
				List<Biglietto> Biglietti = BigliettoController.bigliettiVendutiEvento(currEvento.getNome());
				for(Biglietto bigliettoCurr:Biglietti){
					ricavatoCorrente=ricavatoCorrente+bigliettoCurr.getPrezzo();
					}
				intervallo[j]=currEvento.getNome();
				valori[j]=ricavatoCorrente;
				//prepara la prossima iterazione
				j=j+1;
				ricavatoCorrente=0;
			}
				DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
				for(int k=0;k<i;k++) dataset.addValue( valori[k] , intervallo[k] , "Eventi");  
				BarChart_AWT chart = new BarChart_AWT("Istogramma","Ricavato per evento", "", "Euro", dataset);
				ChartPanel chartPanel = new ChartPanel(chart.chart);
				
				FinestraUtente.statPanel.removeAll();
				FinestraUtente.statPanel.add(chartPanel);
				FinestraUtente.statPanel.revalidate();
		}
	
				public static LocalDate StringToDate(String data) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
					LocalDate LocalDataCurr;
					LocalDataCurr = LocalDate.parse(data, formatter);
					return LocalDataCurr;
				}	
}
