package default1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Biglietto {
	
	//attributi
	private String NumeroBiglietto;
	private String CodFiscale;
	private double Prezzo;
	private String Luogo;
	private String Evento;
	private String DataAcquisto;
	
	//costruttore
	public Biglietto(String numbiglietto, String codfiscale, double prezzo, String luogo, String evento, String dataacquisto) {
		NumeroBiglietto=numbiglietto;
		CodFiscale=codfiscale;
		Prezzo=prezzo;
		Luogo=luogo;
		Evento=evento;
		DataAcquisto=dataacquisto;
	}
	
	//getter
	public String getNumeroBiglietto() {
		return NumeroBiglietto;
	}
	
	public String getCodFiscale() {
		return CodFiscale;
	}	
	
	public double getPrezzo() {
		return Prezzo;
	}
	
	public String getLuogo() {
		return Luogo;
	}
	
	public String getEvento() {
		return Evento;
	}	
	
	public String getDataAcquisto() {
		return DataAcquisto;
	}

	//setter
	public void setNumeroBiglietto(String numbiglietto) {
		NumeroBiglietto=numbiglietto;
	}
	
	public void setCodFiscale(String codfiscale) {
		CodFiscale=codfiscale;
	}
	
	public void setPrezzo(double prezzo) {
		Prezzo=prezzo;
	}
	
	public void setLuogo(String luogo) {
		Luogo=luogo;
	}
	
	public void setEvento(String evento) {
		Evento=evento;
	}
	
	public void setDataAcquisto(String dataacquisto) {
		DataAcquisto=dataacquisto;
	}
	
	public int getMese() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getDataAcquisto(), formatter); 
    	int mese = LocalDataInserimento.getMonthValue();
    	return mese;
	}	
	
	public int getAnno() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    	LocalDate LocalDataInserimento = LocalDate.parse(getDataAcquisto(), formatter); 
    	int anno = LocalDataInserimento.getYear();
    	return anno;
	}
}
