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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StatisticheLuogo extends JFrame {

	private JPanel contentPane;
	Date date = new Date();
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int year  = localDate.getYear();

	public StatisticheLuogo(ChartPanel chart, ChartPanel chart2, double soldispesi, int biglietticomprati, double guadagnomedio, String nome) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1317, 527);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel statPanel1 = new JPanel();
		statPanel1.setBorder(null);
		statPanel1.setBounds(24, 22, 1057, 430);
		contentPane.add(statPanel1);
		statPanel1.add(chart);
		
		JTextPane textPaneSoldiSpesi = new JTextPane();
		textPaneSoldiSpesi.setContentType("text/html");
		textPaneSoldiSpesi.setEditable(false);
		textPaneSoldiSpesi.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>€ "+ soldispesi +"</font></b></center></html>");
		textPaneSoldiSpesi.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneSoldiSpesi.setBounds(1091, 131, 200, 50);
		contentPane.add(textPaneSoldiSpesi);
		textPaneSoldiSpesi.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblNewLabel = new JLabel("Numero eventi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1091, 83, 200, 27);
		contentPane.add(lblNewLabel);

		JTextPane textPaneBigliettiAcquistati = new JTextPane();
		textPaneBigliettiAcquistati.setContentType("text/html");
		textPaneBigliettiAcquistati.setEditable(false);
		textPaneBigliettiAcquistati.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)> "+ biglietticomprati +"</font></b></center></html>");
		textPaneBigliettiAcquistati.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneBigliettiAcquistati.setBounds(1091, 22, 200, 50);
		contentPane.add(textPaneBigliettiAcquistati);
		textPaneBigliettiAcquistati.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblTotaleGuadagno = new JLabel("Totale guadagno");
		lblTotaleGuadagno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleGuadagno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotaleGuadagno.setBounds(1091, 192, 200, 27);
		contentPane.add(lblTotaleGuadagno);
		
		JButton btn = new JButton("Cliente ospitati nel tempo");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (btn.getText().equals("Cliente ospitati nel tempo")) {
						statPanel1.remove(chart);
						statPanel1.add(chart2);
						statPanel1.repaint();
						btn.setText("Eventi ospitati nel tempo");
					}
					else {
						statPanel1.remove(chart2);
						statPanel1.add(chart);
						statPanel1.repaint();
						btn.setText("Cliente ospitati nel tempo");
					}
			}
		});
		btn.setBounds(24, 454, 171, 23);
		contentPane.add(btn);
		
		JTextPane textPaneGuadagnoMedio = new JTextPane();
		textPaneGuadagnoMedio.setContentType("text/html");
		textPaneGuadagnoMedio.setEditable(false);
		textPaneGuadagnoMedio.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>€ "+ soldispesi +"</font></b></center></html>");
		textPaneGuadagnoMedio.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneGuadagnoMedio.setBounds(1091, 241, 200, 50);
		contentPane.add(textPaneGuadagnoMedio);
		textPaneGuadagnoMedio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		
		JLabel lblGuadagnoMedioPer = new JLabel("Guadagno medio per evento");
		lblGuadagnoMedioPer.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuadagnoMedioPer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuadagnoMedioPer.setBounds(1091, 304, 200, 27);
		contentPane.add(lblGuadagnoMedioPer);
		
		JComboBox cbAnno = new JComboBox();
		cbAnno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		int year2=year-5;
		for (int i=0; i<11; i++, year2++)
			cbAnno.addItem(year2);
		cbAnno.setSelectedItem(year);

		cbAnno.setBounds(1091, 430, 75, 22);
		contentPane.add(cbAnno);
		
		JButton btnNewButton = new JButton("Ricalcola");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String anno = cbAnno.getSelectedItem().toString();
				StatisticheLuogo.super.dispose();
				LuogoController.generaStatisticheLuogo(nome, anno);
			}
		});
		btnNewButton.setBounds(1176, 429, 115, 23);
		contentPane.add(btnNewButton);
	}
}
