package default1;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;


public class EventoController {
    
    
	private static String normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
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
		
			DefaultTableModel model = (DefaultTableModel) FinestraUtente.eventotable.getModel();
			int i;
			int j = model.getRowCount();
			for (i=0; i<j; i++)
            model.removeRow(0);
			
			List<Evento> risultati = EventoDAO.cerca( nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo);
			
			if (risultati.isEmpty())
				FinestraUtente.messaggio.setText("Nessun risultato trovato");
			else {
			//String prezzocurr;
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
						model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(),curr.getDataInserimento(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), prezzocurr});
					}
					else {
						model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(),curr.getDataInserimento(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), "Non disponibile"});
					}
				}
			}
		}
		catch(Exception E) {
			  //
		}
	}

	public static Evento cerca(String Nome) {
		return EventoDAO.cerca(Nome);
	}
	
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
						
				nome=normalizza(nome);
				doubleprezzoiniziale=normalizzaPrezzo(doubleprezzoiniziale);
				doubleprezzofinale=normalizzaPrezzo(doubleprezzofinale);
				
				EventoDAO.inserisciModifica(nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo, datainserimento);
				   
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
				
				if (!(prezzoiniziale.equals(""))) doubleprezzoiniziale=Double.parseDouble(prezzoiniziale.replaceAll(",", "."));
				if (!(prezzofinale.equals(""))) doubleprezzofinale=Double.parseDouble(prezzofinale.replaceAll(",", "."));
				if (!(maxspettatori.equals(""))) intmaxspettatori=Integer.parseInt(maxspettatori);
					
				nome=normalizza(nome);
				doubleprezzoiniziale=normalizzaPrezzo(doubleprezzoiniziale);
				doubleprezzofinale=normalizzaPrezzo(doubleprezzofinale);
			
				SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
				Date now = new Date();
				String datacorrente = sdfDate.format(now);
			
				EventoDAO.inserisciModifica(nome,  data,  doubleprezzoiniziale,  doubleprezzofinale,  intmaxspettatori,  tipo, luogo, datacorrente);
				FinestraUtente.eventoClear.doClick();
				FinestraUtente.messaggio.setText("<html><font color=\"green\">Evento inserito correttamente </font></html>");

			}
		   
		   else {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto</font></html>");
		   }
		}
		catch(Exception e1) {
			//do something
		}
	}
	
	public static void elimina(String Nome) {
		if(BigliettoController.isBigliettiVendutiEvento(Nome)==false) {
			EventoDAO.elimina(Nome);
			DefaultTableModel model = (DefaultTableModel) FinestraUtente.eventotable.getModel();
			int i;
			int j = model.getRowCount();
			for (i=0; i<j; i++)
				model.removeRow(0);
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Evento eliminato correttamente </font></html>");
		}else{
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Evento non eliminabile, c'è almeno un biglietto venduto</font></html>");
		}
	}
	
	public static void eliminaPerLuogo(String Luogo) {
		//metodo che elimina tutti gli eventi che hanno un dato luogo, non svolge alcun controllo se vi sono o meno biglietti venduti per ogni evento (invocabile solo dopo un controllo di LuogoController)
		List<Evento> risultati = EventoDAO.cerca("","", 0.00, 0.00, 0, "", Luogo);
		for(Evento curr:risultati) {
			EventoDAO.elimina(curr.getNome());
		}
	}
	
	public static boolean isInteger (String testo) throws Exception {
		 
		try {
			  if(testo.equals("")) return true;
			  Integer.parseInt(testo);
			  return true;
		 }catch(Exception E) {
			 return false;
		 }
	}
		
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
	
	public static void generaStatisticheEvento(String nome, String dataInserimento, String dataEvento) {
		//1: ricerca i biglietti per evento
		List<Biglietto> Biglietti=BigliettoController.bigliettiVendutiEvento(nome);
		Evento curr=EventoController.cerca(nome);
		//2: calcola la differenza in mesi tra data finale e iniziale
		long n=ChronoUnit.MONTHS.between(StringToDate(dataInserimento), StringToDate(dataEvento));
		//3: dichiaro due array, uno per i valori e uno per mese e anno
		String[] intervallo = null;
		double valori[]=null;
		int mesecurr=curr.getMese();
		int annocurr=curr.getAnno();
		//popolo l'array intervallo
		for(int i=0;i<n;i++) {
			intervallo[i]=mesecurr+"/"+annocurr;
			if(mesecurr%12==0) annocurr=annocurr+1;
		}
		//popolo l'array con i valori che ottengo come numero di mesi di differenza tra la data di acquisto del biglietto e la data di inserimento dell'evento
		for(int j=0;j<n;j++) valori[j]=0;
		long indice=0;
		for(Biglietto currBiglietto:Biglietti) {
			indice=ChronoUnit.MONTHS.between(StringToDate(currBiglietto.getDataAcquisto()), StringToDate(currBiglietto.getDataAcquisto()));
			valori[(int) indice]=valori[(int) indice]+currBiglietto.getPrezzo();
		}
	}
	
	public static LocalDate StringToDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
		LocalDate LocalDataCurr;
		LocalDataCurr = LocalDate.parse(data, formatter);
		return LocalDataCurr;
	}
	
}
	
