/**
 * 
 */
package control;

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

import java.util.*;
import entity.*;
/**
 * @author cresc
 *
 */
class ClienteDAOTest {
    static AmazonDynamoDB dynamoDB;
    private static DynamoDB connessione;
    private ClienteDAO clienteDAO;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
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
	    clienteDAO=new ClienteDAO(connessione);
		clienteDAO.inserisciModifica("Michele", "Caparezza", "michelecaparezza@gmail.com", "MCHSVM73R09F284X", "9 ottobre 1973");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		clienteDAO.elimina("MCHSVM73R09F284X");
	}

	@Test
	void cercaConTuttiParametriTest() {
		List<Cliente> risultati = clienteDAO.cerca("Michele", "Caparezza", "michelecaparezza@gmail.com", "MCHSVM73R09F284X", "9 ottobre 1973");
		for(Cliente curr:risultati){
		assertEquals("MCHSVM73R09F284X", curr.getCodiceFiscale());
		assertEquals("Michele", curr.getNome());
		assertEquals("Caparezza", curr.getCognome());
		assertEquals("michelecaparezza@gmail.com", curr.getEmail());
		assertEquals("9 ottobre 1973", curr.getData());
		}
	}
	
	@Test
	void cercaSenzaParametriTest() {
		int count=0;
		List<Cliente> risultati = clienteDAO.cerca("", "", "", "", "");
		for(Cliente curr:risultati){
			if (curr.getCodiceFiscale().equals("MCHSVM73R09F284X")) {
				count++;
			}
		}
		assertEquals(1,count);
	}
	
	@Test
	void cercaPerCodiceFiscaleTest() {
		Cliente risultato = clienteDAO.cerca("MCHSVM73R09F284X"); 
		assertEquals("MCHSVM73R09F284X", risultato.getCodiceFiscale());
		assertEquals("Michele", risultato.getNome());
		assertEquals("Caparezza", risultato.getCognome());
		assertEquals("michelecaparezza@gmail.com", risultato.getEmail());
		assertEquals("9 ottobre 1973", risultato.getData());
	}
	
	void eliminaPerCodFiscale(){
		int count=0,count2=0;
		List<Cliente> risultati = clienteDAO.cerca("", "", "", "MCHSVM73R09F284X", "");
					for(Cliente curr:risultati) count=count+1;
		assertEquals(count, 1);
		clienteDAO.elimina("MCHSVM73R09F284X");
		List<Cliente> risultati2 = clienteDAO.cerca("", "", "", "MCHSVM73R09F284X", ""); 
					for(Cliente curr2:risultati2) count2=count2+1;
		assertEquals(count2, 0);
		}

	
	void inserisciModificaTest() {
	    clienteDAO.inserisciModifica("Ani", "Caparezza", "michelecaparezza@gmail.com", "MCHSVM73R09F284X", "9 ottobre 1973");
	    List<Cliente> risultati = clienteDAO.cerca("", "", "", "MCHSVM73R09F284X", ""); 
	    for(Cliente curr:risultati) assertEquals("Ani", curr.getNome());
	  }
	
	
}
