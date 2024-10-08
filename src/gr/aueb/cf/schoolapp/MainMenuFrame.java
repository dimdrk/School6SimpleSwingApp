package gr.aueb.cf.schoolapp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection connection;


	public MainMenuFrame() {
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				
//				String sql = "jdbc:mysql://localhost:3306/school6db?serverTimeZone=UTC";
//				String username = "userdb6";
//				String password = "12345";		// Password should not appear (not be visible) in code-base.
//				
//				try {
//					connection = DriverManager.getConnection(sql, username, password);
//					System.out.println("Connection Success");
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuFrame.class.getResource("/resources/eduv2.png")));
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 349);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setBounds(10, 87, 426, 1);
		contentPane.add(headerSeparator);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 0, 204));
		header.setBounds(10, 10, 427, 67);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel codingFactoryLabel = new JLabel("Coding Factory");
		codingFactoryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		codingFactoryLabel.setForeground(new Color(255, 255, 255));
		codingFactoryLabel.setBounds(10, 22, 145, 35);
		header.add(codingFactoryLabel);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setVisible(true);
				Main.getMainMenuFrame().setEnabled(false);
			}
		});
		teachersBtn.setBackground(new Color(214, 214, 214));
		teachersBtn.setBounds(10, 128, 40, 40);
		contentPane.add(teachersBtn);
		
		JLabel teachersLabel = new JLabel("Εκπαιδευτές");
		teachersLabel.setForeground(new Color(0, 0, 179));
		teachersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		teachersLabel.setBounds(60, 128, 106, 40);
		contentPane.add(teachersLabel);
		
		JLabel studentsLabel = new JLabel("Εκπαιδευόμενοι");
		studentsLabel.setForeground(new Color(0, 0, 179));
		studentsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studentsLabel.setBounds(60, 196, 106, 40);
		contentPane.add(studentsLabel);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentsMenuFrame().setVisible(true);
				Main.getMainMenuFrame().setEnabled(false);
			}
		});
		studentsBtn.setBackground(new Color(214, 214, 214));
		studentsBtn.setBounds(10, 196, 40, 40);
		contentPane.add(studentsBtn);
		
		JPanel footer = new JPanel();
		footer.setBackground(new Color(214, 214, 214));
		footer.setBounds(10, 262, 426, 50);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel manual = new JLabel("Εγχειρίδιο χρήσης");
		manual.setForeground(new Color(0, 0, 204));
		manual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		manual.setBounds(10, 10, 130, 30);
		footer.add(manual);
	}


//	public static Connection getConnection() {
//		return connection;
//	}
	
	
}
