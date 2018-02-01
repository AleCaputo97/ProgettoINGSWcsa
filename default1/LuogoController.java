package default1;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class LuogoController {

	private static String normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
		return string;
		
	}
	
	public static void cerca (String nome, String città, String stato, String indirizzo) {
		
		nome=normalizza(nome);
		città=normalizza(città);
		stato=normalizza(stato);
		indirizzo=normalizza(indirizzo);
		
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.luogotable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
        
		List<Luogo> risultati = LuogoDAO.cerca(nome,città,stato,indirizzo);
		
		if (risultati.isEmpty())
			FinestraUtente.messaggio.setText("Nessun risultato trovato");
		else {
			for(Luogo curr:risultati) {
				model.addRow (new Object[]{curr.getNome(), curr.getCittà(), curr.getStato(), curr.getIndirizzo()});
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

		}
		else {
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
		}
		else {
			FinestraUtente.messaggio.setText("<html><font color=\"red\">ERRORE: Almeno uno dei campi è vuoto </font></html>");
			return false;
		}
	}
	
	public static void elimina(String nome) {
		if (BigliettoController.isBigliettiVendutiLuogo(nome)==false) {
		 EventoController.eliminaPerLuogo(nome);
			LuogoDAO.elimina(nome);
			DefaultTableModel model = (DefaultTableModel) FinestraUtente.luogotable.getModel();
			int i;
			int j = model.getRowCount();
			for (i=0; i<j; i++)
				model.removeRow(0);
			FinestraUtente.messaggio.setText("<html><font color=\"red\">Luogo eliminato correttamente </font></html>");

			}else {
				FinestraUtente.messaggio.setText("<html><font color=\"red\">Luogo non eliminabile, c'è almeno un biglietto venduto</font></html>");
			}
		}
	
	}
	
