/**
 * 
 */
package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

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
	@Before
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
		clienteDAO.inserisciModifica("Michele", "Caparezza", "michelecaparezza@gmail.com", "QWERTYUIOPLKJHGF", "9 ottobre 1973");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	void tearDown() throws Exception {
		clienteDAO.elimina("QWERTYUIOPLKJHGF");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
