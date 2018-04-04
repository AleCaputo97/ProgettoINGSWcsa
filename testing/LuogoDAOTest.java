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

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import boundary.FinestraUtente;
import control.BigliettoDAO;
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
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIK4NMHLFUMXA57QQ", "kabnaccrPC75Oy/0Qh1GwGbF7vka/J/X18nX9dgE");
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .withRegion("eu-central-1")
            .build();
    connessione = new DynamoDB(dynamoDB);
      luogoDAO=new LuogoDAO(connessione);
  }
	

	@Test
	void testCercaConTuttiParametri() {
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
	void testInserisciModifica() {
	    luogoDAO.inserisciModifica("Test2018", "MILANO","Italia","via Cinthia");
	    List<Luogo>risultati = luogoDAO.cerca("Test2018", "","","");
	    for(Luogo curr:risultati) {
	    	assertEquals("Test2018", curr.getNome());
	    	assertEquals("MILANO", curr.getCittà());
	    }
	    //ripristina il vecchio valore della città
	    luogoDAO.inserisciModifica("Test2018", "Napoli","Italia","via Cinthia");
	}
	
	
}
