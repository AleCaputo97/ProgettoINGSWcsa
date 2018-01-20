package default1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class ClienteDAO {
	
	public static void cerca(String CodiceFiscale, String nome, String cognome, String email, String Data) {
	       
		 
		   String tableName = "Cliente";
		   Table table = ((DynamoDB) ProgettoINGSWcsa.connessione).getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   //se ogni campo è vuoto deve svolgere una scan di tutto
		   if(CodiceFiscale.equals("") && nome.equals("") && cognome.equals("") && email.equals("") && Data.equals("")) {
			   ItemCollection<ScanOutcome> items = table.scan (
				        null,                                  
				        null,
				        null,                                          
				        null);
				         
				       Iterator<Item> iterator = items.iterator();
				   
				       Item iteratorcurr;
				       String c1;
				       while (iterator.hasNext()) {
				        iteratorcurr = iterator.next();
				          System.out.println(iteratorcurr);
				      }
		   }else{ //un campo non è vuoto e deve costruire la query
			   String ricerca = "";
			   if(!(nome.equals(""))) {
			   ricerca=ricerca + "AND Nome = :nome ";
			   expressionAttributeValues.put(":nome", nome);
			   }
			   if(!(CodiceFiscale.equals(""))) {
				   expressionAttributeValues.put(":cf", CodiceFiscale);
				   ricerca=ricerca + "AND CodiceFiscale = :cf ";
				   }
			   if(!(Data.equals(""))) {
				   ricerca=ricerca + "AND Data = :data ";
				   expressionAttributeValues.put(":data", Data);
				   }
			   if(!(cognome.equals(""))) {
				   ricerca=ricerca + "AND Cognome = :cognome ";
				   expressionAttributeValues.put(":cognome", cognome);
				   }
			   if(!(email.equals(""))) {
				   ricerca=ricerca + "AND Email = :email ";
				   expressionAttributeValues.put(":email", email);
				   }
			   //rimuove i primi tre caratteri della stringa ricerca rendendola idonea alla scan con attributi
			   System.out.println("la query creata è:" + ricerca);
			   ricerca = ricerca.substring(3);
			   System.out.println("la query che voglio stampare è:" + ricerca);
			   ItemCollection<ScanOutcome> items = table.scan (
				        ricerca,                                  
				        "CodiceFiscale, Nome, Cognome, Email, DataNascita",
				        null,                                          
				        expressionAttributeValues);
				         
				       Iterator<Item> iterator = items.iterator();
				   
				       Item iteratorcurr;
				       while (iterator.hasNext()) {
				        iteratorcurr = iterator.next();
				          System.out.println(iteratorcurr.get("Nome"));
				       }
		   		}
	}
}
