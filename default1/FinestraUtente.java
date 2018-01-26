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

public class FinestraUtente {
	JButton clienteCerca = new JButton("Cerca");
	JButton clienteModifica = new JButton("Modifica");
	JButton clienteElimina = new JButton("Elimina");
	JButton clienteStatistiche = new JButton("Statistiche");
	JButton clienteClear = new JButton("Clear");
	JButton clienteInserisci = new JButton("Inserisci");
	public static JLabel messaggio = new JLabel("");
	public JFrame frmProgettoingswcsa;
	private JTextField clientetfNome;
	private JTextField clientetfCognome;
	private JTextField clientetfEmail;
	private JTextField clientetfCodicefiscale;
	public static JTable clientetable;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	public static JTable eventotable;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	public static JTable luogotable;
	public DatePicker clienteData=null;

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
					
					clienteModifica.setText("Modifica");
					
					String nome = clientetfNome.getText();
					String data = clienteData.getText();
					String cognome = clientetfCognome.getText();
					String email = clientetfEmail.getText();
					String codicefiscale = clientetfCodicefiscale.getText();
					ClienteController.inserisci(nome, cognome, email, codicefiscale, data);
					
					clienteCerca.setEnabled(true);
					clienteStatistiche.setEnabled(true);
					clienteElimina.setEnabled(true);
					clienteInserisci.setEnabled(true);
					clienteClear.setEnabled(true);
					clientetfCodicefiscale.setEnabled(true);
					clienteClear.doClick();


					
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(105, 11, 179, 20);
		Evento.add(textField_3);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 14, 85, 14);
		Evento.add(label);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(105, 39, 179, 20);
		Evento.add(textField_4);
		
		JLabel lblPrezzoIniziale = new JLabel("Prezzo iniziale:");
		lblPrezzoIniziale.setBounds(10, 42, 85, 14);
		Evento.add(lblPrezzoIniziale);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(105, 70, 179, 20);
		Evento.add(textField_5);
		
		JLabel lblPrezzoFinale = new JLabel("Prezzo finale");
		lblPrezzoFinale.setBounds(10, 73, 85, 14);
		Evento.add(lblPrezzoFinale);
		///
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(105, 101, 179, 20);
		Evento.add(textField_6);
		
		JLabel lblMassimoPosti = new JLabel("Massimo posti:");
		lblMassimoPosti.setBounds(10, 104, 85, 14);
		Evento.add(lblMassimoPosti);
		
		JButton eventoCerca = new JButton("Cerca");
		eventoCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		eventoCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoCerca.setBounds(10, 150, 129, 23);
		Evento.add(eventoCerca);
		
		JButton eventoInserisci = new JButton("Inserisci");
		eventoInserisci.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoInserisci.setBounds(155, 150, 129, 23);
		Evento.add(eventoInserisci);
		
		JButton eventoModifica = new JButton("Modifica");
		eventoModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoModifica.setBounds(684, 150, 129, 23);
		Evento.add(eventoModifica);
		
		JButton eventoElimina = new JButton("Elimina");
		eventoElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoElimina.setBounds(829, 150, 129, 23);
		Evento.add(eventoElimina);
		
		JButton eventoStatistiche = new JButton("Statistiche");
		eventoStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		eventoStatistiche.setBounds(684, 116, 274, 23);
		Evento.add(eventoStatistiche);
		
		JButton eventoHelp = new JButton("?");
		eventoHelp.setBounds(921, 10, 37, 23);
		Evento.add(eventoHelp);
		
		JButton eventoClear = new JButton("Clear");
		eventoClear.setBounds(831, 10, 80, 23);
		Evento.add(eventoClear);
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setBounds(332, 14, 85, 14);
		Evento.add(lblLuogo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(427, 10, 137, 20);
		Evento.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(427, 39, 137, 20);
		Evento.add(comboBox_1);
		
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
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Nome", "Luogo", "Data", "\u20AC Iniziale", "\u20AC Finale", "Max posti", "Tipo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(eventotable);
		
		DatePicker eventoData = new DatePicker();
		eventoData.setBounds(427, 70, 137, 22);
		Evento.add(eventoData);
		
		JPanel Luogo = new JPanel();
		Luogo.setBackground(Color.WHITE);
		Luogo.setLayout(null);
		tabbedPane.addTab("Luogo", null, Luogo, null);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(105, 11, 179, 20);
		Luogo.add(textField_7);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(10, 14, 85, 14);
		Luogo.add(label_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(105, 39, 179, 20);
		Luogo.add(textField_8);
		
		JLabel label_2 = new JLabel("Cognome:");
		label_2.setBounds(10, 42, 85, 14);
		Luogo.add(label_2);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(105, 70, 179, 20);
		Luogo.add(textField_9);
		
		JLabel label_3 = new JLabel("eMail:");
		label_3.setBounds(10, 73, 85, 14);
		Luogo.add(label_3);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(105, 101, 179, 20);
		Luogo.add(textField_10);
		
		JLabel label_4 = new JLabel("Codice fiscale:");
		label_4.setBounds(10, 104, 85, 14);
		Luogo.add(label_4);
		
		JButton luogoCerca = new JButton("Cerca");
		luogoCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoCerca.setBounds(10, 150, 129, 23);
		Luogo.add(luogoCerca);
		
		JButton luogoInserisci = new JButton("Inserisci");
		luogoInserisci.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoInserisci.setBounds(155, 150, 129, 23);
		Luogo.add(luogoInserisci);
		
		JButton luogoModifica = new JButton("Modifica");
		luogoModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoModifica.setBounds(684, 150, 129, 23);
		Luogo.add(luogoModifica);
		
		JButton luogoElimina = new JButton("Elimina");
		luogoElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoElimina.setBounds(829, 150, 129, 23);
		Luogo.add(luogoElimina);
		
		JButton luogoStatistiche = new JButton("Statistiche");
		luogoStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		luogoStatistiche.setBounds(684, 116, 274, 23);
		Luogo.add(luogoStatistiche);
		
		JButton luogoHelp = new JButton("?");
		luogoHelp.setBounds(921, 10, 37, 23);
		Luogo.add(luogoHelp);
		
		JButton luogoClear = new JButton("Clear");
		luogoClear.setBounds(831, 10, 80, 23);
		Luogo.add(luogoClear);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 202, 948, 248);
		Luogo.add(scrollPane_2);
		
		luogotable = new JTable();
		luogotable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
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
		scrollPane_2.setViewportView(luogotable);
		messaggio.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		messaggio.setForeground(Color.BLACK);
		messaggio.setBounds(14, 503, 963, 22);
		frmProgettoingswcsa.getContentPane().add(messaggio);
	}
	
	public void toggle() {
		
		if (clientetfNome.isEnabled()) {
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
		}
	}
	
}