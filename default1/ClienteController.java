package default1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern p= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(email).matches() && !(email.equals(""))) {
			FinestraUtente.messaggio.setText("ERRORE: la mail non � nel formato testo@dominio.testo");
			return;
		}
		if (!(codicefiscale.equals(""))) {
			if (!(codicefiscale.length()==16))
				FinestraUtente.messaggio.setText("ERRORE: Il codice fiscale deve essere di 16 caratteri");
			
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
		
		for(Cliente curr:risultati) {
			model.addRow (new Object[]{curr.getNome(), curr.getCognome(), curr.getEmail(), curr.getCodiceFiscale(), curr.getData()});
	    	//System.out.println(curr.getNome());
	       }
		}
}
	public static void inserisci (String nome, String cognome, String email, String codicefiscale, String data) {
		//Controllo iniziale: se c'� un campo vuoto in un inserimento questi deve essere impedito
		if((!nome.equals("")&&!cognome.equals("")&&!email.equals("")&&!codicefiscale.equals("")&&!data.equals(""))) {
			//Controllo successivo: mail ben posta e codice fiscale di 16 caratteri
			if(codicefiscale.length()!=16) {
				FinestraUtente.messaggio.setText("ERRORE: Il codice fiscale non � di 16 caratteri");
				return;
			}
			Pattern p= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			if(!p.matcher(email).matches()) {
				FinestraUtente.messaggio.setText("ERRORE: la mail non � nel formato testo@dominio.testo");
				return;
			}
		nome=normalizza(nome);
		cognome=normalizza(cognome);
		email=normalizzaEmail(email);
		codicefiscale=normalizzaCF(codicefiscale);
		ClienteDAO.inserisciModifica(nome, cognome, email, codicefiscale, data);
		}else {
			FinestraUtente.messaggio.setText("ERRORE: Almeno uno dei campi � vuoto");
			System.out.println("errore, almeno uno dei campi � vuoto");
		}
	}
	
	public static void elimina(String CodiceFiscale) {
		ClienteDAO.elimina(CodiceFiscale);
		DefaultTableModel model = (DefaultTableModel) FinestraUtente.clientetable.getModel();
        int i;
        int j = model.getRowCount();
        for (i=0; i<j; i++)
            model.removeRow(0);
		}
	
	

}
	
