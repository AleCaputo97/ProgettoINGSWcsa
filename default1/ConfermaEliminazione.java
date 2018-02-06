package default1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfermaEliminazione extends JFrame {
	
	public String doveeliminare="", elemento;
	FinestraUtente window;
	private JPanel contentPane;
	
	//crea il frame prendendo in input cosa eliminare e dove eliminare (si può voler eliminare un luogo, un evento o un Cliente)
	public ConfermaEliminazione(String DoveEliminare, String CosaEliminare, FinestraUtente Window) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 405, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		doveeliminare = DoveEliminare;
		elemento = CosaEliminare;
		window=Window;
		JLabel lblSicuroDiVoler = new JLabel("<html>Sicuro di voler eliminare <i>" + CosaEliminare + "</i> ?</html>");
		lblSicuroDiVoler.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSicuroDiVoler.setHorizontalAlignment(SwingConstants.CENTER);
		lblSicuroDiVoler.setBounds(10, 11, 369, 29);
		contentPane.add(lblSicuroDiVoler);
		JButton buttonno = new JButton("NO");
		buttonno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.toggle();
				ConfermaEliminazione.super.dispose();
			}
		});
		buttonno.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonno.setBounds(39, 51, 135, 48);
		contentPane.add(buttonno);
		JButton buttonsi = new JButton("S\u00CC");
		buttonsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doveeliminare.equals("Cliente"))
					ClienteController.elimina(elemento);
				else if (doveeliminare.equals("Evento"))
					EventoController.elimina(elemento);
				else if (doveeliminare.equals("Luogo"))
					LuogoController.elimina(elemento); 
				window.toggle();
				ConfermaEliminazione.super.dispose();
			}
		});
		buttonsi.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonsi.setBounds(213, 51, 135, 48);
		contentPane.add(buttonsi);
	}
}
