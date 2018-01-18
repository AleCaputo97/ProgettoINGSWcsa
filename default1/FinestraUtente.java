package com.amazonaws.samples;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class FinestraUtente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraUtente frame = new FinestraUtente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinestraUtente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(977, 187, 133, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(834, 187, 133, 30);
		contentPane.add(btnNewButton_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBounds(10, 228, 1100, 237);
		contentPane.add(table);
		
		JButton button = new JButton("New button");
		button.setBounds(10, 191, 133, 30);
		contentPane.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(153, 191, 133, 30);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(296, 191, 133, 30);
		contentPane.add(button_2);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 82, 133, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 113, 133, 20);
		contentPane.add(textField_2);
	}
}
