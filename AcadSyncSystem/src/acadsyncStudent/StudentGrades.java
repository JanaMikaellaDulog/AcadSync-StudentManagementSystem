package acadsyncStudent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import acadsyncFile.AcadSyncFontManager;
import acadsyncFile.DatabaseCredentials;

public class StudentGrades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JPanel panel;
	
	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGrades frame = new StudentGrades();
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
	public StudentGrades() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Subject Grades");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 710);
		setLocationRelativeTo(null);  // Center the frame on the screen
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
    
			
			JPanel toppanel = new JPanel();
			toppanel.setBackground(new Color(0, 0, 0));
			toppanel.setBounds(0, 0, 1236, 92);
			contentPane.add(toppanel);
			toppanel.setLayout(null);
			
			
				JButton menubutton = new JButton("");
				menubutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StudentGrades1 StudentGradesNavOpen = new StudentGrades1();
						StudentGradesNavOpen.setVisible(true);
						
						dispose();
					}
				});
				
				   // Add MouseListener for hover effect
		        menubutton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 menubutton.setBackground(new Color(27, 27, 27));  // Hover background color
		                 }
		                 
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 menubutton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });
		        
		        
		        JButton logoutbutton = new JButton("");
				logoutbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 StudentPortalLogIn StudentLogIn = new StudentPortalLogIn();
						 StudentLogIn.setVisible(true);
				        	 
				         dispose(); // Close the current window frame
					}
				});
				
				// Add MouseListener for hover effect
		        logoutbutton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 logoutbutton.setBackground(new Color(27, 27, 27));  // Hover background color
		                 }
		                 
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 logoutbutton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });


				
		        menubutton.setIcon(new ImageIcon("src/list (1).png"));
   				menubutton.setFocusPainted(false);
   				menubutton.setBorderPainted(false);
   				menubutton.setBackground(new Color(0, 0, 0));
   				menubutton.setBorder(null);
   				menubutton.setBounds(0, 0, 89, 90);
   				toppanel.add(menubutton);
 
   				
   				logoutbutton.setBackground(new Color(0, 0, 0));
   				logoutbutton.setFocusPainted(false);
   				logoutbutton.setBorderPainted(false);
   				logoutbutton.setOpaque(true);
   				logoutbutton.setIcon(new ImageIcon("src/turn-off.png"));
   				logoutbutton.setBounds(1167, 0, 70, 90);
   				toppanel.add(logoutbutton);
		   				
				
				
				JLabel logoIcon = new JLabel("New label");
				logoIcon.setHorizontalAlignment(SwingConstants.CENTER);
				logoIcon.setIcon(new ImageIcon("src/SMS Logo Small.jpg"));
				logoIcon.setBounds(99, 0, 77, 88);
				toppanel.add(logoIcon);
				
				JLabel TopStudentLabel = new JLabel("STUDENT");
				TopStudentLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
				TopStudentLabel.setForeground(new Color(255, 255, 255));
				TopStudentLabel.setBounds(175, 10, 238, 38);
				toppanel.add(TopStudentLabel);
				
				JLabel TopPortalLabel = new JLabel("PORTAL");
				TopPortalLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
				TopPortalLabel.setForeground(Color.WHITE);
				TopPortalLabel.setBounds(175, 36, 238, 26);
				toppanel.add(TopPortalLabel);
				
				JLabel AcadsyncLabel = new JLabel("AcadSync²");
				AcadsyncLabel.setForeground(Color.WHITE);
				AcadsyncLabel.setFont(AcadSyncFontManager.getFont("Poppins-Light"));
				AcadsyncLabel.setBounds(175, 54, 238, 23);
				toppanel.add(AcadsyncLabel);
				

	  
				//Line color white
		        ExtraLabel.setForeground(new Color(255, 255, 255));
		        ExtraLabel.setBounds(1162, -3, 15, 15);
		       
		        toppanel.add(ExtraLabel);
		                
	            JLabel ExtraLabel_1 = new JLabel("│");
	            ExtraLabel_1.setForeground(Color.WHITE);
	            ExtraLabel_1.setBounds(1162, 8, 15, 15);
	            toppanel.add(ExtraLabel_1);
	            
	            JLabel ExtraLabel_2 = new JLabel("│");
	            ExtraLabel_2.setForeground(Color.WHITE);
	            ExtraLabel_2.setBounds(1162, 19, 15, 15);
	            toppanel.add(ExtraLabel_2);
	            
	            JLabel ExtraLabel_3 = new JLabel("│");
	            ExtraLabel_3.setForeground(Color.WHITE);
	            ExtraLabel_3.setBounds(1162, 30, 15, 15);
	            toppanel.add(ExtraLabel_3);
	            
	            JLabel ExtraLabel_4 = new JLabel("│");
	            ExtraLabel_4.setForeground(Color.WHITE);
	            ExtraLabel_4.setBounds(1162, 41, 15, 15);
	            toppanel.add(ExtraLabel_4);
	            
	            JLabel ExtraLabel_5 = new JLabel("│");
	            ExtraLabel_5.setForeground(Color.WHITE);
	            ExtraLabel_5.setBounds(1162, 52, 15, 15);
	            toppanel.add(ExtraLabel_5);
	            
	            JLabel ExtraLabel_6 = new JLabel("│");
	            ExtraLabel_6.setForeground(Color.WHITE);
	            ExtraLabel_6.setBounds(1162, 63, 15, 15);
	            toppanel.add(ExtraLabel_6);
	            
	            JLabel ExtraLabel_7 = new JLabel("│");
	            ExtraLabel_7.setForeground(Color.WHITE);
	            ExtraLabel_7.setBounds(1162, 74, 15, 15);
	            toppanel.add(ExtraLabel_7);
	            
	            JLabel ExtraLabel_8 = new JLabel("│");
	            ExtraLabel_8.setForeground(Color.WHITE);
	            ExtraLabel_8.setBounds(1162, 85, 15, 15);
	            toppanel.add(ExtraLabel_8);
	            

			
			CustomRoundPanel Mainpanel = new CustomRoundPanel(20);
			Mainpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            Mainpanel.setBounds(10, 98, 1218, 565);
			contentPane.add(Mainpanel);
			Mainpanel.setLayout(null);
			
			
			
			//Top 
			JPanel GreetingPanel = new JPanel();
			GreetingPanel.setLayout(null);
			GreetingPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
			GreetingPanel.setBackground(Color.WHITE);
			GreetingPanel.setBounds(10, 11, 1198, 60);
			Mainpanel.add(GreetingPanel);
			
			JLabel IconProfile = new JLabel("Student Profile");
			IconProfile.setIcon(new ImageIcon("src/best.png"));
			IconProfile.setBounds(43, 15, 24, 24);
			GreetingPanel.add(IconProfile);
			
			JLabel LabelGrades = new JLabel("Subject Grades");
			LabelGrades.setBounds(79, 15, 296, 28);
			LabelGrades.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
			GreetingPanel.add(LabelGrades);
			
			
			panel = new JPanel();
	        panel.setBounds(20, 82, 914, 438);
	        Mainpanel.add(panel);
	        panel.setLayout(null);
	        panel.setOpaque(false);

	        int yOffset = 35; // Initial Y offset for components
	        int gap = 72; // Gap between each set of components
	        
	        
	        //fetch Grades
	        List<Data> dataList = null;

			try {
				dataList = fetchAcademicRecords();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			
	        for (Data data : dataList) {
	            JLabel acadyrlabel = new JLabel(data.getAcademicYear());
	            acadyrlabel.setBounds(178, yOffset + 17, 94, 14);
	            acadyrlabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
	            panel.add(acadyrlabel);

	            JLabel semesterlabel = new JLabel(data.getSemester());
	            semesterlabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
	            semesterlabel.setBounds(370, yOffset + 17, 176, 14);
	            panel.add(semesterlabel);

	            CustomButtonGrades gradesButton = new CustomButtonGrades("", 12);
	            gradesButton.setText("Academic Year:                                 |   Semester:");
	            gradesButton.setHorizontalAlignment(SwingConstants.LEFT);
	            gradesButton.setSize(858, 49);
	            gradesButton.setLocation(25, yOffset);
	            gradesButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
	            gradesButton.setForeground(Color.BLACK);
	            gradesButton.setLayout(null);
	            panel.add(gradesButton);

	            gradesButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	
	                	//Get AcademicYear and Semester
	                	GradesButtonSession.setAcademicYear(data.getAcademicYear());
	                	GradesButtonSession.setSemester(data.getSemester());
	                	
	                    StudentGradesWindow1 OpenGradesWindow = new StudentGradesWindow1();
	                    
	                    OpenGradesWindow.setVisible(true);
	                    
	                    
	                }
	            });

	            yOffset += gap; // Increase Y offset for the next set
	        }
	    }

///METHOD TO FETCH GRADES
    
    private List<Data> fetchAcademicRecords() throws ClassNotFoundException {
    int studentNumber = Session.getStudentNumber();
    List<Data> dataList = new ArrayList<>();
    String query = "SELECT DISTINCT AcademicYear, Semester FROM enrollment_status WHERE StudentNumber = ?";
    
    
    try {
        // Register the JDBC driver (for MySQL, it could be different for other databases)
        Class.forName("com.mysql.cj.jdbc.Driver"); // Make sure to import the MySQL JDBC driver

        // Establish the connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
        	

            // Set the student number parameter in the query
            statement.setInt(1, studentNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    String academicYear = resultSet.getString("AcademicYear");
                    String semester = resultSet.getString("Semester");
                    String buttonText = "Academic Year: " + academicYear + " | Semester: " + semester;

                    dataList.add(new Data(academicYear, semester, buttonText));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch academic records.", "Fetch Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "JDBC Driver not found.", "Driver Error", JOptionPane.ERROR_MESSAGE);
    }

    return dataList;
}


	// Data class to hold academic year, semester, and button text
	class Data {
	    private String academicYear;
	    private String semester;
	    private String buttonText;

	    public Data(String academicYear, String semester, String buttonText) {
	        this.academicYear = academicYear;
	        this.semester = semester;
	        this.buttonText = buttonText;
	    }

	    public String getAcademicYear() {
	        return academicYear;
	    }

	    public String getSemester() {
	        return semester;
	    }

	    public String getButtonText() {
	        return buttonText;
	    }
	}
}
