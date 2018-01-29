package default1;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

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
		for(Evento curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getLuogo(), curr.getData(), curr.getPrezzoIniziale(), curr.getPrezzoFinale(), curr.getMassimoSpettatori(), curr.getTipo()});
	       }
		}

	public static void inserisci (String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {
		//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
		   if(!(nome.equals("")) && !(data.equals("")) && !(prezzoiniziale==0.00) && !(prezzofinale==0.00) && !(maxspettatori==0) && !(tipo.equals(""))&& !(luogo.equals(""))) {
			
			nome=normalizza(nome);
			prezzoiniziale=normalizzaPrezzo(prezzoiniziale);
			prezzofinale=normalizzaPrezzo(prezzofinale);
		EventoDAO.inserisciModifica(nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo);
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
	
