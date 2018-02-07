package control;

import java.awt.Color;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import boundary.FinestraUtente;
import boundary.StatisticheLuogo;
import boundary.*;
import entity.*;
public class LuogoController {

	private static String normalizza (String string) {
		if (!(string.equals(""))) string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		return string;
	}

	public static void cerca (String nome, String città, String stato, String indirizzo) {
		nome=normalizza(nome);
		città=normalizza(città);
		stato=normalizza(stato);
		indirizzo=normalizza(indirizzo);
        FinestraUtente.azzeraTabellaLuogo();
		List<Luogo> risultati = LuogoDAO.cerca(nome,città,stato,indirizzo);
		if (risultati.isEmpty())
			FinestraUtente.messaggio.setText("Nessun risultato trovato");
		else {
			for(Luogo curr:risultati) {
				FinestraUtente.aggiungiElementoLuogo(curr.getNome(), curr.getCittà(), curr.getStato(), curr.getIndirizzo());
			}
		}
		
}
	
	public static void inserisci (String nome, String città, String stato, String indirizzo) {
		if((!nome.equals("")&&!città.equals("")&&!stato.equals("")&&!indirizzo.equals(""))) {
			nome=normalizza(nome);
			città=normalizza(città);
			stato=normalizza(stato);
			indirizzo=normalizza(indirizzo);
			LuogoDAO.inserisciModifica(nome,città,stato,indirizzo);
			FinestraUtente.luogoClear.doClick();
			FinestraUtente.messaggio.setText("<html><font color=\"green\">Luogo inserito correttamente </font></html>");
		}else{
			FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto </font></html>");
		}
	}
	
	public static boolean modifica (String nome, String città, String stato, String indirizzo) {
		if((!nome.equals("")&&!città.equals("")&&!stato.equals("")&&!indirizzo.equals(""))) {
			nome=normalizza(nome);
			città=normalizza(città);
			stato=normalizza(stato);
			indirizzo=normalizza(indirizzo);
			LuogoDAO.inserisciModifica(nome,città,stato,indirizzo);
			FinestraUtente.luogoClear.doClick();
			FinestraUtente.messaggio.setText("<html><font color=\"blue\">Luogo modificato correttamente </font></html>");
			return true;
		}else{
			FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto </font></html>");
			return false;
		}
	}
	
	public static void elimina(String nome) {
		if (BigliettoController.isBigliettiVendutiLuogo(nome)==false) {
		 EventoController.eliminaPerLuogo(nome);
			LuogoDAO.elimina(nome);
			FinestraUtente.azzeraTabellaLuogo();
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Luogo eliminato correttamente </font></html>");
			}else{
				FinestraUtente.messaggio.setText("<html><font color=\"red\">Luogo non eliminabile, c'è almeno un biglietto venduto</font></html>");
			}
		}
	
	
	 public static List<Luogo> cercaTuttiLuoghi(){
		 return LuogoDAO.cerca("", "", "", "");
	 }
	 
	
	
	public static void generaStatisticheLuogo(String NomeLuogo, String anno) {
		double SpesaTotale=0,GuadagnoMedio=0;
		List<Evento> Eventi=EventoController.cercaPerLuogo(NomeLuogo);
		//3: dichiara due array, uno per i valori e uno per mese e anno
		String[] intervallo = new String[(int) 12];
		double valori[]=new double[(int) 12];
		int annocurr=Integer.parseInt(anno);
		int mesecurr=1;
		int num_eventi=0;
		double ricavato=0;
		//popola l'array intervallo
		for(int i=0;i<12;i++) {
			intervallo[i]=mesecurr+"/"+annocurr;
			mesecurr=mesecurr+1;
		}
		//popola l'array con i valori che ottengo analizzando gli eventi inseriti per cui l'anno corrente corrisponde a in input
		for(int j=0;j<12;j++) valori[j]=0;
		long indice=0;
		for(Evento currEvento:Eventi) {
			num_eventi=num_eventi+1;
			if(currEvento.getAnnoEvento()==annocurr) {
				indice=currEvento.getMeseEvento()-1;
				valori[(int) indice]=valori[(int) indice]+1;
			}
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		for(int i=0;i<12;i++) dataset.addValue( valori[i] , "plot1" , intervallo[i]);  
		LineChart chart= new LineChart( "", "Eventi ospitati nell'anno "+anno, "", "Numero eventi", dataset);
		ChartPanel chartPanel = new ChartPanel(chart.getChart());
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		//Statistica2: numero clienti del luogo per anno

		List<Biglietto> Biglietti=BigliettoController.bigliettiVendutiLuogo(NomeLuogo);
		int OspitiPerMese[]={0,0,0,0,0,0,0,0,0,0,0,0};
		for(Biglietto curr:Biglietti) {
			SpesaTotale=SpesaTotale + curr.getPrezzo();
			if(curr.getAnno()==annocurr) {
				OspitiPerMese[curr.getMese()-1]=OspitiPerMese[curr.getMese()-1]+1;
			}
		}
		GuadagnoMedio=SpesaTotale/num_eventi;
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset( ); 
		for(int i=0;i<12;i++) dataset2.addValue( OspitiPerMese[i] , intervallo[i] , "mese");  
		BarChart chart2 = new BarChart("Istogramma","Numero ospiti del luogo" +anno, "", "Numero ospiti", dataset);
		ChartPanel chartPanel2 = new ChartPanel(chart2.getChart());
		chartPanel2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		chartPanel.setPreferredSize(new java.awt.Dimension( 955 , 430 ));
		chartPanel2.setPreferredSize(new java.awt.Dimension( 955 , 430 ));
		StatisticheLuogo frame = new StatisticheLuogo(chartPanel, chartPanel2, SpesaTotale, num_eventi, GuadagnoMedio, NomeLuogo);
		frame.setTitle("Statistiche relative a: " + NomeLuogo);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
	}

}
	
