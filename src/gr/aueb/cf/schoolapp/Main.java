package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

public class Main {
	
	private final static MainMenuFrame mainMenuFrame = new MainMenuFrame();
	private final static TeachersMenuFrame teachersMenuFrame = new TeachersMenuFrame();
	private final static TeacherInsertFrame teachersInsertFrame = new TeacherInsertFrame();
	private final static TeachersUpdateDeleteFrame teachersUpdateDeleteFrame = new TeachersUpdateDeleteFrame();
	private final static StudentsMenuFrame studentsMenuFrame = new StudentsMenuFrame();
	private final static StudentInsertFrame studentInsertFrame = new StudentInsertFrame();
	private final static StudentsUpdateDeleteFrame studentsUpdateDeleteFrame = new StudentsUpdateDeleteFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainMenuFrame.setLocationRelativeTo(null);
					mainMenuFrame.setVisible(true);
					
					teachersMenuFrame.setLocationRelativeTo(null);
					teachersMenuFrame.setVisible(false);
					
					teachersInsertFrame.setLocationRelativeTo(null);
					teachersInsertFrame.setVisible(false);
					
					teachersUpdateDeleteFrame.setLocationRelativeTo(null);
					teachersUpdateDeleteFrame.setVisible(false);
					
					studentsMenuFrame.setLocationRelativeTo(null);
					studentsMenuFrame.setVisible(false);
					
					studentInsertFrame.setLocationRelativeTo(null);
					studentInsertFrame.setVisible(false);
					
					studentsUpdateDeleteFrame.setLocationRelativeTo(null);
					studentsUpdateDeleteFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainMenuFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	public static TeachersMenuFrame getTeachersMenuFrame() {
		return teachersMenuFrame;
	}

	public static TeacherInsertFrame getTeachersInsertFrame() {
		return teachersInsertFrame;
	}

	public static TeachersUpdateDeleteFrame getTeachersUpdateDeleteFrame() {
		return teachersUpdateDeleteFrame;
	}
	
	public static StudentsMenuFrame getStudentsMenuFrame() {
		return studentsMenuFrame;
	}

	public static StudentInsertFrame getStudentInsertFrame() {
		return studentInsertFrame;
	}
	
	public static StudentsUpdateDeleteFrame getStudentsUpdateDeleteFrame() {
		return studentsUpdateDeleteFrame;
	}
}
