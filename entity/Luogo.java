package entity;

public class Luogo {
	
	//attributi
	private String Nome;
	private String Città;
	private String Stato;
	private String Indirizzo;

	
	//costruttore
	public Luogo(String nome, String città, String stato, String indirizzo) {
		Nome=nome;
		Città=città;
		Stato=stato;
		Indirizzo=indirizzo;
	}
	
	//getter
	public String getNome() {
		return Nome;
	}
	
	public String getCittà() {
		return Città;
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
	
	public void setCittà(String città) {
		Città=città;
	}	
	
	public void setStato(String stato) {
		Stato=stato;
	}
	
	
	public void setIndirizzo(String indirizzo) {
		Indirizzo=indirizzo;
	}
	
}
