package default1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class FinestraUtente {
	
	public static JLabel messaggio = new JLabel("");
	public JFrame frmProgettoingswcsa;
	private int togglevar = 1;
	
	//cliente
	JButton clienteCerca = new JButton("Cerca");
	JButton clienteModifica = new JButton("Modifica");
	JButton clienteElimina = new JButton("Elimina");
	JButton clienteStatistiche = new JButton("Statistiche");
	public static JButton clienteClear = new JButton("Clear");
	JButton clienteInserisci = new JButton("Inserisci");
	public static JTable clientetable;
	private JTextField clientetfNome;
	private JTextField clientetfCognome;
	private JTextField clientetfEmail;
	private JTextField clientetfCodicefiscale;
	public DatePicker clienteData=null;
	
	//evento
	JButton eventoModifica = new JButton("Modifica");
	JButton eventoElimina = new JButton("Elimina");
	JButton eventoStatistiche = new JButton("Statistiche");
	JButton eventoCerca = new JButton("Cerca");
	JButton eventoInserisci = new JButton("Inserisci");
	public static JButton eventoClear = new JButton("Clear");
	public static JTable eventotable;
	private JTextField eventotfNome;
	private JTextField eventotfPrezzoiniziale;
	private JTextField eventotfPrezzofinale;
	private JTextField eventotfMassimoposti;
	JComboBox eventocbTipo = new JComboBox();
	JComboBox eventocbLuogo = new JComboBox();
	DatePicker eventoData = null;
	
	//luogo
	JButton luogoCerca = new JButton("Cerca");
	JButton luogoModifica = new JButton("Modifica");
	JButton luogoInserisci = new JButton("Inserisci");
	JButton luogoElimina = new JButton("Elimina");
	JButton luogoStatistiche = new JButton("Statistiche");
	public static JButton luogoClear = new JButton("Clear");
	public static JTable luogotable;
	private JTextField luogotfCitta;
	private JTextField luogotfStato;
	private JTextField luogotfIndirizzo;
	private JTextField luogotfNome;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public FinestraUtente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProgettoingswcsa = new JFrame();
		frmProgettoingswcsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgettoingswcsa.setResizable(false);
		frmProgettoingswcsa.setTitle("ProgettoINGSWcsa");
		frmProgettoingswcsa.getContentPane().setBackground(Color.WHITE);
		frmProgettoingswcsa.getContentPane().setLayout(null);
		frmProgettoingswcsa.setSize(1008, 575);
		frmProgettoingswcsa.setLocationRelativeTo(null);
		
		try {
		URL url = new URL("https://raw.githubusercontent.com/AleCaputo97/ProgettoINGSWcsa/master/icon.png");
		Image icon = ImageIO.read(url);  
	    frmProgettoingswcsa.setIconImage(icon);

		}
	    catch(Exception e){ }
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Product Sans", Font.PLAIN, 14));
		tabbedPane.setBounds(10, 0, 973, 492);
		frmProgettoingswcsa.getContentPane().add(tabbedPane);
		
		JPanel Cliente = new JPanel();
		Cliente.setBackground(Color.WHITE);
		tabbedPane.addTab("Cliente", null, Cliente, null);
		Cliente.setLayout(null);
		
		clientetfNome = new JTextField();
		clientetfNome.setBounds(105, 11, 179, 20);
		Cliente.add(clientetfNome);
		clientetfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 14, 85, 14);
		Cliente.add(lblNome);
		
		clientetfCognome = new JTextField();
		clientetfCognome.setColumns(10);
		clientetfCognome.setBounds(105, 39, 179, 20);
		Cliente.add(clientetfCognome);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(10, 42, 85, 14);
		Cliente.add(lblCognome);
		
		clientetfEmail = new JTextField();
		clientetfEmail.setColumns(10);
		clientetfEmail.setBounds(105, 70, 179, 20);
		Cliente.add(clientetfEmail);
		
		JLabel lblEmail = new JLabel("eMail:");
		lblEmail.setBounds(10, 73, 85, 14);
		Cliente.add(lblEmail);
		
		clientetfCodicefiscale = new JTextField();
		clientetfCodicefiscale.setColumns(10);
		clientetfCodicefiscale.setBounds(105, 101, 179, 20);
		Cliente.add(clientetfCodicefiscale);
		
		JLabel lblCodiceFiscale = new JLabel("Codice fiscale:");
		lblCodiceFiscale.setBounds(10, 104, 85, 14);
		Cliente.add(lblCodiceFiscale);
		
	    clienteData = new DatePicker();
	    eventoData = new DatePicker();
	    clienteData.getComponentDateTextField().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
		clienteData.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		clienteData.setBounds(429, 11, 137, 22);
		Cliente.add(clienteData);
//inizializza in Evento le combobox di Tipo (valori fissi) e Luoghi disponibili per la creazione, ricerca etc. di eventi
		eventocbTipo.addItem("");
		eventocbTipo.addItem("Concerto");
		eventocbTipo.addItem("Evento sportivo");
		eventocbTipo.addItem("Teatro");
		eventocbTipo.addItem("Convegno");
		eventocbTipo.addItem("Mostra");
		eventocbTipo.addItem("Altro");
		popolaeventocbLuogo();
		
		clienteCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String nome = clientetfNome.getText();
				String data = clienteData.getText();
				String cognome = clientetfCognome.getText();
				String email = clientetfEmail.getText();
				String codicefiscale = clientetfCodicefiscale.getText();
				messaggio.setText("");
				//System.out.println("[FINESTRA UTENTE] Si vuole cercare: " + nome + cognome + email + codicefiscale + data);
				ClienteController.cerca(nome, cognome, email, codicefiscale, data);
				clienteModifica.setEnabled(false);
				clienteElimina.setEnabled(false);
				clienteStatistiche.setEnabled(false);

			}
		});
		clienteCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteCerca.setBounds(10, 150, 129, 23);
		Cliente.add(clienteCerca);
		
		clienteInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clienteModifica.setEnabled(false);
				clienteElimina.setEnabled(false);
				clienteStatistiche.setEnabled(false);
				
				String nome = clientetfNome.getText();
				String data = clienteData.getText();
				String cognome = clientetfCognome.getText();
				String email = clientetfEmail.getText();
				String codicefiscale = clientetfCodicefiscale.getText();
				ClienteController.inserisci(nome, cognome, email, codicefiscale, data);
			}
		});
		clienteInserisci.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteInserisci.setBounds(155, 150, 129, 23);
		Cliente.add(clienteInserisci);
		clienteElimina.setEnabled(false);
		
		clienteElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = clientetable.getSelectedRow();
		        String codicefiscale = clientetable.getValueAt(row,clientetable.getColumn("Codice fiscale").getModelIndex()).toString();
		        ConfermaEliminazione frame = new ConfermaEliminazione("Cliente",codicefiscale,FinestraUtente.this);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				toggle();
			}
		});
		clienteElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteElimina.setBounds(829, 150, 129, 23);
		Cliente.add(clienteElimina);
		clienteStatistiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		clienteStatistiche.setEnabled(false);
		
		clienteStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteStatistiche.setBounds(684, 116, 274, 23);
		Cliente.add(clienteStatistiche);
		
		clienteClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientetfNome.setText("");
				clientetfCognome.setText("");
				clientetfEmail.setText("");
				clientetfCodicefiscale.setText("");
				clienteData.setText("");
				messaggio.setText("");
				clienteModifica.setEnabled(false);
				clienteElimina.setEnabled(false);
				clienteStatistiche.setEnabled(false);
				
				DefaultTableModel model = (DefaultTableModel) FinestraUtente.clientetable.getModel();
		        int i;
		        int j = model.getRowCount();
		        for (i=0; i<j; i++)
		            model.removeRow(0);
		        
			}
		});
		clienteClear.setBounds(831, 10, 80, 23);
		Cliente.add(clienteClear);
		
		clienteModifica.setEnabled(false);
		clienteModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (clienteModifica.getText().equals("Modifica")){
				
				int row = clientetable.getSelectedRow();
				clientetfNome.setText(clientetable.getValueAt(row,clientetable.getColumn("Nome").getModelIndex()).toString());
				clientetfCognome.setText(clientetable.getValueAt(row,clientetable.getColumn("Cognome").getModelIndex()).toString());
				clientetfCodicefiscale.setText(clientetable.getValueAt(row,clientetable.getColumn("Codice fiscale").getModelIndex()).toString());
				clienteData.setText(clientetable.getValueAt(row,clientetable.getColumn("Data di nascita").getModelIndex()).toString());
				clientetfEmail.setText(clientetable.getValueAt(row,clientetable.getColumn("eMail").getModelIndex()).toString());
				
				clienteCerca.setEnabled(false);
				clienteStatistiche.setEnabled(false);
				clienteElimina.setEnabled(false);
				clienteInserisci.setEnabled(false);
				clienteClear.setEnabled(false);
				clientetfCodicefiscale.setEnabled(false);
				
				clienteModifica.setText("Conferma");
				
				}
				else {
					
					
					String nome = clientetfNome.getText();
					String data = clienteData.getText();
					String cognome = clientetfCognome.getText();
					String email = clientetfEmail.getText();
					String codicefiscale = clientetfCodicefiscale.getText();
					
					if (ClienteController.modifica(nome, cognome, email, codicefiscale, data)) {
					
					clienteCerca.setEnabled(true);
					clienteStatistiche.setEnabled(true);
					clienteElimina.setEnabled(true);
					clienteInserisci.setEnabled(true);
					clienteClear.setEnabled(true);
					clientetfCodicefiscale.setEnabled(true);
					clienteClear.doClick();
					clienteModifica.setText("Modifica");
					FinestraUtente.messaggio.setText("<html><font color=\"blue\">Cliente modificato correttamente </font></html>");
					}
					}


					
				}
				
				

			}
		);
		clienteModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteModifica.setBounds(684, 150, 129, 23);
		Cliente.add(clienteModifica);
		
		JButton clienteHelp = new JButton("?");
		clienteHelp.setBounds(921, 10, 37, 23);
		Cliente.add(clienteHelp);
		

		
		JLabel lblDataDiNascita = new JLabel("Data di nascita:");
		lblDataDiNascita.setBounds(332, 14, 85, 14);
		Cliente.add(lblDataDiNascita);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 948, 248);
		Cliente.add(scrollPane);
		
		clientetable = new JTable();
		clientetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientetable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Cognome", "eMail", "Codice fiscale", "Data di nascita"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		clientetable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent event) {
				 
				 if (clienteModifica.getText().equals("Modifica")) {
					 clienteModifica.setEnabled(true);
					 clienteElimina.setEnabled(true);
					 clienteStatistiche.setEnabled(true);
				 }
			 }
			});
		scrollPane.setViewportView(clientetable);
		

		
		JPanel Evento = new JPanel();
		Evento.setBackground(Color.WHITE);
		Evento.setLayout(null);
		tabbedPane.addTab("Evento", null, Evento, null);
		
		eventotfNome = new JTextField();
		eventotfNome.setColumns(10);
		eventotfNome.setBounds(105, 11, 179, 20);
		Evento.add(eventotfNome);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 14, 85, 14);
		Evento.add(label);
		
		eventotfPrezzoiniziale = new JTextField();
		eventotfPrezzoiniziale.setColumns(10);
		eventotfPrezzoiniziale.setBounds(105, 39, 179, 20);
		Evento.add(eventotfPrezzoiniziale);
		
		JLabel lblPrezzoIniziale = new JLabel("Prezzo iniziale:");
		lblPrezzoIniziale.setBounds(10, 42, 85, 14);
		Evento.add(lblPrezzoIniziale);
		
		eventotfPrezzofinale = new JTextField();
		eventotfPrezzofinale.setColumns(10);
		eventotfPrezzofinale.setBounds(105, 70, 179, 20);
		Evento.add(eventotfPrezzofinale);
		
		JLabel lblPrezzoFinale = new JLabel("Prezzo finale");
		lblPrezzoFinale.setBounds(10, 73, 85, 14);
		Evento.add(lblPrezzoFinale);
		///
		eventotfMassimoposti = new JTextField();
		eventotfMassimoposti.setColumns(10);
		eventotfMassimoposti.setBounds(105, 101, 179, 20);
		Evento.add(eventotfMassimoposti);
		
		JLabel lblMassimoPosti = new JLabel("Massimo posti:");
		lblMassimoPosti.setBounds(10, 104, 85, 14);
		Evento.add(lblMassimoPosti);
		
		eventoCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String nome = eventotfNome.getText();
				String data = eventoData.getText();
				double prezzoiniziale = 00.00;
				if(isDouble(eventotfPrezzoiniziale.getText())==false || isDouble(eventotfPrezzofinale.getText())==false || isInteger(eventotfMassimoposti.getText())==false) {
					messaggio.setText("Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!");
				}
				
				if (!(eventotfPrezzoiniziale.getText().equals(""))) prezzoiniziale=Double.parseDouble(eventotfPrezzoiniziale.getText());
				double prezzofinale = 00.00;
				if (!(eventotfPrezzofinale.getText().equals(""))) prezzofinale=Double.parseDouble(eventotfPrezzofinale.getText());
				int maxspettatori = 0;
				
				if (!(eventotfMassimoposti.getText().equals(""))) maxspettatori=Integer.parseInt(eventotfMassimoposti.getText());
				String tipo = "", luogo = "";
				if (!(eventocbTipo.getSelectedItem().equals(""))) tipo = eventocbTipo.getSelectedItem().toString();
				if (!(eventocbLuogo.getSelectedItem().equals(""))) luogo = eventocbLuogo.getSelectedItem().toString();
				messaggio.setText("");
				EventoController.cerca(nome,  data, prezzoiniziale, prezzofinale,  maxspettatori,  tipo, luogo);
				
				eventoModifica.setEnabled(false);
				eventoElimina.setEnabled(false);
				eventoStatistiche.setEnabled(false);
				}catch(Exception E) {
					//do something
				}
			}
			
		});
		eventoCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoCerca.setBounds(10, 150, 129, 23);
		Evento.add(eventoCerca);
		
		eventoInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if(isDouble(eventotfPrezzoiniziale.getText())==false || isDouble(eventotfPrezzofinale.getText())==false || isInteger(eventotfMassimoposti.getText())==false) {
					messaggio.setText("Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!");
					return;
				}
				eventoModifica.setEnabled(false);
				eventoElimina.setEnabled(false);
				eventoStatistiche.setEnabled(false);
				
				String nome = eventotfNome.getText();
				String data = eventoData.getText();
				float prezzoiniziale = Float.parseFloat(eventotfPrezzoiniziale.getText());
				float prezzofinale = Float.parseFloat(eventotfPrezzofinale.getText());
				int maxspettatori = Integer.parseInt(eventotfMassimoposti.getText());
				String tipo = eventocbTipo.getSelectedItem().toString();
				String luogo = eventocbLuogo.getSelectedItem().toString();
				EventoController.inserisci(nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo);
				}catch(Exception e2) {
					//do something
				}
			}
		});
		eventoInserisci.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoInserisci.setBounds(155, 150, 129, 23);
		Evento.add(eventoInserisci);
		
		eventoModifica.setEnabled(false);
		eventoModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				if (eventoModifica.getText().equals("Modifica")){
					
					int row = eventotable.getSelectedRow();
					eventotfNome.setText(eventotable.getValueAt(row,eventotable.getColumn("Nome").getModelIndex()).toString());
					eventoData.setText(eventotable.getValueAt(row,eventotable.getColumn("Data").getModelIndex()).toString());					
					eventotfPrezzoiniziale.setText(eventotable.getValueAt(row,eventotable.getColumn("€ Iniziale").getModelIndex()).toString());
					eventotfPrezzofinale.setText(eventotable.getValueAt(row,eventotable.getColumn("€ Finale").getModelIndex()).toString());
					eventotfMassimoposti.setText(eventotable.getValueAt(row,eventotable.getColumn("Max posti").getModelIndex()).toString());	
					eventocbTipo.setSelectedItem(eventotable.getValueAt(row,eventotable.getColumn("Tipo").getModelIndex()).toString());
					eventocbLuogo.setSelectedItem(eventotable.getValueAt(row,eventotable.getColumn("Luogo").getModelIndex()).toString());
										
					eventoCerca.setEnabled(false);
					eventoStatistiche.setEnabled(false);
					eventoElimina.setEnabled(false);
					eventoInserisci.setEnabled(false);
					eventoClear.setEnabled(false);
					eventotfNome.setEnabled(false);
					
					eventoModifica.setText("Conferma");
					
					}
					else {
						if(isDouble(eventotfPrezzoiniziale.getText())==false || isDouble(eventotfPrezzofinale.getText())==false || isInteger(eventotfMassimoposti.getText())==false) {
							messaggio.setText("Prezzo iniziale e finale e il numero di spettatori devono essere valori numerici!");
							return;
						}
						eventoModifica.setText("Modifica");
						String nome = eventotfNome.getText();
						String data = eventoData.getText();
						float prezzoiniziale = Float.parseFloat(eventotfPrezzoiniziale.getText());
						float prezzofinale = Float.parseFloat(eventotfPrezzofinale.getText());
						int maxspettatori = Integer.parseInt(eventotfMassimoposti.getText());
						String tipo = eventocbTipo.getSelectedItem().toString();
						String luogo = eventocbLuogo.getSelectedItem().toString();
						EventoController.modifica(nome,  data,  prezzoiniziale,  prezzofinale,  maxspettatori,  tipo, luogo);
						
						eventoCerca.setEnabled(true);
						eventoStatistiche.setEnabled(true);
						eventoElimina.setEnabled(true);
						eventoInserisci.setEnabled(true);
						eventoClear.setEnabled(true);
						eventotfNome.setEnabled(true);
						eventoClear.doClick();
				
			}
			}catch(Exception e3) {
				//do something
			}
		}});
		eventoModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoModifica.setBounds(684, 150, 129, 23);
		Evento.add(eventoModifica);
		
		eventoElimina.setEnabled(false);
		eventoElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = eventotable.getSelectedRow();
		        String nome = eventotable.getValueAt(row,eventotable.getColumn("Nome").getModelIndex()).toString();
		        ConfermaEliminazione frame = new ConfermaEliminazione("Evento",nome,FinestraUtente.this);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				toggle();
				
			}
		});
		eventoElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoElimina.setBounds(829, 150, 129, 23);
		Evento.add(eventoElimina);
		
		eventoStatistiche.setEnabled(false);
		eventoStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoStatistiche.setBounds(684, 116, 274, 23);
		Evento.add(eventoStatistiche);
		
		JButton eventoHelp = new JButton("?");
		eventoHelp.setBounds(921, 10, 37, 23);
		Evento.add(eventoHelp);
		
		eventoClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eventotfNome.setText("");
				eventoData.setText("");
				eventotfPrezzoiniziale.setText("");
				eventotfPrezzofinale.setText("");
				eventotfMassimoposti.setText("");
				eventocbLuogo.setSelectedIndex(0);
				eventocbTipo.setSelectedIndex(0);
				messaggio.setText("");

				DefaultTableModel model = (DefaultTableModel) FinestraUtente.eventotable.getModel();
		        int i;
		        int j = model.getRowCount();
		        for (i=0; i<j; i++)
		            model.removeRow(0);

				
			}
		});
		eventoClear.setBounds(831, 10, 80, 23);
		Evento.add(eventoClear);
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setBounds(332, 14, 85, 14);
		Evento.add(lblLuogo);
		
		eventocbLuogo.setBounds(427, 10, 137, 20);
		Evento.add(eventocbLuogo);
		
		eventocbTipo.setBounds(427, 39, 137, 20);
		Evento.add(eventocbTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(332, 43, 85, 14);
		Evento.add(lblTipo);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(332, 73, 85, 14);
		Evento.add(lblData);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 202, 948, 248);
		Evento.add(scrollPane_1);
		
		eventotable = new JTable();
		eventotable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Luogo", "Data", "\u20AC Iniziale", "\u20AC Finale", "Max posti", "Tipo", "Prezzo corrente"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(eventotable);
		
		eventotable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent event) {
				 
				 if (eventoModifica.getText().equals("Modifica")) {
					 eventoModifica.setEnabled(true);
					 eventoElimina.setEnabled(true);
					 eventoStatistiche.setEnabled(true);
				 }
			 }
			});
		
		eventoData.setBounds(427, 70, 137, 22);
		Evento.add(eventoData);
		
		JPanel Luogo = new JPanel();
		Luogo.setBackground(Color.WHITE);
		Luogo.setLayout(null);
		tabbedPane.addTab("Luogo", null, Luogo, null);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(10, 14, 85, 14);
		Luogo.add(label_1);
		
		luogotfCitta = new JTextField();
		luogotfCitta.setColumns(10);
		luogotfCitta.setBounds(105, 39, 179, 20);
		Luogo.add(luogotfCitta);
		
		JLabel lblCitt = new JLabel("Citt\u00E0:");
		lblCitt.setBounds(10, 42, 85, 14);
		Luogo.add(lblCitt);
		
		luogotfStato = new JTextField();
		luogotfStato.setColumns(10);
		luogotfStato.setBounds(105, 70, 179, 20);
		Luogo.add(luogotfStato);
		
		JLabel lblStato = new JLabel("Stato:");
		lblStato.setBounds(10, 73, 85, 14);
		Luogo.add(lblStato);
		
		luogotfIndirizzo = new JTextField();
		luogotfIndirizzo.setColumns(10);
		luogotfIndirizzo.setBounds(105, 101, 179, 20);
		Luogo.add(luogotfIndirizzo);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setBounds(10, 104, 85, 14);
		Luogo.add(lblIndirizzo);
		luogoCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = luogotfNome.getText();
				String citta = luogotfCitta.getText();
				String stato = luogotfStato.getText();
				String indirizzo = luogotfIndirizzo.getText();
				messaggio.setText("");
				LuogoController.cerca(nome, citta, stato, indirizzo);
				luogoModifica.setEnabled(false);
				luogoElimina.setEnabled(false);
				luogoStatistiche.setEnabled(false);
				
			}
		});
		
		luogoCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoCerca.setBounds(10, 150, 129, 23);
		Luogo.add(luogoCerca);
		luogoInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				luogoModifica.setEnabled(false);
				luogoElimina.setEnabled(false);
				luogoStatistiche.setEnabled(false);
				
				String nome = luogotfNome.getText();
				String citta = luogotfCitta.getText();
				String stato = luogotfStato.getText();
				String indirizzo = luogotfIndirizzo.getText();
				LuogoController.inserisci(nome, citta, stato, indirizzo);	
				popolaeventocbLuogo();
			}
		});

		luogoInserisci.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoInserisci.setBounds(155, 150, 129, 23);
		Luogo.add(luogoInserisci);
		luogoModifica.setEnabled(false);
		luogoModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (luogoModifica.getText().equals("Modifica")){
					
					int row = luogotable.getSelectedRow();
					luogotfNome.setText(luogotable.getValueAt(row,luogotable.getColumn("Nome").getModelIndex()).toString());
					luogotfCitta.setText(luogotable.getValueAt(row,luogotable.getColumn("Città").getModelIndex()).toString());
					luogotfStato.setText(luogotable.getValueAt(row,luogotable.getColumn("Stato").getModelIndex()).toString());
					luogotfIndirizzo.setText(luogotable.getValueAt(row,luogotable.getColumn("Indirizzo").getModelIndex()).toString());
					
					luogoCerca.setEnabled(false);
					luogoStatistiche.setEnabled(false);
					luogoElimina.setEnabled(false);
					luogoInserisci.setEnabled(false);
					luogoClear.setEnabled(false);
					luogotfNome.setEnabled(false);
					
					luogoModifica.setText("Conferma");
					
					}
					else {
						
						luogoModifica.setText("Modifica");
						
						String nome = luogotfNome.getText();
						String citta = luogotfCitta.getText();
						String stato = luogotfStato.getText();
						String indirizzo = luogotfIndirizzo.getText();
						LuogoController.inserisci(nome, citta, stato, indirizzo);		
						popolaeventocbLuogo();
						luogoCerca.setEnabled(true);
						luogoStatistiche.setEnabled(true);
						luogoElimina.setEnabled(true);
						luogoInserisci.setEnabled(true);
						luogoClear.setEnabled(true);
						luogotfNome.setEnabled(true);
						luogoClear.doClick();


						
					}
					
					}
				
			
		});
		
		
		luogoModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoModifica.setBounds(684, 150, 129, 23);
		Luogo.add(luogoModifica);
		luogoElimina.setEnabled(false);
		luogoElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = luogotable.getSelectedRow();
		        String nome = luogotable.getValueAt(row,luogotable.getColumn("Nome").getModelIndex()).toString();
		        ConfermaEliminazione frame = new ConfermaEliminazione("Luogo",nome,FinestraUtente.this);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				toggle();
				popolaeventocbLuogo();
			}
		});
		

		luogoElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoElimina.setBounds(829, 150, 129, 23);
		Luogo.add(luogoElimina);
		luogoStatistiche.setEnabled(false);
		

		luogoStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoStatistiche.setBounds(684, 116, 274, 23);
		Luogo.add(luogoStatistiche);
		
		JButton luogoHelp = new JButton("?");
		luogoHelp.setBounds(921, 10, 37, 23);
		Luogo.add(luogoHelp);
		luogoClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				luogotfNome.setText("");
				luogotfCitta.setText("");
				luogotfStato.setText("");
				luogotfIndirizzo.setText("");
				messaggio.setText("");

				
				DefaultTableModel model = (DefaultTableModel) FinestraUtente.luogotable.getModel();
		        int i;
		        int j = model.getRowCount();
		        for (i=0; i<j; i++)
		            model.removeRow(0);
				
			}
		});
		
		luogoClear.setBounds(831, 10, 80, 23);
		Luogo.add(luogoClear);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 202, 948, 248);
		Luogo.add(scrollPane_2);
		
		luogotable = new JTable();
		luogotable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Citt\u00E0", "Stato", "Indirizzo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		luogotable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent event) {
				 
				 if (luogoModifica.getText().equals("Modifica")) {
					 luogoModifica.setEnabled(true);
					 luogoElimina.setEnabled(true);
					 luogoStatistiche.setEnabled(true);
				 }
			 }
			});
		
		scrollPane_2.setViewportView(luogotable);
		
		luogotfNome = new JTextField();
		luogotfNome.setColumns(10);
		luogotfNome.setBounds(105, 11, 179, 20);
		Luogo.add(luogotfNome);
		messaggio.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		messaggio.setForeground(Color.BLACK);
		messaggio.setBounds(14, 503, 963, 22);
		frmProgettoingswcsa.getContentPane().add(messaggio);
	}
	
	public void toggle() {
		
		if (togglevar==1) {
		clientetfNome.setEnabled(false);
		clientetfCognome.setEnabled(false);
		clientetfEmail.setEnabled(false);
		clientetfCodicefiscale.setEnabled(false);
		clienteData.setEnabled(false);
		clienteCerca.setEnabled(false);
		clienteModifica.setEnabled(false);
		clienteInserisci.setEnabled(false);
		clienteStatistiche.setEnabled(false);
		clienteClear.setEnabled(false);
		clienteElimina.setEnabled(false);
		clientetable.setEnabled(false);
		
		eventoCerca.setEnabled(false);
		eventoStatistiche.setEnabled(false);
		eventoElimina.setEnabled(false);
		eventoModifica.setEnabled(false);
		eventoInserisci.setEnabled(false);
		eventoClear.setEnabled(false);
		eventotfNome.setEnabled(false);
		eventoData.setEnabled(false);
		eventocbTipo.setEnabled(false);
		eventocbLuogo.setEnabled(false);
		eventotfPrezzoiniziale.setEnabled(false);
		eventotfPrezzofinale.setEnabled(false);
		eventotfMassimoposti.setEnabled(false);
		eventotable.setEnabled(false);
		
		luogoCerca.setEnabled(false);
		luogoStatistiche.setEnabled(false);
		luogoElimina.setEnabled(false);
		luogoModifica.setEnabled(false);
		luogoInserisci.setEnabled(false);
		luogoClear.setEnabled(false);
		luogotfNome.setEnabled(false);
		luogotfCitta.setEnabled(false);
		luogotfStato.setEnabled(false);
		luogotfIndirizzo.setEnabled(false);
		luogotable.setEnabled(false);

		
		togglevar=0;
		}
		else {
			clientetfNome.setEnabled(true);
			clientetfCognome.setEnabled(true);
			clientetfEmail.setEnabled(true);
			clientetfCodicefiscale.setEnabled(true);
			clienteData.setEnabled(true);
			clienteCerca.setEnabled(true);
			clienteModifica.setEnabled(true);
			clienteInserisci.setEnabled(true);
			clienteStatistiche.setEnabled(true);
			clienteClear.setEnabled(true);
			clienteElimina.setEnabled(true);
			clientetable.setEnabled(true);
			
			eventoCerca.setEnabled(true);
			eventoStatistiche.setEnabled(true);
			eventoElimina.setEnabled(true);
			eventoInserisci.setEnabled(true);
			eventoModifica.setEnabled(true);
			eventoClear.setEnabled(true);
			eventotfNome.setEnabled(true);
			eventoData.setEnabled(true);
			eventocbTipo.setEnabled(true);
			eventocbLuogo.setEnabled(true);
			eventotfPrezzoiniziale.setEnabled(true);
			eventotfPrezzofinale.setEnabled(true);
			eventotfMassimoposti.setEnabled(true);
			eventotable.setEnabled(true);
			
			luogoCerca.setEnabled(true);
			luogoStatistiche.setEnabled(true);
			luogoElimina.setEnabled(true);
			luogoModifica.setEnabled(true);
			luogoInserisci.setEnabled(true);
			luogoClear.setEnabled(true);
			luogotfNome.setEnabled(true);
			luogotfCitta.setEnabled(true);
			luogotfStato.setEnabled(true);
			luogotfIndirizzo.setEnabled(true);
			luogotable.setEnabled(true);
			
			togglevar=1;
		}
	}
	
	public void popolaeventocbLuogo (){
		eventocbLuogo.removeAllItems();
		eventocbLuogo.addItem("");
		List<Luogo> risultati = LuogoDAO.cerca("","","","");
		for(Luogo curr:risultati){
			eventocbLuogo.addItem(curr.getNome());
		}
	}
	
	public boolean isInteger (String testo) throws Exception {
	 try {
		  Integer.parseInt(testo);
		  return true;
	 }catch(Exception E) {
		 return false;
	 }
	}
	
	public boolean isDouble (String testo) throws Exception {
		 try {
			  Double.parseDouble(testo);
			  return true;
		 }catch(Exception E) {
			 return false;
		 }
		}
}