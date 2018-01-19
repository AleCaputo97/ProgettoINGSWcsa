package default1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
 
public class ProgettoINGSWcsa {
    static AmazonDynamoDB dynamoDB;
    static DynamoDB connessione;
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		init();
		connessione = new DynamoDB(dynamoDB);
		new FinestraUtente();
	}

    public static void init() throws Exception {
        
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
        }
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(credentialsProvider)
            .withRegion("eu-central-1")
            .build();
       
    }
}
