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
						FinestraUtente.messaggio.setText("Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!");
						return;
					}

				if (!(prezzoiniziale.equals(""))) doubleprezzoiniziale=Double.parseDouble(prezzoiniziale);

				if (!(prezzofinale.equals(""))) doubleprezzofinale=Double.parseDouble(prezzofinale);
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
		    	model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), prezzocurr});
				}else {
					model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), "Non disponibile"});
				}
      		}
		  }catch(Exception E) {
			  //
		  }
		}

	public static void modifica (String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {
		//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
		   if(!(nome.equals("")) && !(data.equals("")) && !(prezzoiniziale==0.00) && !(prezzofinale==0.00) && !(maxspettatori==0) && !(tipo.equals(""))&& !(luogo.equals(""))) {
			
			nome=normalizza(nome);
			prezzoiniziale=normalizzaPrezzo(prezzoiniziale);
			prezzofinale=normalizzaPrezzo(prezzofinale);
		EventoDAO.modifica(nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo);
		FinestraUtente.eventoClear.doClick();
		FinestraUtente.messaggio.setText("<html><font color=\"green\">Evento inserito correttamente </font></html>");

		}
		   
		   else {
			FinestraUtente.messaggio.setText("ERRORE: Almeno uno dei campi è vuoto");
			System.out.println("errore, almeno uno dei campi è vuoto");
		}
	}
	
	public static void inserisci (String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {
		//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
		   if(!(nome.equals("")) && !(data.equals("")) && !(prezzoiniziale==0.00) && !(prezzofinale==0.00) && !(maxspettatori==0) && !(tipo.equals(""))&& !(luogo.equals(""))) {
			
			nome=normalizza(nome);
			prezzoiniziale=normalizzaPrezzo(prezzoiniziale);
			prezzofinale=normalizzaPrezzo(prezzofinale);
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
		    Date now = new Date();
		    String datacorrente = sdfDate.format(now);
			
		EventoDAO.inserisci(nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo, datacorrente);
		FinestraUtente.eventoClear.doClick();
		FinestraUtente.messaggio.setText("<html><font color=\"green\">Evento inserito correttamente </font></html>");

		}
		   
		   else {
			FinestraUtente.messaggio.setText("ERRORE: Almeno uno dei campi è vuoto");
			System.out.println("errore, almeno uno dei campi è vuoto");
		}
	}
	
	public static void elimina(String Nome) {
		EventoDAO.elimina(Nome);
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.eventotable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
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
}
	
