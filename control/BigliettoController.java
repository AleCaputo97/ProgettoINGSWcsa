package control;

import java.util.List;
import boundary.*;
import entity.*;

public class BigliettoController {

	static BigliettoDAO bigliettoDAO;
	
	//costruttore 
	 public BigliettoController(BigliettoDAO InputBiglietto) {
		    bigliettoDAO=InputBiglietto;
		  }
	
	private static String normalizza (String string) {	
		if (!(string.equals(""))) string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		return string;	
	}

	private static String normalizzaCF (String codicefiscale) {
		if (!(codicefiscale.equals(""))) codicefiscale = codicefiscale.toUpperCase();
		return codicefiscale;
	}
	
	
	public static List<Biglietto> bigliettiVendutiEvento(String evento) {
		evento=normalizza(evento);
		return bigliettoDAO.cercaPerEvento(evento);	
	}
	
	public static List<Biglietto> bigliettiVendutiLuogo(String luogo) {
		luogo=normalizza(luogo);
		return bigliettoDAO.cercaPerLuogo(luogo);	
	}
	
	public static boolean isBigliettiVendutiEvento(String evento) {
		//metodo che si interroga se vi sono biglietti venduti per questo luogo: se vi sono, torna vero, altrimenti falso
		evento=normalizza(evento);
		if (bigliettoDAO.cercaPerEvento(evento).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public static boolean isBigliettiVendutiLuogo(String luogo) {
		luogo=normalizza(luogo);
		if (bigliettoDAO.cercaPerLuogo(luogo).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public static List<Biglietto> bigliettiPerCliente(String cf){
		cf=normalizzaCF(cf);
		return bigliettoDAO.cercaPerCodiceFiscale(cf);
	}


	public static void eliminaBiglietti(String CodiceFiscale) {
		bigliettoDAO.eliminaBiglietti(CodiceFiscale);
	}

	
	public static void aggiornaLuogo(List<Biglietto> Lista, String Luogo) {
		for(Biglietto curr :Lista) bigliettoDAO.inserisciModifica(curr.getNumeroBiglietto(),curr.getCodFiscale(),curr.getDataAcquisto(),curr.getEvento(),Luogo,curr.getPrezzo());
	}
	
}

