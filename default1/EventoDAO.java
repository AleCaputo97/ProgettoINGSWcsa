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

public class EventoDAO {
	
	public static List<Evento> cerca(String nome, String data, float prezzoiniziale, float prezzofinale, int maxspettatori, String tipo) {
	       
		 
		   String tableName = "Evento";
		   Iterator<Item> iterator = null;
		   List<Evento> risultati=new ArrayList<Evento>();  
		   Table table = ((DynamoDB) ProgettoINGSWcsa.connessione).getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   //se ogni campo è vuoto deve svolgere una scan di tutto
		   if(nome.equals("") && data.equals("") && prezzoiniziale==0.00 && prezzofinale==0.00 && maxspettatori==0 && tipo.equals("")) {
			   ItemCollection<ScanOutcome> items = table.scan (
				        null,                                  
				        null,
				        null,                                          
				        null);
				         
				       iterator = items.iterator();
				   
		   }else{ //un campo non è vuoto e deve costruire la query
			   String ricerca = "";
			   if(!(nome.equals(""))) {
			   ricerca=ricerca + "AND Nome = :nome ";
			   expressionAttributeValues.put(":nome", nome);
			   }
			   if(!(data.equals(""))) {
				   expressionAttributeValues.put(":data", data);
				   ricerca=ricerca + "AND Data = :data ";
				   }
			   if(!(prezzoiniziale==0.00)) {
				   ricerca=ricerca + "AND PrezzoIniziale = :pi ";
				   expressionAttributeValues.put(":pi", prezzoiniziale);
				   }
			   if(!(prezzofinale==0.00)) {
				   ricerca=ricerca + "AND PrezzoFinale = :pe ";
				   expressionAttributeValues.put(":pe", prezzofinale);
				   }
			   if(!(maxspettatori==0)) {
				   ricerca=ricerca + "AND MassimoSpettatori = :maxspettatori ";
				   expressionAttributeValues.put(":maxspettatori", maxspettatori);
				   }
			   if(!(tipo.equals(""))) {
				   ricerca=ricerca + "AND Tipo = :tipo ";
				   expressionAttributeValues.put(":tipo", tipo);
				   }
			   //rimuove i primi tre caratteri della stringa ricerca rendendola idonea alla scan con attributi
			   ricerca = ricerca.substring(3);
			    ItemCollection<ScanOutcome> items = table.scan (
				        ricerca,                                  
				        null,
				        null,                                          
				        expressionAttributeValues);
				         
				       iterator = items.iterator();
				   
				      
		   		}
		   //costruisce la lista con i risultati da restituire
		  // System.out.println("[DAO] Si vuole cercare: " + nome + cognome + email + CodiceFiscale + DataNascita);
		   Item iteratorcurr;
		   Evento curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Evento((String) iteratorcurr.get("Nome"),(String) iteratorcurr.get("Data"),(float)  iteratorcurr.get("PrezzoIniziale"),(float) iteratorcurr.get("PrezzoFinale"),(int) iteratorcurr.get("MassimoSpettatori"),(String) iteratorcurr.get("Tipo"));
	        risultati.add(curr);
	       }
	   return risultati;
	}
	
	 public static void inserisciModifica(String nome, String data, float prezzoiniziale, float prezzofinale, int maxspettatori, String tipo) {
		    String tableName = "Evento";
		    Table table = ((DynamoDB) ProgettoINGSWcsa.connessione).getTable(tableName);
		    Item item = new Item()
		    	    .withPrimaryKey("Nome", nome)
		    	    .withString("Data", data)
		    	    .withNumber("PrezzoIniziale", prezzoiniziale)
		    	    .withNumber("PrezzoFinale", prezzofinale)
		    	    .withNumber("MassimoSpettatori", maxspettatori);
		    
		    PutItemOutcome outcome = table.putItem(item, null, null, null);
		    
		    
		    }
	 
	 public static void elimina(String Nome) {
	        String tableName = "Evento";
	        Table table = ((DynamoDB) ProgettoINGSWcsa.connessione).getTable(tableName);
	        DeleteItemOutcome outcome = table.deleteItem("Nome", Nome);
	        }
}
