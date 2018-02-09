package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import control.EventoDAO;

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
	void test() {
		fail("Not yet implemented");
	}

}
