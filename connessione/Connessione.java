package connessione;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIManager;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import boundary.*;
import control.*;
public class Connessione {
    static AmazonDynamoDB dynamoDB;
    private static DynamoDB connessione;
    
	public static void main(String[] args) throws Exception {
	    try{ 
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    	init();
	    	connessione = new DynamoDB(dynamoDB);
	    	LuogoDAO luogoDAO=new LuogoDAO(connessione);
	    	LuogoController luogoController=new LuogoController(luogoDAO);
	    	ClienteDAO clienteDAO=new ClienteDAO(connessione);
	    	ClienteController clienteController=new ClienteController(clienteDAO);
	    	EventoDAO eventoDAO=new EventoDAO(connessione);
	    	EventoController eventoController=new EventoController(eventoDAO);
			BigliettoDAO bigliettoDAO=new BigliettoDAO(connessione);
			BigliettoController bigliettoController=new BigliettoController(bigliettoDAO);
			FinestraUtente window = new FinestraUtente();
			window.frmProgettoingswcsa.setVisible(true);
		}catch(Exception e){ 
			FinestraMessaggioErrore errore=new FinestraMessaggioErrore(e);
			errore.setTitle("Errore");
			errore.setLocationRelativeTo(null);
			errore.setVisible(true);
	    }
	}

    
    public static void init() throws Exception {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJAXEAITBHDSGIJHA", "6icXD20+m0XecIUJAhWqAibiUUmt/lAjByO0X1W4");
        try {
            dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion("eu-central-1")
                .build();
        } catch (Exception e) {
        }
       
    }
    
}

