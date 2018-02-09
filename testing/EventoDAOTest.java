package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import control.EventoDAO;
import entity.Evento;

class EventoDAOTest {
    static AmazonDynamoDB dynamoDB;
    private static DynamoDB connessione;
    private EventoDAO eventoDAO;
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
	    eventoDAO=new EventoDAO(connessione);
	    //inserisce una nuova riga
		eventoDAO.inserisciModifica("test2018", "4 giugno 2018", 10.00, 20.00, 200, "altro", "Croke park", "9 febbraio 2018");
	}

	@AfterEach
	void tearDown() throws Exception {
		eventoDAO.elimina("test2018");
	}

	@Test
	void cercaConTuttiParametriTest() {
		//Test1: si cerca evento inserito nell'operazione di setup
		int count=0;
		List<Evento> risultati = eventoDAO.cerca("test2018", "4 giugno 2018", 10.00, 20.00, 200, "altro", "Croke park");
		for(Evento curr:risultati){
			assertEquals("test2018", curr.getNome());
			assertEquals("4 giugno 2018", curr.getData());
			assertEquals(10, curr.getPrezzoIniziale());
			assertEquals(20, curr.getPrezzoFinale());
			assertEquals("altro", curr.getTipo());
		}
		//Test2: si cerca se tra tutti i risultati di una ricerca generica vi è quello inserito nel setup
		risultati = eventoDAO.cerca("", "", 00.00, 00.00, 0,"","");
		for(Evento curr2:risultati){
			if (curr2.getNome().equals("test2018")) count++;
		}
		assertEquals(1,count);
	}
	
	void cercaPerNomeTest() {
		Evento risultato = eventoDAO.cerca("test2018"); 
		assertEquals("test2018", risultato.getNome());
		assertEquals("4 giugno 2018", risultato.getData());
		assertEquals(10, risultato.getPrezzoIniziale());
		assertEquals(20, risultato.getPrezzoFinale());
		assertEquals("altro", risultato.getTipo());
	}
	
	@Test
	void eliminaPerNomeTest(){
		int count=0,count2=0;
		List<Evento> risultati = eventoDAO.cerca("test2018", "", 00.00, 00.00, 0,"","");
					for(Evento curr:risultati) count=count+1;
		assertEquals(count, 1);
		eventoDAO.elimina("test2018");
		List<Evento> risultati2 = eventoDAO.cerca("test2018", "", 00.00, 00.00, 0,"","");
					for(Evento curr2:risultati2) count2=count2+1;
		assertEquals(count2, 0);
	}

	@Test
	void inserisciModificaTest() {
		//test di un inserimento
	    eventoDAO.inserisciModifica("test20_18", "4 giugno 2018", 10.00, 20.00, 200, "altro", "Croke park", "10 febbraio 2018");
	    List<Evento> risultati = eventoDAO.cerca("test20_18", "", 00.00, 00.00, 0, "", "");
	    for(Evento curr:risultati) assertEquals("test20_18", curr.getNome());
	    eventoDAO.elimina("test20_18");
	    //test di una modifica
	    eventoDAO.inserisciModifica("test2018", "4 giugno 2018", 10.00, 20.00, 200, "altro", "Croke park", "10 MARZO 2018");
	    risultati = eventoDAO.cerca("test2018", "", 00.00, 00.00, 0, "", "");
	    for(Evento curr:risultati) {
	    	assertEquals("test2018", curr.getNome());
	    	assertEquals("10 MARZO 2018", curr.getData());
	    }
	}
}
