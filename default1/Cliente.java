package default1;

public class Cliente {
	
	//attributi
	private String Nome;
	private String Cognome;
	private String Email;
	private String CodiceFiscale;
	private String Data;
	
	//costruttore
	public Cliente(String nome, String cognome, String email, String codicefiscale, String data) {
		Nome=nome;
		Cognome=cognome;
		Email=email;
		CodiceFiscale=codicefiscale;
		Data=data;
	}
	
	//getter
	public String getNome() {
		return Nome;
	}
	
	public String getCognome() {
		return Cognome;
	}	
	
	public String getEmail() {
		return Email;
	}
	
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	
	public String getData() {
		return Data;
	}

	//setter
	public void setNome(String nome) {
		 Nome=nome;
	}
	
	public void setCognome(String cognome) {
		 Cognome=cognome;
	}
	
	public void setEmail(String email) {
		 Email=email;
	}
	
	public void setCodiceFiscale(String codicefiscale) {
		 CodiceFiscale=codicefiscale;
	}
	
	public void setData(String data) {
		 Data=data;
	}
	
}
