package control;

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
import boundary.*;
import entity.*;
import progettoINGSWcsa.*;
public class ClienteDAO {
	
	private DynamoDB connessione;
	
	public ClienteDAO(DynamoDB input) {
		connessione=input;
	}
	
	public List<Cliente> cerca(String nome, String cognome, String email, String CodiceFiscale, String DataNascita) {
		   String tableName = "Cliente";
		   Iterator<Item> iterator = null;
		   List<Cliente> risultati=new ArrayList<Cliente>();  
		   Table table = connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   //se ogni campo è vuoto deve svolgere una scan di tutto
		   if(CodiceFiscale.equals("") && nome.equals("") && cognome.equals("") && email.equals("") && DataNascita.equals("")) {
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
			   if(!(CodiceFiscale.equals(""))) {
				   expressionAttributeValues.put(":cf", CodiceFiscale);
				   ricerca=ricerca + "AND CodiceFiscale = :cf ";
				   }
			   if(!(DataNascita.equals(""))) {
				   ricerca=ricerca + "AND DataNascita = :data ";
				   expressionAttributeValues.put(":data", DataNascita);
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
			   ricerca = ricerca.substring(3);
			    ItemCollection<ScanOutcome> items = table.scan (
				        ricerca,                                  
				        null,
				        null,                                          
				        expressionAttributeValues);
				         
				       iterator = items.iterator();
				   
				      
		   	}
		   //costruisce la lista con i risultati da restituire
		   Item iteratorcurr;
		   Cliente curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Cliente((String) iteratorcurr.get("Nome"),(String) iteratorcurr.get("Cognome"),(String)  iteratorcurr.get("Email"),(String) iteratorcurr.get("CodiceFiscale"),(String) iteratorcurr.get("DataNascita"));
	        risultati.add(curr);
	       }
	   return risultati;
	}
	
	 public Cliente cerca(String CodFiscale) {
		   String tableName = "Cliente";
		   Table table = connessione.getTable(tableName);
		   Item item = null;
		   item = table.getItem("CodiceFiscale", CodFiscale);
		   if (item!=null) {
			   return new Cliente((String) item.get("Nome"),(String) item.get("Cognome"),(String)  item.get("Email"),(String) item.get("CodiceFiscale"),(String) item.get("DataNascita"));
		   }else {
			   return null;
		   }
	 }
		  
	 
	 public Cliente cercaPerEmail(String email) {
		 String tableName = "Cliente";
		 Iterator<Item> iterator = null;
		 List<Cliente> risultati=new ArrayList<Cliente>();  
	     Table table = connessione.getTable(tableName);
         Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
         String ricerca = "Email = :email";
         expressionAttributeValues.put(":email", email);
			    ItemCollection<ScanOutcome> items = table.scan (
				        ricerca,                                  
				        null,
				        null,                                          
				        expressionAttributeValues);
		iterator = items.iterator();	
		Item iteratorcurr;
		Cliente curr;
	      while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Cliente((String) iteratorcurr.get("Nome"),(String) iteratorcurr.get("Cognome"),(String)  iteratorcurr.get("Email"),(String) iteratorcurr.get("CodiceFiscale"),(String) iteratorcurr.get("DataNascita"));
	        return curr;
	       }
	   return null;
	}
	 
	 public void inserisciModifica(String nome, String cognome, String email, String CodiceFiscale, String Data) {
		    String tableName = "Cliente";
		    Table table = connessione.getTable(tableName);
		    //costruisce l'item e poi esegue un put inserendolo (se già presente lo modifica)
		    Item item = new Item()
		    	    .withPrimaryKey("CodiceFiscale", CodiceFiscale)
		    	    .withString("Nome", nome)
		    	    .withString("Cognome", cognome)
		    	    .withString("Email", email)
		    	    .withString("DataNascita", Data);
		    PutItemOutcome outcome = table.putItem(item, null, null, null);
	}
	 
	 public void elimina(String CodiceFiscale) {
	        String tableName = "Cliente";
	        Table table = connessione.getTable(tableName);
	        DeleteItemOutcome outcome = table.deleteItem("CodiceFiscale", CodiceFiscale);
	        }
}
