package entity;

public class Luogo {
	
	//attributi
	private String Nome;
	private String Citt�;
	private String Stato;
	private String Indirizzo;

	
	//costruttore
	public Luogo(String nome, String citt�, String stato, String indirizzo) {
		Nome=nome;
		Citt�=citt�;
		Stato=stato;
		Indirizzo=indirizzo;
	}
	
	//getter
	public String getNome() {
		return Nome;
	}
	
	public String getCitt�() {
		return Citt�;
	}	
	
	public String getStato() {
		return Stato;
	}
	
	
	public String getIndirizzo() {
		return Indirizzo;
	}
	

	//setter
	public void setNome(String nome) {
		Nome=nome;
	}
	
	public void setCitt�(String citt�) {
		Citt�=citt�;
	}	
	
	public void setStato(String stato) {
		Stato=stato;
	}
	
	
	public void setIndirizzo(String indirizzo) {
		Indirizzo=indirizzo;
	}
	
}
