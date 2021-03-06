package boundary;

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
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import control.*;
public class StatisticheLuogo extends JFrame {

	private JPanel contentPane;
	Date date = new Date();
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int year  = localDate.getYear();

	public StatisticheLuogo(ChartPanel chart, ChartPanel chart2, double soldispesi, int biglietticomprati, double guadagnomedio, String nome) {	
		try {
			URL url = new URL("https://raw.githubusercontent.com/AleCaputo97/ProgettoINGSWcsa/master/iconstat.png");
			Image icon = ImageIO.read(url);  
			Image newimg = icon.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
		    this.setIconImage(newimg);
			}
		catch(Exception e){ 
		    	System.out.println(e);
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1209, 527);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel statPanel1 = new JPanel();
		statPanel1.setBorder(null);
		statPanel1.setBounds(10, 11, 955, 441);
		contentPane.add(statPanel1);
		statPanel1.add(chart);
		
		JTextPane textPaneSoldiSpesi = new JTextPane();
		textPaneSoldiSpesi.setContentType("text/html");
		textPaneSoldiSpesi.setEditable(false);
		textPaneSoldiSpesi.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>� "+ soldispesi +"</font></b></center></html>");
		textPaneSoldiSpesi.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneSoldiSpesi.setBounds(982, 126, 200, 50);
		contentPane.add(textPaneSoldiSpesi);
		textPaneSoldiSpesi.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblNewLabel = new JLabel("Numero eventi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(982, 78, 200, 27);
		contentPane.add(lblNewLabel);

		JTextPane textPaneBigliettiAcquistati = new JTextPane();
		textPaneBigliettiAcquistati.setContentType("text/html");
		textPaneBigliettiAcquistati.setEditable(false);
		textPaneBigliettiAcquistati.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)> "+ biglietticomprati +"</font></b></center></html>");
		textPaneBigliettiAcquistati.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneBigliettiAcquistati.setBounds(982, 17, 200, 50);
		contentPane.add(textPaneBigliettiAcquistati);
		textPaneBigliettiAcquistati.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblTotaleGuadagno = new JLabel("Guadagno totale");
		lblTotaleGuadagno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleGuadagno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotaleGuadagno.setBounds(982, 187, 200, 27);
		contentPane.add(lblTotaleGuadagno);
		
		JButton btn = new JButton("Biglietti venduti");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (btn.getText().equals("Biglietti venduti")) {
						statPanel1.remove(chart);
						statPanel1.add(chart2);
						statPanel1.repaint();
						btn.setText("Eventi ospitati");
					}
					else {
						statPanel1.remove(chart2);
						statPanel1.add(chart);
						statPanel1.repaint();
						btn.setText("Biglietti venduti");
					}
			}
		});
		btn.setBounds(10, 454, 171, 23);
		contentPane.add(btn);
		
		JTextPane textPaneGuadagnoMedio = new JTextPane();
		textPaneGuadagnoMedio.setContentType("text/html");
		textPaneGuadagnoMedio.setEditable(false);
		textPaneGuadagnoMedio.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>� "+ guadagnomedio +"</font></b></center></html>");
		textPaneGuadagnoMedio.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneGuadagnoMedio.setBounds(982, 236, 200, 50);
		contentPane.add(textPaneGuadagnoMedio);
		textPaneGuadagnoMedio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		
		JLabel lblGuadagnoMedioPer = new JLabel("Guadagno medio per evento");
		lblGuadagnoMedioPer.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuadagnoMedioPer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuadagnoMedioPer.setBounds(982, 299, 200, 27);
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

		cbAnno.setBounds(765, 455, 75, 22);
		contentPane.add(cbAnno);
		
		JButton btnNewButton = new JButton("Ricalcola");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String anno = cbAnno.getSelectedItem().toString();
				LuogoController.generaStatisticheLuogo(nome, anno);
				StatisticheLuogo.super.dispose();
			}
		});
		btnNewButton.setBounds(850, 454, 115, 23);
		contentPane.add(btnNewButton);
	}
}
