package acadsyncStudent;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import acadsyncFile.AcadSyncFontManager;
import acadsyncFile.DatabaseCredentials;

public class StudentEnrolledSubs1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JLabel LabelSubs;
	
	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	int studentNumber = Session.getStudentNumber();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentEnrolledSubs1 frame = new StudentEnrolledSubs1();
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
	public StudentEnrolledSubs1() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Enrolled Subjects");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 710);
		setLocationRelativeTo(null);  // Center the frame on the screen
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Create a JPanel for scroll
        CustomEnrollPanel panel = new CustomEnrollPanel(AcadSyncFontManager.getFont("Poppins-Light"), AcadSyncFontManager.getFont("Poppins-Medium"));
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(300, 1200));
        
        
        // Fetch Data for Enrolled Subjects
        String[][] data = CustomEnrollPanel.fetchData(studentNumber); // Pass the student number
        panel.setData(data);
             
        
        // Create a JScrollPane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setLocation(291, 322);
        scrollPane.setSize(929, 331);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Remove background and border of the scroll pane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        // Add the scrollPane to the frame
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        
        
			
			CustomButtonNavigation NavigationButton = new CustomButtonNavigation("NAVIGATION ", 0);
			
	        NavigationButton.addActionListener(new ActionListener() {
	              public void actionPerformed(ActionEvent e) {	         	     
	            	  StudentEnrolledSubs StudendEnrolledsSubsNoNav = new StudentEnrolledSubs();
	            	  StudendEnrolledsSubsNoNav.setVisible(true);
		                 
		                 dispose();
	              }
		     });
	        
	        
	        
	        
	        CustomButtonNavigation DashBoardButton = new CustomButtonNavigation("Dashboard", 0);
            // Add ActionListener
	         DashBoardButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 StudentDashboard StudentDashboardOpen = new StudentDashboard();
		        	 StudentDashboardOpen.setVisible(true);
		        	 
		        	 dispose();
		         }
		     });
	         
	         CustomButtonNavigation StudentProfileButton = new CustomButtonNavigation("Profile",0);
	        
	            // Add ActionListener
	         StudentProfileButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 StudentProfile1 StudentProfileOpen = new StudentProfile1();
		        	 StudentProfileOpen.setVisible(true);
		        	 
		        	 dispose();
		       
		             // Action code here (e.g., open another window or perform login)
		         }
		     });
	         
	         CustomButtonNavigation SectionSubjectButton = new  CustomButtonNavigation("Enrolled Subject", 0);
		        
		        // Add ActionListener
		        SectionSubjectButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {

			             // Action code here (e.g., open another window or perform login)
			         }
			     });
	        
		      CustomButtonNavigation StudentStatusButton = new CustomButtonNavigation("Subjects Offered", 0);
			      
			      StudentStatusButton.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				        	 StudentsSubsOffered1 StudentSubsOfferedOpen = new StudentsSubsOffered1();
				        	 StudentSubsOfferedOpen.setVisible(true);
				        	 
				        	 dispose();
				             // Action code here (e.g., open another window or perform login)
				         }
				     });
		      
		      CustomButtonNavigation StudentGradesButton = new CustomButtonNavigation("Grades", 0);	      

			      StudentGradesButton.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				        	 StudentGrades1 StudentGradesOpen = new StudentGrades1();
				        	 StudentGradesOpen.setVisible(true);
				        	 
				        	 dispose();
				        
				             // Action code here (e.g., open another window or perform login)
				         }
				     });
	         
	        
			      NavigationButton.setIcon(new ImageIcon("src/back.png"));
			      NavigationButton.setBounds(0, 92, 270, 60);
			      NavigationButton.setForeground(new Color(255, 102, 54));
			      NavigationButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
			      // Align text to the left and icon to the right
			      NavigationButton.setHorizontalTextPosition(SwingConstants.LEFT); // Text on the left
			      NavigationButton.setHorizontalAlignment(SwingConstants.CENTER);   // Align content to the left
			      NavigationButton.setIconTextGap(70);  // Adjust gap between text and icon
			      contentPane.add(NavigationButton);
			        
			      DashBoardButton.setIcon(new ImageIcon("src/dashboard.png"));
			      DashBoardButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	     		  DashBoardButton.setBackground(new Color(217, 217, 217));
	     		  DashBoardButton.setBounds(-77, 153, 347, 60);
	     		  DashBoardButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
	     		  DashBoardButton.setIconTextGap(20);
	     		  contentPane.add(DashBoardButton);
			       
			        
	     		  StudentProfileButton.setMargin(new Insets(0, 10, 0, 0));
		          StudentProfileButton.setIconTextGap(21);
		          StudentProfileButton.setIcon(new ImageIcon("src/avatar.png"));
		          StudentProfileButton.setForeground(Color.BLACK);
		          StudentProfileButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
		          StudentProfileButton.setBackground(new Color(217, 217, 217));
		          StudentProfileButton.setBounds(-117, 212, 387, 60);
			      contentPane.add(StudentProfileButton);
			        
			        
			      SectionSubjectButton.setIconTextGap(21);
				  SectionSubjectButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
				  SectionSubjectButton.setIcon(new ImageIcon("src/books-stack-of-three.png"));
				  SectionSubjectButton.setForeground(Color.BLACK);
				  SectionSubjectButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
				  SectionSubjectButton.setBounds(-26, 271, 296, 60);
				  contentPane.add(SectionSubjectButton);
					
					
				  StudentStatusButton.setIconTextGap(21);
				  StudentStatusButton.setIcon(new ImageIcon("src/book.png"));
				  StudentStatusButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
				  StudentStatusButton.setForeground(Color.BLACK);
				  StudentStatusButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
				  StudentStatusButton.setBounds(-17, 329, 286, 60);
			      contentPane.add(StudentStatusButton);
					
					
			      StudentGradesButton.setIconTextGap(21);
				  StudentGradesButton.setIcon(new ImageIcon("src/best.png"));
				  StudentGradesButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
				  StudentGradesButton.setForeground(Color.BLACK);
				  StudentGradesButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
				  StudentGradesButton.setBounds(-99, 388, 369, 60);
				  contentPane.add(StudentGradesButton);
	        
	         
	        
			
			JPanel toppanel = new JPanel();
			toppanel.setBackground(new Color(0, 0, 0));
			toppanel.setBounds(0, 0, 1236, 92);
			contentPane.add(toppanel);
			toppanel.setLayout(null);
			
			
				JButton menubutton = new JButton("");
				menubutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StudentEnrolledSubs StudendEnrolledsSubsNoNav = new StudentEnrolledSubs();
		            	  StudendEnrolledsSubsNoNav.setVisible(true);
			                 
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
				TopPortalLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
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
	            
			
		
			
	         JLabel NavigationBackground = new JLabel("");
			 NavigationBackground.setBounds(0, 90, 270, 736);
			 contentPane.add(NavigationBackground);
			 NavigationBackground.setIcon(new ImageIcon("src/Group 89.png"));
			
			CustomRoundPanel Mainpanel = new CustomRoundPanel(20);
			Mainpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            Mainpanel.setBounds(279, 98, 949, 565);
			contentPane.add(Mainpanel);
			Mainpanel.setLayout(null);
			
			
			//Top
			JPanel GreetingPanel = new JPanel();
			GreetingPanel.setLayout(null);
			GreetingPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
			GreetingPanel.setBackground(Color.WHITE);
			GreetingPanel.setBounds(10, 11, 929, 60);
			Mainpanel.add(GreetingPanel);
			
			JLabel IconProfile = new JLabel("");
			IconProfile.setIcon(new ImageIcon("src/books-stack-of-three.png"));
			IconProfile.setBounds(43, 15, 24, 24);
			GreetingPanel.add(IconProfile);
			
			
			LabelSubs = new JLabel("");
			LabelSubs.setBounds(79, 15, 462, 28);
			LabelSubs.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
			GreetingPanel.add(LabelSubs);
			
			
			 //Info Panel    
            CustomSmallRoundPanel InfoPanel = new CustomSmallRoundPanel(20);
            InfoPanel.setBounds(21, 90, 905, 120);
            InfoPanel.setBackground(new Color(250, 165, 139));
            InfoPanel.setLayout(null);
			Mainpanel.add(InfoPanel);  
				
			JLabel ReminderLabel = new JLabel("Reminder:");
			ReminderLabel.setBounds(39, 32, 175, 14);
			ReminderLabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
            InfoPanel.add(ReminderLabel);
            
            
            JTextArea InfoText = new JTextArea();
            InfoText.setWrapStyleWord(true);
            InfoText.setBounds(40, 63, 822, 43);
            InfoText.setLineWrap(true);
            InfoText.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
            InfoText.setText("Assigned schedule, instructor and other information shown in the table may change without prior notice.");
            InfoText.setEditable(false);
            InfoText.setOpaque(false);
            InfoPanel.add(InfoText);
            
            
            //Load text for enrollment year and semester
            enrollYearSem();

	}
	
//METHOD TO FETCH CURRENT ENROLLED SUBS AY AND SEM
  private void enrollYearSem() {
	String query = "SELECT AcademicYear, Semester FROM enrollment_status WHERE StudentNumber = ? AND Status = 'Enrolled'";
    
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
        	

            // Set the student number parameter in the query
            statement.setInt(1, studentNumber);

            try (ResultSet rs = statement.executeQuery()) {
                // Process the result set
                while (rs.next()) {
                    String academicYear = rs.getString("AcademicYear");
                    String semester = rs.getString("Semester");
                    
                    LabelSubs.setText("Subjects for " + semester + " - "  + academicYear); 
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

}
}