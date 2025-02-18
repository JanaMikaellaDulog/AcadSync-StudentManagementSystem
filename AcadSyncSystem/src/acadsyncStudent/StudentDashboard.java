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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import acadsyncFile.DatabaseCredentials;

public class StudentDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JTextArea Textgreeting;
	private JTextField Logintext;
	private JTextField TextField1;
	private JTextField TextField2;
	private JTextField TextField3;
	private JTextField TextField4;
	
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
					StudentDashboard frame = new StudentDashboard();
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
	public StudentDashboard() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Student Dashboard");
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
						StudentDashboard1 StudentDashboardNavOpen = new StudentDashboard1();
						StudentDashboardNavOpen.setVisible(true);
			        	 
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
				TopStudentLabel.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
				TopStudentLabel.setForeground(new Color(255, 255, 255));
				TopStudentLabel.setBounds(175, 10, 238, 38);
				toppanel.add(TopStudentLabel);
				
				JLabel TopPortalLabel = new JLabel("PORTAL");
				TopPortalLabel.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
				TopPortalLabel.setForeground(Color.WHITE);
				TopPortalLabel.setBounds(175, 36, 238, 26);
				toppanel.add(TopPortalLabel);
				
				JLabel AcadsyncLabel = new JLabel("AcadSync²");
				AcadsyncLabel.setForeground(Color.WHITE);
				AcadsyncLabel.setFont(AcadSyncFontManager1.getFont("Poppins-Light"));
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
			
			
			//Small Panels
				CustomSmallRoundPanel Smallpanel1 = new CustomSmallRoundPanel(20);
				Smallpanel1.setBounds(46, 139, 245, 160);
				Smallpanel1.setBackground(new Color(42, 157, 143));
				Mainpanel.add(Smallpanel1);
				
				CustomSmallRoundPanel Smallpanel2 = new CustomSmallRoundPanel(20);
                Smallpanel2.setBackground(new Color(127, 89, 176));
                Smallpanel2.setBounds(338, 139, 245, 160);
                Mainpanel.add(Smallpanel2);                        
                
                CustomSmallRoundPanel Smallpanel3 = new CustomSmallRoundPanel(20);
                Smallpanel3.setBackground(new Color(252, 180, 36));
                Smallpanel3.setBounds(631, 139, 245, 160);
                Mainpanel.add(Smallpanel3);	                

                CustomSmallRoundPanel Smallpanel4 = new CustomSmallRoundPanel(20);
                Smallpanel4.setBackground(new Color(99, 197, 234));
                Smallpanel4.setBounds(926, 139, 245, 160);
                Mainpanel.add(Smallpanel4);
	            
               
			    //Labels                       
                JLabel AcadYrLabel = new JLabel("Current Academic Year");
                AcadYrLabel.setLabelFor(AcadYrLabel);
                AcadYrLabel.setHorizontalAlignment(SwingConstants.CENTER);
                AcadYrLabel.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                Smallpanel1.setLayout(null);
                AcadYrLabel.setForeground(new Color(255, 255, 255));
                AcadYrLabel.setBounds(30, 70, 180, 14);
                Smallpanel1.add(AcadYrLabel);
                
                	                
                JLabel SemesterLabel = new JLabel("Current Semester");
                SemesterLabel.setHorizontalAlignment(SwingConstants.CENTER);
                SemesterLabel.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                Smallpanel2.setLayout(null);
                SemesterLabel.setForeground(Color.WHITE);
                SemesterLabel.setBounds(30, 70, 180, 14);
                Smallpanel2.add(SemesterLabel);
               
                
                JLabel StatusLabel = new JLabel("State");
                StatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
                StatusLabel.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                Smallpanel3.setLayout(null);
                StatusLabel.setForeground(Color.WHITE);
                StatusLabel.setBounds(30, 70, 180, 14);
                Smallpanel3.add(StatusLabel);

                
                JLabel CourseLabel = new JLabel("Course & Year Level");
                CourseLabel.setHorizontalAlignment(SwingConstants.CENTER);
                CourseLabel.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                Smallpanel4.setLayout(null);
                CourseLabel.setForeground(Color.WHITE);
                CourseLabel.setBounds(30, 70, 180, 14);
                Smallpanel4.add(CourseLabel);
              
                               
                //Icons
                JLabel AcadIcon = new JLabel("");
                AcadIcon.setIcon(new ImageIcon("src/calendar.png"));
                AcadIcon.setBounds(105, 95, 32, 32);
                Smallpanel1.add(AcadIcon);
                
         	                
                JLabel SemesterIcon = new JLabel("");
                SemesterIcon.setIcon(new ImageIcon("src/level-up.png"));
                SemesterIcon.setBounds(105, 95, 32, 32);
                Smallpanel2.add(SemesterIcon);
             
                              
                JLabel StatusIcon = new JLabel("");
                StatusIcon.setIcon(new ImageIcon("src/status.png"));
                StatusIcon.setBounds(105, 95, 32, 32);
                Smallpanel3.add(StatusIcon);
                
        
                JLabel CourseIcon = new JLabel("");
                CourseIcon.setIcon(new ImageIcon("src/graduate (1).png"));
                CourseIcon.setBounds(105, 95, 32, 32);
                Smallpanel4.add(CourseIcon);
                
               
                
                
                //Text fields
                String text1Value = "...";
                TextField1 = new JTextField();
                TextField1.setForeground(new Color(255, 255, 255));
                TextField1.setHorizontalAlignment(SwingConstants.CENTER);
                TextField1.setText(text1Value);
                TextField1.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
                TextField1.setEditable(false);
                TextField1.setOpaque(false);
                TextField1.setColumns(10);
                TextField1.setBorder(null);
                TextField1.setBackground(Color.WHITE);
                TextField1.setBounds(30, 27, 180, 32);
                Smallpanel1.add(TextField1);
                //command to insert data on text field
	           
                String text2Value = "...";
                TextField2 = new JTextField();
                TextField2.setText(text2Value);
                TextField2.setOpaque(false);
                TextField2.setHorizontalAlignment(SwingConstants.CENTER);
                TextField2.setForeground(Color.WHITE);
                TextField2.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
                TextField2.setEditable(false);
                TextField2.setColumns(10);
                TextField2.setBorder(null);
                TextField2.setBackground(Color.WHITE);
                TextField2.setBounds(30, 27, 180, 32);
                Smallpanel2.add(TextField2);
                
                String text3Value = "...";
                TextField3 = new JTextField();
                TextField3.setText(text3Value);
                TextField3.setOpaque(false);
                TextField3.setHorizontalAlignment(SwingConstants.CENTER);
                TextField3.setForeground(Color.WHITE);
                TextField3.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
                TextField3.setEditable(false);
                TextField3.setColumns(10);
                TextField3.setBorder(null);
                TextField3.setBackground(Color.WHITE);
                TextField3.setBounds(30, 27, 180, 32);
                Smallpanel3.add(TextField3);
                
                
                String text4Value = "...";
                TextField4 = new JTextField();
                TextField4.setText(text4Value);
                TextField4.setOpaque(false);
                TextField4.setHorizontalAlignment(SwingConstants.CENTER);
                TextField4.setForeground(Color.WHITE);
                TextField4.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
                TextField4.setEditable(false);
                TextField4.setColumns(10);
                TextField4.setBorder(null);
                TextField4.setBackground(Color.WHITE);
                TextField4.setBounds(30, 27, 180, 32);
                Smallpanel4.add(TextField4);
	                
                
                //Greeting Panel
                JPanel GreetingPanel = new JPanel();
                GreetingPanel.setBackground(Color.WHITE);
                GreetingPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
                GreetingPanel.setBounds(21, 12, 1187, 95);
                Mainpanel.add(GreetingPanel);
                GreetingPanel.setLayout(null);
                
                JLabel Icongreeting = new JLabel("");
                Icongreeting.setIcon(new ImageIcon("src/avatar.png"));
                Icongreeting.setBounds(43, 15, 24, 24);
                GreetingPanel.add(Icongreeting);	
                
                
                
                Textgreeting = new JTextArea();
                Textgreeting.setEditable(false);
                //Malalagyan ng data
                Textgreeting.setBounds(82, 12, 573, 35);
                Textgreeting.setBorder(null);
             
                Textgreeting.setFont(AcadSyncFontManager1.getFont("Poppins-Bold"));
                GreetingPanel.add(Textgreeting);
                Textgreeting.setColumns(10);
                //addition of command for fetching student name to appear on set text 
                
                
                JLabel Labellogin = new JLabel("Last Login:");
                Labellogin.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold1"));
                Labellogin.setBounds(43, 55, 80, 16);
                GreetingPanel.add(Labellogin);
                
                
                 
                Logintext = new JTextField();
                Logintext.setBackground(new Color(255, 255, 255));
                Logintext.setBorder(null);
                Logintext.setEditable(false);
                Logintext.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold1"));
                Logintext.setBounds(135, 53, 277, 20);
                GreetingPanel.add(Logintext);
                Logintext.setColumns(10);          
                //command to show last login to appear on set text
                
	                
	            //Info Panel    
                CustomSmallRoundPanel InfoPanel = new CustomSmallRoundPanel(20);
                InfoPanel.setBounds(46, 348, 1127, 173);
                InfoPanel.setBackground(new Color(250, 165, 139));
                InfoPanel.setLayout(null);
				Mainpanel.add(InfoPanel);  
					
				JLabel NoticeLabel = new JLabel("Notice:");
				NoticeLabel.setBounds(41, 35, 70, 14);
				NoticeLabel.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                InfoPanel.add(NoticeLabel);
                
                
                JTextArea InfoText = new JTextArea();
                InfoText.setWrapStyleWord(true);
                InfoText.setBounds(40, 63, 1043, 80);
                InfoText.setLineWrap(true);
                InfoText.setFont(AcadSyncFontManager1.getFont("Poppins-SemiBold"));
                InfoText.setText("Welcome to AcadSync² Student Portal. Please take note that school records are not directly connected to the website records. Therefore, enrollment transactions made in campus will not immediately reflect in the website. For questions, inquiries or bug reports regarding the portal please contact the MIS office.");
                InfoText.setEditable(false);
                InfoText.setOpaque(false);
                InfoPanel.add(InfoText);
                
                
             // Load Dashboard
				loadDashboard();

	        
		}
	  
    
	// Load the student profile and enrollment status, and populate the dashboard
		private void loadDashboard() {
	    // Get student number from session
	    int studentNumber = Session.getStudentNumber();
	    String profileQuery = "SELECT * FROM student_profile WHERE StudentNumber = ?";
	    String enrollmentQuery = "SELECT * FROM enrollment_status WHERE StudentNumber = ? AND Status = 'Enrolled'";

	    // Establish database connection
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             PreparedStatement profileStmt = connection.prepareStatement(profileQuery);
	             PreparedStatement enrollmentStmt = connection.prepareStatement(enrollmentQuery)) {

	            profileStmt.setInt(1, studentNumber);
	            enrollmentStmt.setInt(1, studentNumber);

	            ResultSet rsProfile = profileStmt.executeQuery();
	            ResultSet rsEnrollment = enrollmentStmt.executeQuery();

	            if (rsProfile.next()) {
	                // Retrieve profile details from the result set
	                String fname = rsProfile.getString("FirstName");
	                String course = rsProfile.getString("Course");
	                String lastLog = String.valueOf(rsProfile.getTimestamp("LastLog"));

	                // Default values for enrollment status if not enrolled
	                String yearLvl = " ";
	                String status = " ";
	                String academicyr = " ";
	                String semester = " ";

	                if (rsEnrollment.next()) {
	                	academicyr = rsEnrollment.getString("AcademicYear");
	                	semester = rsEnrollment.getString("Semester");
	                    yearLvl = rsEnrollment.getString("YearLevel");
	                    status = rsEnrollment.getString("State");      
	                }

	                // Update UI elements
	                Textgreeting.setText("Hi! " + fname);
	                Logintext.setText(lastLog);
	                TextField1.setText(academicyr);
	                TextField2.setText(semester);
	                TextField3.setText(status);
	                TextField4.setText(course + "-" + yearLvl);
	                
	                

	            } else {
	                JOptionPane.showMessageDialog(null, "Student profile not found.", "Error", JOptionPane.ERROR_MESSAGE);
	            }

	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error loading student profile.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	    } catch (ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(
	                this,
	                "MySQL driver not found: " + e.getMessage(),
	                "Driver Error",
	                JOptionPane.ERROR_MESSAGE);
	    }
		}
	
}
