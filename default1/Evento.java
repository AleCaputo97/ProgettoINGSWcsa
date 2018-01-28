package default1;

public class Evento {
	
	//attributi
	private String Nome;
	private String Data;
	private double PrezzoIniziale;
	private double PrezzoFinale;
	private int MassimoSpettatori;
	private String Tipo;
	private String Luogo;
	
	//costruttore
	public Evento(String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {
		Nome=nome;
		Data=data;
		PrezzoIniziale=prezzoiniziale;
		PrezzoFinale=prezzofinale;
		MassimoSpettatori=maxspettatori;
		Tipo=tipo;
		Luogo=luogo;
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
