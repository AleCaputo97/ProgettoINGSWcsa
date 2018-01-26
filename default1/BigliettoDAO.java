package default1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class BigliettoDAO {
	 public static void eliminaBiglietti(String CodiceFiscale) {
	        String tableName = "Biglietto";
	        Table table = ((DynamoDB) ProgettoINGSWcsa.connessione).getTable(tableName);
	        Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
	        expressionAttributeValues.put(":CodFiscale", CodiceFiscale);
	        ItemCollection<ScanOutcome> items = table.scan (
			        "CodFiscale = :CodFiscale",                                  
			        null,
			        null,                                          
			        expressionAttributeValues);
			         
			       Iterator<Item> iterator = items.iterator();
			   
			       Item iteratorcurr;

			       while (iterator.hasNext()) {
			        iteratorcurr = iterator.next();
			       // System.out.println("sto per eliminare:" + iteratorcurr);
			        table.deleteItem("NumeroBiglietto", (String) iteratorcurr.get("NumeroBiglietto"));
			       }
	        }
}
