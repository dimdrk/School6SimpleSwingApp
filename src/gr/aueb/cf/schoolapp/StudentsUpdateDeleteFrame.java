package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gr.aueb.cf.schoolapp.util.DBUtil;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StudentsUpdateDeleteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable studentsTable;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField idText;
	private JLabel idLabel;
	private JTextField firstnameText;
	private JTextField lastnameText;
	private JTextField lastnameSearchText;
	private JLabel firstnameLabel;
	private JLabel errorFirstname;
	private JLabel errorLastname;

	public StudentsUpdateDeleteFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentsUpdateDeleteFrame.class.getResource("/resources/eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευόμενου");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				buildTable();	// initial rendering
				idText.setText("");
				firstnameText.setText("");
				lastnameText.setText("");
			}
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();	// refresh after update / delete
				idText.setText("");
				firstnameText.setText("");
				lastnameText.setText("");
			}
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 878, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		studentsTable = new JTable();
		studentsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 0));
				firstnameText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 1));
				lastnameText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 2));
			}
		});
		studentsTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Κωδικός", "Όνομα", "Επώνυμο"}
		));
		
		model = (DefaultTableModel) studentsTable.getModel();
		
		studentsTable.setBounds(33, 97, 313, 320);
		contentPane.add(studentsTable);
		
		JScrollPane scrollPane = new JScrollPane(studentsTable);
		scrollPane.setBounds(33, 66, 371, 392);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(500, 59, 330, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		idLabel = new JLabel("Κωδικός");
		idLabel.setForeground(new Color(0, 0, 128));
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idLabel.setBounds(45, 33, 45, 13);
		panel.add(idLabel);
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setColumns(10);
		idText.setBounds(102, 30, 64, 19);
		panel.add(idText);
		
		firstnameText = new JTextField();
		firstnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;
				inputFirstname = firstnameText.getText().trim();
				validateFirstname(inputFirstname);				
			}
		});
		firstnameText.setColumns(10);
		firstnameText.setBounds(102, 56, 170, 19);
		panel.add(firstnameText);
		
		firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(new Color(0, 0, 128));
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(55, 59, 36, 13);
		panel.add(firstnameLabel);
		
		JLabel lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setForeground(new Color(0, 0, 128));
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setBounds(36, 120, 55, 13);
		panel.add(lastnameLabel);
		
		lastnameText = new JTextField();
		lastnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameText.getText().trim();
				validateLastname(inputLastname);
			}
		});
		lastnameText.setColumns(10);
		lastnameText.setBounds(102, 117, 170, 19);
		panel.add(lastnameText);
		
		errorLastname = new JLabel("");
		errorLastname.setForeground(Color.RED);
		errorLastname.setBounds(102, 146, 201, 19);
		panel.add(errorLastname);
		
		errorFirstname = new JLabel("");
		errorFirstname.setForeground(Color.RED);
		errorFirstname.setBounds(102, 85, 170, 19);
		panel.add(errorFirstname);
		
		JButton updateBtn = new JButton("Ενημέρωση");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				// Data Binding
				int inputId = Integer.parseInt(idText.getText().trim());
				String inputFirstname = firstnameText.getText().trim();
				String inputLastname = lastnameText.getText().trim();
				
				// Validation
				validateFirstname(inputFirstname);
				validateLastname(inputLastname);
				
				if (inputFirstname.isEmpty() || inputLastname.isEmpty()) {
					return;
				}
				
				String sql = "UPDATE students SET firstname = ?, lastname = ? WHERE id = ?";
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql);) {
					
					ps.setString(1, inputFirstname);
					ps.setString(2, inputLastname);
					ps.setInt(3, inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Ενημέρωση", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected + " γραμμές ενημερώθηκαν", "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (SQLException e1) {
					
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		updateBtn.setForeground(new Color(0, 0, 128));
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateBtn.setBounds(535, 298, 134, 56);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Διαγραφή");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "DELETE FROM students WHERE id = ?";
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql);) {
					
					int inputId = Integer.parseInt(idText.getText().trim());					
					ps.setInt(1, inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Διαγραγή", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected + " γραμμές διαγράφηκαν", "Διαγραγή", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
					
				} catch (SQLException ex) {
					// ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteBtn.setForeground(new Color(0, 0, 128));
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deleteBtn.setBounds(679, 298, 134, 56);
		contentPane.add(deleteBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentsMenuFrame().setEnabled(true);
				Main.getStudentsUpdateDeleteFrame().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 128));
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		closeBtn.setBounds(696, 408, 134, 56);
		contentPane.add(closeBtn);
		
		JLabel lastnameSearchLabel = new JLabel("Επώνυμο");
		lastnameSearchLabel.setForeground(new Color(189, 0, 0));
		lastnameSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameSearchLabel.setBounds(49, 22, 56, 25);
		contentPane.add(lastnameSearchLabel);
		
		lastnameSearchText = new JTextField();
		lastnameSearchText.setColumns(10);
		lastnameSearchText.setBounds(115, 22, 199, 25);
		contentPane.add(lastnameSearchText);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setForeground(new Color(0, 0, 128));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(335, 24, 100, 21);
		contentPane.add(btnSearch);		
	}
	
private void buildTable() {
		
		Vector<String> vector;
		
		String sql = "SELECT id, firstname, lastname FROM students WHERE lastname LIKE ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {


			ps.setString(1,  lastnameSearchText.getText().trim() + "%");
			
			ResultSet rs = ps.executeQuery();
			
			// Clear model -> clear table - MVVM
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			while (rs.next()) {
				vector = new Vector<>(3);
				vector.add(rs.getString("id"));
				vector.add(rs.getString("firstname"));
				vector.add(rs.getString("lastname"));
				
				model.addRow(vector);
			}			
		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void validateFirstname(String inputFirstname) {
		if (inputFirstname.equals("")) {
			errorFirstname.setText("Το όνομα είναι υποχρεώτικό");
		};
		
		if (!inputFirstname.equals("")) {
			errorFirstname.setText("");
		};
	}
	
	private void validateLastname(String inputLastname) {
		if (inputLastname.equals("")) {
			errorLastname.setText("Το επώνυμο είναι υποχρεώτικό");
		};
		
		if (!inputLastname.equals("")) {
			errorLastname.setText("");
		};
	}
}
