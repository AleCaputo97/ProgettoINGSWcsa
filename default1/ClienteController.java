package default1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class ClienteController {
    
    
	private static String normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
		return string;
		
	}
	
	private static String normalizzaEmail (String email) {
		
		if (!(email.equals("")))
			email = email.toLowerCase();
		
		return email;
		
	}
	
	private static String normalizzaCF (String codicefiscale) {
		
		if (!(codicefiscale.equals("")))
			codicefiscale = codicefiscale.toUpperCase();
		
		return codicefiscale;
		
	}
		
		

	public static void cerca (String nome, String cognome, String email, String codicefiscale, String data) {
		Pattern p= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(email).matches() && !(email.equals(""))) {
			FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: la mail non � nel formato testo@dominio.testo</font></html>");
			return;
		}
		if (!(codicefiscale.equals(""))) {
			if (!(codicefiscale.length()==16))
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Il codice fiscale deve essere di 16 caratteri</font></html>");
			
		}
		else {
		
		nome=normalizza(nome);
		cognome=normalizza(cognome);
		email=normalizzaEmail(email);
		codicefiscale=normalizzaCF(codicefiscale);
		
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.clientetable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
        
		List<Cliente> risultati = ClienteDAO.cerca(nome, cognome, email, codicefiscale, data);
		
		if (risultati.isEmpty())
			FinestraUtente.messaggio.setText("Nessun risultato trovato");
		else {
		
		for(Cliente curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getCognome(), curr.getEmail(), curr.getCodiceFiscale(), curr.getData()});
	    	//System.out.println(curr.getNome());
	       }
		}
		
		}
}
	public static void inserisci (String nome, String cognome, String email, String codicefiscale, String data) {
		
		Pattern p= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		//Controllo iniziale: se c'� un campo vuoto in un inserimento questi deve essere impedito
		if((nome.equals("")||cognome.equals("")||email.equals("")||codicefiscale.equals("")||data.equals(""))) {
			FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi � vuoto </font></html>");
		}
			//Controllo successivo: mail ben posta e codice fiscale di 16 caratteri
			else if(codicefiscale.length()!=16) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Il codice fiscale non � di 16 caratteri </font></html>");
			}
			else if(!p.matcher(email).matches()) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: la mail non � nel formato testo@dominio.testo </font></html>");
			}
			else {
			nome=normalizza(nome);
			cognome=normalizza(cognome);
			email=normalizzaEmail(email);
			codicefiscale=normalizzaCF(codicefiscale);
			ClienteDAO.inserisciModifica(nome, cognome, email, codicefiscale, data);
			FinestraUtente.clienteClear.doClick();
			FinestraUtente.messaggio.setText("<html><font color=\"green\">Cliente inserito correttamente </font></html>");
			}

		}
	
	public static boolean modifica (String nome, String cognome, String email, String codicefiscale, String data) {
		
		Pattern p= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		//Controllo iniziale: se c'� un campo vuoto in un inserimento questi deve essere impedito
		if((nome.equals("")||cognome.equals("")||email.equals("")||codicefiscale.equals("")||data.equals(""))) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi � vuoto </font></html>");
				return false;
		}
			//Controllo successivo: mail ben posta e codice fiscale di 16 caratteri
			else if(codicefiscale.length()!=16) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Il codice fiscale non � di 16 caratteri </font></html>");
				return false;
			}
			else if(!p.matcher(email).matches()) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: la mail non � nel formato testo@dominio.testo </font></html>");
				return false;
			}
			else {
			nome=normalizza(nome);
			cognome=normalizza(cognome);
			email=normalizzaEmail(email);
			codicefiscale=normalizzaCF(codicefiscale);
			ClienteDAO.inserisciModifica(nome, cognome, email, codicefiscale, data);
			FinestraUtente.clienteClear.doClick();
			return true;
			}

		}
	
	public static void elimina(String CodiceFiscale) {
		ClienteDAO.elimina(CodiceFiscale);
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.clientetable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
		FinestraUtente.messaggio.setText("<html><font color=\"red\">Cliente eliminato correttamente </font></html>");
		}

	
	public static void generaStatisticheCliente(String CodiceFiscale) {
		List<Biglietto> Biglietti = BigliettoController.bigliettiPerCliente(CodiceFiscale);
		double SoldiPerMese[]= {0,0,0,0,0,0,0,0,0,0,0,0}, SpesaTotale=0;
		int BigliettiAcquistati=0;
		String Mese[]= {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
		//ricerca i biglietti idonei tra quelli acquistati dal cliente e li aggiunge a soldi per mese al giusto mese
		for(Biglietto curr:Biglietti) {
			//calcola intanto quanti biglietti sono stati acquistati e la spesa totale a prescindere dall'anno
			BigliettiAcquistati=BigliettiAcquistati+1;
			SpesaTotale=SpesaTotale + curr.getPrezzo();
			if(curr.getAnno()==2018) {
				System.out.println("aggiungo all'elemento" + curr.getMese() + "i soldi" + curr.getPrezzo());
				SoldiPerMese[curr.getMese()-1]=SoldiPerMese[curr.getMese()-1]+curr.getPrezzo();
			}
		}
		
		//popola il dataSet da passare all'istogramma con i valori di soldi e mese
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		for(int i=0;i<12;i++) dataset.addValue( SoldiPerMese[i] , Mese[i] , "mese");  
		BarChart_AWT chart = new BarChart_AWT("Istogramma","Soldi spesi dal cliente", "", "Euro", dataset);
		ChartPanel chartPanel = new ChartPanel(chart.chart);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		//crea il PieChart con i luoghi frequentati
		int num_concerti=0, num_eventi_sportivi=0, num_eventi_teatrali=0, num_convegni=0, num_mostre=0, num_altro=0;
		Evento currEvento=null;
		for(Biglietto curr:Biglietti){
		   //query in Evento passando il nome dell'evento
			currEvento=EventoController.cerca(curr.getEvento());
			if(currEvento.getTipo().equals("Concerto")) num_concerti++;
			if(currEvento.getTipo().equals("Evento sportivo")) num_eventi_sportivi++;
			if(currEvento.getTipo().equals("Teatro")) num_eventi_teatrali++;
			if(currEvento.getTipo().equals("Mostra")) num_mostre++;
			if(currEvento.getTipo().equals("Convegno")) num_convegni++;
			if(currEvento.getTipo().equals("Altro")) num_altro++;
			}
		//crea e popola l'array di Tipo[] e valore[]
		String Tipo[] = {"Concerto", "Evento sportivo", "Teatro","Convegno", "Mostra", "Altro"};
		int valori[]={num_concerti,num_eventi_sportivi,num_eventi_teatrali,num_convegni,num_mostre,num_altro};
		PieChart piechart= new PieChart("Tipologie di biglietti acquistati", 6, Tipo, valori);
		ChartPanel chartPanel2 = new ChartPanel(piechart.chart);
		chartPanel2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		StatisticheCliente frame = new StatisticheCliente(chartPanel, chartPanel2, SpesaTotale, BigliettiAcquistati);
		frame.setTitle("Statistiche relative a: " + CodiceFiscale);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	}

}
	
