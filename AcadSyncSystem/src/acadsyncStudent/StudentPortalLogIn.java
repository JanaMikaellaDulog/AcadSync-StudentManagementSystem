// -------------------------------------- Dependencies --------------------------------------
package acadsyncStudent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import acadsyncFile.DatabaseCredentials;



//--------------------------------------------------------------------------------------------




public class StudentPortalLogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtStudentNumber;
    
    
    // Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
  	private JPasswordField StudentPasswordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
 	 

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentPortalLogIn frame = new StudentPortalLogIn();
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
    public StudentPortalLogIn() {
    	UIManager.put("Button.select", new Color(232, 0, 85)); // Change the default "pressed" color
    	
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Student Portal Log In");
        setResizable(false);  // Disable resizing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 710);  // Set the frame's size and position 
        setUndecorated(false);
        setLocationRelativeTo(null);  // Center the frame on the screen

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
			                
			    
			 
        
			    JButton LogInButton = new JButton("Log In >");
			
			     // Add ActionListener
			        	 LogInButton.addActionListener(new ActionListener() {
			        		    public void actionPerformed(ActionEvent e) {
			        		        int StudentNumber;
			        		        String Password = new String(StudentPasswordField.getPassword()).trim(); // Use getPassword for JPasswordField

			        		        try {
			        		            StudentNumber = Integer.parseInt(txtStudentNumber.getText().trim()); // Parse as integer
 
			        		            // Hash the password
			        		            //String hashedPassword = hashPassword(Password);

			        		            // Validate login with hashed password
			        		            if (validateLogin(StudentNumber, Password)) {
			        		                // Store the student number in the session
			        		                Session.setStudentNumber(StudentNumber);

			        		                // Open Profile Frame on successful login
			        		                StudentDashboard1 OpenDashboard = new StudentDashboard1();
			        		                OpenDashboard.setVisible(true);
			        		                dispose();  // Close the login frame

			        		            } else {
			        		                JOptionPane.showMessageDialog(
			        		                        StudentPortalLogIn.this,
			        		                        "Invalid Student number or Password.",
			        		                        "Login Error",
			        		                        JOptionPane.ERROR_MESSAGE
			        		                );
			        		            }
			        		        } catch (NumberFormatException ex) {
			        		            JOptionPane.showMessageDialog(
			        		                    StudentPortalLogIn.this,
			        		                    "Please enter a valid Student Number.",
			        		                    "Input Error",
			        		                    JOptionPane.WARNING_MESSAGE
			        		            );
			        		        }
			        		    }
			        		});

			        
			
			     // Add MouseListener for hover effect
			    LogInButton.addMouseListener(new MouseAdapter() {
			         @Override
			         public void mouseEntered(MouseEvent e) {
			             // On mouse enter, change button background and text color
			             LogInButton.setBackground(new Color(232, 33, 106));  // Hover background color
			         }
			         
				     @Override
				     public void mouseExited(MouseEvent e) {
				          // On mouse enter, change button background and text color
				          LogInButton.setBackground(new Color(232, 0, 85));  // Hover background color
				         }		
			     });
			    
			    JLabel StudentPasswordLabel = new JLabel("Student Password");
			    StudentPasswordLabel.setForeground(Color.GRAY);
			    StudentPasswordLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
			    StudentPasswordLabel.setBounds(955, 415, 128, 13);
			    contentPane.add(StudentPasswordLabel);
			    
			    JLabel StudentNumberLabel = new JLabel("Student Number");
			    StudentNumberLabel.setForeground(Color.GRAY);
			    StudentNumberLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
			    StudentNumberLabel.setBounds(958, 305, 128, 13);
			    contentPane.add(StudentNumberLabel);
			    
			    JButton EyeButton = new JButton("");
			    ImageIcon originalIcon = new ImageIcon("src/hide.png");
				Image originalImage = originalIcon.getImage();
				Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
				EyeButton.setIcon(new ImageIcon(resizedImage));
			    EyeButton.setOpaque(true);
			    EyeButton.setFocusPainted(false);
			    EyeButton.setContentAreaFilled(true);
			    EyeButton.setBorderPainted(false);
			    EyeButton.setBackground(new Color(245, 245, 247));
			    EyeButton.setBounds(1095, 365, 29, 21);
				EyeButton.addActionListener(new ActionListener() {
					    private boolean showPassword = false; // Toggle state for password visibility
		
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        showPassword = !showPassword; // Toggle the state
		
					        if (showPassword) {
					            StudentPasswordField.setEchoChar((char) 0); // Show plain text
					            ImageIcon originalIcon = new ImageIcon("src/eye.png");
								Image originalImage = originalIcon.getImage();
								Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
								EyeButton.setIcon(new ImageIcon(resizedImage));
								
					        } else {
					        	StudentPasswordField.setEchoChar('*'); // Mask with '*'
					        	ImageIcon originalIcon = new ImageIcon("src/hide.png");
								Image originalImage = originalIcon.getImage();
								Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
								EyeButton.setIcon(new ImageIcon(resizedImage));
					        }
					    }
					});
				
			    contentPane.add(EyeButton);
			    
			    StudentPasswordField = new JPasswordField();
			    StudentPasswordField.setHorizontalAlignment(SwingConstants.LEFT);
			    StudentPasswordField.setForeground(Color.GRAY);
			    StudentPasswordField.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
			    StudentPasswordField.setEchoChar('*');
			    StudentPasswordField.setColumns(10);
			    StudentPasswordField.setBorder(new LineBorder(new Color(245, 245, 247), 0));
			    StudentPasswordField.setBackground(new Color(245, 245, 247));
			    StudentPasswordField.setBounds(902, 365, 181, 19);
			    contentPane.add(StudentPasswordField);
			    
			    
			    
			    
			    // Set initial button properties
			    LogInButton.setForeground(new Color(0, 0, 0));  // Initial text color
			    LogInButton.setBackground(new Color(232, 0, 85));  // Initial background color 
			    LogInButton.setBorder(null);  // No border
			    LogInButton.setFont(new Font("Holtwood One SC", Font.PLAIN, 17));  // Font settings
			
			     // Set button to be opaque and allow background color change
			    LogInButton.setOpaque(true);  // Make sure the button is opaque
			    LogInButton.setContentAreaFilled(true);  // Prevent the default button background rendering
			    LogInButton.setBorderPainted(false);  // Disable border painting
			    LogInButton.setFocusPainted(false);   // Disable focus painting
			    LogInButton.setBounds(1023, 452, 128, 47);  // Button size and position
			
			    contentPane.add(LogInButton);
			    
			    JButton ForgotPassButton = new JButton("Forgot Password?");
			    ForgotPassButton.setForeground(new Color(255, 102, 54));
			    ForgotPassButton.setFont(new Font("Arial", Font.BOLD, 12));
			    ForgotPassButton.setBackground(new Color(240, 240, 240));
			    
			     // Set button to be opaque and allow background color change
			    ForgotPassButton.setOpaque(true);  // Make sure the button is opaque
			    ForgotPassButton.setContentAreaFilled(false);  // Prevent the default button background rendering
			    ForgotPassButton.setBorderPainted(false);  // Disable border painting
			    ForgotPassButton.setFocusPainted(false);   // Disable focus painting
			    ForgotPassButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		StudentPortalForgotPass  OpenStudentLogin = new StudentPortalForgotPass();
		        		OpenStudentLogin.setVisible(true);
		        		
		        		dispose();
		        	}
		        });
			    
			   
			    ForgotPassButton.setBounds(932, 510, 151, 21);
			    contentPane.add(ForgotPassButton);
			    
			    
			    
			    JLabel Instruction1Label3 = new JLabel("<html>" +
			    		"<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Fill out the provided form for assistance.</div></html>");
			    Instruction1Label3.setForeground(Color.WHITE);
			    Instruction1Label3.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label3.setBounds(88, 586, 368, 45);
			    
			    contentPane.add(Instruction1Label3);
			    
			    
			    JLabel Instruction1Label2 = new JLabel("<html>" +
			    		"<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Ensure your account is activated and that your student number and</div>" +
			    		"<div style='padding-bottom:10px;'>password match our records. If you forgot your password, click the recovery link.</div>" +
			    		"</html>");
			    Instruction1Label2.setForeground(new Color(255, 255, 255));
			    Instruction1Label2.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label2.setBounds(88, 468, 645, 96);
			    
			    contentPane.add(Instruction1Label2);
			    
			    
			    JLabel Instruction1Label = new JLabel("<html>" +
			    	    "<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Register by providing the requested information, ensuring your email is</div>" +
			    	    "<div style='padding-bottom:10px;'>valid and correctly formatted. Only students enrolled since the Academic Year</div>" +
			    	    "<div style='padding-bottom:10px;'>2014-2015 can register. Graduate students (except TCP) should register on the </div>" +
			    	    "<div style='padding-bottom:10px;'>main campus website.</div>" +
			    	    "</html>");
			    Instruction1Label.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label.setForeground(new Color(255, 255, 255));
			    Instruction1Label.setBounds(88, 312, 645, 133); // Adjust the bounds to fit the text
			    
			    contentPane.add(Instruction1Label);
			    
			    
			    JLabel ForStudentConcernsLabel = new JLabel("   • For Student Portal Concerns");
			    ForStudentConcernsLabel.setForeground(Color.WHITE);
			    ForStudentConcernsLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    ForStudentConcernsLabel.setBounds(78, 550, 320, 27);
			    contentPane.add(ForStudentConcernsLabel);
			    
			    JLabel LogInIssuesLabel = new JLabel("   • Log In Issues");
			    LogInIssuesLabel.setForeground(Color.WHITE);
			    LogInIssuesLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    LogInIssuesLabel.setBounds(78, 443, 181, 27);
			    contentPane.add(LogInIssuesLabel);
			    
			    JLabel CreatingAccountLabel = new JLabel("   • Creating Account");
			    CreatingAccountLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    CreatingAccountLabel.setForeground(new Color(255, 255, 255));
			    CreatingAccountLabel.setBounds(78, 275, 214, 27);
			    contentPane.add(CreatingAccountLabel);
			    
			    JLabel TitleLabel = new JLabel(" AcadSync²");
			    TitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
			    TitleLabel.setForeground(new Color(255, 102, 54));
			    TitleLabel.setFont(new Font("Arial", Font.BOLD, 52));
			    TitleLabel.setBounds(67, 176, 521, 60);
			    contentPane.add(TitleLabel);
			    
			    // Create a JLabel to hold the icon
			    JLabel SPIcon = new JLabel("");
			    ImageIcon originalIcon1 = new ImageIcon("src/portal.png");

			    // Scale the image to fit the JLabel's size (keep the original aspect ratio)
			    Image originalImage1 = originalIcon1.getImage();
			    Image scaledImage1 = originalImage1.getScaledInstance(56, 54, Image.SCALE_SMOOTH); // Adjust width and height as necessary
			    SPIcon.setIcon(new ImageIcon(scaledImage1));

			    // Set the bounds for the icon
			    SPIcon.setBounds(78, 112, 58, 54);
			    SPIcon.setFocusable(false);   // Disable focus painting
			    contentPane.add(SPIcon);
			    
			    JLabel StudentPortalLabel = new JLabel("Student Portal");
			    StudentPortalLabel.setForeground(Color.WHITE);
			    StudentPortalLabel.setFont(new Font("Arial", Font.BOLD, 52));
			    StudentPortalLabel.setBounds(151, 107, 521, 70);
			    contentPane.add(StudentPortalLabel);
			    
			    JLabel LitttleTitleLabel = new JLabel(" AcadSync²");
			    LitttleTitleLabel.setForeground(new Color(255, 255, 255));
			    LitttleTitleLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    LitttleTitleLabel.setBounds(78, 41, 115, 27);
			    contentPane.add(LitttleTitleLabel);
                
                txtStudentNumber = new JTextField();
                txtStudentNumber.setBackground(new Color(245, 245, 247));
                txtStudentNumber.setColumns(10);
                txtStudentNumber.setForeground(new Color(128, 128, 128));
                txtStudentNumber.setFont(AcadSyncFontManager1.getFont("Poppins-Medium2"));
                txtStudentNumber.setBorder(new LineBorder(new Color(245, 245, 247), 0));  // Set a custom border with a specific color and thickness
                txtStudentNumber.setBounds(902, 257, 181, 19);
                contentPane.add(txtStudentNumber);
        
                // Add text labels over the image
                JLabel LogInLabel = new JLabel("Log Into your account");
                LogInLabel.setFont(new Font("Holtwood One SC", Font.PLAIN, 17)); // Larger font for visibility
                LogInLabel.setForeground(new Color(0, 0, 0));  // White text
                LogInLabel.setBounds(870, 160, 265, 45);  // Adjust position to be above image
                contentPane.add(LogInLabel);

		        // Create a JLabel to hold the image (initialize before using it)
		        JLabel BgFrameLabel = new JLabel();
		        BgFrameLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the image in the label
		        BgFrameLabel.setBounds(0, 0 , 1236, 673);  // Set the label to take the full window size
		        contentPane.add(BgFrameLabel);
		        
		        // Load and scale the image
		        try {
		            // Load the image from file
		            ImageIcon originalIconPS = new ImageIcon("src/Student Portal Log In.png");
		            Image StudentPortalImage = originalIconPS.getImage();
		
		            // Scale the image to fit the content pane size
		            Image scaledImage = StudentPortalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		
		            // Create a new ImageIcon with the scaled image
		            ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		            // Set the scaled image as the JLabel's icon
		            BgFrameLabel.setIcon((scaledIcon));
		            
		        } catch (Exception e) {
		            // Handle any error related to loading or scaling the image
		            e.printStackTrace();
        }
    }
    
    
    
    
    
					    /// Method to hash the password
						private String hashPassword(String password) {
						    try {
						        MessageDigest md = MessageDigest.getInstance("SHA-256");
						        byte[] hashedBytes = md.digest(password.getBytes());
						        return Base64.getEncoder().encodeToString(hashedBytes);
						    } catch (NoSuchAlgorithmException e) {
						        e.printStackTrace();
						        return null;
						    }
						}
						 
						 
					

					    //METHOD TO VALIDATE LOGIN   
						private boolean validateLogin(int StudentNumber, String Password) {
						    boolean isValid = false;

						    String selectQuery = "SELECT StudentNumber FROM student_profile WHERE StudentNumber = ? AND Password = ?";
						    String updateQuery = "UPDATE student_profile SET LastLog = NOW() WHERE StudentNumber = ?";

						    try {
						        Class.forName("com.mysql.cj.jdbc.Driver");

						        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
						             PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
						             PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

						            // Hash the password before validating
						            String hashedPassword = hashPassword(Password);

						            selectStmt.setInt(1, StudentNumber);
						            selectStmt.setString(2, hashedPassword);
						            ResultSet rs = selectStmt.executeQuery();

						            if (rs.next()) {
						                isValid = true;

						                // Update last login time
						                updateStmt.setInt(1, StudentNumber);
						                updateStmt.executeUpdate();
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
						                "Database error: " + e.getMessage(),
						                "Database Error",
						                JOptionPane.ERROR_MESSAGE
						        );
						    }

						    return isValid;
						}
						
}
