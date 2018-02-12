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
import java.awt.event.ActionEvent;
import control.*;
public class StatisticheEvento extends JFrame {

	private JPanel contentPane;

	public StatisticheEvento(ChartPanel chart, ChartPanel chart2, double soldispesi, int biglietticomprati) {
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
		JTextPane textPaneSoldiGuadagnati = new JTextPane();
		textPaneSoldiGuadagnati.setContentType("text/html");
		textPaneSoldiGuadagnati.setEditable(false);
		textPaneSoldiGuadagnati.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)>€ "+ soldispesi +"</font></b></center></html>");
		textPaneSoldiGuadagnati.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneSoldiGuadagnati.setBounds(982, 126, 200, 50);
		contentPane.add(textPaneSoldiGuadagnati);
		textPaneSoldiGuadagnati.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		JLabel lblNewLabel = new JLabel("Totale biglietti venduti");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(982, 78, 200, 27);
		contentPane.add(lblNewLabel);
		
		JTextPane textPaneBigliettiVenduti = new JTextPane();
		textPaneBigliettiVenduti.setContentType("text/html");
		textPaneBigliettiVenduti.setEditable(false);
		textPaneBigliettiVenduti.setText("<html><center><b><font face=\"Tahoma\" size=20 color=rgb(1,1,1)> "+ biglietticomprati +"</font></b></center></html>");
		textPaneBigliettiVenduti.setFont(new Font("Product Sans", Font.BOLD, 18));
		textPaneBigliettiVenduti.setBounds(982, 17, 200, 50);
		contentPane.add(textPaneBigliettiVenduti);
		textPaneBigliettiVenduti.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblTotaleSoldiGuadagnati = new JLabel("Guadagno totale");
		lblTotaleSoldiGuadagnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleSoldiGuadagnati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotaleSoldiGuadagnati.setBounds(982, 187, 200, 27);
		contentPane.add(lblTotaleSoldiGuadagnati);
		
		JButton btn = new JButton("Età degli acquirenti");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (btn.getText().equals("Età degli acquirenti")) {
						statPanel1.remove(chart);
						statPanel1.add(chart2);
						statPanel1.repaint();
						btn.setText("Andamento del guadagno");
					}
					else {
						statPanel1.remove(chart2);
						statPanel1.add(chart);
						statPanel1.repaint();
						btn.setText("Età degli acquirenti");
					}
			}
		});
		btn.setBounds(10, 454, 171, 23);
		contentPane.add(btn);
	}
}
