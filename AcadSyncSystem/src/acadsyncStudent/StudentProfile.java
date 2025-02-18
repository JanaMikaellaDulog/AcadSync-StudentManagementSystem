package acadsyncStudent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import acadsyncFile.AcadSyncFontManager;
import acadsyncFile.DatabaseCredentials;


public class StudentProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private CustomTextField TextName;
	private CustomTextField TextBirth;
	private CustomTextField TextSex;
	private CustomTextField TextReligion;
	private CustomTextField TextStatus;
	private CustomTextField TextAge;
	private CustomTextField TextAdd;
	private CustomTextField TextGuard;
	private CustomTextField TextNum;
	private CustomTextField TextCourse;
	private CustomTextField TextStudNo;
	private CustomTextField TextEmail;
	private CustomTextField TextPass;

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
					StudentProfile frame = new StudentProfile();
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
	public StudentProfile() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Student Profile");
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
	        JPanel panel = new JPanel();
	        panel.setLayout(null);
	        panel.setOpaque(false);
	        panel.setPreferredSize(new Dimension(300, 900));
	        
	        // Add some components to the panel
	        JPanel PersonalDetails = new JPanel();
	        PersonalDetails.setBackground(new Color(255, 255, 255));
	        PersonalDetails.setBounds(0, 0, 902, 1000);
	        panel.add(PersonalDetails);
	        PersonalDetails.setLayout(null);
	        
	        JLabel Label1 = new JLabel("Personal Details");
	        Label1.setBounds(199, 0, 178, 31);
	        Label1.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        PersonalDetails.add(Label1);
	        
	        JLabel FNameLabel = new JLabel("Full Name");
	        FNameLabel.setBounds(30, 42, 111, 30);
	        FNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        PersonalDetails.add(FNameLabel);
	        
	        JLabel BirthLabel = new JLabel("Birth Date");
	        BirthLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        BirthLabel.setBounds(30, 90, 111, 30);
	        PersonalDetails.add(BirthLabel);
	        
	        JLabel SexLabel = new JLabel("Sex");
	        SexLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        SexLabel.setBounds(30, 140, 111, 30);
	        PersonalDetails.add(SexLabel);
	        
	        JLabel ReligionLabel = new JLabel("Religion");
	        ReligionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        ReligionLabel.setBounds(30, 191, 111, 30);
	        PersonalDetails.add(ReligionLabel);
	        
	        JLabel CivilLabel = new JLabel("Civil Status");
	        CivilLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        CivilLabel.setBounds(30, 245, 111, 30);
	        PersonalDetails.add(CivilLabel);
	        
	        JLabel AgeLabel = new JLabel("Age");
	        AgeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        AgeLabel.setBounds(30, 300, 111, 30);
	        PersonalDetails.add(AgeLabel);
	        
	        JLabel AddressLabel = new JLabel("Address");
	        AddressLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        AddressLabel.setBounds(30, 391, 111, 30);
	        PersonalDetails.add(AddressLabel);
	        
	        JLabel GuardianLabel = new JLabel("Guardian");
	        GuardianLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        GuardianLabel.setBounds(30, 445, 111, 30);
	        PersonalDetails.add(GuardianLabel);
	        
	        JLabel ContactNoLabel = new JLabel("Contact Number");
	        ContactNoLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        ContactNoLabel.setBounds(30, 499, 200, 30);
	        PersonalDetails.add(ContactNoLabel);
	        
	        JLabel CourseLabel = new JLabel("Course");
	        CourseLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        CourseLabel.setBounds(30, 590, 111, 30);
	        PersonalDetails.add(CourseLabel);
	        
	        JLabel StudNoLabel = new JLabel("Student Number");
	        StudNoLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        StudNoLabel.setBounds(30, 644, 200, 30);
	        PersonalDetails.add(StudNoLabel);
	        
	        JLabel AccNameLabel = new JLabel("AcadSync² Email Ad");
	        AccNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        AccNameLabel.setBounds(30, 730, 200, 30);
	        PersonalDetails.add(AccNameLabel);
	        
	        JLabel PasswordLabel = new JLabel("Password");
	        PasswordLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	        PasswordLabel.setBounds(30, 784, 111, 30);
	        PersonalDetails.add(PasswordLabel);
	        
	        //for all textfields to add function to have show text details 
	        
	        TextName = new CustomTextField(20);
	        TextName.setEnabled(false);
	        TextName.setEditable(false);
	        TextName.setLocation(199, 42);
	        TextName.setSize(539, 33);
	        TextName.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        ((CustomTextField) TextName).setShadowColor(Color.LIGHT_GRAY);
	        PersonalDetails.add(TextName);
	        
	        TextBirth = new CustomTextField(20);
	        TextBirth.setEnabled(false);
	        TextBirth.setEditable(false);
	        TextBirth.setShadowColor(Color.LIGHT_GRAY);
	        TextBirth.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextBirth.setBounds(199, 90, 539, 33);
	        PersonalDetails.add(TextBirth);
	        
	        
	        TextSex = new CustomTextField(20);
	        TextSex.setEnabled(false);
	        TextSex.setEditable(false);
	        TextSex.setShadowColor(Color.LIGHT_GRAY);
	        TextSex.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextSex.setBounds(199, 140, 539, 33);
	        PersonalDetails.add(TextSex);	            
	        
	        
	        TextReligion = new CustomTextField(20);
	        TextReligion.setEnabled(false);
	        TextReligion.setEditable(false);
	        TextReligion.setShadowColor(Color.LIGHT_GRAY);
	        TextReligion.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextReligion.setBounds(199, 191, 539, 33);
	        PersonalDetails.add(TextReligion);
	                          
	        
	        TextStatus = new CustomTextField(20);
	        TextStatus.setEnabled(false);
	        TextStatus.setEditable(false);
	        TextStatus.setShadowColor(Color.LIGHT_GRAY);
	        TextStatus.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextStatus.setBounds(199, 245, 539, 33);
	        PersonalDetails.add(TextStatus);
	     
	        
	        TextAge = new CustomTextField(20);
	        TextAge.setEnabled(false);
	        TextAge.setEditable(false);
	        TextAge.setShadowColor(Color.LIGHT_GRAY);
	        TextAge.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextAge.setBounds(199, 300, 539, 33);
	        PersonalDetails.add(TextAge);
	        
	        TextAdd = new CustomTextField(20);
	        TextAdd.setShadowColor(Color.LIGHT_GRAY);
	        TextAdd.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextAdd.setEnabled(false);
	        TextAdd.setEditable(false);
	        TextAdd.setBounds(199, 391, 539, 33);
	        PersonalDetails.add(TextAdd);
	                
	        
	        TextGuard = new CustomTextField(20);
	        TextGuard.setShadowColor(Color.LIGHT_GRAY);
	        TextGuard.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextGuard.setEnabled(false);
	        TextGuard.setEditable(false);
	        TextGuard.setBounds(199, 445, 539, 33);
	        PersonalDetails.add(TextGuard);
	                
	        
	        TextNum = new CustomTextField(20);
	        TextNum.setShadowColor(Color.LIGHT_GRAY);
	        TextNum.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextNum.setEnabled(false);
	        TextNum.setEditable(false);
	        TextNum.setBounds(199, 499, 539, 33);
	        PersonalDetails.add(TextNum);
	             
	        
	        TextCourse = new CustomTextField(20);
	        TextCourse.setShadowColor(Color.LIGHT_GRAY);
	        TextCourse.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextCourse.setEnabled(false);
	        TextCourse.setEditable(false);
	        TextCourse.setBounds(199, 590, 539, 33);
	        PersonalDetails.add(TextCourse);
	              
	        
	        TextStudNo = new CustomTextField(20);
	        TextStudNo.setShadowColor(Color.LIGHT_GRAY);
	        TextStudNo.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextStudNo.setEnabled(false);
	        TextStudNo.setEditable(false);
	        TextStudNo.setBounds(199, 644, 539, 33);
	        PersonalDetails.add(TextStudNo);
    
	        
	        TextEmail = new CustomTextField(20);
	        TextEmail.setShadowColor(Color.LIGHT_GRAY);
	        TextEmail.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextEmail.setEnabled(false);
	        TextEmail.setEditable(false);
	        TextEmail.setBounds(199, 730, 539, 33);
	        PersonalDetails.add(TextEmail);
	
	        
	        TextPass = new CustomTextField(20);
	        TextPass.setShadowColor(Color.LIGHT_GRAY);
	        TextPass.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        TextPass.setEnabled(false);
	        TextPass.setEditable(false);
	        TextPass.setBounds(199, 784, 539, 33);
	        PersonalDetails.add(TextPass);
	        
	        JLabel ContactLabel = new JLabel("Contact Details");
	        ContactLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        ContactLabel.setBounds(199, 356, 178, 31);
	        PersonalDetails.add(ContactLabel);
	        
	        JLabel EnrollmentLabel = new JLabel("Enrollment Details");
	        EnrollmentLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        EnrollmentLabel.setBounds(199, 558, 178, 31);
	        PersonalDetails.add(EnrollmentLabel);
	        
	        JLabel AccLabel = new JLabel("AcadSync Google Account\r\n");
	        AccLabel.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
	        AccLabel.setBounds(199, 700, 373, 31);
	        PersonalDetails.add(AccLabel);
	        
	        
	        
	        
	        
	        
        
        
        
        
        // Create a JScrollPane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        

        scrollPane.setLocation(25, 182);
        scrollPane.setSize(1195, 471);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Remove background and border of the scroll pane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        // Add the scrollPane to the frame
        contentPane.add(scrollPane, BorderLayout.CENTER);
     
	        
			
			JPanel toppanel = new JPanel();
			toppanel.setBackground(new Color(0, 0, 0));
			toppanel.setBounds(0, 0, 1236, 92);
			contentPane.add(toppanel);
			toppanel.setLayout(null);
			
			
				JButton menubutton = new JButton("");
				menubutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					StudentProfile1 StudentProfileNavOpen = new StudentProfile1 ();
					StudentProfileNavOpen.setVisible(true);
					
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
	            
			
			
			CustomRoundPanel Mainpanel = new CustomRoundPanel(20);
			Mainpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            Mainpanel.setBounds(10, 98, 1218, 565);
			contentPane.add(Mainpanel);
			Mainpanel.setLayout(null);
			
			JPanel GreetingPanel = new JPanel();
			GreetingPanel.setLayout(null);
			GreetingPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
			GreetingPanel.setBackground(Color.WHITE);
			GreetingPanel.setBounds(10, 11, 1198, 60);
			Mainpanel.add(GreetingPanel);
			
			JLabel IconProfile = new JLabel("Student Profile");
			IconProfile.setIcon(new ImageIcon("src/avatar.png"));
			IconProfile.setBounds(43, 15, 24, 24);
			GreetingPanel.add(IconProfile);
			
			JLabel LabelProfile = new JLabel("Student Profile");
			LabelProfile.setBounds(79, 15, 296, 28);
			LabelProfile.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
			GreetingPanel.add(LabelProfile);
			
			
			//Load Student Profile
			loadProfile();
			

	}

//////// METHOD TO LOAD PROFILE

	private void loadProfile() {
		int studentNumber = Session.getStudentNumber();
	   
	    String query = "SELECT * FROM student_profile WHERE StudentNumber = ?";

	    try {
	       // Load MySQL driver
	       Class.forName("com.mysql.cj.jdbc.Driver");

	       // Try-with-resources block to handle database resources
	       try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	       	 PreparedStatement statement = connection.prepareStatement(query)) {

	           statement.setInt(1, studentNumber);                
	           ResultSet rs = statement.executeQuery();

	           if (rs.next()) {
	           	// Populate the UI elements with the retrieved student profile data
	           	String fname = rs.getString("FirstName");
	               String mname = rs.getString("MiddleName");
	               String lname = rs.getString("LastName");
	               String sname = rs.getString("SuffixName");
	               String birth = String.valueOf(rs.getDate("BirthDate"));
	               String sex = rs.getString("Sex");
	               String addr = rs.getString("Address");
	               String religion = rs.getString("Religion");
	               String age = String.valueOf(rs.getInt("Age"));
	               String status = rs.getString("CivilStatus");
	               String guard = rs.getString("Guardian");
	               String num = rs.getString("ContactNumber");
	               String course = rs.getString("Course");
	               String studentno = String.valueOf(rs.getInt("StudentNumber"));
	               String email = rs.getString("AcadSyncEmail");
	               String pass = rs.getString("Password");
	               
	               
	               
	               TextName.setText(fname + " " + mname + " " + lname + " " + (sname != null ? " " + sname : "")); 
	               TextBirth.setText(birth);
		   	       TextSex.setText(sex);
		   	       TextReligion.setText(religion);
		   	       TextStatus.setText(status);
		   	       TextAge.setText(age);
		   	       TextAdd.setText(addr);
		   	       TextGuard.setText(guard);
		   	       TextNum.setText(num);
		   	       TextCourse.setText(course);
		   	       TextStudNo.setText(studentno);
		  	       TextEmail.setText(email);
		   	       TextPass.setText(pass);
	           }
	           else {
	               JOptionPane.showMessageDialog(this, "Student profile not found.", "Error", JOptionPane.ERROR_MESSAGE);
	           }
	       }

	   } catch (ClassNotFoundException e) {
	       JOptionPane.showMessageDialog(
	               this,
	               "MySQL driver not found: " + e.getMessage(),
	               "Driver Error",
	               JOptionPane.ERROR_MESSAGE
	       );
	   } catch (SQLException e) {
	       JOptionPane.showMessageDialog(
	               this,
	               "Error loading student profile.", "Error", JOptionPane.ERROR_MESSAGE);
	   }

	}
						
}
