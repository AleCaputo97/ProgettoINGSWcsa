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

public class FinestraUtente {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraUtente window = new FinestraUtente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 973, 481);
		frame.getContentPane().add(tabbedPane);
		
		JPanel Cliente = new JPanel();
		tabbedPane.addTab("Cliente", null, Cliente, null);
		Cliente.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(105, 11, 179, 20);
		Cliente.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 14, 85, 14);
		Cliente.add(lblNome);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(105, 39, 179, 20);
		Cliente.add(textField);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(10, 42, 85, 14);
		Cliente.add(lblCognome);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(105, 70, 179, 20);
		Cliente.add(textField_1);
		
		JLabel lblEmail = new JLabel("eMail:");
		lblEmail.setBounds(10, 73, 85, 14);
		Cliente.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(105, 101, 179, 20);
		Cliente.add(textField_2);
		
		JLabel lblCodiceFiscale = new JLabel("Codice fiscale:");
		lblCodiceFiscale.setBounds(10, 104, 85, 14);
		Cliente.add(lblCodiceFiscale);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(10, 150, 129, 23);
		Cliente.add(btnCerca);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(155, 150, 129, 23);
		Cliente.add(btnInserisci);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(684, 150, 129, 23);
		Cliente.add(btnModifica);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(829, 150, 129, 23);
		Cliente.add(btnElimina);
		
		JButton btnStatistiche = new JButton("Statistiche");
		btnStatistiche.setBounds(684, 116, 274, 23);
		Cliente.add(btnStatistiche);
		
		JButton button = new JButton("?");
		button.setBounds(921, 10, 37, 23);
		Cliente.add(button);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(831, 10, 80, 23);
		Cliente.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 948, 240);
		Cliente.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
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
		scrollPane.setViewportView(table);
		
		JLabel lblDataDiNascita = new JLabel("Data di nascita:");
		lblDataDiNascita.setBounds(332, 14, 85, 14);
		Cliente.add(lblDataDiNascita);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(427, 12, 132, 20);
		Cliente.add(datePicker);
		
		JPanel Evento = new JPanel();
		tabbedPane.addTab("Evento", null, Evento, null);
		Evento.setLayout(null);
		
		JPanel Luogo = new JPanel();
		tabbedPane.addTab("Luogo", null, Luogo, null);
	}
}
