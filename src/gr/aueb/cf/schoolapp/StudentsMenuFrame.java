package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentsMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public StudentsMenuFrame() {
		setTitle("Μενού Εκπαιδευόμενων");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentsMenuFrame.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton CloseBtn = new JButton("Κλείσιμο");
		CloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMainMenuFrame().setEnabled(true);
				Main.getStudentsMenuFrame().setVisible(false);
			}
		});
		CloseBtn.setForeground(new Color(0, 0, 255));
		CloseBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CloseBtn.setBounds(313, 202, 113, 35);
		contentPane.add(CloseBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 182, 416, 1);
		contentPane.add(separator);
		
		JButton StudentsViewBtn = new JButton("Προβολή Εκπαιδευόμενων");
		StudentsViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentsUpdateDeleteFrame().setVisible(true);
				Main.getStudentsMenuFrame().setEnabled(false);
			}
		});
		StudentsViewBtn.setForeground(new Color(0, 0, 255));
		StudentsViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StudentsViewBtn.setBounds(105, 25, 214, 46);
		contentPane.add(StudentsViewBtn);
		
		JButton InsertBtn = new JButton("Εισαγωγή Εκπαιδευόμενου");
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentInsertFrame().setVisible(true);
				Main.getStudentsMenuFrame().setEnabled(false);
			}
		});
		InsertBtn.setForeground(Color.BLUE);
		InsertBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		InsertBtn.setBounds(105, 106, 214, 46);
		contentPane.add(InsertBtn);
	}
}
