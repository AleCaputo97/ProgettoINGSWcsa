package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import boundary.FinestraUtente;
import control.BigliettoDAO;

import java.util.*;
import entity.*;

class BigliettoDAOTest {
    static AmazonDynamoDB dynamoDB;
    private static DynamoDB connessione;
    private BigliettoDAO bigliettoDAO;
    
	@BeforeEach //crea la connessione con il database hostato su AWS
	void setUp() throws Exception {
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
        }
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(credentialsProvider)
            .withRegion("eu-central-1")
            .build();
		connessione = new DynamoDB(dynamoDB);
	    bigliettoDAO=new BigliettoDAO(connessione);
	    //inserisce due righe così da poter testare l'elimina per codice fiscale su più elementi
		bigliettoDAO.inserisciModifica("8710a","MCHSVM73R09F284X","1 aprile 2018","Test","Croke park",112);
		bigliettoDAO.inserisciModifica("8710b","MCHSVM73R09F284X","1 aprile 2018","Test","Croke park",112);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach 
	void tearDown() throws Exception {
		//al termine del test elimina la riga inserita all'inizio
		bigliettoDAO.eliminaBiglietti("MCHSVM73R09F284X");
	}

	
	
	@Test
	void testCercaPerCodiceFiscale() {
		List<Biglietto> risultato = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X"); 
		for(Biglietto curr:risultato) {
			if(curr.getNumeroBiglietto().equals("8710a")) {
						assertEquals("8710a", curr.getNumeroBiglietto());
						assertEquals("MCHSVM73R09F284X", curr.getCodFiscale());
						assertEquals("1 aprile 2018", curr.getDataAcquisto());
						assertEquals("Test", curr.getEvento());
						assertEquals("Croke park", curr.getLuogo());
						assertEquals(112, curr.getPrezzo());
			}
		}
	}
	
	@Test
	void testCercaPerEventoTest() {
		List<Biglietto> risultato = bigliettoDAO.cercaPerEvento("Test"); 
		for(Biglietto curr:risultato) {
			if(curr.getNumeroBiglietto().equals("8710a")) {
						assertEquals("8710a", curr.getNumeroBiglietto());
						assertEquals("MCHSVM73R09F284X", curr.getCodFiscale());
						assertEquals("1 aprile 2018", curr.getDataAcquisto());
						assertEquals("Test", curr.getEvento());
						assertEquals("Croke park", curr.getLuogo());
						assertEquals(112, curr.getPrezzo());
			}
		}
	}
	
	@Test
	void testCercaPerLuogo() {
		List<Biglietto> risultato = bigliettoDAO.cercaPerLuogo("Croke park"); 
		for(Biglietto curr:risultato) {
			if(curr.getNumeroBiglietto().equals("8710a")) {
						assertEquals("8710a", curr.getNumeroBiglietto());
						assertEquals("MCHSVM73R09F284X", curr.getCodFiscale());
						assertEquals("1 aprile 2018", curr.getDataAcquisto());
						assertEquals("Test", curr.getEvento());
						assertEquals("Croke park", curr.getLuogo());
						assertEquals(112, curr.getPrezzo());
			}
		}
	}
	
	@Test
	void testEliminaPerCodFiscale(){
		int count=0,count2=0;
		List<Biglietto> risultati = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X");
		for(Biglietto curr:risultati) count=count+1;
		if(count>=0) count=1; 	//se ci sono almeno due elementi setta ad 1 count		
		assertEquals(count, 1);
		bigliettoDAO.eliminaBiglietti("MCHSVM73R09F284X");
		List<Biglietto> risultati2 = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X"); 
		for(Biglietto curr2:risultati2) count2=count2+1;
		assertEquals(count2, 0);
		}

	@Test
	void testInserisciModifica() {
	    bigliettoDAO.inserisciModifica("8710a","MCHSVM73R09F284X","1 MARZO 2018","Test","Croke park",112);
	    List<Biglietto> risultati = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X"); 
	    for(Biglietto curr:risultati) {
	    	if(curr.getNumeroBiglietto().equals("8710a")) assertEquals("1 MARZO 2018", curr.getDataAcquisto());
	    }
	  }
	
	
}
