package default1;

import java.util.List;

import javax.swing.table.DefaultTableModel;


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
		
		for(Cliente curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getCognome(), curr.getEmail(), curr.getCodiceFiscale(), curr.getData()});
	    	//System.out.println(curr.getNome());
	       }
}
	public static void inserisci (String nome, String cognome, String email, String codicefiscale, String data) {
		if((!nome.equals("")&&!cognome.equals("")&&!email.equals("")&&!codicefiscale.equals("")&&!data.equals(""))) {
		nome=normalizza(nome);
		cognome=normalizza(cognome);
		email=normalizzaEmail(email);
		codicefiscale=normalizzaCF(codicefiscale);
		ClienteDAO.inserisciModifica(nome, cognome, email, codicefiscale, data);
		}else {
			System.out.println("errore, almeno uno dei campi è vuoto");
		}
	}
	
	public static void elimina(String CodiceFiscale) {
		ConfermaEliminazione frame = new ConfermaEliminazione();
		frame.setVisible(true);
		ClienteDAO.elimina(CodiceFiscale);
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.clientetable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
		}
	
	

}
	
