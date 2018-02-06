package default1;

import java.util.List;


public class BigliettoController {

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
		return BigliettoDAO.cercaPerEvento(evento);	
	}
	
	public static List<Biglietto> bigliettiVendutiLuogo(String luogo) {
		luogo=normalizza(luogo);
		return BigliettoDAO.cercaPerLuogo(luogo);	
	}
	
	public static boolean isBigliettiVendutiEvento(String evento) {
		evento=normalizza(evento);
		if (BigliettoDAO.cercaPerEvento(evento).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public static boolean isBigliettiVendutiLuogo(String luogo) {
		luogo=normalizza(luogo);
		if (BigliettoDAO.cercaPerLuogo(luogo).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public static List<Biglietto> bigliettiPerCliente(String cf){
		cf=normalizzaCF(cf);
		return BigliettoDAO.cercaPerCodiceFiscale(cf);
	}


	public static void eliminaBiglietti(String CodiceFiscale) {
		BigliettoDAO.eliminaBiglietti(CodiceFiscale);
	}

}

