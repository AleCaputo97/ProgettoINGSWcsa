package default1;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class LuogoController {

	private static String normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
		return string;
		
	}
	
	public static void cerca (String nome, String citt�, String stato, String indirizzo) {
		
		
		nome=normalizza(nome);
		citt�=normalizza(citt�);
		stato=normalizza(stato);
		indirizzo=normalizza(indirizzo);
		
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.luogotable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
        
		List<Luogo> risultati = LuogoDAO.cerca(nome,citt�,stato,indirizzo);
		
		for(Luogo curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getCitt�(), curr.getStato(), curr.getIndirizzo()});
	       }
		
}

	
	
	public static void inserisci (String nome, String citt�, String stato, String indirizzo) {
		//Controllo iniziale: se c'� un campo vuoto in un inserimento questi deve essere impedito
		if((!nome.equals("")&&!citt�.equals("")&&!stato.equals("")&&!indirizzo.equals(""))) {


			
			nome=normalizza(nome);
			citt�=normalizza(citt�);
			stato=normalizza(stato);
			indirizzo=normalizza(indirizzo);
		LuogoDAO.inserisciModifica(nome,citt�,stato,indirizzo);
		}else {
			FinestraUtente.messaggio.setText("ERRORE: Almeno uno dei campi � vuoto");
			System.out.println("errore, almeno uno dei campi � vuoto");
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
	
	
