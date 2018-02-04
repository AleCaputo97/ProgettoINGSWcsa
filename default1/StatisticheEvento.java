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
		
		JTextPane textPaneSoldiGuadagnati = new JTextPane();
		textPaneSoldiGuadagnati.setContentType("text/html");
		textPaneSoldiGuadagnati.setEditable(false);
		textPaneSoldiGuadagnati.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>� "+ soldispesi +"</font></b></center></html>");
		textPaneSoldiGuadagnati.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneSoldiGuadagnati.setBounds(720, 132, 200, 50);
		contentPane.add(textPaneSoldiGuadagnati);
		textPaneSoldiGuadagnati.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblNewLabel = new JLabel("Totale biglietti venduti");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(720, 82, 200, 27);
		contentPane.add(lblNewLabel);

		JTextPane textPaneBigliettiVenduti = new JTextPane();
		textPaneBigliettiVenduti.setContentType("text/html");
		textPaneBigliettiVenduti.setEditable(false);
		textPaneBigliettiVenduti.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)> "+ biglietticomprati +"</font></b></center></html>");
		textPaneBigliettiVenduti.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneBigliettiVenduti.setBounds(720, 23, 200, 50);
		contentPane.add(textPaneBigliettiVenduti);
		textPaneBigliettiVenduti.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblTotaleSoldiGuadagnati = new JLabel("Totale soldi guadagnati");
		lblTotaleSoldiGuadagnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleSoldiGuadagnati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotaleSoldiGuadagnati.setBounds(720, 193, 200, 27);
		contentPane.add(lblTotaleSoldiGuadagnati);
		
		JButton btn = new JButton("Et� dei clienti");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (btn.getText().equals("Et� dei clienti")) {
						statPanel1.remove(chart);
						statPanel1.add(chart2);
						statPanel1.repaint();
						btn.setText("Soldi nel tempo");
					}
					else {
						statPanel1.remove(chart2);
						statPanel1.add(chart);
						statPanel1.repaint();
						btn.setText("Et� dei clienti");
					}
			}
		});
		btn.setBounds(10, 452, 171, 23);
		contentPane.add(btn);
	}
}
