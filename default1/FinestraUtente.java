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

public class FinestraUtente {

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
	private JTable table_2;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTable table_1;
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
		
		JButton clienteCerca = new JButton("Cerca");
		clienteCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = clientetfNome.getText();
				String data = clienteData.getText();
				String cognome = clientetfCognome.getText();
				String email = clientetfEmail.getText();
				String codicefiscale = clientetfCodicefiscale.getText();
				//System.out.println("[FINESTRA UTENTE] Si vuole cercare: " + nome + cognome + email + codicefiscale + data);
				ClienteController.cerca(nome, cognome, email, codicefiscale, data);

			}
		});
		clienteCerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteCerca.setBounds(10, 150, 129, 23);
		Cliente.add(clienteCerca);
		
		JButton clienteInserisci = new JButton("Inserisci");
		clienteInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JButton clienteModifica = new JButton("Modifica");
		clienteModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = clientetable.getSelectedRow();
				clientetfNome.setText(clientetable.getValueAt(row,clientetable.getColumn("Nome").getModelIndex()).toString());
				clientetfCognome.setText(clientetable.getValueAt(row,clientetable.getColumn("Cognome").getModelIndex()).toString());
				clientetfCodicefiscale.setText(clientetable.getValueAt(row,clientetable.getColumn("Codice fiscale").getModelIndex()).toString());
				clienteData.setText(clientetable.getValueAt(row,clientetable.getColumn("Data di nascita").getModelIndex()).toString());
				clientetfEmail.setText(clientetable.getValueAt(row,clientetable.getColumn("eMail").getModelIndex()).toString());

			}
		});
		clienteModifica.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteModifica.setBounds(684, 150, 129, 23);
		Cliente.add(clienteModifica);
		
		JButton clienteElimina = new JButton("Elimina");
		clienteElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = clientetable.getSelectedRow();
		        String codicefiscale = clientetable.getValueAt(row,clientetable.getColumn("Codice fiscale").getModelIndex()).toString();
				ClienteController.elimina(codicefiscale);
			}
		});
		clienteElimina.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteElimina.setBounds(829, 150, 129, 23);
		Cliente.add(clienteElimina);
		
		JButton clienteStatistiche = new JButton("Statistiche");
		clienteStatistiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		clienteStatistiche.setBounds(684, 116, 274, 23);
		Cliente.add(clienteStatistiche);
		
		JButton clienteHelp = new JButton("?");
		clienteHelp.setBounds(921, 10, 37, 23);
		Cliente.add(clienteHelp);
		
		JButton clienteClear = new JButton("Clear");
		clienteClear.setBounds(831, 10, 80, 23);
		Cliente.add(clienteClear);
		
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
		
		JButton button_1 = new JButton("Cerca");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBounds(10, 150, 129, 23);
		Evento.add(button_1);
		
		JButton button_2 = new JButton("Inserisci");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(155, 150, 129, 23);
		Evento.add(button_2);
		
		JButton button_3 = new JButton("Modifica");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_3.setBounds(684, 150, 129, 23);
		Evento.add(button_3);
		
		JButton button_4 = new JButton("Elimina");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_4.setBounds(829, 150, 129, 23);
		Evento.add(button_4);
		
		JButton button_5 = new JButton("Statistiche");
		button_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_5.setBounds(684, 116, 274, 23);
		Evento.add(button_5);
		
		JButton button_6 = new JButton("?");
		button_6.setBounds(921, 10, 37, 23);
		Evento.add(button_6);
		
		JButton button_7 = new JButton("Clear");
		button_7.setBounds(831, 10, 80, 23);
		Evento.add(button_7);
		
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
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
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
		scrollPane_1.setViewportView(table_2);
		
		DatePicker datePicker_1 = new DatePicker();
		datePicker_1.setBounds(427, 70, 137, 22);
		Evento.add(datePicker_1);
		
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
		
		JButton button_8 = new JButton("Cerca");
		button_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_8.setBounds(10, 150, 129, 23);
		Luogo.add(button_8);
		
		JButton button_9 = new JButton("Inserisci");
		button_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_9.setBounds(155, 150, 129, 23);
		Luogo.add(button_9);
		
		JButton button_10 = new JButton("Modifica");
		button_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_10.setBounds(684, 150, 129, 23);
		Luogo.add(button_10);
		
		JButton button_11 = new JButton("Elimina");
		button_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_11.setBounds(829, 150, 129, 23);
		Luogo.add(button_11);
		
		JButton button_12 = new JButton("Statistiche");
		button_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_12.setBounds(684, 116, 274, 23);
		Luogo.add(button_12);
		
		JButton button_13 = new JButton("?");
		button_13.setBounds(921, 10, 37, 23);
		Luogo.add(button_13);
		
		JButton button_14 = new JButton("Clear");
		button_14.setBounds(831, 10, 80, 23);
		Luogo.add(button_14);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 202, 948, 248);
		Luogo.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
		scrollPane_2.setViewportView(table_1);
		
		JLabel messaggio = new JLabel("");
		messaggio.setForeground(Color.BLACK);
		messaggio.setBounds(14, 503, 963, 22);
		frmProgettoingswcsa.getContentPane().add(messaggio);
	}
}
