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
public class EventoDAO {
	private DynamoDB connessione;
	//costruttore che prende in input soltanto la connessione con il database
	public EventoDAO(DynamoDB Connessione) {
		connessione=Connessione;
	}
	
	public List<Evento> cerca(String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo) {	 
		   String tableName = "Evento";
		   Iterator<Item> iterator = null;
		   List<Evento> risultati=new ArrayList<Evento>();  
		   Table table =connessione.getTable(tableName);
		   Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		   //se ogni campo è vuoto deve svolgere una scan di tutto
		   if(nome.equals("") && data.equals("") && prezzoiniziale==0.00 && prezzofinale==0.00 && maxspettatori==0 && tipo.equals("")&& luogo.equals("")) {
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
				   ricerca=ricerca + "AND DataEvento = :data ";
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
			   if(!(luogo.equals(""))) {
				   ricerca=ricerca + "AND Luogo = :luogo ";
				   expressionAttributeValues.put(":luogo", luogo);
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
		   Evento curr;
	       while (iterator.hasNext()) {
	        iteratorcurr = iterator.next();
	        curr=new Evento((String) iteratorcurr.get("Nome"),(String) iteratorcurr.get("DataEvento"),(double) Double.parseDouble(iteratorcurr.get("PrezzoIniziale").toString()),(double) Double.parseDouble(iteratorcurr.get("PrezzoFinale").toString()),(int) Integer.parseInt(iteratorcurr.get("MassimoSpettatori").toString()),(String) iteratorcurr.get("Tipo"), (String) iteratorcurr.get("Luogo"), (String) iteratorcurr.get("DataInserimento"));
	        risultati.add(curr);
	       }
	   return risultati;
	}
	
	 public Evento cerca(String nome) {
		   String tableName = "Evento";
		   Table table =connessione.getTable(tableName);
		   Item item = null;
		   item = table.getItem("Nome", nome);
		   if (item!=null) {
			   return new Evento((String) item.get("Nome"),(String) item.get("DataEvento"),(double) Double.parseDouble(item.get("PrezzoIniziale").toString()),(double) Double.parseDouble(item.get("PrezzoFinale").toString()),(int) Integer.parseInt(item.get("MassimoSpettatori").toString()),(String) item.get("Tipo"), (String) item.get("Luogo"), (String) item.get("DataInserimento"));
		   }else {
			   return null;
		   }
	 }
		    
		
	 
	 
	 public void inserisciModifica(String nome, String data, double prezzoiniziale, double prezzofinale, int maxspettatori, String tipo, String luogo, String datainserimento) {
		    String tableName = "Evento";
		    Table table = connessione.getTable(tableName);
		    Item item = new Item()
		    	    .withPrimaryKey("Nome", nome)
		    	    .withString("DataEvento", data)
		    	    .withNumber("PrezzoIniziale", prezzoiniziale)
		    	    .withNumber("PrezzoFinale", prezzofinale)
		    	    .withNumber("MassimoSpettatori", maxspettatori)
		    		.withString("Luogo", luogo)
		    		.withString("Tipo", tipo)
		    		.withString("DataInserimento", datainserimento);
		    PutItemOutcome outcome = table.putItem(item, null, null, null);  
		    }
	 
	 public void elimina(String Nome) {
	        String tableName = "Evento";
	        Table table = connessione.getTable(tableName);
	        DeleteItemOutcome outcome = table.deleteItem("Nome", Nome);
	        }
}
