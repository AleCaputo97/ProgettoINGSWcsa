package control;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import org.jfree.data.category.DefaultCategoryDataset;

import boundary.*;
import entity.*;

public class EventoController {
	static EventoDAO eventoDAO;
	static StatisticheEvento frame;
	static BigliettoController bigliettoController;
	static ClienteController clienteController;
	
	public EventoController(EventoDAO InputEventoDAO) {
		eventoDAO=InputEventoDAO;
	}
	
	private static String normalizza (String string) {
		if (!(string.equals(""))) string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		return string;	
	}
	
	private static double normalizzaPrezzo (double prezzo) {
		if (!(prezzo==0.00)) {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			String prezzostringa;
			prezzostringa = df.format(prezzo);
			prezzostringa=prezzostringa.replaceAll(",",".");
			prezzo = Double.parseDouble(prezzostringa);
		}
		return prezzo;
	}
		
		
	//tutte le possibili richieste della finestra utente sono gestite da questo metodo
	//Cerca gli eventi filtrando per i campi inseriti nella maschera di evento
	public static void cerca (String nome, String data, String prezzoiniziale, String prezzofinale, String maxspettatori, String tipo, String luogo) {
		try {	
			double doubleprezzoiniziale = 00.00;
			double doubleprezzofinale = 00.00;
			int intmaxspettatori = 0;
			if(isDouble(prezzoiniziale)==false || isDouble(prezzofinale)==false || isInteger(maxspettatori)==false) {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!</font></html>");
				return;
			}
			if (!(prezzoiniziale.equals(""))) doubleprezzoiniziale=Double.parseDouble(prezzoiniziale.replaceAll(",", "."));
			if (!(prezzofinale.equals(""))) doubleprezzofinale=Double.parseDouble(prezzofinale.replaceAll(",", "."));
			if (!(maxspettatori.equals(""))) intmaxspettatori=Integer.parseInt(maxspettatori);
			nome=normalizza(nome);
			doubleprezzoiniziale=normalizzaPrezzo(doubleprezzoiniziale);
			doubleprezzofinale=normalizzaPrezzo(doubleprezzofinale);
			FinestraUtente.azzeraTabellaEvento();
			List<Evento> risultati = eventoDAO.cerca( nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo);
			if (risultati.isEmpty()) {
				FinestraUtente.messaggio.setText("Nessun risultato trovato");
			}else {
				SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
				Date Datacurr = new Date();
				String strDatacurr = sdfDate.format(Datacurr); //Data corrente nel formato cercato
				String DataInserimento, DataEvento; //parametri che prende dalla clsse Evento
				double prezzoIniziale, prezzoFinale;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
				LocalDate LocalDataInserimento, LocalDataEvento, LocalDataCurr;
				LocalDataCurr = LocalDate.parse(strDatacurr, formatter);
				double risultato = 0, differenzaOdiernaIniziale, differenzaFinaleIniziale, prezzocurr, differenzaOdiernaFinale;
			
				for(Evento curr:risultati) {
					LocalDataInserimento = LocalDate.parse(curr.getDataInserimento(), formatter);
					LocalDataEvento = LocalDate.parse(curr.getData(), formatter);
					differenzaOdiernaFinale = ChronoUnit.DAYS.between(LocalDataCurr, LocalDataEvento);
					if (differenzaOdiernaFinale >= 0) {
						differenzaOdiernaIniziale = ChronoUnit.DAYS.between(LocalDataInserimento, LocalDataCurr);
						differenzaFinaleIniziale = ChronoUnit.DAYS.between(LocalDataInserimento, LocalDataEvento);
						prezzocurr = normalizzaPrezzo(differenzaOdiernaIniziale/differenzaFinaleIniziale * (curr.getPrezzoFinale() - curr.getPrezzoIniziale()) + curr.getPrezzoIniziale());
						FinestraUtente.aggiungiElementoEvento( curr.getNome(), curr.getLuogo(), curr.getData(),curr.getDataInserimento(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(),String.valueOf(prezzocurr));
					}
					else{
						FinestraUtente.aggiungiElementoEvento( curr.getNome(), curr.getLuogo(), curr.getData(),curr.getDataInserimento(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), "Non disponibile");
					}
				}
			}
		}
		catch(Exception E) {
			  //
		}
	}
	//i seguenti metodi sono necessari perché serve un valore di ritorno che è una lista di Eventi
	//ricerca per nome soltanto
	public static Evento cerca(String Nome) {
		return eventoDAO.cerca(Nome);
	}
	//ricerca per luogo
	public static List<Evento> cercaPerLuogo(String NomeLuogo) {
		return eventoDAO.cerca("", "", 0.00, 0.00, 0, "", NomeLuogo);
	}
	public static List<Evento> cercaTuttiEventi(){
		return eventoDAO.cerca("", "", 0.00, 0.00, 0, "", "");
	}
	
	
	//Modifica un evento selezionato usando i campi inseriti nella maschera
	public static boolean modifica (String nome, String data, String datainserimento, String prezzoiniziale, String prezzofinale, String maxspettatori, String tipo, String luogo) {
		try {
			//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
			if(!(nome.equals("")) && !(data.equals("")) && !(prezzoiniziale.equals("")) && !(prezzofinale.equals("")) && !(maxspettatori.equals("")) && !(tipo.equals(""))&& !(luogo.equals(""))) {
				double doubleprezzoiniziale = 00.00;
				double doubleprezzofinale = 00.00;
				int intmaxspettatori = 0;
				if(isDouble(prezzoiniziale)==false || isDouble(prezzofinale)==false || isInteger(maxspettatori)==false) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!</font></html>");
					return false;
				}
				if (!(prezzoiniziale.equals(""))) doubleprezzoiniziale=Double.parseDouble(prezzoiniziale.replaceAll(",", "."));
				if (!(prezzofinale.equals(""))) doubleprezzofinale=Double.parseDouble(prezzofinale.replaceAll(",", "."));
				if (!(maxspettatori.equals(""))) intmaxspettatori=Integer.parseInt(maxspettatori);
				if(doubleprezzoiniziale<=0 || doubleprezzofinale<=0 ) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">“ERRORE:  Prezzo iniziale e finale devono essere >=0! ”</font></html>");
					return false;
				}
				if(doubleprezzoiniziale>doubleprezzofinale) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: prezzo finale minore del prezzo iniziale </font></html>");
					return false;
				}
				if(intmaxspettatori<=0) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE:   il numero massimo di spettatori deve essere maggiore di 0 </font></html>");
					return false;
				}
				nome=normalizza(nome);
				doubleprezzoiniziale=normalizzaPrezzo(doubleprezzoiniziale);
				doubleprezzofinale=normalizzaPrezzo(doubleprezzofinale);
				eventoDAO.inserisciModifica(nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo, datainserimento);
				FinestraUtente.eventoClear.doClick();
				FinestraUtente.messaggio.setText("<html><font color=\"green\">Evento modificato correttamente </font></html>");
				return true;
			}
			   
			else {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto</font></html>");
				return false;
			}
			
		}
		catch(Exception e1) {
			return false;
		}
	}
	
	//Inserisce un evento con i dati inseriti nella maschera evento
	public static void inserisci (String nome, String data, String prezzoiniziale, String prezzofinale, String maxspettatori, String tipo, String luogo) {
		try {
		//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
			if(!(nome.equals("")) && !(data.equals("")) && !(prezzoiniziale.equals("")) && !(prezzofinale.equals("")) && !(maxspettatori.equals("")) && !(tipo.equals(""))&& !(luogo.equals(""))) {
			    double doubleprezzoiniziale = 00.00;
				double doubleprezzofinale = 00.00;
				int intmaxspettatori = 0;
				if(isDouble(prezzoiniziale)==false || isDouble(prezzofinale)==false || isInteger(maxspettatori)==false) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!</font></html>");
					return;
				}
				if((eventoDAO.cerca(normalizza(nome)))!=null) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: nome evento già esistente </font></html>");
					return;
				}
				if (!(prezzoiniziale.equals(""))) doubleprezzoiniziale=Double.parseDouble(prezzoiniziale.replaceAll(",", "."));
				if (!(prezzofinale.equals(""))) doubleprezzofinale=Double.parseDouble(prezzofinale.replaceAll(",", "."));
				if (!(maxspettatori.equals(""))) intmaxspettatori=Integer.parseInt(maxspettatori);
				if(doubleprezzoiniziale<=0 || doubleprezzofinale<=0 ) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">“ERRORE:  Prezzo iniziale e finale devono essere >=0! ”</font></html>");
					return;
				}
				if(doubleprezzoiniziale>doubleprezzofinale) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: prezzo finale minore del prezzo iniziale </font></html>");
					return;
				}
				if(intmaxspettatori<=0) {
					FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE:   il numero massimo di spettatori deve essere maggiore di 0 </font></html>");
					return;
				}
				nome=normalizza(nome);
				doubleprezzoiniziale=normalizzaPrezzo(doubleprezzoiniziale);
				doubleprezzofinale=normalizzaPrezzo(doubleprezzofinale);
				SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
				Date now = new Date();
				String datacorrente = sdfDate.format(now);
				eventoDAO.inserisciModifica(nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo, datacorrente);
				FinestraUtente.eventoClear.doClick();
				FinestraUtente.messaggio.setText("<html><font color=\"green\">Evento inserito correttamente </font></html>");
			}else{
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto</font></html>");
		    }
		}
		catch(Exception e1) {
			//
		}
	}
	
	public static void elimina(String Nome) {
		if(BigliettoController.isBigliettiVendutiEvento(Nome)==false) {
			eventoDAO.elimina(Nome);
			FinestraUtente.azzeraTabellaEvento();
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Evento eliminato correttamente </font></html>");
		}else{
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Evento non eliminabile, c'è almeno un biglietto venduto</font></html>");
		}
	}
	
	
	//metodo che elimina tutti gli eventi che hanno un dato luogo, non svolge alcun controllo se vi sono o meno biglietti venduti per ogni evento (invocabile solo dopo un controllo di LuogoController)	
	public static void eliminaPerLuogo(String Luogo) {
		List<Evento> risultati = eventoDAO.cerca("","", 0.00, 0.00, 0, "", Luogo);
		for(Evento curr:risultati) {
			eventoDAO.elimina(curr.getNome());
		}
	}
	
	//controlla se una stringa puo' essere convertita a intero
	public static boolean isInteger (String testo) throws Exception {	 
		try {
			  if(testo.equals("")) return true;
			  Integer.parseInt(testo);
			  return true;
		 }catch(Exception E) {
			  return false;
		 }
	}
	
	//controlla se una stringa puo' essere convertita a double
	public static boolean isDouble (String testo) throws Exception {
		try {
			if(testo.equals("")) return true;
			testo=testo.replaceAll(",",".");
			Double.parseDouble(testo);
			return true;
		}catch(Exception E) {
			return false;
		}
	}
	
	public static void generaStatisticheEvento(String nome) {
		//STATISTICA1: linechart con valore sulle x dei mesi da data inserimento evento a data stessa dell'evento con i ricavi mese per mese
		//1: ricerca i biglietti per evento
		List<Biglietto> Biglietti=BigliettoController.bigliettiVendutiEvento(nome);
		Evento curr=EventoController.cerca(nome);
		int BigliettiVenduti=0;
		double Ricavato=0;
		//2: calcola la differenza in mesi tra data finale e iniziale
		long n=ChronoUnit.MONTHS.between(StringToDate(curr.getDataInserimento()), StringToDate(curr.getData()));
		if(n==0) 
			n=n+1;
		else 
			n=n+2;
		//3: dichiara due array, uno per i valori e uno per mese e anno
		String[] intervallo = new String[(int) n];
		double valori[]=new double[(int) n];
		int mesecurr=curr.getMeseInserimento();
		int annocurr=curr.getAnnoInserimento();
		//popola l'array intervallo
		for(int i=0;i<n;i++) {
			intervallo[i]=mesecurr+"/"+annocurr;
			mesecurr=mesecurr+1;
			if(mesecurr%12==0) annocurr=annocurr+1;
		}
		//popola l'array con i valori che ottengo come numero di mesi di differenza tra la data di acquisto del biglietto e la data di inserimento dell'evento
		for(int j=0;j<n;j++) valori[j]=0;
		long indice=0;
		for(Biglietto currBiglietto:Biglietti) {
			BigliettiVenduti=BigliettiVenduti+1;
			Ricavato=Ricavato+currBiglietto.getPrezzo();
			indice=ChronoUnit.MONTHS.between(StringToDate(curr.getDataInserimento()),StringToDate(currBiglietto.getDataAcquisto()));
			valori[(int) indice]=valori[(int) indice]+currBiglietto.getPrezzo();
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		for(int i=0;i<n;i++) dataset.addValue( valori[i] , "plot1" , intervallo[i]);  
		LineChart chart= new LineChart( "", "Andamento del guadagno", "", "Guadagno in €", dataset);
		ChartPanel chartPanel = new ChartPanel(chart.getChart());
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		
		//STATISTICA 2 creazione piechart età dei clienti per tale evento
		int classe1=0,classe2=0,classe3=0;
		Cliente currCliente=null;
		SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
        Date Datacurr = new Date();
        String strDatacurr = sdfDate.format(Datacurr);
		for(Biglietto currBiglietto2:Biglietti){
				currCliente=ClienteController.cerca(currBiglietto2.getCodFiscale());
				if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<24){
					classe1=classe1+1;
				}else if(ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))>25 && ChronoUnit.YEARS.between(StringToDate(currCliente.getData()),StringToDate(strDatacurr))<39 ) {
					classe2=classe2+1;
				}else {
					classe3=classe3+1;
				}
		}
		String[] Età = {"<25","(26,40)","41+"};
		int[] valori2 = {classe1,classe2,classe3};
		PieChart piechart= new PieChart("Età degli acquirenti", 3, Età , valori2);
		ChartPanel chartPanel2 = new ChartPanel(piechart.getChart());
		chartPanel2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		chartPanel.setPreferredSize(new java.awt.Dimension( 955 , 430 ));
		chartPanel2.setPreferredSize(new java.awt.Dimension( 955 , 430 ));
		frame = new StatisticheEvento(chartPanel, chartPanel2, Ricavato, BigliettiVenduti);
		frame.setTitle("Statistiche relative a: " + nome);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
	}
	
	public static LocalDate StringToDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
		LocalDate LocalDataCurr;
		LocalDataCurr = LocalDate.parse(data, formatter);
		return LocalDataCurr;
	}
	
}
	
