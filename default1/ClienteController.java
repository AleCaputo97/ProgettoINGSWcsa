package default1;

public class ClienteController {
    public FinestraUtente window;
    public ClienteDAO clienteDAOcurr;
    
    public ClienteController(FinestraUtente Window,ClienteDAO ClienteDAOcurr) {
    	window = Window;
    	clienteDAOcurr = ClienteDAOcurr;
    }
    
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
		
		ClienteDAO.cerca(nome, cognome, email, codicefiscale, data);
	
}
	
}
