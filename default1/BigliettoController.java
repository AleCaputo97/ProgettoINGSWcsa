package default1;

import java.util.List;


public class BigliettoController {

private static String normalizza (String string) {
		
		if (!(string.equals("")))
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		
		return string;
		
	}
	
	
	private static List<Biglietto> bigliettiVendutiEvento(String evento) {
		evento=normalizza(evento);
		return cercaPerEvento(evento);
		
	}
	
private static List<Biglietto> bigliettiVendutiLuogo(String luogo) {
		luogo=normalizza(luogo);
		return cercaPerLuogo(luogo);
		
	}
	
private static boolean isBigliettiVendutiEvento(String evento) {
	evento=normalizza(evento);
	if (cercaPerEvento(evento)!=NULL) {
		return true;
	}else {
		return false;
	}
}

private static boolean isBigliettiVendutiLuogo(String luogo) {
	luogo=normalizza(luogo);
	if (cercaPerLuogo(luogo)!=NULL) {
		return true;
	}else {
		return false;
	}
}

}

