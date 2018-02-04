package default1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticheEvento extends JFrame {

	private JPanel contentPane;

	public StatisticheEvento(ChartPanel chart, ChartPanel chart2, double soldispesi, int biglietticomprati) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 958, 527);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel statPanel1 = new JPanel();
		statPanel1.setBorder(null);
		statPanel1.setBounds(10, 11, 680, 430);
		contentPane.add(statPanel1);
		statPanel1.add(chart);
		
		JTextPane textPaneSoldiSpesi = new JTextPane();
		textPaneSoldiSpesi.setContentType("text/html");
		textPaneSoldiSpesi.setEditable(false);
		textPaneSoldiSpesi.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>€ "+ soldispesi +"</font></b></center></html>");
		textPaneSoldiSpesi.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneSoldiSpesi.setBounds(720, 132, 200, 50);
		contentPane.add(textPaneSoldiSpesi);
		textPaneSoldiSpesi.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblNewLabel = new JLabel("Totale biglietti venduti");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(720, 82, 200, 27);
		contentPane.add(lblNewLabel);

		JTextPane textPaneBigliettiAcquistati = new JTextPane();
		textPaneBigliettiAcquistati.setContentType("text/html");
		textPaneBigliettiAcquistati.setEditable(false);
		textPaneBigliettiAcquistati.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)> "+ biglietticomprati +"</font></b></center></html>");
		textPaneBigliettiAcquistati.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneBigliettiAcquistati.setBounds(720, 23, 200, 50);
		contentPane.add(textPaneBigliettiAcquistati);
		textPaneBigliettiAcquistati.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblTotaleSoldiGuadagnati = new JLabel("Totale soldi guadagnati");
		lblTotaleSoldiGuadagnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleSoldiGuadagnati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotaleSoldiGuadagnati.setBounds(720, 193, 200, 27);
		contentPane.add(lblTotaleSoldiGuadagnati);
		
		JButton btn = new JButton("Età dei clienti");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (btn.getText().equals("Età dei clienti")) {
						statPanel1.remove(chart);
						statPanel1.add(chart2);
						statPanel1.repaint();
						btn.setText("Soldi nel tempo");
					}
					else {
						statPanel1.remove(chart2);
						statPanel1.add(chart);
						statPanel1.repaint();
						btn.setText("Età dei clienti");
					}
			}
		});
		btn.setBounds(10, 452, 171, 23);
		contentPane.add(btn);
	}
}
