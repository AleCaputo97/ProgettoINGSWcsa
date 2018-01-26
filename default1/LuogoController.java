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
		
		for(Luogo curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getCittà(), curr.getStato(), curr.getIndirizzo()});
	       }
		
}

	
	
	public static void inserisci (String nome, String città, String stato, String indirizzo) {
		//Controllo iniziale: se c'è un campo vuoto in un inserimento questi deve essere impedito
		if((!nome.equals("")&&!città.equals("")&&!stato.equals("")&&!indirizzo.equals(""))) {


			
			nome=normalizza(nome);
			città=normalizza(città);
			stato=normalizza(stato);
			indirizzo=normalizza(indirizzo);
		LuogoDAO.inserisciModifica(nome,città,stato,indirizzo);
		}else {
			FinestraUtente.messaggio.setText("ERRORE: Almeno uno dei campi è vuoto");
			System.out.println("errore, almeno uno dei campi è vuoto");
		}
	}
	
	public static void elimina(String nome) {
		LuogoDAO.elimina(nome);
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.luogotable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
		}
	
	

}
	
	
