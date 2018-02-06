package default1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Evento {
	
	//attributi
	private String Nome;
	private String Data;
	private double PrezzoIniziale;
	private double PrezzoFinale;
	private int MassimoSpettatori;
	private String Tipo;
	private String Luogo;
	private String DataInserimento;
	//costruttore
	public Evento(String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo, String dataInserimento) {
		Nome=nome;
		Data=data;
		PrezzoIniziale=prezzoiniziale;
		PrezzoFinale=prezzofinale;
		MassimoSpettatori=maxspettatori;
		Tipo=tipo;
		Luogo=luogo;
		DataInserimento=dataInserimento;
	}
	
	//getter
	public String getNome() {
		return Nome;
	}
	
	public String getData() {
		return Data;
	}	
	
	public double getPrezzoIniziale() {
		return PrezzoIniziale;
	}
	
	public double getPrezzoFinale() {
		return PrezzoFinale;
	}
	
	public int getMassimoSpettatori() {
		return MassimoSpettatori;
	}
	
	public String getTipo() {
		return Tipo;
	}
	
	public String getLuogo() {
		return Luogo;
	}
	public String getDataInserimento() {
		return DataInserimento;
	}
	
	public int getMeseInserimento() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getDataInserimento(), formatter); 
    	int mese = LocalDataInserimento.getMonthValue();
    	return mese;
	}	
	
	public int getAnnoInserimento() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getDataInserimento(), formatter); 
    	int anno = LocalDataInserimento.getYear();
    	return anno;
	}
	public int getMeseEvento() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getData(), formatter); 
    	int mese = LocalDataInserimento.getMonthValue();
    	return mese;
	}	
	
	public int getAnnoEvento() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getData(), formatter); 
    	int anno = LocalDataInserimento.getYear();
    	return anno;
	}
	
	
	//setter
	public void setNome(String nome) {
		 Nome=nome;
	}
	
	public void setData(String data) {
		 Data=data;
	}
	
	public void setPrezzoIniziale(double prezzoiniziale) {
		PrezzoIniziale=prezzoiniziale;
	}
	
	public void setPrezzoFinale(double prezzoFinale) {
		PrezzoFinale=prezzoFinale;
	}
	
	public void setMassimoSpettatori(int maxspettatori) {
		MassimoSpettatori=maxspettatori;
	}
	
	public void setTipo(String tipo) {
		 Tipo=tipo;
	}
	public void setLuogo(String luogo) {
		Luogo=luogo;
	}
	
}
