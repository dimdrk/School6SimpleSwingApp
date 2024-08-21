package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.util.DBUtil;

import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StudentInsertFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnameText;
	private JTextField lastnameText;
	private JLabel errorFirstname;
	private JLabel errorLastname;

	
	public StudentInsertFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameText.setText("");
				lastnameText.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentInsertFrame.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Εκπαιδευόμενου");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 456, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(44, 43, 376, 168);
		contentPane.add(panel);
		panel.setLayout(null);
		
		errorFirstname = new JLabel("");
		errorFirstname.setForeground(Color.RED);
		errorFirstname.setBounds(102, 60, 243, 26);
		panel.add(errorFirstname);
		
		errorLastname = new JLabel("");
		errorLastname.setForeground(Color.RED);
		errorLastname.setBounds(102, 132, 243, 26);
		panel.add(errorLastname);
		
		JLabel firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(Color.BLUE);
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(50, 23, 41, 26);
		panel.add(firstnameLabel);
		
		firstnameText = new JTextField();
		firstnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;
				inputFirstname = firstnameText.getText().trim();
				
				if (inputFirstname.equals("")) {
					errorFirstname.setText("Το όνομα είναι υποχρεώτικό");
				};
				
				if (!inputFirstname.equals("")) {
					errorFirstname.setText("");
				};
			}
		});
		
		firstnameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameText.setColumns(10);
		firstnameText.setBounds(102, 24, 243, 26);
		panel.add(firstnameText);
		
		lastnameText = new JTextField();
		lastnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameText.getText().trim();
				
				if (inputLastname.equals("")) {
					errorLastname.setText("Το επώνυμο είναι υποχρεώτικό");
				};
				
				if (!inputLastname.equals("")) {
					errorLastname.setText("");
				};				
			}
		});
		
		lastnameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameText.setColumns(10);
		lastnameText.setBounds(102, 96, 243, 26);
		panel.add(lastnameText);
		
		JLabel lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setForeground(Color.BLUE);
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setBounds(39, 95, 52, 26);
		panel.add(lastnameLabel);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Data binding
				String inputFirstname = firstnameText.getText().trim();
				String inputLastname = lastnameText.getText().trim();
				
				
				
				if (inputFirstname.equals("")) {
					errorFirstname.setText("Το όνομα είναι υποχρεώτικό");
				};
				
				if (!inputFirstname.equals("")) {
					errorFirstname.setText("");
				};
				
				if (inputLastname.equals("")) {
					errorLastname.setText("Το επώνυμο είναι υποχρεώτικό");
				};
				
				if (!inputLastname.equals("")) {
					errorLastname.setText("");
				};
				
				if (inputFirstname.equals("") || inputLastname.equals("")) {
					return;
				}
				
				String sql = "INSERT INTO students (firstname, lastname) VALUES (?, ?)";
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql);) {
					
					ps.setString(1, inputFirstname);
					ps.setString(2, inputLastname);
					
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, n + " records inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
					
				} catch (SQLException e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		insertBtn.setBounds(216, 236, 97, 32);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentsMenuFrame().setEnabled(true);
				Main.getStudentInsertFrame().setVisible(false);
				
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		closeBtn.setBounds(323, 236, 97, 32);
		contentPane.add(closeBtn);
	}
}
