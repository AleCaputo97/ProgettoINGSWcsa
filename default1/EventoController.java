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
		
		/*DecimalFormat df = new DecimalFormat(".##");
		df.setRoundingMode(RoundingMode.DOWN);
		String prezzostringa;
		
		if (!(prezzo==0.00)) {}
		
			prezzostringa = df.format(prezzo);
			prezzostringa.replaceAll(",",".");
			prezzo = Double.parseDouble(prezzostringa);
			
		*/	
		return prezzo;
			
	}
		
		

public static void cerca (String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {
		
		nome=normalizza(nome);
		prezzoiniziale=normalizzaPrezzo(prezzoiniziale);
		prezzofinale=normalizzaPrezzo(prezzofinale);
		
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.eventotable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
      List<Evento> risultati = EventoDAO.cerca( nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo);
      //String prezzocurr;
      SimpleDateFormat sdfDate = new SimpleDateFormat("d MMMM yyyy", Locale.ITALIAN);//dd/MM/yyyy
      Date Datacurr = new Date();
      String strDatacurr = sdfDate.format(Datacurr); //Data corrente nel formato cercato
      String DataInserimento, DataEvento; //parametri che prende dalla clsse Evento
      double prezzoIniziale, prezzoFinale;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
      LocalDate LocalDataInserimento, LocalDataEvento, LocalDataCurr;
      LocalDataCurr = LocalDate.parse(strDatacurr, formatter);
      double risultato = 0, differenzaOdiernaIniziale, differenzaFinaleIniziale, prezzocurr;
      for(Evento curr:risultati) {
			LocalDataInserimento = LocalDate.parse(curr.getDataInserimento(), formatter);
			LocalDataEvento = LocalDate.parse(curr.getData(), formatter);
		    differenzaOdiernaIniziale = ChronoUnit.DAYS.between(LocalDataInserimento, LocalDataCurr);
		    differenzaFinaleIniziale = ChronoUnit.DAYS.between(LocalDataInserimento, LocalDataEvento);
		   System.out.println("evento in corso + diff od in + diff fin in" + curr.getNome() + differenzaOdiernaIniziale + differenzaFinaleIniziale);
		    prezzocurr = (differenzaOdiernaIniziale/differenzaFinaleIniziale * (curr.getPrezzoFinale() - curr.getPrezzoIniziale()) + curr.getPrezzoIniziale());
		    double differenza = differenzaOdiernaIniziale/differenzaFinaleIniziale * (curr.getPrezzoFinale() - curr.getPrezzoIniziale());
		    System.out.println("differenza e totale " + differenza + prezzocurr);
			model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo(), prezzocurr});
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
	
	

}
	
