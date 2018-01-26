package default1;

public class Evento {
	
	//attributi
	private String Nome;
	private String Data;
	private float PrezzoIniziale;
	private float PrezzoFinale;
	private int MassimoSpettatori;
	private String Tipo;
	
	//costruttore
	public Evento(String nome, String data, float prezzoiniziale, float prezzofinale, int maxspettatori, String tipo) {
		Nome=nome;
		Data=data;
		PrezzoIniziale=prezzoiniziale;
		PrezzoFinale=prezzofinale;
		MassimoSpettatori=maxspettatori;
		Tipo=tipo;
	}
	
	//getter
	public String getNome() {
		return Nome;
	}
	
	public String getData() {
		return Data;
	}	
	
	public float getPrezzoIniziale() {
		return PrezzoIniziale;
	}
	
	public float getPrezzoFinale() {
		return PrezzoFinale;
	}
	
	public int getMassimoSpettatori() {
		return MassimoSpettatori;
	}
	
	public String getTipo() {
		return Tipo;
	}

	//setter
	public void setNome(String nome) {
		 Nome=nome;
	}
	
	public void setData(String data) {
		 Data=data;
	}
	
	public void setPrezzoIniziale(float prezzoiniziale) {
		PrezzoIniziale=prezzoiniziale;
	}
	
	public void setPrezzoFinale(float prezzoFinale) {
		PrezzoFinale=prezzoFinale;
	}
	
	public void setMassimoSpettatori(int maxspettatori) {
		MassimoSpettatori=maxspettatori;
	}
	
	public void setTipo(String tipo) {
		 Tipo=tipo;
	}
	
}
