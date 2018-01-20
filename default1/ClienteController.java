package default1;

public class ClienteController {
	
	private String Normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
		return string;
		
	}
	
	private String NormalizzaEmail (String email) {
		
		if (!(email.equals("")))
			email = email.toLowerCase();
		
		return email;
		
	}
	
	private String NormalizzaCF (String codicefiscale) {
		
		if (!(codicefiscale.equals("")))
			codicefiscale = codicefiscale.toUpperCase();
		
		return codicefiscale;
		
	}
		
		

	public void cerca (String nome, String cognome, String email, String codicefiscale, String data) {
	
		nome=Normalizza(nome);
		cognome=Normalizza(cognome);
		email=NormalizzaEmail(email);
		codicefiscale=NormalizzaCF(codicefiscale);
		
		//invoca metodo DAO
	
	
}
	
}
