package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
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
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIK4NMHLFUMXA57QQ", "kabnaccrPC75Oy/0Qh1GwGbF7vka/J/X18nX9dgE");
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .withRegion("eu-central-1")
            .build();
    connessione = new DynamoDB(dynamoDB);
      bigliettoDAO=new BigliettoDAO(connessione);
  }
	
	@Test
	void testCercaPerCodiceFiscale() {
		List<Biglietto> risultato = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X"); 
		for(Biglietto curr:risultato) {
			if(curr.getNumeroBiglietto().equals("8710a")) {
						assertEquals("8710a", curr.getNumeroBiglietto());
						assertEquals("MCHSVM73R09F284X", curr.getCodFiscale());
						assertEquals("1 aprile 2018", curr.getDataAcquisto());
						assertEquals("Test2018", curr.getEvento());
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
						assertEquals("Test2018", curr.getEvento());
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
						assertEquals("Test2018", curr.getEvento());
						assertEquals("Croke park", curr.getLuogo());
						assertEquals(112, curr.getPrezzo());
			}
		}
	}
	

	@Test
	void testInserisciModifica() {
	    bigliettoDAO.inserisciModifica("8710a","MCHSVM73R09F284X","1 MARZO 2018","Test2018","Croke park",112);
	    List<Biglietto> risultati = bigliettoDAO.cercaPerCodiceFiscale("MCHSVM73R09F284X"); 
	    for(Biglietto curr:risultati) {
	    	if(curr.getNumeroBiglietto().equals("8710a")) 	assertEquals("1 MARZO 2018", curr.getDataAcquisto());
	    }
	    //ripristina il vecchio ticket
	    bigliettoDAO.inserisciModifica("8710a","MCHSVM73R09F284X","1 aprile 2018","Test2018","Croke park",112);
	  }
	
	
}
