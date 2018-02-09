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
public class LuogoDAO {
	private DynamoDB connessione;
	//costruttore che prende in input soltanto la connessione con il database
	public LuogoDAO(DynamoDB Connessione) {
		connessione=Connessione;
	}
	
	public List<Luogo> cerca(String nome, String città, String stato, String indirizzo) {
		   String tableName = "Luogo";
		   Iterator<Item> iterator = null;
		   List<Luogo> risultati=new ArrayList<Luogo>();  
		   Table table = connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   //se ogni campo è vuoto deve svolgere una scan di tutto
		   if(nome.equals("") && città.equals("") && stato.equals("") && indirizzo.equals("")) {
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
			   if(!(città.equals(""))) {
				   expressionAttributeValues.put(":citta", città);
				   ricerca=ricerca + "AND Citta = :citta ";
				   }
			   if(!(stato.equals(""))) {
				   ricerca=ricerca + "AND Stato = :stato ";
				   expressionAttributeValues.put(":stato", stato);
				   }
			   if(!(indirizzo.equals(""))) {
				   ricerca=ricerca + "AND Indirizzo = :indirizzo ";
				   expressionAttributeValues.put(":indirizzo", indirizzo);
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
		   Luogo curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Luogo((String) iteratorcurr.get("Nome"),(String) iteratorcurr.get("Città"),(String)  iteratorcurr.get("Stato"),(String) iteratorcurr.get("Indirizzo"));
	        risultati.add(curr);
	       }
	   return risultati;
	}
	
	
	 public Luogo cerca(String nome) {
		   String tableName = "Luogo";
		   Table table =connessione.getTable(tableName);
		   Item item = null;
		   item = table.getItem("Nome", nome);
		   if (item!=null) {
		   return new Luogo((String) item.get("Nome"),(String) item.get("Città"),(String) item.get("Stato"),(String) item.get("Indirizzo"));
	   }else {
		   return null;
	   }
 }
	 public void inserisciModifica(String nome, String città, String stato, String indirizzo) {
		    String tableName = "Luogo";
		    Table table = connessione.getTable(tableName);
		    Item item = new Item()
		    	    .withPrimaryKey("Nome", nome)
		    	    .withString("Città", città)
		    	    .withString("Stato", stato)
		    	    .withString("Indirizzo", indirizzo);
		    PutItemOutcome outcome = table.putItem(item, null, null, null);
		    }
	 
	 public void elimina(String nome) {
	        String tableName = "Luogo";
	        Table table = connessione.getTable(tableName);
	        DeleteItemOutcome outcome = table.deleteItem("Nome", nome);
	        }

}
