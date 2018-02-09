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
public class BigliettoDAO {
	private DynamoDB connessione;
	private List<Biglietto> risultati=new ArrayList<Biglietto>();  
	
	public BigliettoDAO(DynamoDB input) {
		connessione=input;
	}
	
	
	
	 public void eliminaBiglietti(String CodiceFiscale) {
	        String tableName = "Biglietto";
	        Table table = connessione.getTable(tableName);
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
			        table.deleteItem("NumeroBiglietto", (String) iteratorcurr.get("NumeroBiglietto"));
			       }
	        }
	 
	 public List<Biglietto> cercaPerEvento(String evento) {
		   String tableName = "Biglietto";
		   Iterator<Item> iterator = null;
		   Table table = connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   expressionAttributeValues.put(":evento", evento);
			    ItemCollection<ScanOutcome> items = table.scan (
				        "Evento = :evento",                                  
				        null,
				        null,                                          
				        expressionAttributeValues);   
				       iterator = items.iterator();
		   //costruisce la lista con i risultati da restituire
		   Item iteratorcurr;
		   Biglietto curr;
	       while (iterator.hasNext()) {
	    	   iteratorcurr = iterator.next();
	    	   curr=new Biglietto((String) iteratorcurr.get("NumeroBiglietto"),(String) iteratorcurr.get("CodFiscale"),(double) Double.parseDouble(iteratorcurr.get("Prezzo").toString()),(String) iteratorcurr.get("Luogo"),(String) iteratorcurr.get("Evento"), (String) iteratorcurr.get("DataAcquisto"));
	    	   risultati.add(curr);
	       }
	   return risultati;
	}
	 
	 public List<Biglietto> cercaPerCodiceFiscale(String cf) {
		   String tableName = "Biglietto";
		   Iterator<Item> iterator = null;
		   List<Biglietto> risultati=new ArrayList<Biglietto>();  
		   Table table = connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   expressionAttributeValues.put(":cf", cf);
			    ItemCollection<ScanOutcome> items = table.scan (
				        "CodFiscale = :cf",                                  
				        null,
				        null,                                          
				        expressionAttributeValues);   
				       iterator = items.iterator();
		   //costruisce la lista con i risultati da restituire
		   Item iteratorcurr;
		   Biglietto curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Biglietto((String) iteratorcurr.get("NumeroBiglietto"),(String) iteratorcurr.get("CodFiscale"),(double) Double.parseDouble(iteratorcurr.get("Prezzo").toString()),(String) iteratorcurr.get("Luogo"),(String) iteratorcurr.get("Evento"), (String) iteratorcurr.get("DataAcquisto"));
	        risultati.add(curr);
	       }
	   return risultati;
	}
	 
	 public List<Biglietto> cercaPerLuogo(String luogo) {
		   String tableName = "Biglietto";
		   Iterator<Item> iterator = null;
		   List<Biglietto> risultati=new ArrayList<Biglietto>();  
		   Table table = connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   expressionAttributeValues.put(":luogo", luogo);
		   ItemCollection<ScanOutcome> items = table.scan (
				        "Luogo = :luogo",                                  
				        null,
				        null,                                          
				        expressionAttributeValues);   
				       iterator = items.iterator();
		   //costruisce la lista con i risultati da restituire
		   Item iteratorcurr;
		   Biglietto curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Biglietto((String) iteratorcurr.get("NumeroBiglietto"),(String) iteratorcurr.get("CodFiscale"),(double) Double.parseDouble(iteratorcurr.get("Prezzo").toString()),(String) iteratorcurr.get("Luogo"),(String) iteratorcurr.get("Evento"), (String) iteratorcurr.get("DataAcquisto"));
	        risultati.add(curr);
	       }
	   return risultati;
	   
	   }
	 //metodo utilizzato per popolare il database ed in fase di testing per poter testare gli altri
	 public void inserisciModifica(String numero, String codicefiscale, String data, String evento, String luogo, float prezzo) {
		    String tableName = "Biglietto";
		    Table table = connessione.getTable(tableName);
		    //costruisce l'item e poi esegue un put inserendolo (se già presente lo modifica)
		    Item item = new Item()
		    	    .withPrimaryKey("NumeroBiglietto", numero)
		    	    .withString("CodFiscale", codicefiscale)
		    	    .withString("DataAcquisto", data)
		    	    .withString("Evento", evento)
		    	    .withString("Luogo", luogo)
		    	    .withNumber("Prezzo", prezzo);;
		    PutItemOutcome outcome = table.putItem(item, null, null, null);
	}
}
