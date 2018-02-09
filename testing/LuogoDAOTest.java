/**
 * 
 */
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
import control.LuogoDAO;

import java.util.*;
import entity.*;
/**
 *
 */
class LuogoDAOTest {
    static AmazonDynamoDB dynamoDB;
    private static DynamoDB connessione;
    private LuogoDAO luogoDAO;
    
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
	    luogoDAO=new LuogoDAO(connessione);
	    //inserisce una nuova riga
		luogoDAO.inserisciModifica("Test2018", "Napoli","Italia","via Cinthia");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach 
	void tearDown() throws Exception {
		//al termine del test elimina la riga inserita all'inizio
		luogoDAO.elimina("Test2018");
	}

	@Test
	void cercaConTuttiParametriTest() {
		//Test1: si cerca il luogo inserito nell'operazione di setup
		int count=0;
		List<Luogo> risultati = luogoDAO.cerca("Test2018","Napoli","Italia","via Cinthia");
		for(Luogo curr:risultati){
			assertEquals("Test2018", curr.getNome());
			assertEquals("Napoli", curr.getCittà());
			assertEquals("Italia", curr.getStato());
			assertEquals("via Cinthia", curr.getIndirizzo());
		}
		//Test2: si cerca se tra tutti i risultati di una ricerca generica vi è quello inserito nel setup
		risultati = luogoDAO.cerca("", "", "", "");
		for(Luogo curr2:risultati){
			if (curr2.getNome().equals("Test2018")) count++;
		}
		assertEquals(1,count);
	}
	

	@Test
	void eliminaPerNomeTest(){
		int count=0,count2=0;
		List<Luogo> risultati = luogoDAO.cerca("Test2018", "","","");
		for(Luogo curr:risultati) count=count+1;
		assertEquals(count, 1);
		luogoDAO.elimina("Test2018");
		List<Luogo> risultati2 = luogoDAO.cerca("Test2018", "","","");
		for(Luogo curr2:risultati2) count2=count2+1;
		assertEquals(count2, 0);
		}

	@Test
	void inserisciModificaTest() {
		//Test di inserimento
	    luogoDAO.inserisciModifica("Test2019", "Roma","Italia","via Cinthia");
	    List<Luogo> risultati = luogoDAO.cerca("Test2019", "","","");
	    for(Luogo curr:risultati) assertEquals("Test2019", curr.getNome());
	    //test di modifica
	    luogoDAO.inserisciModifica("Test2019", "MILANO","Italia","via Cinthia");
	    risultati = luogoDAO.cerca("Test2019", "","","");
	    for(Luogo curr:risultati) {
	    	assertEquals("Test2019", curr.getNome());
	    	assertEquals("MILANO", curr.getCittà());
	    }
	    luogoDAO.elimina("Test2019");
	}
	
	
}
